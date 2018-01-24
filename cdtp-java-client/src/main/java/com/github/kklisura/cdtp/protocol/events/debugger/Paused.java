package com.github.kklisura.cdtp.protocol.events.debugger;

import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import com.github.kklisura.cdtp.protocol.types.debugger.CallFrame;
import com.github.kklisura.cdtp.protocol.types.runtime.StackTrace;
import java.util.List;
import java.util.Map;

/**
 * Fired when the virtual machine stopped on breakpoint or exception or any other stop criteria.
 */
public class Paused {

	private List<CallFrame> callFrames;

	private Reason reason;

	@Optional
	private Map<String, Object> data;

	@Optional
	private List<String> hitBreakpoints;

	@Optional
	private StackTrace asyncStackTrace;

	/**
	 * Call stack the virtual machine stopped on.
	 */
	public List<CallFrame> getCallFrames() {
		return callFrames;
	}

	/**
	 * Call stack the virtual machine stopped on.
	 */
	public void setCallFrames(List<CallFrame> callFrames) {
		this.callFrames = callFrames;
	}

	/**
	 * Pause reason.
	 */
	public Reason getReason() {
		return reason;
	}

	/**
	 * Pause reason.
	 */
	public void setReason(Reason reason) {
		this.reason = reason;
	}

	/**
	 * Object containing break-specific auxiliary properties.
	 */
	public Map<String, Object> getData() {
		return data;
	}

	/**
	 * Object containing break-specific auxiliary properties.
	 */
	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	/**
	 * Hit breakpoints IDs
	 */
	public List<String> getHitBreakpoints() {
		return hitBreakpoints;
	}

	/**
	 * Hit breakpoints IDs
	 */
	public void setHitBreakpoints(List<String> hitBreakpoints) {
		this.hitBreakpoints = hitBreakpoints;
	}

	/**
	 * Async stack trace, if any.
	 */
	public StackTrace getAsyncStackTrace() {
		return asyncStackTrace;
	}

	/**
	 * Async stack trace, if any.
	 */
	public void setAsyncStackTrace(StackTrace asyncStackTrace) {
		this.asyncStackTrace = asyncStackTrace;
	}
}
