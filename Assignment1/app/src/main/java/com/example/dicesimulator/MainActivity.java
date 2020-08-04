package com.example.dicesimulator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //App simulates dice rolls for user's choice of dice.
    //User chooses number of dice and how many sides on each dice.
    //Default dice set is 1d6
    private Dice dice = new Dice(1, 6);
    private TextView result;
    private TextView diceSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
        diceSet = findViewById(R.id.diceSet);
        if (savedInstanceState != null) {
            //Restores the current variables when app resets.
            dice = new Dice(savedInstanceState.getInt("numDice"), (savedInstanceState.getInt("sides")));
            result.setText(savedInstanceState.getCharSequence("result"));
            String set = String.valueOf(dice.getNumDice()) + 'd' + (dice.getSides());
            diceSet.setText(set);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putInt("numDice", dice.getNumDice());
        outState.putInt("sides", dice.getSides());
        outState.putCharSequence("result", result.getText());
    }

    public void Roll(View view) {
        //Simulates the dice roll and displays the result.
        int rollTotal = dice.Roll();
        result.setText(String.valueOf(rollTotal));
    }

    public void settingsClicked(View view) {
        //Opens settings activity.
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivityForResult(intent, SettingsActivity.SETTINGS_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SettingsActivity.SETTINGS_REQUEST) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    //Replaces the dice class based on the user input and changes the text fields.
                    dice = new Dice(data.getIntExtra("numDice", 1), data.getIntExtra("sides", 6));
                    String set = String.valueOf(dice.getNumDice()) + 'd' + (dice.getSides());
                    diceSet.setText(set);
                }
            }
        }
    }
}
