package publi.xz.com.smartcoupon.ui.presenter;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import publi.xz.com.smartcoupon.entity.Popular;
import publi.xz.com.smartcoupon.ui.Top100Activity;
import publi.xz.com.smartcoupon.ui.model.IModel;
import publi.xz.com.smartcoupon.ui.model.Model;

public class Presenter_Top100 {
    private Model model;
    private Top100Activity view;

    public Presenter_Top100(Top100Activity view){
        this.view = view;
        model = new Model();
    }

    /**
     * 获取
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
//                    Popular popular = gson.fromJson(obj.toString(),Popular.class);
                    view.setCommInfo(gson.fromJson(obj.toString(),Popular.class));
                } catch (JSONException e) {
                    e.printStackTrace();
                    view.stopLoading();
                    view.sToast("数据解析失败失败");
                }
            }

            @Override
            public void failed(Exception e) {
                view.stopLoading();
                view.sToast("请求失败");
            }
        });
    }
}
