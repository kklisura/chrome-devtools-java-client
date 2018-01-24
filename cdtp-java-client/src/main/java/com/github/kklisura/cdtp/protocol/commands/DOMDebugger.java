package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import com.github.kklisura.cdtp.protocol.support.annotations.ParamName;
import com.github.kklisura.cdtp.protocol.support.annotations.Returns;
import com.github.kklisura.cdtp.protocol.types.domdebugger.DOMBreakpointType;
import com.github.kklisura.cdtp.protocol.types.domdebugger.EventListener;
import java.util.List;

/**
 * DOM debugging allows setting breakpoints on particular DOM operations and events. JavaScript execution will stop on these operations as if there was a regular breakpoint set.
 */
public interface DOMDebugger {

	/**
	 * Sets breakpoint on particular operation with DOM.
	 */
	void setDOMBreakpoint(@ParamName("nodeId") Integer nodeId, @ParamName("type") DOMBreakpointType type);

	/**
	 * Removes DOM breakpoint that was set using <code>setDOMBreakpoint</code>.
	 */
	void removeDOMBreakpoint(@ParamName("nodeId") Integer nodeId, @ParamName("type") DOMBreakpointType type);

	/**
	 * Sets breakpoint on particular DOM event.
	 */
	void setEventListenerBreakpoint(@ParamName("eventName") String eventName);

	/**
	 * Sets breakpoint on particular DOM event.
	 */
	void setEventListenerBreakpoint(@ParamName("eventName") String eventName, @Experimental @Optional @ParamName("targetName") String targetName);

	/**
	 * Removes breakpoint on particular DOM event.
	 */
	void removeEventListenerBreakpoint(@ParamName("eventName") String eventName);

	/**
	 * Removes breakpoint on particular DOM event.
	 */
	void removeEventListenerBreakpoint(@ParamName("eventName") String eventName, @Experimental @Optional @ParamName("targetName") String targetName);

	/**
	 * Sets breakpoint on particular native event.
	 */
	@Experimental
	void setInstrumentationBreakpoint(@ParamName("eventName") String eventName);

	/**
	 * Removes breakpoint on particular native event.
	 */
	@Experimental
	void removeInstrumentationBreakpoint(@ParamName("eventName") String eventName);

	/**
	 * Sets breakpoint on XMLHttpRequest.
	 */
	void setXHRBreakpoint(@ParamName("url") String url);

	/**
	 * Removes breakpoint from XMLHttpRequest.
	 */
	void removeXHRBreakpoint(@ParamName("url") String url);

	/**
	 * Returns event listeners of the given object.
	 */
	@Experimental
	@Returns("listeners")
	List<EventListener> getEventListeners(@ParamName("objectId") String objectId);

	/**
	 * Returns event listeners of the given object.
	 */
	@Experimental
	@Returns("listeners")
	List<EventListener> getEventListeners(@ParamName("objectId") String objectId, @Experimental @Optional @ParamName("depth") Integer depth, @Experimental @Optional @ParamName("pierce") Boolean pierce);
}
