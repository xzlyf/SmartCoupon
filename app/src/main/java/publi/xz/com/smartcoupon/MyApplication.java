package publi.xz.com.smartcoupon;

import android.app.Application;
import android.widget.Toast;

import com.alibaba.baichuan.android.trade.AlibcTradeSDK;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeInitCallback;
import com.xz.com.log.LogUtil;

import java.util.logging.Logger;

import publi.xz.com.smartcoupon.ui.MainActivity;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AlibcTradeSDK.asyncInit(this, new AlibcTradeInitCallback() {
            @Override
            public void onSuccess() {
                LogUtil.w("阿里百川初始化成功");
            }

            @Override
            public void onFailure(int code, String msg) {
                LogUtil.e("阿里百川初始化失败:"+code+"信息："+msg);

            }
        });
    }
}
