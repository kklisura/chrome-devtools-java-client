package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import com.github.kklisura.cdtp.protocol.support.annotations.ParamName;
import com.github.kklisura.cdtp.protocol.support.annotations.Returns;
import com.github.kklisura.cdtp.protocol.types.io.Read;

/**
 * Input/Output operations for streams produced by DevTools.
 */
@Experimental
public interface IO {

	/**
	 * Read a chunk of the stream
	 *
	 * @param handle Handle of the stream to read.
	 */
	Read read(@ParamName("handle") String handle);

	/**
	 * Read a chunk of the stream
	 *
	 * @param handle Handle of the stream to read.
	 * @param offset Seek to the specified offset before reading (if not specificed, proceed with offset following the last read).
	 * @param size Maximum number of bytes to read (left upon the agent discretion if not specified).
	 */
	Read read(@ParamName("handle") String handle, @Optional @ParamName("offset") Integer offset, @Optional @ParamName("size") Integer size);

	/**
	 * Close the stream, discard any temporary backing storage.
	 *
	 * @param handle Handle of the stream to close.
	 */
	void close(@ParamName("handle") String handle);

	/**
	 * Return UUID of Blob object specified by a remote object id.
	 *
	 * @param objectId Object id of a Blob object wrapper.
	 */
	@Returns("uuid")
	String resolveBlob(@ParamName("objectId") String objectId);
}
