package com.github.kklisura.cdtp.protocol.types.runtime;

import com.github.kklisura.cdtp.protocol.annotations.Optional;

public class CompileScript {

	@Optional
	private String scriptId;

	@Optional
	private ExceptionDetails exceptionDetails;

	/**
	 * Id of the script.
	 */
	public String getScriptId() {
		return scriptId;
	}

	/**
	 * Id of the script.
	 */
	public void setScriptId(String scriptId) {
		this.scriptId = scriptId;
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
