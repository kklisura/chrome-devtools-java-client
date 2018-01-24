package com.github.kklisura.cdtp.protocol.types.animation;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.Optional;

/**
 * Animation instance.
 */
@Experimental
public class Animation {

	private String id;

	private String name;

	@Experimental
	private Boolean pausedState;

	private String playState;

	private Double playbackRate;

	private Double startTime;

	private Double currentTime;

	private AnimationEffect source;

	private Type type;

	@Optional
	private String cssId;

	/**
	 * <code>Animation</code>'s id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * <code>Animation</code>'s id.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * <code>Animation</code>'s name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * <code>Animation</code>'s name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * <code>Animation</code>'s internal paused state.
	 */
	public Boolean getPausedState() {
		return pausedState;
	}

	/**
	 * <code>Animation</code>'s internal paused state.
	 */
	public void setPausedState(Boolean pausedState) {
		this.pausedState = pausedState;
	}

	/**
	 * <code>Animation</code>'s play state.
	 */
	public String getPlayState() {
		return playState;
	}

	/**
	 * <code>Animation</code>'s play state.
	 */
	public void setPlayState(String playState) {
		this.playState = playState;
	}

	/**
	 * <code>Animation</code>'s playback rate.
	 */
	public Double getPlaybackRate() {
		return playbackRate;
	}

	/**
	 * <code>Animation</code>'s playback rate.
	 */
	public void setPlaybackRate(Double playbackRate) {
		this.playbackRate = playbackRate;
	}

	/**
	 * <code>Animation</code>'s start time.
	 */
	public Double getStartTime() {
		return startTime;
	}

	/**
	 * <code>Animation</code>'s start time.
	 */
	public void setStartTime(Double startTime) {
		this.startTime = startTime;
	}

	/**
	 * <code>Animation</code>'s current time.
	 */
	public Double getCurrentTime() {
		return currentTime;
	}

	/**
	 * <code>Animation</code>'s current time.
	 */
	public void setCurrentTime(Double currentTime) {
		this.currentTime = currentTime;
	}

	/**
	 * <code>Animation</code>'s source animation node.
	 */
	public AnimationEffect getSource() {
		return source;
	}

	/**
	 * <code>Animation</code>'s source animation node.
	 */
	public void setSource(AnimationEffect source) {
		this.source = source;
	}

	/**
	 * Animation type of <code>Animation</code>.
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Animation type of <code>Animation</code>.
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * A unique ID for <code>Animation</code> representing the sources that triggered this CSS animation/transition.
	 */
	public String getCssId() {
		return cssId;
	}

	/**
	 * A unique ID for <code>Animation</code> representing the sources that triggered this CSS animation/transition.
	 */
	public void setCssId(String cssId) {
		this.cssId = cssId;
	}
}
