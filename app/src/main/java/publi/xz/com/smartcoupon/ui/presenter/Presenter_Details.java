package publi.xz.com.smartcoupon.ui.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import publi.xz.com.smartcoupon.constant.Local;
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
}
