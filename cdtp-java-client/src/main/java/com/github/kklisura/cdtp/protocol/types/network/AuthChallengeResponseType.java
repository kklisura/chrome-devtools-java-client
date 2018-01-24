package com.github.kklisura.cdtp.protocol.types.network;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AuthChallengeResponseType {

	@JsonProperty("Default")
	DEFAULT, @JsonProperty("CancelAuth")
	CANCEL_AUTH, @JsonProperty("ProvideCredentials")
	PROVIDE_CREDENTIALS
}
