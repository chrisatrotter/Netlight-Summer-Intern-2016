package no.netlight.poker;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
import static no.netlight.poker.util.PlayerUtil.evaluateHand;

public class Player {
    private String name;
    private List<Card> cards;
    private int score;

    public Player(String name, List<Card> cards) throws Exception {
        this.name = name;
        this.cards = sortHand(cards, comparing(Card::getValue).thenComparing(Card::getSuit).reversed());
        this.score = evaluateHand(this.cards);
    }

    /**
     * Sorts the current hand of the player.
     * @return A sorted list of the player's cards(descending-order).
     */
    private static List<Card> sortHand(List<Card> hand, Comparator<Card> comparator) {
        return 	hand.stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
    }

    /**
     * Gets the score of the given player.
     * @return A number of the give score the player has.
     */
    public int getScore() {
        return score;
    }

    /**
     * Gets the hand of the given player.
     * @return the list of cards of the give player's hand.
     */
    public List<Card> getHand() {
        return cards;
    }

    /**
     * Gets the name of the given player.
     * @return String of the give player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Prints the Player and his hand to terminal
     * @return A String with the name and cards.
     */
    public String toString() {
        return String.format("%s has %s", name, cards.stream()
                                                     .map(Card::toString)
                                                     .collect(joining(", ")));
    }
}
