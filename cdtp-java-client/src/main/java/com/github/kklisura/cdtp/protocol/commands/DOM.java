package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.annotations.Optional;
import com.github.kklisura.cdtp.protocol.annotations.Returns;
import com.github.kklisura.cdtp.protocol.types.dom.BoxModel;
import com.github.kklisura.cdtp.protocol.types.dom.Node;
import com.github.kklisura.cdtp.protocol.types.dom.PerformSearch;
import com.github.kklisura.cdtp.protocol.types.runtime.RemoteObject;
import java.util.List;

/**
 * This domain exposes DOM read/write operations. Each DOM Node is represented with its mirror object that has an <code>id</code>. This <code>id</code> can be used to get additional information on the Node, resolve it into the JavaScript object wrapper, etc. It is important that client receives DOM events only for the nodes that are known to the client. Backend keeps track of the nodes that were sent to the client and never sends the same node twice. It is client's responsibility to collect information about the nodes that were sent to the client.<p>Note that <code>iframe</code> owner elements will return corresponding document elements as their child nodes.</p>
 */
public interface DOM {

	/**
	 * Enables DOM agent for the given page.
	 */
	void enable();

	/**
	 * Disables DOM agent for the given page.
	 */
	void disable();

	/**
	 * Returns the root DOM node (and optionally the subtree) to the caller.
	 */
	@Returns("root")
	Node getDocument(@Experimental @Optional Integer depth, @Experimental @Optional Boolean pierce);

	/**
	 * Returns the root DOM node (and optionally the subtree) to the caller.
	 */
	@Returns("nodes")
	List<Node> getFlattenedDocument(@Experimental @Optional Integer depth, @Experimental @Optional Boolean pierce);

	/**
	 * Collects class names for the node with given id and all of it's child nodes.
	 */
	@Experimental
	@Returns("classNames")
	List<String> collectClassNamesFromSubtree(Integer nodeId);

	/**
	 * Requests that children of the node with given id are returned to the caller in form of <code>setChildNodes</code> events where not only immediate children are retrieved, but all children down to the specified depth.
	 */
	void requestChildNodes(Integer nodeId, @Experimental @Optional Integer depth, @Experimental @Optional Boolean pierce);

	/**
	 * Executes <code>querySelector</code> on a given node.
	 */
	@Returns("nodeId")
	Integer querySelector(Integer nodeId, String selector);

	/**
	 * Executes <code>querySelectorAll</code> on a given node.
	 */
	@Returns("nodeIds")
	List<Integer> querySelectorAll(Integer nodeId, String selector);

	/**
	 * Sets node name for a node with given id.
	 */
	@Returns("nodeId")
	Integer setNodeName(Integer nodeId, String name);

	/**
	 * Sets node value for a node with given id.
	 */
	void setNodeValue(Integer nodeId, String value);

	/**
	 * Removes node with given id.
	 */
	void removeNode(Integer nodeId);

	/**
	 * Sets attribute for an element with given id.
	 */
	void setAttributeValue(Integer nodeId, String name, String value);

	/**
	 * Sets attributes on element with given id. This method is useful when user edits some existing attribute value and types in several attribute name/value pairs.
	 */
	void setAttributesAsText(Integer nodeId, String text, @Optional String name);

	/**
	 * Removes attribute with given name from an element with given id.
	 */
	void removeAttribute(Integer nodeId, String name);

	/**
	 * Returns node's HTML markup.
	 */
	@Returns("outerHTML")
	String getOuterHTML(@Optional Integer nodeId, @Optional Integer backendNodeId, @Optional String objectId);

	/**
	 * Sets node HTML markup, returns new node id.
	 */
	void setOuterHTML(Integer nodeId, String outerHTML);

	/**
	 * Searches for a given string in the DOM tree. Use <code>getSearchResults</code> to access search results or <code>cancelSearch</code> to end this search session.
	 */
	@Experimental
	PerformSearch performSearch(String query, @Experimental @Optional Boolean includeUserAgentShadowDOM);

