package com.github.kklisura.cdtp.protocol.types.systeminfo;

import com.github.kklisura.cdtp.protocol.annotations.Optional;
import java.util.List;

/**
 * Provides information about the GPU(s) on the system.
 */
public class GPUInfo {

	private List<GPUDevice> devices;

	@Optional
	private Object auxAttributes;

	@Optional
	private Object featureStatus;

	private List<String> driverBugWorkarounds;

	/**
	 * The graphics devices on the system. Element 0 is the primary GPU.
	 */
	public List<GPUDevice> getDevices() {
		return devices;
	}

	/**
	 * The graphics devices on the system. Element 0 is the primary GPU.
	 */
	public void setDevices(List<GPUDevice> devices) {
		this.devices = devices;
	}

	/**
	 * An optional dictionary of additional GPU related attributes.
	 */
	public Object getAuxAttributes() {
		return auxAttributes;
	}

	/**
	 * An optional dictionary of additional GPU related attributes.
	 */
	public void setAuxAttributes(Object auxAttributes) {
		this.auxAttributes = auxAttributes;
	}

	/**
	 * An optional dictionary of graphics features and their status.
	 */
	public Object getFeatureStatus() {
		return featureStatus;
	}

	/**
	 * An optional dictionary of graphics features and their status.
	 */
	public void setFeatureStatus(Object featureStatus) {
		this.featureStatus = featureStatus;
	}

	/**
	 * An optional array of GPU driver bug workarounds.
	 */
	public List<String> getDriverBugWorkarounds() {
		return driverBugWorkarounds;
	}

	/**
	 * An optional array of GPU driver bug workarounds.
	 */
	public void setDriverBugWorkarounds(List<String> driverBugWorkarounds) {
		this.driverBugWorkarounds = driverBugWorkarounds;
	}
}
