package it.unimi.dsi.fastutil.shorts;

import static org.junit.Assert.assertTrue;
import java.util.Random;

import org.junit.Test;

public class ShortArraysTest {
	
	private static short[] castIdentity( int n ) {
		final short[] a = new short[ n ];
		while( n-- != 0 ) a[ n ] = (short)n;
		return a;
	}


	@Test
	public void testRadixSort1() {
		short[] t = { 2, 1, 0, 4 };
		ShortArrays.radixSort( t );
		for( int i = t.length - 1; i-- != 0; ) assertTrue( t[ i ] <= t[ i + 1 ] );
		
		t = new short[] { 2, -1, 0, -4 };
		ShortArrays.radixSort( t );
		for( int i = t.length - 1; i-- != 0; ) assertTrue( t[ i ] <= t[ i + 1 ] );
		
		t = ShortArrays.shuffle( castIdentity( 100 ), new Random( 0 ) );
		ShortArrays.radixSort( t );
		for( int i = t.length - 1; i-- != 0; ) assertTrue( t[ i ] <= t[ i + 1 ] );

		t = new short[ 100 ];
		Random random = new Random( 0 );
		for( int i = t.length; i-- != 0; ) t[ i ] = (short)random.nextInt();
		ShortArrays.radixSort( t );
		for( int i = t.length - 1; i-- != 0; ) assertTrue( t[ i ] <= t[ i + 1 ] );

		t = new short[ 100000 ];
		random = new Random( 0 );
		for( int i = t.length; i-- != 0; ) t[ i ] = (short)random.nextInt();
		ShortArrays.radixSort( t );
		for( int i = t.length - 1; i-- != 0; ) assertTrue( t[ i ] <= t[ i + 1 ] );

		t = new short[ 10000000 ];
		random = new Random( 0 );
		for( int i = t.length; i-- != 0; ) t[ i ] = (short)random.nextInt();
		ShortArrays.radixSort( t );
		for( int i = t.length - 1; i-- != 0; ) assertTrue( t[ i ] <= t[ i + 1 ] );
	}

