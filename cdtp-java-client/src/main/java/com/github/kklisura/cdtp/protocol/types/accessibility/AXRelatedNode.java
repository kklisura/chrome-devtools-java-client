package com.github.kklisura.cdtp.protocol.types.accessibility;

import com.github.kklisura.cdtp.protocol.annotations.Optional;

public class AXRelatedNode {

	private Integer backendDOMNodeId;

	@Optional
	private String idref;

	@Optional
	private String text;

	/**
	 * The BackendNodeId of the related DOM node.
	 */
	public Integer getBackendDOMNodeId() {
		return backendDOMNodeId;
	}

	/**
	 * The BackendNodeId of the related DOM node.
	 */
	public void setBackendDOMNodeId(Integer backendDOMNodeId) {
		this.backendDOMNodeId = backendDOMNodeId;
	}

	/**
	 * The IDRef value provided, if any.
	 */
	public String getIdref() {
		return idref;
	}

	/**
	 * The IDRef value provided, if any.
	 */
	public void setIdref(String idref) {
		this.idref = idref;
	}

	/**
	 * The text alternative of this node in the current context.
	 */
	public String getText() {
		return text;
	}

	/**
	 * The text alternative of this node in the current context.
	 */
	public void setText(String text) {
		this.text = text;
	}
}
