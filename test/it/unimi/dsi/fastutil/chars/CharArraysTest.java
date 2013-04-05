package it.unimi.dsi.fastutil.chars;

import static org.junit.Assert.assertTrue;
import java.util.Random;

import org.junit.Test;

public class CharArraysTest {
	
	private static char[] castIdentity( int n ) {
		final char[] a = new char[ n ];
		while( n-- != 0 ) a[ n ] = (char)n;
		return a;
	}


	@Test
	public void testRadixSort1() {
		char[] t = { 2, 1, 0, 4 };
		CharArrays.radixSort( t );
		for( int i = t.length - 1; i-- != 0; ) assertTrue( t[ i ] <= t[ i + 1 ] );
		
		t = new char[] { 2, (char)-1, 0, (char)-4 };
		CharArrays.radixSort( t );
		for( int i = t.length - 1; i-- != 0; ) assertTrue( t[ i ] <= t[ i + 1 ] );
		
		t = CharArrays.shuffle( castIdentity( 100 ), new Random( 0 ) );
		CharArrays.radixSort( t );
		for( int i = t.length - 1; i-- != 0; ) assertTrue( t[ i ] <= t[ i + 1 ] );

		t = new char[ 100 ];
		Random random = new Random( 0 );
		for( int i = t.length; i-- != 0; ) t[ i ] = (char)random.nextInt();
		CharArrays.radixSort( t );
		for( int i = t.length - 1; i-- != 0; ) assertTrue( t[ i ] <= t[ i + 1 ] );

		t = new char[ 100000 ];
		random = new Random( 0 );
		for( int i = t.length; i-- != 0; ) t[ i ] = (char)random.nextInt();
		CharArrays.radixSort( t );
		for( int i = t.length - 1; i-- != 0; ) assertTrue( t[ i ] <= t[ i + 1 ] );

		t = new char[ 10000000 ];
		random = new Random( 0 );
		for( int i = t.length; i-- != 0; ) t[ i ] = (char)random.nextInt();
		CharArrays.radixSort( t );
		for( int i = t.length - 1; i-- != 0; ) assertTrue( t[ i ] <= t[ i + 1 ] );
	}

