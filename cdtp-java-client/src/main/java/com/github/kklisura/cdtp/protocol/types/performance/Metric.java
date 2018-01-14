package com.github.kklisura.cdtp.protocol.types.performance;

/**
 * Run-time execution metric.
 */
public class Metric {

	private String name;

	private Double value;

	/**
	 * Metric name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Metric name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Metric value.
	 */
	public Double getValue() {
		return value;
	}

	/**
	 * Metric value.
	 */
	public void setValue(Double value) {
		this.value = value;
	}
}
