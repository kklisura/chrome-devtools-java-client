package com.github.kklisura.cdtp.protocol.events.dom;

import com.github.kklisura.cdtp.protocol.types.dom.Node;
import java.util.List;

/**
 * Fired when backend wants to provide client with the missing DOM structure. This happens upon most of the calls requesting node ids.
 */
public class SetChildNodes {

	private Integer parentId;

	private List<Node> nodes;

	/**
	 * Parent node id to populate with children.
	 */
	public Integer getParentId() {
		return parentId;
	}

	/**
	 * Parent node id to populate with children.
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/**
	 * Child nodes array.
	 */
	public List<Node> getNodes() {
		return nodes;
	}

	/**
	 * Child nodes array.
	 */
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
}
