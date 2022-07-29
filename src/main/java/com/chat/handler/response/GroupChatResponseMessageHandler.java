package com.chat.handler.response;


import com.chat.message.GroupChatResponseMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 群聊，发送聊天消息
 *
 * @param
 * @author laishixiong
 * @time 2022/7/28 21:15
 * @return
 */
@Slf4j
@ChannelHandler.Sharable
public class GroupChatResponseMessageHandler extends SimpleChannelInboundHandler<GroupChatResponseMessage> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, GroupChatResponseMessage msg) throws Exception {
		log.info("收到群聊");
		log.info("消息来自：{}", msg.getFrom());
		log.info("消息内容：{}", msg.getContent());
	}
}
