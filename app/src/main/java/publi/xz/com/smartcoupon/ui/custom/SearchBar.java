package publi.xz.com.smartcoupon.ui.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import publi.xz.com.smartcoupon.R;

public class SearchBar extends LinearLayout implements View.OnClickListener {
    private EditText searchInput;
    private ImageView searchDelete;
    private TextView searchBtn;



    public SearchBar(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.custom_main_bar,this);
    }

    public SearchBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.custom_main_bar,this);

        init();


    }

    private void init() {
        searchInput = findViewById(R.id.search_input);
        searchDelete = findViewById(R.id.search_delete);
        searchBtn = findViewById(R.id.search_btn_a);
        searchBtn.setOnClickListener(this);
        searchDelete.setOnClickListener(this);
        searchInput.clearFocus();//清楚焦点
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.search_btn_a:
                break;
            case R.id.search_delete:
                searchInput.setText("");
                break;
        }
    }
}
