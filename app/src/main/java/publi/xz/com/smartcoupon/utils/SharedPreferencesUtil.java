package publi.xz.com.smartcoupon.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SP工具类
 */
public class SharedPreferencesUtil {

    /*
    apply() 异步提交
    commit() 同步提交，效率会比apply异步提交的速度慢，但是apply没有返回值，永远无法知道存储是否失败

     */


    /**
     * 固定式存储
     * @param context
     * @param key
     * @param data
     */
    public static void saveJson(Context context, String key,String data){
        SharedPreferences sp = context.getSharedPreferences("init_data_list",
                Context.MODE_PRIVATE);
        sp.edit().putString(key, data).apply();
    }

    /**
     * 固定式读取
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static String getJson(Context context, String key, String defValue){
        SharedPreferences sp = context.getSharedPreferences("init_data_list",
                Context.MODE_PRIVATE);
        return sp.getString(key,defValue);
    }

    /**
     * 储存时间戳的工具类
     * @param context
     * @param key
     * @param time
     */
    public static void saveJson2time(Context context, String key,long time){
        SharedPreferences sp = context.getSharedPreferences("init_date_time",
                Context.MODE_PRIVATE);
        sp.edit().putLong(key, time).apply();
    }

    /**
     * 存储状态
     * @param context
     * @param key
     * @param value
     */
    public static void saveState(Context context, String key,boolean value){
        SharedPreferences sp = context.getSharedPreferences("state",
                Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).apply();
    }
    /**
     * 读取状态
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static boolean getState(Context context, String key, boolean defValue){
        SharedPreferences sp = context.getSharedPreferences("state",
                Context.MODE_PRIVATE);
        return sp.getBoolean(key,defValue);
    }
}
