package com.chat.clinet;

import com.chat.handler.*;
import com.chat.handler.request.PingRequestMessageHandler;
import com.chat.handler.response.*;
import com.chat.protocol.MessageCodec;
import com.chat.protocol.ProcotolFrameDecoder;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author laishixiong
 * @time 2022/7/28 16:04
 */
@Slf4j
public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		// 添加各种handler，每个handler只会处理自己感兴趣的消息
		MessageCodec messageCodec = new MessageCodec();
		LoggingHandler loggingHandler = new LoggingHandler(LogLevel.DEBUG);
		// 处理指令用的handler
		UIControlHandler uiControlHandler = new UIControlHandler();
		// 各种回复消息处理用的handler
		GroupCreateResponseMessageHandler groupCreateResponseMessageHandler = new GroupCreateResponseMessageHandler();
		GroupChatResponseMessageHandler groupChatResponseMessageHandler = new GroupChatResponseMessageHandler();
		GroupJoinResponseMessageHandler groupJoinResponseMessageHandler = new GroupJoinResponseMessageHandler();
		LoginResponseMessageHandler loginResponseMessageHandler = new LoginResponseMessageHandler();
		ChatResponseMessageHandler chatResponseMessageHandler = new ChatResponseMessageHandler();
		GroupMembersResponseMessageHandler groupMembersResponseMessageHandler = new GroupMembersResponseMessageHandler();
		// 处理沾包
		ProcotolFrameDecoder procotolFrameDecoder = new ProcotolFrameDecoder();
		// 做测试用的
		TestHandler testHandler = new TestHandler();
		ch.pipeline().addLast(loggingHandler);
		ch.pipeline().addLast(procotolFrameDecoder);
		ch.pipeline().addLast(messageCodec);
		//出栈处理器
		ch.pipeline().addLast(uiControlHandler);
		//自定义简单内容打印
		ch.pipeline().addLast(testHandler);
		ch.pipeline().addLast(loginResponseMessageHandler);
		ch.pipeline().addLast(chatResponseMessageHandler);
		ch.pipeline().addLast(groupCreateResponseMessageHandler);
		ch.pipeline().addLast(groupChatResponseMessageHandler);
		ch.pipeline().addLast(groupJoinResponseMessageHandler);
		ch.pipeline().addLast(groupMembersResponseMessageHandler);
		// 用来判断是不是 读空闲时间过长，或 写空闲时间过长
		// 3s 内如果没有向服务器写数据，会触发一个 IdleState#WRITER_IDLE 事件
		ch.pipeline().addLast(new IdleStateHandler(0, 3, 0));
		ch.pipeline().addLast(new PingRequestMessageHandler());
	}
}
