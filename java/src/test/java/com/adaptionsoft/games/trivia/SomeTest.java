package com.adaptionsoft.games.trivia;


import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;



public class SomeTest {

    private String scriptGameWithSeed1 = "Chet was added\n" +
            "They are player number 1\n" +
            "Pat was added\n" +
            "They are player number 2\n" +
            "Sue was added\n" +
            "They are player number 3\n" +
            "Chet is the current player\n" +
            "They have rolled a 1\n" +
            "Chet's new location is 1\n" +
            "The category is Science\n" +
            "Science Question 0\n" +
            "Answer was corrent!!!!\n" +
            "Chet now has 1 Gold Coins.\n" +
            "Pat is the current player\n" +
            "They have rolled a 3\n" +
            "Pat's new location is 3\n" +
            "The category is Rock\n" +
            "Rock Question 0\n" +
            "Answer was corrent!!!!\n" +
            "Pat now has 1 Gold Coins.\n" +
            "Sue is the current player\n" +
            "They have rolled a 5\n" +
            "Sue's new location is 5\n" +
            "The category is Science\n" +
            "Science Question 1\n" +
            "Answer was corrent!!!!\n" +
            "Sue now has 1 Gold Coins.\n" +
            "Chet is the current player\n" +
            "They have rolled a 5\n" +
            "Chet's new location is 6\n" +
            "The category is Sports\n" +
            "Sports Question 0\n" +
            "Answer was corrent!!!!\n" +
            "Chet now has 2 Gold Coins.\n" +
            "Pat is the current player\n" +
            "They have rolled a 4\n" +
            "Pat's new location is 7\n" +
            "The category is Rock\n" +
            "Rock Question 1\n" +
            "Answer was corrent!!!!\n" +
            "Pat now has 2 Gold Coins.\n" +
            "Sue is the current player\n" +
            "They have rolled a 5\n" +
            "Sue's new location is 10\n" +
            "The category is Sports\n" +
            "Sports Question 1\n" +
            "Question was incorrectly answered\n" +
            "Sue was sent to the penalty box\n" +
            "Chet is the current player\n" +
            "They have rolled a 3\n" +
            "Chet's new location is 9\n" +
            "The category is Science\n" +
            "Science Question 2\n" +
            "Answer was corrent!!!!\n" +
            "Chet now has 3 Gold Coins.\n" +
            "Pat is the current player\n" +
            "They have rolled a 3\n" +
            "Pat's new location is 10\n" +
            "The category is Sports\n" +
            "Sports Question 2\n" +
            "Question was incorrectly answered\n" +
            "Pat was sent to the penalty box\n" +
            "Sue is the current player\n" +
            "They have rolled a 3\n" +
            "Sue is getting out of the penalty box\n" +
            "Sue's new location is 1\n" +
            "The category is Science\n" +
            "Science Question 3\n" +
            "Answer was correct!!!!\n" +
            "Sue now has 2 Gold Coins.\n" +
            "Chet is the current player\n" +
            "They have rolled a 2\n" +
            "Chet's new location is 11\n" +
            "The category is Rock\n" +
            "Rock Question 2\n" +
            "Answer was corrent!!!!\n" +
            "Chet now has 4 Gold Coins.\n" +
            "Pat is the current player\n" +
            "They have rolled a 2\n" +
            "Pat is not getting out of the penalty box\n" +
            "Sue is the current player\n" +
            "They have rolled a 1\n" +
            "Sue is getting out of the penalty box\n" +
            "Sue's new location is 2\n" +
            "The category is Sports\n" +
            "Sports Question 3\n" +
            "Answer was correct!!!!\n" +
            "Sue now has 3 Gold Coins.\n" +
            "Chet is the current player\n" +
            "They have rolled a 5\n" +
            "Chet's new location is 4\n" +
            "The category is Pop\n" +
            "Pop Question 0\n" +
            "Answer was corrent!!!!\n" +
            "Chet now has 5 Gold Coins.\n" +
            "Pat is the current player\n" +
            "They have rolled a 4\n" +
            "Pat is not getting out of the penalty box\n" +
            "Sue is the current player\n" +
            "They have rolled a 3\n" +
            "Sue is getting out of the penalty box\n" +
            "Sue's new location is 5\n" +
            "The category is Science\n" +
            "Science Question 4\n" +
            "Answer was correct!!!!\n" +
            "Sue now has 4 Gold Coins.\n" +
            "Chet is the current player\n" +
            "They have rolled a 1\n" +
            "Chet's new location is 5\n" +
            "The category is Science\n" +
            "Science Question 5\n" +
            "Answer was corrent!!!!\n" +
            "Chet now has 6 Gold Coins.\n";

    private static boolean notAWinner;
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;
    private static final PrintStream originalErr = System.err;

    @BeforeAll
    public static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterAll
    public static void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void add_two_players(){
        Game game = new Game();

        game.add("Chet");
        game.add("Pat");
        game.add("Sue");
        long seed = 1;
        Random rand = new Random(seed);
        play_game(game, rand);
        assertEquals(
                scriptGameWithSeed1.replace("\r\n", "\n").replace("\r", "\n"),
                outContent.toString().replace("\r\n", "\n").replace("\r", "\n")
        );
    }

    public void play_game(Game game, Random rand){
        do {
            game.roll(rand.nextInt(5) + 1);
            if (rand.nextInt(9) == 7) {
                notAWinner = game.wrongAnswer();
            } else {
                notAWinner = game.wasCorrectlyAnswered();
            }
        } while (notAWinner);
    }
}
