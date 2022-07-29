package com.chat.protocol;

import com.chat.message.AbstMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * @author laishixiong
 * @time 2022/7/27 17:04
 */
@Slf4j
//@ChannelHandler.Sharable  //该类线程安全
public class MessageCodec extends ByteToMessageCodec<AbstMessage> {

	//编码
	@Override
	public void encode(ChannelHandlerContext channelHandlerContext, AbstMessage abstMessage, ByteBuf byteBuf) throws Exception {
		log.info("MessageCodec-encode");
		//魔数
		byteBuf.writeBytes(new byte[]{1, 2, 3, 4});
		//字节版本
		byteBuf.writeByte(1);
		//字节序列化方式 jdk0 json1
		byteBuf.writeByte(0);
		//指令类型
		byteBuf.writeByte(abstMessage.getMessageType());
		//指令序列号
		byteBuf.writeInt(abstMessage.getSequenceId());
		//占位符号，保证头部是8的倍数
		byteBuf.writeByte(0xff);
		//jdk对象序列化
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
		objectOutputStream.writeObject(abstMessage);
		byte[] bytes = byteArrayOutputStream.toByteArray();
		//消息内容长度
		byteBuf.writeInt(bytes.length);
		//消息真实内容
		byteBuf.writeBytes(bytes);


	}

	//解码
	@Override
	public void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
		log.info("MessageCodec-decode");
		int magic = byteBuf.readInt();
		byte version = byteBuf.readByte();
		byte serializerType = byteBuf.readByte();
		byte messageType = byteBuf.readByte();
		int sequenceId = byteBuf.readInt();
		byteBuf.readByte();
		int length = byteBuf.readInt();
		byte[] contentByte = new byte[length];
		byteBuf.readBytes(contentByte, 0, length);
		ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(contentByte));
		Class<? extends AbstMessage> messageClass = AbstMessage.getMessageClass(messageType);
		AbstMessage abstMessage = (AbstMessage) objectInputStream.readObject();
		list.add(abstMessage);
	}
}
