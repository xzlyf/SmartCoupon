package publi.xz.com.smartcoupon.ui.custom;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import publi.xz.com.smartcoupon.R;
import publi.xz.com.smartcoupon.utils.SharedPreferencesUtil;

public class LoginState extends LinearLayout implements View.OnClickListener {
    private Context context;
    private FrameLayout layout1;
    private ImageView bannerView;
    private TextView loginBtn;
    private RelativeLayout layout2;
    private ImageView headPhoto;
    private TextView idManage;
    private ImageView backBtn;


    public LoginState(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.custom_login_state, this);
        findID();
        layout1.setVisibility(GONE);
        layout2.setVisibility(GONE);
//        SharedPreferencesUtil.saveState(context,"login",false);

        //读取登陆状态
        if (SharedPreferencesUtil.getState(context, "login", false)) {
            //已登录
            layout2.setVisibility(View.VISIBLE);
        } else {
            //未登录
            layout1.setVisibility(View.VISIBLE);

        }

        this.context = context;

        init_anim();
    }

    private void findID() {
        layout1 = findViewById(R.id.layout_1);
        bannerView = findViewById(R.id.banner_view);
        loginBtn = findViewById(R.id.login_btn);
        layout2 = findViewById(R.id.layout_2);
        headPhoto = findViewById(R.id.head_photo);
        idManage = findViewById(R.id.id_manage);
        backBtn = findViewById(R.id.back_btn);
        loginBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
        idManage.setOnClickListener(this);
        headPhoto.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                break;
            case R.id.back_btn:

                if (context instanceof Activity) {
                    ((Activity) context).finish();
                }
                break;
            case R.id.id_manage:
                break;
            case R.id.head_photo:
                break;
        }
    }


    private ScaleAnimation showAnim;
    private ScaleAnimation hideAnim;

    public void init_anim() {
        showAnim = new ScaleAnimation(1.0f, 1.0f, 0.0f, 1.0f);
        showAnim.setDuration(500);
        hideAnim = new ScaleAnimation(1.0f, 1.0f, 1.0f, 0.0f);
        hideAnim.setDuration(500);
    }
}
