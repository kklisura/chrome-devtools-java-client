package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.annotations.Optional;
import com.github.kklisura.cdtp.protocol.annotations.ParamName;
import com.github.kklisura.cdtp.protocol.annotations.Returns;
import com.github.kklisura.cdtp.protocol.types.io.Read;

/**
 * Input/Output operations for streams produced by DevTools.
 */
@Experimental
public interface IO {

	/**
	 * Read a chunk of the stream
	 */
	Read read(@ParamName("handle") String handle, @Optional @ParamName("offset") Integer offset, @Optional @ParamName("size") Integer size);

	/**
	 * Close the stream, discard any temporary backing storage.
	 */
	void close(@ParamName("handle") String handle);

	/**
	 * Return UUID of Blob object specified by a remote object id.
	 */
	@Returns("uuid")
	String resolveBlob(@ParamName("objectId") String objectId);
}
