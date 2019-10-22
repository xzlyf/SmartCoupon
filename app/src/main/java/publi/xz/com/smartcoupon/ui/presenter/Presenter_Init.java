package publi.xz.com.smartcoupon.ui.presenter;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import com.alibaba.baichuan.trade.biz.login.AlibcLoginCallback;
import com.google.gson.Gson;
import com.xz.com.log.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import publi.xz.com.smartcoupon.constant.Local;
import publi.xz.com.smartcoupon.entity.UserNetInfo;
import publi.xz.com.smartcoupon.ui.InitActivity;
import publi.xz.com.smartcoupon.ui.SettingActivity;
import publi.xz.com.smartcoupon.ui.model.IModel;
import publi.xz.com.smartcoupon.ui.model.Model;
import publi.xz.com.smartcoupon.utils.SharedPreferencesUtil;

public class Presenter_Init {
    private static InitActivity view;

    public Presenter_Init(InitActivity view) {
        Presenter_Init.view = view;
    }

    /**
     * 请求网络
     *
     * @param url
     * @return
     */
    private static String getRespone(String url) {

        try {
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .build();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = null;
            response = client.newCall(request).execute();
            String responseData = response.body().string();
            return responseData;
        } catch (IOException e) {
            e.printStackTrace();
            LogUtil.w("请求失败链接：" + url);

        }
        return "";

    }

    /**
     * 获取 热搜词数据
     */
    public static class GetDetailFromNet implements Runnable {
        //屏障点（集结点），必须所有人达到集合点才能继续后面的任务
        CyclicBarrier ch;

        public GetDetailFromNet(CyclicBarrier ch ) {
            this.ch = ch;
        }

        @Override
        public void run() {
            String data = getRespone(Local.HOT_WORD);
            JSONObject obj = null;
            try {
//                    LogUtil.w("热搜词数据"+data);
                obj = new JSONObject(data);
                if (obj.getString("er_msg").equals("请求成功")) {
                    //保存json到本地
                    SharedPreferencesUtil.saveJson(view, "HOT_WORD", data);
                    Local.state.put("热搜词", true);
                } else {
                    //失败处理
                    Local.state.put("热搜词", false);

                }

            } catch (JSONException e) {
                e.printStackTrace();
                Local.state.put("热搜词", false);

            }

            try {
                ch.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取用户网络位置 IP  服务商信息
     */
    public static class GetUserIpFromNet implements Runnable {
        //屏障点（集结点），必须所有人达到集合点才能继续后面的任务
        CyclicBarrier ch;

        public GetUserIpFromNet(CyclicBarrier ch) {
            this.ch = ch;
        }

        @Override
        public void run() {
            String data = getRespone(Local.GET_USER_IP_URL);
            JSONObject obj = null;

            try {
//                    LogUtil.w("用户信息"+data);
                obj = new JSONObject(data);
                if (obj.getString("code").equals("1")) {
                    JSONObject obj2 = obj.getJSONObject("data");
                    if (obj2 != null) {
                        Gson gson = new Gson();
                        UserNetInfo info = gson.fromJson(obj2.toString(), UserNetInfo.class);
                        //赋值给全局变量
                        Local.self.city = info.getCity();
                        Local.self.ip = info.getIp();
                        Local.self.isp = info.getIsp();
                        Local.self.province = info.getProvince();
                        Local.state.put("用户信息", true);

                    }


                } else if (obj.getString("code").equals("0")) {
                    Local.state.put("用户信息", false);

                }

            } catch (JSONException e1) {
                e1.printStackTrace();
                Local.state.put("用户信息", false);

            }

            try {
                ch.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 获取服务器时间
     */
    public static class GetServerTime implements Runnable {
        //屏障点（集结点），必须所有人达到集合点才能继续后面的任务
        CyclicBarrier ch;

        public GetServerTime(CyclicBarrier ch) {
            this.ch = ch;
        }

        @Override
        public void run() {
            String data = getRespone(Local.GET_SERVER_TIME);
            JSONObject obj = null;

            try {
                //    LogUtil.w("网络时间："+data);
                obj = new JSONObject(data);
                if (obj.getString("code").equals("1")) {
                    JSONObject obj2 = obj.getJSONObject("data");
                    if (obj2 != null) {

                        Local.self.server_time = obj2.getLong("time");
                        Local.state.put("服务器时间", true);
                    }


                } else if (obj.getString("code").equals("0")) {

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


            try {
                ch.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 检查更新
     */
    public static class CheckUpdate implements Runnable {
        //屏障点（集结点），必须所有人达到集合点才能继续后面的任务
        CyclicBarrier ch;

        public CheckUpdate(CyclicBarrier ch) {
            this.ch = ch;
        }

        @Override
        public void run() {
            String data = getRespone(Local.UPDATE_SERVER);
            JSONObject obj = null;

            try {
//                    LogUtil.d("更新数据"+data);
                obj = new JSONObject(data);
                if (obj.getInt("value") == 1) {
                    JSONObject obj2 = obj.getJSONObject("data");
                    //保存json到本地
                    SharedPreferencesUtil.saveJson(view, "UPDATE_DATA", obj2.toString());
                    Local.state.put("更新信息", true);
                } else {
                    //服务器问题
                    Local.state.put("更新信息", false);
                }

            } catch (JSONException e) {
                e.printStackTrace();
                //解析问题
                Local.state.put("更新信息", false);
            }


            try {
                ch.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 远程控制
     * 检查死亡开关
     * state 1 正常使用
     * state 0 软件关闭
     */
    public static class CheckState implements Runnable {
        //屏障点（集结点），必须所有人达到集合点才能继续后面的任务
        CyclicBarrier ch;
        int i;
        public CheckState(CyclicBarrier ch) {
            this.ch = ch;
        }

        @Override
        public void run() {
            String data = getRespone(Local.STATE_SERVER);
            JSONObject obj = null;
            try {
//                    LogUtil.json("软件状态",data);
                obj = new JSONObject(data);

                Local.softState = obj.getInt("state");
                Local.stateMsg = obj.getString("msg");
                Local.final_url = obj.getString("url");

                Local.state.put("软件状态", true);
            } catch (JSONException e) {
                e.printStackTrace();
                Local.state.put("软件状态", false);
            }


            try {
                ch.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 获取本地信息
     */

    public static class GetLocalInfo implements Runnable {
        //屏障点（集结点），必须所有人达到集合点才能继续后面的任务
        CyclicBarrier ch;

        public GetLocalInfo(CyclicBarrier ch) {
            this.ch = ch;
        }

        @Override
        public void run() {

            try {
                PackageManager manager = view.getPackageManager();
                PackageInfo info = manager.getPackageInfo(view.getPackageName(), 0);
                Local.LocalInfo.versionCode = info.versionCode;
                Local.LocalInfo.versionName = info.versionName;
                Local.LocalInfo.systemVersion = Build.VERSION.SDK_INT;
                Local.state.put("软件信息", true);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                Local.state.put("软件信息", false);

            }


            try {
                ch.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
