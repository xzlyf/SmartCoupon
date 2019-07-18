package publi.xz.com.smartcoupon.ui.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.TreeMap;

import publi.xz.com.smartcoupon.constant.Local;
import publi.xz.com.smartcoupon.entity.Detail;
import publi.xz.com.smartcoupon.entity.MainCNXH;
import publi.xz.com.smartcoupon.ui.DetailsActivity;
import publi.xz.com.smartcoupon.ui.MainActivity;
import publi.xz.com.smartcoupon.ui.model.IModel;
import publi.xz.com.smartcoupon.ui.model.Model;
import publi.xz.com.smartcoupon.utils.SignMD5Util;
import publi.xz.com.smartcoupon.utils.SplicString;

public class Presenter_Main {
    private Model model;
    private MainActivity view;

    public Presenter_Main(MainActivity view){
        this.view = view;
        model = new Model();
    }


    private String default_pageSize = "50";//每页数据50条
    private int default_pageId = 1;//起始页
    /**
     * 获取
     */
    public void getGoodsFromNet(){
        TreeMap<String, String> paraMap = new TreeMap<>();
        paraMap.put("appKey", Local.appKey);
        paraMap.put("version", Local.appversion);
        paraMap.put("pageSize", default_pageSize);
        paraMap.put("pageId", default_pageId+"");
        paraMap.put("cid", "1");
        paraMap.put("sign", SignMD5Util.getSignStr(paraMap, Local.appSecret));

        //拼接成最终url
        String url = SplicString.SplicUrl(Local.BAOYOU9_, paraMap);
//        Log.d("xz", "目标链接： "+url);
        model.getDataFromNet(url, new IModel.OnLoadCompleteListener() {
            @Override
            public void success(String data) {
                default_pageId++;//默认页加1
                JSONObject obj = null;
                try {
//                    Logger.w("主页商品列表"+data);
                    obj = new JSONObject(data);
                    Gson gson = new Gson();
                    view.showData(gson.fromJson(obj.toString(),MainCNXH.class));
                } catch (JSONException e) {
                    e.printStackTrace();
                    view.showDialog("服务器异常，请稍后重试！", Local.DIALOG_W);

                }
            }

            @Override
            public void failed(Exception e) {
                view.showDialog("请求失败，请稍后重试！",Local.DIALOG_W);

            }
        });
    }
}
