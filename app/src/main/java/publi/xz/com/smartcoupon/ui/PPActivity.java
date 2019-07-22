package publi.xz.com.smartcoupon.ui;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import java.util.List;

import publi.xz.com.smartcoupon.R;
import publi.xz.com.smartcoupon.adapter.PPAdapter;
import publi.xz.com.smartcoupon.base.BaseActivity;
import publi.xz.com.smartcoupon.entity.PPBrand;
import publi.xz.com.smartcoupon.utils.SpacesItemDecorationVertical;

/**
 * 品牌库
 */
public class PPActivity extends BaseActivity {
    private RecyclerView recyclerPp;
    private PPAdapter adapter;


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    @Override
    public int getLayoutResource() {
        return R.layout.activity_pp;
    }

    @Override
    public void findID() {
        recyclerPp = findViewById(R.id.recycler_pp);

    }

    @Override
    public void init_Data() {
        presenter.getPPBrand();
        init_recycler();
    }

    private void init_recycler() {
        adapter = new PPAdapter(this);
        recyclerPp.setLayoutManager(new LinearLayoutManager(this));
        recyclerPp.addItemDecoration(new SpacesItemDecorationVertical(10));
        recyclerPp.setAdapter(adapter);

    }

    @Override
    public void showData(final Object object) {
        if (object instanceof PPBrand){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    adapter.refresh((PPBrand) object);

                }
            });
        }

    }
}
