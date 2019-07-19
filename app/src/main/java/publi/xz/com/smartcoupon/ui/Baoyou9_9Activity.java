package publi.xz.com.smartcoupon.ui;

import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import publi.xz.com.smartcoupon.R;
import publi.xz.com.smartcoupon.adapter.Baoyou9_9Adapter;
import publi.xz.com.smartcoupon.base.BaseActivity;
import publi.xz.com.smartcoupon.entity.Baoyou9_9;
import publi.xz.com.smartcoupon.utils.SharedPreferencesUtil;
import publi.xz.com.smartcoupon.utils.SpacesItemDecorationVertical;

public class Baoyou9_9Activity extends BaseActivity {
    private Baoyou9_9Adapter adapter;
    private RecyclerView recycler;
    private TabLayout tabs;
    private int cid = 1;

    /**
     * 一级分类id请求详情：-1-精选，1 -居家百货，2 -美食，3 -服饰，4 -配饰，5 -美妆，6 -内衣，7 -母婴，8 -箱包，9 -数码配件，10 -文娱车品
     */
    private String[] cids = { "居家百货", "美食", "服饰", "配饰", "美妆", "内衣", "母婴", "箱包", "数码配件", "文娱车品"};

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    public int getLayoutResource() {
        return R.layout.activity_baoyou9_9;
    }

    @Override
    public void init_Data() {
        startLoading();
        findID();
        init_recycler();
        presenter.get9_9tehui("10", "1", cid+"");
        init_tabs();

    }

    private void init_tabs() {
        for (int i = 0; i < cids.length; i++) {
            tabs.addTab(tabs.newTab().setText(cids[i]));
        }
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                cid = (tab.getPosition()+1);
                //当前选中
                presenter.get9_9tehui("10", "1", ""+cid);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //未选中

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

                //再次选中
            }
        });
    }

    @Override
    public void showData(final Object object) {
        if (object instanceof Baoyou9_9) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    adapter.refresh((Baoyou9_9) object);
                    recycler.setAdapter(adapter);
                    stopLoading();
                }
            });
        } else {
            sToast("致命错误");
        }
    }

    private void init_recycler() {
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Baoyou9_9Adapter(Baoyou9_9Activity.this);
        //滑倒底部判断
        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                //得到当前显示的最后一个item的view
                View lastChildView = recyclerView.getLayoutManager().getChildAt(recyclerView.getLayoutManager().getChildCount() - 1);
                //得到lastChildView的bottom坐标值
                int lastChildBottom = lastChildView.getBottom();
                //得到Recyclerview的底部坐标减去底部padding值，也就是显示内容最底部的坐标
                int recyclerBottom = recyclerView.getBottom() - recyclerView.getPaddingBottom();
                //通过这个lastChildView得到这个view当前的position值
                int lastPosition = recyclerView.getLayoutManager().getPosition(lastChildView);

                //判断lastChildView的bottom值跟recyclerBottom
                //判断lastPosition是不是最后一个position
                //如果两个条件都满足则说明是真正的滑动到了底部
                if (lastChildBottom == recyclerBottom && lastPosition == recyclerView.getLayoutManager().getItemCount() - 1) {
//                    presenter.get9_9tehui("10", "1", ""+cid);

                }
            }

        });
    }


    public void findID() {
        recycler = findViewById(R.id.baoyou_recycler_view);
        recycler.addItemDecoration(new SpacesItemDecorationVertical(8));//设置item的间距
        tabs = findViewById(R.id.tabs);

    }


}
