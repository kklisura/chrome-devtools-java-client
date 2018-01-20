package com.github.kklisura.cdtp.client.services;

import com.github.kklisura.cdtp.client.services.exceptions.ChromeServiceException;
import com.github.kklisura.cdtp.client.services.model.chrome.ChromeTab;
import com.github.kklisura.cdtp.client.services.model.chrome.ChromeVersion;

import java.util.List;

/**
 * Chrome service definition.
 *
 * @author Kenan Klisura
 */
public interface ChromeService {
	/**
	 * Gets the list of opened chrome tabs.
	 *
	 * @return List of chrome tabs.
	 * @throws ChromeServiceException the chrome service exception
	 */
	List<ChromeTab> getTabs() throws ChromeServiceException;

	/**
	 * Creates a new chrome tab.
	 *
	 * @param url the url
	 * @return Created chrome tab.
	 * @throws ChromeServiceException the chrome service exception
	 */
	ChromeTab createTab(String url) throws ChromeServiceException;

	/**
	 * Activates a tab.
	 *
	 * @param tab the tab
	 * @throws ChromeServiceException the chrome service exception
	 */
	void activateTab(ChromeTab tab) throws ChromeServiceException;

	/**
	 * Closes a chrome tab.
	 *
	 * @param ta the ta
	 * @throws ChromeServiceException the chrome service exception
	 */
	void closeTab(ChromeTab ta) throws ChromeServiceException;

	/**
	 * Gets the version info.
	 *
	 * @return Version info.
	 * @throws ChromeServiceException If request fails for any reason.
	 */
	ChromeVersion getVersion() throws ChromeServiceException;;
}
