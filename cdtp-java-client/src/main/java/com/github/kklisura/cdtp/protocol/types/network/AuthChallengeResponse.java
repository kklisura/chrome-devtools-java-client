package com.github.kklisura.cdtp.protocol.types.network;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.Optional;

/**
 * Response to an AuthChallenge.
 */
@Experimental
public class AuthChallengeResponse {

	private AuthChallengeResponseType response;

	@Optional
	private String username;

	@Optional
	private String password;

	/**
	 * The decision on what to do in response to the authorization challenge.  Default means deferring to the default behavior of the net stack, which will likely either the Cancel authentication or display a popup dialog box.
	 */
	public AuthChallengeResponseType getResponse() {
		return response;
	}

	/**
	 * The decision on what to do in response to the authorization challenge.  Default means deferring to the default behavior of the net stack, which will likely either the Cancel authentication or display a popup dialog box.
	 */
	public void setResponse(AuthChallengeResponseType response) {
		this.response = response;
	}

	/**
	 * The username to provide, possibly empty. Should only be set if response is ProvideCredentials.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * The username to provide, possibly empty. Should only be set if response is ProvideCredentials.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * The password to provide, possibly empty. Should only be set if response is ProvideCredentials.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * The password to provide, possibly empty. Should only be set if response is ProvideCredentials.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
