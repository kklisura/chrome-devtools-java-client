package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.annotations.Optional;
import com.github.kklisura.cdtp.protocol.annotations.Returns;
import com.github.kklisura.cdtp.protocol.types.dom.RGBA;
import com.github.kklisura.cdtp.protocol.types.overlay.HighlightConfig;
import com.github.kklisura.cdtp.protocol.types.overlay.InspectMode;
import java.util.List;

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
	void setShowPaintRects(Boolean result);

	/**
	 * Requests that backend shows debug borders on layers
	 */
	void setShowDebugBorders(Boolean show);

	/**
	 * Requests that backend shows the FPS counter
	 */
	void setShowFPSCounter(Boolean show);

	/**
	 * Requests that backend shows scroll bottleneck rects
	 */
	void setShowScrollBottleneckRects(Boolean show);

	/**
	 * Paints viewport size upon main frame resize.
	 */
	void setShowViewportSizeOnResize(Boolean show);

	void setPausedInDebuggerMessage(@Optional String message);

	void setSuspended(Boolean suspended);

	/**
	 * Enters the 'inspect' mode. In this mode, elements that user is hovering over are highlighted. Backend then generates 'inspectNodeRequested' event upon element selection.
	 */
	void setInspectMode(InspectMode mode, @Optional HighlightConfig highlightConfig);

	/**
	 * Highlights given rectangle. Coordinates are absolute with respect to the main frame viewport.
	 */
	void highlightRect(Integer x, Integer y, Integer width, Integer height, @Optional RGBA color, @Optional RGBA outlineColor);

	/**
	 * Highlights given quad. Coordinates are absolute with respect to the main frame viewport.
	 */
	void highlightQuad(List<Double> quad, @Optional RGBA color, @Optional RGBA outlineColor);

	/**
	 * Highlights DOM node with given id or with the given JavaScript object wrapper. Either nodeId or objectId must be specified.
	 */
	void highlightNode(HighlightConfig highlightConfig, @Optional Integer nodeId, @Optional Integer backendNodeId, @Optional String objectId);

	/**
	 * Highlights owner element of the frame with given id.
	 */
	void highlightFrame(String frameId, @Optional RGBA contentColor, @Optional RGBA contentOutlineColor);

	/**
	 * Hides any highlight.
	 */
	void hideHighlight();

	/**
	 * For testing.
	 */
	@Returns("highlight")
	Object getHighlightObjectForTest(Integer nodeId);
}
