package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.types.runtime.RemoteObject;
import com.github.kklisura.cdtp.protocol.annotations.Optional;
import com.github.kklisura.cdtp.protocol.types.heapprofiler.SamplingHeapProfile;

@Experimental
public interface HeapProfiler {

	/**
	 * Enables console to refer to the node with given id via $x (see Command Line API for more details
	 * $x functions).
	 */
	void addInspectedHeapObject(String heapObjectId);

	void collectGarbage();

	void disable();

	void enable();

	String getHeapObjectId(String objectId);

	RemoteObject getObjectByHeapObjectId(String objectId, @Optional String objectGroup);

	SamplingHeapProfile getSamplingProfile();

	void startSampling(@Optional Double samplingInterval);

	void startTrackingHeapObjects(@Optional Boolean trackAllocations);

	SamplingHeapProfile stopSampling();

	void stopTrackingHeapObjects(@Optional Boolean reportProgress);

	void takeHeapSnapshot(@Optional Boolean reportProgress);
}
