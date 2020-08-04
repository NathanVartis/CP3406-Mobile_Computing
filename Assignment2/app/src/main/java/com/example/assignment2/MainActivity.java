package com.example.assignment2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment2.game.Difficulty;
import com.example.assignment2.sound.SoundPoolManager;

public class MainActivity extends AppCompatActivity {

    //App is a physics quiz with multiple choice questions. Players have 5 lives to get through the questions with question skips
    //available depending on the difficulty.High scores are recorded and split according to difficulty.

    private Difficulty difficulty = Difficulty.Medium;
    private static MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SoundPoolManager soundPoolManager = new SoundPoolManager(this);

        if (savedInstanceState != null) {
            //Restores the current variables when app resets.
            difficulty = (Difficulty) savedInstanceState.getSerializable("difficulty");
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        //Starts music
        if(!mediaPlayer.isPlaying()) {
            mediaPlayer = MediaPlayer.create(this, R.raw.music);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }
    }


    public void buttonClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case (R.id.playButton):
                //Starts new game
                intent = new Intent(this, GameActivity.class);
                intent.putExtra("difficulty", difficulty);
                startActivity(intent);
                break;
            case (R.id.settingsButton):
                //Opens settings activity
                intent = new Intent(this, SettingsActivity.class);
                intent.putExtra("difficulty", difficulty);
                startActivityForResult(intent, SettingsActivity.REQUEST);
                break;
            case (R.id.scoreButton):
                //Opens high score activity
                intent = new Intent(this, ScoresActivity.class);
                startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SettingsActivity.REQUEST && resultCode == RESULT_OK && data != null) {
            difficulty = Difficulty.valueOf(data.getStringExtra("difficulty"));
            Log.i("Diff: ", difficulty.toString());
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putSerializable("difficulty", difficulty);
    }


}
