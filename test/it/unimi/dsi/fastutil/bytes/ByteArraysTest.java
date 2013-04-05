package it.unimi.dsi.fastutil.bytes;

import static org.junit.Assert.assertTrue;
import java.util.Random;

import org.junit.Test;

public class ByteArraysTest {
	
	private static byte[] castIdentity( int n ) {
		final byte[] a = new byte[ n ];
		while( n-- != 0 ) a[ n ] = (byte)n;
		return a;
	}


	@Test
	public void testRadixSort1() {
		byte[] t = { 2, 1, 0, 4 };
		ByteArrays.radixSort( t );
		for( int i = t.length - 1; i-- != 0; ) assertTrue( t[ i ] <= t[ i + 1 ] );
		
		t = new byte[] { 2, (byte)-1, 0, (byte)-4 };
		ByteArrays.radixSort( t );
		for( int i = t.length - 1; i-- != 0; ) assertTrue( t[ i ] <= t[ i + 1 ] );
		
		t = ByteArrays.shuffle( castIdentity( 100 ), new Random( 0 ) );
		ByteArrays.radixSort( t );
		for( int i = t.length - 1; i-- != 0; ) assertTrue( t[ i ] <= t[ i + 1 ] );

		t = new byte[ 100 ];
		Random random = new Random( 0 );
		for( int i = t.length; i-- != 0; ) t[ i ] = (byte)random.nextInt();
		ByteArrays.radixSort( t );
		for( int i = t.length - 1; i-- != 0; ) assertTrue( t[ i ] <= t[ i + 1 ] );

		t = new byte[ 100000 ];
		random = new Random( 0 );
		for( int i = t.length; i-- != 0; ) t[ i ] = (byte)random.nextInt();
		ByteArrays.radixSort( t );
		for( int i = t.length - 1; i-- != 0; ) assertTrue( t[ i ] <= t[ i + 1 ] );

		t = new byte[ 10000000 ];
		random = new Random( 0 );
		for( int i = t.length; i-- != 0; ) t[ i ] = (byte)random.nextInt();
		ByteArrays.radixSort( t );
		for( int i = t.length - 1; i-- != 0; ) assertTrue( t[ i ] <= t[ i + 1 ] );
	}

