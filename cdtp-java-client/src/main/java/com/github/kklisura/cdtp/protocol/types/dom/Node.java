package com.github.kklisura.cdtp.protocol.types.dom;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import java.util.List;

/**
 * DOM interaction is implemented in terms of mirror objects that represent the actual DOM nodes.
 * DOMNode is a base node mirror type.
 */
public class Node {

  private Integer nodeId;

  @Experimental @Optional private Integer parentId;

  @Experimental private Integer backendNodeId;

  private Integer nodeType;

  private String nodeName;

  private String localName;

  private String nodeValue;

  @Optional private Integer childNodeCount;

  @Optional private List<com.github.kklisura.cdtp.protocol.types.dom.Node> children;

  @Optional private List<String> attributes;

  @Optional private String documentURL;

  @Experimental @Optional private String baseURL;

  @Optional private String publicId;

  @Optional private String systemId;

  @Optional private String internalSubset;

  @Optional private String xmlVersion;

  @Optional private String name;

  @Optional private String value;

  @Optional private PseudoType pseudoType;

  @Optional private ShadowRootType shadowRootType;

  @Experimental @Optional private String frameId;

  @Optional private com.github.kklisura.cdtp.protocol.types.dom.Node contentDocument;

  @Experimental @Optional
  private List<com.github.kklisura.cdtp.protocol.types.dom.Node> shadowRoots;

  @Experimental @Optional private com.github.kklisura.cdtp.protocol.types.dom.Node templateContent;

  @Experimental @Optional
  private List<com.github.kklisura.cdtp.protocol.types.dom.Node> pseudoElements;

  @Optional private com.github.kklisura.cdtp.protocol.types.dom.Node importedDocument;

  @Experimental @Optional private List<BackendNode> distributedNodes;

  @Experimental @Optional private Boolean isSVG;

  /**
   * Node identifier that is passed into the rest of the DOM messages as the <code>nodeId</code>.
   * Backend will only push node with given <code>id</code> once. It is aware of all requested nodes
   * and will only fire DOM events for nodes known to the client.
   */
  public Integer getNodeId() {
    return nodeId;
  }

  /**
   * Node identifier that is passed into the rest of the DOM messages as the <code>nodeId</code>.
   * Backend will only push node with given <code>id</code> once. It is aware of all requested nodes
   * and will only fire DOM events for nodes known to the client.
   */
  public void setNodeId(Integer nodeId) {
    this.nodeId = nodeId;
  }

  /** The id of the parent node if any. */
  public Integer getParentId() {
    return parentId;
  }

  /** The id of the parent node if any. */
  public void setParentId(Integer parentId) {
    this.parentId = parentId;
  }

  /** The BackendNodeId for this node. */
  public Integer getBackendNodeId() {
    return backendNodeId;
  }

  /** The BackendNodeId for this node. */
  public void setBackendNodeId(Integer backendNodeId) {
    this.backendNodeId = backendNodeId;
  }

  /** <code>Node</code>'s nodeType. */
  public Integer getNodeType() {
    return nodeType;
  }

  /** <code>Node</code>'s nodeType. */
  public void setNodeType(Integer nodeType) {
    this.nodeType = nodeType;
  }

  /** <code>Node</code>'s nodeName. */
  public String getNodeName() {
    return nodeName;
  }

  /** <code>Node</code>'s nodeName. */
  public void setNodeName(String nodeName) {
    this.nodeName = nodeName;
  }

  /** <code>Node</code>'s localName. */
  public String getLocalName() {
    return localName;
  }

  /** <code>Node</code>'s localName. */
  public void setLocalName(String localName) {
    this.localName = localName;
  }

  /** <code>Node</code>'s nodeValue. */
  public String getNodeValue() {
    return nodeValue;
  }

  /** <code>Node</code>'s nodeValue. */
  public void setNodeValue(String nodeValue) {
    this.nodeValue = nodeValue;
  }

  /** Child count for <code>Container</code> nodes. */
  public Integer getChildNodeCount() {
    return childNodeCount;
  }

  /** Child count for <code>Container</code> nodes. */
  public void setChildNodeCount(Integer childNodeCount) {
    this.childNodeCount = childNodeCount;
  }

  /** Child nodes of this node when requested with children. */
  public List<com.github.kklisura.cdtp.protocol.types.dom.Node> getChildren() {
    return children;
  }

  /** Child nodes of this node when requested with children. */
  public void setChildren(List<com.github.kklisura.cdtp.protocol.types.dom.Node> children) {
    this.children = children;
  }

  /**
   * Attributes of the <code>Element</code> node in the form of flat array <code>
   * [name1, value1, name2, value2]</code>.
   */
  public List<String> getAttributes() {
    return attributes;
  }

  /**
   * Attributes of the <code>Element</code> node in the form of flat array <code>
   * [name1, value1, name2, value2]</code>.
   */
  public void setAttributes(List<String> attributes) {
    this.attributes = attributes;
  }

