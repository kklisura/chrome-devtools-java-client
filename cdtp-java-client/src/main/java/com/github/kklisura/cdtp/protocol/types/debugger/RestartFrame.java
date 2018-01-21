package com.github.kklisura.cdtp.protocol.types.debugger;

import com.github.kklisura.cdtp.protocol.annotations.Optional;
import com.github.kklisura.cdtp.protocol.types.runtime.StackTrace;
import java.util.List;

public class RestartFrame {

	private List<CallFrame> callFrames;

	@Optional
	private StackTrace asyncStackTrace;

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
}
