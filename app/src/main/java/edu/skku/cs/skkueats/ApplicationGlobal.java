package edu.skku.cs.skkueats;

import android.app.Application;

public class ApplicationGlobal extends Application {
    private String serverURL;
    @Override
    public void onCreate() {
        super.onCreate();
        serverURL = "http://3.39.192.139:5000/";
    }

    public String getServerURL() {
        return serverURL;
    }

    public void setServerURL(String serverURL) {
        this.serverURL = serverURL;
    }
}
