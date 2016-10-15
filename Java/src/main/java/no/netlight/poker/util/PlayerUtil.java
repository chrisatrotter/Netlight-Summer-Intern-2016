package no.netlight.poker.util;

import no.netlight.poker.Card;
import no.netlight.poker.RANK;
import no.netlight.poker.RULES;
import no.netlight.poker.SUIT;

import java.util.*;
import java.util.stream.Collectors;

import static no.netlight.poker.util.GameUtil.HAND_SIZE;

/**
 * Created by chrisat on 14.10.16.
 */
public class PlayerUtil {

    /**
     * Evaluates the hand of the player and return a value if won.
     * @param hand - the given Player's hand, which the player evaluates(independently).
     * @return The score of the given hand based on the rules of poker.
     */
    public static int evaluateHand(List<Card> hand) {
        if (checkStraightFlush(hand)) {
            return RULES.STRAIGHT_FLUSH.ordinal();
        } else if (checkFullHouse(hand)) {
            return RULES.FULL_HOUSE.ordinal();
        } else if (checkPair(hand)) {
            return RULES.ONE_PAIR.ordinal();
        } else {
            return RULES.HIGH_CARD.ordinal();
        }
    }

    /**
     * Checking if the current hand is a Straight Flush.
     * @param hand - the given Player's hand, checking if it is a Straight Flush.
     * @return true: if the current hand is a Straight Flush, else false.
     */
    private static boolean checkStraightFlush(List<Card> hand) {
        if (checkFlush(hand) == true && checkStraight(hand) == true) {
            return true;
        }
        return false;
    }

    /**
     * Checking if the current hand is a Straight.
     * @param hand - the given Player's hand, checking if it is a Straight.
     * @return true: if the current hand is a Straight, else false.
     */
    private static boolean checkStraight(List<Card> hand) {
        boolean isStraight = true;
        if (hand.get(0).getRank() == RANK.ACE
                && hand.get(1).getRank() == RANK.FIVE
                && hand.get(2).getRank() == RANK.FOUR
                && hand.get(3).getRank() == RANK.THREE
                && hand.get(4).getRank() == RANK.TWO) {
            isStraight = true;
        } else {                            // An ordinary straight.
            for (int i = 0; i < HAND_SIZE-1; i++) {
                isStraight &= hand.get(i).getValue() == hand.get(i+1).getValue() + 1;
            }
        }
        return isStraight;
    }

    /**
     * Checking if the current hand is a Flush.
     * @param hand - the given Player's hand, checking if it is a Flush.
     * @return true: if the current hand is a Flush, else false.
     */
    private static boolean checkFlush(List<Card> hand) {
        SUIT suit = hand.get(0).getSuit();
        List<Card> flush = hand.stream()
                               .filter(card -> card.getSuit().equals(suit))
                               .collect(Collectors.toList());

        return flush.size() == hand.size();
    }

    /**
     * Checking if the current hand is a Full House.
     * @param hand - the given Player's hand, checking if it is a Full House.
     * @return true: if the current hand is a Full House, else false.
     */
    private static boolean checkFullHouse(List<Card> hand) {
        Map<Integer, Integer> freqMap = checkFrequency(hand);
        Set<Integer> fullHouseCheck = new HashSet<>(freqMap.values());

        if (fullHouseCheck.contains(2) && fullHouseCheck.contains(3)) {
            return true;
        }
        return false;
    }

    /**
     * Checking if the current hand is a Single Pair.
     * @param hand - the given Player's hand, checking if it is a Single Pair.
     * @return true: if the current hand is a Single Pair, else false.
     */
    private static boolean checkPair(List<Card> hand) {
        Map<Integer, Integer> freqMap = checkFrequency(hand);

        if (freqMap.containsValue(2)) {
            return true;
        }
        return false;
    }

    /**
     * Check Frequency of a value in a Player's hand.
     * <p>
     *     Used as a subroutine for the methods checkPair(List<Card> hand) and
     *     checkFullHouse(List<Card> hand).
     * </p>
     * @param hand - the given Player's hand, checking if it is a Single Pair.
     * @return A map with the number of occurrences of a card based on rank.
     */
    private static Map<Integer, Integer> checkFrequency(List<Card> hand) {
        Map<Integer, Integer> freqMap = new HashMap<>();

        for (Card c : hand) {
            if (freqMap.containsKey(c.getValue())) {
                freqMap.put(c.getValue(), freqMap.get(c.getValue()) + 1);
            } else {
                freqMap.put(c.getValue(), 1);
            }
        }
        return freqMap;
    }
}