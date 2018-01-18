package com.github.kklisura.cdtp.protocol.types.tracing;

public class RequestMemoryDump {

	private String dumpGuid;

	private Boolean success;

	/**
	 * GUID of the resulting global memory dump.
	 */
	public String getDumpGuid() {
		return dumpGuid;
	}

	/**
	 * GUID of the resulting global memory dump.
	 */
	public void setDumpGuid(String dumpGuid) {
		this.dumpGuid = dumpGuid;
	}

	/**
	 * True iff the global memory dump succeeded.
	 */
	public Boolean getSuccess() {
		return success;
	}

	/**
	 * True iff the global memory dump succeeded.
	 */
	public void setSuccess(Boolean success) {
		this.success = success;
	}
}
