package com.github.kklisura.cdtp.protocol.types.systeminfo;

public class Info {

	private GPUInfo gpu;

	private String modelName;

	private String modelVersion;

	private String commandLine;

	/**
	 * Information about the GPUs on the system.
	 */
	public GPUInfo getGpu() {
		return gpu;
	}

	/**
	 * Information about the GPUs on the system.
	 */
	public void setGpu(GPUInfo gpu) {
		this.gpu = gpu;
	}

	/**
	 * A platform-dependent description of the model of the machine. On Mac OS, this is, for example, 'MacBookPro'. Will be the empty string if not supported.
	 */
	public String getModelName() {
		return modelName;
	}

	/**
	 * A platform-dependent description of the model of the machine. On Mac OS, this is, for example, 'MacBookPro'. Will be the empty string if not supported.
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	/**
	 * A platform-dependent description of the version of the machine. On Mac OS, this is, for example, '10.1'. Will be the empty string if not supported.
	 */
	public String getModelVersion() {
		return modelVersion;
	}

	/**
	 * A platform-dependent description of the version of the machine. On Mac OS, this is, for example, '10.1'. Will be the empty string if not supported.
	 */
	public void setModelVersion(String modelVersion) {
		this.modelVersion = modelVersion;
	}

	/**
	 * The command line string used to launch the browser. Will be the empty string if not supported.
	 */
	public String getCommandLine() {
		return commandLine;
	}

	/**
	 * The command line string used to launch the browser. Will be the empty string if not supported.
	 */
	public void setCommandLine(String commandLine) {
		this.commandLine = commandLine;
	}
}
