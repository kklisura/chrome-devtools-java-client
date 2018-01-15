package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.types.debugger.Location;
import com.github.kklisura.cdtp.protocol.types.debugger.TargetCallFrames;
import com.github.kklisura.cdtp.protocol.annotations.Optional;
import com.github.kklisura.cdtp.protocol.types.debugger.EvaluateOnCallFrame;
import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.types.debugger.BreakLocation;
import java.util.List;
import com.github.kklisura.cdtp.protocol.types.runtime.StackTraceId;
import com.github.kklisura.cdtp.protocol.types.runtime.StackTrace;
import com.github.kklisura.cdtp.protocol.types.debugger.RestartFrame;
import com.github.kklisura.cdtp.protocol.types.debugger.SearchMatch;
import com.github.kklisura.cdtp.protocol.types.debugger.ScriptPosition;
import com.github.kklisura.cdtp.protocol.types.debugger.SetBreakpoint;
import com.github.kklisura.cdtp.protocol.types.debugger.SetBreakpointByUrl;
import com.github.kklisura.cdtp.protocol.types.debugger.State;
import com.github.kklisura.cdtp.protocol.types.runtime.CallArgument;
import com.github.kklisura.cdtp.protocol.types.debugger.SetScriptSource;

/**
 * Debugger domain exposes JavaScript debugging capabilities. It allows setting and removing
 * breakpoints, stepping through execution, exploring stack traces, etc.
 */
public interface Debugger {

	/**
	 * Continues execution until specific location is reached.
	 */
	void continueToLocation(Location location, @Optional TargetCallFrames targetCallFrames);

	/**
	 * Disables debugger for given page.
	 */
	void disable();

	/**
	 * Enables debugger for the given page. Clients should not assume that the debugging has been
	 * enabled until the result for this command is received.
	 */
	String enable();

	/**
	 * Evaluates expression on a given call frame.
	 */
	EvaluateOnCallFrame evaluateOnCallFrame(String callFrameId, String expression, @Optional String objectGroup, @Optional Boolean includeCommandLineAPI, @Optional Boolean silent, @Optional Boolean returnByValue, @Experimental @Optional Boolean generatePreview, @Optional Boolean throwOnSideEffect);

	/**
	 * Returns possible locations for breakpoint. scriptId in start and end range locations should be
	 * the same.
	 */
	List<BreakLocation> getPossibleBreakpoints(Location start, @Optional Location end, @Optional Boolean restrictToFunction);

	/**
	 * Returns source for the script with given id.
	 */
	String getScriptSource(String scriptId);

	/**
	 * Returns stack trace with given `stackTraceId`.
	 */
	@Experimental
	StackTrace getStackTrace(StackTraceId stackTraceId);

	/**
	 * Stops on the next JavaScript statement.
	 */
	void pause();

	@Experimental
	void pauseOnAsyncCall(StackTraceId parentStackTraceId);

	/**
	 * Removes JavaScript breakpoint.
	 */
	void removeBreakpoint(String breakpointId);

	/**
	 * Restarts particular call frame from the beginning.
	 */
	RestartFrame restartFrame(String callFrameId);

	/**
	 * Resumes JavaScript execution.
	 */
	void resume();

	/**
	 * This method is deprecated - use Debugger.stepInto with breakOnAsyncCall and
	 * Debugger.pauseOnAsyncTask instead. Steps into next scheduled async task if any is scheduled
	 * before next pause. Returns success when async task is actually scheduled, returns error if no
	 * task were scheduled or another scheduleStepIntoAsync was called.
	 */
	@Experimental
	void scheduleStepIntoAsync();

	/**
	 * Searches for given string in script content.
	 */
	List<SearchMatch> searchInContent(String scriptId, String query, @Optional Boolean caseSensitive, @Optional Boolean isRegex);

	/**
	 * Enables or disables async call stacks tracking.
	 */
	void setAsyncCallStackDepth(Integer maxDepth);

	/**
	 * Replace previous blackbox patterns with passed ones. Forces backend to skip stepping/pausing in
	 * scripts with url matching one of the patterns. VM will try to leave blackboxed script by
	 * performing 'step in' several times, finally resorting to 'step out' if unsuccessful.
	 */
	@Experimental
	void setBlackboxPatterns(List<String> patterns);

	/**
	 * Makes backend skip steps in the script in blackboxed ranges. VM will try leave blacklisted
	 * scripts by performing 'step in' several times, finally resorting to 'step out' if unsuccessful.
	 * Positions array contains positions where blackbox state is changed. First interval isn't
	 * blackboxed. Array should be sorted.
	 */
	@Experimental
	void setBlackboxedRanges(String scriptId, List<ScriptPosition> positions);

	/**
	 * Sets JavaScript breakpoint at a given location.
	 */
	SetBreakpoint setBreakpoint(Location location, @Optional String condition);

	/**
	 * Sets JavaScript breakpoint at given location specified either by URL or URL regex. Once this
	 * command is issued, all existing parsed scripts will have breakpoints resolved and returned in
	 * `locations` property. Further matching script parsing will result in subsequent
	 * `breakpointResolved` events issued. This logical breakpoint will survive page reloads.
	 */
	SetBreakpointByUrl setBreakpointByUrl(Integer lineNumber, @Optional String url, @Optional String urlRegex, @Optional String scriptHash, @Optional Integer columnNumber, @Optional String condition);

	/**
	 * Activates / deactivates all breakpoints on the page.
	 */
	void setBreakpointsActive(Boolean active);

	/**
	 * Defines pause on exceptions state. Can be set to stop on all exceptions, uncaught exceptions or
	 * no exceptions. Initial pause on exceptions state is `none`.
	 */
	void setPauseOnExceptions(State state);

	/**
	 * Changes return value in top frame. Available only at return break position.
	 */
	@Experimental
	void setReturnValue(CallArgument newValue);

	/**
	 * Edits JavaScript source live.
	 */
	SetScriptSource setScriptSource(String scriptId, String scriptSource, @Optional Boolean dryRun);

	/**
	 * Makes page not interrupt on any pauses (breakpoint, exception, dom exception etc).
	 */
	void setSkipAllPauses(Boolean skip);

	/**
	 * Changes value of variable in a callframe. Object-based scopes are not supported and must be
	 * mutated manually.
	 */
	void setVariableValue(Integer scopeNumber, String variableName, CallArgument newValue, String callFrameId);

	/**
	 * Steps into the function call.
	 */
	void stepInto(@Experimental @Optional Boolean breakOnAsyncCall);

	/**
	 * Steps out of the function call.
	 */
	void stepOut();

	/**
	 * Steps over the statement.
	 */
	void stepOver();
}
