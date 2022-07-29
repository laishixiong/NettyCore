package com.chat.service;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author laishixiong
 * @time 2022/7/27 10:57
 * 服务端启动类
 */
@Slf4j
public class ServiceBoot {
	public static void main(String[] args) {
		ServiceBoot serviceBoot = new ServiceBoot();
		serviceBoot.start();
	}

	public void start() {
		NioEventLoopGroup bossGroup = new NioEventLoopGroup();
		//指定work线程池里面有三个线程
		NioEventLoopGroup workerGroup = new NioEventLoopGroup(3);
		ServerBootstrap serverBootstrap = new ServerBootstrap();
		serverBootstrap.group(bossGroup, workerGroup);
		serverBootstrap.channel(NioServerSocketChannel.class);
		serverBootstrap.childHandler(new ServiceChannelInitializer());
		//绑定端口
		try {
			ChannelFuture channelFuture = serverBootstrap.bind(8080).sync();
			channelFuture.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
