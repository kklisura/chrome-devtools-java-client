package com.github.kklisura.cdt.definition.builder;

/*-
 * #%L
 * cdt-java-protocol-builder
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

import java.io.File;
import lombok.Getter;
import lombok.Setter;
import org.kohsuke.args4j.Option;

/**
 * Application configuration.
 *
 * @author Kenan Klisura
 */
@Getter
@Setter
public class Configuration {
  @Option(
      name = "--base-package",
      usage = "Base package name (com.github.kklisura.cdt.protocol)",
      metaVar = "PACKAGE",
      required = true)
  private String basePackage;

  @Option(
      name = "--js-protocol",
      usage = "JS JSON protocol file (js_protocol.json)",
      metaVar = "JS_PROTOCOL_JSON",
      required = true)
  private File jsProtocolFile;

  @Option(
      name = "--browser-protocol",
      usage = "Browser JSON protocol file (browser_protocol.json)",
      metaVar = "BROWSER_PROTOCOL_JSON",
      required = true)
  private File browserProtocolFile;

  @Option(name = "--output", usage = "Output project location.", metaVar = "DIR", required = true)
  private File outputProjectLocation;
}
