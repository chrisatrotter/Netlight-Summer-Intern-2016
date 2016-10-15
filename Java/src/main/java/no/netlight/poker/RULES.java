package no.netlight.poker;

/**
 * Created by chrisat on 14.10.16.
 */

/**
 * Rules of the game.
 * <p>
 *     The rules are ordered in increasing order to separate
 *     the worst hand to the best hand based on the game rules.
 * </p>
 */
public enum RULES {
    HIGH_CARD(1),       // Highest value card.
    ONE_PAIR(2),        // Two cards of the same value.
    FULL_HOUSE(3),      // Three of a kind and a pair.
    STRAIGHT_FLUSH(4);  // All cards are consecutive values of same suit.

    private int rules;

    RULES(int v) {
        rules = v;
    }
}

