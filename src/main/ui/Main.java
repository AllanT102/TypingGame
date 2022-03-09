package ui;

import org.json.JSONArray;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {

        try {
            new LoadInScreen();
        } catch (Error e) {
            System.out.println("Unable to run application: file not found!");
        }
    }
}
