package com.chat.clinet;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * @author laishixiong
 * @time 2022/7/27 13:36
 */
@Slf4j
public class Client {
	private Channel channel;

	public static void main(String[] args) {
		Client client = new Client();
		client.connet();
	}

	public void connet() {
		//bossgroup一般就开一个线程处理
		NioEventLoopGroup bossGroup = new NioEventLoopGroup();
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(bossGroup);
		bootstrap.channel(NioSocketChannel.class);
		bootstrap.handler(new ClientChannelInitializer());
		try {
			//同步等待连接，只有连接成功之后sync才会返回
			ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress("127.0.0.1", 8080)).sync();
			//阻塞获取channel关闭
			channelFuture.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (channel != null) {
				channel.close();
			}
			//关闭
			bossGroup.shutdownGracefully();
		}
	}
}
