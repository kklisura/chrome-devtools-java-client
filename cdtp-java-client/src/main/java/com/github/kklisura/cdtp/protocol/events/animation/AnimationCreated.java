package com.github.kklisura.cdtp.protocol.events.animation;

/**
 * Event for each animation that has been created.
 */
public class AnimationCreated {

	private String id;

	/**
	 * Id of the animation that was created.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Id of the animation that was created.
	 */
	public void setId(String id) {
		this.id = id;
	}
}
