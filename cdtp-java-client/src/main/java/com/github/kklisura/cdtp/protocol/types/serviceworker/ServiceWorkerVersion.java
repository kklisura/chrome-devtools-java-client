package com.github.kklisura.cdtp.protocol.types.serviceworker;

import com.github.kklisura.cdtp.protocol.annotations.Optional;
import java.util.List;

/**
 * ServiceWorker version.
 */
public class ServiceWorkerVersion {

	private String versionId;

	private String registrationId;

	private String scriptURL;

	private ServiceWorkerVersionRunningStatus runningStatus;

	private ServiceWorkerVersionStatus status;

	@Optional
	private Double scriptLastModified;

	@Optional
	private Double scriptResponseTime;

	@Optional
	private List<String> controlledClients;

	@Optional
	private String targetId;

	public String getVersionId() {
		return versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public String getScriptURL() {
		return scriptURL;
	}

	public void setScriptURL(String scriptURL) {
		this.scriptURL = scriptURL;
	}

	public ServiceWorkerVersionRunningStatus getRunningStatus() {
		return runningStatus;
	}

	public void setRunningStatus(ServiceWorkerVersionRunningStatus runningStatus) {
		this.runningStatus = runningStatus;
	}

	public ServiceWorkerVersionStatus getStatus() {
		return status;
	}

	public void setStatus(ServiceWorkerVersionStatus status) {
		this.status = status;
	}

	/**
	 * The Last-Modified header value of the main script.
	 */
	public Double getScriptLastModified() {
		return scriptLastModified;
	}

	/**
	 * The Last-Modified header value of the main script.
	 */
	public void setScriptLastModified(Double scriptLastModified) {
		this.scriptLastModified = scriptLastModified;
	}

	/**
	 * The time at which the response headers of the main script were received from the server.  For cached script it is the last time the cache entry was validated.
	 */
	public Double getScriptResponseTime() {
		return scriptResponseTime;
	}

	/**
	 * The time at which the response headers of the main script were received from the server.  For cached script it is the last time the cache entry was validated.
	 */
	public void setScriptResponseTime(Double scriptResponseTime) {
		this.scriptResponseTime = scriptResponseTime;
	}

	public List<String> getControlledClients() {
		return controlledClients;
	}

	public void setControlledClients(List<String> controlledClients) {
		this.controlledClients = controlledClients;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
}
