package com.example.assignment2.game;

import java.util.Arrays;

public class Question {
    private String question, correctAnswer;
    private String[] answers;

    public Question(String question, String correctAnswer, String[] answers) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.answers = answers;
    }

    public boolean checkAnswer(String answer){
        return answer.equals(correctAnswer);
    }

    @Override
    public String toString(){
        return question+", "+correctAnswer+", "+ Arrays.toString(answers);
    }


    public String[] getAnswers() {
        return answers;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

}
