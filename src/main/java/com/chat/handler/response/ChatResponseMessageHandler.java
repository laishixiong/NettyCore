package com.chat.handler.response;

import com.chat.message.ChatResponseMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 单聊，回复聊天消息
 * @author laishixiong
 * @time 2022/7/28 11:16
 *
 */
@Slf4j
@ChannelHandler.Sharable
public class ChatResponseMessageHandler extends SimpleChannelInboundHandler<ChatResponseMessage> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ChatResponseMessage msg) throws Exception {
		log.info("ChatResponseMessageHandler-channelRead0");
		log.info("消息来自：{}", msg.getFrom());
		log.info("消息内容：{}", msg.getContent());
	}
}
