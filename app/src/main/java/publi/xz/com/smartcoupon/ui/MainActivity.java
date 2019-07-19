package publi.xz.com.smartcoupon.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import publi.xz.com.smartcoupon.R;
import publi.xz.com.smartcoupon.adapter.MainAdapter;
import publi.xz.com.smartcoupon.base.BaseActivity;
import publi.xz.com.smartcoupon.entity.MainCNXH;
import publi.xz.com.smartcoupon.utils.GlideImageLoader;
import publi.xz.com.smartcoupon.utils.SpacesItemDecorationVertical;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private Button renqiRank;
    private Button hot_word_rank_btn;
    private Banner banner;
    private Button setting_btn;
    private Button baoyou9_9;
    private RecyclerView cainixihuan_recycler;
    private MainAdapter adapter;
    private NestedScrollView scroller;
    private FloatingActionButton backToTop;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    public int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public void init_Data() {
        Logger.addLogAdapter(new AndroidLogAdapter());

        //设置banner轮播图
        init_banner();

        init_recycler();
    }
    private MainCNXH.DataBean totalList = new MainCNXH.DataBean();

    @Override
    public void showData(Object object) {
        if (object instanceof MainCNXH){
            //追加数据
            totalList.addList(((MainCNXH)object).getData().getList());
            //追加数据
            handler.post(new Runnable() {
                @Override
                public void run() {
//                adapter.refresh(fromJson.getData());
                    adapter.refresh(totalList);
                }
            });
        }else{
            sToast("致命错误");
        }

    }

    private void init_recycler() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        cainixihuan_recycler.setLayoutManager(linearLayoutManager);
        cainixihuan_recycler.addItemDecoration(new SpacesItemDecorationVertical(8));//设置item的间距
        presenter.getGoodsFromNet();
        adapter = new MainAdapter(this);
        cainixihuan_recycler.setAdapter(adapter);
        //滑倒底部检测


        scroller = findViewById(R.id.nestedScroll);
        if (scroller != null) {
            scroller.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                @Override
                public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                    if (scrollY!=0){
                        //不在顶部
                        backToTop.setVisibility(View.VISIBLE);
                    }else{
                        backToTop.setVisibility(View.INVISIBLE);
                    }
                    if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                        //在底部
                        Log.d("xz", "onScrollChange: D");
                        presenter.getGoodsFromNet();

                    }
                }
            });
        }

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

    @Override
    public void findID() {
        renqiRank = findViewById(R.id.pai_hang_bang_btn);
        renqiRank.setOnClickListener(this);
        banner = findViewById(R.id.banner);
        hot_word_rank_btn = findViewById(R.id.hot_word_rank_btn);
        hot_word_rank_btn.setOnClickListener(this);
        setting_btn = findViewById(R.id.setting_btn);
        baoyou9_9 = findViewById(R.id.baoyou9_9);
        setting_btn.setOnClickListener(this);
        baoyou9_9.setOnClickListener(this);
        cainixihuan_recycler = findViewById(R.id.cainixihuan_recycler);
        backToTop = findViewById(R.id.to_top);
        backToTop.setVisibility(View.INVISIBLE);
        backToTop.setOnClickListener(this);
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
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
                break;
            case R.id.baoyou9_9:
                startActivity(new Intent(MainActivity.this, Baoyou9_9Activity.class));
                break;
            case R.id.to_top:
                scroller.setScrollY(0);
                break;
        }
    }



}
