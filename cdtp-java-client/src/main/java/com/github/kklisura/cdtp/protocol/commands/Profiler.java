package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.types.profiler.ScriptCoverage;
import java.util.List;
import com.github.kklisura.cdtp.protocol.annotations.Optional;
import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.types.profiler.Profile;
import com.github.kklisura.cdtp.protocol.types.profiler.ScriptTypeProfile;

public interface Profiler {

	void disable();

	void enable();

	/**
	 * Collect coverage data for the current isolate. The coverage data may be incomplete due to
	 * garbage collection.
	 */
	List<ScriptCoverage> getBestEffortCoverage();

	/**
	 * Changes CPU profiler sampling interval. Must be called before CPU profiles recording started.
	 */
	void setSamplingInterval(Integer interval);

	void start();

	/**
	 * Enable precise code coverage. Coverage data for JavaScript executed before enabling precise code
	 * coverage may be incomplete. Enabling prevents running optimized code and resets execution
	 * counters.
	 */
	void startPreciseCoverage(@Optional Boolean callCount, @Optional Boolean detailed);

	/**
	 * Enable type profile.
	 */
	@Experimental
	void startTypeProfile();

	Profile stop();

	/**
	 * Disable precise code coverage. Disabling releases unnecessary execution count records and allows
	 * executing optimized code.
	 */
	void stopPreciseCoverage();

	/**
	 * Disable type profile. Disabling releases type profile data collected so far.
	 */
	@Experimental
	void stopTypeProfile();

	/**
	 * Collect coverage data for the current isolate, and resets execution counters. Precise code
	 * coverage needs to have started.
	 */
	List<ScriptCoverage> takePreciseCoverage();

	/**
	 * Collect type profile.
	 */
	@Experimental
	List<ScriptTypeProfile> takeTypeProfile();
}
