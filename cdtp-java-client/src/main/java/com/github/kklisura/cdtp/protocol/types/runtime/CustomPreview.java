package com.github.kklisura.cdtp.protocol.types.runtime;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.Optional;

@Experimental
public class CustomPreview {

  private String header;

  private Boolean hasBody;

  private String formatterObjectId;

  private String bindRemoteObjectFunctionId;

  @Optional private String configObjectId;

  public String getHeader() {
    return header;
  }

  public void setHeader(String header) {
    this.header = header;
  }

  public Boolean getHasBody() {
    return hasBody;
  }

  public void setHasBody(Boolean hasBody) {
    this.hasBody = hasBody;
  }

  public String getFormatterObjectId() {
    return formatterObjectId;
  }

  public void setFormatterObjectId(String formatterObjectId) {
    this.formatterObjectId = formatterObjectId;
  }

  public String getBindRemoteObjectFunctionId() {
    return bindRemoteObjectFunctionId;
  }

  public void setBindRemoteObjectFunctionId(String bindRemoteObjectFunctionId) {
    this.bindRemoteObjectFunctionId = bindRemoteObjectFunctionId;
  }

  public String getConfigObjectId() {
    return configObjectId;
  }

  public void setConfigObjectId(String configObjectId) {
    this.configObjectId = configObjectId;
  }
}
