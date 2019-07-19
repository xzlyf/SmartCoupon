package publi.xz.com.smartcoupon.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;

import publi.xz.com.smartcoupon.R;
import publi.xz.com.smartcoupon.adapter.HotRankAdapter;
import publi.xz.com.smartcoupon.base.BaseActivity;
import publi.xz.com.smartcoupon.entity.HotWord;
import publi.xz.com.smartcoupon.utils.SharedPreferencesUtil;
import publi.xz.com.smartcoupon.utils.SpacesItemDecorationVH;
import publi.xz.com.smartcoupon.utils.SpacesItemDecorationVertical;

public class HotWordActivity extends BaseActivity {

    private RecyclerView recyclerKey;
    private HotRankAdapter adapter;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_hot_word;
    }

    @Override
    public void findID() {

    }

    @Override
    public void init_Data() {
        init_recycler();
    }

    private void init_recycler() {
        //尝试读取本地数据
        String jsonData = SharedPreferencesUtil.getJson(this, "HOT_WORD", "null");
        if (jsonData.equals("null")) {
            return;
        }
        Gson gson = new Gson();
        recyclerKey = findViewById(R.id.recycler_key);
        recyclerKey.setLayoutManager(new GridLayoutManager(this,3));
        recyclerKey.addItemDecoration(new SpacesItemDecorationVH(20));//设置item的间距

        adapter=new HotRankAdapter(this,gson.fromJson(jsonData, HotWord.class));
        recyclerKey.setAdapter(adapter);
    }

    @Override
    public void showData(Object object) {

    }
}
