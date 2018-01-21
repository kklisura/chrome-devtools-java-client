package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.annotations.Optional;
import com.github.kklisura.cdtp.protocol.types.io.Read;

/**
 * Input/Output operations for streams produced by DevTools.
 */
@Experimental
public interface IO {

	/**
	 * Read a chunk of the stream
	 */
	Read read(String handle, @Optional Integer offset, @Optional Integer size);

	/**
	 * Close the stream, discard any temporary backing storage.
	 */
	void close(String handle);

	/**
	 * Return UUID of Blob object specified by a remote object id.
	 */
	String resolveBlob(String objectId);
}
