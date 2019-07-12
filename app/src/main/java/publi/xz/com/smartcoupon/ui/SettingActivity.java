package publi.xz.com.smartcoupon.ui;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import publi.xz.com.smartcoupon.R;
import publi.xz.com.smartcoupon.base.BaseActivity;
import publi.xz.com.smartcoupon.base.BaseDialog;
import publi.xz.com.smartcoupon.constant.Local;
import publi.xz.com.smartcoupon.entity.Update;
import publi.xz.com.smartcoupon.ui.custom.UpdateDialog;
import publi.xz.com.smartcoupon.ui.model.IModel;
import publi.xz.com.smartcoupon.ui.model.Model;
import publi.xz.com.smartcoupon.ui.view.IView;
import publi.xz.com.smartcoupon.utils.SharedPreferencesUtil;

import static publi.xz.com.smartcoupon.utils.CacheInfo.cleanPhotoCache;
import static publi.xz.com.smartcoupon.utils.CacheInfo.getPhotoCacheSize;

public class SettingActivity extends BaseActivity implements View.OnClickListener , IView {
    private Button cleanCache;
    private Button checkUpdate;
    private Update update;


    @Override
    public int getLayoutResource() {
        return R.layout.activity_setting;
    }

    @Override
    public void init_Data() {
        findID();
        init();
    }

    private void init() {
        cleanCache.setText("清理缓存（"+getPhotoCacheSize(this)+"Mb）");
        //获取更新信息(已换成到本地
        String data = SharedPreferencesUtil.getJson(this,"UPDATE_DATA","null");
        if (data.equals("null")){
            //本地更新信息不存在将执行重新获取
            return;
        }
        Gson gson = new Gson();
        update = gson.fromJson(data,Update.class);

    }

    private void findID() {
        cleanCache = findViewById(R.id.clean_cache);
        checkUpdate = findViewById(R.id.check_update);
        checkUpdate.setOnClickListener(this);
        cleanCache.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clean_cache:
                cleanPhotoCache(this);
                Toast.makeText(this,"清理完成",Toast.LENGTH_SHORT).show();
                cleanCache.setText("清理缓存（"+getPhotoCacheSize(this)+"Mb）");
                break;
            case R.id.check_update:
                check();
                break;
        }
    }

    private void check() {

        if (update.getCode()>Local.LocalInfo.versionCode){

            showUpdateDialog();
        }else{
            sToast("已是最新版啦!");
        }
    }

    private UpdateDialog dialog;

    private void showUpdateDialog(){
        dialog = new UpdateDialog(SettingActivity.this, R.style.update_dialog);
        dialog.create();
        dialog.show();

    }
    private void dismissUpdateDialog(){

    }

    @Override
    public void startLoading() {
        showLoading();
    }

    @Override
    public void stopLoading() {
        dismissLoading();
    }

    @Override
    public void sToast(String msg) {
        mToast(msg);
    }
}
