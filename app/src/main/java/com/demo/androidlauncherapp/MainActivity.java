package com.demo.androidlauncherapp;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.demo.androidlauncherapp.broadcastreceiver.InstallUninstallAppIntentReceiver;

/**
 * @author Delan.S.Patel
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new HomeScreenFragment());
        //We can done it with the use of Service/Intent Service
        //startService(new Intent(this, MyService.class));

        //this is Without Service/IntentService
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


    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
            return true;
        }

        return false;
    }

    @Override
    public void onBackPressed() {

    }
}
