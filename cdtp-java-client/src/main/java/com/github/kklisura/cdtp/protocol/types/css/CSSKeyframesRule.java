package com.github.kklisura.cdtp.protocol.types.css;

import java.util.List;

/**
 * CSS keyframes rule representation.
 */
public class CSSKeyframesRule {

	private Value animationName;

	private List<CSSKeyframeRule> keyframes;

	/**
	 * Animation name.
	 */
	public Value getAnimationName() {
		return animationName;
	}

	/**
	 * Animation name.
	 */
	public void setAnimationName(Value animationName) {
		this.animationName = animationName;
	}

	/**
	 * List of keyframes.
	 */
	public List<CSSKeyframeRule> getKeyframes() {
		return keyframes;
	}

	/**
	 * List of keyframes.
	 */
	public void setKeyframes(List<CSSKeyframeRule> keyframes) {
		this.keyframes = keyframes;
	}
}
