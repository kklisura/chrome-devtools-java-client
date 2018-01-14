package com.github.kklisura.cdtp.protocol.types.profiler;

import com.github.kklisura.cdtp.protocol.types.runtime.CallFrame;
import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.annotations.Optional;
import java.util.List;

/**
 * Profile node. Holds callsite information, execution statistics and child nodes.
 */
public class ProfileNode {

	private Integer id;

	private CallFrame callFrame;

	@Experimental
	@Optional
	private Integer hitCount;

	@Optional
	private List<Integer> children;

	@Optional
	private String deoptReason;

	@Experimental
	@Optional
	private List<PositionTickInfo> positionTicks;

	/**
	 * Unique id of the node.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Unique id of the node.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Function location.
	 */
	public CallFrame getCallFrame() {
		return callFrame;
	}

	/**
	 * Function location.
	 */
	public void setCallFrame(CallFrame callFrame) {
		this.callFrame = callFrame;
	}

	/**
	 * Number of samples where this node was on top of the call stack.
	 */
	public Integer getHitCount() {
		return hitCount;
	}

	/**
	 * Number of samples where this node was on top of the call stack.
	 */
	public void setHitCount(Integer hitCount) {
		this.hitCount = hitCount;
	}

	/**
	 * Child node ids.
	 */
	public List<Integer> getChildren() {
		return children;
	}

	/**
	 * Child node ids.
	 */
	public void setChildren(List<Integer> children) {
		this.children = children;
	}

	/**
	 * The reason of being not optimized. The function may be deoptimized or marked as don't optimize.
	 */
	public String getDeoptReason() {
		return deoptReason;
	}

	/**
	 * The reason of being not optimized. The function may be deoptimized or marked as don't optimize.
	 */
	public void setDeoptReason(String deoptReason) {
		this.deoptReason = deoptReason;
	}

	/**
	 * An array of source position ticks.
	 */
	public List<PositionTickInfo> getPositionTicks() {
		return positionTicks;
	}

	/**
	 * An array of source position ticks.
	 */
	public void setPositionTicks(List<PositionTickInfo> positionTicks) {
		this.positionTicks = positionTicks;
	}
}
