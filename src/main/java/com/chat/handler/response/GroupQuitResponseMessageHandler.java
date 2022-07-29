package com.chat.handler.response;

import com.chat.message.GroupQuitResponseMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ChannelHandler.Sharable
public class GroupQuitResponseMessageHandler extends SimpleChannelInboundHandler<GroupQuitResponseMessage> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, GroupQuitResponseMessage msg) throws Exception {
		log.info("收到退出群组消息：{}", msg.getReason());
	}
}
