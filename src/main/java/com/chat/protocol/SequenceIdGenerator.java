package com.chat.protocol;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author laishixiong
 * @time 2022/7/28 9:46
 */
public abstract class SequenceIdGenerator {
	private static final AtomicInteger id = new AtomicInteger();

	public static int nextId() {
		return id.incrementAndGet();
	}
}
