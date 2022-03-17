package ui;

import ui.gameFunctionality.SetUp;

public class Main {

    public static void main(String[] args) {

        try {
            new SetUp();
        } catch (Error e) {
            System.out.println("Unable to run application: file not found!");
        }
    }
}
