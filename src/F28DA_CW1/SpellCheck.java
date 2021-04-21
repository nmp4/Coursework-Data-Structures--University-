package F28DA_CW1;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * Main class for the program
 */
public class SpellCheck {

	/**
	 * Main method for the program. The program takes two input filenames in the
	 * command line: the word dictionary file and the file containing the words to
	 * spell-check.
	 */
	public static void main(String[] args) {
		
		long discovery = System.currentTimeMillis(); //check current time at the beginning of the program
		
		if (args.length != 2) {
			System.err.println("Usage: SpellCheck dictionaryFile.txt inputFile.txt ");
			System.exit(1);
		}

		try {
			
			BufferedInputStream dict, file;
			SpellingImpl speller;
			
			
			dict = new BufferedInputStream(new FileInputStream(args[0]));
			
			FileWordRead reader = new FileWordRead(dict);
			
			WordsSet readDict = new LLWordsSet();

			//Loop that reads in every word inside of a file into the created WordsSet readDict which is the dictionary
			while(reader.hasNextWord()) {
								
				readDict.insWord(reader.nextWord());
				
			}
			

			dict.close();

			speller = new SpellingImpl();

			file = new BufferedInputStream(new FileInputStream(args[1]));

			WordsSet suggestions = new LLWordsSet(); //the set that will be filled up with all of the suggested spelling for words
			FileWordRead reader2 = new FileWordRead(file); //second reader
			
			
			while(reader2.hasNextWord()) {
				
				String word = reader2.nextWord();
				suggestions = speller.suggestions(word, readDict); //this auto fills up suggestions if there are any matching words found
				//after all of the different methods are run in suggestions.
				
				if(suggestions != null) {//if the set isn't empty
					
					Iterator<String> i = suggestions.getWordsIterator(); //the iterator 
					
					while(i.hasNext()) {

						i.next();//gets the next value
						System.out.println(" ");

					}

					//System.out.println("");
				}
			
			}
		

			file.close();

		} catch (IOException e) { // catch exceptions caused by file input/output errors
			
			System.err.println("Missing input file, check your filenames");
			System.exit(1);
			
		}catch(SpellCheckException e ) {
			
			e.printStackTrace();
		}
		long daftPunk = System.currentTimeMillis(); // time at the end of the program
		
		long total = daftPunk - discovery; //actual runtime value
		System.out.println(total); //prints out runtime
	}
}