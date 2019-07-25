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
import publi.xz.com.smartcoupon.utils.CustomOnclickListener;
import publi.xz.com.smartcoupon.utils.ItemOnclickListener;
import publi.xz.com.smartcoupon.utils.SharedPreferencesUtil;

public class LoginState extends LinearLayout implements View.OnClickListener {
    private Context context;
    private FrameLayout layout1;
    private ImageView bannerView;
    private RelativeLayout layout2;
    private ImageView headPhoto;
    private ImageView backBtn;
    private CustomOnclickListener onclickListener;

    public LoginState(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.custom_login_state, this);
        findID();
        showState();


        init_anim();
    }

    /**
     * 显示哪个布局
     */
    public void showState() {
        layout1.setVisibility(GONE);
        layout2.setVisibility(GONE);
        //读取登陆状态
        if (SharedPreferencesUtil.getState(context, "login", false)) {
            //已登录
            layout2.setVisibility(View.VISIBLE);
        } else {
            //未登录
            layout1.setVisibility(View.VISIBLE);

        }
    }

    private void findID() {
        layout1 = findViewById(R.id.layout_1);
        layout2 = findViewById(R.id.layout_2);
        bannerView = findViewById(R.id.banner_view);
        headPhoto = findViewById(R.id.head_photo);
        backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(this);
        headPhoto.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                onclickListener.onClick();
                break;
            case R.id.back_btn:

                if (context instanceof Activity) {
                    ((Activity) context).finish();
                }
                break;
            case R.id.head_photo:
                break;
        }
    }

    /**
     * 给外部调用
     * @param listener
     */
    public void setOnclickListener(CustomOnclickListener listener){
        onclickListener = listener;
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
