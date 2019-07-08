package publi.xz.com.smartcoupon.ui.custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import publi.xz.com.smartcoupon.R;

public class SearchBar extends LinearLayout {
    public SearchBar(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.custom_main_bar,null);
    }
}
