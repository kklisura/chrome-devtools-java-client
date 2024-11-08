package com.github.kklisura.cdt.services.utils;

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

import static com.github.kklisura.cdt.services.utils.ProxyUtils.createProxyFromAbstract;

import com.github.kklisura.cdt.services.impl.ChromeDevToolsServiceImpl;
import org.junit.Test;

/**
 * Created by Kenan Klisura on 23/01/2018.
 *
 * @author Kenan Klisura
 */
public class ProxyUtilsTest {
  @Test(expected = RuntimeException.class)
  public void testCreateProxyFromAbstractThrowsException() {
    createProxyFromAbstract(
        ChromeDevToolsServiceImpl.class,
        new Class[] {},
        new Object[] {},
        (unused, method, args) -> null);
  }
}
