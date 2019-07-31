package publi.xz.com.smartcoupon.ui.presenter;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import publi.xz.com.smartcoupon.constant.Local;
import publi.xz.com.smartcoupon.entity.UserNetInfo;
import publi.xz.com.smartcoupon.ui.InitActivity;
import publi.xz.com.smartcoupon.ui.model.IModel;
import publi.xz.com.smartcoupon.ui.model.Model;
import publi.xz.com.smartcoupon.utils.SharedPreferencesUtil;

public class Presenter_Init {
    private Model model;
    private InitActivity view;

    public Presenter_Init(InitActivity view) {
        this.view = view;
        model = new Model();
    }
    public Presenter_Init(){};

    /**
     * 获取 热搜词数据
     */
    public void getDetailFromNet() {
        model.getDataFromNet(Local.HOT_WORD, new IModel.OnLoadCompleteListener() {
            @Override
            public void success(String data) {
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

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Exception e) {

                Local.state.put("热搜词", false);

            }
        });
    }

    /**
     * 获取用户网络位置 IP  服务商信息
     */
    public void getUserIpFromNet() {
        model.getDataFromNet(Local.GET_USER_IP_URL, new IModel.OnLoadCompleteListener() {
            @Override
            public void success(String data) {
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
            }

            @Override
            public void failed(Exception e) {
                Local.state.put("用户信息", false);

            }
        });
    }

    /**
     * 获取服务器时间
     */
    public void getServerTime() {
        model.getDataFromNet(Local.GET_SERVER_TIME, new IModel.OnLoadCompleteListener() {
            @Override
            public void success(String data) {
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

                }
            }

            @Override
            public void failed(Exception e) {
                Local.state.put("服务器时间", false);

            }
        });
    }
    /**
     * 检查更新
     */
    public void checkUpdate() {
        new Model().getDataFromNet(Local.UPDATE_SERVER, new IModel.OnLoadCompleteListener() {
            @Override
            public void success(String data) {
                JSONObject obj = null;

                try {
//                    LogUtil.d("更新数据"+data);
                    obj = new JSONObject(data);
                    if (obj.getInt("value")==1){
                        JSONObject obj2 = obj.getJSONObject("data");
                        //保存json到本地
                        SharedPreferencesUtil.saveJson(view, "UPDATE_DATA", obj2.toString());
                        Local.state.put("更新信息", true);
                    }else{
                        //服务器问题
                        Local.state.put("更新信息", false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    //解析问题
                    Local.state.put("更新信息", false);
                }
            }

            @Override
            public void failed(Exception e) {
                //连接问题
                Local.state.put("更新信息", false);
            }
        });
    }

    /**
     * 远程控制
     * 检查死亡开关
     * state 1 正常使用
     * state 0 软件关闭
     */
    public void checkState() {
        new Model().getDataFromNet(Local.STATE_SERVER, new IModel.OnLoadCompleteListener() {
            @Override
            public void success(String data) {
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
            }

            @Override
            public void failed(Exception e) {
                //连接问题
                Local.state.put("软件状态", false);
            }
        });
    }
    /**
     * 获取本地信息
     */
    public void getLocalInfo() {
        try {

            PackageManager manager = view.getPackageManager();
            PackageInfo info = manager.getPackageInfo(view.getPackageName(),0);
            Local.LocalInfo.versionCode = info.versionCode;
            Local.LocalInfo.versionName = info.versionName;
            Local.LocalInfo.systemVersion = Build.VERSION.SDK_INT;
            Local.state.put("软件信息", true);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Local.state.put("软件信息", true);
        }
    }
}
