package com.hjq.base;


import android.widget.Toast;

import com.orhanobut.logger.Logger;

/**
 * @author : zyw
 * @description :日志打印工具类
 * @date : 2020/6/16
 * @modifier : zyw
 * @date : 2020/6/16
 */
public class LogUtil {


    /**
     * 注意：打包的时候记得设置isDebug为false
     */
    public static boolean isDebug = true;

    private static String defaultTag() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        StackTraceElement log = stackTrace[1];
        String tag = null;
        for (int i = 1; i < stackTrace.length; i++) {
            StackTraceElement e = stackTrace[i];
            //stackTrace第一位是线程对象,第二位开始是此方法具体被调用的位置.
            if (!e.getClassName().equals(log.getClassName())) {
                //所以拿到上一个调用的类则是 i+2
                tag = stackTrace[i + 2].getFileName();
                break;
            }
        }
        if (tag == null) {
            tag = log.getClassName() + "." + log.getMethodName();
        }
        return tag;
    }

    public static void e(String msg) {
        if (isDebug) {
            Logger.t("-" + defaultTag()).e(msg + "");
        }
    }

    public static void d(String msg) {
        if (isDebug) {
            Logger.t("-" + defaultTag()).d(msg + "");
        }
    }

    public static void i(String msg) {
        if (isDebug) {
            Logger.t("-" + defaultTag()).i(msg + "");
        }
    }

    public static void v(String msg) {
        if (isDebug) {
            Logger.t("-" + defaultTag()).v(msg + "");
        }
    }

    public static void w(String msg) {
        if (isDebug) {
            Logger.t("-" + defaultTag()).w(msg + "");
        }
    }

//    public static void e(String msg) {
//        if (isDebug) {
//            Logger.e(msg + "");
//        }
//    }
//
//    public static void d(String msg) {
//        if (isDebug) {
//            Logger.d(msg + "");
//        }
//    }
//
//    public static void i(String msg) {
//        if (isDebug) {
//            Logger.i(msg + "");
//        }
//    }
//
//    public static void v(String msg) {
//        if (isDebug) {
//            Logger.v(msg + "");
//        }
//    }
//
//    public static void w(String msg) {
//        if (isDebug) {
//            Logger.w(msg + "");
//        }
//    }
//
//    public static void json(String json) {
//        if (isDebug) {
//            Logger.json(json);
//        }
//    }
//
//    public static void xml(String xml) {
//        if (isDebug) {
//            Logger.xml(xml);
//        }
//    }
//
//    public static void wtf(String wtf) {
//        if (isDebug) {
//            Logger.wtf(wtf);
//        }
//    }
//
//    public static void showToast(String msg) {
//        if (isDebug) {
//            Toast.makeText(YMSDK.mainappref, msg, Toast.LENGTH_SHORT).show();
//        }
//    }
}
