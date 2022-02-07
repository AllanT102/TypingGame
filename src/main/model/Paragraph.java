package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Paragraph {

    String[] allWords = {"hello", "cheese", "typing", "game", "final", "excellent", "missile", "configuration", "invaders",
            "tank", "tired", "bubble tea", "model", "summer", "winter", "spring", "autumn", "sushi", "tree",
            "Australia", "olympics", "Canada", "Space", "array", "computer", "science", "biology", "paragraph",
            "these", "are", "my", "words", "it", "doesn't", "make", "any", "sense", "but", "it's", "okay",
            "Vancouver"};

    List<String> wordBank = Arrays.asList(allWords);

    List<String> paragraphAsList;
    String paragraphAsString;

    // getters
    public String getParagraphAsString() {
        return paragraphAsString;
    }

    // Constructs a paragraph
    // EFFECTS: creates a paragraph as a list and as a string
    public Paragraph() {
        paragraphAsList = new ArrayList<>();
        this.paragraphAsList = generateParagraph();
        this.paragraphAsString = convertParaToString();
    }

    // EFFECTS: generates a list of words from wordBank, and list has to be of size 25, (24 by zero based indexing)
    public List<String> generateParagraph() {
        for (int i = 0; i < 25; i++) {
            paragraphAsList.add(wordBank.get(i));
        }
        return paragraphAsList;
    }

    // EFFECTS: converts the paragraph that is a list to a string
    public String convertParaToString() {
        String stringPara = "";
        for (String word : generateParagraph()) {
            stringPara = stringPara + " " + word;
        }
        return stringPara;
    }

}

