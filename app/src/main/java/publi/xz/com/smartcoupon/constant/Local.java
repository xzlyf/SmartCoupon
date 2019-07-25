package publi.xz.com.smartcoupon.constant;

import java.util.LinkedHashMap;
import java.util.Map;

public class Local {

    public static final String tx_Appid = "101734081";//QQ互联appid

    public static  final String UPDATE_SERVER = "http://www.xzlyf.club/SmartCoupon/update.json";
    //大淘客推广中心 http://www.dataoke.com/pmc/api-manager.html
    private static final String APIKEY = "b68d26601f";
    public static String TOPURL = "http://api.dataoke.com/index.php?r=Port/index&type=top100&appkey="+APIKEY+"&v=2";
    public static String DETAILS = "http://api.dataoke.com/index.php?r=port/index&appkey="+APIKEY+"&v=2&id=";

    //Github大神做的免费api接口
    public static String BASE_URL = "https://www.mxnzp.com/api/";//api头地址
    public static String GET_USER_IP_URL = BASE_URL+ "ip/self";//用户网络信息
    public static String GET_SERVER_TIME=BASE_URL+"tools/system/time";//服务器时间

    /**
     * 大淘客api
     */
    public static final String appSecret = "e25f590cc656794c86f7da47ea1ba545";
    public static final String appKey = "5d24967e3c4d6";
    public static final String appversion = "v1.0.1";

    public static final String BAOYOU9_= "https://openapi.dataoke.com/api/goods/nine/op-goods-list";
    public static final String sousuo= "https://openapi.dataoke.com/api/goods/list-super-goods";
    public static final String PPBrand= "https://openapi.dataoke.com/api/tb-service/get-brand-list";

    /**
     * 轻淘客apikey
     */
    public static final String QT_APIKEY = "ElJPZlgE";
    //热搜词api
    public static final String HOT_WORD = "http://openapi.qingtaoke.com/hot?app_key="+QT_APIKEY+"&v=1.0&t=1";
    /**
     * 对话框标识
     */
    public static String DIALOG_L = "L";//加载
    public static String DIALOG_W = "W";//警告
    public static String DIALOG_E = "E";//错误
    public static String DIALOG_M = "M";//普通消息

    //初始化返回的状态
    public static Map<String,Boolean> state = new LinkedHashMap<>();

    /**
     * 用户信息
     */
    public static class self{
        public static String ip;//访问者的ip地址
        public static String province;//省份
        public static String city;//城市
        public static String isp;//网络服务商名称
        public static long server_time;//服务器时间
    }

    /**
     * 本地软件信息
     */
    public static class LocalInfo{
        public static int versionCode = 0;// 例子:1 数值
        public static String versionName;// 例子:v1.0.1 字符串
        public static int systemVersion;//安卓版本
        public static boolean isshowed = false;//一个对话框是否显示过的标识
    }
}
