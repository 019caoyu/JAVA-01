package io.github.kimmking.gateway.inbound;

import io.github.kimmking.gateway.filter.HeaderHttpRequestFilter;
import io.github.kimmking.gateway.filter.HeaderHttpRequestMyFilter;
import io.github.kimmking.gateway.filter.HttpRequestFilter;
import io.github.kimmking.gateway.outbound.okhttp.OkHttpHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;

import java.util.List;

public class HttpInboundMyHandler extends ChannelInboundHandlerAdapter  {
    private List<String> proxyServer;

    private OkHttpHandler handler;
    private HttpRequestFilter requestFilter = new HeaderHttpRequestMyFilter();

    public HttpInboundMyHandler(List<String> proxyServer) {
        this.proxyServer = proxyServer;
        this.handler = new OkHttpHandler(proxyServer,false);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        ctx.fireExceptionCaught(cause);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            requestFilter.filter(fullRequest,ctx);
            if ("true".equals(fullRequest.headers().get("IsAcsyn", "false" ))){
                handler.setIsAssyn(true);
            }
            handler.handle(fullRequest, ctx);

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
}
