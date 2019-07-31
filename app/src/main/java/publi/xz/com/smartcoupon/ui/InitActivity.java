package publi.xz.com.smartcoupon.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.xz.com.log.LogConfig;

import publi.xz.com.smartcoupon.R;
import publi.xz.com.smartcoupon.ui.presenter.Presenter_Init;

/**
 * 启动类
 * 负责初始化一些数据
 * 以及缓存一些数据
 * 减轻调用Api
 */
public class InitActivity extends AppCompatActivity {
    private int waitTime = 3000;//等待时间 目前等待时间3秒刚刚好
    private ProgressBar bar;
    private Presenter_Init model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        hideBar();
        findID();
        init_log();
        initData();//测试关闭
        waitTime();
    }

    /**
     * 初始化日志
     */
    private void init_log() {
        LogConfig config = LogConfig.getInstance();
        config.setShowLog(true);//初始化log日志
        config.setFlag("xzlyf_SmartCoupon");
    }

    @Override
    public void onBackPressed() {

    }

    private void waitTime() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(waitTime);
                    startActivity(new Intent(InitActivity.this, MainActivity.class));
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
        //获取更新信息
        model.checkUpdate();
        //获取软件状态
        model.checkState();
        //获取服务器时间
        model.getServerTime();
        //获取今日热搜词
        model.getDetailFromNet();
        //获取用户网络信息
        model.getUserIpFromNet();
        //获取本地软件信息;
        model.getLocalInfo();

    }

    private void findID() {
        bar = findViewById(R.id.loading_init);
        model = new Presenter_Init(this);
    }

    private void hideBar() {
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.hide();
        }
    }
}
