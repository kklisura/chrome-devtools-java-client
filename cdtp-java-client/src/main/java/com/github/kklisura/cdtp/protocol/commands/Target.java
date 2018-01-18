package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.types.target.RemoteLocation;
import java.util.List;
import com.github.kklisura.cdtp.protocol.annotations.Optional;
import com.github.kklisura.cdtp.protocol.types.target.TargetInfo;

/**
 * Supports additional targets discovery and allows to attach to them.
 */
@Experimental
public interface Target {

	/**
	 * Controls whether to discover available targets and notify via <code>targetCreated/targetInfoChanged/targetDestroyed</code> events.
	 */
	void setDiscoverTargets(Boolean discover);

	/**
	 * Controls whether to automatically attach to new targets which are considered to be related to this one. When turned on, attaches to all existing related targets as well. When turned off, automatically detaches from all currently attached targets.
	 */
	void setAutoAttach(Boolean autoAttach, Boolean waitForDebuggerOnStart);

	void setAttachToFrames(Boolean value);

	/**
	 * Enables target discovery for the specified locations, when <code>setDiscoverTargets</code> was set to <code>true</code>.
	 */
	void setRemoteLocations(List<RemoteLocation> locations);

	/**
	 * Sends protocol message over session with given id.
	 */
	void sendMessageToTarget(String message, @Optional String sessionId, @Deprecated @Optional String targetId);

	/**
	 * Returns information about a target.
	 */
	TargetInfo getTargetInfo(String targetId);

	/**
	 * Activates (focuses) the target.
	 */
	void activateTarget(String targetId);

	/**
	 * Closes the target. If the target is a page that gets closed too.
	 */
	Boolean closeTarget(String targetId);

	/**
	 * Attaches to the target with given id.
	 */
	String attachToTarget(String targetId);

	/**
	 * Detaches session with given id.
	 */
	void detachFromTarget(@Optional String sessionId, @Deprecated @Optional String targetId);

	/**
	 * Creates a new empty BrowserContext. Similar to an incognito profile but you can have more than one.
	 */
	String createBrowserContext();

	/**
	 * Deletes a BrowserContext, will fail of any open page uses it.
	 */
	Boolean disposeBrowserContext(String browserContextId);

	/**
	 * Creates a new page.
	 */
	String createTarget(String url, @Optional Integer width, @Optional Integer height, @Optional String browserContextId);

	/**
	 * Retrieves a list of available targets.
	 */
	List<TargetInfo> getTargets();
}
