package publi.xz.com.smartcoupon.ui.custom;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import publi.xz.com.smartcoupon.R;
import publi.xz.com.smartcoupon.adapter.HotWordAdapter;
import publi.xz.com.smartcoupon.entity.HotWord;
import publi.xz.com.smartcoupon.ui.SearchActivity;
import publi.xz.com.smartcoupon.utils.SharedPreferencesUtil;
import publi.xz.com.smartcoupon.utils.SpacesItemDecorationHorizontal;

public class SearchBar extends LinearLayout implements View.OnClickListener {
    private Context context;
    private EditText search_input;
    private ImageView search_delete;
    private TextView search_btn;
    private RecyclerView recyclerView;
    private HotWordAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    public SearchBar(Context context) {
        super(context);
    }

    public SearchBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.custom_main_bar, this);
        this.context = context;

        findID();
        init_recycler();
    }


    /**
     * 加载热词
     */
    private void init_recycler() {
        //尝试读取本地json数据
        String jsonData = SharedPreferencesUtil.getJson(context, "HOT_WORD", "null");
        if (jsonData.equals("null")){
            recyclerView.setVisibility(View.GONE);
            return;
        }
        Gson gson = new Gson();
        HotWord hotWord = gson.fromJson(jsonData, HotWord.class);
        List<HotWord.DataBean> list = hotWord.getData();
        adapter = new HotWordAdapter(context, list);
        linearLayoutManager = new LinearLayoutManager(context);
        //横向布局
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecorationHorizontal(15));
        recyclerView.setAdapter(adapter);
    }

    private void findID() {
        search_input = findViewById(R.id.search_input);
        search_delete = findViewById(R.id.search_delete);
        search_btn = findViewById(R.id.search_btn);
        search_btn.setOnClickListener(this);
        search_delete.setOnClickListener(this);
        recyclerView = findViewById(R.id.hot_work_recycler);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_btn:
                context.startActivity(new Intent(context, SearchActivity.class).putExtra("ant",search_input.getText().toString().trim()));
                break;
            case R.id.search_delete:
                search_input.setText("");
                break;
        }
    }
}
