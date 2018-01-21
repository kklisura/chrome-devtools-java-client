package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.annotations.Optional;
import com.github.kklisura.cdtp.protocol.annotations.ParamName;
import com.github.kklisura.cdtp.protocol.annotations.Returns;
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
}
