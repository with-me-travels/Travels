package com.wjx.travelwithm_master.base;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.bugly.crashreport.CrashReport;

public class BaseApp extends Application {
    private static BaseApp app;
    RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        //腾讯bugly初始化 测试的时候改为true，发布时改为false
        CrashReport.initCrashReport(getApplicationContext(), "a97d1b56da", true);

        //检测内存泄漏初始化
        refWatcher = setupLeakCanary();//2

    }


    private RefWatcher setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);//1
    }


    public static RefWatcher getRefWatcher(Context context) {
        BaseApp leakApplication = (BaseApp) context.getApplicationContext();
        return leakApplication.refWatcher;
    }


    public static BaseApp getInstance() {
        return app;
    }
}
