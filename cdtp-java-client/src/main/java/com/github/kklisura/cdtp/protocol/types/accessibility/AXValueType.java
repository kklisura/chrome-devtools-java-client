package com.github.kklisura.cdtp.protocol.types.accessibility;

/**
 * Enum of possible property types.
 */
public enum AXValueType {

	BOOLEAN("boolean"), TRISTATE("tristate"), BOOLEAN_OR_UNDEFINED("booleanOrUndefined"), IDREF("idref"), IDREF_LIST("idrefList"), INTEGER("integer"), NODE("node"), NODE_LIST("nodeList"), NUMBER("number"), STRING("string"), COMPUTED_STRING("computedString"), TOKEN("token"), TOKEN_LIST("tokenList"), DOM_RELATION("domRelation"), ROLE("role"), INTERNAL_ROLE("internalRole"), VALUE_UNDEFINED("valueUndefined");

	final String propertyName;

	AXValueType(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
