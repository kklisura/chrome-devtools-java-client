package com.github.kklisura.cdtp.definition.builder.protocol;

import com.github.kklisura.cdtp.definition.builder.protocol.types.Domain;
import com.github.kklisura.cdtp.definition.builder.protocol.types.Version;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * DevTools protocol definition.
 *
 * @author Kenan Klisura
 */
@Getter
@Setter
public class DevToolsProtocol {
  private Version version;

  private List<Domain> domains;
}
