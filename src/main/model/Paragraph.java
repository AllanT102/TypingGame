package model;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Paragraph {

    private String[] allWords = {"hello", "cheese", "typing", "game", "final", "excellent", "missile", "configuration",
            "invaders", "tank", "tired", "bubble tea", "model", "summer", "winter", "spring", "autumn", "sushi", "tree",
            "Australia", "olympics", "Canada", "Space", "array", "computer", "science", "biology", "paragraph",
            "these", "are", "my", "words", "it", "doesn't", "make", "any", "sense", "but", "it's", "okay",
            "Vancouver"};

    private List<String> wordBank = Arrays.asList(allWords);

    private List<String> paragraphAsList;
    private ArrayList<Character> paragraphAsListWithChar;
    private String paragraphAsString;
    private String inputPara;
    private int typedCorrect;

    // getters
    public String getParagraphAsString() {
        return paragraphAsString;
    }

    public int getNumWords() {
        return paragraphAsList.size();
    }

    public List<String> getParaAsList() {
        return this.paragraphAsList;
    }

    // Constructs a paragraph
    // EFFECTS: creates a paragraph as a list and as a string
    public Paragraph() {
        paragraphAsList = new ArrayList<>();
        this.paragraphAsList = generateParagraph();
        this.paragraphAsString = convertParaToString();
    }

    // EFFECTS: gets total amount of characters in paragraph
    public int getTotalChar() {
        return paragraphAsListWithChar.size();
    }

    // EFFECTS: gets number of characters that were typed correctly
    public int getTypedCorrect() {
        return typedCorrect;
    }

    // MODIFIES: this
    // EFFECTS: sets inputPara to the input paragraph
    public void setInputPara(String ip) {
        this.inputPara = ip;
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


    // EFFECTS: creates list of characters within the paragraph
    public ArrayList<Character> convertStringToListWithChar(String p) {
        paragraphAsListWithChar = new ArrayList<>();
        for (char s : p.toCharArray()) {
            paragraphAsListWithChar.add(s);
        }
        return paragraphAsListWithChar;
    }

    // EFFECTS: returns the number of characters typed correctly
    public void getNumTypedCorrect(Paragraph p, String orgPara, String inputPara) {

        for (int i = 0; i < p.getNumWords(); i++) {
            if (convertStringToListWithChar(inputPara).size() - 1 < i) {
                break;
            }
            if (convertStringToListWithChar(orgPara).get(i) == convertStringToListWithChar(inputPara).get(i)) {
                this.typedCorrect++;
            }
        }
    }

}


































































































































































