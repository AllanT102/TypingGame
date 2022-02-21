package ui;

import org.json.JSONArray;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        JSONArray jsonArray;

        jsonArray = new JSONArray();

        try {
            new TypingGame(jsonArray);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found!");
        }
    }
}
