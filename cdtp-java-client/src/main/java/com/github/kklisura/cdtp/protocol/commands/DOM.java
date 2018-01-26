package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.events.dom.AttributeModified;
import com.github.kklisura.cdtp.protocol.events.dom.AttributeRemoved;
import com.github.kklisura.cdtp.protocol.events.dom.CharacterDataModified;
import com.github.kklisura.cdtp.protocol.events.dom.ChildNodeCountUpdated;
import com.github.kklisura.cdtp.protocol.events.dom.ChildNodeInserted;
import com.github.kklisura.cdtp.protocol.events.dom.ChildNodeRemoved;
import com.github.kklisura.cdtp.protocol.events.dom.DistributedNodesUpdated;
import com.github.kklisura.cdtp.protocol.events.dom.DocumentUpdated;
import com.github.kklisura.cdtp.protocol.events.dom.InlineStyleInvalidated;
import com.github.kklisura.cdtp.protocol.events.dom.PseudoElementAdded;
import com.github.kklisura.cdtp.protocol.events.dom.PseudoElementRemoved;
import com.github.kklisura.cdtp.protocol.events.dom.SetChildNodes;
import com.github.kklisura.cdtp.protocol.events.dom.ShadowRootPopped;
import com.github.kklisura.cdtp.protocol.events.dom.ShadowRootPushed;
import com.github.kklisura.cdtp.protocol.support.annotations.EventName;
import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import com.github.kklisura.cdtp.protocol.support.annotations.ParamName;
import com.github.kklisura.cdtp.protocol.support.annotations.Returns;
import com.github.kklisura.cdtp.protocol.support.types.EventHandler;
import com.github.kklisura.cdtp.protocol.support.types.EventListener;
import com.github.kklisura.cdtp.protocol.types.dom.BoxModel;
import com.github.kklisura.cdtp.protocol.types.dom.Node;
import com.github.kklisura.cdtp.protocol.types.dom.PerformSearch;
import com.github.kklisura.cdtp.protocol.types.runtime.RemoteObject;
import java.util.List;

/**
 * This domain exposes DOM read/write operations. Each DOM Node is represented with its mirror
 * object that has an <code>id</code>. This <code>id</code> can be used to get additional
 * information on the Node, resolve it into the JavaScript object wrapper, etc. It is important that
 * client receives DOM events only for the nodes that are known to the client. Backend keeps track
 * of the nodes that were sent to the client and never sends the same node twice. It is client's
 * responsibility to collect information about the nodes that were sent to the client.
 *
 * <p>Note that <code>iframe</code> owner elements will return corresponding document elements as
 * their child nodes.
 */
public interface DOM {

  /** Enables DOM agent for the given page. */
  void enable();

  /** Disables DOM agent for the given page. */
  void disable();

  /** Returns the root DOM node (and optionally the subtree) to the caller. */
  @Returns("root")
  Node getDocument();

  /**
   * Returns the root DOM node (and optionally the subtree) to the caller.
   *
   * @param depth The maximum depth at which children should be retrieved, defaults to 1. Use -1 for
   *     the entire subtree or provide an integer larger than 0.
   * @param pierce Whether or not iframes and shadow roots should be traversed when returning the
   *     subtree (default is false).
   */
  @Returns("root")
  Node getDocument(
      @Experimental @Optional @ParamName("depth") Integer depth,
      @Experimental @Optional @ParamName("pierce") Boolean pierce);

  /** Returns the root DOM node (and optionally the subtree) to the caller. */
  @Returns("nodes")
  List<Node> getFlattenedDocument();

  /**
   * Returns the root DOM node (and optionally the subtree) to the caller.
   *
   * @param depth The maximum depth at which children should be retrieved, defaults to 1. Use -1 for
   *     the entire subtree or provide an integer larger than 0.
   * @param pierce Whether or not iframes and shadow roots should be traversed when returning the
   *     subtree (default is false).
   */
  @Returns("nodes")
  List<Node> getFlattenedDocument(
      @Experimental @Optional @ParamName("depth") Integer depth,
      @Experimental @Optional @ParamName("pierce") Boolean pierce);

  /**
   * Collects class names for the node with given id and all of it's child nodes.
   *
   * @param nodeId Id of the node to collect class names.
   */
  @Experimental
  @Returns("classNames")
  List<String> collectClassNamesFromSubtree(@ParamName("nodeId") Integer nodeId);

