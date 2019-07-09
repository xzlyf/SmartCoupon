package publi.xz.com.smartcoupon.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {

    public static void saveJson(Context context, String key,String data){
        SharedPreferences sp = context.getSharedPreferences("init_data_list",
                Context.MODE_PRIVATE);
        sp.edit().putString(key, data).commit();
    }

    public static String getJson(Context context, String key, String defValue){
        SharedPreferences sp = context.getSharedPreferences("init_data_list",
                Context.MODE_PRIVATE);
        return sp.getString(key,defValue);
    }
}
