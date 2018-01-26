package com.github.kklisura.cdtp.protocol.events.serviceworker;

import com.github.kklisura.cdtp.protocol.types.serviceworker.ServiceWorkerVersion;
import java.util.List;

public class WorkerVersionUpdated {

  private List<ServiceWorkerVersion> versions;

  public List<ServiceWorkerVersion> getVersions() {
    return versions;
  }

  public void setVersions(List<ServiceWorkerVersion> versions) {
    this.versions = versions;
  }
}
