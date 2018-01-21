package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.annotations.Optional;
import com.github.kklisura.cdtp.protocol.annotations.ParamName;
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
	void start();

	/**
	 * Start trace events collection.
	 */
	void start(@Deprecated @Optional @ParamName("categories") String categories, @Deprecated @Optional @ParamName("options") String options, @Optional @ParamName("bufferUsageReportingInterval") Double bufferUsageReportingInterval, @Optional @ParamName("transferMode") TransferMode transferMode, @Optional @ParamName("traceConfig") TraceConfig traceConfig);

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
	void recordClockSyncMarker(@ParamName("syncId") String syncId);
}
