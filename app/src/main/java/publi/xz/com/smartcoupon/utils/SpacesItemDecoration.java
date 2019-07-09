package publi.xz.com.smartcoupon.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 修饰RecyclerView的Item的间距
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    int space;
    public SpacesItemDecoration(int space){
        this.space = space;
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.right = space;

        if (parent.getChildPosition(view)==0){
            outRect.left = space;
        }
    }
}
