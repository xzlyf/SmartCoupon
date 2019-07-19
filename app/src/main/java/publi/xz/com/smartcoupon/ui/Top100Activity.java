package publi.xz.com.smartcoupon.ui;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import publi.xz.com.smartcoupon.R;
import publi.xz.com.smartcoupon.adapter.Top100Adapter;
import publi.xz.com.smartcoupon.base.BaseActivity;
import publi.xz.com.smartcoupon.constant.Local;
import publi.xz.com.smartcoupon.entity.Popular;
import publi.xz.com.smartcoupon.ui.view.IView;
import publi.xz.com.smartcoupon.utils.CommonUtil;

public class Top100Activity extends BaseActivity  {
    private RecyclerView recycler;
    private ImageView loading_view;
    private Top100Adapter adapter;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    public int getLayoutResource() {
        return R.layout.activity_top100;
    }

    @Override
    public void findID() {

    }

    @Override
    public void init_Data() {
        startLoading();
        setTitle("人气榜");
        loading_view = findViewById(R.id.loading_view);
        init_recycle();
        //请求数据
        presenter.getTop100DataFromNet(Local.TOPURL);
    }

    private List<List<Popular.ResultBean>> lists;
    private List<Popular.ResultBean> total = new ArrayList<>();
    private int list_size;
    private int curr = 1;

    @Override
    public void showData(Object object) {
        if (object instanceof Popular) {
            //强转，把集合拆分成长度为10的小集合
            lists = CommonUtil.groupList(((Popular) object).getResult());
            total.addAll(lists.get(0));
            list_size = lists.size();

            stopLoading();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    adapter = new Top100Adapter(lists.get(0), Top100Activity.this);
                    recycler.setAdapter(adapter);
                }
            });
        } else {
            sToast("致命错误");
        }
    }

    private boolean isLoadingMore = false;
    private LinearLayoutManager linearLayoutManager;

    private void init_recycle() {
        linearLayoutManager = new LinearLayoutManager(this);
        recycler = findViewById(R.id.comm_info_recycler);
        recycler.setLayoutManager(linearLayoutManager);
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

                    if (curr < list_size) {
                        total.addAll(lists.get(curr));
                        adapter.refresh(total);
                        curr++;
                    }

                }
            }

        });
    }



}
