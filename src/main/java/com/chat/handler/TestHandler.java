package com.chat.handler;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;

/**
 * @author laishixiong
 * @time 2022/7/28 15:57
 * 出入站都在这个里面
 */
@Slf4j
public class TestHandler extends ChannelDuplexHandler {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		log.info("TestHandler-channelActive");
		super.channelActive(ctx);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		log.info("TestHandler-channelRead");
		super.channelRead(ctx, msg);
	}

	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		log.info("TestHandler-write");
		super.write(ctx, msg, promise);
	}
}
