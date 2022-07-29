package com.chat.message;

import lombok.Data;
import lombok.ToString;

/**
 * @author laishixiong
 * @time 2022/7/27 10:50
 */
@Data
@ToString(callSuper = true)
public class ChatRequestMessage extends AbstMessage {

	private String content;
	private String to;
	private String from;

	public ChatRequestMessage() {
	}

	public ChatRequestMessage(String from, String to, String content) {
		this.from = from;
		this.to = to;
		this.content = content;
	}

	@Override
	public int getMessageType() {
		return AbstMessage.ChatRequestMessage;
	}
}
