package com.github.kklisura.cdtp.protocol.events.dom;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import java.util.List;

/**
 * Fired when <code>Element</code>'s inline style is modified via a CSS property modification.
 */
@Experimental
public class InlineStyleInvalidated {

	private List<Integer> nodeIds;

	/**
	 * Ids of the nodes for which the inline styles have been invalidated.
	 */
	public List<Integer> getNodeIds() {
		return nodeIds;
	}

	/**
	 * Ids of the nodes for which the inline styles have been invalidated.
	 */
	public void setNodeIds(List<Integer> nodeIds) {
		this.nodeIds = nodeIds;
	}
}
