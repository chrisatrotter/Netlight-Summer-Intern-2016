package no.netlight.poker;

/**
 * Created by chrisat on 14.10.16.
 */

/**
 * Rank of the card value.
 * <p>
 *     The Rank is ordered in increasing order to separate the
 *     difference between the lowest card value to the highest.
 * </p>
 */
public enum RANK {
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("Jack"),
    QUEEN("Queen"),
    KING("King"),
    ACE("Ace");

    private String rank;

    RANK(String rank) {
        this.rank = rank;
    }

    public String toString() {
        return rank;
    }
}