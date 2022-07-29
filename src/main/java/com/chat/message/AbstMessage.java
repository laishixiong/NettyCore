package com.chat.message;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author laishixiong
 * @time 2022/7/27 10:44
 */
@Data
@ToString(callSuper = true)
public abstract class AbstMessage implements Serializable {
	static final long serialVersionUID = 1L;

	private int sequenceId;

	private int messageType;

	//子类实现该方法，返回自己的消息类型
	public abstract int getMessageType();

	//消息类型
	public static final int LoginRequestMessage = 0;
	public static final int LoginResponseMessage = 1;
	public static final int ChatRequestMessage = 2;
	public static final int ChatResponseMessage = 3;
	public static final int GroupCreateRequestMessage = 4;
	public static final int GroupCreateResponseMessage = 5;
	public static final int GroupJoinRequestMessage = 6;
	public static final int GroupJoinResponseMessage = 7;
	public static final int GroupQuitRequestMessage = 8;
	public static final int GroupQuitResponseMessage = 9;
	public static final int GroupChatRequestMessage = 10;
	public static final int GroupChatResponseMessage = 11;
	public static final int GroupMembersRequestMessage = 12;
	public static final int GroupMembersResponseMessage = 13;
	public static final int PingMessage = 14;
	public static final int PongMessage = 15;

	/**
	 * 请求类型 byte 值
	 */
	public static final int RPC_MESSAGE_TYPE_REQUEST = 101;
	/**
	 * 响应类型 byte 值
	 */
	public static final int  RPC_MESSAGE_TYPE_RESPONSE = 102;

	//内置消息类型存储容器
	private static final Map<Integer, Class<? extends AbstMessage>> messageClasses = new HashMap<>();

	static {
		messageClasses.put(LoginRequestMessage, LoginRequestMessage.class);
		messageClasses.put(LoginResponseMessage, LoginResponseMessage.class);
		messageClasses.put(ChatRequestMessage, com.chat.message.ChatRequestMessage.class);
		messageClasses.put(ChatResponseMessage, com.chat.message.ChatResponseMessage.class);
		/*messageClasses.put(GroupCreateRequestMessage, GroupCreateRequestMessage.class);
		messageClasses.put(GroupCreateResponseMessage, GroupCreateResponseMessage.class);
		messageClasses.put(GroupJoinRequestMessage, GroupJoinRequestMessage.class);
		messageClasses.put(GroupJoinResponseMessage, GroupJoinResponseMessage.class);
		messageClasses.put(GroupQuitRequestMessage, GroupQuitRequestMessage.class);
		messageClasses.put(GroupQuitResponseMessage, GroupQuitResponseMessage.class);
		messageClasses.put(GroupChatRequestMessage, GroupChatRequestMessage.class);
		messageClasses.put(GroupChatResponseMessage, GroupChatResponseMessage.class);
		messageClasses.put(GroupMembersRequestMessage, GroupMembersRequestMessage.class);
		messageClasses.put(GroupMembersResponseMessage, GroupMembersResponseMessage.class);
		messageClasses.put(RPC_MESSAGE_TYPE_REQUEST, RpcRequestMessage.class);
		messageClasses.put(RPC_MESSAGE_TYPE_RESPONSE, RpcResponseMessage.class);*/
	}

	/**
	 * 根据消息类型字节，获得对应的消息 class
	 * @param messageType 消息类型字节
	 * @return 消息 class
	 */
	public static Class<? extends AbstMessage> getMessageClass(int messageType) {
		return messageClasses.get(messageType);
	}

}
