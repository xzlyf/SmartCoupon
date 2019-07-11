package publi.xz.com.smartcoupon.ui.presenter;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.TreeMap;

import publi.xz.com.smartcoupon.constant.Local;
import publi.xz.com.smartcoupon.entity.Baoyou9_9;
import publi.xz.com.smartcoupon.ui.Baoyou9_9Activity;
import publi.xz.com.smartcoupon.ui.model.IModel;
import publi.xz.com.smartcoupon.ui.model.Model;
import publi.xz.com.smartcoupon.utils.SignMD5Util;
import publi.xz.com.smartcoupon.utils.SplicString;

public class Presenter_Baoyou9_9 {
    private Model model;
    private Baoyou9_9Activity view;

    public Presenter_Baoyou9_9(Baoyou9_9Activity view) {
        this.view = view;
        model = new Model();
    }

    /**
     * 获取
     */
    public void get9_9tehui(String pageSize, String pageId, String cid) {
        TreeMap<String, String> paraMap = new TreeMap<>();
        paraMap.put("appKey", Local.appKey);
        paraMap.put("version", Local.appversion);
        paraMap.put("pageSize", pageSize);
        paraMap.put("pageId", pageId);
        paraMap.put("cid", cid);
        paraMap.put("sign", SignMD5Util.getSignStr(paraMap, Local.appSecret));

        String url = SplicString.SplicUrl(Local.BAOYOU9_, paraMap);
//        Logger.w(url);
        model.getDataFromNet(url, new IModel.OnLoadCompleteListener() {
            @Override
            public void success(String data) {
                JSONObject obj = null;
                try {
//                    Logger.w("9.9包邮数据"+data);
                    obj = new JSONObject(data);
                    if (obj.get("msg").equals("成功")){
                        Gson gson  = new Gson();
                        view.showRecycler(gson.fromJson(obj.toString(),Baoyou9_9.class));
                    }else{
                        view.showDialog("服务器异常，请稍后重试！",Local.DIALOG_W);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    view.showDialog("解析失败，请稍后重试！",Local.DIALOG_W);
                }
            }

            @Override
            public void failed(Exception e) {
                view.stopLoading();
                view.showDialog("加载失败，请检查网络是否连接成功！",Local.DIALOG_E);
            }
        });
    }
}
