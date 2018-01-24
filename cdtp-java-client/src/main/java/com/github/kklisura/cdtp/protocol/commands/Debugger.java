package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.events.debugger.BreakpointResolved;
import com.github.kklisura.cdtp.protocol.events.debugger.Paused;
import com.github.kklisura.cdtp.protocol.events.debugger.Resumed;
import com.github.kklisura.cdtp.protocol.events.debugger.ScriptFailedToParse;
import com.github.kklisura.cdtp.protocol.events.debugger.ScriptParsed;
import com.github.kklisura.cdtp.protocol.support.annotations.EventName;
import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import com.github.kklisura.cdtp.protocol.support.annotations.ParamName;
import com.github.kklisura.cdtp.protocol.support.annotations.Returns;
import com.github.kklisura.cdtp.protocol.support.types.EventHandler;
import com.github.kklisura.cdtp.protocol.support.types.EventListener;
import com.github.kklisura.cdtp.protocol.types.debugger.BreakLocation;
import com.github.kklisura.cdtp.protocol.types.debugger.EvaluateOnCallFrame;
import com.github.kklisura.cdtp.protocol.types.debugger.Location;
import com.github.kklisura.cdtp.protocol.types.debugger.RestartFrame;
import com.github.kklisura.cdtp.protocol.types.debugger.ScriptPosition;
import com.github.kklisura.cdtp.protocol.types.debugger.SearchMatch;
import com.github.kklisura.cdtp.protocol.types.debugger.SetBreakpoint;
import com.github.kklisura.cdtp.protocol.types.debugger.SetBreakpointByUrl;
import com.github.kklisura.cdtp.protocol.types.debugger.SetScriptSource;
import com.github.kklisura.cdtp.protocol.types.debugger.State;
import com.github.kklisura.cdtp.protocol.types.debugger.TargetCallFrames;
import com.github.kklisura.cdtp.protocol.types.runtime.CallArgument;
import java.util.List;

/**
 * Debugger domain exposes JavaScript debugging capabilities. It allows setting and removing breakpoints, stepping through execution, exploring stack traces, etc.
 */
public interface Debugger {

	/**
	 * Enables debugger for the given page. Clients should not assume that the debugging has been enabled until the result for this command is received.
	 */
	void enable();

	/**
	 * Disables debugger for given page.
	 */
	void disable();

	/**
	 * Activates / deactivates all breakpoints on the page.
	 */
	void setBreakpointsActive(@ParamName("active") Boolean active);

	/**
	 * Makes page not interrupt on any pauses (breakpoint, exception, dom exception etc).
	 */
	void setSkipAllPauses(@ParamName("skip") Boolean skip);

	/**
	 * Sets JavaScript breakpoint at given location specified either by URL or URL regex. Once this command is issued, all existing parsed scripts will have breakpoints resolved and returned in <code>locations</code> property. Further matching script parsing will result in subsequent <code>breakpointResolved</code> events issued. This logical breakpoint will survive page reloads.
	 */
	SetBreakpointByUrl setBreakpointByUrl(@ParamName("lineNumber") Integer lineNumber);

	/**
	 * Sets JavaScript breakpoint at given location specified either by URL or URL regex. Once this command is issued, all existing parsed scripts will have breakpoints resolved and returned in <code>locations</code> property. Further matching script parsing will result in subsequent <code>breakpointResolved</code> events issued. This logical breakpoint will survive page reloads.
	 */
	SetBreakpointByUrl setBreakpointByUrl(@ParamName("lineNumber") Integer lineNumber, @Optional @ParamName("url") String url, @Optional @ParamName("urlRegex") String urlRegex, @Optional @ParamName("columnNumber") Integer columnNumber, @Optional @ParamName("condition") String condition);

	/**
	 * Sets JavaScript breakpoint at a given location.
	 */
	SetBreakpoint setBreakpoint(@ParamName("location") Location location);

	/**
	 * Sets JavaScript breakpoint at a given location.
	 */
	SetBreakpoint setBreakpoint(@ParamName("location") Location location, @Optional @ParamName("condition") String condition);

	/**
	 * Removes JavaScript breakpoint.
	 */
	void removeBreakpoint(@ParamName("breakpointId") String breakpointId);

	/**
	 * Returns possible locations for breakpoint. scriptId in start and end range locations should be the same.
	 */
	@Experimental
	@Returns("locations")
	List<BreakLocation> getPossibleBreakpoints(@ParamName("start") Location start);

	/**
	 * Returns possible locations for breakpoint. scriptId in start and end range locations should be the same.
	 */
	@Experimental
	@Returns("locations")
	List<BreakLocation> getPossibleBreakpoints(@ParamName("start") Location start, @Optional @ParamName("end") Location end, @Optional @ParamName("restrictToFunction") Boolean restrictToFunction);

	/**
	 * Continues execution until specific location is reached.
	 */
	void continueToLocation(@ParamName("location") Location location);

	/**
	 * Continues execution until specific location is reached.
	 */
	void continueToLocation(@ParamName("location") Location location, @Experimental @Optional @ParamName("targetCallFrames") TargetCallFrames targetCallFrames);

	/**
	 * Steps over the statement.
	 */
	void stepOver();

	/**
	 * Steps into the function call.
	 */
	void stepInto();

