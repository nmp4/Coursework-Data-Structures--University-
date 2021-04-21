package F28DA_CW1;

import java.util.Arrays;
import java.util.Iterator;

public class HTWordsSet implements WordsSet{
	
	private HTWordsSet Hash; 
	private int size = 7;
	private String[] HashSet = new String[size];
	private int probe = 0;
	private float maxLoadFactor=0;
	
	
	/**
	 * First constructor that sets the maxLoadFactor to 0.5 and initialises the Hash
	 */
	public HTWordsSet() {
		
		maxLoadFactor = (float) 0.5;
		Hash = new HTWordsSet();
	}
	
	/**
	 * Second constructor which lets the maxLoadFactor be set at runtime
	 * @param maxLF
	 */
	public HTWordsSet(float maxLF) {
		
		maxLoadFactor = maxLF;
	}
	
	/**
	 * Helper function to double hash a hashkey
	 * @param k the key to be doublehashed
	 * @return the new doublehashed key
	 */
	private int doubleHash(int k) {
		
		return (k * 2^HashSet.length)+ 16537;
		
	}

	/**
	 * Adds a word the the set where it will throw an exception if the word already exists the set.
	 * If it doesnt exist the word will be inserted at the index created by calling giveHashCode with the word to be inserted divided by the
	 * HashSet length.
	 * It also has a check four double hashing afterwards.
	 *@param word
	 */
	public void insWord(String word) throws SpellCheckException{
		
		probe++;
		int key = giveHashCode(word);
		int i1 = key/HashSet.length;
		
		if (wordExists(word)) {
			
			throw new SpellCheckException("This word is already in the set");
			
		} 
		else if (HashSet[i1] != null) {
			
			HashSet[i1] = word;
		} 
		else {
			
			//HashSet[hashInt(index)]= word;
		}
		
		if(getWordsCount() > maxLoadFactor * HashSet.length) {
			
			String[] temp = HashSet;
			HashSet = new String[nextPrime(2*HashSet.length)];
			
			for(int i2 =0; i2<=temp.length; i2++) {
				
				if(temp[i2] != null) {
					
					insWord(temp[i2]);
				}
			}
		}
	}
	
	/**
	 * This method removes a word from the hash and throws an exception if the word doesn't exist in the process
	 *@param word the word to be removed from the hash
	 */
	public void rmWord(String word) throws SpellCheckException{
		
		probe++;
		int a = -1;
		
		if(wordExists(word)) {
			
			throw new SpellCheckException("word is already within the set");
		}
		
		for(int i = 0; i<HashSet.length; i++) {
			
			if(HashSet[i] == word) {
				
				a = i;
				HashSet[i] = null;
			}
			
			if(a > -1 && giveHashCode(HashSet[i]) == a) {
				
				HashSet[a] = HashSet[i];
				rmWord(HashSet[i]);
			}
		}
	}
	
	/**
	 * This method gets the nextPrime number after an input number
	 * @param num the number to find the prime number after
	 * @return the prime after the input num
	 */
	private static int nextPrime(int num) {
		
	      num++;
	      for (int i = 2; i < num; i++) {
	    	  
	         if(num%i == 0) {
	        	 
	            num++;
	            i=2;
	         } 
	         else {
	        	 
	            continue;
	         }
	      }
	      return num;
	 }
	
	/**
	 * This method checks if a word exists within the hash and returns either true if found or false if it wasnt.
	 *@param word
	 *@return 
	 */
	public boolean wordExists(String word) {
		
		probe++;
		
		int key = giveHashCode(word);
		int index = key%HashSet.length;
			
		while(true) {
			if(HashSet[index].equals(null)) {
				return false;
			}
			else if(HashSet[index].equals(word)) {
				return true;
			}
			else {
			index = doubleHash(index)%HashSet.length;
			}
		}

	}
	
	
	/**
	 * This method gets the amount of words inside of the hash 
	 * @return the amount of words in the hash.
	 */
	public int getWordsCount() {
		
		int count =0;
		
		for(int i =0; i<HashSet.length; i++) {
			
			if(HashSet[i] != null) {
				
				count++;
			}
		}
		return count;
	}
	
	/**
	 * This method returns the iterator that would go over the entire HHWordsSet by using Array streams
	 * @return the iterator that goes over a HHWordsSet
	 */
	public Iterator<String> getWordsIterator(){
		
		String[] SecondaryHashSet = new String[getWordsCount()];
		
		for(int i=0, j=0; i<HashSet.length; i++) {
			
			if(HashSet[i] != null) {
				
				SecondaryHashSet[j] = HashSet[i];
				j++;
				}
			}
		return (Iterator<String>) Arrays.stream(SecondaryHashSet).iterator();
		}

	
	/**
	 * This method creates a hashcode by multiplying a base value of 33 by the index in a for loop based on the string length.
	 * @param s the string to be put into a hashcode
	 * @return the hashcode of a string
	 */
	public int giveHashCode(String s) {
		
		int a = 33;
		int hashCode = 0;
		
		for(int i=0; i<s.length(); i++) {
			
			hashCode += (s.charAt(i) * a^i);
		}
		return hashCode;
	}
	
	/**
	 * @return the max load factor
	 */
	public float getMaxLoadFactor() {
		
		return maxLoadFactor;
		
	}
	
	/**
	 * @return the load factor by dividing the size of it by the actual HashSet.length
	 */
	public float getLoadFactor() {
		
		return getWordsCount() / HashSet.length;
		
	}
	
	/**
	 * @return get average probes by dividing probe counter by the hashset length
	 */
	public float getAverageProbes() {
		
		return probe/HashSet.length;
		
	}
	


}
