package com.example.laba6.other;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MyNewDelayHelper {
    boolean isLocked = false;
    Random random = new Random();
    private static final MyNewDelayHelper ourInstance = new MyNewDelayHelper();

    public static MyNewDelayHelper getInstance() {
        return ourInstance;
    }

    public void performPurchase(final Context context) {
        @SuppressLint("StaticFieldLeak") AsyncTask<Void, Void, Boolean> task = new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                try {
                    TimeUnit.SECONDS.sleep(random.nextInt() % 2 + 3);

                    while (isLocked) {
                        TimeUnit.MILLISECONDS.sleep(200);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return true;
            }
        };

    }
}

