package publi.xz.com.smartcoupon.constant;

import java.util.LinkedHashMap;
import java.util.Map;

public class Local {
    private static final String APIKEY = "b68d26601f";
    public static String TOPURL = "http://api.dataoke.com/index.php?r=Port/index&type=top100&appkey="+APIKEY+"&v=2";
    public static String DETAILS = "http://api.dataoke.com/index.php?r=port/index&appkey="+APIKEY+"&v=2&id=";

    //Github大神做的免费api接口
    public static String REQUEST_LINK = "https://www.mxnzp.com/";//api头地址
    public static String REQUEST_IDENTIFY = "api/";//网址的标识
    public static String REQUEST_GET_USER_IP = "ip/self";//api尾部
    public static String BASE_URL = REQUEST_LINK + REQUEST_IDENTIFY;
    public static String GET_USER_IP_URL = BASE_URL+ REQUEST_GET_USER_IP;


    public static final String appSecret = "d7f84bb553a185d6e650c1f773aff2e0";
    public static final String appKey = "5d2345f1cae2c";
    public static final String host = "https://openapi.dataoke.com/api/goods/get-ranking-list";

    //轻淘客apikey
    public static final String QT_APIKEY = "ElJPZlgE";
    //热搜词api
    public static final String HOT_WORD = "http://openapi.qingtaoke.com/hot?app_key="+QT_APIKEY+"&v=1.0&t=1";
    /**
     * 对话框标识
     */
    public static String DIALOG_L = "L";

    //初始化返回的状态
    public static Map<String,Boolean> state = new LinkedHashMap<>();
    public static class self{
        public static String ip;//访问者的ip地址
        public static String province;//省份
        public static String city;//城市
        public static String isp;//网络服务商名称


    }
}
