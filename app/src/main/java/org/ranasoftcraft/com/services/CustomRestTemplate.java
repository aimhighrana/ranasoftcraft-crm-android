package org.ranasoftcraft.com.services;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ranasoftcraft.com.data.model.LoggedInUser;
import org.ranasoftcraft.com.ui.home.Employee;

import java.io.IOException;
import java.time.Duration;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.ByteString;

/**
 * @author sandeep.rana
 */
public class CustomRestTemplate {

    private final Logger logger = Logger.getLogger(CustomRestTemplate.class.getName());
    private HttpUrls httpUrls;
    private OkHttpClient client;

    private String a_token = null;

    @androidx.annotation.RequiresApi(api = Build.VERSION_CODES.O)
    public CustomRestTemplate() {
        this.httpUrls = new HttpUrls();
        this.client = new OkHttpClient().newBuilder()
//                .addInterceptor(new HttpInterceptor())
                .connectTimeout(Duration.ofMinutes(30))
                .build();
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

        AsyncTask<Request, String, Response> asyncTasks = new AsyncTasks(client).execute(request);
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

    public boolean createUpdateEmployee(Employee employee, String _token) {
        String url = httpUrls.createUpdateEmployee();
        logger.info(url);
        Request request = null;
        try {
            RequestBody body = RequestBody.create(
                    MediaType.parse("application/json"), new ObjectMapper().writeValueAsString(employee));

            request = new Request.Builder()
                    .url(url)
                    .addHeader("Authorization","Bearer "+ _token)
                    .post(body)
                    .build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        logger.info(request.toString());

        AsyncTask<Request, String, Response> asyncTasks = new AsyncTasks(client).execute(request);
        try {
            Response response =  asyncTasks.get();
            logger.info("Body  :" + response.body().toString());
            return response.isSuccessful();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("After done !!!");
        return Boolean.FALSE;
    }


    public JSONArray getEmployeeList(String _token) {
        String url = httpUrls.getEmployeeList();
        logger.info(url);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization","Bearer "+ _token)
                .build();
        logger.info(request.toString());

        AsyncTask response = new OkHttpHelper(client).execute(request);
        try {
            return new JSONArray(response.get().toString());
        } catch (ExecutionException | InterruptedException | JSONException e){
            e.printStackTrace();
        }
        logger.info("After done !!!");
        return null;
    }
}
