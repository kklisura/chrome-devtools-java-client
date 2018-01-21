package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.annotations.Optional;
import com.github.kklisura.cdtp.protocol.types.runtime.AwaitPromise;
import com.github.kklisura.cdtp.protocol.types.runtime.CallArgument;
import com.github.kklisura.cdtp.protocol.types.runtime.CallFunctionOn;
import com.github.kklisura.cdtp.protocol.types.runtime.CompileScript;
import com.github.kklisura.cdtp.protocol.types.runtime.Evaluate;
import com.github.kklisura.cdtp.protocol.types.runtime.Properties;
import com.github.kklisura.cdtp.protocol.types.runtime.RemoteObject;
import com.github.kklisura.cdtp.protocol.types.runtime.RunScript;
import java.util.List;

/**
 * Runtime domain exposes JavaScript runtime by means of remote evaluation and mirror objects. Evaluation results are returned as mirror object that expose object type, string representation and unique identifier that can be used for further object reference. Original objects are maintained in memory unless they are either explicitly released or are released along with the other objects in their object group.
 */
public interface Runtime {

	/**
	 * Evaluates expression on global object.
	 */
	Evaluate evaluate(String expression, @Optional String objectGroup, @Optional Boolean includeCommandLineAPI, @Optional Boolean silent, @Optional Integer contextId, @Optional Boolean returnByValue, @Experimental @Optional Boolean generatePreview, @Experimental @Optional Boolean userGesture, @Optional Boolean awaitPromise);

	/**
	 * Add handler to promise with given promise object id.
	 */
	AwaitPromise awaitPromise(String promiseObjectId, @Optional Boolean returnByValue, @Optional Boolean generatePreview);

	/**
	 * Calls function with given declaration on the given object. Object group of the result is inherited from the target object.
	 */
	CallFunctionOn callFunctionOn(String functionDeclaration, @Optional String objectId, @Optional List<CallArgument> arguments, @Optional Boolean silent, @Optional Boolean returnByValue, @Experimental @Optional Boolean generatePreview, @Experimental @Optional Boolean userGesture, @Optional Boolean awaitPromise, @Optional Integer executionContextId, @Optional String objectGroup);

	/**
	 * Returns properties of a given object. Object group of the result is inherited from the target object.
	 */
	Properties getProperties(String objectId, @Optional Boolean ownProperties, @Experimental @Optional Boolean accessorPropertiesOnly, @Experimental @Optional Boolean generatePreview);

	/**
	 * Releases remote object with given id.
	 */
	void releaseObject(String objectId);

	/**
	 * Releases all remote objects that belong to a given group.
	 */
	void releaseObjectGroup(String objectGroup);

	/**
	 * Tells inspected instance to run if it was waiting for debugger to attach.
	 */
	void runIfWaitingForDebugger();

	/**
	 * Enables reporting of execution contexts creation by means of <code>executionContextCreated</code> event. When the reporting gets enabled the event will be sent immediately for each existing execution context.
	 */
	void enable();

	/**
	 * Disables reporting of execution contexts creation.
	 */
	void disable();

	/**
	 * Discards collected exceptions and console API calls.
	 */
	void discardConsoleEntries();

	@Experimental
	void setCustomObjectFormatterEnabled(Boolean enabled);

	/**
	 * Compiles expression.
	 */
	CompileScript compileScript(String expression, String sourceURL, Boolean persistScript, @Optional Integer executionContextId);

	/**
	 * Runs script with given id in a given context.
	 */
	RunScript runScript(String scriptId, @Optional Integer executionContextId, @Optional String objectGroup, @Optional Boolean silent, @Optional Boolean includeCommandLineAPI, @Optional Boolean returnByValue, @Optional Boolean generatePreview, @Optional Boolean awaitPromise);

	@Experimental
	RemoteObject queryObjects(String prototypeObjectId);
}
