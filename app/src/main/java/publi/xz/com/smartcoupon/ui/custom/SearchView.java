package publi.xz.com.smartcoupon.ui.custom;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import publi.xz.com.smartcoupon.R;

public class SearchView extends LinearLayout {
    public SearchView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.item_search,null);
    }
}
