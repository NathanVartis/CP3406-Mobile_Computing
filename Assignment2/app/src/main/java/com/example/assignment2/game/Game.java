package com.example.assignment2.game;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.assignment2.sound.SoundPoolManager;
import com.example.assignment2.sound.Sound;

public class Game implements Parcelable {
    private int skips, questionNumber, score, lives;
    private Question[] questions;
    private int mData;

    public Game(int skips, Question[] questions) {
        this.questions = questions;
        this.skips = skips;
        score = 0;
        questionNumber = 1;
        lives = 5;
    }

    private Game(Parcel in) {
        mData = in.readInt();
    }

    public Question[] getQuestions() {
        return questions;
    }

    public int getScore() {
        return score;
    }

    public int getSkips() {
        return skips;
    }

    public int getLives() {
        return lives;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void updateScore(boolean result, SoundPoolManager soundPoolManager) {
        //updates score/lives depending on whether answer was correct.
        if (result) {
            score += 1;
            if(soundPoolManager.isReady()) {
                soundPoolManager.playEffect(Sound.RIGHT);
            }
        }
        else {
            lives -= 1;
            if(soundPoolManager.isReady()) {
                soundPoolManager.playEffect(Sound.WRONG);
            }
        }
    }

    public boolean isOver() {
        return questions.length < questionNumber || lives == 0;
    }

    public void nextQuestion() {
        questionNumber += 1;
    }

    public void skipQuestion() {
        //skips to the next question if skips are available.
        if (skips > 0) {
            skips -= 1;
            nextQuestion();
        }
    }

    public void answerChosen(int answer, SoundPoolManager soundPoolManager) {
        //checks the answer chosen, updates the score and moves to the next question.
        updateScore(questions[questionNumber - 1].checkAnswer(questions[questionNumber - 1].getAnswers()[answer - 1]), soundPoolManager);
        if (!isOver()) {
            nextQuestion();
        }
    }


    //Saves the Game object for restoring when GameActivity is destroyed/restored.
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(mData);
    }

    public static final Parcelable.Creator<Game> CREATOR
            = new Parcelable.Creator<Game>() {
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

}
