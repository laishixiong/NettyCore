package com.chat.handler.request;

import com.chat.message.GroupJoinRequestMessage;
import com.chat.message.GroupJoinResponseMessage;
import com.chat.session.Group;
import com.chat.session.GroupSessionFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 加入群聊请求消息
 *
 * @param
 * @author laishixiong
 * @time 2022/7/28 21:16
 * @return
 */
@ChannelHandler.Sharable
public class GroupJoinRequestMessageHandler extends SimpleChannelInboundHandler<GroupJoinRequestMessage> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, GroupJoinRequestMessage msg) throws Exception {
		Group group = GroupSessionFactory.getGroupSession().joinMember(msg.getGroupName(), msg.getUsername());
		if (group != null) {
			ctx.writeAndFlush(new GroupJoinResponseMessage(true, msg.getGroupName() + "群加入成功"));
		} else {
			ctx.writeAndFlush(new GroupJoinResponseMessage(true, msg.getGroupName() + "群不存在"));
		}
	}
}
