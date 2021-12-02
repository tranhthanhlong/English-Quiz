package com.example.englishquiz;

import android.app.Application;

import java.util.Timer;
import java.util.TimerTask;

public class MyApp extends Application {

    private LogoutListener listener;
    private Timer timer;

    public void startUserSession() {
        cancelTimer();
        timer = new Timer();
        timer.schedule( new TimerTask() {
            @Override
            public void run() {
                listener.onSessionLogout();
            }
        }, (1000 * 60 * 10));
    }

    private void cancelTimer() {
        if (timer != null)
            timer.cancel();
    }

    public void registerSessionListener(LogoutListener listener) {
        this.listener = listener;
    }

    public void onUserInteracted() {
        startUserSession();
    }
}