  /**
   * Requests that children of the node with given id are returned to the caller in form of <code>
   * setChildNodes</code> events where not only immediate children are retrieved, but all children
   * down to the specified depth.
   *
   * @param nodeId Id of the node to get children for.
   */
  void requestChildNodes(@ParamName("nodeId") Integer nodeId);

  /**
   * Requests that children of the node with given id are returned to the caller in form of <code>
   * setChildNodes</code> events where not only immediate children are retrieved, but all children
   * down to the specified depth.
   *
   * @param nodeId Id of the node to get children for.
   * @param depth The maximum depth at which children should be retrieved, defaults to 1. Use -1 for
   *     the entire subtree or provide an integer larger than 0.
   * @param pierce Whether or not iframes and shadow roots should be traversed when returning the
   *     sub-tree (default is false).
   */
  void requestChildNodes(
      @ParamName("nodeId") Integer nodeId,
      @Experimental @Optional @ParamName("depth") Integer depth,
      @Experimental @Optional @ParamName("pierce") Boolean pierce);

  /**
   * Executes <code>querySelector</code> on a given node.
   *
   * @param nodeId Id of the node to query upon.
   * @param selector Selector string.
   */
  @Returns("nodeId")
  Integer querySelector(
      @ParamName("nodeId") Integer nodeId, @ParamName("selector") String selector);

  /**
   * Executes <code>querySelectorAll</code> on a given node.
   *
   * @param nodeId Id of the node to query upon.
   * @param selector Selector string.
   */
  @Returns("nodeIds")
  List<Integer> querySelectorAll(
      @ParamName("nodeId") Integer nodeId, @ParamName("selector") String selector);

  /**
   * Sets node name for a node with given id.
   *
   * @param nodeId Id of the node to set name for.
   * @param name New node's name.
   */
  @Returns("nodeId")
  Integer setNodeName(@ParamName("nodeId") Integer nodeId, @ParamName("name") String name);

  /**
   * Sets node value for a node with given id.
   *
   * @param nodeId Id of the node to set value for.
   * @param value New node's value.
   */
  void setNodeValue(@ParamName("nodeId") Integer nodeId, @ParamName("value") String value);

  /**
   * Removes node with given id.
   *
   * @param nodeId Id of the node to remove.
   */
  void removeNode(@ParamName("nodeId") Integer nodeId);

  /**
   * Sets attribute for an element with given id.
   *
   * @param nodeId Id of the element to set attribute for.
   * @param name Attribute name.
   * @param value Attribute value.
   */
  void setAttributeValue(
      @ParamName("nodeId") Integer nodeId,
      @ParamName("name") String name,
      @ParamName("value") String value);

  /**
   * Sets attributes on element with given id. This method is useful when user edits some existing
   * attribute value and types in several attribute name/value pairs.
   *
   * @param nodeId Id of the element to set attributes for.
   * @param text Text with a number of attributes. Will parse this text using HTML parser.
   */
  void setAttributesAsText(@ParamName("nodeId") Integer nodeId, @ParamName("text") String text);

  /**
   * Sets attributes on element with given id. This method is useful when user edits some existing
   * attribute value and types in several attribute name/value pairs.
   *
   * @param nodeId Id of the element to set attributes for.
   * @param text Text with a number of attributes. Will parse this text using HTML parser.
   * @param name Attribute name to replace with new attributes derived from text in case text parsed
   *     successfully.
   */
  void setAttributesAsText(
      @ParamName("nodeId") Integer nodeId,
      @ParamName("text") String text,
      @Optional @ParamName("name") String name);

  /**
   * Removes attribute with given name from an element with given id.
   *
   * @param nodeId Id of the element to remove attribute from.
   * @param name Name of the attribute to remove.
   */
  void removeAttribute(@ParamName("nodeId") Integer nodeId, @ParamName("name") String name);

  /** Returns node's HTML markup. */
  @Returns("outerHTML")
  String getOuterHTML();

  /**
   * Returns node's HTML markup.
   *
   * @param nodeId Identifier of the node.
   * @param backendNodeId Identifier of the backend node.
   * @param objectId JavaScript object id of the node wrapper.
   */
  @Returns("outerHTML")
  String getOuterHTML(
      @Optional @ParamName("nodeId") Integer nodeId,
      @Optional @ParamName("backendNodeId") Integer backendNodeId,
      @Optional @ParamName("objectId") String objectId);

