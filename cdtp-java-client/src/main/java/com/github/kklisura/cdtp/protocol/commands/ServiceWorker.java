package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.events.serviceworker.WorkerErrorReported;
import com.github.kklisura.cdtp.protocol.events.serviceworker.WorkerRegistrationUpdated;
import com.github.kklisura.cdtp.protocol.events.serviceworker.WorkerVersionUpdated;
import com.github.kklisura.cdtp.protocol.support.annotations.EventName;
import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.ParamName;
import com.github.kklisura.cdtp.protocol.support.types.EventHandler;
import com.github.kklisura.cdtp.protocol.support.types.EventListener;

@Experimental
public interface ServiceWorker {

	void enable();

	void disable();

	void unregister(@ParamName("scopeURL") String scopeURL);

	void updateRegistration(@ParamName("scopeURL") String scopeURL);

	void startWorker(@ParamName("scopeURL") String scopeURL);

	void skipWaiting(@ParamName("scopeURL") String scopeURL);

	void stopWorker(@ParamName("versionId") String versionId);

	void stopAllWorkers();

	void inspectWorker(@ParamName("versionId") String versionId);

	void setForceUpdateOnPageLoad(@ParamName("forceUpdateOnPageLoad") Boolean forceUpdateOnPageLoad);

	void deliverPushMessage(@ParamName("origin") String origin, @ParamName("registrationId") String registrationId, @ParamName("data") String data);

	void dispatchSyncEvent(@ParamName("origin") String origin, @ParamName("registrationId") String registrationId, @ParamName("tag") String tag, @ParamName("lastChance") Boolean lastChance);

	@EventName("workerRegistrationUpdated")
	EventListener onWorkerRegistrationUpdated(EventHandler<WorkerRegistrationUpdated> eventListener);

	@EventName("workerVersionUpdated")
	EventListener onWorkerVersionUpdated(EventHandler<WorkerVersionUpdated> eventListener);

	@EventName("workerErrorReported")
	EventListener onWorkerErrorReported(EventHandler<WorkerErrorReported> eventListener);
}
