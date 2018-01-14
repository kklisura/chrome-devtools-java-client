package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;

@Experimental
public interface ServiceWorker {

	void enable();

	void disable();

	void unregister(String scopeURL);

	void updateRegistration(String scopeURL);

	void startWorker(String scopeURL);

	void skipWaiting(String scopeURL);

	void stopWorker(String versionId);

	void stopAllWorkers();

	void inspectWorker(String versionId);

	void setForceUpdateOnPageLoad(Boolean forceUpdateOnPageLoad);

	void deliverPushMessage(String origin, String registrationId, String data);

	void dispatchSyncEvent(String origin, String registrationId, String tag, Boolean lastChance);
}
