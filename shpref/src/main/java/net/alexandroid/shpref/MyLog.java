package net.alexandroid.shpref;

import android.util.Log;

import java.text.MessageFormat;

public class MyLog {
    private static String sTag = "MyLog - ";
    private static boolean sShowLogs = false;

    public static void showLogs(boolean pShowLogs) {
        sShowLogs = pShowLogs;
    }

    public static void setTag(String pTag) {
        sTag = pTag + " - ";
    }

    public static void v(String msg) {
        if (sShowLogs) {
            logIt(Log.VERBOSE, msg);
        }
    }

    public static void d(String msg) {
        if (sShowLogs) {
            logIt(Log.DEBUG, msg);
        }
    }

    public static void i(String msg) {
        if (sShowLogs) {
            logIt(Log.INFO, msg);
        }
    }


    public static void w(String msg) {
        if (sShowLogs) {
            logIt(Log.WARN, msg);
        }
    }

    public static void e(String msg) {
        if (sShowLogs) {
            logIt(Log.ERROR, msg);
        }
    }

    public static void a(String msg) {
        if (sShowLogs) {
            logIt(Log.ASSERT, msg);
        }
    }


    private static void logIt(int level, String msg) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace != null && stackTrace.length > 4) {
            StackTraceElement element = stackTrace[4];
            String fullClassName = element.getClassName();

            StringBuilder simpleClassName = new StringBuilder(fullClassName.replace(Contextor.getInstance().getContext().getPackageName(), ""));
            while (simpleClassName.length() < 35) simpleClassName.append(" ");

            StringBuilder methodName = new StringBuilder(element.getMethodName());
            methodName.append("()");
            while (methodName.length() < 25) methodName.append(" ");

            StringBuilder threadId = new StringBuilder(String.valueOf(Thread.currentThread().getId()));
            while (threadId.length() < 6) threadId.append(" ");

            msg = MessageFormat.format("T:{0} | {1} # {2} => {3}", threadId, simpleClassName, methodName, msg);
            Log.println(level, sTag, msg);
        }
    }

}
