package publi.xz.com.smartcoupon.ui.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import publi.xz.com.smartcoupon.R;

public class SearchBar extends LinearLayout implements View.OnClickListener{
    private EditText search_input;
    private ImageView search_delete;
    private TextView search_btn;

    public SearchBar(Context context) {
        super(context);
    }

    public SearchBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.custom_main_bar,this);

        findID();
    }

    private void findID() {
        search_input = findViewById(R.id.search_input);
        search_delete = findViewById(R.id.search_delete);
        search_btn = findViewById(R.id.search_btn);
        search_btn.setOnClickListener(this);
        search_delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.search_btn:
                break;
            case R.id.search_delete:
                search_input.setText("");
                break;
        }
    }
}
