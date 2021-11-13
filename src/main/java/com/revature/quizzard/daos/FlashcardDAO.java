package com.revature.quizzard.daos;

import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.models.Flashcard;
import com.revature.quizzard.util.datasource.ConnectionFactory;
import com.revature.quizzard.util.collections.LinkedList;
import com.revature.quizzard.util.collections.List;

import java.sql.*;
import java.util.UUID;

public class FlashcardDAO implements CrudDAO<Flashcard> {

    public List<Flashcard> findCardsByCreatorId(String creatorId) {

        List<Flashcard> cards = new LinkedList<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * " +
                         "from flashcards f " +
                         "join app_users u " +
                         "on f.creator_id = u.user_id " +
                         "where u.user_id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, creatorId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Flashcard card = new Flashcard();
                AppUser cardCreator = new AppUser();
                card.setId(rs.getString("card_id"));
                card.setQuestionText(rs.getString("question_text"));
                card.setAnswerText(rs.getString("answer_text"));
                cardCreator.setId(rs.getString("user_id"));
                cardCreator.setFirstName(rs.getString("first_name"));
                cardCreator.setLastName(rs.getString("last_name"));
                cardCreator.setEmail(rs.getString("email"));
                cardCreator.setUsername(rs.getString("username"));
                card.setCreator(cardCreator);
                cards.add(card);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cards;

    }

    @Override
    public Flashcard save(Flashcard newCard) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            newCard.setId(UUID.randomUUID().toString());

            String sql = "insert into flashcards (card_id, question_text, answer_text, creator_id) values (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newCard.getId());
            pstmt.setString(2, newCard.getQuestionText());
            pstmt.setString(3, newCard.getAnswerText());
            pstmt.setString(4, newCard.getCreator().getId());

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted != 0) {
                return newCard;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public List<Flashcard> findAll() {

        List<Flashcard> cards = new LinkedList<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from flashcards f join app_users u on f.creator_id = u.id";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Flashcard card = new Flashcard();
                AppUser cardCreator = new AppUser();
                card.setId(rs.getString("card_id"));
                card.setQuestionText(rs.getString("question_text"));
                card.setAnswerText(rs.getString("answer_text"));
                cardCreator.setId(rs.getString("user_id"));
                cardCreator.setFirstName(rs.getString("first_name"));
                cardCreator.setLastName(rs.getString("last_name"));
                cardCreator.setEmail(rs.getString("email"));
                cardCreator.setUsername(rs.getString("username"));
                card.setCreator(cardCreator);
                cards.add(card);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cards;
    }

    @Override
    public Flashcard findById(String cardId) {
        return null;
    }

    @Override
    public boolean update(Flashcard updatedObj) {
        return false;
    }

    @Override
    public boolean removeById(String id) {
        return false;
    }

}
