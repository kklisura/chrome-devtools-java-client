package com.github.kklisura.cdtp;

import com.github.kklisura.cdtp.protocol.ChromeDevTools;
import com.github.kklisura.cdtp.services.ChromeService;
import com.github.kklisura.cdtp.services.exceptions.ChromeServiceException;
import com.github.kklisura.cdtp.services.impl.ChromeServiceImpl;
import com.github.kklisura.cdtp.services.model.chrome.ChromeTab;

/**
 * Hello world!
 *
 */
public class App {
	public static void main( String[] args ) throws ChromeServiceException, InterruptedException {
		final ChromeService chromeService = new ChromeServiceImpl(9222);
		final ChromeTab tab = chromeService.createTab();

		final ChromeDevTools devTools = chromeService.getDevTools(tab);

		// Network requestWillBeSent event
		// Page loadEventFired

		devTools.getPage().navigate("http://google.com");
	}
}
