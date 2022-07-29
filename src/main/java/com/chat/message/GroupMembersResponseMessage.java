package com.chat.message;

import lombok.Data;
import lombok.ToString;

import java.util.Set;

@Data
@ToString(callSuper = true)
public class GroupMembersResponseMessage extends AbstMessage {

	private Set<String> members;

	public GroupMembersResponseMessage(Set<String> members) {
		this.members = members;
	}

	@Override
	public int getMessageType() {
		return GroupMembersResponseMessage;
	}
}