	@Test
	public void testRadixSort2() {
		byte[][] d = new byte[ 2 ][];

		d[ 0 ] = new byte[ 10 ];
		for( int i = d[ 0 ].length; i-- != 0; ) d[ 0 ][ i ] = (byte)( 3 - i % 3 );
		d[ 1 ] = ByteArrays.shuffle( castIdentity( 10 ), new Random( 0 ) );
		ByteArrays.radixSort( d[ 0 ], d[ 1 ] );
		for( int i = d[ 0 ].length - 1; i-- != 0; ) assertTrue( Integer.toString( i ) + ": <" + d[ 0 ][ i ] + ", " + d[ 1 ][ i ] + ">, <" + d[ 0 ][ i + 1 ] + ", " +  d[ 1 ][ i + 1 ] + ">", d[ 0 ][ i ] < d[ 0 ][ i + 1 ] || d[ 0 ][ i ] == d[ 0 ][ i + 1 ] && d[ 1 ][ i ] <= d[ 1 ][ i + 1 ] );
		
		d[ 0 ] = new byte[ 100000 ];
		for( int i = d[ 0 ].length; i-- != 0; ) d[ 0 ][ i ] = (byte)( 100 - i % 100 );
		d[ 1 ] = ByteArrays.shuffle( castIdentity( 100000 ), new Random( 6 ) );
		ByteArrays.radixSort( d[ 0 ], d[ 1 ] );
		for( int i = d[ 0 ].length - 1; i-- != 0; ) assertTrue( Integer.toString( i ) + ": <" + d[ 0 ][ i ] + ", " + d[ 1 ][ i ] + ">, <" + d[ 0 ][ i + 1 ] + ", " +  d[ 1 ][ i + 1 ] + ">", d[ 0 ][ i ] < d[ 0 ][ i + 1 ] || d[ 0 ][ i ] == d[ 0 ][ i + 1 ] && d[ 1 ][ i ] <= d[ 1 ][ i + 1 ] );

		d[ 0 ] = new byte[ 10 ];
		for( int i = d[ 0 ].length; i-- != 0; ) d[ 0 ][ i ] = (byte)( i % 3 - 2 );
		Random random = new Random( 0 );
		d[ 1 ] = new byte[ d[ 0 ].length ];
		for( int i = d.length; i-- != 0; ) d[ 1 ][ i ] = (byte)random.nextInt();
		ByteArrays.radixSort( d[ 0 ], d[ 1 ] );
		for( int i = d[ 0 ].length - 1; i-- != 0; ) assertTrue( Integer.toString( i ) + ": <" + d[ 0 ][ i ] + ", " + d[ 1 ][ i ] + ">, <" + d[ 0 ][ i + 1 ] + ", " +  d[ 1 ][ i + 1 ] + ">", d[ 0 ][ i ] < d[ 0 ][ i + 1 ] || d[ 0 ][ i ] == d[ 0 ][ i + 1 ] && d[ 1 ][ i ] <= d[ 1 ][ i + 1 ] );
		
		d[ 0 ] = new byte[ 100000 ];
		random = new Random( 0 );
		for( int i = d[ 0 ].length; i-- != 0; ) d[ 0 ][ i ] = (byte)random.nextInt();
		d[ 1 ] = new byte[ d[ 0 ].length ];
		for( int i = d.length; i-- != 0; ) d[ 1 ][ i ] = (byte)random.nextInt();
		ByteArrays.radixSort( d[ 0 ], d[ 1 ] );
		for( int i = d[ 0 ].length - 1; i-- != 0; ) assertTrue( Integer.toString( i ) + ": <" + d[ 0 ][ i ] + ", " + d[ 1 ][ i ] + ">, <" + d[ 0 ][ i + 1 ] + ", " +  d[ 1 ][ i + 1 ] + ">", d[ 0 ][ i ] < d[ 0 ][ i + 1 ] || d[ 0 ][ i ] == d[ 0 ][ i + 1 ] && d[ 1 ][ i ] <= d[ 1 ][ i + 1 ] );

		d[ 0 ] = new byte[ 10000000 ];
		random = new Random( 0 );
		for( int i = d[ 0 ].length; i-- != 0; ) d[ 0 ][ i ] = (byte)random.nextInt();
		d[ 1 ] = new byte[ d[ 0 ].length ];
		for( int i = d.length; i-- != 0; ) d[ 1 ][ i ] = (byte)random.nextInt();
		ByteArrays.radixSort( d[ 0 ], d[ 1 ] );
		for( int i = d[ 0 ].length - 1; i-- != 0; ) assertTrue( Integer.toString( i ) + ": <" + d[ 0 ][ i ] + ", " + d[ 1 ][ i ] + ">, <" + d[ 0 ][ i + 1 ] + ", " +  d[ 1 ][ i + 1 ] + ">", d[ 0 ][ i ] < d[ 0 ][ i + 1 ] || d[ 0 ][ i ] == d[ 0 ][ i + 1 ] && d[ 1 ][ i ] <= d[ 1 ][ i + 1 ] );
	}

