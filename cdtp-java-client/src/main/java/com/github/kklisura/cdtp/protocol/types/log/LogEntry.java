package com.github.kklisura.cdtp.protocol.types.log;

import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import com.github.kklisura.cdtp.protocol.types.runtime.StackTrace;

/**
 * Log entry.
 */
public class LogEntry {

	private Source source;

	private Level level;

	private String text;

	private Double timestamp;

	@Optional
	private String url;

	@Optional
	private Integer lineNumber;

	@Optional
	private StackTrace stackTrace;

	@Optional
	private String networkRequestId;

	@Optional
	private String workerId;

	/**
	 * Log entry source.
	 */
	public Source getSource() {
		return source;
	}

	/**
	 * Log entry source.
	 */
	public void setSource(Source source) {
		this.source = source;
	}

	/**
	 * Log entry severity.
	 */
	public Level getLevel() {
		return level;
	}

	/**
	 * Log entry severity.
	 */
	public void setLevel(Level level) {
		this.level = level;
	}

	/**
	 * Logged text.
	 */
	public String getText() {
		return text;
	}

	/**
	 * Logged text.
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Timestamp when this entry was added.
	 */
	public Double getTimestamp() {
		return timestamp;
	}

	/**
	 * Timestamp when this entry was added.
	 */
	public void setTimestamp(Double timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * URL of the resource if known.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * URL of the resource if known.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Line number in the resource.
	 */
	public Integer getLineNumber() {
		return lineNumber;
	}

	/**
	 * Line number in the resource.
	 */
	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}

	/**
	 * JavaScript stack trace.
	 */
	public StackTrace getStackTrace() {
		return stackTrace;
	}

	/**
	 * JavaScript stack trace.
	 */
	public void setStackTrace(StackTrace stackTrace) {
		this.stackTrace = stackTrace;
	}

	/**
	 * Identifier of the network request associated with this entry.
	 */
	public String getNetworkRequestId() {
		return networkRequestId;
	}

	/**
	 * Identifier of the network request associated with this entry.
	 */
	public void setNetworkRequestId(String networkRequestId) {
		this.networkRequestId = networkRequestId;
	}

	/**
	 * Identifier of the worker associated with this entry.
	 */
	public String getWorkerId() {
		return workerId;
	}

	/**
	 * Identifier of the worker associated with this entry.
	 */
	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}
}
