package com.github.kklisura.cdtp.protocol.types.domsnapshot;

import java.util.List;

public class Snapshot {

	private List<DOMNode> domNodes;

	private List<LayoutTreeNode> layoutTreeNodes;

	private List<ComputedStyle> computedStyles;

	/**
	 * The nodes in the DOM tree. The DOMNode at index 0 corresponds to the root document.
	 */
	public List<DOMNode> getDomNodes() {
		return domNodes;
	}

	/**
	 * The nodes in the DOM tree. The DOMNode at index 0 corresponds to the root document.
	 */
	public void setDomNodes(List<DOMNode> domNodes) {
		this.domNodes = domNodes;
	}

	/**
	 * The nodes in the layout tree.
	 */
	public List<LayoutTreeNode> getLayoutTreeNodes() {
		return layoutTreeNodes;
	}

	/**
	 * The nodes in the layout tree.
	 */
	public void setLayoutTreeNodes(List<LayoutTreeNode> layoutTreeNodes) {
		this.layoutTreeNodes = layoutTreeNodes;
	}

	/**
	 * Whitelisted ComputedStyle properties for each node in the layout tree.
	 */
	public List<ComputedStyle> getComputedStyles() {
		return computedStyles;
	}

	/**
	 * Whitelisted ComputedStyle properties for each node in the layout tree.
	 */
	public void setComputedStyles(List<ComputedStyle> computedStyles) {
		this.computedStyles = computedStyles;
	}
}
