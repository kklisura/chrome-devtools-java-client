package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.annotations.Returns;
import com.github.kklisura.cdtp.protocol.types.performance.Metric;
import java.util.List;

@Experimental
public interface Performance {

	/**
	 * Enable collecting and reporting metrics.
	 */
	void enable();

	/**
	 * Disable collecting and reporting metrics.
	 */
	void disable();

	/**
	 * Retrieve current values of run-time metrics.
	 */
	@Returns("metrics")
	List<Metric> getMetrics();
}
