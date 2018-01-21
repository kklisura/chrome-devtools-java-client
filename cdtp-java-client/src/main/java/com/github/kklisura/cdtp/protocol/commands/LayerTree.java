package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.annotations.Optional;
import com.github.kklisura.cdtp.protocol.annotations.Returns;
import com.github.kklisura.cdtp.protocol.types.dom.Rect;
import com.github.kklisura.cdtp.protocol.types.layertree.PictureTile;
import java.util.List;

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
	@Returns("compositingReasons")
	List<String> compositingReasons(String layerId);

	/**
	 * Returns the layer snapshot identifier.
	 */
	@Returns("snapshotId")
	String makeSnapshot(String layerId);

	/**
	 * Returns the snapshot identifier.
	 */
	@Returns("snapshotId")
	String loadSnapshot(List<PictureTile> tiles);

	/**
	 * Releases layer snapshot captured by the back-end.
	 */
	void releaseSnapshot(String snapshotId);

	@Returns("timings")
	List<List<Double>> profileSnapshot(String snapshotId, @Optional Integer minRepeatCount, @Optional Double minDuration, @Optional Rect clipRect);

	/**
	 * Replays the layer snapshot and returns the resulting bitmap.
	 */
	@Returns("dataURL")
	String replaySnapshot(String snapshotId, @Optional Integer fromStep, @Optional Integer toStep, @Optional Double scale);

	/**
	 * Replays the layer snapshot and returns canvas log.
	 */
	@Returns("commandLog")
	List<Object> snapshotCommandLog(String snapshotId);
}
