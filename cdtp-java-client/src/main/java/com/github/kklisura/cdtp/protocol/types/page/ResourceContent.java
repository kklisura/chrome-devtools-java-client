package com.github.kklisura.cdtp.protocol.types.page;

public class ResourceContent {

	private String content;

	private Boolean base64Encoded;

	/**
	 * Resource content.
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Resource content.
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * True, if content was served as base64.
	 */
	public Boolean getBase64Encoded() {
		return base64Encoded;
	}

	/**
	 * True, if content was served as base64.
	 */
	public void setBase64Encoded(Boolean base64Encoded) {
		this.base64Encoded = base64Encoded;
	}
}
