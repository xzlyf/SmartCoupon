package com.xz.com.log;

/**
 * 日志工具初始化
 * @author <a href="http://www.xzlyf.club/">xzlyf</a>
 * @date 2019/7/31
 * @version v0.01
 */
public class LogConfig {
    private static LogConfig logConfig;
    private static boolean ShowLog = true;//控制输出
    private static String flag = "default";//输出标识

    private LogConfig() {
    }

    /**
     * 获取单例类
     *
     * @return
     */
    public static LogConfig getInstance() {
        if (logConfig == null) {
            return new LogConfig();
        }
        return logConfig;
    }


    public static boolean getShowLog() {
        return ShowLog;
    }

    public  void setShowLog(boolean showLog) {
        ShowLog = showLog;
    }

    public static String getFlag() {
        return flag;
    }

    public  void setFlag(String flag) {
        LogConfig.flag = flag;
    }

}
