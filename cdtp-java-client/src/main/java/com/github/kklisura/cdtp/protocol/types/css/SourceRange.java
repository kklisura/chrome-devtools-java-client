package com.github.kklisura.cdtp.protocol.types.css;

/** Text range within a resource. All numbers are zero-based. */
public class SourceRange {

  private Integer startLine;

  private Integer startColumn;

  private Integer endLine;

  private Integer endColumn;

  /** Start line of range. */
  public Integer getStartLine() {
    return startLine;
  }

  /** Start line of range. */
  public void setStartLine(Integer startLine) {
    this.startLine = startLine;
  }

  /** Start column of range (inclusive). */
  public Integer getStartColumn() {
    return startColumn;
  }

  /** Start column of range (inclusive). */
  public void setStartColumn(Integer startColumn) {
    this.startColumn = startColumn;
  }

  /** End line of range */
  public Integer getEndLine() {
    return endLine;
  }

  /** End line of range */
  public void setEndLine(Integer endLine) {
    this.endLine = endLine;
  }

  /** End column of range (exclusive). */
  public Integer getEndColumn() {
    return endColumn;
  }

  /** End column of range (exclusive). */
  public void setEndColumn(Integer endColumn) {
    this.endColumn = endColumn;
  }
}