	@Test
	public void testRadixSort() {
		byte[][] t = { { 2, 1, 0, 4 } };
		ByteArrays.radixSort( t );
		for( int i = t[ 0 ].length - 1; i-- != 0; ) assertTrue( t[ 0 ][ i ] <= t[ 0 ][ i + 1 ] );
		
		t[ 0 ] = ByteArrays.shuffle( castIdentity( 100 ), new Random( 0 ) );
		ByteArrays.radixSort( t );
		for( int i = t[ 0 ].length - 1; i-- != 0; ) assertTrue( t[ 0 ][ i ] <= t[ 0 ][ i + 1 ] );

		byte[][] d = new byte[ 2 ][];

		d[ 0 ] = new byte[ 10 ];
		for( int i = d[ 0 ].length; i-- != 0; ) d[ 0 ][ i ] = (byte)( 3 - i % 3 );
		d[ 1 ] = ByteArrays.shuffle( castIdentity( 10 ), new Random( 0 ) );
		ByteArrays.radixSort( d );
		for( int i = d[ 0 ].length - 1; i-- != 0; ) assertTrue( Integer.toString( i ) + ": <" + d[ 0 ][ i ] + ", " + d[ 1 ][ i ] + ">, <" + d[ 0 ][ i + 1 ] + ", " +  d[ 1 ][ i + 1 ] + ">", d[ 0 ][ i ] < d[ 0 ][ i + 1 ] || d[ 0 ][ i ] == d[ 0 ][ i + 1 ] && d[ 1 ][ i ] <= d[ 1 ][ i + 1 ] );

		
		d[ 0 ] = new byte[ 100000 ];
		for( int i = d[ 0 ].length; i-- != 0; ) d[ 0 ][ i ] = (byte)( 100 - i % 100 );
		d[ 1 ] = ByteArrays.shuffle( castIdentity( 100000 ), new Random( 6 ) );
		ByteArrays.radixSort( d );
		for( int i = d[ 0 ].length - 1; i-- != 0; ) assertTrue( Integer.toString( i ) + ": <" + d[ 0 ][ i ] + ", " + d[ 1 ][ i ] + ">, <" + d[ 0 ][ i + 1 ] + ", " +  d[ 1 ][ i + 1 ] + ">", d[ 0 ][ i ] < d[ 0 ][ i + 1 ] || d[ 0 ][ i ] == d[ 0 ][ i + 1 ] && d[ 1 ][ i ] <= d[ 1 ][ i + 1 ] );

		d[ 0 ] = new byte[ 10 ];
		Random random = new Random( 0 );
		for( int i = d[ 0 ].length; i-- != 0; ) d[ 0 ][ i ] = (byte)random.nextInt();
		d[ 1 ] = new byte[ d[ 0 ].length ];
		for( int i = d.length; i-- != 0; ) d[ 1 ][ i ] = (byte)random.nextInt();
		ByteArrays.radixSort( d );
		for( int i = d[ 0 ].length - 1; i-- != 0; ) assertTrue( Integer.toString( i ) + ": <" + d[ 0 ][ i ] + ", " + d[ 1 ][ i ] + ">, <" + d[ 0 ][ i + 1 ] + ", " +  d[ 1 ][ i + 1 ] + ">", d[ 0 ][ i ] < d[ 0 ][ i + 1 ] || d[ 0 ][ i ] == d[ 0 ][ i + 1 ] && d[ 1 ][ i ] <= d[ 1 ][ i + 1 ] );

		
		d[ 0 ] = new byte[ 100000 ];
		random = new Random( 0 );
		for( int i = d[ 0 ].length; i-- != 0; ) d[ 0 ][ i ] = (byte)random.nextInt();
		d[ 1 ] = new byte[ d[ 0 ].length ];
		for( int i = d.length; i-- != 0; ) d[ 1 ][ i ] = (byte)random.nextInt();
		ByteArrays.radixSort( d );
		for( int i = d[ 0 ].length - 1; i-- != 0; ) assertTrue( Integer.toString( i ) + ": <" + d[ 0 ][ i ] + ", " + d[ 1 ][ i ] + ">, <" + d[ 0 ][ i + 1 ] + ", " +  d[ 1 ][ i + 1 ] + ">", d[ 0 ][ i ] < d[ 0 ][ i + 1 ] || d[ 0 ][ i ] == d[ 0 ][ i + 1 ] && d[ 1 ][ i ] <= d[ 1 ][ i + 1 ] );

		d[ 0 ] = new byte[ 10000000 ];
		random = new Random( 0 );
		for( int i = d[ 0 ].length; i-- != 0; ) d[ 0 ][ i ] = (byte)random.nextInt();
		d[ 1 ] = new byte[ d[ 0 ].length ];
		for( int i = d.length; i-- != 0; ) d[ 1 ][ i ] = (byte)random.nextInt();
		ByteArrays.radixSort( d );
		for( int i = d[ 0 ].length - 1; i-- != 0; ) assertTrue( Integer.toString( i ) + ": <" + d[ 0 ][ i ] + ", " + d[ 1 ][ i ] + ">, <" + d[ 0 ][ i + 1 ] + ", " +  d[ 1 ][ i + 1 ] + ">", d[ 0 ][ i ] < d[ 0 ][ i + 1 ] || d[ 0 ][ i ] == d[ 0 ][ i + 1 ] && d[ 1 ][ i ] <= d[ 1 ][ i + 1 ] );
	}
}
