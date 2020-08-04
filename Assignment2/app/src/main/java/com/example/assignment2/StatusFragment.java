package com.example.assignment2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.assignment2.game.Game;


/**
 * A simple {@link Fragment} subclass.
 */
public class StatusFragment extends Fragment {
    private TextView score, skips, question, lives;

    public StatusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_status, container, false);
        score = v.findViewById(R.id.score);
        skips = v.findViewById(R.id.skips);
        question = v.findViewById(R.id.question);
        lives = v.findViewById(R.id.lives);
        return v;
    }

    public void setText(Game game, boolean over) {
        score.setText(getString(R.string.showScore, String.valueOf(game.getScore())));
        skips.setText(getString(R.string.showSkips, String.valueOf(game.getSkips())));
        lives.setText(getString(R.string.showLives, String.valueOf(game.getLives())));
        if (!over) {
            question.setText(game.getQuestions()[game.getQuestionNumber() - 1].getQuestion());
        } else {
            question.setText(R.string.gameover);
        }
    }
}
