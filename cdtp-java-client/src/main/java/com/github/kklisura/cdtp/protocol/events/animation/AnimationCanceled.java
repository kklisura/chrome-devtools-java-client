package com.github.kklisura.cdtp.protocol.events.animation;

/** Event for when an animation has been cancelled. */
public class AnimationCanceled {

  private String id;

  /** Id of the animation that was cancelled. */
  public String getId() {
    return id;
  }

  /** Id of the animation that was cancelled. */
  public void setId(String id) {
    this.id = id;
  }
}
