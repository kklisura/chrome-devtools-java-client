package com.github.kklisura.cdtp.protocol.types.animation;

import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import java.util.List;

/** Keyframes Rule */
public class KeyframesRule {

  @Optional private String name;

  private List<KeyframeStyle> keyframes;

  /** CSS keyframed animation's name. */
  public String getName() {
    return name;
  }

  /** CSS keyframed animation's name. */
  public void setName(String name) {
    this.name = name;
  }

  /** List of animation keyframes. */
  public List<KeyframeStyle> getKeyframes() {
    return keyframes;
  }

  /** List of animation keyframes. */
  public void setKeyframes(List<KeyframeStyle> keyframes) {
    this.keyframes = keyframes;
  }
}
