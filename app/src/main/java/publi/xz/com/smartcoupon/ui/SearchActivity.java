package publi.xz.com.smartcoupon.ui;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import publi.xz.com.smartcoupon.ui.presenter.Presenter_Search;
import publi.xz.com.smartcoupon.ui.view.IView;
import publi.xz.com.smartcoupon.utils.SpacesItemDecorationVertical;

import static publi.xz.com.smartcoupon.utils.TransparentBarUtil.makeStatusBarTransparent;

public class SearchActivity extends BaseActivity implements IView, View.OnClickListener {
    private String default_ant;
    private ImageView searchBack;
    private EditText searchInput;
    private ImageView searchDelete;
    private TextView searchBtn;
    private RecyclerView recycler;
    private Presenter_Search presenter;
    private SearchAdapter adapter;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    public int getLayoutResource() {
        return R.layout.activity_search;
    }

    @Override
    public void init_Data() {
        findID();
        init_Recycler();
    }

    private void init_Recycler() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recycler.setLayoutManager(manager);
        recycler.addItemDecoration(new SpacesItemDecorationVertical(8));//设置item的间距
        presenter.getSearchData("0",default_ant,"0","0","total_sales");

    }

    private void findID() {
        recycler = findViewById(R.id.search_recycler);
        searchBack = findViewById(R.id.search_back);
        searchInput = findViewById(R.id.search_input);
        searchDelete = findViewById(R.id.search_delete);
        searchBtn = findViewById(R.id.search_btn);
        searchBack.setOnClickListener(this);
        searchDelete.setOnClickListener(this);
        searchBtn.setOnClickListener(this);
        default_ant = getIntent().getStringExtra("ant");
        searchInput.setText(default_ant);
        presenter = new Presenter_Search(this);
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
    public void sToast(String msg) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.search_back:
                finish();
                break;
            case R.id.search_delete:
                searchInput.setText("");
                break;
            case R.id.search_btn:
                break;
        }
    }

    public void showCommList(final Search fromJson) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                adapter = new SearchAdapter(SearchActivity.this,fromJson);
                recycler.setAdapter(adapter);
                stopLoading();



            }
        });
    }
}
