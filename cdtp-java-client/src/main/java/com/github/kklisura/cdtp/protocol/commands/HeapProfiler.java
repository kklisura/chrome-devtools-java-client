package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.events.heapprofiler.AddHeapSnapshotChunk;
import com.github.kklisura.cdtp.protocol.events.heapprofiler.HeapStatsUpdate;
import com.github.kklisura.cdtp.protocol.events.heapprofiler.LastSeenObjectId;
import com.github.kklisura.cdtp.protocol.events.heapprofiler.ReportHeapSnapshotProgress;
import com.github.kklisura.cdtp.protocol.events.heapprofiler.ResetProfiles;
import com.github.kklisura.cdtp.protocol.support.annotations.EventName;
import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import com.github.kklisura.cdtp.protocol.support.annotations.ParamName;
import com.github.kklisura.cdtp.protocol.support.annotations.Returns;
import com.github.kklisura.cdtp.protocol.support.types.EventHandler;
import com.github.kklisura.cdtp.protocol.support.types.EventListener;
import com.github.kklisura.cdtp.protocol.types.heapprofiler.SamplingHeapProfile;
import com.github.kklisura.cdtp.protocol.types.runtime.RemoteObject;

@Experimental
public interface HeapProfiler {

	void enable();

	void disable();

	void startTrackingHeapObjects();

	void startTrackingHeapObjects(@Optional @ParamName("trackAllocations") Boolean trackAllocations);

	void stopTrackingHeapObjects();

	void stopTrackingHeapObjects(@Optional @ParamName("reportProgress") Boolean reportProgress);

	void takeHeapSnapshot();

	void takeHeapSnapshot(@Optional @ParamName("reportProgress") Boolean reportProgress);

	void collectGarbage();

	@Returns("result")
	RemoteObject getObjectByHeapObjectId(@ParamName("objectId") String objectId);

	@Returns("result")
	RemoteObject getObjectByHeapObjectId(@ParamName("objectId") String objectId, @Optional @ParamName("objectGroup") String objectGroup);

	/**
	 * Enables console to refer to the node with given id via $x (see Command Line API for more details $x functions).
	 */
	void addInspectedHeapObject(@ParamName("heapObjectId") String heapObjectId);

	@Returns("heapSnapshotObjectId")
	String getHeapObjectId(@ParamName("objectId") String objectId);

	void startSampling();

	void startSampling(@Optional @ParamName("samplingInterval") Double samplingInterval);

	@Returns("profile")
	SamplingHeapProfile stopSampling();

	@EventName("addHeapSnapshotChunk")
	EventListener onAddHeapSnapshotChunk(EventHandler<AddHeapSnapshotChunk> eventListener);

	@EventName("resetProfiles")
	EventListener onResetProfiles(EventHandler<ResetProfiles> eventListener);

	@EventName("reportHeapSnapshotProgress")
	EventListener onReportHeapSnapshotProgress(EventHandler<ReportHeapSnapshotProgress> eventListener);

	/**
	 * If heap objects tracking has been started then backend regularly sends a current value for last seen object id and corresponding timestamp. If the were changes in the heap since last event then one or more heapStatsUpdate events will be sent before a new lastSeenObjectId event.
	 */
	@EventName("lastSeenObjectId")
	EventListener onLastSeenObjectId(EventHandler<LastSeenObjectId> eventListener);

	/**
	 * If heap objects tracking has been started then backend may send update for one or more fragments
	 */
	@EventName("heapStatsUpdate")
	EventListener onHeapStatsUpdate(EventHandler<HeapStatsUpdate> eventListener);
}
