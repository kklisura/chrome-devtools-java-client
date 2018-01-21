package com.github.kklisura.cdtp.protocol.types.database;

import com.github.kklisura.cdtp.protocol.annotations.Optional;
import java.util.List;

public class ExecuteSQL {

	@Optional
	private List<String> columnNames;

	@Optional
	private List<Object> values;

	@Optional
	private Error sqlError;

	public List<String> getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(List<String> columnNames) {
		this.columnNames = columnNames;
	}

	public List<Object> getValues() {
		return values;
	}

	public void setValues(List<Object> values) {
		this.values = values;
	}

	public Error getSqlError() {
		return sqlError;
	}

	public void setSqlError(Error sqlError) {
		this.sqlError = sqlError;
	}
}
