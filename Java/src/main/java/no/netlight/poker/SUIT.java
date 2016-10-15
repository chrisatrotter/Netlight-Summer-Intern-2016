package no.netlight.poker;

/**
 * Created by chrisat on 14.10.16.
 */

/**
 * SUIT of the card.
 * <p>
 *     The SUIT is ordered in increasing order to separate the
 *     difference between the lowest to the highest SUIT of a card.
 * </p>
 */
public enum SUIT {
    DIAMONDS("Diamonds"),
    CLUBS("Clubs"),
    HEARTS("Hearts"),
    SPADES("Spades");

    private String suit;

    SUIT(String suit) {
        this.suit = suit;
    }

    public String toString() {
        return suit;
    }
}