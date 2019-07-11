package publi.xz.com.smartcoupon.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SP工具类
 */
public class SharedPreferencesUtil {

    public static void saveJson(Context context, String key,String data){
        SharedPreferences sp = context.getSharedPreferences("init_data_list",
                Context.MODE_PRIVATE);
        sp.edit().putString(key, data).commit();
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
        sp.edit().putLong(key, time).commit();
    }

    public static String getJson(Context context, String key, String defValue){
        SharedPreferences sp = context.getSharedPreferences("init_data_list",
                Context.MODE_PRIVATE);
        return sp.getString(key,defValue);
    }
}
