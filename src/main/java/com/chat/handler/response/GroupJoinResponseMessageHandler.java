package com.chat.handler.response;

import com.chat.message.GroupJoinResponseMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 加入群聊请求消息
 *
 * @param
 * @author laishixiong
 * @time 2022/7/28 21:16
 * @return
 */
@Slf4j
@ChannelHandler.Sharable
public class GroupJoinResponseMessageHandler extends SimpleChannelInboundHandler<GroupJoinResponseMessage> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, GroupJoinResponseMessage msg) throws Exception {
		log.info("收到加入群聊回复：{}", msg);
	}
}
