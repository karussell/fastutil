package it.unimi.dsi.fastutil.ints;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IntArrayFIFOQueueTest {
	
	@Test
	public void testEnqueueDequeue() {
		IntArrayFIFOQueue q = new IntArrayFIFOQueue();
		for( int i = 0; i < 100; i++ ) {
			q.enqueue( i );
			assertEquals( i, q.lastInt() );
		}
		for( int i = 0; i < 100; i++ ) {
			assertEquals( i, q.firstInt() );
			assertEquals( i, q.dequeueInt() );
			if ( i != 99 ) assertEquals( 99, q.lastInt() );
		}

		q = new IntArrayFIFOQueue( 10 );
		for( int i = 0; i < 100; i++ ) {
			q.enqueue( i );
			assertEquals( i, q.lastInt() );
		}
		for( int i = 0; i < 100; i++ ) {
			assertEquals( i, q.firstInt() );
			assertEquals( i, q.dequeueInt() );
			if ( i != 99 ) assertEquals( 99, q.lastInt() );
		}

		q = new IntArrayFIFOQueue( 200 );
		for( int i = 0; i < 100; i++ ) {
			q.enqueue( i );
			assertEquals( i, q.lastInt() );
		}
		for( int i = 0; i < 100; i++ ) {
			assertEquals( i, q.firstInt() );
			assertEquals( i, q.dequeueInt() );
			if ( i != 99 ) assertEquals( 99, q.lastInt() );
		}
	}

	@Test
	public void testMix() {
		IntArrayFIFOQueue q = new IntArrayFIFOQueue();
		for( int i = 0, p = 0; i < 200; i++ ) {
			for( int j = 0; j < 20; j++ ) {
				q.enqueue( j + i * 20 );
				assertEquals( j + i * 20, q.lastInt() );
			}
			for( int j = 0; j < 10; j++ ) assertEquals( p++, q.dequeueInt() );
		}
		
		q = new IntArrayFIFOQueue( 10 );
		for( int i = 0, p = 0; i < 200; i++ ) {
			for( int j = 0; j < 20; j++ ) {
				q.enqueue( j + i * 20 );
				assertEquals( j + i * 20, q.lastInt() );
			}
			for( int j = 0; j < 10; j++ ) assertEquals( p++, q.dequeueInt() );
		}

		q = new IntArrayFIFOQueue( 200 );
		for( int i = 0, p = 0; i < 200; i++ ) {
			for( int j = 0; j < 20; j++ ) {
				q.enqueue( j + i * 20 );
				assertEquals( j + i * 20, q.lastInt() );
			}
			for( int j = 0; j < 10; j++ ) assertEquals( p++, q.dequeueInt() );
		}
	}

	@Test
	public void testWrap() {
		IntArrayFIFOQueue q = new IntArrayFIFOQueue( 30 );
		for( int i = 0; i < 20; i++ ) {
			q.enqueue( i );
			assertEquals( i, q.lastInt() );
		}
		for( int j = 0; j < 10; j++ ) assertEquals( j, q.dequeueInt() );
		for( int i = 0; i < 15; i++ ) {
			q.enqueue( i );
			assertEquals( i, q.lastInt() );
		}
		for( int j = 10; j < 20; j++ ) assertEquals( j, q.dequeueInt() );
		for( int j = 0; j < 15; j++ ) assertEquals( j, q.dequeueInt() );
	}

	@Test
	public void testTrim() {
		IntArrayFIFOQueue q = new IntArrayFIFOQueue( 30 );
		for( int j = 0; j < 20; j++ ) q.enqueue( j );
		for( int j = 0; j < 10; j++ ) assertEquals( j, q.dequeueInt() );
		for( int j = 0; j < 15; j++ ) q.enqueue( j );
		
		q.trim();
		for( int j = 10; j < 20; j++ ) assertEquals( j, q.dequeueInt() );
		for( int j = 0; j < 15; j++ ) assertEquals( j, q.dequeueInt() );
		
		q = new IntArrayFIFOQueue( 30 );
		for( int j = 0; j < 20; j++ ) q.enqueue( j );
		q.trim();
		for( int j = 0; j < 20; j++ ) assertEquals( j, q.dequeueInt() );
	}

	@Test
	public void testDeque() {
		IntArrayFIFOQueue q = new IntArrayFIFOQueue( 4 );
		q.enqueue( 0 );
		q.enqueue( 1 );
		q.enqueue( 2 );
		assertEquals( q.dequeueInt(), 0 );
		assertEquals( q.dequeueInt(), 1 );
		q.enqueue( 3 );
		assertEquals( q.dequeueLastInt(), 3 );
		assertEquals( q.dequeueLastInt(), 2 );
		q.enqueueFirst( 1 );
		q.enqueueFirst( 0 );
		assertEquals( 0, q.dequeueInt() );
		assertEquals( 1, q.dequeueInt() );
		
		
		
		q = new IntArrayFIFOQueue( 4 );
		q.enqueueFirst( 0 );
		q.enqueueFirst( 1 );
		assertEquals( 1, q.dequeueInt() );
		assertEquals( 0, q.dequeueInt() );
		q.enqueueFirst( 0 );
		q.enqueueFirst( 1 );
		q.enqueueFirst( 2 );
		q.enqueueFirst( 3 );
		assertEquals( 3, q.dequeueInt() );
		assertEquals( 2, q.dequeueInt() );
		assertEquals( 1, q.dequeueInt() );
		assertEquals( 0, q.dequeueInt() );
	}

	@Test
	public void testImmediateReduce() {
		IntArrayFIFOQueue q = new IntArrayFIFOQueue();
		q.enqueue( 0 );
		q.dequeue();
	}
}
