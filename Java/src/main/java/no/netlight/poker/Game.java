package no.netlight.poker;

import java.util.ArrayList;
import java.util.List;

import static no.netlight.poker.util.GameUtil.*;

public class Game {
    private ArrayList<Player> players;
    private ArrayList<Card> cards;

    public Game(String[] players, int[] cards) throws Exception {
        this.players = new ArrayList<>();
        this.cards = new ArrayList<>();
        checkGameSettings(players, cards);
        initializeGame(players, cards);
    }

    /**
     * Instantiates the game and gets the players ready for poker.
     * @param players - the list of player of the given game.
     * @param cards - the list of valid cards for the game.
     */
    private void initializeGame(String[] players, int[] cards) throws Exception {
        for (int id : cards)
            this.cards.add(new Card(id));

        int cardsToDeal = 0;
        for (String player : players) {
            this.players.add(new Player(player, this.cards.subList(cardsToDeal, cardsToDeal + HAND_SIZE)));
            cardsToDeal += HAND_SIZE;
        }
    }

    /**
     * Get the Player of the game based on id within the correct boundaries.
     * @param int id, a unique value of a card.
     * @return Player, the given player with that unique id.
     */
    public Player getPlayer(int id) throws Exception {
        if (id <= 0 || id > players.size()) throw new Exception("Not a valid player");
        return players.get(id-1);
    }

    /**
     * Gets the winner of the poker game.
     * <p>
     *     Creates an empty list to add winners of the game.
     *     Finds the highestScore player. If it is a draw, add both and split the sum.
     * </p>
     * @return List<Player> winners, a list of winners of the poker game.
     */
    public List<Player> getWinners() {
        List<Player> winners = new ArrayList<>();
        Player currentBestPlayer = players.get(0);
        int highestScore = currentBestPlayer.getScore();

        for (Player player : players)
            if (highestScore < player.getScore())
                highestScore = player.getScore();

        for (Player player : players)
            if (highestScore == player.getScore())
                winners.add(player);

        return winners;
    }
}
