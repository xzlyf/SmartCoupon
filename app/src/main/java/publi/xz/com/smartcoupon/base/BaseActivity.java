package publi.xz.com.smartcoupon.base;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import publi.xz.com.smartcoupon.R;
import publi.xz.com.smartcoupon.entity.Baoyou9_9;


public abstract class BaseActivity extends AppCompatActivity {
    private Context mContext;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //公共属性
        mContext = this;

        //公共方法
        setContentView(getLayoutResource());
        findID();
        getPermission();
    }

    private void getPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            } else {
                init_Data();
            }
        } else {
            init_Data();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "" + "权限" + permissions[i] + "申请成功", Toast.LENGTH_SHORT).show();
                    init_Data();
                } else {
                    Toast.makeText(this, "" + "权限" + permissions[i] + "申请失败", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public abstract int getLayoutResource();

    public abstract void findID();

    public abstract void init_Data();

    //常用方法
    Toast mToast;
    private BaseDialog mDialog;

    //常用方法
    public void mToast(final String text) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (!TextUtils.isEmpty(text)) {
                    if (mToast == null) {
                        mToast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                    } else {
                        mToast.setText(text);
                    }
                    mToast.show();
                }
            }
        });
    }

    /**
     * @param msg  消息
     * @param type 类型
     */
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

    private ProgressDialog progressDialog;

    public void showLoading() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                progressDialog = new ProgressDialog(mContext);
                progressDialog.setTitle("加载中");
                progressDialog.setMessage("稍等片刻!");
                progressDialog.setCancelable(false);
                progressDialog.show();
            }
        });

    }

    public void dismissLoading() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }
            }
        });
    }

}
