package com.chat.handler.request;

import com.chat.message.LoginRequestMessage;
import com.chat.message.LoginResponseMessage;
import com.chat.service.user.UserServiceFactory;
import com.chat.session.SessionFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author laishixiong
 * @time 2022/7/28 11:16
 */
@Slf4j
@ChannelHandler.Sharable
public class LoginRequestMessageHandler extends SimpleChannelInboundHandler<LoginRequestMessage> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, LoginRequestMessage msg) throws Exception {
		log.info("LoginRequestMessageHandler-channelRead0");
		String username = msg.getUsername();
		String password = msg.getPassword();
		boolean login = UserServiceFactory.getUserService().login(username, password);
		LoginResponseMessage message;
		if (login) {
			SessionFactory.getSession().bind(ctx.channel(), username);
			message = new LoginResponseMessage(true, "登录成功");
		} else {
			message = new LoginResponseMessage(false, "用户名或密码不正确");
		}
		ctx.writeAndFlush(message);
	}
}
