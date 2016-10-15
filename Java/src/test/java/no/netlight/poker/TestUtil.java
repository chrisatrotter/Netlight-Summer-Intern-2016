package no.netlight.poker;

import no.netlight.poker.Card;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {

    public static List<Card> createListOfCards(int... cardIds) {
        List<Card> cards = new ArrayList<>();
        for (int cardId : cardIds) {
            cards.add(new Card(cardId));
        }
        return cards;
    }

}
