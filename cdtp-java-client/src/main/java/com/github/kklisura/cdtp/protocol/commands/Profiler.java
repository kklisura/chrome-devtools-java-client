package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.events.profiler.ConsoleProfileFinished;
import com.github.kklisura.cdtp.protocol.events.profiler.ConsoleProfileStarted;
import com.github.kklisura.cdtp.protocol.support.annotations.EventName;
import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import com.github.kklisura.cdtp.protocol.support.annotations.ParamName;
import com.github.kklisura.cdtp.protocol.support.annotations.Returns;
import com.github.kklisura.cdtp.protocol.support.types.EventHandler;
import com.github.kklisura.cdtp.protocol.support.types.EventListener;
import com.github.kklisura.cdtp.protocol.types.profiler.Profile;
import com.github.kklisura.cdtp.protocol.types.profiler.ScriptCoverage;
import com.github.kklisura.cdtp.protocol.types.profiler.ScriptTypeProfile;
import java.util.List;

public interface Profiler {

	void enable();

	void disable();

	/**
	 * Changes CPU profiler sampling interval. Must be called before CPU profiles recording started.
	 *
	 * @param interval New sampling interval in microseconds.
	 */
	void setSamplingInterval(@ParamName("interval") Integer interval);

	void start();

	@Returns("profile")
	Profile stop();

	/**
	 * Enable precise code coverage. Coverage data for JavaScript executed before enabling precise code coverage may be incomplete. Enabling prevents running optimized code and resets execution counters.
	 */
	@Experimental
	void startPreciseCoverage();

	/**
	 * Enable precise code coverage. Coverage data for JavaScript executed before enabling precise code coverage may be incomplete. Enabling prevents running optimized code and resets execution counters.
	 *
	 * @param callCount Collect accurate call counts beyond simple 'covered' or 'not covered'.
	 * @param detailed Collect block-based coverage.
	 */
	@Experimental
	void startPreciseCoverage(@Optional @ParamName("callCount") Boolean callCount, @Optional @ParamName("detailed") Boolean detailed);

	/**
	 * Disable precise code coverage. Disabling releases unnecessary execution count records and allows executing optimized code.
	 */
	@Experimental
	void stopPreciseCoverage();

	/**
	 * Collect coverage data for the current isolate, and resets execution counters. Precise code coverage needs to have started.
	 */
	@Experimental
	@Returns("result")
	List<ScriptCoverage> takePreciseCoverage();

	/**
	 * Collect coverage data for the current isolate. The coverage data may be incomplete due to garbage collection.
	 */
	@Experimental
	@Returns("result")
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
	@Returns("result")
	List<ScriptTypeProfile> takeTypeProfile();

	/**
	 * Sent when new profile recording is started using console.profile() call.
	 */
	@EventName("consoleProfileStarted")
	EventListener onConsoleProfileStarted(EventHandler<ConsoleProfileStarted> eventListener);

	@EventName("consoleProfileFinished")
	EventListener onConsoleProfileFinished(EventHandler<ConsoleProfileFinished> eventListener);
}
