package com.github.kklisura.cdtp.protocol.events.dom;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.types.dom.Node;

/**
 * Called when a pseudo element is added to an element.
 */
@Experimental
public class PseudoElementAdded {

	private Integer parentId;

	private Node pseudoElement;

	/**
	 * Pseudo element's parent element id.
	 */
	public Integer getParentId() {
		return parentId;
	}

	/**
	 * Pseudo element's parent element id.
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/**
	 * The added pseudo element.
	 */
	public Node getPseudoElement() {
		return pseudoElement;
	}

	/**
	 * The added pseudo element.
	 */
	public void setPseudoElement(Node pseudoElement) {
		this.pseudoElement = pseudoElement;
	}
}
