package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.annotations.ParamName;

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
}
