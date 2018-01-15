package com.github.kklisura.cdtp.protocol.types.runtime;

import com.github.kklisura.cdtp.protocol.annotations.Optional;
import java.util.List;
import com.github.kklisura.cdtp.protocol.annotations.Experimental;

/**
 * Call frames for assertions or error messages.
 */
public class StackTrace {

	@Optional
	private String description;

	private List<CallFrame> callFrames;

	@Optional
	private com.github.kklisura.cdtp.protocol.types.runtime.StackTrace parent;

	@Experimental
	@Optional
	private StackTraceId parentId;

	/**
	 * String label of this stack trace. For async traces this may be a name of the function that
	 * initiated the async call.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * String label of this stack trace. For async traces this may be a name of the function that
	 * initiated the async call.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * JavaScript function name.
	 */
	public List<CallFrame> getCallFrames() {
		return callFrames;
	}

	/**
	 * JavaScript function name.
	 */
	public void setCallFrames(List<CallFrame> callFrames) {
		this.callFrames = callFrames;
	}

	/**
	 * Asynchronous JavaScript stack trace that preceded this stack, if available.
	 */
	public com.github.kklisura.cdtp.protocol.types.runtime.StackTrace getParent() {
		return parent;
	}

	/**
	 * Asynchronous JavaScript stack trace that preceded this stack, if available.
	 */
	public void setParent(com.github.kklisura.cdtp.protocol.types.runtime.StackTrace parent) {
		this.parent = parent;
	}

	/**
	 * Asynchronous JavaScript stack trace that preceded this stack, if available.
	 */
	public StackTraceId getParentId() {
		return parentId;
	}

	/**
	 * Asynchronous JavaScript stack trace that preceded this stack, if available.
	 */
	public void setParentId(StackTraceId parentId) {
		this.parentId = parentId;
	}
}
