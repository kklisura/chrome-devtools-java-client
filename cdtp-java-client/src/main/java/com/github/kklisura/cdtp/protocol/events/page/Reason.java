package com.github.kklisura.cdtp.protocol.events.page;

/**
 * The reason for the navigation.
 */
public enum Reason {

	FORM_SUBMISSION("formSubmission"), HTTP_HEADER_REFRESH("httpHeaderRefresh"), SCRIPT_INITIATED("scriptInitiated"), META_TAG_REFRESH("metaTagRefresh"), PAGE_BLOCK_INTERSTITIAL("pageBlockInterstitial"), RELOAD("reload");

	final String propertyName;

	Reason(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
