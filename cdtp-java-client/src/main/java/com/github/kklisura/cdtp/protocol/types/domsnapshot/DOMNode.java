package com.github.kklisura.cdtp.protocol.types.domsnapshot;

import com.github.kklisura.cdtp.protocol.annotations.Optional;
import com.github.kklisura.cdtp.protocol.types.dom.PseudoType;
import java.util.List;

/**
 * A Node in the DOM tree.
 */
public class DOMNode {

	private Integer nodeType;

	private String nodeName;

	private String nodeValue;

	@Optional
	private String textValue;

	@Optional
	private String inputValue;

	@Optional
	private Boolean inputChecked;

	@Optional
	private Boolean optionSelected;

	private Integer backendNodeId;

	@Optional
	private List<Integer> childNodeIndexes;

	@Optional
	private List<NameValue> attributes;

	@Optional
	private List<Integer> pseudoElementIndexes;

	@Optional
	private Integer layoutNodeIndex;

	@Optional
	private String documentURL;

	@Optional
	private String baseURL;

	@Optional
	private String contentLanguage;

	@Optional
	private String documentEncoding;

	@Optional
	private String publicId;

	@Optional
	private String systemId;

	@Optional
	private String frameId;

	@Optional
	private Integer contentDocumentIndex;

	@Optional
	private Integer importedDocumentIndex;

	@Optional
	private Integer templateContentIndex;

	@Optional
	private PseudoType pseudoType;

	@Optional
	private Boolean isClickable;

	/**
	 * <code>Node</code>'s nodeType.
	 */
	public Integer getNodeType() {
		return nodeType;
	}

	/**
	 * <code>Node</code>'s nodeType.
	 */
	public void setNodeType(Integer nodeType) {
		this.nodeType = nodeType;
	}

	/**
	 * <code>Node</code>'s nodeName.
	 */
	public String getNodeName() {
		return nodeName;
	}

	/**
	 * <code>Node</code>'s nodeName.
	 */
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	/**
	 * <code>Node</code>'s nodeValue.
	 */
	public String getNodeValue() {
		return nodeValue;
	}

	/**
	 * <code>Node</code>'s nodeValue.
	 */
	public void setNodeValue(String nodeValue) {
		this.nodeValue = nodeValue;
	}

	/**
	 * Only set for textarea elements, contains the text value.
	 */
	public String getTextValue() {
		return textValue;
	}

	/**
	 * Only set for textarea elements, contains the text value.
	 */
	public void setTextValue(String textValue) {
		this.textValue = textValue;
	}

	/**
	 * Only set for input elements, contains the input's associated text value.
	 */
	public String getInputValue() {
		return inputValue;
	}

	/**
	 * Only set for input elements, contains the input's associated text value.
	 */
	public void setInputValue(String inputValue) {
		this.inputValue = inputValue;
	}

	/**
	 * Only set for radio and checkbox input elements, indicates if the element has been checked
	 */
	public Boolean getInputChecked() {
		return inputChecked;
	}

	/**
	 * Only set for radio and checkbox input elements, indicates if the element has been checked
	 */
	public void setInputChecked(Boolean inputChecked) {
		this.inputChecked = inputChecked;
	}

	/**
	 * Only set for option elements, indicates if the element has been selected
	 */
	public Boolean getOptionSelected() {
		return optionSelected;
	}

	/**
	 * Only set for option elements, indicates if the element has been selected
	 */
	public void setOptionSelected(Boolean optionSelected) {
		this.optionSelected = optionSelected;
	}

	/**
	 * <code>Node</code>'s id, corresponds to DOM.Node.backendNodeId.
	 */
	public Integer getBackendNodeId() {
		return backendNodeId;
	}

	/**
	 * <code>Node</code>'s id, corresponds to DOM.Node.backendNodeId.
	 */
	public void setBackendNodeId(Integer backendNodeId) {
		this.backendNodeId = backendNodeId;
	}

	/**
	 * The indexes of the node's child nodes in the <code>domNodes</code> array returned by <code>getSnapshot</code>, if any.
	 */
	public List<Integer> getChildNodeIndexes() {
		return childNodeIndexes;
	}

	/**
	 * The indexes of the node's child nodes in the <code>domNodes</code> array returned by <code>getSnapshot</code>, if any.
	 */
	public void setChildNodeIndexes(List<Integer> childNodeIndexes) {
		this.childNodeIndexes = childNodeIndexes;
	}

	/**
	 * Attributes of an <code>Element</code> node.
	 */
	public List<NameValue> getAttributes() {
		return attributes;
	}

	/**
	 * Attributes of an <code>Element</code> node.
	 */
	public void setAttributes(List<NameValue> attributes) {
		this.attributes = attributes;
	}

	/**
	 * Indexes of pseudo elements associated with this node in the <code>domNodes</code> array returned by <code>getSnapshot</code>, if any.
	 */
	public List<Integer> getPseudoElementIndexes() {
		return pseudoElementIndexes;
	}

