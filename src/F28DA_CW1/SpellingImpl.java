package F28DA_CW1;

import java.util.Iterator;

public class SpellingImpl implements Spelling {
	
	private WordsSet sugg;

	/**
	 * This is the main method of this class that is the only one available outside of it. It calls reversal, omission, substitution, and insertion
	 * before printing all of them out using the helper print function. 
	 * @param word the misspelled word to be run through the class methods
	 * @param dict the dictionary that the word is being checked against
	 * @return the sugg WordsSet which holds all of the suggestions input into it by the other methods.
	 */
	public WordsSet suggestions(String word, WordsSet dict){
		sugg = new LLWordsSet();
		
		reversal(word, dict);
		omission(word,dict);
		substitution(word, dict);
		insertion(word, dict);
		
		print(sugg, word);
		return sugg;
		
		
	}
	
	
	/**
	 * This method omits every character in a word once to check if there are any extra characters in a word
	 * @param word is the misspelled word
	 * @param dict is the dictionary being checked against
	 */
	private void omission(String word, WordsSet dict)  {
		
		StringBuffer wordBuffer = new StringBuffer(word);
		
		for(int i = 0; i < word.length(); i++) {
			
			char deletedChar = wordBuffer.charAt(i);
			word = wordBuffer.deleteCharAt(i).toString();
			
			if(dict.wordExists(word)) {
				
				add(word);
			}
			
			else {
				
				word = wordBuffer.insert(i, deletedChar).toString();
			}
		}
		
		
	}

	/**
	 * This method substitutes every character in a word to check for a correct spelling. It uses the whole alphabet.
	 * @param word is the misspelled word
	 * @param dict is the dictionary being checked against
	 */
	private void substitution(String word, WordsSet dict) {
		char[] alphabet = new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 
				'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		
		for(int i = 0; i < word.length(); i++) {
			
			StringBuffer wordBuffer = new StringBuffer(word);
			
			for(int j = 0; j < alphabet.length; j++) {
				
				wordBuffer.setCharAt(i, alphabet[j]);
				
				if(dict.wordExists(wordBuffer.toString())) {
					
					add(wordBuffer.toString());
					
				}
			}
		}
		
	}
	
	/**
	 * This method inserts a character in between a words letters to see if a character is missing, it then compares it to the 
	 * dictionary provided to see if it can find the correct spelling of the word
	 * @param word the misspelled word to be inserted into
	 * @param dict the dictionary to be checked against
	 */
	private void insertion(String word, WordsSet dict){
		
		char[] alphabet = new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 
				'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		
		for(int i = 0; i < word.length(); i++) {
			
			for(int j = 0; j < alphabet.length; j++) {
				
				StringBuffer wordBuffer = new StringBuffer(word);
				
				wordBuffer.insert(i, alphabet[j]);
				if(dict.wordExists(wordBuffer.toString())) {
					
					add(wordBuffer.toString());
					
				}
			}
		}
		
	}
	
	/**
	 * This method reverses two characters within a word that are next to each other, it also makes sure it wont swap if it gets to the last
	 * letter in the word.
	 * @param word the word to be reversed
	 * @param dict the dictionary to compare the reversed words with
	 */
	private void reversal(String word, WordsSet dict) {
		
		for(int i = 0; i < word.length()-1; i++) {
			
			StringBuffer wordBuffer = new StringBuffer(word);
			
			char temp = wordBuffer.charAt(i);

			wordBuffer.setCharAt(i, wordBuffer.charAt(i+1));
			wordBuffer.setCharAt(i+1, temp);
			
			if(dict.wordExists(wordBuffer.toString())) {
				add(wordBuffer.toString());
				
			}
			
		}
		
	}
	
	/**
	 * helper function to remove the possibility of throwing a spell check exception directly from insword.
	 * @param word
	 */
	private void add(String word) {
		
		try{

			sugg.insWord(word);
		}
		catch(SpellCheckException e){
			
			e.printStackTrace();
		}
	}
	
	/**
	 * prints the misspelled word before the suggestion for the correct spelling
	 * Its does this by making sure the size of suggestions is larger than 0, which means there have
	 * been words inserted that match the correct words in the dictionary after running the above methods.
	 * @param correct the dictionary with the correct word
	 * @param word the misspelled word
	 */
	private void print(WordsSet correct, String word) {
		
        int size = sugg.getWordsCount();
        Iterator <String> i = sugg.getWordsIterator();

        if (size > 0) { 
        	
            System.out.print(word + " => " + i.next());
            
            while(i.hasNext()) {
            	
                System.out.print(", " + i.next());
            }
            System.out.println();
        }
    }
}
