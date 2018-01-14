package com.github.kklisura.cdtp.protocol.types.applicationcache;

/**
 * Frame identifier - manifest URL pair.
 */
public class FrameWithManifest {

	private String frameId;

	private String manifestURL;

	private Integer status;

	/**
	 * Frame identifier.
	 */
	public String getFrameId() {
		return frameId;
	}

	/**
	 * Frame identifier.
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
	 * Application cache status.
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * Application cache status.
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
}
