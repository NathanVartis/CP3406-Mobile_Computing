package com.example.assignment2.game;

import android.content.Context;
import android.util.Log;

import com.example.assignment2.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameBuilder {
    private Context context;
    private Game game;

    public GameBuilder(Context context) {
        this.context = context;
    }

    public Game newGame(Difficulty difficulty) {

        int n = 0;
        //Difficulty determines number of question skips available.
        switch (difficulty) {
            case Easy:
                n = 5;
                break;
            case Medium:
                n = 3;
                break;
            case Hard:
                n = 0;
                break;
        }

        ArrayList<ArrayList<String>> questionList = new ArrayList<>();
        questionList = this.readFile(questionList);

        Question[] questions = this.shuffleQuestions(questionList);
        game = new Game(n, questions);

        return game;
    }

    private Question[] shuffleQuestions(ArrayList<ArrayList<String>> questionList){
        //converts the arraylist into an an array of Question with the answers shuffled.

        Question[] questions = new Question[questionList.size()];
        String[] answers;
        List<String> answersList;

        for (int i = 0; i < questionList.size(); i++) {
            answers = new String[questionList.get(0).size() - 1];
            for (int j = 0; j < questionList.get(0).size() - 1; j++) {
                answers[j] = questionList.get(i).get(j + 1);
            }
            answersList = Arrays.asList(answers);
            Collections.shuffle(answersList);
            answersList.toArray(answers);
            questions[i] = new Question(questionList.get(i).get(0), questionList.get(i).get(1), answers);
        }
        return questions;
    }

    public ArrayList<ArrayList<String>> readFile(ArrayList<ArrayList<String>> list){
        InputStream inputStream = context.getResources().openRawResource(R.raw.questions);

        //Stores the questions file in a 2D arraylist and shuffles the order.
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(new ArrayList<String>());
                line = line.trim();
                list.get(list.size() - 1).addAll(Arrays.asList(line.split("\\s*,\\s*")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Collections.shuffle(list);
        return list;
    }
}
