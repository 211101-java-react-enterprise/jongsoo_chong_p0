package com.revature.bankjsc.screens;

import com.revature.bankjsc.models.Flashcard;
import com.revature.bankjsc.services.FlashcardService;
import com.revature.bankjsc.util.List;
import com.revature.bankjsc.util.ScreenRouter;

import java.io.BufferedReader;

public class ViewMyFlashcardsScreen extends Screen {

    private final FlashcardService cardService;

    public ViewMyFlashcardsScreen(BufferedReader consoleReader, ScreenRouter router, FlashcardService cardService) {
        super("FlashcardViewerScreen", "/my-flashcards", consoleReader, router);
        this.cardService = cardService;
    }

    @Override
    public void render() throws Exception {
        List<Flashcard> myCards = cardService.findMyCards();

        if (myCards.isEmpty()) {
            System.out.println("You have not made any cards!");
            System.out.println("Navigating back to Flashcard menu...");
            router.navigate("/flashcards");
            return;
        }

        System.out.println("Your cards: \n");
        for (int i = 0; i < myCards.size(); i++) {
            Flashcard card = myCards.get(i);
            System.out.println("Card Id: " + card.getId());
            System.out.println("Question: " + card.getQuestionText());
            System.out.println("Answer: " + card.getAnswerText() + "\n");
        }

        System.out.println("Navigating back to Flashcard menu...");
        router.navigate("/flashcards");

    }

}
