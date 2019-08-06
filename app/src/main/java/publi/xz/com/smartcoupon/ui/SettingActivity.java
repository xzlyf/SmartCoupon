package publi.xz.com.smartcoupon.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.open.utils.HttpUtils;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.xz.com.log.LogUtil;

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
import publi.xz.com.smartcoupon.ui.custom.ChooseLoginDialog;
import publi.xz.com.smartcoupon.ui.custom.LoginState;
import publi.xz.com.smartcoupon.ui.custom.UpdateDialog;
import publi.xz.com.smartcoupon.utils.CustomOnclickListener;
import publi.xz.com.smartcoupon.utils.ImageUtil;
import publi.xz.com.smartcoupon.utils.SharedPreferencesUtil;

import static publi.xz.com.smartcoupon.utils.CacheInfo.cleanCache;
import static publi.xz.com.smartcoupon.utils.CacheInfo.getTotalCache;
import static publi.xz.com.smartcoupon.utils.TransparentBarUtil.makeStatusBarTransparent;

public class SettingActivity extends BaseActivity implements View.OnClickListener {
    private Button cleanCache;
    private Button checkUpdate;
    private Update update;
    private TextView login_btn;
    private TextView id_manage;
    private LoginState login_state;
    private Tencent tx;
    private ImageView head_photo;
    private TextView userName;
    private UserInfo mInfo;

    private String TAG = "xzxzxxz";

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    JSONObject response = (JSONObject) msg.obj;
                    try {
                        userName.setText(response.getString("nickname"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case 1:
                    Bitmap bitmap = (Bitmap) msg.obj;
                    head_photo.setImageBitmap(bitmap);
                    break;
            }
        }

    };

    @Override
    public int getLayoutResource() {
        return R.layout.activity_setting;
    }

    @Override
    public void init_Data() {
        //设置透明状态栏
        makeStatusBarTransparent(this);
        init();
//        init_login();


    }


    /**
     * 初始化QQ接入APi
     */
    private void init_login() {
        tx = Tencent.createInstance(Local.tx_Appid, this.getApplicationContext());
        mInfo = new UserInfo(this, tx.getQQToken());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        Log.d(TAG, "onActivityResult:a " + requestCode);
//        Log.d(TAG, "onActivityResult:b " + resultCode);
        //解决腾讯QQ接入不回调
        Tencent.onActivityResultData(requestCode, requestCode, data, loginListener);
        //对应的返回值对应的动作
        if (requestCode == Constants.REQUEST_LOGIN) {
            Tencent.handleResultData(data, loginListener);
        }

    }

    @Override
    public void showData(Object object) {
    }

    private void init() {
        cleanCache.setText("清理缓存（" + getTotalCache(this) + "Mb）");
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
        head_photo = findViewById(R.id.head_photo);
        id_manage = findViewById(R.id.id_manage);
        userName = findViewById(R.id.user_name);
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
//                doCheckLogin();
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
    public void getUserInfo() {
        tx.requestAsync(Constants.LOGIN_INFO, null,
                Constants.HTTP_GET, new IRequestListener() {
                    @Override
                    public void onComplete(JSONObject jsonObject) {
                        LogUtil.d("a");
                    }

                    @Override
                    public void onIOException(IOException e) {
                        LogUtil.d("b");

                    }

                    @Override
                    public void onMalformedURLException(MalformedURLException e) {
                        LogUtil.d("c");

                    }

                    @Override
                    public void onJSONException(JSONException e) {
                        LogUtil.d("d");

                    }

                    @Override
                    public void onConnectTimeoutException(ConnectTimeoutException e) {
                        LogUtil.d("e");

                    }

                    @Override
                    public void onSocketTimeoutException(SocketTimeoutException e) {
                        LogUtil.d("f");

                    }

                    @Override
                    public void onNetworkUnavailableException(HttpUtils.NetworkUnavailableException e) {
                        LogUtil.d("g");

                    }

                    @Override
                    public void onHttpStatusException(HttpUtils.HttpStatusException e) {
                        LogUtil.d("h");

                    }

                    @Override
                    public void onUnknowException(Exception e) {
                        LogUtil.d("i");

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
    private ChooseLoginDialog loginDialog;

    private void doCheckLogin() {

        loginDialog = new ChooseLoginDialog(SettingActivity.this, R.style.choose_dialog);
        loginDialog.create();
        loginDialog.show();
        //手机Qq登陆
        loginDialog.setQQOclickListener(new CustomOnclickListener() {
            @Override
            public void onClick() {

                loginDialog.dismiss();
                if (!tx.checkSessionValid(Local.tx_Appid)) {
                    //token过期，token过期请调用登录接口拉起手Q授权登录 "
                    tx.login(SettingActivity.this, "all", loginListener, true);

                } else {
                    tx.logout(SettingActivity.this);
                    tx.login(SettingActivity.this, "all", loginListener, true);
                }
            }
        });
        //手机号登陆
        loginDialog.setPhoneOnClickListener(new CustomOnclickListener() {
            @Override
            public void onClick() {
                loginDialog.dismiss();
                mToast("暂未开通");
            }
        });
    }

    /**
     * 清理缓存
     */
    private void clean() {
        cleanCache(this);
        mToast("清理完成");
        cleanCache.setText("清理缓存（" + getTotalCache(this) + "Mb）");
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
            SharedPreferencesUtil.saveLoginJson(SettingActivity.this, "qq", values.toString());
            //重置显示布局
            login_state.showState();
            updateUserInfo();

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
            mToast("登陆已取消 ");
        }
    }

    /**
     * 获取用户信息-QQ
     */
    private void updateUserInfo() {
        if (tx != null && tx.isSessionValid()) {

            mInfo.getUserInfo(listener);

        }
    }

    private IUiListener listener = new IUiListener() {

        @Override
        public void onError(UiError e) {

        }

        @Override
        public void onComplete(final Object response) {
            //保存至本地
            SharedPreferencesUtil.saveLoginJson(SettingActivity.this, "user_info", response.toString());
            Message msg = new Message();
            msg.obj = response;
            msg.what = 0;
            mHandler.sendMessage(msg);
            new Thread() {

                @Override
                public void run() {
                    JSONObject json = (JSONObject) response;
                    if (json.has("figureurl")) {
                        Bitmap bitmap = null;
                        try {
                            bitmap = ImageUtil.getbitmap(json.getString("figureurl_qq_2"));
                        } catch (JSONException e) {

                        }
                        Message msg = new Message();
                        msg.obj = bitmap;
                        msg.what = 1;
                        mHandler.sendMessage(msg);
                    }
                }

            }.start();
        }

        @Override
        public void onCancel() {

        }
    };
}
