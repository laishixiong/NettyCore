package com.chat.service;

import com.chat.handler.*;
import com.chat.handler.request.*;
import com.chat.message.QuitHandler;
import com.chat.protocol.MessageCodec;
import com.chat.protocol.ProcotolFrameDecoder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author laishixiong
 * @time 2022/7/28 15:46
 */
@Slf4j
public class ServiceChannelInitializer extends ChannelInitializer {

	@Override
	protected void initChannel(Channel channel) throws Exception {
		//日志
		LoggingHandler loggingHandler = new LoggingHandler(LogLevel.DEBUG);
		//消息编解码器
		MessageCodec messageCodec = new MessageCodec();
		//登入消息
		LoginRequestMessageHandler loginRequestMessageHandler = new LoginRequestMessageHandler();
		//单人聊天消息
		ChatRequestMessageHandler chatRequestMessageHandler = new ChatRequestMessageHandler();
		// 创建群组
		GroupCreateRequestMessageHandler groupCreateRequestMessageHandler = new GroupCreateRequestMessageHandler();
		// 加入群组
		GroupJoinRequestMessageHandler groupJoinRequestMessageHandler = new GroupJoinRequestMessageHandler();
		// 查询群里面的人
		GroupMembersRequestMessageHandler groupMembersRequestMessageHandler = new GroupMembersRequestMessageHandler();
		// 退出群
		GroupQuitRequestMessageHandler groupQuitRequestMessageHandler = new GroupQuitRequestMessageHandler();
		// 群消息
		GroupChatRequestMessageHandler groupChatRequestMessageHandler = new GroupChatRequestMessageHandler();
		// 退出
		QuitHandler quitHandler = new QuitHandler();

		//测试用
		TestHandler testHandler = new TestHandler();

		//关键handler顺非要小心放置
		//channel.pipeline().addLast(loggingHandler);
		channel.pipeline().addLast(new ProcotolFrameDecoder());
		channel.pipeline().addLast(testHandler);
		channel.pipeline().addLast(new MessageCodec());
		channel.pipeline().addLast(loginRequestMessageHandler);
		channel.pipeline().addLast(chatRequestMessageHandler);
		channel.pipeline().addLast(groupCreateRequestMessageHandler);
		channel.pipeline().addLast(groupJoinRequestMessageHandler);
		channel.pipeline().addLast(groupMembersRequestMessageHandler);
		channel.pipeline().addLast(groupQuitRequestMessageHandler);
		channel.pipeline().addLast(groupChatRequestMessageHandler);
		channel.pipeline().addLast(quitHandler);
		// 用来判断是不是 读空闲时间过长，或 写空闲时间过长
		// 5s 内如果没有收到 channel 的数据，会触发一个 IdleState#READER_IDLE 事件
		channel.pipeline().addLast(new IdleStateHandler(5, 0, 0));
		channel.pipeline().addLast(new PingRequestMessageHandler());
	}


}
