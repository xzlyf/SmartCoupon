package publi.xz.com.smartcoupon.ui.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import publi.xz.com.smartcoupon.entity.Detail;
import publi.xz.com.smartcoupon.entity.Popular;
import publi.xz.com.smartcoupon.ui.DetailsActivity;
import publi.xz.com.smartcoupon.ui.Top100Activity;
import publi.xz.com.smartcoupon.ui.model.IModel;
import publi.xz.com.smartcoupon.ui.model.Model;

public class Presenter_Details {
    private Model model;
    private DetailsActivity view;

    public Presenter_Details(DetailsActivity view){
        this.view = view;
        model = new Model();
    }

    /**
     * 获取
     * @param url
     */
    public void getDetailFromNet(String url){
        model.getDataFromNet(url, new IModel.OnLoadCompleteListener() {
            @Override
            public void success(String data) {
                JSONObject obj = null;
                try {
//                    Logger.w("单品详情数据"+data);
                    obj = new JSONObject(data);
                    Gson gson = new Gson();
                    Detail detail = gson.fromJson(obj.toString(),Detail.class);
                    view.setDetailData(detail);
                } catch (JSONException e) {
                    e.printStackTrace();
                    view.stopLoading();
                }
            }

            @Override
            public void failed(Exception e) {
                view.stopLoading();
            }
        });
    }
}
