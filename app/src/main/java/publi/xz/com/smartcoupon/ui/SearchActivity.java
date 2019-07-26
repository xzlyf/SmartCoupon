package publi.xz.com.smartcoupon.ui;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import publi.xz.com.smartcoupon.R;
import publi.xz.com.smartcoupon.adapter.SearchAdapter;
import publi.xz.com.smartcoupon.base.BaseActivity;
import publi.xz.com.smartcoupon.entity.Search;
import publi.xz.com.smartcoupon.utils.SpacesItemDecorationVertical;

/**
 * 搜索界面
 *
 * 接受intent传入的关键字 ant
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener {
    private String default_ant;
    private ImageView searchBack;
    private EditText searchInput;
    private ImageView searchDelete;
    private TextView searchBtn;
    private RecyclerView recycler;
    private SearchAdapter adapter;


    @Override
    public int getLayoutResource() {
        return R.layout.activity_search;
    }

    @Override
    public void init_Data() {
        findID();
        //获取传入的关键词
        default_ant = getIntent().getStringExtra("ant");
        if (!default_ant.equals("")) {
            searchInput.setText(default_ant);
            init_Recycler();

        }
    }

    @Override
    public void showData(final Object object) {
        if (object instanceof Search) {
            adapter.refresh(((Search) object));
            recycler.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            dismissLoading();
        }else{
            mToast("致命错误");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        default_ant = null;
        searchBack = null;
        searchInput = null;
        searchDelete = null;
        searchBtn = null;
        recycler = null;
        adapter = null;
    }

    private void init_Recycler() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recycler.setLayoutManager(manager);
        recycler.addItemDecoration(new SpacesItemDecorationVertical(8));//设置item的间距
        adapter = new SearchAdapter(SearchActivity.this);
        presenter.getSearchData("0", default_ant, "0", "0", "total_sales");

    }

    public void findID() {
        recycler = findViewById(R.id.search_recycler);
        searchBack = findViewById(R.id.search_back);
        searchInput = findViewById(R.id.search_input);
        searchDelete = findViewById(R.id.search_delete);
        searchBtn = findViewById(R.id.search_btn);
        searchBack.setOnClickListener(this);
        searchDelete.setOnClickListener(this);
        searchBtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_back:
                finish();
                break;
            case R.id.search_delete:
                searchInput.setText("");
                break;
            case R.id.search_btn:
                presenter.getSearchData("0", searchInput.getText().toString().trim(), "0", "0", "total_sales");
                break;
        }
    }

}
