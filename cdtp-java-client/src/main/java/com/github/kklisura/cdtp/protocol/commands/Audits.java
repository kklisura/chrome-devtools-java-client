package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import com.github.kklisura.cdtp.protocol.support.annotations.ParamName;
import com.github.kklisura.cdtp.protocol.types.audits.EncodedResponse;
import com.github.kklisura.cdtp.protocol.types.audits.Encoding;

/** Audits domain allows investigation of page violations and possible improvements. */
@Experimental
public interface Audits {

  /**
   * Returns the response body and size if it were re-encoded with the specified settings. Only
   * applies to images.
   *
   * @param requestId Identifier of the network request to get content for.
   * @param encoding The encoding to use.
   */
  EncodedResponse getEncodedResponse(
      @ParamName("requestId") String requestId, @ParamName("encoding") Encoding encoding);

  /**
   * Returns the response body and size if it were re-encoded with the specified settings. Only
   * applies to images.
   *
   * @param requestId Identifier of the network request to get content for.
   * @param encoding The encoding to use.
   * @param quality The quality of the encoding (0-1). (defaults to 1)
   * @param sizeOnly Whether to only return the size information (defaults to false).
   */
  EncodedResponse getEncodedResponse(
      @ParamName("requestId") String requestId,
      @ParamName("encoding") Encoding encoding,
      @Optional @ParamName("quality") Double quality,
      @Optional @ParamName("sizeOnly") Boolean sizeOnly);
}
