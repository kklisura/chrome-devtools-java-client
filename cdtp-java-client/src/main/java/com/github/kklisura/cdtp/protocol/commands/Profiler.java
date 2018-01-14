package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.types.profiler.Profile;
import com.github.kklisura.cdtp.protocol.annotations.Optional;
import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.types.profiler.ScriptCoverage;
import java.util.List;
import com.github.kklisura.cdtp.protocol.types.profiler.ScriptTypeProfile;

public interface Profiler {

	void enable();

	void disable();

	/**
	 * Changes CPU profiler sampling interval. Must be called before CPU profiles recording started.
	 */
	void setSamplingInterval(Integer interval);

	void start();

	Profile stop();

	/**
	 * Enable precise code coverage. Coverage data for JavaScript executed before enabling precise code coverage may be incomplete. Enabling prevents running optimized code and resets execution counters.
	 */
	@Experimental
	void startPreciseCoverage(@Optional Boolean callCount, @Optional Boolean detailed);

	/**
	 * Disable precise code coverage. Disabling releases unnecessary execution count records and allows executing optimized code.
	 */
	@Experimental
	void stopPreciseCoverage();

	/**
	 * Collect coverage data for the current isolate, and resets execution counters. Precise code coverage needs to have started.
	 */
	@Experimental
	List<ScriptCoverage> takePreciseCoverage();

	/**
	 * Collect coverage data for the current isolate. The coverage data may be incomplete due to garbage collection.
	 */
	@Experimental
	List<ScriptCoverage> getBestEffortCoverage();

	/**
	 * Enable type profile.
	 */
	@Experimental
	void startTypeProfile();

	/**
	 * Disable type profile. Disabling releases type profile data collected so far.
	 */
	@Experimental
	void stopTypeProfile();

	/**
	 * Collect type profile.
	 */
	@Experimental
	List<ScriptTypeProfile> takeTypeProfile();
}
