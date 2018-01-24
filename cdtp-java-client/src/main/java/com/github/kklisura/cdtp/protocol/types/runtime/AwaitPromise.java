package com.github.kklisura.cdtp.protocol.types.runtime;

import com.github.kklisura.cdtp.protocol.support.annotations.Optional;

public class AwaitPromise {

	private RemoteObject result;

	@Optional
	private ExceptionDetails exceptionDetails;

	/**
	 * Promise result. Will contain rejected value if promise was rejected.
	 */
	public RemoteObject getResult() {
		return result;
	}

	/**
	 * Promise result. Will contain rejected value if promise was rejected.
	 */
	public void setResult(RemoteObject result) {
		this.result = result;
	}

	/**
	 * Exception details if stack strace is available.
	 */
	public ExceptionDetails getExceptionDetails() {
		return exceptionDetails;
	}

	/**
	 * Exception details if stack strace is available.
	 */
	public void setExceptionDetails(ExceptionDetails exceptionDetails) {
		this.exceptionDetails = exceptionDetails;
	}
}
