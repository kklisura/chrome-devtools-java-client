package com.github.kklisura.cdtp.protocol.types.profiler;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import java.util.List;

/**
 * Source offset and types for a parameter or return value.
 */
@Experimental
public class TypeProfileEntry {

	private Integer offset;

	private List<TypeObject> types;

	/**
	 * Source offset of the parameter or end of function for return values.
	 */
	public Integer getOffset() {
		return offset;
	}

	/**
	 * Source offset of the parameter or end of function for return values.
	 */
	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	/**
	 * The types for this parameter or return value.
	 */
	public List<TypeObject> getTypes() {
		return types;
	}

	/**
	 * The types for this parameter or return value.
	 */
	public void setTypes(List<TypeObject> types) {
		this.types = types;
	}
}
