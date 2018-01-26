package com.github.kklisura.cdtp.protocol.events.tethering;

/** Informs that port was successfully bound and got a specified connection id. */
public class Accepted {

  private Integer port;

  private String connectionId;

  /** Port number that was successfully bound. */
  public Integer getPort() {
    return port;
  }

  /** Port number that was successfully bound. */
  public void setPort(Integer port) {
    this.port = port;
  }

  /** Connection id to be used. */
  public String getConnectionId() {
    return connectionId;
  }

  /** Connection id to be used. */
  public void setConnectionId(String connectionId) {
    this.connectionId = connectionId;
  }
}
