package com.github.kklisura.cdtp.protocol.types.debugger;

import com.github.kklisura.cdtp.protocol.annotations.Optional;
import com.github.kklisura.cdtp.protocol.types.runtime.ExceptionDetails;
import com.github.kklisura.cdtp.protocol.types.runtime.StackTrace;
import java.util.List;

public class SetScriptSource {

	@Optional
	private List<CallFrame> callFrames;

	@Optional
	private Boolean stackChanged;

	@Optional
	private StackTrace asyncStackTrace;

	@Optional
	private ExceptionDetails exceptionDetails;

	/**
	 * New stack trace in case editing has happened while VM was stopped.
	 */
	public List<CallFrame> getCallFrames() {
		return callFrames;
	}

	/**
	 * New stack trace in case editing has happened while VM was stopped.
	 */
	public void setCallFrames(List<CallFrame> callFrames) {
		this.callFrames = callFrames;
	}

	/**
	 * Whether current call stack  was modified after applying the changes.
	 */
	public Boolean getStackChanged() {
		return stackChanged;
	}

	/**
	 * Whether current call stack  was modified after applying the changes.
	 */
	public void setStackChanged(Boolean stackChanged) {
		this.stackChanged = stackChanged;
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
	 * Exception details if any.
	 */
	public ExceptionDetails getExceptionDetails() {
		return exceptionDetails;
	}

	/**
	 * Exception details if any.
	 */
	public void setExceptionDetails(ExceptionDetails exceptionDetails) {
		this.exceptionDetails = exceptionDetails;
	}
}
