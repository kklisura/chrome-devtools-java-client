package com.github.kklisura.cdtp.protocol.events.applicationcache;

public class ApplicationCacheStatusUpdated {

	private String frameId;

	private String manifestURL;

	private Integer status;

	/**
	 * Identifier of the frame containing document whose application cache updated status.
	 */
	public String getFrameId() {
		return frameId;
	}

	/**
	 * Identifier of the frame containing document whose application cache updated status.
	 */
	public void setFrameId(String frameId) {
		this.frameId = frameId;
	}

	/**
	 * Manifest URL.
	 */
	public String getManifestURL() {
		return manifestURL;
	}

	/**
	 * Manifest URL.
	 */
	public void setManifestURL(String manifestURL) {
		this.manifestURL = manifestURL;
	}

	/**
	 * Updated application cache status.
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * Updated application cache status.
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
}
