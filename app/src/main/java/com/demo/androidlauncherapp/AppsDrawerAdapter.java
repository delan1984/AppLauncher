package com.demo.androidlauncherapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Delan.S.Patel
 */

public class AppsDrawerAdapter extends RecyclerView.Adapter<AppsDrawerAdapter.ViewHolder> {

    private static Context context;
    private static List<AppInfo> appsList;
    private static List<AppInfo> orgAppsList;


    public AppsDrawerAdapter(Context c) {

        //This is where we build our list of app details, using the app
        //object we created to store the label, package name and icon

        context = c;
        setUpApps();


    }

    public static void setUpApps() {

        PackageManager pManager = context.getPackageManager();
        appsList = new ArrayList<AppInfo>();
        orgAppsList = new ArrayList<AppInfo>();

        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> allApps = pManager.queryIntentActivities(i, 0);
        for (ResolveInfo ri : allApps) {
            AppInfo app = new AppInfo();
            app.appName = ri.loadLabel(pManager);
            app.packageName = ri.activityInfo.packageName;
            app.icon = ri.activityInfo.loadIcon(pManager);
            int pos = ri.activityInfo.name.lastIndexOf('.') + 1;
            app.mainActivityClassName = ri.activityInfo.name.substring(pos);
            try {
                PackageInfo pInfo = pManager.getPackageInfo(ri.activityInfo.packageName, 0);
                app.versionName = pInfo.versionName;
                app.versionCode = pInfo.versionCode;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            appsList.add(app);
            orgAppsList.add(app);
            Collections.sort(appsList);
            Collections.sort(orgAppsList);

        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //This is what adds the code we've written in here to our target view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_view_list, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        String appLabel = "App Name : " + appsList.get(position).appName.toString() + "\nPackage Name : " + appsList.get(position).packageName.toString() + "\nActivity Name : " + appsList.get(position).mainActivityClassName.toString() + "\nVersion Name : " + appsList.get(position).versionName.toString() + "\nVersion Code : " + appsList.get(position).versionCode;
        Drawable appIcon = appsList.get(position).icon;

        TextView textView = holder.textView;
        textView.setText(appLabel);
        ImageView imageView = holder.img;
        imageView.setImageDrawable(appIcon);
    }

    @Override
    public int getItemCount() {
        return appsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView;
        public ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);

            //Finds the views from our row.xml
            textView = itemView.findViewById(R.id.tv_app_name);
            img = itemView.findViewById(R.id.app_icon);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            Context context = v.getContext();
            Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(appsList.get(pos).packageName.toString());
            context.startActivity(launchIntent);
            Toast.makeText(v.getContext(), appsList.get(pos).appName.toString(), Toast.LENGTH_LONG).show();

        }

    }
    public static void filterData(String query) {
        query = query.toLowerCase();
        appsList.clear();

        if (query.isEmpty()) {
            appsList.addAll(orgAppsList);
        } else {
            ArrayList<AppInfo> newList = new ArrayList<AppInfo>();
            for (int i = 0; i < orgAppsList.size(); i++) {
                if (orgAppsList.get(i).appName.toString().toLowerCase().contains(query)) {
                    newList.add(orgAppsList.get(i));
                }
            }
            if (newList.size() > 0) {
                appsList.addAll(newList);
            }
        }
    }
}


