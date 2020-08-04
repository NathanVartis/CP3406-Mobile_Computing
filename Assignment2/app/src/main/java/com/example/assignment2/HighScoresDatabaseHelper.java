package com.example.assignment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.assignment2.game.Difficulty;

public class HighScoresDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "highScores";
    private static final int DB_VERSION = 1;
    private Context context;
    private static SQLiteDatabase db;
    private static final String score = "SCORE";
    private static final String diff = "DIFFICULTY";
    private static final String table = "HIGHSCORES";

    HighScoresDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    public void addScore(int score, Difficulty diff) {
        //adds the new score into the database
        ContentValues values = new ContentValues();
        values.put(this.score, score);
        values.put(this.diff, String.valueOf(diff));

        db = getWritableDatabase();

        db.insert(table, null, values);
        db.close();
    }

    public Cursor getScores(String diff) {
        //retrieves all the scores for the given difficulty and sorts them
        db = getReadableDatabase();
        Cursor cursor = db.query(table,
                new String[]{"_id", score}, "DIFFICULTY = ?", new String[]{diff}, null, null, "SCORE DESC");
        return cursor;
    }

    public void clearDatabase() {
        db = getWritableDatabase();
        db.delete(table, null, null);
        db.close();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        updateDatabase(db, 0, DB_VERSION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateDatabase(db, oldVersion, newVersion);
    }


    private void updateDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE HIGHSCORES (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "SCORE INTEGER, "
                    + "DIFFICULTY TEXT);");
        }
    }

}
