package com.github.kklisura.cdtp.protocol.types.page;

import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import java.util.List;

public class AppManifest {

	private String url;

	private List<AppManifestError> errors;

	@Optional
	private String data;

	/**
	 * Manifest location.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Manifest location.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	public List<AppManifestError> getErrors() {
		return errors;
	}

	public void setErrors(List<AppManifestError> errors) {
		this.errors = errors;
	}

	/**
	 * Manifest content.
	 */
	public String getData() {
		return data;
	}

	/**
	 * Manifest content.
	 */
	public void setData(String data) {
		this.data = data;
	}
}