  /**
   * Sets node HTML markup, returns new node id.
   *
   * @param nodeId Id of the node to set markup for.
   * @param outerHTML Outer HTML markup to set.
   */
  void setOuterHTML(@ParamName("nodeId") Integer nodeId, @ParamName("outerHTML") String outerHTML);

  /**
   * Searches for a given string in the DOM tree. Use <code>getSearchResults</code> to access search
   * results or <code>cancelSearch</code> to end this search session.
   *
   * @param query Plain text or query selector or XPath search query.
   */
  @Experimental
  PerformSearch performSearch(@ParamName("query") String query);

  /**
   * Searches for a given string in the DOM tree. Use <code>getSearchResults</code> to access search
   * results or <code>cancelSearch</code> to end this search session.
   *
   * @param query Plain text or query selector or XPath search query.
   * @param includeUserAgentShadowDOM True to search in user agent shadow DOM.
   */
  @Experimental
  PerformSearch performSearch(
      @ParamName("query") String query,
      @Experimental @Optional @ParamName("includeUserAgentShadowDOM")
          Boolean includeUserAgentShadowDOM);

  /**
   * Returns search results from given <code>fromIndex</code> to given <code>toIndex</code> from the
   * sarch with the given identifier.
   *
   * @param searchId Unique search session identifier.
   * @param fromIndex Start index of the search result to be returned.
   * @param toIndex End index of the search result to be returned.
   */
  @Experimental
  @Returns("nodeIds")
  List<Integer> getSearchResults(
      @ParamName("searchId") String searchId,
      @ParamName("fromIndex") Integer fromIndex,
      @ParamName("toIndex") Integer toIndex);

  /**
   * Discards search results from the session with the given id. <code>getSearchResults</code>
   * should no longer be called for that search.
   *
   * @param searchId Unique search session identifier.
   */
  @Experimental
  void discardSearchResults(@ParamName("searchId") String searchId);

  /**
   * Requests that the node is sent to the caller given the JavaScript node object reference. All
   * nodes that form the path from the node to the root are also sent to the client as a series of
   * <code>setChildNodes</code> notifications.
   *
   * @param objectId JavaScript object id to convert into node.
   */
  @Returns("nodeId")
  Integer requestNode(@ParamName("objectId") String objectId);

  /**
   * Requests that the node is sent to the caller given its path. // FIXME, use XPath
   *
   * @param path Path to node in the proprietary format.
   */
  @Experimental
  @Returns("nodeId")
  Integer pushNodeByPathToFrontend(@ParamName("path") String path);

  /**
   * Requests that a batch of nodes is sent to the caller given their backend node ids.
   *
   * @param backendNodeIds The array of backend node ids.
   */
  @Experimental
  @Returns("nodeIds")
  List<Integer> pushNodesByBackendIdsToFrontend(
      @ParamName("backendNodeIds") List<Integer> backendNodeIds);

  /**
   * Enables console to refer to the node with given id via $x (see Command Line API for more
   * details $x functions).
   *
   * @param nodeId DOM node id to be accessible by means of $x command line API.
   */
  @Experimental
  void setInspectedNode(@ParamName("nodeId") Integer nodeId);

  /** Resolves the JavaScript node object for a given NodeId or BackendNodeId. */
  @Returns("object")
  RemoteObject resolveNode();

  /**
   * Resolves the JavaScript node object for a given NodeId or BackendNodeId.
   *
   * @param nodeId Id of the node to resolve.
   * @param backendNodeId Backend identifier of the node to resolve.
   * @param objectGroup Symbolic group name that can be used to release multiple objects.
   */
  @Returns("object")
  RemoteObject resolveNode(
      @Optional @ParamName("nodeId") Integer nodeId,
      @Optional @ParamName("backendNodeId") Integer backendNodeId,
      @Optional @ParamName("objectGroup") String objectGroup);

  /**
   * Returns attributes for the specified node.
   *
   * @param nodeId Id of the node to retrieve attibutes for.
   */
  @Returns("attributes")
  List<String> getAttributes(@ParamName("nodeId") Integer nodeId);

  /**
   * Creates a deep copy of the specified node and places it into the target container before the
   * given anchor.
   *
   * @param nodeId Id of the node to copy.
   * @param targetNodeId Id of the element to drop the copy into.
   */
  @Experimental
  @Returns("nodeId")
  Integer copyTo(
      @ParamName("nodeId") Integer nodeId, @ParamName("targetNodeId") Integer targetNodeId);

