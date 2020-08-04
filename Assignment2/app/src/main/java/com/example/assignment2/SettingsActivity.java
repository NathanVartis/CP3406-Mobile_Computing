package com.example.assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    public static final int REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void doneClicked(View view) {
        //Returns to main menu and saves the selected difficulty
        Intent intent = new Intent(this, MainActivity.class);
        Spinner spinner = findViewById(R.id.difficulty);
        intent.putExtra("difficulty", spinner.getSelectedItem().toString());
        setResult(RESULT_OK, intent);
        finish();
    }

    public void clearScores(View view) {
        //Clears the database of all entries.
        HighScoresDatabaseHelper db = new HighScoresDatabaseHelper(this);
        db.clearDatabase();
        Toast.makeText(this, "High scores cleared." , Toast.LENGTH_SHORT).show();

    }


}
