package publi.xz.com.smartcoupon.ui;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ali.auth.third.core.model.Session;
import com.alibaba.baichuan.trade.biz.login.AlibcLogin;
import com.alibaba.baichuan.trade.biz.login.AlibcLoginCallback;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;
import publi.xz.com.smartcoupon.R;
import publi.xz.com.smartcoupon.base.BaseActivity;
import publi.xz.com.smartcoupon.constant.Local;
import publi.xz.com.smartcoupon.entity.Update;
import publi.xz.com.smartcoupon.ui.custom.UpdateDialog;
import publi.xz.com.smartcoupon.utils.SharedPreferencesUtil;

import static publi.xz.com.smartcoupon.utils.CacheInfo.cleanCache;
import static publi.xz.com.smartcoupon.utils.CacheInfo.getTotalCache;
import static publi.xz.com.smartcoupon.utils.TransparentBarUtil.makeStatusBarTransparent;

public class SettingActivity extends BaseActivity implements View.OnClickListener {
    private Button cleanCache;
    private Button checkUpdate;
    private Update update;
    private ImageView back;
    private TextView login_btn;
    private TextView id_manage;
    private CircleImageView head_photo;
    private TextView userName;
    private UpdateDialog dialog;
    private FrameLayout layout1;
    private RelativeLayout layout2;

    private String TAG = "xzxzxxz";

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    userName.setText((String) msg.obj + " ");
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
        cleanCache.setText("清理缓存（" + getTotalCache(this) + "Mb）");
        //获取更新信息(已换成到本地
        String data = SharedPreferencesUtil.getJson(this, "UPDATE_DATA", "null");
        if (data.equals("null")) {
            //本地更新信息不存在将执行重新获取
            return;
        }
        Gson gson = new Gson();
        update = gson.fromJson(data, Update.class);

        //检查是否已登录
        if (SharedPreferencesUtil.getState(this, "autoLogin", false)) {
            layout1.setVisibility(View.GONE);
            layout2.setVisibility(View.VISIBLE);
            updatelayout(Local.self.session.nick,Local.self.session.avatarUrl);
        } else {
            layout1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.GONE);
        }


    }


    @Override
    public void showData(Object object) {
    }


    @Override
    public void findID() {
        layout1 = findViewById(R.id.layout_1);
        layout2 = findViewById(R.id.layout_2);
        cleanCache = findViewById(R.id.clean_cache);
        checkUpdate = findViewById(R.id.check_update);
        login_btn = findViewById(R.id.login_btn);
        head_photo = findViewById(R.id.head_photo);
        id_manage = findViewById(R.id.id_manage);
        userName = findViewById(R.id.user_name);
        back = findViewById(R.id.back_btn);
        back.setOnClickListener(this);
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
                doLoginTaobao();

                break;
            case R.id.id_manage:
                doLoginOutTaobao();
                break;
            case R.id.back_btn:
                finish();
                break;
        }
    }

    /**
     * 注销淘宝
     */
    private void doLoginOutTaobao() {
        login = AlibcLogin.getInstance();
        login.logout(new AlibcLoginCallback() {
            @Override
            public void onSuccess(int i, String s, String s1) {
                //等出成功
                SharedPreferencesUtil.saveState(SettingActivity.this, "autoLogin", false);
                cleanLayout();//还原顶部页面

            }

            @Override
            public void onFailure(int i, String s) {
                //等出失败

            }
        });
    }

    private AlibcLogin login;

    /**
     * 淘宝接入
     * 登录
     */
    private void doLoginTaobao() {
        login = AlibcLogin.getInstance();
        login.showLogin(new AlibcLoginCallback() {
            @Override
            public void onSuccess(int i, String s, String s1) {

                mToast("登录成功");
                //开启自动登录
                SharedPreferencesUtil.saveState(SettingActivity.this, "autoLogin", true);
                Local.self.session = login.getSession();
                updatelayout(Local.self.session.nick, Local.self.session.avatarUrl);

            }

            @Override
            public void onFailure(int i, String s) {
                showDialog("错误代码：" + i + "\n" + s,Local.DIALOG_M );
                SharedPreferencesUtil.saveState(SettingActivity.this, "autoLogin", false);


            }
        });

    }

    /**
     * 刷新顶部页面
     *
     * @param nick     名称
     * @param photoUrl 头像
     */
    private void updatelayout(String nick, String photoUrl) {
        //设置用户名
        Message message = mHandler.obtainMessage();
        message.obj = nick;
        mHandler.sendMessage(message);
        //显示头像
        Glide.with(this).load(photoUrl).into(head_photo);

        layout1.setVisibility(View.GONE);
        layout2.setVisibility(View.VISIBLE);
    }

    /**
     * 清除顶部页面
     */
    private void cleanLayout(){
        layout1.setVisibility(View.VISIBLE);
        layout2.setVisibility(View.GONE);
        //设置用户名
        Message message = mHandler.obtainMessage();
        message.obj = "user123";
        mHandler.sendMessage(message);
        head_photo.setImageResource(R.drawable.default_head);
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

    private void showUpdateDialog() {
        dialog = new UpdateDialog(SettingActivity.this, R.style.update_dialog);
        dialog.create();
        dialog.setMsg(update.getMsg());
        dialog.setVersionName(update.getName());
        dialog.setDownloadLink(update.getLink());
        dialog.show();
        Local.LocalInfo.isshowed = true;

    }


}
