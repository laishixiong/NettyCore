package com.chat.message;

import lombok.Data;
import lombok.ToString;

/**
 * @author laishixiong
 * @time 2022/7/27 14:39
 */
@Data
@ToString(callSuper = true)
public abstract class AbstResponseMessage extends AbstMessage {
	//抽象通用的返回
	private boolean success;
	// 如果失败，那么这个里面就有原因
	private String reason;

	public AbstResponseMessage() {
	}

	public AbstResponseMessage(boolean success, String reason) {
		this.success = success;
		this.reason = reason;
	}
}
