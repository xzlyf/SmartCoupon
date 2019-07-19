package publi.xz.com.smartcoupon.ui.presenter;

import android.app.Activity;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.TreeMap;

import publi.xz.com.smartcoupon.base.BaseActivity;
import publi.xz.com.smartcoupon.constant.Local;
import publi.xz.com.smartcoupon.entity.Baoyou9_9;
import publi.xz.com.smartcoupon.entity.MainCNXH;
import publi.xz.com.smartcoupon.entity.Popular;
import publi.xz.com.smartcoupon.entity.Search;
import publi.xz.com.smartcoupon.ui.model.IModel;
import publi.xz.com.smartcoupon.ui.model.Model;
import publi.xz.com.smartcoupon.utils.SignMD5Util;
import publi.xz.com.smartcoupon.utils.SplicString;

public class Presenter {
    private Model model;
    private BaseActivity view;

    public Presenter(BaseActivity view){
        this.view = view;
        model = new Model();
    }

    /**
     * 9.9包邮
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
//        Logger.w("9.9包邮"+url);
        model.getDataFromNet(url, new IModel.OnLoadCompleteListener() {
            @Override
            public void success(String data) {
                JSONObject obj = null;
                try {
//                    Logger.w("9.9包邮数据"+data);
                    obj = new JSONObject(data);
                    if (obj.get("msg").equals("成功")){
                        Gson gson  = new Gson();
                        view.showData(gson.fromJson(obj.toString(), Baoyou9_9.class));
                    }else{
                        view.stopLoading();
                        view.showDialog("服务器异常，请稍后重试！",Local.DIALOG_W);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    view.stopLoading();
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


    /**
     * 主页商品列表
     */
    private String default_pageSize = "50";//每页数据50条
    private int default_pageId = 1;//起始页
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
//        Logger.d("商品主列表： "+url);
        model.getDataFromNet(url, new IModel.OnLoadCompleteListener() {
            @Override
            public void success(String data) {
                default_pageId++;//默认页加1
                JSONObject obj = null;
                try {
//                    Logger.w("主页商品列表"+data);
                    obj = new JSONObject(data);
                    Gson gson = new Gson();
                    view.showData(gson.fromJson(obj.toString(), MainCNXH.class));
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

    /**
     * 获取商品详情
     * @param url
     */
    public void getDetailFromNet(String url){
        model.getDataFromNet(url, new IModel.OnLoadCompleteListener() {
            @Override
            public void success(String data) {
                JSONObject obj = null;
                try {
                    Logger.w("单品详情数据"+data);
                    obj = new JSONObject(data);
                    Gson gson = new Gson();

                } catch (JSONException e) {
                    e.printStackTrace();
                    view.stopLoading();
                    view.showDialog("服务器异常，请稍后重试！", Local.DIALOG_W);

                }
            }

            @Override
            public void failed(Exception e) {
                view.stopLoading();
                view.showDialog("请求失败，请稍后重试！",Local.DIALOG_W);

            }
        });
    }
    /**
     * @param type 搜索类型         0-综合结果，1-大淘客商品，2-联盟商品
     * @param keyWords 关键词搜索
     * @param tmall 是否天猫商品  	1-天猫商品，0-所有商品，不填默认为0
     * @param haitao 是否海淘商品     1-海淘商品，0-所有商品，不填默认为0
     * @param sort 排序字段      排序字段信息 销量（total_sales） 价格（price），排序_des（降序），排序_asc（升序）
     */
    public void getSearchData(String type,String keyWords, String tmall, String haitao,String sort) {
        if (keyWords.equals("") ){
            return;
        }
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
                        view.showData(gson.fromJson(obj.toString(), Search.class));
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
    /**
     * 获取人气榜
     * @param url
     */
    public void getTop100DataFromNet(String url){
//        view.startLoading();
        model.getDataFromNet(url, new IModel.OnLoadCompleteListener() {
            @Override
            public void success(String data) {
                JSONObject obj = null;
                try {
//                    Logger.w("top100人气榜数据"+data);
                    obj = new JSONObject(data);
                    Gson gson = new Gson();
                    view.showData(gson.fromJson(obj.toString(), Popular.class));
                } catch (JSONException e) {
                    e.printStackTrace();
                    view.stopLoading();
                    view.showDialog("解析失败，请稍后重试", Local.DIALOG_W);
                }
            }

            @Override
            public void failed(Exception e) {
                view.stopLoading();
                view.showDialog("请求失败，请检查网络连接是否正常", Local.DIALOG_E);

            }
        });
    }
}