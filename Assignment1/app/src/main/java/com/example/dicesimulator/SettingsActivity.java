package com.example.dicesimulator;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    public static final int SETTINGS_REQUEST = 1;
    private EditText sides;
    private EditText numDice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        sides = findViewById(R.id.sidesInput);
        numDice = findViewById(R.id.numDiceInput);
    }


    public void doneClicked(View view) {
        //Returns to main activity and passes the values the user input. Checks to make sure input is above 0 and not empty.
        if (!TextUtils.isEmpty(numDice.getText().toString()) && !TextUtils.isEmpty(sides.getText().toString())) {
            if (Integer.parseInt(numDice.getText().toString()) > 0 && Integer.parseInt(sides.getText().toString()) > 0) {
                System.out.println(numDice.getText().toString());
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("sides", Integer.parseInt(sides.getText().toString()));
                intent.putExtra("numDice", Integer.parseInt(numDice.getText().toString()));
                setResult(RESULT_OK, intent);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Please choose numbers above 0.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Please fill all text fields.", Toast.LENGTH_SHORT).show();
        }
    }
}
