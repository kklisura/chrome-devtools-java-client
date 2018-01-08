package com.github.kklisura.dev.tools.java.generator;

import com.github.kklisura.dev.tools.java.generator.protocol.DevToolsProtocol;
import com.github.kklisura.dev.tools.java.generator.utils.DevToolsProtocolUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Application
 *
 * @author Kenan Klisura
 */
public class Application {
	/**
	 * Applications main entry point.
	 *
	 * @param args Arguments.
	 */
	public static void main( String[] args ) throws IOException {
		InputStream inputStream = Application.class.getClassLoader().getResourceAsStream("protocol.json");

		DevToolsProtocol protocol = DevToolsProtocolUtils.readJson(inputStream);
	}
}
