package com.github.kklisura.cdtp.protocol.types.runtime;

import com.github.kklisura.cdtp.protocol.annotations.Optional;

/**
 * Detailed information about exception (or error) that was thrown during script compilation or execution.
 */
public class ExceptionDetails {

	private Integer exceptionId;

	private String text;

	private Integer lineNumber;

	private Integer columnNumber;

	@Optional
	private String scriptId;

	@Optional
	private String url;

	@Optional
	private StackTrace stackTrace;

	@Optional
	private RemoteObject exception;

	@Optional
	private Integer executionContextId;

	/**
	 * Exception id.
	 */
	public Integer getExceptionId() {
		return exceptionId;
	}

	/**
	 * Exception id.
	 */
	public void setExceptionId(Integer exceptionId) {
		this.exceptionId = exceptionId;
	}

	/**
	 * Exception text, which should be used together with exception object when available.
	 */
	public String getText() {
		return text;
	}

	/**
	 * Exception text, which should be used together with exception object when available.
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Line number of the exception location (0-based).
	 */
	public Integer getLineNumber() {
		return lineNumber;
	}

	/**
	 * Line number of the exception location (0-based).
	 */
	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}

	/**
	 * Column number of the exception location (0-based).
	 */
	public Integer getColumnNumber() {
		return columnNumber;
	}

	/**
	 * Column number of the exception location (0-based).
	 */
	public void setColumnNumber(Integer columnNumber) {
		this.columnNumber = columnNumber;
	}

	/**
	 * Script ID of the exception location.
	 */
	public String getScriptId() {
		return scriptId;
	}

	/**
	 * Script ID of the exception location.
	 */
	public void setScriptId(String scriptId) {
		this.scriptId = scriptId;
	}

	/**
	 * URL of the exception location, to be used when the script was not reported.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * URL of the exception location, to be used when the script was not reported.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * JavaScript stack trace if available.
	 */
	public StackTrace getStackTrace() {
		return stackTrace;
	}

	/**
	 * JavaScript stack trace if available.
	 */
	public void setStackTrace(StackTrace stackTrace) {
		this.stackTrace = stackTrace;
	}

	/**
	 * Exception object if available.
	 */
	public RemoteObject getException() {
		return exception;
	}

	/**
	 * Exception object if available.
	 */
	public void setException(RemoteObject exception) {
		this.exception = exception;
	}

	/**
	 * Identifier of the context where exception happened.
	 */
	public Integer getExecutionContextId() {
		return executionContextId;
	}

	/**
	 * Identifier of the context where exception happened.
	 */
	public void setExecutionContextId(Integer executionContextId) {
		this.executionContextId = executionContextId;
	}
}
