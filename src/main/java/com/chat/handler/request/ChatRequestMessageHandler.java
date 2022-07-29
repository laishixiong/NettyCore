package com.chat.handler.request;

import com.chat.message.ChatRequestMessage;
import com.chat.message.ChatResponseMessage;
import com.chat.session.SessionFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 单聊，发送聊天消息
 * @author laishixiong
 * @time 2022/7/28 11:34
 *
 */
@Slf4j
@ChannelHandler.Sharable
public class ChatRequestMessageHandler extends SimpleChannelInboundHandler<ChatRequestMessage> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ChatRequestMessage msg) throws Exception {
		log.info("ChatRequestMessageHandler channelRead0");
		String to = msg.getTo();
		Channel channel = SessionFactory.getSession().getChannel(to);
		// 在线
		if (channel != null) {
			channel.writeAndFlush(new ChatResponseMessage(msg.getFrom(), msg.getContent()));
		}
		// 不在线
		else {
			ctx.writeAndFlush(new ChatResponseMessage(false, "对方用户不存在或者不在线"));
		}
	}
}