  /** Document URL that <code>Document</code> or <code>FrameOwner</code> node points to. */
  public String getDocumentURL() {
    return documentURL;
  }

  /** Document URL that <code>Document</code> or <code>FrameOwner</code> node points to. */
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

  /** <code>DocumentType</code>'s publicId. */
  public String getPublicId() {
    return publicId;
  }

  /** <code>DocumentType</code>'s publicId. */
  public void setPublicId(String publicId) {
    this.publicId = publicId;
  }

  /** <code>DocumentType</code>'s systemId. */
  public String getSystemId() {
    return systemId;
  }

  /** <code>DocumentType</code>'s systemId. */
  public void setSystemId(String systemId) {
    this.systemId = systemId;
  }

  /** <code>DocumentType</code>'s internalSubset. */
  public String getInternalSubset() {
    return internalSubset;
  }

  /** <code>DocumentType</code>'s internalSubset. */
  public void setInternalSubset(String internalSubset) {
    this.internalSubset = internalSubset;
  }

  /** <code>Document</code>'s XML version in case of XML documents. */
  public String getXmlVersion() {
    return xmlVersion;
  }

  /** <code>Document</code>'s XML version in case of XML documents. */
  public void setXmlVersion(String xmlVersion) {
    this.xmlVersion = xmlVersion;
  }

  /** <code>Attr</code>'s name. */
  public String getName() {
    return name;
  }

  /** <code>Attr</code>'s name. */
  public void setName(String name) {
    this.name = name;
  }

  /** <code>Attr</code>'s value. */
  public String getValue() {
    return value;
  }

  /** <code>Attr</code>'s value. */
  public void setValue(String value) {
    this.value = value;
  }

  /** Pseudo element type for this node. */
  public PseudoType getPseudoType() {
    return pseudoType;
  }

  /** Pseudo element type for this node. */
  public void setPseudoType(PseudoType pseudoType) {
    this.pseudoType = pseudoType;
  }

  /** Shadow root type. */
  public ShadowRootType getShadowRootType() {
    return shadowRootType;
  }

  /** Shadow root type. */
  public void setShadowRootType(ShadowRootType shadowRootType) {
    this.shadowRootType = shadowRootType;
  }

  /** Frame ID for frame owner elements. */
  public String getFrameId() {
    return frameId;
  }

  /** Frame ID for frame owner elements. */
  public void setFrameId(String frameId) {
    this.frameId = frameId;
  }

  /** Content document for frame owner elements. */
  public com.github.kklisura.cdtp.protocol.types.dom.Node getContentDocument() {
    return contentDocument;
  }

  /** Content document for frame owner elements. */
  public void setContentDocument(com.github.kklisura.cdtp.protocol.types.dom.Node contentDocument) {
    this.contentDocument = contentDocument;
  }

  /** Shadow root list for given element host. */
  public List<com.github.kklisura.cdtp.protocol.types.dom.Node> getShadowRoots() {
    return shadowRoots;
  }

  /** Shadow root list for given element host. */
  public void setShadowRoots(List<com.github.kklisura.cdtp.protocol.types.dom.Node> shadowRoots) {
    this.shadowRoots = shadowRoots;
  }

  /** Content document fragment for template elements. */
  public com.github.kklisura.cdtp.protocol.types.dom.Node getTemplateContent() {
    return templateContent;
  }

  /** Content document fragment for template elements. */
  public void setTemplateContent(com.github.kklisura.cdtp.protocol.types.dom.Node templateContent) {
    this.templateContent = templateContent;
  }

  /** Pseudo elements associated with this node. */
  public List<com.github.kklisura.cdtp.protocol.types.dom.Node> getPseudoElements() {
    return pseudoElements;
  }

  /** Pseudo elements associated with this node. */
  public void setPseudoElements(
      List<com.github.kklisura.cdtp.protocol.types.dom.Node> pseudoElements) {
    this.pseudoElements = pseudoElements;
  }

  /** Import document for the HTMLImport links. */
  public com.github.kklisura.cdtp.protocol.types.dom.Node getImportedDocument() {
    return importedDocument;
  }

  /** Import document for the HTMLImport links. */
  public void setImportedDocument(
      com.github.kklisura.cdtp.protocol.types.dom.Node importedDocument) {
    this.importedDocument = importedDocument;
  }

  /** Distributed nodes for given insertion point. */
  public List<BackendNode> getDistributedNodes() {
    return distributedNodes;
  }

  /** Distributed nodes for given insertion point. */
  public void setDistributedNodes(List<BackendNode> distributedNodes) {
    this.distributedNodes = distributedNodes;
  }

  /** Whether the node is SVG. */
  public Boolean getIsSVG() {
    return isSVG;
  }

  /** Whether the node is SVG. */
  public void setIsSVG(Boolean isSVG) {
    this.isSVG = isSVG;
  }
}
