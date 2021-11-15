package com.revature.banking.services;

import com.revature.banking.daos.FlashcardDAO;
import com.revature.banking.exceptions.AuthorizationException;
import com.revature.banking.exceptions.InvalidRequestException;
import com.revature.banking.exceptions.ResourcePersistenceException;
import com.revature.banking.models.Flashcard;
import com.revature.banking.util.collections.List;

public class FlashcardService {

    private final FlashcardDAO cardDAO;
    private final UserService userService;

    public FlashcardService(FlashcardDAO cardDAO, UserService userService) {
        this.cardDAO = cardDAO;
        this.userService = userService;
    }

    public List<Flashcard> findAllCards() {
        return cardDAO.findAll();
    }

    public List<Flashcard> findMyCards() {

        if (!userService.isSessionActive()) {
            throw new AuthorizationException("No active user session to perform operation!");
        }

        return cardDAO.findCardsByCreatorId(userService.getSessionUser().getId());

    }

    public void createNewCard(Flashcard newCard) {

        if (!isCardValid(newCard)) {
            throw new InvalidRequestException("Invalid card information values provided!");
        }

        newCard.setCreator(userService.getSessionUser());
        Flashcard addedCard = cardDAO.save(newCard);

        if (addedCard == null) {
            throw new ResourcePersistenceException("The card could not be persisted to the datasource!");
        }

    }

    public boolean isCardValid(Flashcard card) {
        if (card == null) return false;
        if (card.getQuestionText() == null || card.getQuestionText().trim().equals("")) return false;
        return (card.getAnswerText() != null && !card.getAnswerText().trim().equals(""));
    }
}