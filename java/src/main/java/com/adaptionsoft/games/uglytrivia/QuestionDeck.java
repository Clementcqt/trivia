package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayDeque;
import java.util.Deque;

public class QuestionDeck {
    private final Deque<String> questions = new ArrayDeque<>();

    public void addQuestion(String question) {
        questions.addLast(question);
    }

    public String drawQuestion() {
        return questions.isEmpty() ? "No more questions in this category!" : questions.pollFirst();
    }

    public boolean isEmpty() {
        return questions.isEmpty();
    }
}
