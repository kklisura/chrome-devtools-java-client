package com.github.kklisura.cdtp.protocol.types.profiler;

/*-
 * #%L
 * cdpt-java-client
 * %%
 * Copyright (C) 2018 Kenan Klisura
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

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import java.util.List;

/** Coverage data for a JavaScript function. */
@Experimental
public class FunctionCoverage {

  private String functionName;

  private List<CoverageRange> ranges;

  private Boolean isBlockCoverage;

  /** JavaScript function name. */
  public String getFunctionName() {
    return functionName;
  }

  /** JavaScript function name. */
  public void setFunctionName(String functionName) {
    this.functionName = functionName;
  }

  /** Source ranges inside the function with coverage data. */
  public List<CoverageRange> getRanges() {
    return ranges;
  }

  /** Source ranges inside the function with coverage data. */
  public void setRanges(List<CoverageRange> ranges) {
    this.ranges = ranges;
  }

  /** Whether coverage data for this function has block granularity. */
  public Boolean getIsBlockCoverage() {
    return isBlockCoverage;
  }

  /** Whether coverage data for this function has block granularity. */
  public void setIsBlockCoverage(Boolean isBlockCoverage) {
    this.isBlockCoverage = isBlockCoverage;
  }
}
