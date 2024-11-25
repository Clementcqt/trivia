package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
	private static final int MAX_PLAYERS = 6;
	private static final int BOARD_SIZE = 12;
	private static final int WINNING_COINS = 6;

	private final Map<String, QuestionDeck> questionDecks = new HashMap<>();
	private final List<Player> players = new ArrayList<>();

	private int currentPlayerIndex = 0;
	private boolean isGettingOutOfPenaltyBox;

	public Game() {
		initializeQuestionDecks();
	}

	private void initializeQuestionDecks() {
		questionDecks.put("Pop", new QuestionDeck());
		questionDecks.put("Science", new QuestionDeck());
		questionDecks.put("Sports", new QuestionDeck());
		questionDecks.put("Rock", new QuestionDeck());

		for (int i = 0; i < 50; i++) {
			questionDecks.get("Pop").addQuestion("Pop Question " + i);
			questionDecks.get("Science").addQuestion("Science Question " + i);
			questionDecks.get("Sports").addQuestion("Sports Question " + i);
			questionDecks.get("Rock").addQuestion("Rock Question " + i);
		}
	}

	public boolean isPlayable() {
		return players.size() >= 2;
	}

	public boolean addPlayer(String playerName) {
		if (players.size() >= MAX_PLAYERS) {
			System.out.println("Cannot add more players.");
			return false;
		}
		players.add(new Player(playerName));
		System.out.println(playerName + " was added");
		System.out.println("They are player number " + players.size());
		return true;
	}

	public void roll(int roll) {
		Player currentPlayer = players.get(currentPlayerIndex);
		System.out.println(currentPlayer.getName() + " is the current player");
		System.out.println("They have rolled a " + roll);

		if (currentPlayer.isInPenaltyBox()) {
			handlePenaltyBoxRoll(roll, currentPlayer);
		} else {
			movePlayer(roll, currentPlayer);
			askQuestion();
		}
	}

	public boolean wasCorrectlyAnswered() {
		Player currentPlayer = players.get(currentPlayerIndex);

		if (currentPlayer.isInPenaltyBox() && !isGettingOutOfPenaltyBox) {
			nextPlayer();
			return true;
		}

		System.out.println("Answer was correct!!!!");
		currentPlayer.incrementPurse();
		System.out.println(currentPlayer.getName() + " now has " + currentPlayer.getPurse() + " Gold Coins.");

		boolean winner = !currentPlayer.hasWon(WINNING_COINS);
		nextPlayer();
		return winner;
	}

	public boolean wrongAnswer() {
		Player currentPlayer = players.get(currentPlayerIndex);
		System.out.println("Question was incorrectly answered");
		System.out.println(currentPlayer.getName() + " was sent to the penalty box");
		currentPlayer.setInPenaltyBox(true);

		nextPlayer();
		return true;
	}

	private void handlePenaltyBoxRoll(int roll, Player currentPlayer) {
		if (roll % 2 != 0) {
			isGettingOutOfPenaltyBox = true;
			System.out.println(currentPlayer.getName() + " is getting out of the penalty box");
			movePlayer(roll, currentPlayer);
			askQuestion();
		} else {
			isGettingOutOfPenaltyBox = false;
			System.out.println(currentPlayer.getName() + " is not getting out of the penalty box");
		}
	}

	private void movePlayer(int roll, Player player) {
		player.move(roll, BOARD_SIZE);
		System.out.println(player.getName() + "'s new location is " + player.getPlace());
		System.out.println("The category is " + currentCategory(player.getPlace()));
	}

	private void askQuestion() {
		String category = currentCategory(players.get(currentPlayerIndex).getPlace());
		System.out.println(questionDecks.get(category).drawQuestion());
	}

	private String currentCategory(int place) {
		if (place % 4 == 0) return "Pop";
		if (place % 4 == 1) return "Science";
		if (place % 4 == 2) return "Sports";
		return "Rock";
	}

	private void nextPlayer() {
		currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
	}
}
