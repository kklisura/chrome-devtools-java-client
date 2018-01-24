package com.github.kklisura.cdtp;

import com.github.kklisura.cdtp.protocol.events.network.RequestWillBeSent;
import com.github.kklisura.cdtp.protocol.support.types.EventHandler;
import com.github.kklisura.cdtp.services.ChromeDevToolsService;
import com.github.kklisura.cdtp.services.ChromeService;
import com.github.kklisura.cdtp.services.impl.ChromeServiceImpl;
import com.github.kklisura.cdtp.services.types.ChromeTab;

/**
 * Hello world!
 *
 */
public class App {
	public static void main( String[] args ) throws Exception {
		final ChromeService chromeService = new ChromeServiceImpl(9222);
		final ChromeTab tab = chromeService.createTab();

		try (ChromeDevToolsService cdtpService = chromeService.createDevToolsService(tab)) {

			cdtpService.getNetwork().onRequestWillBeSent(new EventHandler<RequestWillBeSent>() {
				@Override
				public void onEvent(RequestWillBeSent event) {
					System.out.println(event.getRequest().getUrl());
				}
			});

			cdtpService.getNetwork().enable();

			// Network requestWillBeSent event
			// Page loadEventFired

			cdtpService.getPage().navigate("http://google.com");

			Thread.sleep(10000);
		}

		chromeService.closeTab(tab);
	}
}
