package it.unimi.dsi.fastutil.io;

/*		 
 * fastutil: Fast & compact type-specific collections for Java
 *
 * Copyright (C) 2005-2011 Sebastiano Vigna 
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */

import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

/** Lightweight, unsynchronized output stream buffering class with
 *  {@linkplain MeasurableStream measurability} and
 *  {@linkplain RepositionableStream repositionability}.
 *
 * <P>This class provides buffering for output streams, but it does so with 
 * purposes and an internal logic that are radically different from the ones
 * adopted in {@link java.io.BufferedOutputStream}. The main features follow.
 * 
 * <ul>
 * <li><P>All methods are unsychronized.
 * 
 * <li><P>As an additional feature, this class implements the {@link
 * RepositionableStream} and {@link MeasurableStream} interfaces.  
 * An instance of this class will try to cast
 * the underlying byte stream to a {@link RepositionableStream} and to fetch by
 * reflection the {@link java.nio.channels.FileChannel} underlying the given
 * output stream, in this order. If either reference can be successfully
 * fetched, you can use {@link #position(long)} to reposition the stream.
 * Much in the same way, an instance of this class will try to cast the
 * the underlying byte stream to a {@link MeasurableStream}, and if this
 * operation is successful, or if a {@link java.nio.channels.FileChannel} can
 * be detected, then {@link #position()} and {@link #length()} will work as expected.
 * </ul>
 * @since 4.4
 */

public class FastBufferedOutputStream extends MeasurableOutputStream implements RepositionableStream {
	private static final boolean ASSERTS = false;
	
	/** The default size of the internal buffer in bytes (8Ki). */
	public final static int DEFAULT_BUFFER_SIZE = 8 * 1024;

	/** The internal buffer. */
	protected byte buffer[];

	/** The current position in the buffer. */
	protected int pos;

	/** The number of buffer bytes available starting from {@link #pos} 
	 * (it must be always equal to <code>buffer.length - pos</code>). */
	protected int avail;

	/** The underlying output stream. */
	protected OutputStream os;

	/** The cached file channel underlying {@link #os}, if any. */
	private FileChannel fileChannel;

	/** {@link #os} cast to a positionable stream, if possible. */
	private RepositionableStream repositionableStream;

	/** {@link #os} cast to a measurable stream, if possible. */
	private MeasurableStream measurableStream;

	private static int ensureBufferSize( final int bufferSize ) {
		if ( bufferSize <= 0 ) throw new IllegalArgumentException( "Illegal buffer size: " + bufferSize );
		return bufferSize;
	}
		
	/** Creates a new fast buffered output stream by wrapping a given output stream with a given buffer. 
	 *
	 * @param os an output stream to wrap.
	 * @param buffer a buffer of positive length.
	 */
	public FastBufferedOutputStream( final OutputStream os, final byte[] buffer ) {
		this.os = os;
		ensureBufferSize( buffer.length );
		this.buffer = buffer;
		avail = buffer.length;
		
		if ( os instanceof RepositionableStream ) repositionableStream = (RepositionableStream)os;
		if ( os instanceof MeasurableStream ) measurableStream = (MeasurableStream)os;
			
		if ( repositionableStream == null ) {
				
			try {
				fileChannel = (FileChannel)( os.getClass().getMethod( "getChannel", new Class[] {} ) ).invoke( os, new Object[] {} );
			}
			catch( IllegalAccessException e ) {}
			catch( IllegalArgumentException e ) {}
			catch( NoSuchMethodException e ) {}
			catch( java.lang.reflect.InvocationTargetException e ) {}
			catch( ClassCastException e ) {}
		}

	}

	/** Creates a new fast buffered output stream by wrapping a given output stream with a given buffer size. 
	 *
	 * @param os an output stream to wrap.
	 * @param bufferSize the size in bytes of the internal buffer.
	 */
	public FastBufferedOutputStream( final OutputStream os, final int bufferSize ) {
		this( os, new byte[ ensureBufferSize( bufferSize ) ] );
	}

	/** Creates a new fast buffered ouptut stream by wrapping a given output stream with a buffer of {@link #DEFAULT_BUFFER_SIZE} bytes. 
	 *
	 * @param os an output stream to wrap.
	 */
	public FastBufferedOutputStream( final OutputStream os ) {
		this( os, DEFAULT_BUFFER_SIZE );
	}

	private void dumpBuffer( final boolean ifFull ) throws IOException {
		if ( ! ifFull || avail == 0 ) {
			os.write( buffer, 0, pos );
			pos = 0;
			avail = buffer.length;
		}
	}

	public void write( final int b ) throws IOException {
		if ( ASSERTS ) assert avail > 0;
		avail--;
		buffer[ pos++ ] = (byte)b;
		dumpBuffer( true );
	}


	public void write( final byte b[], final int offset, final int length ) throws IOException {
		if ( length >= buffer.length ) {
			dumpBuffer( false );
			os.write( b, offset, length );
			return;
		}
		
		if ( length <= avail ) {
			// Copy in buffer
			System.arraycopy( b, offset, buffer, pos, length );
			pos += length;
			avail -= length;
			dumpBuffer( true );
			return;
		}
		
		dumpBuffer( false );
		System.arraycopy( b, offset, buffer, 0, length );
		pos = length;
		avail -= length;
	}

	public void flush() throws IOException {
		dumpBuffer( false );
		os.flush();
	}
	
	public void close() throws IOException {
		if ( os == null ) return;
		flush();
		if ( os != System.out ) os.close();
		os = null;
		buffer = null;
	}
	
	public long position() throws IOException {
		if ( repositionableStream != null ) return repositionableStream.position() + pos;
		else if ( measurableStream != null ) return measurableStream.position() + pos;
		else if ( fileChannel != null ) return fileChannel.position() + pos;
		else throw new UnsupportedOperationException( "position() can only be called if the underlying byte stream implements the MeasurableStream or RepositionableStream interface or if the getChannel() method of the underlying byte stream exists and returns a FileChannel" );
	}

	/** Repositions the stream.
	 * 
	 * <p>Note that this method performs a {@link #flush()} before changing the underlying stream position.
	 */
	
	public void position( final long newPosition ) throws IOException {
		flush();
		if ( repositionableStream != null ) repositionableStream.position( newPosition  );
		else if ( fileChannel != null ) fileChannel.position( newPosition );
		else throw new UnsupportedOperationException( "position() can only be called if the underlying byte stream implements the RepositionableStream interface or if the getChannel() method of the underlying byte stream exists and returns a FileChannel" );
	}

	/** Returns the length of the underlying output stream, if it is {@linkplain MeasurableStream measurable}.
	 *
	 * <p>Note that this method performs a {@link #flush()} before detecting the length.
	 * 
	 * @return the length of the underlying output stream.
	 * @throws UnsupportedOperationException if the underlying output stream is not {@linkplain MeasurableStream measurable} and
	 * cannot provide a {@link FileChannel}.
	 */

	public long length() throws IOException {
		flush();
		if ( measurableStream != null ) return measurableStream.length();
		if ( fileChannel != null ) return fileChannel.size();
		throw new UnsupportedOperationException();
	}
}