	@Test
	public void testRadixSort2() {
		short[][] d = new short[ 2 ][];

		d[ 0 ] = new short[ 10 ];
		for( int i = d[ 0 ].length; i-- != 0; ) d[ 0 ][ i ] = (short)( 3 - i % 3 );
		d[ 1 ] = ShortArrays.shuffle( castIdentity( 10 ), new Random( 0 ) );
		ShortArrays.radixSort( d[ 0 ], d[ 1 ] );
		for( int i = d[ 0 ].length - 1; i-- != 0; ) assertTrue( Integer.toString( i ) + ": <" + d[ 0 ][ i ] + ", " + d[ 1 ][ i ] + ">, <" + d[ 0 ][ i + 1 ] + ", " +  d[ 1 ][ i + 1 ] + ">", d[ 0 ][ i ] < d[ 0 ][ i + 1 ] || d[ 0 ][ i ] == d[ 0 ][ i + 1 ] && d[ 1 ][ i ] <= d[ 1 ][ i + 1 ] );
		
		d[ 0 ] = new short[ 100000 ];
		for( int i = d[ 0 ].length; i-- != 0; ) d[ 0 ][ i ] = (short)( 100 - i % 100 );
		d[ 1 ] = ShortArrays.shuffle( castIdentity( 100000 ), new Random( 6 ) );
		ShortArrays.radixSort( d[ 0 ], d[ 1 ] );
		for( int i = d[ 0 ].length - 1; i-- != 0; ) assertTrue( Integer.toString( i ) + ": <" + d[ 0 ][ i ] + ", " + d[ 1 ][ i ] + ">, <" + d[ 0 ][ i + 1 ] + ", " +  d[ 1 ][ i + 1 ] + ">", d[ 0 ][ i ] < d[ 0 ][ i + 1 ] || d[ 0 ][ i ] == d[ 0 ][ i + 1 ] && d[ 1 ][ i ] <= d[ 1 ][ i + 1 ] );

		d[ 0 ] = new short[ 10 ];
		for( int i = d[ 0 ].length; i-- != 0; ) d[ 0 ][ i ] = (short)( i % 3 - 2 );
		Random random = new Random( 0 );
		d[ 1 ] = new short[ d[ 0 ].length ];
		for( int i = d.length; i-- != 0; ) d[ 1 ][ i ] = (short)random.nextInt();
		ShortArrays.radixSort( d[ 0 ], d[ 1 ] );
		for( int i = d[ 0 ].length - 1; i-- != 0; ) assertTrue( Integer.toString( i ) + ": <" + d[ 0 ][ i ] + ", " + d[ 1 ][ i ] + ">, <" + d[ 0 ][ i + 1 ] + ", " +  d[ 1 ][ i + 1 ] + ">", d[ 0 ][ i ] < d[ 0 ][ i + 1 ] || d[ 0 ][ i ] == d[ 0 ][ i + 1 ] && d[ 1 ][ i ] <= d[ 1 ][ i + 1 ] );
		
		d[ 0 ] = new short[ 100000 ];
		random = new Random( 0 );
		for( int i = d[ 0 ].length; i-- != 0; ) d[ 0 ][ i ] = (short)random.nextInt();
		d[ 1 ] = new short[ d[ 0 ].length ];
		for( int i = d.length; i-- != 0; ) d[ 1 ][ i ] = (short)random.nextInt();
		ShortArrays.radixSort( d[ 0 ], d[ 1 ] );
		for( int i = d[ 0 ].length - 1; i-- != 0; ) assertTrue( Integer.toString( i ) + ": <" + d[ 0 ][ i ] + ", " + d[ 1 ][ i ] + ">, <" + d[ 0 ][ i + 1 ] + ", " +  d[ 1 ][ i + 1 ] + ">", d[ 0 ][ i ] < d[ 0 ][ i + 1 ] || d[ 0 ][ i ] == d[ 0 ][ i + 1 ] && d[ 1 ][ i ] <= d[ 1 ][ i + 1 ] );

		d[ 0 ] = new short[ 10000000 ];
		random = new Random( 0 );
		for( int i = d[ 0 ].length; i-- != 0; ) d[ 0 ][ i ] = (short)random.nextInt();
		d[ 1 ] = new short[ d[ 0 ].length ];
		for( int i = d.length; i-- != 0; ) d[ 1 ][ i ] = (short)random.nextInt();
		ShortArrays.radixSort( d[ 0 ], d[ 1 ] );
		for( int i = d[ 0 ].length - 1; i-- != 0; ) assertTrue( Integer.toString( i ) + ": <" + d[ 0 ][ i ] + ", " + d[ 1 ][ i ] + ">, <" + d[ 0 ][ i + 1 ] + ", " +  d[ 1 ][ i + 1 ] + ">", d[ 0 ][ i ] < d[ 0 ][ i + 1 ] || d[ 0 ][ i ] == d[ 0 ][ i + 1 ] && d[ 1 ][ i ] <= d[ 1 ][ i + 1 ] );
	}

