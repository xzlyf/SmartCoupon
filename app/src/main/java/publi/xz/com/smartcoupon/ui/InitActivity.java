package publi.xz.com.smartcoupon.ui;

import android.app.slice.Slice;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import publi.xz.com.smartcoupon.R;
import publi.xz.com.smartcoupon.constant.Local;
import publi.xz.com.smartcoupon.entity.HotWord;
import publi.xz.com.smartcoupon.ui.model.IModel;
import publi.xz.com.smartcoupon.ui.presenter.Presenter_Init;
import publi.xz.com.smartcoupon.utils.SharedPreferencesUtil;

/**
 * 启动类
 * 负责初始化一些数据
 * 以及缓存一些数据
 * 减轻调用Api
 */
public class InitActivity extends AppCompatActivity {
    private ProgressBar bar;
    private Presenter_Init model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        Logger.addLogAdapter(new AndroidLogAdapter());
        hideBar();
        findID();
        initData();
        waitTime();
    }

    private void waitTime() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    startActivity(new Intent(InitActivity.this,MainActivity.class));
                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        //获取服务器时间
        model.getServerTime();
        //获取今日热搜词
        model.getDetailFromNet();
        //获取用户网络信息
        model.getUserIpFromNet();
        //获取更新信息
        model.checkUpdate();
        //获取本地软件信息;
        model.getLocalInfo();

    }

    private void findID() {
        bar = findViewById(R.id.loading_init);
        model = new Presenter_Init(this);
    }

    private void hideBar() {
        ActionBar bar = getSupportActionBar();
        if (bar!=null){
            bar.hide();
        }
    }
}
