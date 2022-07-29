package com.chat.service.user;

/**
 * @author laishixiong
 * @time 2022/7/28 11:17
 */
public abstract class UserServiceFactory {
	private static UserService userService = new UserServiceMemoryImpl();

	public static UserService getUserService() {
		return userService;
	}
}
