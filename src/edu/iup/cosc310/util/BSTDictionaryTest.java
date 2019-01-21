package edu.iup.cosc310.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.iup.cosc310.util.BSTDictionary.BSTIterator;

/**
 * JUnit test class for the BSTDictionary
 * 
 * @author Nicholas Sycz
 *
 */
class BSTDictionaryTest {

	Dictionary<String, Integer> d = new BSTDictionary<String, Integer>();

	/**
	 * method with items added into the tree
	 */
	public void tree() {
		d.put("apple", 1);
		d.put("banana", 2);
		d.put("coke", 3);
		d.put("dog", 4);
		d.put("elephant", 5);
		d.put("ale", 6);
		d.put("cat", 7);
	}

	/*
	 * test for putting items into the tree
	 */
	@Test
	public void testPut() {
		tree();

	}

	// Test for the get method
	@Test
	void testGet() {
		tree();
		assertEquals(Integer.valueOf(1), d.get("apple"));
		assertEquals(Integer.valueOf(2), d.get("banana"));
		assertEquals(Integer.valueOf(3), d.get("coke"));
		assertEquals(Integer.valueOf(4), d.get("dog"));
		assertEquals(Integer.valueOf(5), d.get("elephant"));
		assertEquals(Integer.valueOf(6), d.get("ale"));
		assertEquals(Integer.valueOf(7), d.get("cat"));
	}

	/*
	 * testing the remove method
	 */
	@Test
	void testRemove() {
		tree();

		assertEquals(Integer.valueOf(1), d.remove("apple"));
		BSTIterator iter = (BSTIterator) d.keys();
		assertEquals(true, iter.hasNext());
		assertEquals("ale", iter.next());
		assertEquals(6, d.noKeys());
		assertEquals(Integer.valueOf(3), d.remove("coke"));
		assertEquals(5, d.noKeys());
		assertEquals(Integer.valueOf(5), d.remove("elephant"));
		assertEquals(4, d.noKeys());
		assertEquals(Integer.valueOf(4), d.remove("dog"));
		assertEquals(3, d.noKeys());
		assertEquals(Integer.valueOf(2), d.remove("banana"));
		assertEquals(2, d.noKeys());
		assertEquals(null, d.remove("pineapples"));
		tree();
		assertEquals(Integer.valueOf(3), d.remove("coke"));

	}

	/*
	 * testing removing the entire list then adding one.
	 */
	@Test
	void totalRemove() {
		tree();
		assertEquals(Integer.valueOf(6), d.remove("ale"));
		assertEquals(6, d.noKeys());
		assertEquals(Integer.valueOf(2), d.remove("banana"));
		assertEquals(5, d.noKeys());
		assertEquals(Integer.valueOf(4), d.remove("dog"));
		assertEquals(4, d.noKeys());
		assertEquals(Integer.valueOf(1), d.remove("apple"));
		assertEquals(3, d.noKeys());
		assertEquals(Integer.valueOf(5), d.remove("elephant"));
		assertEquals(2, d.noKeys());
		assertEquals(Integer.valueOf(7), d.remove("cat"));
		assertEquals(1, d.noKeys());
		assertEquals(Integer.valueOf(3), d.remove("coke"));
		assertEquals(0, d.noKeys());
		assertEquals(true, d.isEmpty());
		d.put("fire", 2);
		assertEquals(false, d.isEmpty());
	}

	/*
	 * testing the iterator
	 */
	@Test
	void testIterator() {
		tree();
		BSTIterator iter = (BSTIterator) d.keys();
		assertEquals(true, iter.hasNext());
		assertEquals("ale", iter.next());
		assertEquals(true, iter.hasNext());
		assertEquals("apple", iter.next());
		assertEquals(true, iter.hasNext());
		assertEquals("banana", iter.next());
		assertEquals(true, iter.hasNext());
		assertEquals("cat", iter.next());
		assertEquals(true, iter.hasNext());
		assertEquals("coke", iter.next());
		assertEquals(true, iter.hasNext());
		assertEquals("dog", iter.next());
		assertEquals(true, iter.hasNext());
		assertEquals("elephant", iter.next());
		assertEquals(false, iter.hasNext());

	}

	/*
	 * testing the isEmpty method when there's nothing in the tree and when there
	 * are items in the tree
	 */
	@Test
	void testIsEmpty() {
		BSTIterator iter = (BSTIterator) d.keys();
		assertEquals(true, d.isEmpty());
		assertEquals(false, iter.hasNext());
		tree();
		BSTIterator iter2 = (BSTIterator) d.keys();
		assertEquals(false, d.isEmpty());
		assertEquals(true, iter2.hasNext());
		assertEquals("ale", iter2.next());
	}

	/*
	 * testing the number of keys
	 */
	@Test
	void testNoKeys() {
		tree();
		assertEquals(d.noKeys(), 7);
	}
}
