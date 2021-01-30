package io.github.kimmking.gateway.outbound.okhttp;


import io.github.kimmking.gateway.router.HttpEndpointRouter;
import io.github.kimmking.gateway.router.RandomHttpEndpointRouter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class OkHttpHandler {

    private List<String> backendUrls;
    private boolean isAcsyn = false;
    private HttpEndpointRouter router = new RandomHttpEndpointRouter();
    private MyOkHttpClient clientMy = new MyOkHttpClient();


    public OkHttpHandler(List<String> backendurls, boolean isAcsyn){
         this.backendUrls = backendurls.stream().map(this::formatUrl).collect(Collectors.toList());
         this.isAcsyn = isAcsyn;
    }

    public void setIsAssyn(boolean isAcsyn){
        this.isAcsyn = isAcsyn;
    }

    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx) throws Exception {
        String backendUrl = router.route(this.backendUrls);
        final String url = backendUrl + fullRequest.uri();
        if (isAcsyn){
            clientMy.doGetAysn(backendUrl, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        handleResponse(fullRequest,ctx,response);
                    } catch (Exception e) {
                        IOException ex = new IOException(e.getMessage());
                        ex.addSuppressed(e);
                        throw ex;
                    }
                }
            });
        }else{
            Response response = clientMy.doGet(backendUrl);
            handleResponse(fullRequest,ctx,response);
        }

    }

    private String formatUrl(String backend) {
        return backend.endsWith("/")?backend.substring(0,backend.length()-1):backend;
    }

    private void handleResponse(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, final Response endpointResponse) throws Exception {
        FullHttpResponse response = null;
        try {

            byte[] body = endpointResponse.body().bytes();


            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));

            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", Integer.parseInt(endpointResponse.headers().get("Content-Length")));


        } catch (Exception e) {
            e.printStackTrace();
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
            exceptionCaught(ctx, e);
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    ctx.write(response);
                }
            }
            ctx.flush();
        }

    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
