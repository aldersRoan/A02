package a02;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DequeTest {
	private Deque<String> deque;

	@BeforeEach
	void setUp() throws Exception {
		deque = new Deque<String>();
	}

	@Test
	final void testDeque() {
		fail("Not yet implemented");
	}

	@Test
	final void testIsEmptyTrue() {
		assertTrue(deque.isEmpty());
	}
	
	@Test
	final void testIsEmptyFalse() {
		deque.addFirst("Hi");
		assertFalse(deque.isEmpty());
	}

	@Test
	final void testSize0() {
		int expected = 0;
		int actual = deque.size();
		assertEquals(expected,actual);
	}
	
	@Test
	final void testSize3() {
		int expected = 3;
		deque.addFirst("Hi");
		deque.addLast("Bonjour");
		deque.addFirst("Hola");
		int actual = deque.size();
		assertEquals(expected,actual);
	}

	@Test
	final void testAddFirst() {
		deque.addFirst("Yes");
		deque.addFirst("No");
		
		String actual = deque.peekFirst();
		String expected = "No";
		assertEquals(expected, actual);
	}

	@Test
	final void testAddLast() {
		deque.addLast("Yes");
		deque.addLast("No");
		
		String actual = deque.peekFirst();
		String expected = "Yes";
		assertEquals(expected, actual);
	}

	@Test
	final void testRemoveFirst() {
		deque.addFirst("Hi");
		deque.addLast("Bonjour");
		deque.addFirst("Hola");
		
		String actual = deque.removeFirst();
		String expected = "Hola";
		
		assertEquals(expected, actual);
	}

	@Test
	final void testRemoveLast() {
		deque.addFirst("Hi");
		deque.addLast("Bonjour");
		deque.addFirst("Hola");
		
		String actual = deque.removeLast();
		String expected = "Bonjour";
		
		assertEquals(expected, actual);
	}

	@Test
	final void testIterator() {
		fail("Not yet implemented");
	}

	@Test
	final void testPeekFirst() {
		deque.addFirst("Hi");
		deque.addLast("Bonjour");
		deque.addFirst("Hola");
		
		String actual = deque.peekFirst();
		String expected = "Hola";
		
		assertEquals(expected, actual);
	}

}
