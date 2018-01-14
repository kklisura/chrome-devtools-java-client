package com.github.kklisura.cdtp.protocol.events.overlay;

/**
 * Fired when the node should be inspected. This happens after call to <code>setInspectMode</code> or when user manually inspects an element.
 */
public class InspectNodeRequested {

	private Integer backendNodeId;

	/**
	 * Id of the node to inspect.
	 */
	public Integer getBackendNodeId() {
		return backendNodeId;
	}

	/**
	 * Id of the node to inspect.
	 */
	public void setBackendNodeId(Integer backendNodeId) {
		this.backendNodeId = backendNodeId;
	}
}
