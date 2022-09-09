package org.ranasoftcraft.com.services;

import android.os.AsyncTask;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpHelper extends AsyncTask {
    OkHttpClient client = null;

    public OkHttpHelper(OkHttpClient okHttpClient) {
        this.client = okHttpClient;
    }

    @Override
    protected String doInBackground(Object[] objects) {
        try {
            Response response = client.newCall((Request) objects[0]).execute();
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

