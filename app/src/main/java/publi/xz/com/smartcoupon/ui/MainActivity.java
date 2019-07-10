package publi.xz.com.smartcoupon.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import publi.xz.com.smartcoupon.R;
import publi.xz.com.smartcoupon.base.BaseActivity;
import publi.xz.com.smartcoupon.utils.GlideImageLoader;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button test;
    private Button hot_word_rank_btn;
    private Banner banner;
    private Button setting_btn;
    private Button baoyou9_9;


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
//        paraMap.put("appKey", "5d24967e3c4d6");
//        paraMap.put("version","v1.0.1");
//        paraMap.put("pageSize","100");
//        paraMap.put("pageId","1");
//        paraMap.put("cid","1");
//        paraMap.put("sign", SignMD5Util.getSignStr(paraMap,"e25f590cc656794c86f7da47ea1ba545"));
//        Log.d("xz", "init_Data: "+paraMap.get("sign"));
    }

    private void init_banner() {
        List<String> img = new ArrayList<>();
        img.add("https://s1.ax1x.com/2018/05/03/Ct1lr9.jpg");
        img.add("https://s1.ax1x.com/2018/04/20/CK32R0.jpg");
        img.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        img.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
        img.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");
        banner.setImages(img)
                .setImageLoader(new GlideImageLoader())
                .setDelayTime(3000)
                .start();


    }

    private void findID() {
        test = findViewById(R.id.pai_hang_bang_btn);
        test.setOnClickListener(this);
        banner = findViewById(R.id.banner);
        hot_word_rank_btn = findViewById(R.id.hot_word_rank_btn);
        hot_word_rank_btn.setOnClickListener(this);
        setting_btn = findViewById( R.id.setting_btn);
        baoyou9_9 = findViewById( R.id.baoyou9_9);
        setting_btn.setOnClickListener(this);
        baoyou9_9.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pai_hang_bang_btn:
                startActivity(new Intent(MainActivity.this, Top100Activity.class));
                break;
            case R.id.hot_word_rank_btn:
                //热搜排行榜
                break;
            case R.id.setting_btn:
                //设置
                break;
            case R.id.baoyou9_9:
                startActivity(new Intent(MainActivity.this,Baoyou9_9Activity.class));
                break;
        }
    }
}
