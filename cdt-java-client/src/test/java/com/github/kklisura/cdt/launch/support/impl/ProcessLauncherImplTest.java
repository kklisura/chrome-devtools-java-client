package com.github.kklisura.cdt.launch.support.impl;

/*-
 * #%L
 * cdt-java-client
 * %%
 * Copyright (C) 2018 - 2023 Kenan Klisura
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

import static org.easymock.EasyMock.capture;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.mock;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.ProcessBuilder.Redirect;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.easymock.Capture;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Created by Kenan Klisura on 31/01/2018.
 *
 * @author Kenan Klisura
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ProcessBuilder.class, ProcessLauncherImpl.class, Files.class, Paths.class})
public class ProcessLauncherImplTest {
  private ProcessLauncherImpl processLauncher;

  private ProcessBuilder processBuilder;

  @Before
  public void setUp() throws Exception {
    processLauncher = new ProcessLauncherImpl();
  }

  @Test
  public void testLaunch() throws Exception {
    List<String> args = new ArrayList<>();
    args.add("arg1");
    args.add("arg2");

    ProcessBuilder processBuilder = PowerMock.createMock(ProcessBuilder.class);

    PowerMock.expectNew(ProcessBuilder.class, new Class[] {}).andReturn(processBuilder);

    Capture<List<String>> captureCommands = Capture.newInstance();
    expect(processBuilder.command(capture(captureCommands))).andReturn(processBuilder);
    expect(processBuilder.redirectErrorStream(true)).andReturn(processBuilder);
    expect(processBuilder.redirectOutput(Redirect.PIPE)).andReturn(processBuilder);

    Process process = mock(Process.class);
    expect(processBuilder.start()).andReturn(process);

    PowerMock.replayAll(ProcessBuilder.class, processBuilder);

    assertEquals(process, processLauncher.launch("program-name", args));

    PowerMock.verify(ProcessBuilder.class, processBuilder);

    List<String> commands = captureCommands.getValue();
    assertEquals(3, commands.size());
    assertEquals("program-name", commands.get(0));
    assertEquals("arg1", commands.get(1));
    assertEquals("arg2", commands.get(2));
  }

  @Test
  public void testIsExecutable() {
    PowerMock.mockStatic(Paths.class);
    PowerMock.mockStatic(Files.class);

    final Path path = mock(Path.class);

    Paths.get("test-file");
    PowerMock.expectLastCall().andReturn(path);

    Files.isRegularFile(path);
    PowerMock.expectLastCall().andReturn(false);

    PowerMock.replayAll();
    assertFalse(processLauncher.isExecutable("test-file"));
    PowerMock.verifyAll();
    PowerMock.resetAll();

    // --

    Paths.get("test-file");
    PowerMock.expectLastCall().andReturn(path);

    Files.isRegularFile(path);
    PowerMock.expectLastCall().andReturn(true);

    Files.isReadable(path);
    PowerMock.expectLastCall().andReturn(false);

    PowerMock.replayAll();
    assertFalse(processLauncher.isExecutable("test-file"));
    PowerMock.verifyAll();
    PowerMock.resetAll();

    // --

    Paths.get("test-file");
    PowerMock.expectLastCall().andReturn(path);

    Files.isRegularFile(path);
    PowerMock.expectLastCall().andReturn(true);

    Files.isReadable(path);
    PowerMock.expectLastCall().andReturn(true);

    Files.isExecutable(path);
    PowerMock.expectLastCall().andReturn(false);

    PowerMock.replayAll();
    assertFalse(processLauncher.isExecutable("test-file"));
    PowerMock.verifyAll();
    PowerMock.resetAll();

    // --

    Paths.get("test-file");
    PowerMock.expectLastCall().andReturn(path);

    Files.isRegularFile(path);
    PowerMock.expectLastCall().andReturn(true);

    Files.isReadable(path);
    PowerMock.expectLastCall().andReturn(true);

    Files.isExecutable(path);
    PowerMock.expectLastCall().andReturn(true);

    PowerMock.replayAll();
    assertTrue(processLauncher.isExecutable("test-file"));
    PowerMock.verifyAll();
  }
}
