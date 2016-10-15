package no.netlight.poker;

import no.netlight.poker.Card;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CardTest {

    @Test
    public void getValue() throws Exception {
        assertEquals(3, new Card(2).getValue());
        assertEquals(8, new Card(20).getValue());
        assertEquals(13, new Card(38).getValue());
        assertEquals(14, new Card(52).getValue());
    }

    @Test
    public void cardToString() throws Exception {
        assertEquals("3 of Diamonds", new Card(2).toString());
        assertEquals("8 of Clubs", new Card(20).toString());
        assertEquals("King of Hearts", new Card(38).toString());
        assertEquals("Ace of Spades", new Card(52).toString());
    }

}