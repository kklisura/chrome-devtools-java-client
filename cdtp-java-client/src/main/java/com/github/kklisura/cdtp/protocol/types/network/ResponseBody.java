package com.github.kklisura.cdtp.protocol.types.network;

public class ResponseBody {

  private String body;

  private Boolean base64Encoded;

  /** Response body. */
  public String getBody() {
    return body;
  }

  /** Response body. */
  public void setBody(String body) {
    this.body = body;
  }

  /** True, if content was sent as base64. */
  public Boolean getBase64Encoded() {
    return base64Encoded;
  }

  /** True, if content was sent as base64. */
  public void setBase64Encoded(Boolean base64Encoded) {
    this.base64Encoded = base64Encoded;
  }
}
