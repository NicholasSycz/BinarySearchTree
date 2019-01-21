package edu.iup.cosc310.wordcount;

import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.Iterator;
import java.util.Scanner;

import edu.iup.cosc310.util.BSTDictionary;
import edu.iup.cosc310.util.Dictionary;

/**
 * Word counting program for the BSTDictionary. It prints out a list of all the
 * found words in sorted order, and the number of occurrences per word.
 * 
 * @author Nicholas Sycz
 */
public class WordCount {

	/**
	 * main method of the program
	 * 
	 * @param args
	 *            string arguments
	 * @throws FileNotFoundException
	 *             exception to throw if there is no file
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Dictionary<String, Integer> dictionary = new BSTDictionary<String, Integer>();

		FileReader file = new FileReader(args[0]);
		Scanner sc = new Scanner(file);
		sc.useDelimiter("\\W+");

		while (sc.hasNext()) {
			String word = sc.next().trim();
			Integer count = dictionary.get(word);

			if (count == null) {
				dictionary.put(word, 1);
			} else {
				dictionary.put(word, count + 1);
			}
		}

		System.out.println("| Binary Search Tree Dictionary |");
		System.out.println();
		for (Iterator<String> iter = dictionary.keys(); iter.hasNext();) {
			String word = iter.next();
			int count = dictionary.get(word);
			System.out.printf("Word: %-15s      Occurences: %d", word, count);
			System.out.println();
		}
		sc.close();
	}

}
