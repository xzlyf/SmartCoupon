package publi.xz.com.smartcoupon.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import publi.xz.com.smartcoupon.R;

public class BaseDialog extends Dialog {
    private Context mContext;
    private TextView title;
    private TextView msg;

    public BaseDialog(@NonNull Context context) {
        super(context);
        mContext = context;

    }


    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;

    }
    public void setMsg(String msg){
        this.msg.setText(msg);
    }
    @Override
    public void dismiss() {
        super.dismiss();
        title = null;
        msg = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater1 = LayoutInflater.from(mContext);
        View view1 = inflater1.inflate(R.layout.dialog_base, null);
        setContentView(view1);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = mContext.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
        title = findViewById(R.id.dialog_base_title);
        msg = findViewById(R.id.dialog_base_msg);
        title.setText("提示");
    }
}
