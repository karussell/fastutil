package it.unimi.dsi.fastutil.ints;

import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Ignore;
import org.junit.Test;

@SuppressWarnings("rawtypes")
public class IntBigArrayBigListTest {

	private static java.util.Random r = new java.util.Random( 0 );

	private static int genKey() {
		return r.nextInt();
	}

	private static Object[] k, nk;

	private static int kt[];

	private static int nkt[];

	@SuppressWarnings("unchecked")
	protected static void testLists( IntBigList m, IntBigList t, int n, int level ) {
		Exception mThrowsOutOfBounds, tThrowsOutOfBounds;
		Object rt = null;
		int rm = ( 0 );
		if ( level > 4 ) return;
		/* Now we check that both sets agree on random keys. For m we use the polymorphic method. */
		for ( int i = 0; i < n; i++ ) {
			int p = r.nextInt() % ( n * 2 );
			int T = genKey();
			mThrowsOutOfBounds = tThrowsOutOfBounds = null;
			try {
				m.set( p, T );
			}
			catch ( IndexOutOfBoundsException e ) {
				mThrowsOutOfBounds = e;
			}
			try {
				t.set( p, ( Integer.valueOf( T ) ) );
			}
			catch ( IndexOutOfBoundsException e ) {
				tThrowsOutOfBounds = e;
			}
			
			if ( mThrowsOutOfBounds == null ) 
			p = r.nextInt() % ( n * 2 );
			mThrowsOutOfBounds = tThrowsOutOfBounds = null;
			try {
				m.getInt( p );
			}
			catch ( IndexOutOfBoundsException e ) {
				mThrowsOutOfBounds = e;
			}
			try {
				t.get( p );
			}
			catch ( IndexOutOfBoundsException e ) {
				tThrowsOutOfBounds = e;
			}
			assertTrue( "Error (" + level + "): get() divergence at start in IndexOutOfBoundsException for index " + p + "  (" + mThrowsOutOfBounds + ", " + tThrowsOutOfBounds + ")" ,  ( mThrowsOutOfBounds == null ) == ( tThrowsOutOfBounds == null ) );
			if ( mThrowsOutOfBounds == null ) assertTrue( "Error (" + level + "): m and t differ aftre get() on position " + p + " (" + m.getInt( p ) + ", " + t.get( p ) + ")" ,  t.get( p ).equals( ( Integer.valueOf( m.getInt( p ) ) ) ) );
		}
		/* Now we check that both sets agree on random keys. For m we use the standard method. */
		for ( int i = 0; i < n; i++ ) {
			int p = r.nextInt() % ( n * 2 );
			mThrowsOutOfBounds = tThrowsOutOfBounds = null;
			try {
				m.get( p );
			}
			catch ( IndexOutOfBoundsException e ) {
				mThrowsOutOfBounds = e;
			}
			try {
				t.get( p );
			}
			catch ( IndexOutOfBoundsException e ) {
				tThrowsOutOfBounds = e;
			}
			assertTrue( "Error (" + level + "): get() divergence at start in IndexOutOfBoundsException for index " + p+ "  (" + mThrowsOutOfBounds + ", " + tThrowsOutOfBounds + ")" ,  ( mThrowsOutOfBounds == null ) == ( tThrowsOutOfBounds == null ) );
			if ( mThrowsOutOfBounds == null ) assertTrue( "Error (" + level + "): m and t differ at start on position " + p + " (" + m.get( p ) + ", " + t.get( p ) + ")" ,  t.get( p ).equals( m.get( p ) ) );
		}
		/* Now we check that m and t are equal. */
		if ( !m.equals( t ) || !t.equals( m ) ) System.err.println( "m: " + m + " t: " + t );
		assertTrue( "Error (" + level + "): ! m.equals( t ) at start" ,  m.equals( t ) );
		assertTrue( "Error (" + level + "): ! t.equals( m ) at start" ,  t.equals( m ) );
		/* Now we check that m actually holds that data. */
		for ( Iterator i = t.iterator(); i.hasNext(); ) {
			assertTrue( "Error (" + level + "): m and t differ on an entry after insertion (iterating on t)" ,  m.contains( i.next() ) );
		}
		/* Now we check that m actually holds that data, but iterating on m. */
		for ( Iterator i = m.listIterator(); i.hasNext(); ) {
			assertTrue( "Error (" + level + "): m and t differ on an entry after insertion (iterating on m)" ,  t.contains( i.next() ) );
		}
		/*
		 * Now we check that inquiries about random data give the same answer in m and t. For m we
		 * use the polymorphic method.
		 */
		for ( int i = 0; i < n; i++ ) {
			int T = genKey();
			assertTrue( "Error (" + level + "): divergence in content between t and m (polymorphic method)" ,  m.contains( T ) == t.contains( ( Integer.valueOf( T ) ) ) );
		}
		/*
		 * Again, we check that inquiries about random data give the same answer in m and t, but for
		 * m we use the standard method.
		 */
		for ( int i = 0; i < n; i++ ) {
			int T = genKey();
			assertTrue( "Error (" + level + "): divergence in content between t and m (polymorphic method)" ,  m.contains( ( Integer.valueOf( T ) ) ) == t.contains( ( Integer.valueOf( T ) ) ) );
		}
		/* Now we add and remove random data in m and t, checking that the result is the same. */
		for ( int i = 0; i < 2 * n; i++ ) {
			int T = genKey();
			try {
				m.add( T );
			}
			catch ( IndexOutOfBoundsException e ) {
				mThrowsOutOfBounds = e;
			}
			try {
				t.add( ( Integer.valueOf( T ) ) );
			}
			catch ( IndexOutOfBoundsException e ) {
				tThrowsOutOfBounds = e;
			}
			T = genKey();
			int p = r.nextInt() % ( 2 * n + 1 );
			mThrowsOutOfBounds = tThrowsOutOfBounds = null;
			try {
				m.add( p, T );
			}
			catch ( IndexOutOfBoundsException e ) {
				mThrowsOutOfBounds = e;
			}
			try {
				t.add( p, ( Integer.valueOf( T ) ) );
			}
			catch ( IndexOutOfBoundsException e ) {
				tThrowsOutOfBounds = e;
			}
			assertTrue( "Error (" + level + "): add() divergence in IndexOutOfBoundsException for index " + p + " for " + T+ " (" + mThrowsOutOfBounds + ", " + tThrowsOutOfBounds + ")" ,  ( mThrowsOutOfBounds == null ) == ( tThrowsOutOfBounds == null ) );
			p = r.nextInt() % ( 2 * n + 1 );
			mThrowsOutOfBounds = tThrowsOutOfBounds = null;
			try {
				rm = m.removeInt( p );
			}
			catch ( IndexOutOfBoundsException e ) {
				mThrowsOutOfBounds = e;
			}
			try {
				rt = t.remove( p );
			}
			catch ( IndexOutOfBoundsException e ) {
				tThrowsOutOfBounds = e;
			}
			assertTrue( "Error (" + level + "): remove() divergence in IndexOutOfBoundsException for index " + p + " (" + mThrowsOutOfBounds + ", " + tThrowsOutOfBounds + ")" ,  ( mThrowsOutOfBounds == null ) == ( tThrowsOutOfBounds == null ) );
			if ( mThrowsOutOfBounds == null ) assertTrue( "Error (" + level + "): divergence in remove() between t and m (" + rt + ", " + rm + ")" ,  rt.equals( ( Integer.valueOf( rm ) ) ) );
		}
		assertTrue( "Error (" + level + "): ! m.equals( t ) after add/remove" ,  m.equals( t ) );
		assertTrue( "Error (" + level + "): ! t.equals( m ) after add/remove" ,  t.equals( m ) );
		/*
		 * Now we add random data in m and t using addAll on a collection, checking that the result
		 * is the same.
		 */
		for ( int i = 0; i < n; i++ ) {
			int p = r.nextInt() % ( 2 * n + 1 );
			java.util.Collection m1 = new java.util.ArrayList();
			int s = r.nextInt( n / 2 + 1 );
			for ( int j = 0; j < s; j++ )
				m1.add( ( Integer.valueOf( genKey() ) ) );
			mThrowsOutOfBounds = tThrowsOutOfBounds = null;
			try {
				m.addAll( p, m1 );
			}
			catch ( IndexOutOfBoundsException e ) {
				mThrowsOutOfBounds = e;
			}
			try {
				t.addAll( p, m1 );
			}
			catch ( IndexOutOfBoundsException e ) {
				tThrowsOutOfBounds = e;
			}
			assertTrue( "Error (" + level + "): addAll() divergence in IndexOutOfBoundsException for index " + p + " for "+ m1 + " (" + mThrowsOutOfBounds + ", " + tThrowsOutOfBounds + ")" ,  ( mThrowsOutOfBounds == null ) == ( tThrowsOutOfBounds == null ) );
			assertTrue( "Error (" + level + "," + m + t + "): ! m.equals( t ) after addAll" ,  m.equals( t ) );
			assertTrue( "Error (" + level + "," + m + t + "): ! t.equals( m ) after addAll" ,  t.equals( m ) );
		}
		if ( m.size64() > n ) {
			m.size( n );
			while ( t.size() != n )
				t.remove( t.size() - 1 );
		}
		/*
		 * Now we add random data in m and t using addAll on a type-specific collection, checking
		 * that the result is the same.
		 */
		for ( int i = 0; i < n; i++ ) {
			int p = r.nextInt() % ( 2 * n + 1 );
			IntCollection m1 = new IntBigArrayBigList();
			java.util.Collection t1 = new java.util.ArrayList();
			int s = r.nextInt( n / 2 + 1 );
			for ( int j = 0; j < s; j++ ) {
				int x = genKey();
				m1.add( x );
				t1.add( ( Integer.valueOf( x ) ) );
			}
			mThrowsOutOfBounds = tThrowsOutOfBounds = null;
			try {
				m.addAll( p, m1 );
			}
			catch ( IndexOutOfBoundsException e ) {
				mThrowsOutOfBounds = e;
			}
			try {
				t.addAll( p, t1 );
			}
			catch ( IndexOutOfBoundsException e ) {
				tThrowsOutOfBounds = e;
			}
			assertTrue( "Error (" + level + "): polymorphic addAll() divergence in IndexOutOfBoundsException for index "+ p + " for " + m1 + " (" + mThrowsOutOfBounds + ", " + tThrowsOutOfBounds + ")" ,  ( mThrowsOutOfBounds == null ) == ( tThrowsOutOfBounds == null ) );
			assertTrue( "Error (" + level + "," + m + t + "): ! m.equals( t ) after polymorphic addAll" ,  m.equals( t ) );
			assertTrue( "Error (" + level + "," + m + t + "): ! t.equals( m ) after polymorphic addAll" ,  t.equals( m ) );
		}
		if ( m.size64() > n ) {
			m.size( n );
			while ( t.size() != n )
				t.remove( t.size() - 1 );
		}
		/*
		 * Now we add random data in m and t using addAll on a list, checking that the result is the
		 * same.
		 */
		for ( int i = 0; i < n; i++ ) {
			int p = r.nextInt() % ( 2 * n + 1 );
			IntBigList m1 = new IntBigArrayBigList();
			java.util.Collection t1 = new java.util.ArrayList();
			int s = r.nextInt( n / 2 + 1 );
			for ( int j = 0; j < s; j++ ) {
				int x = genKey();
				m1.add( x );
				t1.add( ( Integer.valueOf( x ) ) );
			}
			mThrowsOutOfBounds = tThrowsOutOfBounds = null;
			try {
				m.addAll( p, m1 );
			}
			catch ( IndexOutOfBoundsException e ) {
				mThrowsOutOfBounds = e;
			}
			try {
				t.addAll( p, t1 );
			}
			catch ( IndexOutOfBoundsException e ) {
				tThrowsOutOfBounds = e;
			}
			assertTrue( "Error (" + level + "): list addAll() divergence in IndexOutOfBoundsException for index " + p+ " for " + m1 + " (" + mThrowsOutOfBounds + ", " + tThrowsOutOfBounds + ")" ,  ( mThrowsOutOfBounds == null ) == ( tThrowsOutOfBounds == null ) );
			assertTrue( "Error (" + level + "): ! m.equals( t ) after list addAll" ,  m.equals( t ) );
			assertTrue( "Error (" + level + "): ! t.equals( m ) after list addAll" ,  t.equals( m ) );
		}
		/* Now we check that both sets agree on random keys. For m we use the standard method. */
		for ( int i = 0; i < n; i++ ) {
			int p = r.nextInt() % ( n * 2 );
			mThrowsOutOfBounds = tThrowsOutOfBounds = null;
			try {
				m.get( p );
			}
			catch ( IndexOutOfBoundsException e ) {
				mThrowsOutOfBounds = e;
			}
			try {
				t.get( p );
			}
			catch ( IndexOutOfBoundsException e ) {
				tThrowsOutOfBounds = e;
			}
			assertTrue( "Error (" + level + "): get() divergence in IndexOutOfBoundsException for index " + p + "  ("	+ mThrowsOutOfBounds + ", " + tThrowsOutOfBounds + ")" ,  ( mThrowsOutOfBounds == null ) == ( tThrowsOutOfBounds == null ) );
			if ( mThrowsOutOfBounds == null ) assertTrue( "Error (" + level + "): m and t differ on position " + p + " (" + m.get( p ) + ", " + t.get( p )	+ ")" ,  t.get( p ).equals( m.get( p ) ) );
		}
		/* Now we inquiry about the content with indexOf()/lastIndexOf(). */
		for ( int i = 0; i < 10 * n; i++ ) {
			int T = genKey();
			assertTrue( "Error (" + level + "): indexOf() divergence for " + T + "  (" + m.indexOf( ( Integer.valueOf( T ) ) ) + ", " + t.indexOf( ( Integer.valueOf( T ) ) ) + ")", m.indexOf( ( Integer.valueOf( T ) ) ) == t.indexOf( ( Integer.valueOf( T ) ) ) );
			assertTrue( "Error (" + level + "): lastIndexOf() divergence for " + T + "  (" + m.lastIndexOf( ( Integer.valueOf( T ) ) ) + ", " + t.lastIndexOf( ( Integer.valueOf( T ) ) )	+ ")", m.lastIndexOf( ( Integer.valueOf( T ) ) ) == t.lastIndexOf( ( Integer.valueOf( T ) ) ) );
			assertTrue( "Error (" + level + "): polymorphic indexOf() divergence for " + T + "  (" + m.indexOf( T ) + ", " + t.indexOf( ( Integer.valueOf( T ) ) ) + ")" ,  m.indexOf( T ) == t.indexOf( ( Integer.valueOf( T ) ) ) );
			assertTrue( "Error (" + level + "): polymorphic lastIndexOf() divergence for " + T + "  (" + m.lastIndexOf( T ) + ", " + t.lastIndexOf( ( Integer.valueOf( T ) ) ) + ")" ,  m.lastIndexOf( T ) == t.lastIndexOf( ( Integer.valueOf( T ) ) ) );
		}
		/* Now we check cloning. */
		if ( level == 0 ) {
			assertTrue( "Error (" + level + "): m does not equal m.clone()" ,  m.equals( ( (IntBigArrayBigList)m ).clone() ) );
			assertTrue( "Error (" + level + "): m.clone() does not equal m" ,  ( (IntBigArrayBigList)m ).clone().equals( m ) );
		}
		/* Now we play with constructors. */
		assertTrue( "Error (" + level + "): m does not equal new ( type-specific Collection m )" ,  m.equals( new IntBigArrayBigList( (IntCollection)m ) ) );
		assertTrue( "Error (" + level + "): new ( type-specific nCollection m ) does not equal m" ,  ( new IntBigArrayBigList( (IntCollection)m ) ).equals( m ) );
		assertTrue( "Error (" + level + "): m does not equal new ( type-specific List m )" ,  m.equals( new IntBigArrayBigList( m ) ) );
		assertTrue( "Error (" + level + "): new ( type-specific List m ) does not equal m" ,  ( new IntBigArrayBigList( m ) ).equals( m ) );
		assertTrue( "Error (" + level + "): m does not equal new ( m.listIterator() )" ,  m.equals( new IntBigArrayBigList( m.listIterator() ) ) );
		assertTrue( "Error (" + level + "): new ( m.listIterator() ) does not equal m" ,  ( new IntBigArrayBigList( m.listIterator() ) ).equals( m ) );
		assertTrue( "Error (" + level + "): m does not equal new ( m.type_specific_iterator() )" ,  m.equals( new IntBigArrayBigList( m.iterator() ) ) );
		assertTrue( "Error (" + level + "): new ( m.type_specific_iterator() ) does not equal m" ,  ( new IntBigArrayBigList( m.iterator() ) ).equals( m ) );
		int h = m.hashCode();
		/* Now we save and read m. */
		IntBigList m2 = null;
		try {
			java.io.File ff = new java.io.File( "it.unimi.dsi.fastutil.test" );
			java.io.OutputStream os = new java.io.FileOutputStream( ff );
			java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream( os );
			oos.writeObject( m );
			oos.close();
			java.io.InputStream is = new java.io.FileInputStream( ff );
			java.io.ObjectInputStream ois = new java.io.ObjectInputStream( is );
			m2 = (IntBigList)ois.readObject();
			ois.close();
			ff.delete();
		}
		catch ( Exception e ) {
			e.printStackTrace();
			System.exit( 1 );
		}
		assertTrue( "Error (" + level + "): hashCode() changed after save/read" ,  m2.hashCode() == h );
		/* Now we check that m2 actually holds that data. */
		assertTrue( "Error (" + level + "): ! m2.equals( t ) after save/read" ,  m2.equals( t ) );
		assertTrue( "Error (" + level + "): ! t.equals( m2 ) after save/read" ,  t.equals( m2 ) );
		/* Now we take out of m everything, and check that it is empty. */
		for ( Iterator i = t.iterator(); i.hasNext(); )
			m2.remove( i.next() );
		assertTrue( "Error (" + level + "): m2 is not empty (as it should be)" ,  m2.isEmpty() );
		/* Now we play with iterators. */
		{
			IntBigListIterator i;
			IntBigListIterator j;
			i = m.listIterator();
			j = t.listIterator();
			for ( int k = 0; k < 2 * n; k++ ) {
				assertTrue( "Error (" + level + "): divergence in hasNext()" ,  i.hasNext() == j.hasNext() );
				assertTrue( "Error (" + level + "): divergence in hasPrevious()" ,  i.hasPrevious() == j.hasPrevious() );
				if ( r.nextFloat() < .8 && i.hasNext() ) {
					assertTrue( "Error (" + level + "): divergence in next()" ,  i.next().equals( j.next() ) );
					if ( r.nextFloat() < 0.2 ) {
						i.remove();
						j.remove();
					}
					else if ( r.nextFloat() < 0.2 ) {
						int T = genKey();
						i.set( T );
						j.set( ( Integer.valueOf( T ) ) );
					}
					else if ( r.nextFloat() < 0.2 ) {
						int T = genKey();
						i.add( T );
						j.add( ( Integer.valueOf( T ) ) );
					}
				}
				else if ( r.nextFloat() < .2 && i.hasPrevious() ) {
					assertTrue( "Error (" + level + "): divergence in previous()" ,  i.previous().equals( j.previous() ) );
					if ( r.nextFloat() < 0.2 ) {
						i.remove();
						j.remove();
					}
					else if ( r.nextFloat() < 0.2 ) {
						int T = genKey();
						i.set( T );
						j.set( ( Integer.valueOf( T ) ) );
					}
					else if ( r.nextFloat() < 0.2 ) {
						int T = genKey();
						i.add( T );
						j.add( ( Integer.valueOf( T ) ) );
					}
				}
				assertTrue( "Error (" + level + "): divergence in nextIndex()" ,  i.nextIndex() == j.nextIndex() );
				assertTrue( "Error (" + level + "): divergence in previousIndex()" ,  i.previousIndex() == j.previousIndex() );
			}
		}
		{
			Object I, J;
			int from = r.nextInt( m.size() + 1 );
			IntBigListIterator i;
			IntBigListIterator j;
			i = m.listIterator( from );
			j = t.listIterator( from );
			for ( int k = 0; k < 2 * n; k++ ) {
				assertTrue( "Error (" + level + "): divergence in hasNext() (iterator with starting point " + from + ")" ,  i.hasNext() == j.hasNext() );
				assertTrue( "Error (" + level + "): divergence in hasPrevious() (iterator with starting point " + from + ")" ,  i.hasPrevious() == j.hasPrevious() );
				if ( r.nextFloat() < .8 && i.hasNext() ) {
					I = i.next();
					J = j.next();
					assertTrue( "Error (" + level + "): divergence in next() (" + I + ", " + J + ", iterator with starting point " + from + ")" ,  I.equals( J ) );
					// System.err.println("Done next " + I + " " + J + "  " + badPrevious);
					if ( r.nextFloat() < 0.2 ) {
						// System.err.println("Removing in next");
						i.remove();
						j.remove();
					}
					else if ( r.nextFloat() < 0.2 ) {
						int T = genKey();
						i.set( T );
						j.set( ( Integer.valueOf( T ) ) );
					}
					else if ( r.nextFloat() < 0.2 ) {
						int T = genKey();
						i.add( T );
						j.add( ( Integer.valueOf( T ) ) );
					}
				}
				else if ( r.nextFloat() < .2 && i.hasPrevious() ) {
					I = i.previous();
					J = j.previous();
					assertTrue( "Error (" + level + "): divergence in previous() (" + I + ", " + J + ", iterator with starting point "	+ from + ")" ,  I.equals( J ) );
					if ( r.nextFloat() < 0.2 ) {
						// System.err.println("Removing in prev");
						i.remove();
						j.remove();
					}
					else if ( r.nextFloat() < 0.2 ) {
						int T = genKey();
						i.set( T );
						j.set( ( Integer.valueOf( T ) ) );
					}
					else if ( r.nextFloat() < 0.2 ) {
						int T = genKey();
						i.add( T );
						j.add( ( Integer.valueOf( T ) ) );
					}
				}
			}
		}
		/* Now we check that m actually holds that data. */
		assertTrue( "Error (" + level + "): ! m.equals( t ) after iteration" ,  m.equals( t ) );
		assertTrue( "Error (" + level + "): ! t.equals( m ) after iteration" ,  t.equals( m ) );
		/* Now we select a pair of keys and create a subset. */
		if ( !m.isEmpty() ) {
			int start = r.nextInt( m.size() );
			int end = start + r.nextInt( m.size() - start );
			// System.err.println("Checking subList from " + start + " to " + end + " (level=" +
			// (level+1) + ")..." );
			testLists( m.subList( start, end ), t.subList( start, end ), n, level + 1 );
			assertTrue( "Error (" + level + "," + m + t + "): ! m.equals( t ) after subList" ,  m.equals( t ) );
			assertTrue( "Error (" + level + "): ! t.equals( m ) after subList" ,  t.equals( m ) );
		}
		m.clear();
		t.clear();
		assertTrue( "Error (" + level + "): m is not empty after clear()" ,  m.isEmpty() );
	}

	protected static void test( int n ) {
		IntBigArrayBigList m = new IntBigArrayBigList();
		IntBigList t = IntBigLists.asBigList( new IntArrayList() );
		k = new Object[ n ];
		nk = new Object[ n ];
		kt = new int[ n ];
		nkt = new int[ n ];
		for ( int i = 0; i < n; i++ ) {
			k[ i ] = new Integer( kt[ i ] = genKey() );
			nk[ i ] = new Integer( nkt[ i ] = genKey() );
		}
		/* We add pairs to t. */
		for ( int i = 0; i < n; i++ )
			t.add( (Integer)k[ i ] );
		/* We add to m the same data */
		m.addAll( t );
		testLists( m, t, n, 0 );
		return;
	}

	@Test
	public void test1() {
		test( 1 );
	}

	@Test
	public void test10() {
		test( 10 );
	}

	@Test
	public void test100() {
		test( 100 );
	}

	@Ignore("Too long")
	@Test
	public void test1000() {
		test( 1000 );
	}
}
