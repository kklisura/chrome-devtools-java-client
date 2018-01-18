package com.github.kklisura.cdtp.protocol.types.runtime;

import com.github.kklisura.cdtp.protocol.annotations.Optional;
import com.github.kklisura.cdtp.protocol.annotations.Experimental;

/**
 * Mirror object referencing original JavaScript object.
 */
public class RemoteObject {

	private Type type;

	@Optional
	private Subtype subtype;

	@Optional
	private String className;

	@Optional
	private Object value;

	@Optional
	private UnserializableValue unserializableValue;

	@Optional
	private String description;

	@Optional
	private String objectId;

	@Experimental
	@Optional
	private ObjectPreview preview;

	@Experimental
	@Optional
	private CustomPreview customPreview;

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
	 * Object class (constructor) name. Specified for <code>object</code> type values only.
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * Object class (constructor) name. Specified for <code>object</code> type values only.
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * Remote object value in case of primitive values or JSON values (if it was requested).
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Remote object value in case of primitive values or JSON values (if it was requested).
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * Primitive value which can not be JSON-stringified does not have <code>value</code>, but gets this property.
	 */
	public UnserializableValue getUnserializableValue() {
		return unserializableValue;
	}

	/**
	 * Primitive value which can not be JSON-stringified does not have <code>value</code>, but gets this property.
	 */
	public void setUnserializableValue(UnserializableValue unserializableValue) {
		this.unserializableValue = unserializableValue;
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
	 * Unique object identifier (for non-primitive values).
	 */
	public String getObjectId() {
		return objectId;
	}

	/**
	 * Unique object identifier (for non-primitive values).
	 */
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	/**
	 * Preview containing abbreviated property values. Specified for <code>object</code> type values only.
	 */
	public ObjectPreview getPreview() {
		return preview;
	}

	/**
	 * Preview containing abbreviated property values. Specified for <code>object</code> type values only.
	 */
	public void setPreview(ObjectPreview preview) {
		this.preview = preview;
	}

	public CustomPreview getCustomPreview() {
		return customPreview;
	}

	public void setCustomPreview(CustomPreview customPreview) {
		this.customPreview = customPreview;
	}
}
