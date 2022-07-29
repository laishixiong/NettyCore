package com.chat.handler.response;

import com.chat.message.GroupMembersResponseMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ChannelHandler.Sharable
public class GroupMembersResponseMessageHandler extends SimpleChannelInboundHandler<GroupMembersResponseMessage> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, GroupMembersResponseMessage msg) throws Exception {
		log.info("收到查询群组人员：{}", msg.getMembers());
	}
}
