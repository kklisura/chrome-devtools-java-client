package com.github.kklisura.cdtp.protocol.types.profiler;

/**
 * Coverage data for a source range.
 */
public class CoverageRange {

	private Integer startOffset;

	private Integer endOffset;

	private Integer count;

	/**
	 * JavaScript script source offset for the range start.
	 */
	public Integer getStartOffset() {
		return startOffset;
	}

	/**
	 * JavaScript script source offset for the range start.
	 */
	public void setStartOffset(Integer startOffset) {
		this.startOffset = startOffset;
	}

	/**
	 * JavaScript script source offset for the range end.
	 */
	public Integer getEndOffset() {
		return endOffset;
	}

	/**
	 * JavaScript script source offset for the range end.
	 */
	public void setEndOffset(Integer endOffset) {
		this.endOffset = endOffset;
	}

	/**
	 * Collected execution count of the source range.
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * Collected execution count of the source range.
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
}
