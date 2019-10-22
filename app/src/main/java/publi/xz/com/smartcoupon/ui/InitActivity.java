package publi.xz.com.smartcoupon.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.alibaba.baichuan.trade.biz.login.AlibcLogin;
import com.alibaba.baichuan.trade.biz.login.AlibcLoginCallback;
import com.xz.com.log.LogConfig;
import com.xz.com.log.LogUtil;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import publi.xz.com.smartcoupon.BuildConfig;
import publi.xz.com.smartcoupon.R;
import publi.xz.com.smartcoupon.constant.Local;
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
    private AlibcLogin login;

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        hideBar();
        findID();
        init_log();

        initData();//测试关闭
    }

    /**
     * 初始化日志
     */
    private void init_log() {
        LogConfig config = LogConfig.getInstance();
        config.setShowLog(true);//是否显示日志
        config.setFlag("xzlyf_SmartCoupon");
//        config.toLocal(this,true);//是否输出日志
    }

    @Override
    public void onBackPressed() {

    }


    /**
     * 初始化数据
     */
    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //淘宝账号登录
                login = AlibcLogin.getInstance();
                //检查是否已登录-获取最新的用户资料
                if (SharedPreferencesUtil.getState(InitActivity.this, "autoLogin", false)) {
                    login.showLogin(new AlibcLoginCallback() {
                        @Override
                        public void onSuccess(int i, String s, String s1) {
                            //自动登录成功
                            Local.self.session = login.getSession();
                            LogUtil.d("淘宝账号自动登录成功");
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            //自动登录失败
                            SharedPreferencesUtil.saveState(InitActivity.this, "autoLogin", false);
                            LogUtil.w("淘宝账号自动登录失败:"+i+"  "+s);

                        }
                    });

                }

                //发布版调试版判断
                if (BuildConfig.DEBUG){
                    login.turnOnDebug();
                    LogUtil.d("Debug模式");
                }else{
                    login.turnOffDebug();
                }

                new Presenter_Init(InitActivity.this);

                /*
                CyclicBarrier
                 屏障点（集结点），必须所有人达到集合点才能继续后面的任务
                 */
                int total = 6;//有多少个线程就写多少个
                CyclicBarrier cb = new CyclicBarrier(total + 1);//加上一个主线程

                new Thread(new Presenter_Init.CheckUpdate(cb)).start();
                new Thread(new Presenter_Init.CheckState(cb)).start();
                new Thread(new Presenter_Init.GetServerTime(cb)).start();
                new Thread(new Presenter_Init.GetDetailFromNet(cb)).start();
                new Thread(new Presenter_Init.GetUserIpFromNet(cb)).start();
                new Thread(new Presenter_Init.GetLocalInfo(cb)).start();

                try {
                    cb.await();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LogUtil.map("初始化情況", Local.state);
                startActivity(new Intent(InitActivity.this, MainActivity.class));
                finish();
            }
        }).start();
    }

    private void findID() {
        bar = findViewById(R.id.loading_init);
    }

    private void hideBar() {
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.hide();
        }
    }

}
