package publi.xz.com.smartcoupon.ui.presenter;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.TreeMap;

import publi.xz.com.smartcoupon.constant.Local;
import publi.xz.com.smartcoupon.entity.Baoyou9_9;
import publi.xz.com.smartcoupon.entity.Search;
import publi.xz.com.smartcoupon.ui.Baoyou9_9Activity;
import publi.xz.com.smartcoupon.ui.SearchActivity;
import publi.xz.com.smartcoupon.ui.model.IModel;
import publi.xz.com.smartcoupon.ui.model.Model;
import publi.xz.com.smartcoupon.utils.SignMD5Util;
import publi.xz.com.smartcoupon.utils.SplicString;

public class Presenter_Search {
    private Model model;
    private SearchActivity view;

    public Presenter_Search(SearchActivity view) {
        this.view = view;
        model = new Model();
    }

    /**
     * @param type 搜索类型         0-综合结果，1-大淘客商品，2-联盟商品
     * @param keyWords 关键词搜索
     * @param tmall 是否天猫商品  	1-天猫商品，0-所有商品，不填默认为0
     * @param haitao 是否海淘商品     1-海淘商品，0-所有商品，不填默认为0
     * @param sort 排序字段      排序字段信息 销量（total_sales） 价格（price），排序_des（降序），排序_asc（升序）
     */
    public void getSearchData(String type,String keyWords, String tmall, String haitao,String sort) {
        view.showLoading();
        TreeMap<String, String> paraMap = new TreeMap<>();
        paraMap.put("appKey", Local.appKey);
        paraMap.put("version", Local.appversion);
        paraMap.put("type", type);
        paraMap.put("keyWords", keyWords);
        paraMap.put("tmall", tmall);
        paraMap.put("haitao", haitao);
        paraMap.put("sort", sort);
        paraMap.put("sign", SignMD5Util.getSignStr(paraMap, Local.appSecret));

        String url = SplicString.SplicUrl(Local.sousuo, paraMap);
//        Logger.w(url);
        model.getDataFromNet(url, new IModel.OnLoadCompleteListener() {
            @Override
            public void success(String data) {
                JSONObject obj = null;
                try {
//                    Logger.w("搜索数据"+data);
                    obj = new JSONObject(data);
                    if (obj.get("msg").equals("成功")){
                        Gson gson = new Gson();
                        view.showCommList(gson.fromJson(obj.toString(),Search.class));
                    }else{
                        view.stopLoading();
                        view.showDialog("服务器异常，请稍后重试！"+'\n'+obj.get("msg"),Local.DIALOG_W);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    view.stopLoading();
                    view.showDialog("解析异常，请稍后重试！",Local.DIALOG_W);

                }
            }

            @Override
            public void failed(Exception e) {
                view.stopLoading();
                view.showDialog("请求失败，请稍后重试！",Local.DIALOG_W);

            }
        });
    }
}
