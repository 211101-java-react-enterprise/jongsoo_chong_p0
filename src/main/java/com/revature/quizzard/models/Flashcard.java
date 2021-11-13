package com.revature.quizzard.models;

public class Flashcard {

    private String id;
    private String questionText;
    private String answerText;
    private AppUser creator;

    public Flashcard(String questionText, String answerText) {
        this.questionText = questionText;
        this.answerText = answerText;
    }

    public Flashcard(String questionText, String answerText, AppUser creator) {
        this(questionText, answerText);
        this.creator = creator;
    }

    public Flashcard(String id, String questionText, String answerText, AppUser creator) {
        this(questionText, answerText, creator);
        this.id = id;
    }

    public Flashcard() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public AppUser getCreator() {
        return creator;
    }

    public void setCreator(AppUser creator) {
        this.creator = creator;
    }

}
