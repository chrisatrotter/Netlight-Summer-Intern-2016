package no.netlight.poker;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest extends TestUtil {

    private Player unicorn;
    private Player consultant;
    private Player lovelace;
    private Player hopper;

    @Before
    public void setUp() throws Exception {
        unicorn = new Player("Unicorn", createListOfCards(4, 52, 17, 20, 35));
        consultant = new Player("Consultant", createListOfCards(42, 13, 37, 5, 9));
        lovelace = new Player("Ada Lovelace", createListOfCards(29, 28, 39, 27, 30));
        hopper = new Player("Grace Hopper", createListOfCards(24, 11, 50, 51, 38));
    }

    @Test
    public void player_toString() throws Exception {
        assertEquals("Unicorn has Ace of Spades, 10 of Hearts, 8 of Clubs, 5 of Clubs, 5 of Diamonds",
                unicorn.toString());
        assertEquals("Consultant has Ace of Diamonds, Queen of Hearts, 10 of Diamonds, 6 of Diamonds, 4 of Spades",
                consultant.toString());
        assertEquals("Ada Lovelace has Ace of Hearts, 5 of Hearts, 4 of Hearts, 3 of Hearts, 2 of Hearts",
                lovelace.toString());
        assertEquals("Grace Hopper has King of Spades, King of Hearts, Queen of Spades, Queen of Clubs, Queen of Diamonds",
                hopper.toString());
    }

    @Test
    public void getHand_correctSize() throws Exception {
        assertEquals(5, unicorn.getHand().size());
        assertEquals(5, consultant.getHand().size());
        assertEquals(5, lovelace.getHand().size());
        assertEquals(5, hopper.getHand().size());
    }

    @Test
    public void getHand_shouldBeOrdered() throws Exception {
        assertEquals(createListOfCards(52, 35, 20, 17, 4), unicorn.getHand());
        assertEquals(createListOfCards(13, 37, 9, 5, 42), consultant.getHand());
        assertEquals(createListOfCards(39, 30, 29, 28, 27), lovelace.getHand());
        assertEquals(createListOfCards(51, 38, 50, 24, 11), hopper.getHand());
    }
}