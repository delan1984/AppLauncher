package com.demo.androidlauncherapp;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.demo.androidlauncherapp.broadcastreceiver.InstallUninstallAppIntentReceiver;

/**
 * @author Delan.S.Patel
 */
public class MyService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService", "Started");
        IntentFilter s_intentFilter = new IntentFilter();
        s_intentFilter.addAction(Intent.ACTION_PACKAGE_FULLY_REMOVED);
        s_intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
        s_intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        s_intentFilter.addAction(Intent.ACTION_PACKAGE_CHANGED);
        s_intentFilter.addAction(Intent.ACTION_PACKAGE_REPLACED);
        s_intentFilter.addAction(Intent.ACTION_MY_PACKAGE_REPLACED);
        s_intentFilter.addDataScheme("package");
        registerReceiver(new InstallUninstallAppIntentReceiver(), s_intentFilter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
