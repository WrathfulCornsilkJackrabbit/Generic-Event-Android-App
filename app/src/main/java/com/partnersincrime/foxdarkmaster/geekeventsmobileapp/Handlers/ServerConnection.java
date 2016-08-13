package com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Handlers;

import android.content.Context;
import android.util.Log;

import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Managers.LocaleManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by foxdarkmaster on 12-08-2016.
 */
public class ServerConnection {
    private final static int TIMEOUT= 15000;
    public final static String SERVER_URL = "http://www.mangacomicevent.pt";
    // public final static String SERVER_URL = "http://192.168.1.74:8001";

    private static final String TAG = "ServerConnection";

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    private static String queryBuilder(String token, String method, String source, String param){
        final String formattedDate = dateFormat.format(new Date());
        return method + ":"
                + source + ":"
                + formattedDate
                + (token != null ? ":" + token : "" )
                + "#"
                + (param != null ? param : "");
    }

    private static String urlBuilder(String locale){
        return SERVER_URL +
                "/" +
                locale +
                "/mobile/";
    }

    private static JSONObject post(Context context, final String query){
        String locale = LocaleManager.getCurrentLocale(context);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .build();

        RequestBody requestBody = new FormBody.Builder()
                .add("q", query)
                .build();

        Request request = new Request.Builder()
                .url(urlBuilder(locale))
                .post(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String responseString = response.body().string();
            Log.d(TAG, "EXECUTING REQUEST: \n"
                    + "SERVER URL: " + urlBuilder(locale) + "\n"
                    + "REQUEST: " + query + "\n"
                    + "RESULT: " + responseString);
            return new JSONObject(responseString);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static JSONObject request(Context context, String token, String method, String source, String param){
        String query = queryBuilder(token, method, source, param);
        return post(context, query);
    }
}