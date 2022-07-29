package com.chat.handler;

import com.chat.clinet.ClientCommand;
import com.chat.clinet.GlobalSignal;
import com.chat.message.LoginResponseMessage;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @author laishixiong
 * @time 2022/7/28 19:25
 * 处理客户端的交互
 */
@Slf4j
public class UIControlHandler extends ChannelDuplexHandler {
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		log.info("UIControlHandler-channelActive");
		//把ChannelHandlerContext放到一个指令接受线程处理
		ClientCommand clientCommand = new ClientCommand(ctx);
		//新开线程处理客户端操作
		new Thread(clientCommand).start();
		super.channelActive(ctx);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		log.info("UIControlHandler-channelRead");
		if ((msg instanceof LoginResponseMessage)) {
			LoginResponseMessage response = (LoginResponseMessage) msg;
			if (response.isSuccess()) {
				// 如果登录成功,默认是false
				GlobalSignal.getSingleton().getLOGIN().set(true);
			}
			// 唤醒 system in 线程
			GlobalSignal.getSingleton().getWAIT_FOR_LOGIN().countDown();
		}
		super.channelRead(ctx, msg);
	}

	// 在连接断开时触发
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		log.debug("连接已经断开，按任意键退出..");
		GlobalSignal.getSingleton().getEXIT().set(true);
	}

	// 在出现异常时触发
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		log.debug("连接已经断开，按任意键退出..{}", cause.getMessage());
		GlobalSignal.getSingleton().getEXIT().set(true);
	}

}
