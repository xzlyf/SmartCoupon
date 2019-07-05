package publi.xz.com.smartcoupon.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import publi.xz.com.smartcoupon.R;


public abstract class BaseActivity extends AppCompatActivity {
    private Context mContext;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //公共属性
        mContext = this;
        Logger.addLogAdapter(new AndroidLogAdapter());
        //公共方法
        setContentView(getLayoutResource());
        init_Data();
    }

    public abstract int getLayoutResource();

    public abstract void init_Data();

    //常用方法
    Toast mToast;
    private BaseDialog mDialog;

    //常用方法
    public void mToast(String text) {
        if (!TextUtils.isEmpty(text)) {
            if (mToast == null) {
                mToast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
            } else {
                mToast.setText(text);
            }
            mToast.show();
        }
    }

    public void showDialog(final String msg, final String type) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                mDialog = new BaseDialog(mContext, R.style.base_dialog);
                mDialog.create();
                mDialog.setType(type);
                mDialog.setMsg(msg);
                mDialog.show();
            }
        });
    }

    public void dismissDialog() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (mDialog != null && mDialog.isShowing()) {
                    mDialog.dismiss();
                    mDialog = null;
                }
            }
        });
    }

}
