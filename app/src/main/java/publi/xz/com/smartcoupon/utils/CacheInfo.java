package publi.xz.com.smartcoupon.utils;

import android.content.Context;

import java.io.File;

/**
 * 缓存信息
 */
public class CacheInfo {
    /**
     * 获取图片的缓存大小
     *  目前只清理Picasso产生的缓存
     * @param context
     */
    public static int getPhotoCacheSize(Context context) {
        long filesize = 0;
        File file = new File(context.getCacheDir() + "/picasso-cache");
        File[] files = file.listFiles();
        for (File f : files) {
            if (!f.isDirectory()) {
                filesize = filesize + f.length();
            }
        }
        //返回兆字节
        return (int) (filesize / 1024 / 1024);
    }

    /**
     * 清理图片缓存
     */
    public static boolean cleanPhotoCache(Context context) {
        File file = new File(context.getCacheDir() + "/picasso-cache");
        File[] files = file.listFiles();
        for (File f : files
        ) {
            if (f.exists()) {
                if (!f.isDirectory()) {
                    f.delete();
                }
            }

        }
        return true;
    }
}
