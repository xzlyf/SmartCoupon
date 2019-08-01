package publi.xz.com.smartcoupon.utils;

import android.content.Context;
import android.util.Log;

import com.xz.com.log.LogUtil;

import java.io.File;

/**
 * 缓存信息
 */
public class CacheInfo {
    public static int getTotalCache(Context context) {
        return getPhotoCacheSize(context) + getLogCacheSize(context);
    }

    public static boolean cleanCache(Context context) {
        cleanPhotoCache(context);
        cleanLogCache(context);
        return true;
    }

    /**
     * 获取图片的缓存大小
     * 目前只清理Picasso产生的缓存
     *
     * @param context
     */
    private static int getPhotoCacheSize(Context context) {
        long filesize = 0;
        File file = new File(context.getCacheDir() + "/picasso-cache");
        //刚安装应用会导致缓存文件夹没创建
        if (!file.exists()) {
            return 0;
        }
        File[] files = file.listFiles();
        for (File f : files) {
            if (!f.isDirectory()) {
                filesize = filesize + f.length();
            }
        }
        LogUtil.e("图片" + filesize);

        //返回兆字节
        return (int) (filesize / 1024 / 1024);
    }

    /**
     * 获取图片的缓存大小
     *
     * @param context
     * @return
     */
    private static int getLogCacheSize(Context context) {
        long filesize = 0;
        File file = context.getExternalFilesDir("log");
        if (!file.exists()) {
            return 0;
        }
        File[] files = file.listFiles();
        for (File f : files) {
            if (!f.isDirectory()) {
                filesize = filesize + f.length();
            }
        }
        //返回兆字节\
        LogUtil.e("日志" + filesize);
        return (int) (filesize / 1024 / 1024);
    }

    /**
     * 清理图片缓存
     */
    private static boolean cleanPhotoCache(Context context) {
        File file = new File(context.getCacheDir() + "/picasso-cache");
        //刚安装应用会导致缓存文件夹没创建
        if (!file.exists()) {
            return true;
        }
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

    /**
     * 清理日志缓存
     *
     * @param context
     * @return
     */
    private static boolean cleanLogCache(Context context) {
        File file = context.getExternalFilesDir("log");
        //刚安装应用会导致缓存文件夹没创建
        if (!file.exists()) {
            return true;
        }
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
