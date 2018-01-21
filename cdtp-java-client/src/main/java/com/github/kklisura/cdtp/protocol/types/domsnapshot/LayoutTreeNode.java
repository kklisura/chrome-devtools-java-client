package com.github.kklisura.cdtp.protocol.types.domsnapshot;

import com.github.kklisura.cdtp.protocol.annotations.Optional;
import com.github.kklisura.cdtp.protocol.types.css.InlineTextBox;
import com.github.kklisura.cdtp.protocol.types.dom.Rect;
import java.util.List;

/**
 * Details of an element in the DOM tree with a LayoutObject.
 */
public class LayoutTreeNode {

	private Integer domNodeIndex;

	private Rect boundingBox;

	@Optional
	private String layoutText;

	@Optional
	private List<InlineTextBox> inlineTextNodes;

	@Optional
	private Integer styleIndex;

	/**
	 * The index of the related DOM node in the <code>domNodes</code> array returned by <code>getSnapshot</code>.
	 */
	public Integer getDomNodeIndex() {
		return domNodeIndex;
	}

	/**
	 * The index of the related DOM node in the <code>domNodes</code> array returned by <code>getSnapshot</code>.
	 */
	public void setDomNodeIndex(Integer domNodeIndex) {
		this.domNodeIndex = domNodeIndex;
	}

	/**
	 * The absolute position bounding box.
	 */
	public Rect getBoundingBox() {
		return boundingBox;
	}

	/**
	 * The absolute position bounding box.
	 */
	public void setBoundingBox(Rect boundingBox) {
		this.boundingBox = boundingBox;
	}

	/**
	 * Contents of the LayoutText, if any.
	 */
	public String getLayoutText() {
		return layoutText;
	}

	/**
	 * Contents of the LayoutText, if any.
	 */
	public void setLayoutText(String layoutText) {
		this.layoutText = layoutText;
	}

	/**
	 * The post-layout inline text nodes, if any.
	 */
	public List<InlineTextBox> getInlineTextNodes() {
		return inlineTextNodes;
	}

	/**
	 * The post-layout inline text nodes, if any.
	 */
	public void setInlineTextNodes(List<InlineTextBox> inlineTextNodes) {
		this.inlineTextNodes = inlineTextNodes;
	}

	/**
	 * Index into the <code>computedStyles</code> array returned by <code>getSnapshot</code>.
	 */
	public Integer getStyleIndex() {
		return styleIndex;
	}

	/**
	 * Index into the <code>computedStyles</code> array returned by <code>getSnapshot</code>.
	 */
	public void setStyleIndex(Integer styleIndex) {
		this.styleIndex = styleIndex;
	}
}
