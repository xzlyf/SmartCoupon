package publi.xz.com.smartcoupon.ui;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.tencent.connect.common.Constants;
import com.tencent.open.utils.HttpUtils;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

import publi.xz.com.smartcoupon.R;
import publi.xz.com.smartcoupon.base.BaseActivity;
import publi.xz.com.smartcoupon.constant.Local;
import publi.xz.com.smartcoupon.entity.Update;
import publi.xz.com.smartcoupon.ui.custom.LoginState;
import publi.xz.com.smartcoupon.ui.custom.UpdateDialog;
import publi.xz.com.smartcoupon.utils.SharedPreferencesUtil;

import static publi.xz.com.smartcoupon.utils.CacheInfo.cleanPhotoCache;
import static publi.xz.com.smartcoupon.utils.CacheInfo.getPhotoCacheSize;
import static publi.xz.com.smartcoupon.utils.TransparentBarUtil.makeStatusBarTransparent;

public class SettingActivity extends BaseActivity implements View.OnClickListener {
    private Button cleanCache;
    private Button checkUpdate;
    private Update update;
    private TextView login_btn;
    private TextView id_manage;
    private LoginState login_state;


    @Override
    public int getLayoutResource() {
        return R.layout.activity_setting;
    }

    @Override
    public void init_Data() {
        //设置透明状态栏
        makeStatusBarTransparent(this);
        init();
        init_login();


    }

    private Tencent tx;

    /**
     * 初始化QQ接入
     */
    private void init_login() {
        final String TAG = "xz_tx";
        tx = Tencent.createInstance(Local.tx_Appid, this);
//        if (!tx.isSessionValid()) {
//            tx.login(this, "all", loginListener, true);
//
//        }

    }


    @Override
    public void showData(Object object) {
    }

    private void init() {
        cleanCache.setText("清理缓存（" + getPhotoCacheSize(this) + "Mb）");
        //获取更新信息(已换成到本地
        String data = SharedPreferencesUtil.getJson(this, "UPDATE_DATA", "null");
        if (data.equals("null")) {
            //本地更新信息不存在将执行重新获取
            return;
        }
        Gson gson = new Gson();
        update = gson.fromJson(data, Update.class);

    }

    @Override
    public void findID() {
        cleanCache = findViewById(R.id.clean_cache);
        checkUpdate = findViewById(R.id.check_update);
        login_btn = findViewById(R.id.login_btn);
        login_state = findViewById(R.id.login_state);
        id_manage = findViewById(R.id.id_manage);
        checkUpdate.setOnClickListener(this);
        cleanCache.setOnClickListener(this);
        login_btn.setOnClickListener(this);
        id_manage.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clean_cache:
                clean();
                break;
            case R.id.check_update:
                check();
                break;
            case R.id.login_btn:
                doCheckLogin();
                break;
            case R.id.id_manage:
//                getUserInfo();
                logout();
                break;
        }
    }

    /**
     * 获取用户信息
     */
    public void getUserInfo()
    {
        tx.requestAsync(Constants.LOGIN_INFO, null,
                Constants.HTTP_GET, new IRequestListener() {
                    @Override
                    public void onComplete(JSONObject jsonObject) {
                        Logger.d("a");
                    }

                    @Override
                    public void onIOException(IOException e) {
                        Logger.d("b");

                    }

                    @Override
                    public void onMalformedURLException(MalformedURLException e) {
                        Logger.d("c");

                    }

                    @Override
                    public void onJSONException(JSONException e) {
                        Logger.d("d");

                    }

                    @Override
                    public void onConnectTimeoutException(ConnectTimeoutException e) {
                        Logger.d("e");

                    }

                    @Override
                    public void onSocketTimeoutException(SocketTimeoutException e) {
                        Logger.d("f");

                    }

                    @Override
                    public void onNetworkUnavailableException(HttpUtils.NetworkUnavailableException e) {
                        Logger.d("g");

                    }

                    @Override
                    public void onHttpStatusException(HttpUtils.HttpStatusException e) {
                        Logger.d("h");

                    }

                    @Override
                    public void onUnknowException(Exception e) {
                        Logger.d("i");

                    }
                }, null);
    }

    /**
     * 注销
     */
    private void logout() {

            tx.logout(this);
            //保存登陆状态
            SharedPreferencesUtil.saveState(SettingActivity.this, "login", false);
            //重置显示布局
            login_state.showState();
    }


    /**
     * 检查登陆状况
     */
    private void doCheckLogin() {
        if (!tx.checkSessionValid(Local.tx_Appid)) {
            //token过期，请调用登录接口拉起手Q授权登录"
            tx.login(this, "all", loginListener, true);
        }
    }

    /**
     * 清理缓存
     */
    private void clean() {
        cleanPhotoCache(this);
        Toast.makeText(this, "清理完成", Toast.LENGTH_SHORT).show();
        cleanCache.setText("清理缓存（" + getPhotoCacheSize(this) + "Mb）");
    }

    /**
     * 检查更新
     */
    private void check() {
        try {

            if (update.getCode() > Local.LocalInfo.versionCode) {
                if (Local.LocalInfo.isshowed) {
                    dialog.show();
                } else {
                    showUpdateDialog();
                }
            } else {
                mToast("已是最新版啦!");
            }

        } catch (Exception e) {
            mToast("更新异常");
            return;
        }

    }

    /**
     * 升级对话框
     */
    private UpdateDialog dialog;

    private void showUpdateDialog() {
        dialog = new UpdateDialog(SettingActivity.this, R.style.update_dialog);
        dialog.create();
        dialog.setMsg(update.getMsg());
        dialog.setVersionName(update.getName());
        dialog.setDownloadLink(update.getLink());
        dialog.show();
        Local.LocalInfo.isshowed = true;

    }

    /**
     * 腾讯qq接入登陆回调接口
     */
    IUiListener loginListener = new BaseUiListener() {
        @Override
        protected void doComplete(JSONObject values) {
            //保存登陆状态
            SharedPreferencesUtil.saveState(SettingActivity.this, "login", true);
            //重置显示布局
            login_state.showState();
        }
    };

    class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            if (null == response) {
                mToast("返回为空 登录失败");
                return;
            }
            JSONObject jsonResponse = (JSONObject) response;
            if (null != jsonResponse && jsonResponse.length() == 0) {
                mToast("返回为空 登录失败");
                return;
            }
            mToast("登录成功");
            // 有奖分享处理
            doComplete((JSONObject) response);
        }

        protected void doComplete(JSONObject values) {

        }

        @Override
        public void onError(UiError e) {
            mToast("onError: " + e.errorDetail);
        }

        @Override
        public void onCancel() {
            mToast("onCancel: ");
        }
    }


}
