package no.netlight.poker;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class GameTest {

    private final Game validGame1;
    private final Game validGame2;

    public GameTest() throws Exception {
        validGame1 = new Game(
                new String[]{"Unicorn", "Consultant", "Ada Lovelace", "Grace Hopper"},
                new int[]{4, 52, 17, 20, 35, 42, 13, 37, 5, 9, 29, 28, 39, 27, 30, 24, 11, 50, 51, 38});
        validGame2 = new Game(
                new String[]{"Steve Jobs", "Steve Balmer", "Bill Gates"},
                new int[]{4, 10, 17, 20, 35, 52, 51, 50, 49, 47, 22, 13, 37, 5, 9});
    }

    @Test
    public void invalidPlayers_nullOrEmpty() throws Exception {
        Exception expectedException = new Exception("You need to specify the players as an array of players");
        assertGameThrow(expectedException, () -> new Game(null, null));
        assertGameThrow(expectedException, () -> new Game(new String[]{}, null));
    }

    @Test
    public void invalidCards_nullOrEmpty() throws Exception {
        Exception expectedException = new Exception("You need to specify the cards as an array of integers");
        String[] players = {"a", "b"};
        assertGameThrow(expectedException, () -> new Game(players, null));
        assertGameThrow(expectedException, () -> new Game(players, new int[]{}));
    }

    @Test
    public void tooFewCards() throws Exception {
        Exception expectedException = new Exception("There was not enough cards for all players");
        assertGameThrow(expectedException, () -> new Game(new String[]{"a", "b"}, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
    }

    @Test
    public void tooManyCards() throws Exception {
        Exception expectedException = new Exception("There was too many cards for the amount of players");
        assertGameThrow(expectedException, () -> new Game(new String[]{"a", "b"}, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}));
    }

    @Test
    public void duplicates() throws Exception {
        Exception expectedException = new Exception("The same card can't be dealt more than once");
        assertGameThrow(expectedException,
                () -> new Game(new String[]{"a", "b"}, new int[]{1, 2, 3, 4, 5, 2, 7, 8, 9, 10}));
    }

    @Test
    public void getPlayers_incorrectInput() throws Exception {
        Exception exception = new Exception("Not a valid player");
        assertGameThrow(exception, () -> validGame1.getPlayer(0));
        assertGameThrow(exception, () -> validGame1.getPlayer(5));
    }

    @Test
    public void getPlayers_correctPlayer() throws Exception {
        assertEquals("Unicorn", validGame1.getPlayer(1).getName());
        assertEquals("Consultant", validGame1.getPlayer(2).getName());
        assertEquals("Ada Lovelace", validGame1.getPlayer(3).getName());
        assertEquals("Grace Hopper", validGame1.getPlayer(4).getName());
    }

    @Test
    public void getWinners() throws Exception {
        assertEquals(new ArrayList<Player>() {{
            add(validGame1.getPlayer(3));
        }}, validGame1.getWinners());

        assertEquals(new ArrayList<Player>() {{
            add(validGame2.getPlayer(1));
            add(validGame2.getPlayer(3));
        }}, validGame2.getWinners());

    }

    private interface GameFunction {
        void test() throws Exception;
    }

    private void assertGameThrow(Exception ex, GameFunction gameFunction) throws Exception {
        String message = "Exception not expected. Expected result was:" + ex.getMessage();
        boolean exceptionIsThrown = false;
        try {
            gameFunction.test();
        } catch (Exception e) {
            exceptionIsThrown = true;
            if (e.getMessage() == null || !e.getMessage().equals(ex.getMessage())) {
                throw new Exception(message);
            }
        }
        if (!exceptionIsThrown) {
            throw new Exception(message);
        }
    }
}