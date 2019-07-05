package publi.xz.com.smartcoupon.ui;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import publi.xz.com.smartcoupon.R;
import publi.xz.com.smartcoupon.adapter.CommodityAdapter;
import publi.xz.com.smartcoupon.base.BaseActivity;
import publi.xz.com.smartcoupon.constant.Local;
import publi.xz.com.smartcoupon.entity.Popular;
import publi.xz.com.smartcoupon.ui.presenter.Presenter_Top100;
import publi.xz.com.smartcoupon.ui.view.IView;

public class Top100Activity extends BaseActivity implements IView{
    private Presenter_Top100 mPresenterTop100;
    private RecyclerView recycler;
    private CommodityAdapter adapter;
    Handler handler = new Handler(){
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
    public void init_Data() {
        mPresenterTop100 = new Presenter_Top100(this);
        init_recycle();
        //请求数据
        mPresenterTop100.getTop100DataFromNet(Local.TOPURL);
    }

    private void init_recycle() {
        recycler = findViewById(R.id.comm_info_recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void startLoading() {
        handler.post(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    @Override
    public void stopLoading() {
        handler.post(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    @Override
    public void sToast(final String msg) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                mToast(msg);
            }
        });
    }

    /**
     * 显示商品信息
     * @param commInfo
     */
    public void setCommInfo(final Popular commInfo) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                adapter = new CommodityAdapter(commInfo,Top100Activity.this);
                recycler.setAdapter(adapter);
            }
        });
    }
}
