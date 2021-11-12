package com.revature.bankjsc.screens;

import com.revature.bankjsc.exceptions.InvalidRequestException;
import com.revature.bankjsc.exceptions.ResourcePersistenceException;
import com.revature.bankjsc.models.Flashcard;
import com.revature.bankjsc.services.FlashcardService;
import com.revature.bankjsc.util.ScreenRouter;

import java.io.BufferedReader;

public class FlashcardCreatorScreen extends Screen {

    private final FlashcardService cardService;

    public FlashcardCreatorScreen(BufferedReader consoleReader, ScreenRouter router, FlashcardService cardService) {
        super("FlashcardCreatorScreen", "/create-flashcards", consoleReader, router);
        this.cardService = cardService;
    }

    @Override
    public void render() throws Exception {

        System.out.print("Flashcard Creator\n" +
                         "Question text > ");

        String questionText = consoleReader.readLine();

        System.out.print("Answer text > ");
        String answerText = consoleReader.readLine();

        Flashcard newCard = new Flashcard(questionText, answerText);

        try {
            cardService.createNewCard(newCard);
            System.out.printf("Card successfully created with id: %s!\n", newCard.getId());
        } catch (InvalidRequestException | ResourcePersistenceException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Navigating back to Flashcard menu...");

    }

}
