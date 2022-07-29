package com.chat.message;

import lombok.Data;
import lombok.ToString;

/**
 * @author laishixiong
 * @time 2022/7/27 10:56
 */
@Data
@ToString(callSuper = true)
public class ChatResponseMessage extends AbstResponseMessage {

	//定制的返回字段
	private String from;
	private String content;

	public ChatResponseMessage(boolean success, String reason) {
		super(success, reason);
	}

	public ChatResponseMessage(String from, String content) {
		this.from = from;
		this.content = content;
	}

	@Override
	public int getMessageType() {
		return AbstMessage.ChatResponseMessage;
	}
}
