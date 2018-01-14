package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import java.util.List;
import com.github.kklisura.cdtp.protocol.types.layertree.PictureTile;
import com.github.kklisura.cdtp.protocol.types.dom.Rect;
import com.github.kklisura.cdtp.protocol.annotations.Optional;

@Experimental
public interface LayerTree {

	/**
	 * Enables compositing tree inspection.
	 */
	void enable();

	/**
	 * Disables compositing tree inspection.
	 */
	void disable();

	/**
	 * Provides the reasons why the given layer was composited.
	 */
	List<String> compositingReasons(String layerId);

	/**
	 * Returns the layer snapshot identifier.
	 */
	String makeSnapshot(String layerId);

	/**
	 * Returns the snapshot identifier.
	 */
	String loadSnapshot(List<PictureTile> tiles);

	/**
	 * Releases layer snapshot captured by the back-end.
	 */
	void releaseSnapshot(String snapshotId);

	List<List<Double>> profileSnapshot(String snapshotId, @Optional Integer minRepeatCount, @Optional Double minDuration, @Optional Rect clipRect);

	/**
	 * Replays the layer snapshot and returns the resulting bitmap.
	 */
	String replaySnapshot(String snapshotId, @Optional Integer fromStep, @Optional Integer toStep, @Optional Double scale);

	/**
	 * Replays the layer snapshot and returns canvas log.
	 */
	List<Object> snapshotCommandLog(String snapshotId);
}
