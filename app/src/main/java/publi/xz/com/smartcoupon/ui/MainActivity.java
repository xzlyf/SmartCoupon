package publi.xz.com.smartcoupon.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import publi.xz.com.smartcoupon.R;
import publi.xz.com.smartcoupon.base.BaseActivity;
import publi.xz.com.smartcoupon.constant.Local;
import publi.xz.com.smartcoupon.utils.GlideImageLoader;
import publi.xz.com.smartcoupon.utils.SignMD5Util;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button test;
    private Banner banner;


    @Override
    public int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public void init_Data() {
        findID();
        //设置banner轮播图
        init_banner();

//        TreeMap<String,String> paraMap = new TreeMap<>();
//        paraMap.put("appKey", "5d2345f1cae2c");
//        paraMap.put("version","v1.0.1");
//        paraMap.put("sign", SignMD5Util.getSignStr(paraMap,"13fec554229f608f816c2aa36c355559"));
//
//
//        Log.d("xz", "init_Data: "+paraMap.get("sign"));
    }

    private void init_banner() {
        List<String> img=new ArrayList<>();
        img.add("https://s2.ax1x.com/2019/07/09/ZyGEHH.png");
        img.add("https://s2.ax1x.com/2019/07/09/ZyGQv8.png");
        img.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        img.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
        img.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");
        banner.setImages(img)
                .setImageLoader(new GlideImageLoader())
                .setDelayTime(3000)
                .start();


    }

    private void findID() {
        test = findViewById(R.id.test);
        test.setOnClickListener(this);
        banner = findViewById(R.id.banner);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.test:
                startActivity(new Intent(MainActivity.this, Top100Activity.class));
                break;
        }
    }
}
