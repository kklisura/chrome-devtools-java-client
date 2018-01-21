package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.annotations.Optional;
import com.github.kklisura.cdtp.protocol.annotations.ParamName;
import com.github.kklisura.cdtp.protocol.types.input.Button;
import com.github.kklisura.cdtp.protocol.types.input.GestureSourceType;
import com.github.kklisura.cdtp.protocol.types.input.TouchPoint;
import com.github.kklisura.cdtp.protocol.types.input.Type;
import java.util.List;

public interface Input {

	/**
	 * Ignores input events (useful while auditing page).
	 */
	void setIgnoreInputEvents(@ParamName("ignore") Boolean ignore);

	/**
	 * Dispatches a key event to the page.
	 */
	void dispatchKeyEvent(@ParamName("type") Type type, @Optional @ParamName("modifiers") Integer modifiers, @Optional @ParamName("timestamp") Double timestamp, @Optional @ParamName("text") String text, @Optional @ParamName("unmodifiedText") String unmodifiedText, @Optional @ParamName("keyIdentifier") String keyIdentifier, @Optional @ParamName("code") String code, @Optional @ParamName("key") String key, @Optional @ParamName("windowsVirtualKeyCode") Integer windowsVirtualKeyCode, @Optional @ParamName("nativeVirtualKeyCode") Integer nativeVirtualKeyCode, @Optional @ParamName("autoRepeat") Boolean autoRepeat, @Optional @ParamName("isKeypad") Boolean isKeypad, @Optional @ParamName("isSystemKey") Boolean isSystemKey);

	/**
	 * Dispatches a mouse event to the page.
	 */
	void dispatchMouseEvent(@ParamName("type") Type type, @ParamName("x") Double x, @ParamName("y") Double y, @Optional @ParamName("modifiers") Integer modifiers, @Optional @ParamName("timestamp") Double timestamp, @Optional @ParamName("button") Button button, @Optional @ParamName("clickCount") Integer clickCount, @Optional @ParamName("deltaX") Double deltaX, @Optional @ParamName("deltaY") Double deltaY);

	/**
	 * Dispatches a touch event to the page.
	 */
	@Experimental
	void dispatchTouchEvent(@ParamName("type") Type type, @ParamName("touchPoints") List<TouchPoint> touchPoints, @Optional @ParamName("modifiers") Integer modifiers, @Optional @ParamName("timestamp") Double timestamp);

	/**
	 * Emulates touch event from the mouse event parameters.
	 */
	@Experimental
	void emulateTouchFromMouseEvent(@ParamName("type") Type type, @ParamName("x") Integer x, @ParamName("y") Integer y, @ParamName("timestamp") Double timestamp, @ParamName("button") Button button, @Optional @ParamName("deltaX") Double deltaX, @Optional @ParamName("deltaY") Double deltaY, @Optional @ParamName("modifiers") Integer modifiers, @Optional @ParamName("clickCount") Integer clickCount);

	/**
	 * Synthesizes a pinch gesture over a time period by issuing appropriate touch events.
	 */
	@Experimental
	void synthesizePinchGesture(@ParamName("x") Double x, @ParamName("y") Double y, @ParamName("scaleFactor") Double scaleFactor, @Optional @ParamName("relativeSpeed") Integer relativeSpeed, @Optional @ParamName("gestureSourceType") GestureSourceType gestureSourceType);

	/**
	 * Synthesizes a scroll gesture over a time period by issuing appropriate touch events.
	 */
	@Experimental
	void synthesizeScrollGesture(@ParamName("x") Double x, @ParamName("y") Double y, @Optional @ParamName("xDistance") Double xDistance, @Optional @ParamName("yDistance") Double yDistance, @Optional @ParamName("xOverscroll") Double xOverscroll, @Optional @ParamName("yOverscroll") Double yOverscroll, @Optional @ParamName("preventFling") Boolean preventFling, @Optional @ParamName("speed") Integer speed, @Optional @ParamName("gestureSourceType") GestureSourceType gestureSourceType, @Optional @ParamName("repeatCount") Integer repeatCount, @Optional @ParamName("repeatDelayMs") Integer repeatDelayMs, @Optional @ParamName("interactionMarkerName") String interactionMarkerName);

	/**
	 * Synthesizes a tap gesture over a time period by issuing appropriate touch events.
	 */
	@Experimental
	void synthesizeTapGesture(@ParamName("x") Double x, @ParamName("y") Double y, @Optional @ParamName("duration") Integer duration, @Optional @ParamName("tapCount") Integer tapCount, @Optional @ParamName("gestureSourceType") GestureSourceType gestureSourceType);
}
