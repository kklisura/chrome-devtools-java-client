package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.annotations.Optional;
import com.github.kklisura.cdtp.protocol.annotations.Returns;
import com.github.kklisura.cdtp.protocol.types.tracing.RequestMemoryDump;
import com.github.kklisura.cdtp.protocol.types.tracing.TraceConfig;
import com.github.kklisura.cdtp.protocol.types.tracing.TransferMode;
import java.util.List;

@Experimental
public interface Tracing {

	/**
	 * Start trace events collection.
	 */
	void start(@Deprecated @Optional String categories, @Deprecated @Optional String options, @Optional Double bufferUsageReportingInterval, @Optional TransferMode transferMode, @Optional TraceConfig traceConfig);

	/**
	 * Stop trace events collection.
	 */
	void end();

	/**
	 * Gets supported tracing categories.
	 */
	@Returns("categories")
	List<String> getCategories();

	/**
	 * Request a global memory dump.
	 */
	RequestMemoryDump requestMemoryDump();

	/**
	 * Record a clock sync marker in the trace.
	 */
	void recordClockSyncMarker(String syncId);
}
