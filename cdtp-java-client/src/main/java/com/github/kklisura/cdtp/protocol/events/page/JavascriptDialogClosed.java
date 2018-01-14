package com.github.kklisura.cdtp.protocol.events.page;

/**
 * Fired when a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload) has been closed.
 */
public class JavascriptDialogClosed {

	private Boolean result;

	private String userInput;

	/**
	 * Whether dialog was confirmed.
	 */
	public Boolean getResult() {
		return result;
	}

	/**
	 * Whether dialog was confirmed.
	 */
	public void setResult(Boolean result) {
		this.result = result;
	}

	/**
	 * User input in case of prompt.
	 */
	public String getUserInput() {
		return userInput;
	}

	/**
	 * User input in case of prompt.
	 */
	public void setUserInput(String userInput) {
		this.userInput = userInput;
	}
}
