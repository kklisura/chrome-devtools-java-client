package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.annotations.Optional;
import com.github.kklisura.cdtp.protocol.annotations.Returns;
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
	void setDOMBreakpoint(Integer nodeId, DOMBreakpointType type);

	/**
	 * Removes DOM breakpoint that was set using <code>setDOMBreakpoint</code>.
	 */
	void removeDOMBreakpoint(Integer nodeId, DOMBreakpointType type);

	/**
	 * Sets breakpoint on particular DOM event.
	 */
	void setEventListenerBreakpoint(String eventName, @Experimental @Optional String targetName);

	/**
	 * Removes breakpoint on particular DOM event.
	 */
	void removeEventListenerBreakpoint(String eventName, @Experimental @Optional String targetName);

	/**
	 * Sets breakpoint on particular native event.
	 */
	@Experimental
	void setInstrumentationBreakpoint(String eventName);

	/**
	 * Removes breakpoint on particular native event.
	 */
	@Experimental
	void removeInstrumentationBreakpoint(String eventName);

	/**
	 * Sets breakpoint on XMLHttpRequest.
	 */
	void setXHRBreakpoint(String url);

	/**
	 * Removes breakpoint from XMLHttpRequest.
	 */
	void removeXHRBreakpoint(String url);

	/**
	 * Returns event listeners of the given object.
	 */
	@Experimental
	@Returns("listeners")
	List<EventListener> getEventListeners(String objectId, @Experimental @Optional Integer depth, @Experimental @Optional Boolean pierce);
}
