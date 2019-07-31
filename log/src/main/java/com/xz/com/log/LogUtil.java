package com.xz.com.log;

import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 日志工具调用类
 *
 * @author <a href="http://www.xzlyf.club/">xzlyf</a>
 * @version v0.01
 * @date 2019/7/31
 */
public class LogUtil {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static boolean showlog = LogConfig.getShowLog();
    private static String tag = LogConfig.getFlag();


    /**
     * json格式输出=================================================================
     *
     * @param head 标识
     * @param msg  json内容
     */
    public static void json(String head, String msg) {
        if (!showlog) return;

        String message;
        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(4);
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(4);
            } else {
                message = msg;
            }
        } catch (JSONException e) {
            message = msg;
        }

        printLinetop(Log.DEBUG);

        Log.d(tag, "║ " + getFunctionName());

        printLinemiddle(Log.DEBUG);

        Log.d(tag, "║ " + head);

        printLinemiddle(Log.DEBUG);

//        message = head + LINE_SEPARATOR + message;
        String[] lines = message.split(LINE_SEPARATOR);
        for (String line : lines) {
            Log.d(tag, "║ " + line);
        }
        printLinebottom(Log.DEBUG);
    }

    /**
     * list集合输出=================================================================
     *
     * @param list
     */
    public static void list(String head,@NonNull List<?> list) {
        printLinetop(Log.DEBUG);
        Log.println(Log.DEBUG, tag, "║ " + getFunctionName());
        printLinemiddle(Log.DEBUG);
        Log.d(tag, "║ " + head);
        printLinemiddle(Log.DEBUG);
        for (Object obj : list) {
            Log.println(Log.DEBUG, tag, "║ " + obj.toString());
        }
        printLinebottom(Log.DEBUG);
    }

    /**
     * map集合的遍历================================================================
     *
     * @param map
     */
    public static void map(String head,@NonNull Map<?, ?> map) {
        printLinetop(Log.DEBUG);
        Log.println(Log.DEBUG, tag, "║ " + getFunctionName());
        printLinemiddle(Log.DEBUG);
        Log.d(tag, "║ " + head);
        printLinemiddle(Log.DEBUG);
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Log.println(Log.DEBUG, tag,"║ " +entry.getKey()+":"+entry.getValue());
        }
        printLinebottom(Log.DEBUG);
    }


    /**
     * 普通日志输出==================================================================
     *
     * @param obj
     */
    public static void v(Object... obj) {
        print(Log.VERBOSE, obj);
    }

    public static void d(Object... obj) {
        print(Log.DEBUG, obj);
    }

    public static void i(Object... obj) {
        print(Log.INFO, obj);
    }

    public static void w(Object... obj) {
        print(Log.WARN, obj);
    }

    public static void e(Object... obj) {
        print(Log.ERROR, obj);
    }


    private static void print(int verbose, Object[] obj) {
        if (!showlog) return;
        printLinetop(verbose);
        Log.println(verbose, tag, "║ " + getFunctionName());
        printLinemiddle(verbose);
        for (Object line : obj) {
            Log.println(verbose, tag, "║ " + line);
        }
        printLinebottom(verbose);

    }

    private static void printLinetop(int level) {
        Log.println(level, tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
    }

    private static void printLinemiddle(int level) {
        Log.println(level, tag, "╠═══════════════════════════════════════════════════════════════════════════════════════");

    }

    private static void printLinebottom(int level) {
        Log.println(level, tag, "╚══════════════════════════════════════════════════════════════════════════════════════");

    }


    /**
     * 获取打印信息所在方法名，行号等信息
     *
     * @return
     */
    private static String getFunctionName() {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        if (sts != null) {
            for (StackTraceElement st : sts) {
                if (st.isNativeMethod()) {
                    continue;
                }
                if (st.getClassName().equals(Thread.class.getName())) {
                    continue;
                }
                if (st.getClassName().equals(LogUtil.class.getName())) {
                    continue;
                }
                return "Thread:" + Thread.currentThread().getName() + ", at " + st.getClassName() + "." + st.getMethodName()
                        + "(" + st.getFileName() + ":" + st.getLineNumber() + ")";
            }
        }
        return null;
    }
}
