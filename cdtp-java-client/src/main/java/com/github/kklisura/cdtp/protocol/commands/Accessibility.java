package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.annotations.Optional;
import com.github.kklisura.cdtp.protocol.annotations.Returns;
import com.github.kklisura.cdtp.protocol.types.accessibility.AXNode;
import java.util.List;

@Experimental
public interface Accessibility {

	/**
	 * Fetches the accessibility node and partial accessibility tree for this DOM node, if it exists.
	 */
	@Experimental
	@Returns("nodes")
	List<AXNode> getPartialAXTree(Integer nodeId, @Optional Boolean fetchRelatives);
}