	/**
	 * Steps out of the function call.
	 */
	void stepOut();

	/**
	 * Stops on the next JavaScript statement.
	 */
	void pause();

	/**
	 * Steps into next scheduled async task if any is scheduled before next pause. Returns success when async task is actually scheduled, returns error if no task were scheduled or another scheduleStepIntoAsync was called.
	 */
	@Experimental
	void scheduleStepIntoAsync();

	/**
	 * Resumes JavaScript execution.
	 */
	void resume();

	/**
	 * Searches for given string in script content.
	 */
	@Experimental
	@Returns("result")
	List<SearchMatch> searchInContent(@ParamName("scriptId") String scriptId, @ParamName("query") String query);

	/**
	 * Searches for given string in script content.
	 */
	@Experimental
	@Returns("result")
	List<SearchMatch> searchInContent(@ParamName("scriptId") String scriptId, @ParamName("query") String query, @Optional @ParamName("caseSensitive") Boolean caseSensitive, @Optional @ParamName("isRegex") Boolean isRegex);

	/**
	 * Edits JavaScript source live.
	 */
	SetScriptSource setScriptSource(@ParamName("scriptId") String scriptId, @ParamName("scriptSource") String scriptSource);

	/**
	 * Edits JavaScript source live.
	 */
	SetScriptSource setScriptSource(@ParamName("scriptId") String scriptId, @ParamName("scriptSource") String scriptSource, @Optional @ParamName("dryRun") Boolean dryRun);

	/**
	 * Restarts particular call frame from the beginning.
	 */
	RestartFrame restartFrame(@ParamName("callFrameId") String callFrameId);

	/**
	 * Returns source for the script with given id.
	 */
	@Returns("scriptSource")
	String getScriptSource(@ParamName("scriptId") String scriptId);

	/**
	 * Defines pause on exceptions state. Can be set to stop on all exceptions, uncaught exceptions or no exceptions. Initial pause on exceptions state is <code>none</code>.
	 */
	void setPauseOnExceptions(@ParamName("state") State state);

	/**
	 * Evaluates expression on a given call frame.
	 */
	EvaluateOnCallFrame evaluateOnCallFrame(@ParamName("callFrameId") String callFrameId, @ParamName("expression") String expression);

	/**
	 * Evaluates expression on a given call frame.
	 */
	EvaluateOnCallFrame evaluateOnCallFrame(@ParamName("callFrameId") String callFrameId, @ParamName("expression") String expression, @Optional @ParamName("objectGroup") String objectGroup, @Optional @ParamName("includeCommandLineAPI") Boolean includeCommandLineAPI, @Optional @ParamName("silent") Boolean silent, @Optional @ParamName("returnByValue") Boolean returnByValue, @Experimental @Optional @ParamName("generatePreview") Boolean generatePreview, @Experimental @Optional @ParamName("throwOnSideEffect") Boolean throwOnSideEffect);

	/**
	 * Changes value of variable in a callframe. Object-based scopes are not supported and must be mutated manually.
	 */
	void setVariableValue(@ParamName("scopeNumber") Integer scopeNumber, @ParamName("variableName") String variableName, @ParamName("newValue") CallArgument newValue, @ParamName("callFrameId") String callFrameId);

	/**
	 * Enables or disables async call stacks tracking.
	 */
	void setAsyncCallStackDepth(@ParamName("maxDepth") Integer maxDepth);

	/**
	 * Replace previous blackbox patterns with passed ones. Forces backend to skip stepping/pausing in scripts with url matching one of the patterns. VM will try to leave blackboxed script by performing 'step in' several times, finally resorting to 'step out' if unsuccessful.
	 */
	@Experimental
	void setBlackboxPatterns(@ParamName("patterns") List<String> patterns);

	/**
	 * Makes backend skip steps in the script in blackboxed ranges. VM will try leave blacklisted scripts by performing 'step in' several times, finally resorting to 'step out' if unsuccessful. Positions array contains positions where blackbox state is changed. First interval isn't blackboxed. Array should be sorted.
	 */
	@Experimental
	void setBlackboxedRanges(@ParamName("scriptId") String scriptId, @ParamName("positions") List<ScriptPosition> positions);

	/**
	 * Fired when virtual machine parses script. This event is also fired for all known and uncollected scripts upon enabling debugger.
	 */
	@EventName("scriptParsed")
	EventListener onScriptParsed(EventHandler<ScriptParsed> eventListener);

	/**
	 * Fired when virtual machine fails to parse the script.
	 */
	@EventName("scriptFailedToParse")
	EventListener onScriptFailedToParse(EventHandler<ScriptFailedToParse> eventListener);

	/**
	 * Fired when breakpoint is resolved to an actual script and location.
	 */
	@EventName("breakpointResolved")
	EventListener onBreakpointResolved(EventHandler<BreakpointResolved> eventListener);

	/**
	 * Fired when the virtual machine stopped on breakpoint or exception or any other stop criteria.
	 */
	@EventName("paused")
	EventListener onPaused(EventHandler<Paused> eventListener);

	/**
	 * Fired when the virtual machine resumed execution.
	 */
	@EventName("resumed")
	EventListener onResumed(EventHandler<Resumed> eventListener);
}
