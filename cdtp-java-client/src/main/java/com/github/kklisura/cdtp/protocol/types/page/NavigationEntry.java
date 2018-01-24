package com.github.kklisura.cdtp.protocol.types.page;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;

/**
 * Navigation history entry.
 */
@Experimental
public class NavigationEntry {

	private Integer id;

	private String url;

	private String userTypedURL;

	private String title;

	private TransitionType transitionType;

	/**
	 * Unique id of the navigation history entry.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Unique id of the navigation history entry.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * URL of the navigation history entry.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * URL of the navigation history entry.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * URL that the user typed in the url bar.
	 */
	public String getUserTypedURL() {
		return userTypedURL;
	}

	/**
	 * URL that the user typed in the url bar.
	 */
	public void setUserTypedURL(String userTypedURL) {
		this.userTypedURL = userTypedURL;
	}

	/**
	 * Title of the navigation history entry.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Title of the navigation history entry.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Transition type.
	 */
	public TransitionType getTransitionType() {
		return transitionType;
	}

	/**
	 * Transition type.
	 */
	public void setTransitionType(TransitionType transitionType) {
		this.transitionType = transitionType;
	}
}
