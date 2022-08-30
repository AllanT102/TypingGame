package model;

import ui.gamefunctionality.SentenceScraper;

import java.util.ArrayList;
import java.util.List;


// Represents the paragraph that the user will be typing and the paragraph that was typed by the user
public class Paragraph {

    private List<String> paragraphAsList;
    private ArrayList<Character> paragraphAsListWithChar;
    private String paragraphAsString;
    private String inputPara;
    private SentenceScraper scraper;

    // EFFECTS: constructs a paragraph as a list and as a string
    public Paragraph() {
        scraper = new SentenceScraper();
        paragraphAsList = new ArrayList<>();
        this.paragraphAsList = generateParagraph();
        this.paragraphAsString = convertParaToString(paragraphAsList);
    }

    public List<String> getParagraphAsList() {
        return paragraphAsList;
    }

    public String getParagraphAsString() {
        return paragraphAsString;
    }

    public String getInputPara() {
        return inputPara;
    }

    // EFFECTS: gets total amount of characters in paragraph
    public int getTotalChar() {
        return convertStringToListWithChar(paragraphAsString.trim()).size();
    }


    // MODIFIES: this
    // EFFECTS: sets inputPara to the given string, used for tests
    public void setInputPara(String ip) {
        this.inputPara = ip;
    }

    // MODIFIES: this
    // EFFECTS: sets paragraph to the given string, used for tests
    public void setParagraph(String s) {
        this.paragraphAsString = s;
    }


    // EFFECTS: generates and returns a list of words from wordBank, and list has to be of size 25,
    //          (24 by zero based indexing), there can be duplicate words
    public List<String> generateParagraph() {
        return scraper.convertToStringList(scraper.scrape());
    }

    // EFFECTS: returns and converts the paragraph that is a list of strings to one string connected by space
    public String convertParaToString(List<String> p) {
        String stringPara = "";
        for (String word : p) {
            stringPara = stringPara + word + " ";
        }
        return stringPara;
    }

    // EFFECTS: returns and converts the string to a list of individual characters
    public ArrayList<Character> convertStringToListWithChar(String p) {
        paragraphAsListWithChar = new ArrayList<>();
        for (char s : p.toCharArray()) {
            paragraphAsListWithChar.add(s);
        }
        return paragraphAsListWithChar;
    }

    // REQUIRES: orgPara must be the paragraph that is generated when instantiating p,
    //           and the inputPara must be the paragraph that the user types
    // EFFECTS: returns the number of characters typed correctly by comparing the user input to the actual paragraph
    public double getNumTypedCorrect(Paragraph p, String orgPara, String inputPara) {
        double typedCorrect = 0;
        for (int i = 0; i < p.getTotalChar(); i++) {
            if (convertStringToListWithChar(inputPara.trim()).size() - 1 < i) {
                return typedCorrect;
            } else if (convertStringToListWithChar(orgPara).get(i) == convertStringToListWithChar(inputPara).get(i)) {
                typedCorrect++;
            }
        }
        return typedCorrect;
    }
}


































































































































































