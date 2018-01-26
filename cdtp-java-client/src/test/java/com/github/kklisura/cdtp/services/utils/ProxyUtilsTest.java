package com.github.kklisura.cdtp.services.utils;

import com.github.kklisura.cdtp.services.impl.ChromeDevToolsServiceImpl;
import org.junit.Test;

import static com.github.kklisura.cdtp.services.utils.ProxyUtils.createProxyFromAbstract;

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
