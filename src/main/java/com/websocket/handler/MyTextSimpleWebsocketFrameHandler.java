package com.websocket.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * websocket专用处理handler
 *
 * @author laishixiong
 * @time 2022/7/29 15:22
 */
@Slf4j
@ChannelHandler.Sharable
public class MyTextSimpleWebsocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		log.info("收到消息：{}", msg);
		log.info(msg.toString());

		//第一次连接的时候，消息类型是FullHttpRequest，拦截这个请求，把uri自己处理一下
		if (null != msg && msg instanceof FullHttpRequest) {
			FullHttpRequest request = (FullHttpRequest) msg;
			String uri = request.uri();

			Map paramMap = getUrlParams(uri);
			System.out.println("接收到的参数是：" + paramMap);
			//如果url包含参数，需要处理
			if (uri.contains("?")) {
				String newUri = uri.substring(0, uri.indexOf("?"));
				System.out.println(newUri);
				//把uri后面的参数去掉
				request.setUri(newUri);
			}

		} else if (msg instanceof TextWebSocketFrame) {
			//正常的TEXT消息类型
			TextWebSocketFrame frame = (TextWebSocketFrame) msg;
			System.out.println("客户端收到服务器数据：" + frame.text());
			ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器时间：" + new Date()));
		}
		super.channelRead(ctx, msg);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
		log.info("收到消息：{}", msg.text());
		//这里面不能调用super
	}

	// 拿到uri后面的参数
	private static Map getUrlParams(String url) {
		Map<String, String> map = new HashMap<>();
		url = url.replace("?", ";");
		if (!url.contains(";")) {
			return map;
		}
		if (url.split(";").length > 0) {
			String[] arr = url.split(";")[1].split("&");
			for (String s : arr) {
				String key = s.split("=")[0];
				String value = s.split("=")[1];
				map.put(key, value);
			}
			return map;
		} else {
			return map;
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		log.info("exceptionCaught:{}", cause.getMessage());
		super.exceptionCaught(ctx, cause);
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		log.info("handlerAdded:{}", ctx.channel().remoteAddress());
		super.handlerAdded(ctx);
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		log.info("handlerRemoved:{}", ctx.channel().id());
		super.handlerRemoved(ctx);
	}
}
