package publi.xz.com.smartcoupon.ui;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import publi.xz.com.smartcoupon.R;
import publi.xz.com.smartcoupon.adapter.Baoyou9_9Adapter;
import publi.xz.com.smartcoupon.base.BaseActivity;
import publi.xz.com.smartcoupon.entity.Baoyou9_9;
import publi.xz.com.smartcoupon.ui.presenter.Presenter_Baoyou9_9;
import publi.xz.com.smartcoupon.ui.view.IView;

public class Baoyou9_9Activity extends BaseActivity implements IView {
    private Presenter_Baoyou9_9 presenter;
    private Baoyou9_9Adapter adapter;
    private RecyclerView recycler;

    Handler handler = new Handler(){
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
        //可以加载很多的
        presenter.get9_9tehui("100", "1", "1");
    }

    private void init_recycler() {
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }


    private void findID() {
        presenter = new Presenter_Baoyou9_9(this);
        recycler = findViewById(R.id.baoyou_recycler_view);
    }

    @Override
    public void startLoading() {
        showLoading();
    }

    @Override
    public void stopLoading() {
        dismissLoading();
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
     * @param baoyou9_9
     */
    public void showRecycler(final Baoyou9_9 baoyou9_9) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                adapter = new Baoyou9_9Adapter(Baoyou9_9Activity.this,baoyou9_9);
                recycler.setAdapter(adapter);
                stopLoading();
            }
        });
    }
}
