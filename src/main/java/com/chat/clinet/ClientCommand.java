package com.chat.clinet;

import com.chat.message.*;
import io.netty.channel.ChannelHandlerContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author laishixiong
 * @time 2022/7/28 17:04
 */
public class ClientCommand implements Runnable {
	ChannelHandlerContext ctx;

	public ClientCommand(ChannelHandlerContext socketChannel) {
		this.ctx = socketChannel;
	}

	@Override
	public void run() {
		Scanner scanner = new Scanner(System.in);


		System.out.println("请输入用户名:");
		String username = scanner.nextLine();
		if (GlobalSignal.getSingleton().getEXIT().get()) {
			return;
		}
		System.out.println("请输入密码:");
		String password = scanner.nextLine();
		if (GlobalSignal.getSingleton().getEXIT().get()) {
			return;
		}
		// 构造消息对象
		LoginRequestMessage message = new LoginRequestMessage(username, password);
		System.out.println(message);
		// 发送消息
		ctx.writeAndFlush(message);
		System.out.println("等待后续操作...");
		try {
			GlobalSignal.getSingleton().getWAIT_FOR_LOGIN().await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 如果登录失败
		if (!GlobalSignal.getSingleton().getLOGIN().get()) {
			ctx.channel().close();
			return;
		}
		while (true) {
			System.out.println("==================================");
			System.out.println("send [username] [content]");
			System.out.println("gsend [group name] [content]");
			System.out.println("gcreate [group name] [m1,m2,m3...]");
			System.out.println("gmembers [group name]");
			System.out.println("gjoin [group name]");
			System.out.println("gquit [group name]");
			System.out.println("quit");
			System.out.println("==================================");
			String command = null;
			try {
				command = scanner.nextLine();
			} catch (Exception e) {
				break;
			}
			if (GlobalSignal.getSingleton().getEXIT().get()) {
				return;
			}
			String[] s = command.split(" ");
			switch (s[0]) {
				case "send":
					ctx.writeAndFlush(new ChatRequestMessage(username, s[1], s[2]));
					break;
				case "gsend":
					ctx.writeAndFlush(new GroupChatRequestMessage(username, s[1], s[2]));
					break;
				case "gcreate":
					Set<String> set = new HashSet<>(Arrays.asList(s[2].split(",")));
					set.add(username); // 加入自己
					ctx.writeAndFlush(new GroupCreateRequestMessage(s[1], set));
					break;
				case "gmembers":
					ctx.writeAndFlush(new GroupMembersRequestMessage(s[1]));
					break;
				case "gjoin":
					ctx.writeAndFlush(new GroupJoinRequestMessage(username, s[1]));
					break;
				case "gquit":
					ctx.writeAndFlush(new GroupQuitRequestMessage(username, s[1]));
					break;
				case "quit":
					ctx.channel().close();
					return;
			}
		}

	}
}
