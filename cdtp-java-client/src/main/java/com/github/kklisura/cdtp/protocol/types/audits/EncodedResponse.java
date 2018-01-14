package com.github.kklisura.cdtp.protocol.types.audits;

import com.github.kklisura.cdtp.protocol.annotations.Optional;

public class EncodedResponse {

	@Optional
	private String body;

	private Integer originalSize;

	private Integer encodedSize;

	/**
	 * The encoded body as a base64 string. Omitted if sizeOnly is true.
	 */
	public String getBody() {
		return body;
	}

	/**
	 * The encoded body as a base64 string. Omitted if sizeOnly is true.
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * Size before re-encoding.
	 */
	public Integer getOriginalSize() {
		return originalSize;
	}

	/**
	 * Size before re-encoding.
	 */
	public void setOriginalSize(Integer originalSize) {
		this.originalSize = originalSize;
	}

	/**
	 * Size after re-encoding.
	 */
	public Integer getEncodedSize() {
		return encodedSize;
	}

	/**
	 * Size after re-encoding.
	 */
	public void setEncodedSize(Integer encodedSize) {
		this.encodedSize = encodedSize;
	}
}
