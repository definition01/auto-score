package com.sennan.common.context;

public class BaseContext {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();


    public static void setCurrentName(String username) {
       threadLocal.set(username);
    }

    public static String getCurrentName() {
        return threadLocal.get();
    }

    public static void removeCurrentName() {
        threadLocal.remove();
    }

}