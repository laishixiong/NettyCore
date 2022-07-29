package com.chat.clinet;

import lombok.Data;
import lombok.Getter;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 客户端用信号量
 * @author laishixiong
 * @time 2022/7/28 19:51
 */
@Data
@Getter
public class GlobalSignal {
	private GlobalSignal() {
	}

	CountDownLatch WAIT_FOR_LOGIN = new CountDownLatch(1);
	AtomicBoolean LOGIN = new AtomicBoolean(false);
	AtomicBoolean EXIT = new AtomicBoolean(false);

	private static GlobalSignal that = new GlobalSignal();

	public static GlobalSignal getSingleton() {
		return that;
	}

}
