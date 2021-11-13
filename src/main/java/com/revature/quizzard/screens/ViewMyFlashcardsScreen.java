package com.revature.quizzard.screens;

import com.revature.quizzard.models.Flashcard;
import com.revature.quizzard.services.FlashcardService;
import com.revature.quizzard.util.collections.List;
import com.revature.quizzard.util.ScreenRouter;

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
