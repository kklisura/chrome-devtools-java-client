package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.annotations.Optional;
import com.github.kklisura.cdtp.protocol.annotations.ParamName;
import com.github.kklisura.cdtp.protocol.annotations.Returns;
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
	Evaluate evaluate(@ParamName("expression") String expression);

	/**
	 * Evaluates expression on global object.
	 */
	Evaluate evaluate(@ParamName("expression") String expression, @Optional @ParamName("objectGroup") String objectGroup, @Optional @ParamName("includeCommandLineAPI") Boolean includeCommandLineAPI, @Optional @ParamName("silent") Boolean silent, @Optional @ParamName("contextId") Integer contextId, @Optional @ParamName("returnByValue") Boolean returnByValue, @Experimental @Optional @ParamName("generatePreview") Boolean generatePreview, @Experimental @Optional @ParamName("userGesture") Boolean userGesture, @Optional @ParamName("awaitPromise") Boolean awaitPromise);

	/**
	 * Add handler to promise with given promise object id.
	 */
	AwaitPromise awaitPromise(@ParamName("promiseObjectId") String promiseObjectId);

	/**
	 * Add handler to promise with given promise object id.
	 */
	AwaitPromise awaitPromise(@ParamName("promiseObjectId") String promiseObjectId, @Optional @ParamName("returnByValue") Boolean returnByValue, @Optional @ParamName("generatePreview") Boolean generatePreview);

	/**
	 * Calls function with given declaration on the given object. Object group of the result is inherited from the target object.
	 */
	CallFunctionOn callFunctionOn(@ParamName("functionDeclaration") String functionDeclaration);

	/**
	 * Calls function with given declaration on the given object. Object group of the result is inherited from the target object.
	 */
	CallFunctionOn callFunctionOn(@ParamName("functionDeclaration") String functionDeclaration, @Optional @ParamName("objectId") String objectId, @Optional @ParamName("arguments") List<CallArgument> arguments, @Optional @ParamName("silent") Boolean silent, @Optional @ParamName("returnByValue") Boolean returnByValue, @Experimental @Optional @ParamName("generatePreview") Boolean generatePreview, @Experimental @Optional @ParamName("userGesture") Boolean userGesture, @Optional @ParamName("awaitPromise") Boolean awaitPromise, @Optional @ParamName("executionContextId") Integer executionContextId, @Optional @ParamName("objectGroup") String objectGroup);

	/**
	 * Returns properties of a given object. Object group of the result is inherited from the target object.
	 */
	Properties getProperties(@ParamName("objectId") String objectId);

	/**
	 * Returns properties of a given object. Object group of the result is inherited from the target object.
	 */
	Properties getProperties(@ParamName("objectId") String objectId, @Optional @ParamName("ownProperties") Boolean ownProperties, @Experimental @Optional @ParamName("accessorPropertiesOnly") Boolean accessorPropertiesOnly, @Experimental @Optional @ParamName("generatePreview") Boolean generatePreview);

	/**
	 * Releases remote object with given id.
	 */
	void releaseObject(@ParamName("objectId") String objectId);

	/**
	 * Releases all remote objects that belong to a given group.
	 */
	void releaseObjectGroup(@ParamName("objectGroup") String objectGroup);

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
	void setCustomObjectFormatterEnabled(@ParamName("enabled") Boolean enabled);

	/**
	 * Compiles expression.
	 */
	CompileScript compileScript(@ParamName("expression") String expression, @ParamName("sourceURL") String sourceURL, @ParamName("persistScript") Boolean persistScript);

	/**
	 * Compiles expression.
	 */
	CompileScript compileScript(@ParamName("expression") String expression, @ParamName("sourceURL") String sourceURL, @ParamName("persistScript") Boolean persistScript, @Optional @ParamName("executionContextId") Integer executionContextId);

	/**
	 * Runs script with given id in a given context.
	 */
	RunScript runScript(@ParamName("scriptId") String scriptId);

	/**
	 * Runs script with given id in a given context.
	 */
	RunScript runScript(@ParamName("scriptId") String scriptId, @Optional @ParamName("executionContextId") Integer executionContextId, @Optional @ParamName("objectGroup") String objectGroup, @Optional @ParamName("silent") Boolean silent, @Optional @ParamName("includeCommandLineAPI") Boolean includeCommandLineAPI, @Optional @ParamName("returnByValue") Boolean returnByValue, @Optional @ParamName("generatePreview") Boolean generatePreview, @Optional @ParamName("awaitPromise") Boolean awaitPromise);

	@Experimental
	@Returns("objects")
	RemoteObject queryObjects(@ParamName("prototypeObjectId") String prototypeObjectId);
}
