package com.github.kklisura.cdtp.protocol.events.dom;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.types.dom.BackendNode;
import java.util.List;

/**
 * Called when distrubution is changed.
 */
@Experimental
public class DistributedNodesUpdated {

	private Integer insertionPointId;

	private List<BackendNode> distributedNodes;

	/**
	 * Insertion point where distrubuted nodes were updated.
	 */
	public Integer getInsertionPointId() {
		return insertionPointId;
	}

	/**
	 * Insertion point where distrubuted nodes were updated.
	 */
	public void setInsertionPointId(Integer insertionPointId) {
		this.insertionPointId = insertionPointId;
	}

	/**
	 * Distributed nodes for given insertion point.
	 */
	public List<BackendNode> getDistributedNodes() {
		return distributedNodes;
	}

	/**
	 * Distributed nodes for given insertion point.
	 */
	public void setDistributedNodes(List<BackendNode> distributedNodes) {
		this.distributedNodes = distributedNodes;
	}
}
