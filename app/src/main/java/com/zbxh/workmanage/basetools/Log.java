package com.zbxh.workmanage.basetools;


/**
 * Created by ljt on 2017-08-15 10:03
 * Email: jiantaoli@yeah.net
 * QQ: 178771128
 * Company: 北京中北信号软件有限公司
 * <p>
 * Description: app日志管理类
 */

public class Log {

    private static boolean DEBUG = true;
    private static String TAG = "zbxhWorkMag";

    public static void setLog(boolean log) {

        Log.DEBUG = log;
    }

    public static boolean getLog() {

        return Log.DEBUG;
    }

    public static void setTag(String tag) {

        Log.TAG = tag;
    }

    public static void i(String tag, String msg) {
        if (DEBUG)
            android.util.Log.i(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (DEBUG)
            android.util.Log.e(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (DEBUG)
            android.util.Log.d(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (DEBUG)
            android.util.Log.v(tag, msg);
    }

    public static void w(String tag, String msg) {
        if (DEBUG)
            android.util.Log.w(tag, msg);
    }

    public static void i(String msg) {
        if (DEBUG)
            android.util.Log.i(TAG, msg);
    }

    public static void d(String msg) {
        if (DEBUG)
            android.util.Log.d(TAG, msg);
    }

    public static void e(String msg) {
        if (DEBUG)
            android.util.Log.e(TAG, msg);
    }

    public static void w(String msg) {
        if (DEBUG)
            android.util.Log.w(TAG, msg);
    }
}