  /**
   * Creates a deep copy of the specified node and places it into the target container before the
   * given anchor.
   *
   * @param nodeId Id of the node to copy.
   * @param targetNodeId Id of the element to drop the copy into.
   * @param insertBeforeNodeId Drop the copy before this node (if absent, the copy becomes the last
   *     child of <code>targetNodeId</code>).
   */
  @Experimental
  @Returns("nodeId")
  Integer copyTo(
      @ParamName("nodeId") Integer nodeId,
      @ParamName("targetNodeId") Integer targetNodeId,
      @Optional @ParamName("insertBeforeNodeId") Integer insertBeforeNodeId);

  /**
   * Moves node into the new container, places it before the given anchor.
   *
   * @param nodeId Id of the node to move.
   * @param targetNodeId Id of the element to drop the moved node into.
   */
  @Returns("nodeId")
  Integer moveTo(
      @ParamName("nodeId") Integer nodeId, @ParamName("targetNodeId") Integer targetNodeId);

  /**
   * Moves node into the new container, places it before the given anchor.
   *
   * @param nodeId Id of the node to move.
   * @param targetNodeId Id of the element to drop the moved node into.
   * @param insertBeforeNodeId Drop node before this one (if absent, the moved node becomes the last
   *     child of <code>targetNodeId</code>).
   */
  @Returns("nodeId")
  Integer moveTo(
      @ParamName("nodeId") Integer nodeId,
      @ParamName("targetNodeId") Integer targetNodeId,
      @Optional @ParamName("insertBeforeNodeId") Integer insertBeforeNodeId);

  /** Undoes the last performed action. */
  @Experimental
  void undo();

  /** Re-does the last undone action. */
  @Experimental
  void redo();

  /** Marks last undoable state. */
  @Experimental
  void markUndoableState();

  /** Focuses the given element. */
  @Experimental
  void focus();

  /**
   * Focuses the given element.
   *
   * @param nodeId Identifier of the node.
   * @param backendNodeId Identifier of the backend node.
   * @param objectId JavaScript object id of the node wrapper.
   */
  @Experimental
  void focus(
      @Optional @ParamName("nodeId") Integer nodeId,
      @Optional @ParamName("backendNodeId") Integer backendNodeId,
      @Optional @ParamName("objectId") String objectId);

  /**
   * Sets files for the given file input element.
   *
   * @param files Array of file paths to set.
   */
  @Experimental
  void setFileInputFiles(@ParamName("files") List<String> files);

  /**
   * Sets files for the given file input element.
   *
   * @param files Array of file paths to set.
   * @param nodeId Identifier of the node.
   * @param backendNodeId Identifier of the backend node.
   * @param objectId JavaScript object id of the node wrapper.
   */
  @Experimental
  void setFileInputFiles(
      @ParamName("files") List<String> files,
      @Optional @ParamName("nodeId") Integer nodeId,
      @Optional @ParamName("backendNodeId") Integer backendNodeId,
      @Optional @ParamName("objectId") String objectId);

  /** Returns boxes for the currently selected nodes. */
  @Experimental
  @Returns("model")
  BoxModel getBoxModel();

  /**
   * Returns boxes for the currently selected nodes.
   *
   * @param nodeId Identifier of the node.
   * @param backendNodeId Identifier of the backend node.
   * @param objectId JavaScript object id of the node wrapper.
   */
  @Experimental
  @Returns("model")
  BoxModel getBoxModel(
      @Optional @ParamName("nodeId") Integer nodeId,
      @Optional @ParamName("backendNodeId") Integer backendNodeId,
      @Optional @ParamName("objectId") String objectId);

  /**
   * Returns node id at given location.
   *
   * @param x X coordinate.
   * @param y Y coordinate.
   */
  @Experimental
  @Returns("nodeId")
  Integer getNodeForLocation(@ParamName("x") Integer x, @ParamName("y") Integer y);

  /**
   * Returns node id at given location.
   *
   * @param x X coordinate.
   * @param y Y coordinate.
   * @param includeUserAgentShadowDOM False to skip to the nearest non-UA shadow root ancestor
   *     (default: false).
   */
  @Experimental
  @Returns("nodeId")
  Integer getNodeForLocation(
      @ParamName("x") Integer x,
      @ParamName("y") Integer y,
      @Optional @ParamName("includeUserAgentShadowDOM") Boolean includeUserAgentShadowDOM);

