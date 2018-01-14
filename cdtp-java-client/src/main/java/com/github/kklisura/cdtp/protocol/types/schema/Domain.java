package com.github.kklisura.cdtp.protocol.types.schema;

/**
 * Description of the protocol domain.
 */
public class Domain {

	private String name;

	private String version;

	/**
	 * Domain name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Domain name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Domain version.
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Domain version.
	 */
	public void setVersion(String version) {
		this.version = version;
	}
}
