package com.demo.androidlauncherapp;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import java.util.Comparator;

/**
 * @author Delan.S.Patel
 */

class AppInfo implements Comparable<AppInfo> {

    //This is with Comparator Interface
    public static Comparator<AppInfo> AppNameComparator = new Comparator<AppInfo>() {
        public int compare(AppInfo s1, AppInfo s2) {

            String appName1
                    = s1.appName.toString().toUpperCase();
            String appName2
                    = s2.appName.toString().toUpperCase();

            // ascending order
            return appName1.compareTo(
                    appName2);

            // descending order
            // return
            // appName2.compareTo(appName1);
        }
    };
    CharSequence appName;
    CharSequence packageName;
    Drawable icon;
    CharSequence mainActivityClassName;
    int versionCode;
    CharSequence versionName;

    @Override
    public int compareTo(@NonNull AppInfo appInfo) {
        return this.appName.toString().compareTo(((AppInfo) appInfo).appName.toString());
    }
}
