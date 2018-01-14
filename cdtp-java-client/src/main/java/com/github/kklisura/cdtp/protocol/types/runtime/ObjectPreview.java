package com.github.kklisura.cdtp.protocol.types.runtime;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.annotations.Optional;
import java.util.List;

/**
 * Object containing abbreviated remote object value.
 */
@Experimental
public class ObjectPreview {

	private Type type;

	@Optional
	private Subtype subtype;

	@Optional
	private String description;

	private Boolean overflow;

	private List<PropertyPreview> properties;

	@Optional
	private List<EntryPreview> entries;

	/**
	 * Object type.
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Object type.
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * Object subtype hint. Specified for <code>object</code> type values only.
	 */
	public Subtype getSubtype() {
		return subtype;
	}

	/**
	 * Object subtype hint. Specified for <code>object</code> type values only.
	 */
	public void setSubtype(Subtype subtype) {
		this.subtype = subtype;
	}

	/**
	 * String representation of the object.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * String representation of the object.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * True iff some of the properties or entries of the original object did not fit.
	 */
	public Boolean getOverflow() {
		return overflow;
	}

	/**
	 * True iff some of the properties or entries of the original object did not fit.
	 */
	public void setOverflow(Boolean overflow) {
		this.overflow = overflow;
	}

	/**
	 * List of the properties.
	 */
	public List<PropertyPreview> getProperties() {
		return properties;
	}

	/**
	 * List of the properties.
	 */
	public void setProperties(List<PropertyPreview> properties) {
		this.properties = properties;
	}

	/**
	 * List of the entries. Specified for <code>map</code> and <code>set</code> subtype values only.
	 */
	public List<EntryPreview> getEntries() {
		return entries;
	}

	/**
	 * List of the entries. Specified for <code>map</code> and <code>set</code> subtype values only.
	 */
	public void setEntries(List<EntryPreview> entries) {
		this.entries = entries;
	}
}
