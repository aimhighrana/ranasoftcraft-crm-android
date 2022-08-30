package org.ranasoftcraft.com.services;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;

import org.ranasoftcraft.com.data.model.LoggedInUser;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author sandeep.rana
 */
public class CustomRestTemplate {

    private final Logger logger = Logger.getLogger(CustomRestTemplate.class.getName());
    private HttpUrls httpUrls;
    private OkHttpClient client;

    public CustomRestTemplate() {
        this.httpUrls = new HttpUrls();
        this.client = new OkHttpClient();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Map<String,String> login(String username , String password) {
        String url = httpUrls.login();
        logger.info(url);
        String cre = Base64.getEncoder().encodeToString(new StringBuffer(username)
                .append(":")
                .append(password).toString().getBytes());

        Request request = new Request.Builder()
                .url(url)
                .addHeader("authorization","Basic "+ cre)
                .post(RequestBody.create(new byte[]{}))
                .build();

        AsyncTask<Request, String, Response> asyncTasks = new AsyncTasks().execute(request);
        try {
            Response response =  asyncTasks.get();
            logger.info("a_token :" + response.header("a_token") );
            logger.info("r_token :" + response.header("r_token") );
            if(response.code() == 200) {
                Map<String,String> tokens = new HashMap<>();
                tokens.put("a_token",response.header("a_token"));
                tokens.put("r_token",response.header("r_token"));
                return tokens;
            } else {
                return null;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("After done !!!");
        return null;
    }
}
