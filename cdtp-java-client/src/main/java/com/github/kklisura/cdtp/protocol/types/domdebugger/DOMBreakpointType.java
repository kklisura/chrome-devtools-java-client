package com.github.kklisura.cdtp.protocol.types.domdebugger;

/**
 * DOM breakpoint type.
 */
public enum DOMBreakpointType {

	SUBTREE_MODIFIED("subtree-modified"), ATTRIBUTE_MODIFIED("attribute-modified"), NODE_REMOVED("node-removed");

	final String propertyName;

	DOMBreakpointType(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
