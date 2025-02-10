package com.github.kklisura.cdt.protocol.types.css;

/*-
 * #%L
 * cdt-java-client
 * %%
 * Copyright (C) 2018 - 2025 Kenan Klisura
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.github.kklisura.cdt.protocol.support.annotations.Optional;
import java.util.List;

/** Inherited CSS style collection for animated styles from ancestor node. */
public class InheritedAnimatedStyleEntry {

  @Optional private List<CSSAnimationStyle> animationStyles;

  @Optional private CSSStyle transitionsStyle;

  /** Styles coming from the animations of the ancestor, if any, in the style inheritance chain. */
  public List<CSSAnimationStyle> getAnimationStyles() {
    return animationStyles;
  }

  /** Styles coming from the animations of the ancestor, if any, in the style inheritance chain. */
  public void setAnimationStyles(List<CSSAnimationStyle> animationStyles) {
    this.animationStyles = animationStyles;
  }

  /**
   * The style coming from the transitions of the ancestor, if any, in the style inheritance chain.
   */
  public CSSStyle getTransitionsStyle() {
    return transitionsStyle;
  }

  /**
   * The style coming from the transitions of the ancestor, if any, in the style inheritance chain.
   */
  public void setTransitionsStyle(CSSStyle transitionsStyle) {
    this.transitionsStyle = transitionsStyle;
  }
}
