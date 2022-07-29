package com.chat.handler.request;

import com.chat.message.PingMessage;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author laishixiong
 * @time 2022/7/29 17:15
 */
@Slf4j
public class PingRequestMessageHandler extends ChannelDuplexHandler {

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		log.info("收到心跳:{}",ctx.channel().remoteAddress());
		IdleStateEvent event = (IdleStateEvent) evt;
		// 触发了写空闲事件
		if (event.state() == IdleState.WRITER_IDLE) {
			//log.debug("3s 没有写数据了，发送一个心跳包");
			ctx.writeAndFlush(new PingMessage());
		} else if (event.state() == IdleState.READER_IDLE) {
			// 触发了读空闲事件
			log.debug("已经 5s 没有读到数据了");
			ctx.channel().close();
		}
		super.userEventTriggered(ctx, evt);
	}
}
