package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.annotations.Optional;
import com.github.kklisura.cdtp.protocol.annotations.ParamName;
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
	List<String> compositingReasons(@ParamName("layerId") String layerId);

	/**
	 * Returns the layer snapshot identifier.
	 */
	@Returns("snapshotId")
	String makeSnapshot(@ParamName("layerId") String layerId);

	/**
	 * Returns the snapshot identifier.
	 */
	@Returns("snapshotId")
	String loadSnapshot(@ParamName("tiles") List<PictureTile> tiles);

	/**
	 * Releases layer snapshot captured by the back-end.
	 */
	void releaseSnapshot(@ParamName("snapshotId") String snapshotId);

	@Returns("timings")
	List<List<Double>> profileSnapshot(@ParamName("snapshotId") String snapshotId);

	@Returns("timings")
	List<List<Double>> profileSnapshot(@ParamName("snapshotId") String snapshotId, @Optional @ParamName("minRepeatCount") Integer minRepeatCount, @Optional @ParamName("minDuration") Double minDuration, @Optional @ParamName("clipRect") Rect clipRect);

	/**
	 * Replays the layer snapshot and returns the resulting bitmap.
	 */
	@Returns("dataURL")
	String replaySnapshot(@ParamName("snapshotId") String snapshotId);

	/**
	 * Replays the layer snapshot and returns the resulting bitmap.
	 */
	@Returns("dataURL")
	String replaySnapshot(@ParamName("snapshotId") String snapshotId, @Optional @ParamName("fromStep") Integer fromStep, @Optional @ParamName("toStep") Integer toStep, @Optional @ParamName("scale") Double scale);

	/**
	 * Replays the layer snapshot and returns canvas log.
	 */
	@Returns("commandLog")
	List<Object> snapshotCommandLog(@ParamName("snapshotId") String snapshotId);
}
