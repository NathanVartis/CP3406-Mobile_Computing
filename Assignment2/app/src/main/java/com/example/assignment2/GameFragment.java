package com.example.assignment2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.assignment2.game.Game;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {
    private Button answer1, answer2, answer3;
    public GameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_game, container, false);

        answer1 = v.findViewById(R.id.answer1);
        answer2 = v.findViewById(R.id.answer2);
        answer3 = v.findViewById(R.id.answer3);
        return v;
    }


    public void setText(Game game) {
        answer1.setText(game.getQuestions()[game.getQuestionNumber() - 1].getAnswers()[0]);
        answer2.setText(game.getQuestions()[game.getQuestionNumber() - 1].getAnswers()[1]);
        answer3.setText(game.getQuestions()[game.getQuestionNumber() - 1].getAnswers()[2]);
    }


    public int answerClicked(View view) {
        switch (view.getId()) {
            case R.id.answer1:
                return 1;
            case R.id.answer2:
                return 2;
            case R.id.answer3:
                return 3;
        }
        return 0;
    }
}
