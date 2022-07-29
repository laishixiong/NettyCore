package com.chat.handler.response;

import com.chat.message.GroupCreateResponseMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 群聊消息回复
 *
 * @param
 * @author laishixiong
 * @time 2022/7/28 21:16
 * @return
 */
@Slf4j
@ChannelHandler.Sharable
public class GroupCreateResponseMessageHandler extends SimpleChannelInboundHandler<GroupCreateResponseMessage> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, GroupCreateResponseMessage msg) throws Exception {
		log.info("GroupCreateResponseMessageHandler-channelRead0");
		log.info("收到创建群聊结果：{}", msg);
	}
}
