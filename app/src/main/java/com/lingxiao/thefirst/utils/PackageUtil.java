package com.lingxiao.thefirst.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;

import java.util.List;

public class PackageUtil {
    public static List<ResolveInfo> getResolveInfos(Context context){
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> resolveInfos = context.getPackageManager()
                .queryIntentActivities(mainIntent, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
        return resolveInfos;
    }

    public static void startActivity(Context context,ResolveInfo resolveInfo) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(resolveInfo.activityInfo.packageName,
                resolveInfo.activityInfo.name));
        context.startActivity(intent);
    }

    public static Drawable getAppIcon(Context context, ResolveInfo resolveInfo) {
        return resolveInfo.loadIcon(context.getPackageManager());
    }

    public static CharSequence getAppName(Context context, ResolveInfo resolveInfo) {
        return resolveInfo.loadLabel(context.getPackageManager());
    }

}
