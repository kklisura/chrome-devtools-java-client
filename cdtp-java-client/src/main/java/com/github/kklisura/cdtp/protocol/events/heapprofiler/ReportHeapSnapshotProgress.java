package com.github.kklisura.cdtp.protocol.events.heapprofiler;

import com.github.kklisura.cdtp.protocol.support.annotations.Optional;

public class ReportHeapSnapshotProgress {

  private Integer done;

  private Integer total;

  @Optional private Boolean finished;

  public Integer getDone() {
    return done;
  }

  public void setDone(Integer done) {
    this.done = done;
  }

  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public Boolean getFinished() {
    return finished;
  }

  public void setFinished(Boolean finished) {
    this.finished = finished;
  }
}
