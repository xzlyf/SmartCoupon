package publi.xz.com.smartcoupon.ui;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import publi.xz.com.smartcoupon.R;
import publi.xz.com.smartcoupon.adapter.Baoyou9_9Adapter;
import publi.xz.com.smartcoupon.base.BaseActivity;
import publi.xz.com.smartcoupon.entity.Baoyou9_9;
import publi.xz.com.smartcoupon.utils.SpacesItemDecorationVertical;

public class Baoyou9_9Activity extends BaseActivity {
    private Baoyou9_9Adapter adapter;
    private RecyclerView recycler;

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
        //可以加载很多的
        presenter.get9_9tehui("100", "1", "1");
    }

    @Override
    public void showData(final Object object) {
        if (object instanceof Baoyou9_9) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    adapter = new Baoyou9_9Adapter(Baoyou9_9Activity.this, (Baoyou9_9) object);
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
    }


    public void findID() {
        recycler = findViewById(R.id.baoyou_recycler_view);
        recycler.addItemDecoration(new SpacesItemDecorationVertical(8));//设置item的间距

    }


}
