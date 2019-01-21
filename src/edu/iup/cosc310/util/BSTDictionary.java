package edu.iup.cosc310.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * BSTDictionary class implementing the dictionary interface
 * 
 * @author Nicholas Sycz
 *
 * @param <K>
 *            key generic
 * @param <V>
 *            value generic
 */
public class BSTDictionary<K, V> implements Dictionary<K, V> {

	private int noKeys = 0;
	private V value;
	private K key;
	private BSTDictionary<K, V> left;
	private BSTDictionary<K, V> right;

	/**
	 * BSTDictionary constructor setting the left, right to null and the number of keys to be zero
	 */
	public BSTDictionary() {
		super();
		left = null;
		right = null;
	}

	/**
	 * Iterator inner class for the implementation of iterator
	 *
	 * @author Nicholas Sycz
	 *
	 */
	public class BSTIterator<K> implements Iterator<K> {
		Stack<BSTDictionary<K, V>> stack = new Stack<BSTDictionary<K, V>>();

		/**
		 * iterator constructor
		 * 
		 * @param tree
		 *            a bsdictionary to pushLeft
		 */
		public BSTIterator(BSTDictionary<K, V> tree) {
			super();
			pushLeft(tree);
		}

		/**
		 * pushLeft method for pushing an item onto the stack
		 * 
		 * @param node
		 *            item to be pushed onto the stack
		 */
		private void pushLeft(BSTDictionary<K, V> node) {
			if (!node.isEmpty()) {
				stack.push(node);
				pushLeft(node.left);
			}

		}
		
		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		@Override
		public K next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			BSTDictionary<K, V> subtree = stack.pop();
			pushLeft(subtree.right);
			return subtree.key;
		}

	}

	@Override
	public V put(K key, V value) {

		V preValue = null;
		
		// check if it's empty, return a new node with the key and value
		if (this.key == null) {
			this.key = key;
			this.value = value;
			this.right = new BSTDictionary<K, V>();
			this.left = new BSTDictionary<K, V>();
			this.noKeys = 1;
			return preValue;
		}

		// compare the original key with the new key
		@SuppressWarnings("unchecked")
		int comp = ((Comparable<K>) key).compareTo(this.key);
		if (comp > 0) {
			this.right.put(key, value);
		} else if (comp < 0) {
			this.left.put(key, value);
		} else if (comp == 0) {
			preValue = this.value;
			this.value = value;
		}
		if (preValue == null) {
			noKeys++;
		}

		return preValue;
	}

	@Override
	public V get(K key) {

		// if it is empty, return a null value.
		if (isEmpty()) {
			return null;
		}

		// compare the original key with the new key
		@SuppressWarnings("unchecked")
		int comp = ((Comparable<K>) key).compareTo(this.key);
		
		// uses recursion to get the key if the statements are true
		if (comp > 0) {
			return right.get(key);
		} else if (comp < 0) {
			return left.get(key);
		}
		return value;
	}

	@Override
	public V remove(K key) {
		V preValue = null;
		
		// return a null if the is empty check is true
		if (isEmpty()) {
			return null;
		}
		
		// compare the original key with the new key
		@SuppressWarnings("unchecked")
		int comp = ((Comparable<K>) key).compareTo(this.key);
		// checking if the comparison between the two keys is 0.
		// if true, it will test conditions for copying the items.
		if (comp == 0) {
			preValue = this.value;
			if (noKeys == 1) {
				this.key = null;
				this.value = null;
				this.left = null;
				this.right = null;
				noKeys = 0;
				return preValue;
			}

			// check if the right is empty then copy the left if true
			if (right.isEmpty()) {
				this.key = left.key;
				this.value = left.value;
				this.noKeys = left.noKeys;
				this.right = left.right;
				this.left = left.left;
				return preValue;

			}

			// check if the left is empty then copy the right if true
			if (left.isEmpty()) {
				this.key = right.key;
				this.value = right.value;
				this.noKeys = right.noKeys;
				this.left = right.left;
				this.right = right.right;
				return preValue;
			}

			BSTDictionary<K, V> subtree = this.getLeftMost();
			this.key = subtree.key;
			this.value = subtree.value;
			right.remove(subtree.key);
			noKeys--;
			return preValue;
		}

		// comparing the keys to remove a left or right value
		if (comp < 0) {
			preValue = left.remove(key);
		} else if (comp > 0) {
			preValue = right.remove(key);
		}

		// increment the number of keys, otherwise, there's no need to decrement
		if (preValue != null) {
			noKeys--;
		}
		return preValue;
	}

	/**
	 * gets the left most subtree
	 * 
	 * @return the subtree
	 */
	private BSTDictionary<K, V> getLeftMost() {
		if (!left.isEmpty()) {
			return left.getLeftMost();
		}
		return this;
	}

	@Override
	public Iterator<K> keys() {
		return new BSTIterator<K>(this);
	}

	@Override
	public boolean isEmpty() {
		if (noKeys == 0) {
			return true;
		}
		return false;
	}

	@Override
	public int noKeys() {
		return noKeys;
	}

	@Override
	public Iterator<K> iterator() {
		return keys();
	}

}
