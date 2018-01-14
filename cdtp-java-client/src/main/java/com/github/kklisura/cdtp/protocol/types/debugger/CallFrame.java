package com.github.kklisura.cdtp.protocol.types.debugger;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.annotations.Optional;
import java.util.List;
import com.github.kklisura.cdtp.protocol.types.runtime.RemoteObject;

/**
 * JavaScript call frame. Array of call frames form the call stack.
 */
public class CallFrame {

	private String callFrameId;

	private String functionName;

	@Experimental
	@Optional
	private Location functionLocation;

	private Location location;

	private String url;

	private List<Scope> scopeChain;

	private RemoteObject that;

	@Optional
	private RemoteObject returnValue;

	/**
	 * Call frame identifier. This identifier is only valid while the virtual machine is paused.
	 */
	public String getCallFrameId() {
		return callFrameId;
	}

	/**
	 * Call frame identifier. This identifier is only valid while the virtual machine is paused.
	 */
	public void setCallFrameId(String callFrameId) {
		this.callFrameId = callFrameId;
	}

	/**
	 * Name of the JavaScript function called on this call frame.
	 */
	public String getFunctionName() {
		return functionName;
	}

	/**
	 * Name of the JavaScript function called on this call frame.
	 */
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	/**
	 * Location in the source code.
	 */
	public Location getFunctionLocation() {
		return functionLocation;
	}

	/**
	 * Location in the source code.
	 */
	public void setFunctionLocation(Location functionLocation) {
		this.functionLocation = functionLocation;
	}

	/**
	 * Location in the source code.
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * Location in the source code.
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * JavaScript script name or url.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * JavaScript script name or url.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Scope chain for this call frame.
	 */
	public List<Scope> getScopeChain() {
		return scopeChain;
	}

	/**
	 * Scope chain for this call frame.
	 */
	public void setScopeChain(List<Scope> scopeChain) {
		this.scopeChain = scopeChain;
	}

	/**
	 * <code>this</code> object for this call frame.
	 */
	public RemoteObject getThat() {
		return that;
	}

	/**
	 * <code>this</code> object for this call frame.
	 */
	public void setThat(RemoteObject that) {
		this.that = that;
	}

	/**
	 * The value being returned, if the function is at return point.
	 */
	public RemoteObject getReturnValue() {
		return returnValue;
	}

	/**
	 * The value being returned, if the function is at return point.
	 */
	public void setReturnValue(RemoteObject returnValue) {
		this.returnValue = returnValue;
	}
}
