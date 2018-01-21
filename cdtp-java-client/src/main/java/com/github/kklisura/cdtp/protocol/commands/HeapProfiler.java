package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.annotations.Optional;
import com.github.kklisura.cdtp.protocol.types.heapprofiler.SamplingHeapProfile;
import com.github.kklisura.cdtp.protocol.types.runtime.RemoteObject;

@Experimental
public interface HeapProfiler {

	void enable();

	void disable();

	void startTrackingHeapObjects(@Optional Boolean trackAllocations);

	void stopTrackingHeapObjects(@Optional Boolean reportProgress);

	void takeHeapSnapshot(@Optional Boolean reportProgress);

	void collectGarbage();

	RemoteObject getObjectByHeapObjectId(String objectId, @Optional String objectGroup);

	/**
	 * Enables console to refer to the node with given id via $x (see Command Line API for more details $x functions).
	 */
	void addInspectedHeapObject(String heapObjectId);

	String getHeapObjectId(String objectId);

	void startSampling(@Optional Double samplingInterval);

	SamplingHeapProfile stopSampling();
}
