package F28DA_CW1;

import java.util.LinkedList;
import java.util.Iterator;

/**
 * @author Natasha Muthoni Pregetter
 * nmp4
 * H00326153
 *
 */
public class LLWordsSet implements WordsSet{

	private LinkedList<String> set;

	//Constructor for LLWordsSet, initialises the set inside
	public LLWordsSet() {

		set = new LinkedList<String>();

	}

	/**
	 * This method inserts a word into the LinkedList, but only if the word thats to be inserted isn't already in the list
	 * @param word the word to be inserted
	 */
	public void insWord(String word) {
		
		if (!wordExists(word)) {
			set.add(word);

		}
	}

	/**
	 * This method removes a given word from the linkedlist, but only if the word actually exists in the list.
	 * @param word is the word to be removed
	 * @throws SpellCheckException will be thrown if the list doesnt contain the word.
	 */
	public void rmWord(String word) throws SpellCheckException {
		if (!set.contains(word)) {
			throw new SpellCheckException("This set doesn't contain said word.");
		} else {
			set.remove(word);
		}
	}

	/**
	 * checks if a word exists inside of the list
	 * @param word is the word to be checked
	 * @return is the the boolean value of true if it exists and false if it doesn't.
	 */
	public boolean wordExists(String word) {
		if(set.indexOf(word) > -1) {
			
			return true;
			
		}
		return false;
	}

	/**
	 * This method gets and returns the amount of elements in the list
	 * @return the size of the list
	 */
	public int getWordsCount() {

		return set.size();
	}

	/**
	 * Gets the iterator for the LLWordsSet
	 * @return The iterator for the LLWordsSet
	 */
	public Iterator<String> getWordsIterator() {

		return set.iterator();
	}
	
	/**
	 * 
	 */
	/*public void print() {
		
		for(int i=0; i < set.size(); i++)
		   {
		      System.out.println("Element at index "+i+": " + set.get(i));
		    } 
	}*/

}
