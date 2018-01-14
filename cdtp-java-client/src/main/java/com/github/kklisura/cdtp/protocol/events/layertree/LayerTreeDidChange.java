package com.github.kklisura.cdtp.protocol.events.layertree;

import com.github.kklisura.cdtp.protocol.types.layertree.Layer;
import java.util.List;
import com.github.kklisura.cdtp.protocol.annotations.Optional;

public class LayerTreeDidChange {

	@Optional
	private List<Layer> layers;

	/**
	 * Layer tree, absent if not in the comspositing mode.
	 */
	public List<Layer> getLayers() {
		return layers;
	}

	/**
	 * Layer tree, absent if not in the comspositing mode.
	 */
	public void setLayers(List<Layer> layers) {
		this.layers = layers;
	}
}
