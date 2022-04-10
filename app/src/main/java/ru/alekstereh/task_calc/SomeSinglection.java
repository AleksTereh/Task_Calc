package ru.alekstereh.task_calc;

import android.content.Context;

class SomeSingleton {

    private static SomeSingleton INSTANCE;
    private final Context context;

    private SomeSingleton(Context context) {
        this.context = context;
    }

    public static SomeSingleton getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SomeSingleton(context);
        }
        return INSTANCE;
    }

}

