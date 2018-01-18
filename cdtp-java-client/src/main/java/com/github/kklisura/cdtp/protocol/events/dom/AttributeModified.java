package com.github.kklisura.cdtp.protocol.events.dom;

/**
 * Fired when <code>Element</code>'s attribute is modified.
 */
public class AttributeModified {

	private Integer nodeId;

	private String name;

	private String value;

	/**
	 * Id of the node that has changed.
	 */
	public Integer getNodeId() {
		return nodeId;
	}

	/**
	 * Id of the node that has changed.
	 */
	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}

	/**
	 * Attribute name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Attribute name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Attribute value.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Attribute value.
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
