package com.github.kklisura.cdtp.protocol.types.runtime;

import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import java.util.List;

public class Properties {

	private List<PropertyDescriptor> result;

	@Optional
	private List<InternalPropertyDescriptor> internalProperties;

	@Optional
	private ExceptionDetails exceptionDetails;

	/**
	 * Object properties.
	 */
	public List<PropertyDescriptor> getResult() {
		return result;
	}

	/**
	 * Object properties.
	 */
	public void setResult(List<PropertyDescriptor> result) {
		this.result = result;
	}

	/**
	 * Internal object properties (only of the element itself).
	 */
	public List<InternalPropertyDescriptor> getInternalProperties() {
		return internalProperties;
	}

	/**
	 * Internal object properties (only of the element itself).
	 */
	public void setInternalProperties(List<InternalPropertyDescriptor> internalProperties) {
		this.internalProperties = internalProperties;
	}

	/**
	 * Exception details.
	 */
	public ExceptionDetails getExceptionDetails() {
		return exceptionDetails;
	}

	/**
	 * Exception details.
	 */
	public void setExceptionDetails(ExceptionDetails exceptionDetails) {
		this.exceptionDetails = exceptionDetails;
	}
}
