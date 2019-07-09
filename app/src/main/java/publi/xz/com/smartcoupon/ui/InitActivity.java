package publi.xz.com.smartcoupon.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import publi.xz.com.smartcoupon.R;

/**
 * 启动类
 * 负责初始化一些数据
 * 以及缓存一些数据
 * 减轻调用Api
 */
public class InitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
    }
}
