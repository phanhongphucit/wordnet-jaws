package wordnet;

import java.util.List;

import edu.smu.tspell.wordnet.NounSynset;
import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.SynsetType;
import edu.smu.tspell.wordnet.WordNetDatabase;

public class Demo {

	/**
	 * Main entry point. The command-line arguments are concatenated together
	 * (separated by spaces) and used as the word form to look up.
	 */
	public static void main(String[] args) {
		System.setProperty("wordnet.database.dir", "WordNet-3.0\\dict\\");
		String wordForm = "chair";
		// Get the synsets containing the wrod form
		WordNetDatabase database = WordNetDatabase.getFileInstance();
		Synset[] synsets = database.getSynsets(wordForm);
		// Display the word forms and definitions for synsets retrieved
		if (synsets.length > 0) {
			System.out.println("The following synsets contain '" + wordForm + "' or a possible base form "
					+ "of that text:");
			for (int i = 0; i < synsets.length; i++) {
				System.out.println("");
				String[] wordForms = synsets[i].getWordForms();
				for (int j = 0; j < wordForms.length; j++) {
					System.out.print((j > 0 ? ", " : "") + wordForms[j]);
				}
				System.out.println(": " + synsets[i].getDefinition());
			}
		} else {
			System.err.println("No synsets exist that contain " + "the word form '" + wordForm + "'");
		}
	}
	
	public static List<Synset> getSisterTerms(String word) {
		NounSynset nounSynset; 
		NounSynset[] hyponyms; 

		WordNetDatabase database = WordNetDatabase.getFileInstance(); 
		Synset[] synsets = database.getSynsets(word, SynsetType.NOUN); 
		for (int i = 0; i < synsets.length; i++) { 
		    nounSynset = (NounSynset)(synsets[i]); 
		    hyponyms = nounSynset.getHyponyms(); 
		    System.err.println(nounSynset.getWordForms()[0] + 
		            ": " + nounSynset.getDefinition() + ") has " + hyponyms.length + " hyponyms"); 
		}
		
		return null;
	}

}
