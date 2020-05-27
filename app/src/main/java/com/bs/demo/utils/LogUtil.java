package com.bs.demo.utils;

import android.util.Log;

import com.google.gson.Gson;

/**
 * Log统一管理类
 */
public class LogUtil {

    private LogUtil() { 
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    // 是否需要打印log，可以在application的onCreate函数里面初始化
    public static boolean isDebug = false;
    private static final String TAG = "zjw";

    // 下面四个是默认tag的函数 
    public static void i(String msg) {
        if (isDebug)
            Log.i(TAG,""+ msg);
    }

    public static void d(String msg) {
        if (isDebug)
            Log.d(TAG,""+ msg);
    }
    public static void d(Object o) {
        if (isDebug)
            Log.d(TAG,""+ new Gson().toJson(o));
    }

    public static void e(String msg) {
        if (isDebug)
            Log.e(TAG,""+ msg);
    }

    public static void v(String msg) {
        if (isDebug)
            Log.v(TAG,""+ msg);
    }


    // 下面是传入自定义tag的函数 
    public static void i(String tag, String msg) {
        if (isDebug)
            Log.i(tag,""+ msg);
    }

    public static void d(String tag, String msg) {
        if (isDebug)
            Log.d(tag,""+ msg);
    }

    public static void e(String tag, String msg) {
        if (isDebug)
            Log.e(tag,""+ msg);
    }

    public static void v(String tag, String msg) {
        if (isDebug)
            Log.v(tag,""+ msg);
    }
}