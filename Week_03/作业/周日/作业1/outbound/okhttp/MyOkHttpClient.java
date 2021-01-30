package io.github.kimmking.gateway.outbound.okhttp;

import okhttp3.*;

import java.io.IOException;


public class MyOkHttpClient {

    private OkHttpClient client = new OkHttpClient();


    public MyOkHttpClient doGetAysn(String url, Callback callback) throws IOException {

        Request request = new Request.Builder()
                .url(url)
                .build();

        this.client.newCall(request).enqueue(callback);

        return this;
    }


    public  Response doGet( String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        return this.client.newCall(request).execute();
    }
}