	/**
	 * Indexes of pseudo elements associated with this node in the <code>domNodes</code> array returned by <code>getSnapshot</code>, if any.
	 */
	public void setPseudoElementIndexes(List<Integer> pseudoElementIndexes) {
		this.pseudoElementIndexes = pseudoElementIndexes;
	}

	/**
	 * The index of the node's related layout tree node in the <code>layoutTreeNodes</code> array returned by <code>getSnapshot</code>, if any.
	 */
	public Integer getLayoutNodeIndex() {
		return layoutNodeIndex;
	}

	/**
	 * The index of the node's related layout tree node in the <code>layoutTreeNodes</code> array returned by <code>getSnapshot</code>, if any.
	 */
	public void setLayoutNodeIndex(Integer layoutNodeIndex) {
		this.layoutNodeIndex = layoutNodeIndex;
	}

	/**
	 * Document URL that <code>Document</code> or <code>FrameOwner</code> node points to.
	 */
	public String getDocumentURL() {
		return documentURL;
	}

	/**
	 * Document URL that <code>Document</code> or <code>FrameOwner</code> node points to.
	 */
	public void setDocumentURL(String documentURL) {
		this.documentURL = documentURL;
	}

	/**
	 * Base URL that <code>Document</code> or <code>FrameOwner</code> node uses for URL completion.
	 */
	public String getBaseURL() {
		return baseURL;
	}

	/**
	 * Base URL that <code>Document</code> or <code>FrameOwner</code> node uses for URL completion.
	 */
	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

	/**
	 * Only set for documents, contains the document's content language.
	 */
	public String getContentLanguage() {
		return contentLanguage;
	}

	/**
	 * Only set for documents, contains the document's content language.
	 */
	public void setContentLanguage(String contentLanguage) {
		this.contentLanguage = contentLanguage;
	}

	/**
	 * Only set for documents, contains the document's character set encoding.
	 */
	public String getDocumentEncoding() {
		return documentEncoding;
	}

	/**
	 * Only set for documents, contains the document's character set encoding.
	 */
	public void setDocumentEncoding(String documentEncoding) {
		this.documentEncoding = documentEncoding;
	}

	/**
	 * <code>DocumentType</code> node's publicId.
	 */
	public String getPublicId() {
		return publicId;
	}

	/**
	 * <code>DocumentType</code> node's publicId.
	 */
	public void setPublicId(String publicId) {
		this.publicId = publicId;
	}

	/**
	 * <code>DocumentType</code> node's systemId.
	 */
	public String getSystemId() {
		return systemId;
	}

	/**
	 * <code>DocumentType</code> node's systemId.
	 */
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	/**
	 * Frame ID for frame owner elements and also for the document node.
	 */
	public String getFrameId() {
		return frameId;
	}

	/**
	 * Frame ID for frame owner elements and also for the document node.
	 */
	public void setFrameId(String frameId) {
		this.frameId = frameId;
	}

	/**
	 * The index of a frame owner element's content document in the <code>domNodes</code> array returned by <code>getSnapshot</code>, if any.
	 */
	public Integer getContentDocumentIndex() {
		return contentDocumentIndex;
	}

	/**
	 * The index of a frame owner element's content document in the <code>domNodes</code> array returned by <code>getSnapshot</code>, if any.
	 */
	public void setContentDocumentIndex(Integer contentDocumentIndex) {
		this.contentDocumentIndex = contentDocumentIndex;
	}

	/**
	 * Index of the imported document's node of a link element in the <code>domNodes</code> array returned by <code>getSnapshot</code>, if any.
	 */
	public Integer getImportedDocumentIndex() {
		return importedDocumentIndex;
	}

	/**
	 * Index of the imported document's node of a link element in the <code>domNodes</code> array returned by <code>getSnapshot</code>, if any.
	 */
	public void setImportedDocumentIndex(Integer importedDocumentIndex) {
		this.importedDocumentIndex = importedDocumentIndex;
	}

	/**
	 * Index of the content node of a template element in the <code>domNodes</code> array returned by <code>getSnapshot</code>.
	 */
	public Integer getTemplateContentIndex() {
		return templateContentIndex;
	}

	/**
	 * Index of the content node of a template element in the <code>domNodes</code> array returned by <code>getSnapshot</code>.
	 */
	public void setTemplateContentIndex(Integer templateContentIndex) {
		this.templateContentIndex = templateContentIndex;
	}

	/**
	 * Type of a pseudo element node.
	 */
	public PseudoType getPseudoType() {
		return pseudoType;
	}

	/**
	 * Type of a pseudo element node.
	 */
	public void setPseudoType(PseudoType pseudoType) {
		this.pseudoType = pseudoType;
	}

	/**
	 * Whether this DOM node responds to mouse clicks. This includes nodes that have had click event listeners attached via JavaScript as well as anchor tags that naturally navigate when clicked.
	 */
	public Boolean getIsClickable() {
		return isClickable;
	}

	/**
	 * Whether this DOM node responds to mouse clicks. This includes nodes that have had click event listeners attached via JavaScript as well as anchor tags that naturally navigate when clicked.
	 */
	public void setIsClickable(Boolean isClickable) {
		this.isClickable = isClickable;
	}
}
