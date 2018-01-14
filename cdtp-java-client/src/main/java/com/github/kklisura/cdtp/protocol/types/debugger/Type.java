package com.github.kklisura.cdtp.protocol.types.debugger;

public enum Type {

	DEBUGGER_STATEMENT("debuggerStatement"), CALL("call"), RETURN("return");

	final String propertyName;

	Type(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