	@Test
	public void testRadixSort() {
		short[][] t = { { 2, 1, 0, 4 } };
		ShortArrays.radixSort( t );
		for( int i = t[ 0 ].length - 1; i-- != 0; ) assertTrue( t[ 0 ][ i ] <= t[ 0 ][ i + 1 ] );
		
		t[ 0 ] = ShortArrays.shuffle( castIdentity( 100 ), new Random( 0 ) );
		ShortArrays.radixSort( t );
		for( int i = t[ 0 ].length - 1; i-- != 0; ) assertTrue( t[ 0 ][ i ] <= t[ 0 ][ i + 1 ] );

		short[][] d = new short[ 2 ][];

		d[ 0 ] = new short[ 10 ];
		for( int i = d[ 0 ].length; i-- != 0; ) d[ 0 ][ i ] = (short)( 3 - i % 3 );
		d[ 1 ] = ShortArrays.shuffle( castIdentity( 10 ), new Random( 0 ) );
		ShortArrays.radixSort( d );
		for( int i = d[ 0 ].length - 1; i-- != 0; ) assertTrue( Integer.toString( i ) + ": <" + d[ 0 ][ i ] + ", " + d[ 1 ][ i ] + ">, <" + d[ 0 ][ i + 1 ] + ", " +  d[ 1 ][ i + 1 ] + ">", d[ 0 ][ i ] < d[ 0 ][ i + 1 ] || d[ 0 ][ i ] == d[ 0 ][ i + 1 ] && d[ 1 ][ i ] <= d[ 1 ][ i + 1 ] );

		
		d[ 0 ] = new short[ 100000 ];
		for( int i = d[ 0 ].length; i-- != 0; ) d[ 0 ][ i ] = (short)( 100 - i % 100 );
		d[ 1 ] = ShortArrays.shuffle( castIdentity( 100000 ), new Random( 6 ) );
		ShortArrays.radixSort( d );
		for( int i = d[ 0 ].length - 1; i-- != 0; ) assertTrue( Integer.toString( i ) + ": <" + d[ 0 ][ i ] + ", " + d[ 1 ][ i ] + ">, <" + d[ 0 ][ i + 1 ] + ", " +  d[ 1 ][ i + 1 ] + ">", d[ 0 ][ i ] < d[ 0 ][ i + 1 ] || d[ 0 ][ i ] == d[ 0 ][ i + 1 ] && d[ 1 ][ i ] <= d[ 1 ][ i + 1 ] );

		d[ 0 ] = new short[ 10 ];
		Random random = new Random( 0 );
		for( int i = d[ 0 ].length; i-- != 0; ) d[ 0 ][ i ] = (short)random.nextInt();
		d[ 1 ] = new short[ d[ 0 ].length ];
		for( int i = d.length; i-- != 0; ) d[ 1 ][ i ] = (short)random.nextInt();
		ShortArrays.radixSort( d );
		for( int i = d[ 0 ].length - 1; i-- != 0; ) assertTrue( Integer.toString( i ) + ": <" + d[ 0 ][ i ] + ", " + d[ 1 ][ i ] + ">, <" + d[ 0 ][ i + 1 ] + ", " +  d[ 1 ][ i + 1 ] + ">", d[ 0 ][ i ] < d[ 0 ][ i + 1 ] || d[ 0 ][ i ] == d[ 0 ][ i + 1 ] && d[ 1 ][ i ] <= d[ 1 ][ i + 1 ] );

		
		d[ 0 ] = new short[ 100000 ];
		random = new Random( 0 );
		for( int i = d[ 0 ].length; i-- != 0; ) d[ 0 ][ i ] = (short)random.nextInt();
		d[ 1 ] = new short[ d[ 0 ].length ];
		for( int i = d.length; i-- != 0; ) d[ 1 ][ i ] = (short)random.nextInt();
		ShortArrays.radixSort( d );
		for( int i = d[ 0 ].length - 1; i-- != 0; ) assertTrue( Integer.toString( i ) + ": <" + d[ 0 ][ i ] + ", " + d[ 1 ][ i ] + ">, <" + d[ 0 ][ i + 1 ] + ", " +  d[ 1 ][ i + 1 ] + ">", d[ 0 ][ i ] < d[ 0 ][ i + 1 ] || d[ 0 ][ i ] == d[ 0 ][ i + 1 ] && d[ 1 ][ i ] <= d[ 1 ][ i + 1 ] );

		d[ 0 ] = new short[ 10000000 ];
		random = new Random( 0 );
		for( int i = d[ 0 ].length; i-- != 0; ) d[ 0 ][ i ] = (short)random.nextInt();
		d[ 1 ] = new short[ d[ 0 ].length ];
		for( int i = d.length; i-- != 0; ) d[ 1 ][ i ] = (short)random.nextInt();
		ShortArrays.radixSort( d );
		for( int i = d[ 0 ].length - 1; i-- != 0; ) assertTrue( Integer.toString( i ) + ": <" + d[ 0 ][ i ] + ", " + d[ 1 ][ i ] + ">, <" + d[ 0 ][ i + 1 ] + ", " +  d[ 1 ][ i + 1 ] + ">", d[ 0 ][ i ] < d[ 0 ][ i + 1 ] || d[ 0 ][ i ] == d[ 0 ][ i + 1 ] && d[ 1 ][ i ] <= d[ 1 ][ i + 1 ] );
	}
}
