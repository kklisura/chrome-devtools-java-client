package com.github.kklisura.cdtp.protocol.events.debugger;

import com.github.kklisura.cdtp.protocol.types.debugger.CallFrame;
import java.util.List;
import com.github.kklisura.cdtp.protocol.annotations.Optional;
import com.github.kklisura.cdtp.protocol.types.runtime.StackTrace;
import com.github.kklisura.cdtp.protocol.types.runtime.StackTraceId;
import com.github.kklisura.cdtp.protocol.annotations.Experimental;

/**
 * Fired when the virtual machine stopped on breakpoint or exception or any other stop criteria.
 */
public class Paused {

	private List<CallFrame> callFrames;

	private Reason reason;

	@Optional
	private Object data;

	@Optional
	private List<String> hitBreakpoints;

	@Optional
	private StackTrace asyncStackTrace;

	@Experimental
	@Optional
	private StackTraceId asyncStackTraceId;

	@Experimental
	@Optional
	private StackTraceId asyncCallStackTraceId;

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
	public Object getData() {
		return data;
	}

	/**
	 * Object containing break-specific auxiliary properties.
	 */
	public void setData(Object data) {
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

	/**
	 * Async stack trace, if any.
	 */
	public StackTraceId getAsyncStackTraceId() {
		return asyncStackTraceId;
	}

	/**
	 * Async stack trace, if any.
	 */
	public void setAsyncStackTraceId(StackTraceId asyncStackTraceId) {
		this.asyncStackTraceId = asyncStackTraceId;
	}

	/**
	 * Just scheduled async call will have this stack trace as parent stack during async execution.
	 * This field is available only after `Debugger.stepInto` call with `breakOnAsynCall` flag.
	 */
	public StackTraceId getAsyncCallStackTraceId() {
		return asyncCallStackTraceId;
	}

	/**
	 * Just scheduled async call will have this stack trace as parent stack during async execution.
	 * This field is available only after `Debugger.stepInto` call with `breakOnAsynCall` flag.
	 */
	public void setAsyncCallStackTraceId(StackTraceId asyncCallStackTraceId) {
		this.asyncCallStackTraceId = asyncCallStackTraceId;
	}
}
