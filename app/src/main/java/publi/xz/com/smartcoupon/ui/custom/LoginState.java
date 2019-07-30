package publi.xz.com.smartcoupon.ui.custom;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import publi.xz.com.smartcoupon.R;
import publi.xz.com.smartcoupon.utils.ImageUtil;
import publi.xz.com.smartcoupon.utils.SharedPreferencesUtil;

public class LoginState extends LinearLayout implements View.OnClickListener {
    private Context context;
    private FrameLayout layout1;
    private ImageView bannerView;
    private RelativeLayout layout2;
    private ImageView headPhoto;
    private ImageView backBtn;
    private TextView userName;

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
//            String data = SharedPreferencesUtil.getLoginJson(context,"user_info","null");
//            if (data.equals("null")){
//                return;
//            }
//            JSONObject json = null;
//            try {
//                json = new JSONObject(data);
//                headPhoto.setImageBitmap(ImageUtil.getbitmap(json.getString("figureurl_qq_2")));
//                userName.setText(json.getString("nickname"));
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
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
        userName = findViewById(R.id.user_name);
        backBtn.setOnClickListener(this);
        headPhoto.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                if (context instanceof Activity) {
                    ((Activity) context).finish();
                }
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
