package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import com.github.kklisura.cdtp.protocol.support.annotations.ParamName;
import com.github.kklisura.cdtp.protocol.support.annotations.Returns;
import com.github.kklisura.cdtp.protocol.types.accessibility.AXNode;
import java.util.List;

@Experimental
public interface Accessibility {

	/**
	 * Fetches the accessibility node and partial accessibility tree for this DOM node, if it exists.
	 *
	 * @param nodeId ID of node to get the partial accessibility tree for.
	 */
	@Experimental
	@Returns("nodes")
	List<AXNode> getPartialAXTree(@ParamName("nodeId") Integer nodeId);

	/**
	 * Fetches the accessibility node and partial accessibility tree for this DOM node, if it exists.
	 *
	 * @param nodeId ID of node to get the partial accessibility tree for.
	 * @param fetchRelatives Whether to fetch this nodes ancestors, siblings and children. Defaults to true.
	 */
	@Experimental
	@Returns("nodes")
	List<AXNode> getPartialAXTree(@ParamName("nodeId") Integer nodeId, @Optional @ParamName("fetchRelatives") Boolean fetchRelatives);
}
