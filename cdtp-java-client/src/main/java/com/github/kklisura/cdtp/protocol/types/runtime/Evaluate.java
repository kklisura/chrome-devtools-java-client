package com.github.kklisura.cdtp.protocol.types.runtime;

import com.github.kklisura.cdtp.protocol.annotations.Optional;

public class Evaluate {

	private RemoteObject result;

	@Optional
	private ExceptionDetails exceptionDetails;

	/**
	 * Evaluation result.
	 */
	public RemoteObject getResult() {
		return result;
	}

	/**
	 * Evaluation result.
	 */
	public void setResult(RemoteObject result) {
		this.result = result;
	}

	/**
	 * Exception details.
	 */
	public ExceptionDetails getExceptionDetails() {
		return exceptionDetails;
	}

	/**
	 * Exception details.
	 */
	public void setExceptionDetails(ExceptionDetails exceptionDetails) {
		this.exceptionDetails = exceptionDetails;
	}
}
