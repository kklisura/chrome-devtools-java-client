package com.github.kklisura.cdtp.protocol.types.profiler;

/**
 * Specifies a number of samples attributed to a certain source position.
 */
public class PositionTickInfo {

	private Integer line;

	private Integer ticks;

	/**
	 * Source line number (1-based).
	 */
	public Integer getLine() {
		return line;
	}

	/**
	 * Source line number (1-based).
	 */
	public void setLine(Integer line) {
		this.line = line;
	}

	/**
	 * Number of samples attributed to the source line.
	 */
	public Integer getTicks() {
		return ticks;
	}

	/**
	 * Number of samples attributed to the source line.
	 */
	public void setTicks(Integer ticks) {
		this.ticks = ticks;
	}
}
