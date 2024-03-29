package publi.xz.com.smartcoupon.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * Banner显示类
 */
public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //具体方法内容自己选择
        Glide.with(context.getApplicationContext())
                .load(path)
                .into(imageView);
    }
}
