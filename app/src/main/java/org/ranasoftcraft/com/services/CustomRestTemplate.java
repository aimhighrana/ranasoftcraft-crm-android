package org.ranasoftcraft.com.services;

import android.os.Build;
import android.support.annotation.RequiresApi;

import org.json.JSONObject;
import org.ranasoftcraft.com.data.model.LoggedInUser;

import java.io.IOException;
import java.util.Base64;
import java.util.logging.Logger;

import okhttp3.Authenticator;
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
    public LoggedInUser login(String username , String password) {
        String url = httpUrls.login();
        String cre = Base64.getEncoder().encodeToString(new StringBuffer(username)
                .append(":")
                .append(password).toString().getBytes());

        Request request = new Request.Builder()
                .url(url)
                .addHeader("authorization","Basic "+ cre)
                .post(RequestBody.create(new byte[]{}))
                .build();
        try {
            Response response = client.newCall(request).execute();
            logger.info(response.header("a_token"));
            logger.info(response.header("r_token"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("After done !!!");
        return null;
    }
}
