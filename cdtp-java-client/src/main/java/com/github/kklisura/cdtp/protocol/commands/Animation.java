package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.events.animation.AnimationCanceled;
import com.github.kklisura.cdtp.protocol.events.animation.AnimationCreated;
import com.github.kklisura.cdtp.protocol.events.animation.AnimationStarted;
import com.github.kklisura.cdtp.protocol.support.annotations.EventName;
import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.ParamName;
import com.github.kklisura.cdtp.protocol.support.annotations.Returns;
import com.github.kklisura.cdtp.protocol.support.types.EventHandler;
import com.github.kklisura.cdtp.protocol.support.types.EventListener;
import com.github.kklisura.cdtp.protocol.types.runtime.RemoteObject;
import java.util.List;

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
	@Returns("playbackRate")
	Double getPlaybackRate();

	/**
	 * Sets the playback rate of the document timeline.
	 */
	void setPlaybackRate(@ParamName("playbackRate") Double playbackRate);

	/**
	 * Returns the current time of the an animation.
	 */
	@Returns("currentTime")
	Double getCurrentTime(@ParamName("id") String id);

	/**
	 * Sets the paused state of a set of animations.
	 */
	void setPaused(@ParamName("animations") List<String> animations, @ParamName("paused") Boolean paused);

	/**
	 * Sets the timing of an animation node.
	 */
	void setTiming(@ParamName("animationId") String animationId, @ParamName("duration") Double duration, @ParamName("delay") Double delay);

	/**
	 * Seek a set of animations to a particular time within each animation.
	 */
	void seekAnimations(@ParamName("animations") List<String> animations, @ParamName("currentTime") Double currentTime);

	/**
	 * Releases a set of animations to no longer be manipulated.
	 */
	void releaseAnimations(@ParamName("animations") List<String> animations);

	/**
	 * Gets the remote object of the Animation.
	 */
	@Returns("remoteObject")
	RemoteObject resolveAnimation(@ParamName("animationId") String animationId);

	/**
	 * Event for each animation that has been created.
	 */
	@EventName("animationCreated")
	EventListener onAnimationCreated(EventHandler<AnimationCreated> eventListener);

	/**
	 * Event for animation that has been started.
	 */
	@EventName("animationStarted")
	EventListener onAnimationStarted(EventHandler<AnimationStarted> eventListener);

	/**
	 * Event for when an animation has been cancelled.
	 */
	@EventName("animationCanceled")
	EventListener onAnimationCanceled(EventHandler<AnimationCanceled> eventListener);
}
