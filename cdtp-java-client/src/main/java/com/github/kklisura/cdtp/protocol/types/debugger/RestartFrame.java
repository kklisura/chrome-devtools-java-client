package com.github.kklisura.cdtp.protocol.types.debugger;

import java.util.List;
import com.github.kklisura.cdtp.protocol.types.runtime.StackTrace;
import com.github.kklisura.cdtp.protocol.annotations.Optional;
import com.github.kklisura.cdtp.protocol.types.runtime.StackTraceId;
import com.github.kklisura.cdtp.protocol.annotations.Experimental;

public class RestartFrame {

	private List<CallFrame> callFrames;

	@Optional
	private StackTrace asyncStackTrace;

	@Experimental
	@Optional
	private StackTraceId asyncStackTraceId;

	/**
	 * New stack trace.
	 */
	public List<CallFrame> getCallFrames() {
		return callFrames;
	}

	/**
	 * New stack trace.
	 */
	public void setCallFrames(List<CallFrame> callFrames) {
		this.callFrames = callFrames;
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
}
