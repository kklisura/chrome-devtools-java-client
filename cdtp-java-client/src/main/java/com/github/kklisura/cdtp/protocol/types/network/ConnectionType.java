package com.github.kklisura.cdtp.protocol.types.network;

/**
 * The underlying connection technology that the browser is supposedly using.
 */
public enum ConnectionType {

	NONE("none"), CELLULAR_2G("cellular2g"), CELLULAR_3G("cellular3g"), CELLULAR_4G("cellular4g"), BLUETOOTH("bluetooth"), ETHERNET("ethernet"), WIFI("wifi"), WIMAX("wimax"), OTHER("other");

	final String propertyName;

	ConnectionType(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
