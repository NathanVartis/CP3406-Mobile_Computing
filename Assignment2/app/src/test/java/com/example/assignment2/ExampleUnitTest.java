package com.example.assignment2;

import com.example.assignment2.game.Game;
import com.example.assignment2.game.Question;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testQuestions() {
        //Tests question checking
        Question question = new Question("test question", "answer", new String[]{"answer", "wrong1", "wrong2"});
        assertTrue(question.checkAnswer("answer"));
        assertFalse(question.checkAnswer("wrong1"));
    }

    @Test
    public void testGameScore() {
        //Test the game to see if the question methods work and the score is correctly tracked.
        Question[] questions = new Question[10];
        int skips = 3;
        String[] q = new String[]{"Q1", "Q2", "Q3", "Q4", "Q5", "Q6", "Q7", "Q8", "Q9", "Q10"};
        String[] c = new String[]{"A1", "B2", "C3", "A4", "B5", "C6", "A7", "B8", "C9", "A10"};
        String[][] a = new String[][]{{"A1", "B1", "C1"}, {"A2", "B2", "C2"}, {"A3", "B3", "C3"}, {"A4", "B4", "C4"},
                {"A5", "B5", "C5"}, {"A6", "B6", "C6"}, {"A7", "B7", "C7"}, {"A8", "B8", "C8"}, {"A9", "B9", "C9"}, {"A10", "B10", "C10"}};

        for (int i = 0; i < 10; i++) {
            questions[i] = new Question(q[i], c[i], a[i]);
        }

        Game game = new Game(skips, questions);
        int test = 0;
        while (!game.isOver()) {
            if (test < 5) {
                game.updateScore(questions[test].checkAnswer(questions[test].getCorrectAnswer()));
            } else {
                game.updateScore(questions[test].checkAnswer("wrong"));
            }
            game.nextQuestion();
            test++;
        }
        assertEquals(5, game.getScore());

    }

}
