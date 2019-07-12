package publi.xz.com.smartcoupon.ui.custom;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import publi.xz.com.smartcoupon.R;

/**
 * 更新对话框
 */
public class UpdateDialog extends Dialog {
    Context mContext;
    public UpdateDialog(@NonNull Context context) {
        super(context);
        mContext = context;

    }


    public UpdateDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;

    }
    @Override
    public void dismiss() {
        super.dismiss();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater1 = LayoutInflater.from(mContext);
        View view1 = inflater1.inflate(R.layout.dialog_update, null);
        setContentView(view1);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = mContext.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
    }


}
