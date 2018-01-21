package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.annotations.Optional;
import com.github.kklisura.cdtp.protocol.types.input.Button;
import com.github.kklisura.cdtp.protocol.types.input.GestureSourceType;
import com.github.kklisura.cdtp.protocol.types.input.TouchPoint;
import com.github.kklisura.cdtp.protocol.types.input.Type;
import java.util.List;

public interface Input {

	/**
	 * Ignores input events (useful while auditing page).
	 */
	void setIgnoreInputEvents(Boolean ignore);

	/**
	 * Dispatches a key event to the page.
	 */
	void dispatchKeyEvent(Type type, @Optional Integer modifiers, @Optional Double timestamp, @Optional String text, @Optional String unmodifiedText, @Optional String keyIdentifier, @Optional String code, @Optional String key, @Optional Integer windowsVirtualKeyCode, @Optional Integer nativeVirtualKeyCode, @Optional Boolean autoRepeat, @Optional Boolean isKeypad, @Optional Boolean isSystemKey);

	/**
	 * Dispatches a mouse event to the page.
	 */
	void dispatchMouseEvent(Type type, Double x, Double y, @Optional Integer modifiers, @Optional Double timestamp, @Optional Button button, @Optional Integer clickCount, @Optional Double deltaX, @Optional Double deltaY);

	/**
	 * Dispatches a touch event to the page.
	 */
	@Experimental
	void dispatchTouchEvent(Type type, List<TouchPoint> touchPoints, @Optional Integer modifiers, @Optional Double timestamp);

	/**
	 * Emulates touch event from the mouse event parameters.
	 */
	@Experimental
	void emulateTouchFromMouseEvent(Type type, Integer x, Integer y, Double timestamp, Button button, @Optional Double deltaX, @Optional Double deltaY, @Optional Integer modifiers, @Optional Integer clickCount);

	/**
	 * Synthesizes a pinch gesture over a time period by issuing appropriate touch events.
	 */
	@Experimental
	void synthesizePinchGesture(Double x, Double y, Double scaleFactor, @Optional Integer relativeSpeed, @Optional GestureSourceType gestureSourceType);

	/**
	 * Synthesizes a scroll gesture over a time period by issuing appropriate touch events.
	 */
	@Experimental
	void synthesizeScrollGesture(Double x, Double y, @Optional Double xDistance, @Optional Double yDistance, @Optional Double xOverscroll, @Optional Double yOverscroll, @Optional Boolean preventFling, @Optional Integer speed, @Optional GestureSourceType gestureSourceType, @Optional Integer repeatCount, @Optional Integer repeatDelayMs, @Optional String interactionMarkerName);

	/**
	 * Synthesizes a tap gesture over a time period by issuing appropriate touch events.
	 */
	@Experimental
	void synthesizeTapGesture(Double x, Double y, @Optional Integer duration, @Optional Integer tapCount, @Optional GestureSourceType gestureSourceType);
}
