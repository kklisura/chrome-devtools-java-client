package com.github.kklisura.cdtp.protocol.types.network;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.annotations.Optional;

/**
 * Response to an AuthChallenge.
 */
@Experimental
public class AuthChallengeResponse {

	private Response response;

	@Optional
	private String username;

	@Optional
	private String password;

	/**
	 * The decision on what to do in response to the authorization challenge.  Default means deferring to the default behavior of the net stack, which will likely either the Cancel authentication or display a popup dialog box.
	 */
	public Response getResponse() {
		return response;
	}

	/**
	 * The decision on what to do in response to the authorization challenge.  Default means deferring to the default behavior of the net stack, which will likely either the Cancel authentication or display a popup dialog box.
	 */
	public void setResponse(Response response) {
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
