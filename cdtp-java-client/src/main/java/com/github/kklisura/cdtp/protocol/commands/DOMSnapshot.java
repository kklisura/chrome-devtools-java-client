package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.ParamName;
import com.github.kklisura.cdtp.protocol.types.domsnapshot.Snapshot;
import java.util.List;

/** This domain facilitates obtaining document snapshots with DOM, layout, and style information. */
@Experimental
public interface DOMSnapshot {

  /**
   * Returns a document snapshot, including the full DOM tree of the root node (including iframes,
   * template contents, and imported documents) in a flattened array, as well as layout and
   * white-listed computed style information for the nodes. Shadow DOM in the returned DOM tree is
   * flattened.
   *
   * @param computedStyleWhitelist Whitelist of computed styles to return.
   */
  Snapshot getSnapshot(@ParamName("computedStyleWhitelist") List<String> computedStyleWhitelist);
}
