package com.github.kklisura.cdtp.protocol.types.domdebugger;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DOM breakpoint type.
 */
public enum DOMBreakpointType {

	@JsonProperty("subtree-modified")
	SUBTREE_MODIFIED, @JsonProperty("attribute-modified")
	ATTRIBUTE_MODIFIED, @JsonProperty("node-removed")
	NODE_REMOVED
}
