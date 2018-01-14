package com.github.kklisura.cdtp.protocol.events.runtime;

/**
 * Type of the call.
 */
public enum Type {

	LOG("log"), DEBUG("debug"), INFO("info"), ERROR("error"), WARNING("warning"), DIR("dir"), DIRXML("dirxml"), TABLE("table"), TRACE("trace"), CLEAR("clear"), START_GROUP("startGroup"), START_GROUP_COLLAPSED("startGroupCollapsed"), END_GROUP("endGroup"), ASSERT("assert"), PROFILE("profile"), PROFILE_END("profileEnd"), COUNT("count"), TIME_END("timeEnd");

	final String propertyName;

	Type(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
