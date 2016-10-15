package no.netlight.poker;

import static no.netlight.poker.util.GameUtil.SUIT_SIZE;

public class Card {
    private RANK rank;
    private SUIT suit;
    private int id;

    public Card(int id) {
        setValue(id);
        setSuit(id);
        setRank(id);
    }

    /**
     * Sets the value of the given card based on id.
     * <p>
     *     The given value of the deck is calculated by taking
     *     the modulo of the given id by the size of the suit.
     *     suit size = 13.
     *     Note: The value is 2-14, where 14 is Ace a special case
     *           when taking modulo by size of 13.
     * </p>
     * @param int id a unique value of a card.
     */
    private void setValue(int id) {
        this.id = isAce(id) ? SUIT_SIZE+1 : ((id % SUIT_SIZE)+1);
    }

    /**
     * Checks if it is an Ace.
     * @param id
     * @return returns true if it is an ace.
     */
    private boolean isAce(int id) {
        return ((id % RANK.values().length)) == 0;
    }

    /**
     * Sets the rank of the given card based on id.
     * @param int id a unique value of a card.
     */
    private void setRank(int id) {
        rank = RANK.values()[((id-1)%(SUIT_SIZE))];
    }

    /**
     * Sets the suit of the given card based on id.
     * @param int id a unique value of a card.
     */
    private void setSuit(int id) {
        suit = SUIT.values()[(((id-1)/(SUIT_SIZE)))];
    }

    /**
     * Gets the id of the given card.
     * @return Integer id for the give id of the card.
     */
    public int getValue() {
        return id;
    }

    /**
     * Gets the suit of the given card.
     * @return Enum-type SUIT for the give suit of the card.
     */
    public SUIT getSuit() {
        return suit;
    }

    /**
     * Gets the rank of the given card.
     * @return Enum-type RANK for the give rank of the card.
     */
    public RANK getRank() {
        return rank;
    }

    /**
     * Prints the card to terminal
     * @return A String with the card Rank and Suit.
     */
    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    /**
     * Override of the equals function.
     * @param o - Another card.
     * @return check if the card string is equal.
     */
    @Override
    public boolean equals(Object o) {
        return this.toString().equals(o.toString());
    }
}
