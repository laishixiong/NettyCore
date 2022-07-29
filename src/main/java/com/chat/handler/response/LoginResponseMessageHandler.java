package com.chat.handler.response;

import com.chat.message.LoginResponseMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author laishixiong
 * @time 2022/7/28 11:16
 */
@Slf4j
@ChannelHandler.Sharable
public class LoginResponseMessageHandler extends SimpleChannelInboundHandler<LoginResponseMessage> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, LoginResponseMessage msg) throws Exception {
		log.info("收到登入接口回复：{}", msg);

	}
}
