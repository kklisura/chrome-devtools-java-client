package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.events.applicationcache.ApplicationCacheStatusUpdated;
import com.github.kklisura.cdtp.protocol.events.applicationcache.NetworkStateUpdated;
import com.github.kklisura.cdtp.protocol.support.annotations.EventName;
import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.ParamName;
import com.github.kklisura.cdtp.protocol.support.annotations.Returns;
import com.github.kklisura.cdtp.protocol.support.types.EventHandler;
import com.github.kklisura.cdtp.protocol.support.types.EventListener;
import com.github.kklisura.cdtp.protocol.types.applicationcache.FrameWithManifest;
import java.util.List;

@Experimental
public interface ApplicationCache {

	/**
	 * Returns array of frame identifiers with manifest urls for each frame containing a document associated with some application cache.
	 */
	@Returns("frameIds")
	List<FrameWithManifest> getFramesWithManifests();

	/**
	 * Enables application cache domain notifications.
	 */
	void enable();

	/**
	 * Returns manifest URL for document in the given frame.
	 *
	 * @param frameId Identifier of the frame containing document whose manifest is retrieved.
	 */
	@Returns("manifestURL")
	String getManifestForFrame(@ParamName("frameId") String frameId);

	/**
	 * Returns relevant application cache data for the document in given frame.
	 *
	 * @param frameId Identifier of the frame containing document whose application cache is retrieved.
	 */
	@Returns("applicationCache")
	com.github.kklisura.cdtp.protocol.types.applicationcache.ApplicationCache getApplicationCacheForFrame(@ParamName("frameId") String frameId);

	@EventName("applicationCacheStatusUpdated")
	EventListener onApplicationCacheStatusUpdated(EventHandler<ApplicationCacheStatusUpdated> eventListener);

	@EventName("networkStateUpdated")
	EventListener onNetworkStateUpdated(EventHandler<NetworkStateUpdated> eventListener);
}
