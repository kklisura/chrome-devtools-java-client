package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.events.overlay.InspectNodeRequested;
import com.github.kklisura.cdtp.protocol.events.overlay.NodeHighlightRequested;
import com.github.kklisura.cdtp.protocol.events.overlay.ScreenshotRequested;
import com.github.kklisura.cdtp.protocol.support.annotations.EventName;
import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import com.github.kklisura.cdtp.protocol.support.annotations.ParamName;
import com.github.kklisura.cdtp.protocol.support.annotations.Returns;
import com.github.kklisura.cdtp.protocol.support.types.EventHandler;
import com.github.kklisura.cdtp.protocol.support.types.EventListener;
import com.github.kklisura.cdtp.protocol.types.dom.RGBA;
import com.github.kklisura.cdtp.protocol.types.overlay.HighlightConfig;
import com.github.kklisura.cdtp.protocol.types.overlay.InspectMode;
import java.util.List;
import java.util.Map;

/**
 * This domain provides various functionality related to drawing atop the inspected page.
 */
@Experimental
public interface Overlay {

	/**
	 * Enables domain notifications.
	 */
	void enable();

	/**
	 * Disables domain notifications.
	 */
	void disable();

	/**
	 * Requests that backend shows paint rectangles
	 */
	void setShowPaintRects(@ParamName("result") Boolean result);

	/**
	 * Requests that backend shows debug borders on layers
	 */
	void setShowDebugBorders(@ParamName("show") Boolean show);

	/**
	 * Requests that backend shows the FPS counter
	 */
	void setShowFPSCounter(@ParamName("show") Boolean show);

	/**
	 * Requests that backend shows scroll bottleneck rects
	 */
	void setShowScrollBottleneckRects(@ParamName("show") Boolean show);

	/**
	 * Paints viewport size upon main frame resize.
	 */
	void setShowViewportSizeOnResize(@ParamName("show") Boolean show);

	void setPausedInDebuggerMessage();

	void setPausedInDebuggerMessage(@Optional @ParamName("message") String message);

	void setSuspended(@ParamName("suspended") Boolean suspended);

	/**
	 * Enters the 'inspect' mode. In this mode, elements that user is hovering over are highlighted. Backend then generates 'inspectNodeRequested' event upon element selection.
	 */
	void setInspectMode(@ParamName("mode") InspectMode mode);

	/**
	 * Enters the 'inspect' mode. In this mode, elements that user is hovering over are highlighted. Backend then generates 'inspectNodeRequested' event upon element selection.
	 */
	void setInspectMode(@ParamName("mode") InspectMode mode, @Optional @ParamName("highlightConfig") HighlightConfig highlightConfig);

	/**
	 * Highlights given rectangle. Coordinates are absolute with respect to the main frame viewport.
	 */
	void highlightRect(@ParamName("x") Integer x, @ParamName("y") Integer y, @ParamName("width") Integer width, @ParamName("height") Integer height);

	/**
	 * Highlights given rectangle. Coordinates are absolute with respect to the main frame viewport.
	 */
	void highlightRect(@ParamName("x") Integer x, @ParamName("y") Integer y, @ParamName("width") Integer width, @ParamName("height") Integer height, @Optional @ParamName("color") RGBA color, @Optional @ParamName("outlineColor") RGBA outlineColor);

	/**
	 * Highlights given quad. Coordinates are absolute with respect to the main frame viewport.
	 */
	void highlightQuad(@ParamName("quad") List<Double> quad);

	/**
	 * Highlights given quad. Coordinates are absolute with respect to the main frame viewport.
	 */
	void highlightQuad(@ParamName("quad") List<Double> quad, @Optional @ParamName("color") RGBA color, @Optional @ParamName("outlineColor") RGBA outlineColor);

	/**
	 * Highlights DOM node with given id or with the given JavaScript object wrapper. Either nodeId or objectId must be specified.
	 */
	void highlightNode(@ParamName("highlightConfig") HighlightConfig highlightConfig);

	/**
	 * Highlights DOM node with given id or with the given JavaScript object wrapper. Either nodeId or objectId must be specified.
	 */
	void highlightNode(@ParamName("highlightConfig") HighlightConfig highlightConfig, @Optional @ParamName("nodeId") Integer nodeId, @Optional @ParamName("backendNodeId") Integer backendNodeId, @Optional @ParamName("objectId") String objectId);

	/**
	 * Highlights owner element of the frame with given id.
	 */
	void highlightFrame(@ParamName("frameId") String frameId);

	/**
	 * Highlights owner element of the frame with given id.
	 */
	void highlightFrame(@ParamName("frameId") String frameId, @Optional @ParamName("contentColor") RGBA contentColor, @Optional @ParamName("contentOutlineColor") RGBA contentOutlineColor);

	/**
	 * Hides any highlight.
	 */
	void hideHighlight();

	/**
	 * For testing.
	 */
	@Returns("highlight")
	Map<String, Object> getHighlightObjectForTest(@ParamName("nodeId") Integer nodeId);

	/**
	 * Fired when the node should be highlighted. This happens after call to <code>setInspectMode</code>.
	 */
	@EventName("nodeHighlightRequested")
	EventListener onNodeHighlightRequested(EventHandler<NodeHighlightRequested> eventListener);

	/**
	 * Fired when the node should be inspected. This happens after call to <code>setInspectMode</code> or when user manually inspects an element.
	 */
	@EventName("inspectNodeRequested")
	EventListener onInspectNodeRequested(EventHandler<InspectNodeRequested> eventListener);

	/**
	 * Fired when user asks to capture screenshot of some area on the page.
	 */
	@EventName("screenshotRequested")
	EventListener onScreenshotRequested(EventHandler<ScreenshotRequested> eventListener);
}
