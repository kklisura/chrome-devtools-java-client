package com.github.kklisura.cdtp.protocol.types.animation;

/**
 * Animation type of <code>Animation</code>.
 */
public enum Type {

	CSS_TRANSITION("CSSTransition"), CSS_ANIMATION("CSSAnimation"), WEB_ANIMATION("WebAnimation");

	final String propertyName;

	Type(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
