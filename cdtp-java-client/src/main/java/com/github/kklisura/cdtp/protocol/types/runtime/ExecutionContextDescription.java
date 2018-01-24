package com.github.kklisura.cdtp.protocol.types.runtime;

import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import java.util.Map;

/**
 * Description of an isolated world.
 */
public class ExecutionContextDescription {

	private Integer id;

	private String origin;

	private String name;

	@Optional
	private Map<String, Object> auxData;

	/**
	 * Unique id of the execution context. It can be used to specify in which execution context script evaluation should be performed.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Unique id of the execution context. It can be used to specify in which execution context script evaluation should be performed.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Execution context origin.
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * Execution context origin.
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	/**
	 * Human readable name describing given context.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Human readable name describing given context.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Embedder-specific auxiliary data.
	 */
	public Map<String, Object> getAuxData() {
		return auxData;
	}

	/**
	 * Embedder-specific auxiliary data.
	 */
	public void setAuxData(Map<String, Object> auxData) {
		this.auxData = auxData;
	}
}