	/**
	 * Returns search results from given <code>fromIndex</code> to given <code>toIndex</code> from the sarch with the given identifier.
	 */
	@Experimental
	@Returns("nodeIds")
	List<Integer> getSearchResults(String searchId, Integer fromIndex, Integer toIndex);

	/**
	 * Discards search results from the session with the given id. <code>getSearchResults</code> should no longer be called for that search.
	 */
	@Experimental
	void discardSearchResults(String searchId);

	/**
	 * Requests that the node is sent to the caller given the JavaScript node object reference. All nodes that form the path from the node to the root are also sent to the client as a series of <code>setChildNodes</code> notifications.
	 */
	@Returns("nodeId")
	Integer requestNode(String objectId);

	/**
	 * Highlights given rectangle.
	 */
	void highlightRect();

	/**
	 * Highlights DOM node.
	 */
	void highlightNode();

	/**
	 * Hides any highlight.
	 */
	void hideHighlight();

	/**
	 * Requests that the node is sent to the caller given its path. // FIXME, use XPath
	 */
	@Experimental
	@Returns("nodeId")
	Integer pushNodeByPathToFrontend(String path);

	/**
	 * Requests that a batch of nodes is sent to the caller given their backend node ids.
	 */
	@Experimental
	@Returns("nodeIds")
	List<Integer> pushNodesByBackendIdsToFrontend(List<Integer> backendNodeIds);

	/**
	 * Enables console to refer to the node with given id via $x (see Command Line API for more details $x functions).
	 */
	@Experimental
	void setInspectedNode(Integer nodeId);

	/**
	 * Resolves the JavaScript node object for a given NodeId or BackendNodeId.
	 */
	@Returns("object")
	RemoteObject resolveNode(@Optional Integer nodeId, @Optional Integer backendNodeId, @Optional String objectGroup);

	/**
	 * Returns attributes for the specified node.
	 */
	@Returns("attributes")
	List<String> getAttributes(Integer nodeId);

	/**
	 * Creates a deep copy of the specified node and places it into the target container before the given anchor.
	 */
	@Experimental
	@Returns("nodeId")
	Integer copyTo(Integer nodeId, Integer targetNodeId, @Optional Integer insertBeforeNodeId);

	/**
	 * Moves node into the new container, places it before the given anchor.
	 */
	@Returns("nodeId")
	Integer moveTo(Integer nodeId, Integer targetNodeId, @Optional Integer insertBeforeNodeId);

	/**
	 * Undoes the last performed action.
	 */
	@Experimental
	void undo();

	/**
	 * Re-does the last undone action.
	 */
	@Experimental
	void redo();

	/**
	 * Marks last undoable state.
	 */
	@Experimental
	void markUndoableState();

	/**
	 * Focuses the given element.
	 */
	@Experimental
	void focus(@Optional Integer nodeId, @Optional Integer backendNodeId, @Optional String objectId);

	/**
	 * Sets files for the given file input element.
	 */
	@Experimental
	void setFileInputFiles(List<String> files, @Optional Integer nodeId, @Optional Integer backendNodeId, @Optional String objectId);

	/**
	 * Returns boxes for the currently selected nodes.
	 */
	@Experimental
	@Returns("model")
	BoxModel getBoxModel(@Optional Integer nodeId, @Optional Integer backendNodeId, @Optional String objectId);

	/**
	 * Returns node id at given location.
	 */
	@Experimental
	@Returns("nodeId")
	Integer getNodeForLocation(Integer x, Integer y, @Optional Boolean includeUserAgentShadowDOM);

	/**
	 * Returns the id of the nearest ancestor that is a relayout boundary.
	 */
	@Experimental
	@Returns("nodeId")
	Integer getRelayoutBoundary(Integer nodeId);

	/**
	 * Describes node given its id, does not require domain to be enabled. Does not start tracking any objects, can be used for automation.
	 */
	@Returns("node")
	Node describeNode(@Optional Integer nodeId, @Optional Integer backendNodeId, @Optional String objectId, @Experimental @Optional Integer depth, @Experimental @Optional Boolean pierce);
}