  /**
   * Returns the id of the nearest ancestor that is a relayout boundary.
   *
   * @param nodeId Id of the node.
   */
  @Experimental
  @Returns("nodeId")
  Integer getRelayoutBoundary(@ParamName("nodeId") Integer nodeId);

  /**
   * Describes node given its id, does not require domain to be enabled. Does not start tracking any
   * objects, can be used for automation.
   */
  @Returns("node")
  Node describeNode();

  /**
   * Describes node given its id, does not require domain to be enabled. Does not start tracking any
   * objects, can be used for automation.
   *
   * @param nodeId Identifier of the node.
   * @param backendNodeId Identifier of the backend node.
   * @param objectId JavaScript object id of the node wrapper.
   * @param depth The maximum depth at which children should be retrieved, defaults to 1. Use -1 for
   *     the entire subtree or provide an integer larger than 0.
   * @param pierce Whether or not iframes and shadow roots should be traversed when returning the
   *     subtree (default is false).
   */
  @Returns("node")
  Node describeNode(
      @Optional @ParamName("nodeId") Integer nodeId,
      @Optional @ParamName("backendNodeId") Integer backendNodeId,
      @Optional @ParamName("objectId") String objectId,
      @Experimental @Optional @ParamName("depth") Integer depth,
      @Experimental @Optional @ParamName("pierce") Boolean pierce);

  /** Fired when <code>Document</code> has been totally updated. Node ids are no longer valid. */
  @EventName("documentUpdated")
  EventListener onDocumentUpdated(EventHandler<DocumentUpdated> eventListener);

  /**
   * Fired when backend wants to provide client with the missing DOM structure. This happens upon
   * most of the calls requesting node ids.
   */
  @EventName("setChildNodes")
  EventListener onSetChildNodes(EventHandler<SetChildNodes> eventListener);

  /** Fired when <code>Element</code>'s attribute is modified. */
  @EventName("attributeModified")
  EventListener onAttributeModified(EventHandler<AttributeModified> eventListener);

  /** Fired when <code>Element</code>'s attribute is removed. */
  @EventName("attributeRemoved")
  EventListener onAttributeRemoved(EventHandler<AttributeRemoved> eventListener);

  /** Fired when <code>Element</code>'s inline style is modified via a CSS property modification. */
  @EventName("inlineStyleInvalidated")
  @Experimental
  EventListener onInlineStyleInvalidated(EventHandler<InlineStyleInvalidated> eventListener);

  /** Mirrors <code>DOMCharacterDataModified</code> event. */
  @EventName("characterDataModified")
  EventListener onCharacterDataModified(EventHandler<CharacterDataModified> eventListener);

  /** Fired when <code>Container</code>'s child node count has changed. */
  @EventName("childNodeCountUpdated")
  EventListener onChildNodeCountUpdated(EventHandler<ChildNodeCountUpdated> eventListener);

  /** Mirrors <code>DOMNodeInserted</code> event. */
  @EventName("childNodeInserted")
  EventListener onChildNodeInserted(EventHandler<ChildNodeInserted> eventListener);

  /** Mirrors <code>DOMNodeRemoved</code> event. */
  @EventName("childNodeRemoved")
  EventListener onChildNodeRemoved(EventHandler<ChildNodeRemoved> eventListener);

  /** Called when shadow root is pushed into the element. */
  @EventName("shadowRootPushed")
  @Experimental
  EventListener onShadowRootPushed(EventHandler<ShadowRootPushed> eventListener);

  /** Called when shadow root is popped from the element. */
  @EventName("shadowRootPopped")
  @Experimental
  EventListener onShadowRootPopped(EventHandler<ShadowRootPopped> eventListener);

  /** Called when a pseudo element is added to an element. */
  @EventName("pseudoElementAdded")
  @Experimental
  EventListener onPseudoElementAdded(EventHandler<PseudoElementAdded> eventListener);

  /** Called when a pseudo element is removed from an element. */
  @EventName("pseudoElementRemoved")
  @Experimental
  EventListener onPseudoElementRemoved(EventHandler<PseudoElementRemoved> eventListener);

  /** Called when distrubution is changed. */
  @EventName("distributedNodesUpdated")
  @Experimental
  EventListener onDistributedNodesUpdated(EventHandler<DistributedNodesUpdated> eventListener);
}
