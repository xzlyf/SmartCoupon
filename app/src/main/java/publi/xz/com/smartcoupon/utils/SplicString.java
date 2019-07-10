package publi.xz.com.smartcoupon.utils;

import java.util.TreeMap;

public class SplicString {
    /**
     * 拼接URL
     * @param baseUrl
     * @param paraMap
     * @return
     */
    public static String SplicUrl(String baseUrl, TreeMap<String,String> paraMap){

        baseUrl = baseUrl+"?";
        //拼接Url
        for (String key :paraMap.keySet()){
            baseUrl  = baseUrl+key+"="+paraMap.get(key)+"&";
        }
        baseUrl = baseUrl.substring(0,baseUrl.length()-1);
        return baseUrl;
    }
}
