package java0.nio01;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class HttpClient01 {

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String url = "http://localhost:8801/";

        doGetAysn(client, url, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                System.out.println("doGetAysn callback, the response:"+  response.body().string());
            }
        });

        System.out.println("doGet call, the response:"+ doGet(client, url).body().string());
    }

    private static void doGetAysn(OkHttpClient client,
                                  String url, Callback callback) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(callback);

    }

    private static Response doGet(OkHttpClient client,
                              String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        return client.newCall(request).execute();
    }
}
