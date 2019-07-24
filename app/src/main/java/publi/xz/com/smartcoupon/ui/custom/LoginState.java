package publi.xz.com.smartcoupon.ui.custom;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import publi.xz.com.smartcoupon.R;

public class LoginState extends LinearLayout implements View.OnClickListener {
    private Context context;
    private ImageView bannerView;
    private ImageView backBtn;
    private TextView loginBtn;


    public LoginState(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.custom_login_state, this);
        this.context = context;

        findID();
        init_anim();
    }

    private void findID() {
        bannerView = findViewById(R.id.banner_view);
        backBtn = findViewById(R.id.back_btn);
        loginBtn = findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                break;
            case R.id.back_btn:

                if (context instanceof Activity){
                    ((Activity) context).finish();
                }
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
