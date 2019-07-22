package publi.xz.com.smartcoupon.ui.custom;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import publi.xz.com.smartcoupon.R;
import publi.xz.com.smartcoupon.ui.MainActivity;
import publi.xz.com.smartcoupon.ui.SettingActivity;


public class BottmNav extends LinearLayout implements View.OnClickListener {
    private Context context;
    private TextView navDiscover;
    private TextView navHome;
    private TextView navComm;
    private TextView navMyself;

    public BottmNav(Context context) {
        super(context);
    }

    public BottmNav(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.custom_bottom_nav, this);
        this.context = context;

        findId();
        init_drawable();

    }

    /**
     * 按钮上标
     */
    private void init_drawable() {
        Drawable drawable1 = getResources().getDrawable(R.drawable.nav_discover);
        drawable1.setBounds(0, 20, 75, 75);//第一0是距左边距离，第二20是距上边距离，75分别是长宽
        navDiscover.setCompoundDrawables(null, drawable1, null, null);//只放顶部
        Drawable drawable2 = getResources().getDrawable(R.drawable.nav_home);
        drawable2.setBounds(0, 20, 75, 75);
        navHome.setCompoundDrawables(null, drawable2, null, null);
        Drawable drawable3 = getResources().getDrawable(R.drawable.nav_comm);
        drawable3.setBounds(0, 20, 75, 75);
        navComm.setCompoundDrawables(null, drawable3, null, null);
        Drawable drawable4 = getResources().getDrawable(R.drawable.nav_myself);
        drawable4.setBounds(0, 20, 75, 75);
        navMyself.setCompoundDrawables(null, drawable4, null, null);


    }

    private void findId() {
        navDiscover = findViewById(R.id.nav_discover);
        navHome = findViewById(R.id.nav_home);
        navComm = findViewById(R.id.nav_comm);
        navMyself = findViewById(R.id.nav_myself);
        navDiscover.setOnClickListener(this);
        navHome.setOnClickListener(this);
        navComm.setOnClickListener(this);
        navMyself.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nav_discover:
                break;
            case R.id.nav_home:
                break;
            case R.id.nav_comm:
                break;
            case R.id.nav_myself:
                context.startActivity(new Intent(context, SettingActivity.class));
                break;
        }

    }
}
