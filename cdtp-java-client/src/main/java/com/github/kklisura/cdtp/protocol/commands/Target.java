package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.annotations.Optional;
import com.github.kklisura.cdtp.protocol.annotations.ParamName;
import com.github.kklisura.cdtp.protocol.annotations.Returns;
import com.github.kklisura.cdtp.protocol.types.target.RemoteLocation;
import com.github.kklisura.cdtp.protocol.types.target.TargetInfo;
import java.util.List;

/**
 * Supports additional targets discovery and allows to attach to them.
 */
@Experimental
public interface Target {

	/**
	 * Controls whether to discover available targets and notify via <code>targetCreated/targetInfoChanged/targetDestroyed</code> events.
	 */
	void setDiscoverTargets(@ParamName("discover") Boolean discover);

	/**
	 * Controls whether to automatically attach to new targets which are considered to be related to this one. When turned on, attaches to all existing related targets as well. When turned off, automatically detaches from all currently attached targets.
	 */
	void setAutoAttach(@ParamName("autoAttach") Boolean autoAttach, @ParamName("waitForDebuggerOnStart") Boolean waitForDebuggerOnStart);

	void setAttachToFrames(@ParamName("value") Boolean value);

	/**
	 * Enables target discovery for the specified locations, when <code>setDiscoverTargets</code> was set to <code>true</code>.
	 */
	void setRemoteLocations(@ParamName("locations") List<RemoteLocation> locations);

	/**
	 * Sends protocol message over session with given id.
	 */
	void sendMessageToTarget(@ParamName("message") String message);

	/**
	 * Sends protocol message over session with given id.
	 */
	void sendMessageToTarget(@ParamName("message") String message, @Optional @ParamName("sessionId") String sessionId, @Deprecated @Optional @ParamName("targetId") String targetId);

	/**
	 * Returns information about a target.
	 */
	@Returns("targetInfo")
	TargetInfo getTargetInfo(@ParamName("targetId") String targetId);

	/**
	 * Activates (focuses) the target.
	 */
	void activateTarget(@ParamName("targetId") String targetId);

	/**
	 * Closes the target. If the target is a page that gets closed too.
	 */
	@Returns("success")
	Boolean closeTarget(@ParamName("targetId") String targetId);

	/**
	 * Attaches to the target with given id.
	 */
	@Returns("sessionId")
	String attachToTarget(@ParamName("targetId") String targetId);

	/**
	 * Detaches session with given id.
	 */
	void detachFromTarget();

	/**
	 * Detaches session with given id.
	 */
	void detachFromTarget(@Optional @ParamName("sessionId") String sessionId, @Deprecated @Optional @ParamName("targetId") String targetId);

	/**
	 * Creates a new empty BrowserContext. Similar to an incognito profile but you can have more than one.
	 */
	@Returns("browserContextId")
	String createBrowserContext();

	/**
	 * Deletes a BrowserContext, will fail of any open page uses it.
	 */
	@Returns("success")
	Boolean disposeBrowserContext(@ParamName("browserContextId") String browserContextId);

	/**
	 * Creates a new page.
	 */
	@Returns("targetId")
	String createTarget(@ParamName("url") String url);

	/**
	 * Creates a new page.
	 */
	@Returns("targetId")
	String createTarget(@ParamName("url") String url, @Optional @ParamName("width") Integer width, @Optional @ParamName("height") Integer height, @Optional @ParamName("browserContextId") String browserContextId);

	/**
	 * Retrieves a list of available targets.
	 */
	@Returns("targetInfos")
	List<TargetInfo> getTargets();
}
