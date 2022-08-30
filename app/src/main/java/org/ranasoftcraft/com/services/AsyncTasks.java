package org.ranasoftcraft.com.services;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import org.ranasoftcraft.com.exception.LoginException;

import java.io.IOException;
import java.util.logging.Logger;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AsyncTasks extends AsyncTask<Request,String, Response> {

    private final Logger logger = Logger.getLogger(AsyncTasks.class.getName());

    private OkHttpClient client;

    public AsyncTasks() {
        this.client = new OkHttpClient();
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @SuppressLint("WrongThread")
    @Override
    protected Response doInBackground(Request... requests) {
        try {
            onProgressUpdate("Start to hit http to server");
            return this.client.newCall(requests[0]).execute();
        } catch (IOException e) {
            throw new LoginException(e.getMessage());
        }
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Response o) {
        logger.info(o.toString());
        super.onPostExecute(o);
    }
}
