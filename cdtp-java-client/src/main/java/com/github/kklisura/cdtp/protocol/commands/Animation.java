package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import java.util.List;
import com.github.kklisura.cdtp.protocol.types.runtime.RemoteObject;

@Experimental
public interface Animation {

	/**
	 * Enables animation domain notifications.
	 */
	void enable();

	/**
	 * Disables animation domain notifications.
	 */
	void disable();

	/**
	 * Gets the playback rate of the document timeline.
	 */
	Double getPlaybackRate();

	/**
	 * Sets the playback rate of the document timeline.
	 */
	void setPlaybackRate(Double playbackRate);

	/**
	 * Returns the current time of the an animation.
	 */
	Double getCurrentTime(String id);

	/**
	 * Sets the paused state of a set of animations.
	 */
	void setPaused(List<String> animations, Boolean paused);

	/**
	 * Sets the timing of an animation node.
	 */
	void setTiming(String animationId, Double duration, Double delay);

	/**
	 * Seek a set of animations to a particular time within each animation.
	 */
	void seekAnimations(List<String> animations, Double currentTime);

	/**
	 * Releases a set of animations to no longer be manipulated.
	 */
	void releaseAnimations(List<String> animations);

	/**
	 * Gets the remote object of the Animation.
	 */
	RemoteObject resolveAnimation(String animationId);
}
