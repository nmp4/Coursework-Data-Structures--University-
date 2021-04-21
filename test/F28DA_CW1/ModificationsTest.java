package F28DA_CW1;

import static org.junit.Assert.*;

import org.junit.Test;

public class ModificationsTest {

	@Test
    public void testSubstitution() {

        WordsSet dict = new LLWordsSet();
        
        try {
        	
            dict.insWord("cats");
            dict.insWord("like");
            dict.insWord("on");
            dict.insWord("of");
            dict.insWord("to");
            dict.insWord("play");
            
        } catch (SpellCheckException e) {
        	
            fail("Error with linked list implementation");
        }
        
        Spelling speller = new SpellingImpl();
        WordsSet sugg = speller.suggestions("wats", dict);
        assertTrue(sugg.wordExists("cats"));
    }

	@Test
	public void testInsertion() {
		
		 WordsSet dict = new LLWordsSet();
	        
	        try {
	        	
	            dict.insWord("cats");
	            dict.insWord("like");
	            dict.insWord("on");
	            dict.insWord("of");
	            dict.insWord("to");
	            dict.insWord("play");
	            
	        } catch (SpellCheckException e) {
	        	
	            fail("Error with linked list implementation");
	        }
	        
	        Spelling speller = new SpellingImpl();
	        WordsSet sugg = speller.suggestions("cas", dict);
	        assertTrue(sugg.wordExists("cats"));
	}

	// ...
	
	@Test
	public void testReversal() {
		
		 WordsSet dict = new LLWordsSet();
	        
	        try {
	        	
	            dict.insWord("cats");
	            dict.insWord("like");
	            dict.insWord("on");
	            dict.insWord("of");
	            dict.insWord("to");
	            dict.insWord("play");
	            
	        } catch (SpellCheckException e) {
	        	
	            fail("Error with linked list implementation");
	        }
	        
	        Spelling speller = new SpellingImpl();
	        WordsSet sugg = speller.suggestions("ctas", dict);
	        assertTrue(sugg.wordExists("cats"));
	}
}
