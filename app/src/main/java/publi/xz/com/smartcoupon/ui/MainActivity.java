package publi.xz.com.smartcoupon.ui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import publi.xz.com.smartcoupon.R;
import publi.xz.com.smartcoupon.adapter.MainAdapter;
import publi.xz.com.smartcoupon.base.BaseActivity;
import publi.xz.com.smartcoupon.constant.Local;
import publi.xz.com.smartcoupon.entity.MainCNXH;
import publi.xz.com.smartcoupon.ui.custom.BottmNav;
import publi.xz.com.smartcoupon.utils.GlideImageLoader;
import publi.xz.com.smartcoupon.utils.ItemOnclickListener;
import publi.xz.com.smartcoupon.utils.SpacesItemDecorationVertical;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button renqiRank;
    private Button hot_word_rank_btn;
    private Banner banner;
    private Button btn_3;
    private Button baoyou9_9;
    private RecyclerView cainixihuan_recycler;
    private MainAdapter adapter;
    private NestedScrollView scroller;
    private FloatingActionButton backToTop;
    private BottmNav bottom_nav;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public void init_Data() {
        init_state();
        //设置banner轮播图
        init_banner();
        init_recycler();
        nav_admin();
    }

    /**
     * 软件状态
     */
    private void init_state() {
        if (Local.softState==1){
//            mToast("正常使用");
        }else if (Local.softState==0){
            mToast("停止服务");
            finish();
        }
    }


    @Override
    public void showData(final Object object) {
        if (object instanceof MainCNXH) {
            adapter.refresh(((MainCNXH) object).getData());
        } else {
            mToast("致命错误");
        }

    }

    private void init_recycler() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        cainixihuan_recycler.setLayoutManager(linearLayoutManager);
        cainixihuan_recycler.addItemDecoration(new SpacesItemDecorationVertical(8));//设置item的间距
        presenter.getGoodsFromNet();//开始获取数据
        adapter = new MainAdapter(this);
        cainixihuan_recycler.setAdapter(adapter);
        //滑倒底部检测
        scroller = findViewById(R.id.nestedScroll);
        if (scroller != null) {
            scroller.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                @Override
                public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if (scrollY != 0) {
                        if (scrollY >= 500) {

                            //不在顶部
                            if (backToTop.getVisibility() != View.VISIBLE) {
                                backToTop.startAnimation(bShowAction);
                                backToTop.setVisibility(View.VISIBLE);
                            }
                        }
                    } else {
                        //在顶部
                        if (backToTop.getVisibility() != View.INVISIBLE) {
                            backToTop.startAnimation(bHiddenAction);
                            backToTop.setVisibility(View.INVISIBLE);
                        }

                    }
                    if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                        //在底部
//                        presenter.getGoodsFromNet();
                    }
                    if (oldScrollY < scrollY) {
                        //向下滑-隐藏底部导航栏
                        //先判断是否已经隐藏了
                        if (bottom_nav.getVisibility() != View.INVISIBLE) {
                            //当滚动条滚动位置大于500才开始隐藏动画，这样不用随便一拉就隐藏了
                            if (scrollY >= 500) {
                                bottom_nav.startAnimation(mHiddenAction);
                                bottom_nav.setVisibility(View.INVISIBLE);
                            }
                        }
                    } else if (oldScrollY > scrollY) {
                        //向上划-显示导航栏
                        if (bottom_nav.getVisibility() != View.VISIBLE) {
                            bottom_nav.startAnimation(mShowAction);
                            bottom_nav.setVisibility(View.VISIBLE);

                        }
                    }
                }
            });
        }

        //底部加载更多监听
        adapter.setOnclickListener(new ItemOnclickListener() {
            @Override
            public void OnClick(View view, int position) {
                presenter.getGoodsFromNet();
            }
        });

    }


    private void init_banner() {
        List<String> img = new ArrayList<>();
        img.add("https://s1.ax1x.com/2018/02/14/9YJtQP.jpg");
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
        btn_3 = findViewById(R.id.btn_3);
        baoyou9_9 = findViewById(R.id.baoyou9_9);
        btn_3.setOnClickListener(this);
        baoyou9_9.setOnClickListener(this);
        cainixihuan_recycler = findViewById(R.id.cainixihuan_recycler);
        backToTop = findViewById(R.id.to_top);
        backToTop.setVisibility(View.INVISIBLE);
        backToTop.setOnClickListener(this);
        bottom_nav = findViewById(R.id.bottom_nav);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pai_hang_bang_btn:
                startActivity(new Intent(MainActivity.this, Top100Activity.class));
                break;
            case R.id.hot_word_rank_btn:
                startActivity(new Intent(MainActivity.this, HotWordActivity.class));
                break;
            case R.id.btn_3:
                startActivity(new Intent(MainActivity.this, PPActivity.class));
                break;
            case R.id.baoyou9_9:
                startActivity(new Intent(MainActivity.this, Baoyou9_9Activity.class));
                break;
            case R.id.to_top:
                scroller.setScrollY(0);
                break;

        }
    }

    private TranslateAnimation mHiddenAction;
    private TranslateAnimation mShowAction;
    private TranslateAnimation bShowAction;
    private TranslateAnimation bHiddenAction;

    /**
     * 底部导航栏动画效果
     */
    private void nav_admin() {

        // 隐藏动画
        mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f);
        mHiddenAction.setDuration(500);
        //出现动画
        mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(500);
        // 隐藏动画
        bHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 2.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f);
        bHiddenAction.setDuration(500);
        //出现动画
        bShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 2.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        bShowAction.setDuration(500);
    }

}
