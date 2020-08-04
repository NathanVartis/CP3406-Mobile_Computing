package com.example.assignment2;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class ScoresActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        ListView easyList = findViewById(R.id.easyScore);
        ListView mediumList = findViewById(R.id.mediumScore);
        ListView hardList = findViewById(R.id.hardScore);

        populateList(easyList, "Easy");
        populateList(mediumList, "Medium");
        populateList(hardList, "Hard");

    }

    private void populateList(ListView list, String diff) {
        try {
            HighScoresDatabaseHelper db = new HighScoresDatabaseHelper(this);
            Cursor cursor = db.getScores(diff);
            SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"SCORE"},
                    new int[]{android.R.id.text1},
                    0);
            list.setAdapter(listAdapter);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}