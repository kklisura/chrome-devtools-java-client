package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.events.layertree.LayerPainted;
import com.github.kklisura.cdtp.protocol.events.layertree.LayerTreeDidChange;
import com.github.kklisura.cdtp.protocol.support.annotations.EventName;
import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import com.github.kklisura.cdtp.protocol.support.annotations.ParamName;
import com.github.kklisura.cdtp.protocol.support.annotations.Returns;
import com.github.kklisura.cdtp.protocol.support.types.EventHandler;
import com.github.kklisura.cdtp.protocol.support.types.EventListener;
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
	 *
	 * @param layerId The id of the layer for which we want to get the reasons it was composited.
	 */
	@Returns("compositingReasons")
	List<String> compositingReasons(@ParamName("layerId") String layerId);

	/**
	 * Returns the layer snapshot identifier.
	 *
	 * @param layerId The id of the layer.
	 */
	@Returns("snapshotId")
	String makeSnapshot(@ParamName("layerId") String layerId);

	/**
	 * Returns the snapshot identifier.
	 *
	 * @param tiles An array of tiles composing the snapshot.
	 */
	@Returns("snapshotId")
	String loadSnapshot(@ParamName("tiles") List<PictureTile> tiles);

	/**
	 * Releases layer snapshot captured by the back-end.
	 *
	 * @param snapshotId The id of the layer snapshot.
	 */
	void releaseSnapshot(@ParamName("snapshotId") String snapshotId);

	/**
	 * @param snapshotId The id of the layer snapshot.
	 */
	@Returns("timings")
	List<List<Double>> profileSnapshot(@ParamName("snapshotId") String snapshotId);

	/**
	 * @param snapshotId The id of the layer snapshot.
	 * @param minRepeatCount The maximum number of times to replay the snapshot (1, if not specified).
	 * @param minDuration The minimum duration (in seconds) to replay the snapshot.
	 * @param clipRect The clip rectangle to apply when replaying the snapshot.
	 */
	@Returns("timings")
	List<List<Double>> profileSnapshot(@ParamName("snapshotId") String snapshotId, @Optional @ParamName("minRepeatCount") Integer minRepeatCount, @Optional @ParamName("minDuration") Double minDuration, @Optional @ParamName("clipRect") Rect clipRect);

	/**
	 * Replays the layer snapshot and returns the resulting bitmap.
	 *
	 * @param snapshotId The id of the layer snapshot.
	 */
	@Returns("dataURL")
	String replaySnapshot(@ParamName("snapshotId") String snapshotId);

	/**
	 * Replays the layer snapshot and returns the resulting bitmap.
	 *
	 * @param snapshotId The id of the layer snapshot.
	 * @param fromStep The first step to replay from (replay from the very start if not specified).
	 * @param toStep The last step to replay to (replay till the end if not specified).
	 * @param scale The scale to apply while replaying (defaults to 1).
	 */
	@Returns("dataURL")
	String replaySnapshot(@ParamName("snapshotId") String snapshotId, @Optional @ParamName("fromStep") Integer fromStep, @Optional @ParamName("toStep") Integer toStep, @Optional @ParamName("scale") Double scale);

	/**
	 * Replays the layer snapshot and returns canvas log.
	 *
	 * @param snapshotId The id of the layer snapshot.
	 */
	@Returns("commandLog")
	List<Object> snapshotCommandLog(@ParamName("snapshotId") String snapshotId);

	@EventName("layerTreeDidChange")
	EventListener onLayerTreeDidChange(EventHandler<LayerTreeDidChange> eventListener);

	@EventName("layerPainted")
	EventListener onLayerPainted(EventHandler<LayerPainted> eventListener);
}
