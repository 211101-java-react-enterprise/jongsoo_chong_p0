package com.revature.quizzard.screens;

import com.revature.quizzard.util.ScreenRouter;

import java.io.BufferedReader;

public class FlashcardMenuScreen extends Screen {

    public FlashcardMenuScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("FlashcardMenuScreen", "/flashcards", consoleReader, router);
    }

    @Override
    public void render() throws Exception {

        System.out.print("Flashcard Menu\n" +
                "1) View all flashcards\n" +
                "2) View my flashcards\n" +
                "3) Create new flashcard\n" +
                "4) Go back\n" +
                "> ");

        String userSelection = consoleReader.readLine();

        switch (userSelection) {
            case "1":
                // TODO: implement view all flashcards screen
                router.navigate("/all-flashcards");
                break;
            case "2":
                router.navigate("/my-flashcards");
                break;
            case "3":
                router.navigate("/create-flashcards");
                break;
            case "4":
                break;
            default:
                System.out.println("You have made an invalid selection");
        }

        System.out.println("Navigating back to your dashboard...");

    }

}
