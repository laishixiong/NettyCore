package com.chat.protocol;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 沾包处理
 *
 * @author laishixiong
 * @time 2022/7/28 9:37
 */
public class ProcotolFrameDecoder extends LengthFieldBasedFrameDecoder {

	public ProcotolFrameDecoder() {
		//内置消息编码规则
		//一帧1024字节
		this(1024, 12, 4, 0, 0);
	}

	public ProcotolFrameDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip) {
		super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
	}
}
