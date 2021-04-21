package F28DA_CW1;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import org.junit.Test;

public class HTableWordsTest {

	//changed to check that spellcheck fully works for LLWordsSet implementation
	//adds 1 elements and checks if the returned wordCount is 1
	@Test
	public void test1() {

			LLWordsSet h = new LLWordsSet();
			String word = "abc";
			
			h.insWord(word);
			
			assertTrue(h.getWordsCount() == 1);

	}

	//Add and check that a word exists
	@Test
	public void test2() {

			LLWordsSet h = new LLWordsSet();
			String word = "abc";
			
			h.insWord(word);
			
			Iterator<String> all = h.getWordsIterator();
			String first = all.next();
			
			assertTrue(first.equals(word) && !all.hasNext());

	}

	//Look for an element that doesn't exist
	@Test
	public void test3() {

		LLWordsSet h = new LLWordsSet();
		String word1 = "abc";
		String word2 = "xyz";

			h.insWord(word1);

		assertFalse(h.wordExists(word2));
	}

	//Attempts to delete an element that doesn't exist, which will throw an exception
	@Test
	public void test4() {

		LLWordsSet h = new LLWordsSet();
		String word1 = "abc";
		String word2 = "xyz";
		
			h.insWord(word1);

		try {
			
			h.rmWord(word2);
			fail();
			
		} 
		catch (SpellCheckException e) {
			
			assertTrue(true);
		}
	}

	//Checks that an element that is in the list is deleted
	@Test
	public void test5() {
		try {

			LLWordsSet h = new LLWordsSet();
			String word1 = "abc";
			String word2 = "xyz";
			
			h.insWord(word1);
			
			h.insWord(word2);
			
			h.rmWord(word1);
			
			assertTrue(h.getWordsCount() == 1);
			
			Iterator<String> all = h.getWordsIterator();
			String w = all.next();
			
			assertTrue(w.equals(word2) && !all.hasNext());
			
		} 
		catch (SpellCheckException e) {
			fail();
			
		} 
		catch (NoSuchElementException e) {
			
			fail();
		}
	}


	//Inserts 200 elements into the list and checks that the returned wordCount is 200
	@Test
	public void test6() {

		LLWordsSet h = new LLWordsSet();
		String word;
		
		for (int k = 0; k < 200; k++) {
			
			word = "w" + k;
				h.insWord(word);

		}
		
		assertEquals(h.getWordsCount(), 200);
	}

	//Checks that insertion and deletion of elements functions
	@Test
	public void test7() {
		try	{

			LLWordsSet h = new LLWordsSet();
			String word;
			
			for (int k = 0; k < 200; ++k) {
				
				word = "w" + k;
				h.insWord(word);
			}
			assertEquals(h.getWordsCount(), 200);
			
			for ( int k = 0; k < 200; ++k ){
				
				word = "w" + k;
				h.rmWord(word);
				
			}
			
			assertEquals(h.getWordsCount(), 0);
			
		} 
		catch (SpellCheckException e) {
			
			fail();
		}
	}

	//checks that an iterator is properly returned over the entire set.
	@Test
	public void test8() {

		LLWordsSet h = new LLWordsSet();
		LinkedList<String> l = new LinkedList<String>();
		String word;
		
		try {
			
			for (int k = 0; k < 100; k++) {
				
				word = "w" + k;
				h.insWord(word);
				l.add(word);
			}
			
			for (int k = 10; k < 30; k++) {
				
				word = "w" + k;
				h.rmWord(word);
				l.remove(word);
				
			}
			
		} 
		catch(SpellCheckException e) {
			
			fail();
		}
		
		Iterator<String> all = h.getWordsIterator();
		
		while (all.hasNext()) {
			
			assertTrue(l.remove(all.next()));
		}
		assertEquals(l.size(), 0);
	}
}
