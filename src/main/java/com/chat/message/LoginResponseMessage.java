package com.chat.message;

import lombok.Data;
import lombok.ToString;

/**
 * @author laishixiong
 * @time 2022/7/27 16:15
 */
@Data
@ToString(callSuper = true)
public class LoginResponseMessage extends AbstResponseMessage {

	public LoginResponseMessage(boolean success, String reason) {
		super(success, reason);
	}

	@Override
	public int getMessageType() {
		return AbstMessage.LoginResponseMessage;
	}
}
