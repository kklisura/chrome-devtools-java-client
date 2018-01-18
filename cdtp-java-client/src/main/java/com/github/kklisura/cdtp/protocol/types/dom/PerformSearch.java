package com.github.kklisura.cdtp.protocol.types.dom;

public class PerformSearch {

	private String searchId;

	private Integer resultCount;

	/**
	 * Unique search session identifier.
	 */
	public String getSearchId() {
		return searchId;
	}

	/**
	 * Unique search session identifier.
	 */
	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}

	/**
	 * Number of search results.
	 */
	public Integer getResultCount() {
		return resultCount;
	}

	/**
	 * Number of search results.
	 */
	public void setResultCount(Integer resultCount) {
		this.resultCount = resultCount;
	}
}
