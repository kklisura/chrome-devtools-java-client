package com.github.kklisura.cdtp.protocol.types.profiler;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import java.util.List;

/** Coverage data for a JavaScript script. */
@Experimental
public class ScriptCoverage {

  private String scriptId;

  private String url;

  private List<FunctionCoverage> functions;

  /** JavaScript script id. */
  public String getScriptId() {
    return scriptId;
  }

  /** JavaScript script id. */
  public void setScriptId(String scriptId) {
    this.scriptId = scriptId;
  }

  /** JavaScript script name or url. */
  public String getUrl() {
    return url;
  }

  /** JavaScript script name or url. */
  public void setUrl(String url) {
    this.url = url;
  }

  /** Functions contained in the script that has coverage data. */
  public List<FunctionCoverage> getFunctions() {
    return functions;
  }

  /** Functions contained in the script that has coverage data. */
  public void setFunctions(List<FunctionCoverage> functions) {
    this.functions = functions;
  }
}
