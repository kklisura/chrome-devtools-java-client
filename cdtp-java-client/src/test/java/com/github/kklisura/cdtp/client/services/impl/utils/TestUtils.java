package com.github.kklisura.cdtp.client.services.impl.utils;

import java.io.InputStream;

/**
 * Test utils.
 *
 * @author Kenan Klisura
 */
public final class TestUtils {
	private static final String FIXTURE = "/fixture/";

	/**
	 * Returns fixture input stream.
	 *
	 * @param fixture Fixture name.
	 * @return Fixture input stream.
	 */
	public static InputStream getFixture(String fixture) {
		return TestUtils.class.getResourceAsStream(FIXTURE + fixture);
	}
}
