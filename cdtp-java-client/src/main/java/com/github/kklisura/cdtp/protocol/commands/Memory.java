package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.ParamName;
import com.github.kklisura.cdtp.protocol.types.memory.DOMCounters;
import com.github.kklisura.cdtp.protocol.types.memory.PressureLevel;

@Experimental
public interface Memory {

  DOMCounters getDOMCounters();

  void prepareForLeakDetection();

  /**
   * Enable/disable suppressing memory pressure notifications in all processes.
   *
   * @param suppressed If true, memory pressure notifications will be suppressed.
   */
  void setPressureNotificationsSuppressed(@ParamName("suppressed") Boolean suppressed);

  /**
   * Simulate a memory pressure notification in all processes.
   *
   * @param level Memory pressure level of the notification.
   */
  void simulatePressureNotification(@ParamName("level") PressureLevel level);
}