	@Test
	public void testRadixSort2() {
		char[][] d = new char[ 2 ][];

		d[ 0 ] = new char[ 10 ];
		for( int i = d[ 0 ].length; i-- != 0; ) d[ 0 ][ i ] = (char)( 3 - i % 3 );
		d[ 1 ] = CharArrays.shuffle( castIdentity( 10 ), new Random( 0 ) );
		CharArrays.radixSort( d[ 0 ], d[ 1 ] );
		for( int i = d[ 0 ].length - 1; i-- != 0; ) assertTrue( Integer.toString( i ) + ": <" + d[ 0 ][ i ] + ", " + d[ 1 ][ i ] + ">, <" + d[ 0 ][ i + 1 ] + ", " +  d[ 1 ][ i + 1 ] + ">", d[ 0 ][ i ] < d[ 0 ][ i + 1 ] || d[ 0 ][ i ] == d[ 0 ][ i + 1 ] && d[ 1 ][ i ] <= d[ 1 ][ i + 1 ] );
		
		d[ 0 ] = new char[ 100000 ];
		for( int i = d[ 0 ].length; i-- != 0; ) d[ 0 ][ i ] = (char)( 100 - i % 100 );
		d[ 1 ] = CharArrays.shuffle( castIdentity( 100000 ), new Random( 6 ) );
		CharArrays.radixSort( d[ 0 ], d[ 1 ] );
		for( int i = d[ 0 ].length - 1; i-- != 0; ) assertTrue( Integer.toString( i ) + ": <" + d[ 0 ][ i ] + ", " + d[ 1 ][ i ] + ">, <" + d[ 0 ][ i + 1 ] + ", " +  d[ 1 ][ i + 1 ] + ">", d[ 0 ][ i ] < d[ 0 ][ i + 1 ] || d[ 0 ][ i ] == d[ 0 ][ i + 1 ] && d[ 1 ][ i ] <= d[ 1 ][ i + 1 ] );

		d[ 0 ] = new char[ 10 ];
		for( int i = d[ 0 ].length; i-- != 0; ) d[ 0 ][ i ] = (char)( i % 3 - 2 );
		Random random = new Random( 0 );
		d[ 1 ] = new char[ d[ 0 ].length ];
		for( int i = d.length; i-- != 0; ) d[ 1 ][ i ] = (char)random.nextInt();
		CharArrays.radixSort( d[ 0 ], d[ 1 ] );
		for( int i = d[ 0 ].length - 1; i-- != 0; ) assertTrue( Integer.toString( i ) + ": <" + d[ 0 ][ i ] + ", " + d[ 1 ][ i ] + ">, <" + d[ 0 ][ i + 1 ] + ", " +  d[ 1 ][ i + 1 ] + ">", d[ 0 ][ i ] < d[ 0 ][ i + 1 ] || d[ 0 ][ i ] == d[ 0 ][ i + 1 ] && d[ 1 ][ i ] <= d[ 1 ][ i + 1 ] );
		
		d[ 0 ] = new char[ 100000 ];
		random = new Random( 0 );
		for( int i = d[ 0 ].length; i-- != 0; ) d[ 0 ][ i ] = (char)random.nextInt();
		d[ 1 ] = new char[ d[ 0 ].length ];
		for( int i = d.length; i-- != 0; ) d[ 1 ][ i ] = (char)random.nextInt();
		CharArrays.radixSort( d[ 0 ], d[ 1 ] );
		for( int i = d[ 0 ].length - 1; i-- != 0; ) assertTrue( Integer.toString( i ) + ": <" + d[ 0 ][ i ] + ", " + d[ 1 ][ i ] + ">, <" + d[ 0 ][ i + 1 ] + ", " +  d[ 1 ][ i + 1 ] + ">", d[ 0 ][ i ] < d[ 0 ][ i + 1 ] || d[ 0 ][ i ] == d[ 0 ][ i + 1 ] && d[ 1 ][ i ] <= d[ 1 ][ i + 1 ] );

		d[ 0 ] = new char[ 10000000 ];
		random = new Random( 0 );
		for( int i = d[ 0 ].length; i-- != 0; ) d[ 0 ][ i ] = (char)random.nextInt();
		d[ 1 ] = new char[ d[ 0 ].length ];
		for( int i = d.length; i-- != 0; ) d[ 1 ][ i ] = (char)random.nextInt();
		CharArrays.radixSort( d[ 0 ], d[ 1 ] );
		for( int i = d[ 0 ].length - 1; i-- != 0; ) assertTrue( Integer.toString( i ) + ": <" + d[ 0 ][ i ] + ", " + d[ 1 ][ i ] + ">, <" + d[ 0 ][ i + 1 ] + ", " +  d[ 1 ][ i + 1 ] + ">", d[ 0 ][ i ] < d[ 0 ][ i + 1 ] || d[ 0 ][ i ] == d[ 0 ][ i + 1 ] && d[ 1 ][ i ] <= d[ 1 ][ i + 1 ] );
	}

