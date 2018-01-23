package com.github.kklisura.cdtp;

import com.github.kklisura.cdtp.services.ChromeDevToolsService;
import com.github.kklisura.cdtp.services.ChromeService;
import com.github.kklisura.cdtp.services.impl.ChromeServiceImpl;
import com.github.kklisura.cdtp.services.model.chrome.ChromeTab;

/**
 * Hello world!
 *
 */
public class App {
	public static void main( String[] args ) throws Exception {
		final ChromeService chromeService = new ChromeServiceImpl(9222);
		final ChromeTab tab = chromeService.createTab();

		try (ChromeDevToolsService cdtpService = chromeService.createDevToolsService(tab)) {

			// Network requestWillBeSent event
			// Page loadEventFired

			cdtpService.getPage().navigate("http://google.com");
			cdtpService.getPage().navigate("http://twitter.com");
			cdtpService.getPage().navigate("http://facebook.com");

			cdtpService.getPage().navigate("http://atlantbh.com");
		}

		try (ChromeDevToolsService cdtpService = chromeService.createDevToolsService(tab)) {

			// Network requestWillBeSent event
			// Page loadEventFired

			cdtpService.getPage().navigate("http://google.com");
			cdtpService.getPage().navigate("http://twitter.com");
			cdtpService.getPage().navigate("http://facebook.com");

			cdtpService.getPage().navigate("http://atlantbh.com");
		}

		chromeService.closeTab(tab);
	}
}
