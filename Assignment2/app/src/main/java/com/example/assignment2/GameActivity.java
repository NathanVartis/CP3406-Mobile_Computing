package com.example.assignment2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.assignment2.game.Difficulty;
import com.example.assignment2.game.Game;
import com.example.assignment2.game.GameBuilder;
import com.example.assignment2.sound.SoundPoolManager;

public class GameActivity extends AppCompatActivity implements SensorEventListener {

    private Game game;
    private Difficulty difficulty;
    private StatusFragment statusFragment = new StatusFragment();
    private GameFragment gameFragment = new GameFragment();
    private GameOverFragment gameOverFragment = new GameOverFragment();
    private FragmentManager fm;
    private FragmentTransaction ft;
    private boolean activityRecreated = false;
    private SensorManager sensorManager;
    private Sensor shake;
    private long lastShakeTime;
    private SoundPoolManager soundPoolManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        soundPoolManager = new SoundPoolManager(this);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        shake = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if (savedInstanceState != null) {
            //Restores the current variables when app resets.
            difficulty = (Difficulty) savedInstanceState.getSerializable("difficulty");
            game = savedInstanceState.getParcelable("game");
            activityRecreated = true;
        }

        //Creates the fragments. Status Fragment is always active. Game and Gameover fragment are created bases on the current game progress.
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.add(R.id.statusFrame, statusFragment, "statusFrag");
        if (activityRecreated) {
            if (game.isOver()) {
                ft.add(R.id.gameFrame, gameOverFragment, "gameOverFrag");
            } else {
                ft.add(R.id.gameFrame, gameFragment, "gameFrag");
            }
        } else {
            ft.add(R.id.gameFrame, gameFragment, "gameFrag");
        }
        ft.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Skips initialising the game if the activity has been recreated.
        if (!activityRecreated) {
            difficulty = (Difficulty) getIntent().getSerializableExtra("difficulty");
            GameBuilder gameBuilder = new GameBuilder(getApplicationContext());
            game = gameBuilder.newGame(difficulty);
        }
        setText(game);

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, shake, Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void setText(Game game) {
        //Updates the text on the screen.
        statusFragment.setText(game, game.isOver());
        if (!game.isOver()) {
            gameFragment.setText(game);
        }
    }


    public void answerClicked(View view) {
        //Checks if the answer is correct, updates score and moves to next question.
        int answer = gameFragment.answerClicked(view);
        game.answerChosen(answer, soundPoolManager);
        if (game.isOver()) {
            gameOver();
        } else {
            setText(game);
        }
    }

    private void gameOver() {
        //Replaces the game fragment with the gameover fragment and updates score database.
        ft = fm.beginTransaction();
        ft.replace(R.id.gameFrame, gameOverFragment, "gameOverFrag");
        ft.commit();
        statusFragment.setText(game, game.isOver());

        HighScoresDatabaseHelper helper = new HighScoresDatabaseHelper(this);
        try {
            helper.addScore(game.getScore(), difficulty);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void menuClicked(View view) {
        finish();
    }

    public void twitterClicked(View view) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("difficulty", difficulty);
        outState.putParcelable("game", game);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //Shake is ignored if less than 1 second has passed since the last one
        if (Math.sqrt(event.values[0] / 9.81 * event.values[0] / 9.81 + event.values[1] / 9.81 * event.values[1] / 9.81 + event.values[2] / 9.81 * event.values[2] / 9.81) > 1.2) {
            if (1000 + lastShakeTime > System.currentTimeMillis()) {
                return;
            }
            lastShakeTime = System.currentTimeMillis();
            if (!game.isOver()) {
                game.skipQuestion();
                setText(game);
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
