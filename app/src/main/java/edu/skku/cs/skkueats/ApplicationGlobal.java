package edu.skku.cs.skkueats;

import android.app.Application;

public class ApplicationGlobal extends Application {
    private static String serverURL;
    @Override
    public void onCreate() {
        this.serverURL = "http://3.39.192.139:5000/";
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public String getServerURL() {
        return serverURL;
    }

    public void setServerURL(String serverURL) {
        this.serverURL = serverURL;
    }
}
