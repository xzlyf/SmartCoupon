package publi.xz.com.smartcoupon.ui.custom;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import publi.xz.com.smartcoupon.R;
import publi.xz.com.smartcoupon.utils.CustomOnclickListener;

public class ChooseLoginDialog extends Dialog implements View.OnClickListener{
    private Context mContext;
    private CustomOnclickListener listener;
    private CustomOnclickListener listener2;
    private ImageView qqIcon;
    private ImageView phoneIcon;



    public ChooseLoginDialog(Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(LayoutInflater.from(mContext).inflate(R.layout.dialog_choose_login,null));
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = mContext.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.5); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);

        qqIcon = findViewById(R.id.qq_icon);
        phoneIcon = findViewById(R.id.phone_icon);
        qqIcon.setOnClickListener(this);
        phoneIcon.setOnClickListener(this);
    }

    /**
     * QQ 按钮回调
     * @param listener
     */
    public void setQQOclickListener(CustomOnclickListener listener){
        this.listener = listener;
    }

    /**
     * 手机按钮回调
     * @param listener
     */
    public void setPhoneOnClickListener(CustomOnclickListener listener){
        this.listener2 = listener;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.qq_icon:
                listener.onClick();
                break;
            case R.id.phone_icon:
                listener2.onClick();
                break;
        }
    }
}
