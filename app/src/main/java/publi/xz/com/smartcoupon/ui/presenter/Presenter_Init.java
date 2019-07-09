package publi.xz.com.smartcoupon.ui.presenter;

import android.app.Activity;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

import publi.xz.com.smartcoupon.constant.Local;
import publi.xz.com.smartcoupon.entity.Detail;
import publi.xz.com.smartcoupon.entity.HotWord;
import publi.xz.com.smartcoupon.entity.UserNetInfo;
import publi.xz.com.smartcoupon.ui.DetailsActivity;
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

    /**
     * 获取 热搜词数据
     *
     */
    public void getDetailFromNet() {
        model.getDataFromNet(Local.HOT_WORD, new IModel.OnLoadCompleteListener() {
            @Override
            public void success(String data) {
                JSONObject obj = null;
                try {
//                    Logger.w("热搜词数据"+data);
                    obj = new JSONObject(data);
                    if (obj.getString("er_msg").equals("请求成功")) {
                        //保存json到本地
                        SharedPreferencesUtil.saveJson(view,"HOT_WORD",data);
                        Local.state.put("热搜词",true);
                    } else {
                        //失败处理

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Exception e) {

                Local.state.put("热搜词",false);

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
//                    Logger.w("用户信息"+data);
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
                            Local.state.put("用户信息",true);

                        }


                    } else if (obj.getString("code").equals("0")) {
                    }

                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void failed(Exception e) {
                Local.state.put("用户信息",false);

            }
        });
    }

}
