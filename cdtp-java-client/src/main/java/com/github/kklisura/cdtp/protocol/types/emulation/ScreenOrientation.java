package com.github.kklisura.cdtp.protocol.types.emulation;

/**
 * Screen orientation.
 */
public class ScreenOrientation {

	private Type type;

	private Integer angle;

	/**
	 * Orientation type.
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Orientation type.
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * Orientation angle.
	 */
	public Integer getAngle() {
		return angle;
	}

	/**
	 * Orientation angle.
	 */
	public void setAngle(Integer angle) {
		this.angle = angle;
	}
}
