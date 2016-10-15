package no.netlight.poker.util;

import no.netlight.poker.Card;
import no.netlight.poker.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by chrisat on 14.10.16.
 */
public class GameUtil {
    public static final int HAND_SIZE = 5;
    public static final int SUIT_SIZE = 13; // This could have been put in the enum-class SUIT.

    /**
     * Checks if there are players, right amount of cards and that the deck of cards has correct rank and suit.
     * @return Exceptions if the game is invalid, else the game will start.
     */
    public static void checkGameSettings(String[] players, int[] cards) throws Exception {
        if (players == null || players.length == 0) {
            throw new IllegalArgumentException("You need to specify the players as an array of players");
        }
        if (cards == null || cards.length == 0) {
            throw new IllegalArgumentException("You need to specify the cards as an array of integers");
        }
        if (cards.length < players.length * HAND_SIZE) {
            throw new IllegalArgumentException("There was not enough cards for all players");
        }
        if (cards.length > players.length * HAND_SIZE) {
            throw new IllegalArgumentException("There was too many cards for the amount of players");
        }

        Set<Integer> cardSet = new HashSet<>();
        for (int i = 0; i < cards.length; i++) {
            cardSet.add(cards[i]);
        }

        if (cards.length != cardSet.size()) {
            throw new Exception("The same card can't be dealt more than once");
        }
    }

    /**
     * Gets the highest Card in the players hand.
     * <p>
     *     The hand is sorted in a decreasing fashion, and it is just to return the first.
     * </p>
     */
    public static int getHighestCard(List<Card> hand) {
        return hand.get(0).getValue();
    }

    /**
     * If the game is ended in a draw, then the player with the greatest high card wins.
     * <p>
     *     This is a little extension of the current rules of the game, and is added
     *     as a extra rule. It can be implemented simply by adding the players that
     *     are at a draw into a list, and then passing it into this function.
     * </p>
     * @param players - A list of players at a draw.
     * @return A list with players with also same high card.
     */
    public static List<Player> highCard(List<Player> players) {
        List<Player> winners = new ArrayList<>();
        int highestCardOfAllPlayers = 0;

        for (Player player : players) {
            if (highestCardOfAllPlayers < getHighestCard(player.getHand()))
                highestCardOfAllPlayers = getHighestCard(player.getHand());
        }

        int finalHighestCardOfAllPlayers = highestCardOfAllPlayers;
        winners.addAll(players.stream()
                              .filter(player -> finalHighestCardOfAllPlayers == getHighestCard(player.getHand()))
                              .collect(Collectors.toList()));

        return winners;
    }


}
