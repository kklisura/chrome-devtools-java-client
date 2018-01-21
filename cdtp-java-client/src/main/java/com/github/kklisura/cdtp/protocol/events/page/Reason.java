package com.github.kklisura.cdtp.protocol.events.page;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The reason for the navigation.
 */
public enum Reason {

	@JsonProperty("formSubmission")
	FORM_SUBMISSION, @JsonProperty("httpHeaderRefresh")
	HTTP_HEADER_REFRESH, @JsonProperty("scriptInitiated")
	SCRIPT_INITIATED, @JsonProperty("metaTagRefresh")
	META_TAG_REFRESH, @JsonProperty("pageBlockInterstitial")
	PAGE_BLOCK_INTERSTITIAL, @JsonProperty("reload")
	RELOAD
}
