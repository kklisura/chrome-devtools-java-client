package com.github.kklisura.cdtp.protocol.types.page;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.Optional;

/**
 * Information about the Frame on the page.
 */
public class Frame {

	private String id;

	@Optional
	private String parentId;

	private String loaderId;

	@Optional
	private String name;

	private String url;

	private String securityOrigin;

	private String mimeType;

	@Experimental
	@Optional
	private String unreachableUrl;

	/**
	 * Frame unique identifier.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Frame unique identifier.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Parent frame identifier.
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * Parent frame identifier.
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * Identifier of the loader associated with this frame.
	 */
	public String getLoaderId() {
		return loaderId;
	}

	/**
	 * Identifier of the loader associated with this frame.
	 */
	public void setLoaderId(String loaderId) {
		this.loaderId = loaderId;
	}

	/**
	 * Frame's name as specified in the tag.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Frame's name as specified in the tag.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Frame document's URL.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Frame document's URL.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Frame document's security origin.
	 */
	public String getSecurityOrigin() {
		return securityOrigin;
	}

	/**
	 * Frame document's security origin.
	 */
	public void setSecurityOrigin(String securityOrigin) {
		this.securityOrigin = securityOrigin;
	}

	/**
	 * Frame document's mimeType as determined by the browser.
	 */
	public String getMimeType() {
		return mimeType;
	}

	/**
	 * Frame document's mimeType as determined by the browser.
	 */
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	/**
	 * If the frame failed to load, this contains the URL that could not be loaded.
	 */
	public String getUnreachableUrl() {
		return unreachableUrl;
	}

	/**
	 * If the frame failed to load, this contains the URL that could not be loaded.
	 */
	public void setUnreachableUrl(String unreachableUrl) {
		this.unreachableUrl = unreachableUrl;
	}
}
