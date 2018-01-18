package com.github.kklisura.cdtp.protocol.events.dom;

/**
 * Fired when <code>Container</code>'s child node count has changed.
 */
public class ChildNodeCountUpdated {

	private Integer nodeId;

	private Integer childNodeCount;

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
	 * New node count.
	 */
	public Integer getChildNodeCount() {
		return childNodeCount;
	}

	/**
	 * New node count.
	 */
	public void setChildNodeCount(Integer childNodeCount) {
		this.childNodeCount = childNodeCount;
	}
}
