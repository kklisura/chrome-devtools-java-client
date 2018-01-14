package com.github.kklisura.cdtp.protocol.events.layertree;

import com.github.kklisura.cdtp.protocol.types.dom.Rect;

public class LayerPainted {

	private String layerId;

	private Rect clip;

	/**
	 * The id of the painted layer.
	 */
	public String getLayerId() {
		return layerId;
	}

	/**
	 * The id of the painted layer.
	 */
	public void setLayerId(String layerId) {
		this.layerId = layerId;
	}

	/**
	 * Clip rectangle.
	 */
	public Rect getClip() {
		return clip;
	}

	/**
	 * Clip rectangle.
	 */
	public void setClip(Rect clip) {
		this.clip = clip;
	}
}
