package com.example.assignment2;

import android.content.Context;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.assignment2.game.Difficulty;
import com.example.assignment2.game.Game;
import com.example.assignment2.game.GameBuilder;
import com.example.assignment2.game.Question;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.example.assignment2", appContext.getPackageName());
    }

    @Test
    public void createQuestionList() throws IOException {
        //Test building the question list from the given file.
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        GameBuilder gameBuilder = new GameBuilder(context);
        Game game = gameBuilder.newGame(Difficulty.MEDIUM);
        Question[] questions1 = game.getQuestions();

        GameBuilder gameBuilder2 = new GameBuilder(context);
        Game game2 = gameBuilder2.newGame(Difficulty.MEDIUM);
        Question[] questions2 = game2.getQuestions();

        Log.i("questions 1: ", Arrays.toString(questions1));
        Log.i("questions 2: ", Arrays.toString(questions2));

        assertNotEquals(questions1, questions2);

    }


}