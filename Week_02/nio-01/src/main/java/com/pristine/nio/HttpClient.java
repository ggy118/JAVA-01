package com.pristine.nio;


import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

/**
 * @ClassName : HttpClient
 * @Description :
 * @Author : Pristine
 * @Date: 2021-01-17 23:02
 */
public class HttpClient {
    public static void main(String[] args) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://127.0.0.1:8801").build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                if (body != null) {
                    System.out.println(String.format("response body is %s", body.string()));
                } else {
                    System.out.println("response body is empty!");
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
