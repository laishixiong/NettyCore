package com.chat.message;

import lombok.Data;
import lombok.ToString;

/**
 * @author laishixiong
 * @time 2022/7/27 16:00
 */
@Data
@ToString(callSuper = true)
public class LoginRequestMessage extends AbstMessage {

	private String username;
	private String password;

	public LoginRequestMessage() {
	}

	public LoginRequestMessage(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public int getMessageType() {
		return AbstMessage.LoginRequestMessage;
	}
}