	@Test
	public void testRadixSort() {
		char[][] t = { { 2, 1, 0, 4 } };
		CharArrays.radixSort( t );
		for( int i = t[ 0 ].length - 1; i-- != 0; ) assertTrue( t[ 0 ][ i ] <= t[ 0 ][ i + 1 ] );
		
		t[ 0 ] = CharArrays.shuffle( castIdentity( 100 ), new Random( 0 ) );
		CharArrays.radixSort( t );
		for( int i = t[ 0 ].length - 1; i-- != 0; ) assertTrue( t[ 0 ][ i ] <= t[ 0 ][ i + 1 ] );

		char[][] d = new char[ 2 ][];

		d[ 0 ] = new char[ 10 ];
		for( int i = d[ 0 ].length; i-- != 0; ) d[ 0 ][ i ] = (char)( 3 - i % 3 );
		d[ 1 ] = CharArrays.shuffle( castIdentity( 10 ), new Random( 0 ) );
		CharArrays.radixSort( d );
		for( int i = d[ 0 ].length - 1; i-- != 0; ) assertTrue( Integer.toString( i ) + ": <" + d[ 0 ][ i ] + ", " + d[ 1 ][ i ] + ">, <" + d[ 0 ][ i + 1 ] + ", " +  d[ 1 ][ i + 1 ] + ">", d[ 0 ][ i ] < d[ 0 ][ i + 1 ] || d[ 0 ][ i ] == d[ 0 ][ i + 1 ] && d[ 1 ][ i ] <= d[ 1 ][ i + 1 ] );

		
		d[ 0 ] = new char[ 100000 ];
		for( int i = d[ 0 ].length; i-- != 0; ) d[ 0 ][ i ] = (char)( 100 - i % 100 );
		d[ 1 ] = CharArrays.shuffle( castIdentity( 100000 ), new Random( 6 ) );
		CharArrays.radixSort( d );
		for( int i = d[ 0 ].length - 1; i-- != 0; ) assertTrue( Integer.toString( i ) + ": <" + d[ 0 ][ i ] + ", " + d[ 1 ][ i ] + ">, <" + d[ 0 ][ i + 1 ] + ", " +  d[ 1 ][ i + 1 ] + ">", d[ 0 ][ i ] < d[ 0 ][ i + 1 ] || d[ 0 ][ i ] == d[ 0 ][ i + 1 ] && d[ 1 ][ i ] <= d[ 1 ][ i + 1 ] );

		d[ 0 ] = new char[ 10 ];
		Random random = new Random( 0 );
		for( int i = d[ 0 ].length; i-- != 0; ) d[ 0 ][ i ] = (char)random.nextInt();
		d[ 1 ] = new char[ d[ 0 ].length ];
		for( int i = d.length; i-- != 0; ) d[ 1 ][ i ] = (char)random.nextInt();
		CharArrays.radixSort( d );
		for( int i = d[ 0 ].length - 1; i-- != 0; ) assertTrue( Integer.toString( i ) + ": <" + d[ 0 ][ i ] + ", " + d[ 1 ][ i ] + ">, <" + d[ 0 ][ i + 1 ] + ", " +  d[ 1 ][ i + 1 ] + ">", d[ 0 ][ i ] < d[ 0 ][ i + 1 ] || d[ 0 ][ i ] == d[ 0 ][ i + 1 ] && d[ 1 ][ i ] <= d[ 1 ][ i + 1 ] );

		
		d[ 0 ] = new char[ 100000 ];
		random = new Random( 0 );
		for( int i = d[ 0 ].length; i-- != 0; ) d[ 0 ][ i ] = (char)random.nextInt();
		d[ 1 ] = new char[ d[ 0 ].length ];
		for( int i = d.length; i-- != 0; ) d[ 1 ][ i ] = (char)random.nextInt();
		CharArrays.radixSort( d );
		for( int i = d[ 0 ].length - 1; i-- != 0; ) assertTrue( Integer.toString( i ) + ": <" + d[ 0 ][ i ] + ", " + d[ 1 ][ i ] + ">, <" + d[ 0 ][ i + 1 ] + ", " +  d[ 1 ][ i + 1 ] + ">", d[ 0 ][ i ] < d[ 0 ][ i + 1 ] || d[ 0 ][ i ] == d[ 0 ][ i + 1 ] && d[ 1 ][ i ] <= d[ 1 ][ i + 1 ] );

		d[ 0 ] = new char[ 10000000 ];
		random = new Random( 0 );
		for( int i = d[ 0 ].length; i-- != 0; ) d[ 0 ][ i ] = (char)random.nextInt();
		d[ 1 ] = new char[ d[ 0 ].length ];
		for( int i = d.length; i-- != 0; ) d[ 1 ][ i ] = (char)random.nextInt();
		CharArrays.radixSort( d );
		for( int i = d[ 0 ].length - 1; i-- != 0; ) assertTrue( Integer.toString( i ) + ": <" + d[ 0 ][ i ] + ", " + d[ 1 ][ i ] + ">, <" + d[ 0 ][ i + 1 ] + ", " +  d[ 1 ][ i + 1 ] + ">", d[ 0 ][ i ] < d[ 0 ][ i + 1 ] || d[ 0 ][ i ] == d[ 0 ][ i + 1 ] && d[ 1 ][ i ] <= d[ 1 ][ i + 1 ] );
	}
}
