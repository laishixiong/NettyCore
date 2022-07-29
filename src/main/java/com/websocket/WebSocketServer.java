package com.websocket;

import com.websocket.handler.MyTextSimpleWebsocketFrameHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * websocket增强版，可以在uri后面自定参数
 *
 * @author laishixiong
 * @time 2022/7/29 15:14
 */
public class WebSocketServer {
	public static void main(String[] args) {
		WebSocketServer webSocketServer = new WebSocketServer();
		webSocketServer.start();
	}

	public void start() {
		NioEventLoopGroup bossGroup = new NioEventLoopGroup();
		NioEventLoopGroup workerGroup = new NioEventLoopGroup();
		ServerBootstrap serverBootstrap = new ServerBootstrap();
		serverBootstrap.group(bossGroup, workerGroup);
		serverBootstrap.channel(NioServerSocketChannel.class);
		serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(new LoggingHandler());
				//下面是websocket必须要用的几个handler
				ch.pipeline().addLast(new HttpServerCodec());
				ch.pipeline().addLast(new ChunkedWriteHandler());
				ch.pipeline().addLast(new HttpObjectAggregator(8192));
				// 在这个handler里面去解析uri里面的参数，所有改方法需要放到WebSocketServerProtocolHandler前面
				ch.pipeline().addLast(new MyTextSimpleWebsocketFrameHandler());
				ch.pipeline().addLast(new WebSocketServerProtocolHandler("/ws"));
				// 结束
			}
		});
		try {
			ChannelFuture channelFuture = serverBootstrap.bind(9090).sync();
			channelFuture.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}

	}
}
