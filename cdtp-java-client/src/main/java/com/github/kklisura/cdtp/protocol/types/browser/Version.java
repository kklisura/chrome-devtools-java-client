package com.github.kklisura.cdtp.protocol.types.browser;

public class Version {

	private String protocolVersion;

	private String product;

	private String revision;

	private String userAgent;

	private String jsVersion;

	/**
	 * Protocol version.
	 */
	public String getProtocolVersion() {
		return protocolVersion;
	}

	/**
	 * Protocol version.
	 */
	public void setProtocolVersion(String protocolVersion) {
		this.protocolVersion = protocolVersion;
	}

	/**
	 * Product name.
	 */
	public String getProduct() {
		return product;
	}

	/**
	 * Product name.
	 */
	public void setProduct(String product) {
		this.product = product;
	}

	/**
	 * Product revision.
	 */
	public String getRevision() {
		return revision;
	}

	/**
	 * Product revision.
	 */
	public void setRevision(String revision) {
		this.revision = revision;
	}

	/**
	 * User-Agent.
	 */
	public String getUserAgent() {
		return userAgent;
	}

	/**
	 * User-Agent.
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	/**
	 * V8 version.
	 */
	public String getJsVersion() {
		return jsVersion;
	}

	/**
	 * V8 version.
	 */
	public void setJsVersion(String jsVersion) {
		this.jsVersion = jsVersion;
	}
}
