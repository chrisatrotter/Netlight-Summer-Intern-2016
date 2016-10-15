import unittest

from src import card
from src import player
from src import game

class TestGame(unittest.TestCase):

    def setUp(self):
            self.g1 = game.Game(['Unicorn', 'Consultant', 'Ada Lovelace', 'Grace Hopper'],
                          [4, 52, 17, 20, 35, 42, 13, 37, 5, 9, 29, 28 ,39, 27, 30, 24, 11, 50, 51, 38])
            self.g2 = game.Game(['Unicorn', 'Consultant', 'Ada Lovelace', 'Grace Hopper'],
                        [4, 52, 17, 20, 35, 42, 13, 37, 5, 9, 29, 28 ,39, 27, 30, 24, 11, 50, 51, 38])

            self.g3 = game.Game(['Steve Jobs', 'Steve Balmer', 'Bill Gates'],
                        [4, 10, 17, 20, 35, 52, 51, 50, 49, 47, 22, 13, 37, 5, 9])

    def test_constructor(self):

        def it_should_handle_incorrect_values_for_players(self):
            with self.assertRaises(Exception) as context:
                game.Game(0,[])
            self.assertTrue("You need to specify the players as an array of players" in str(context.exception))
            with self.assertRaises(Exception) as context:
                game.Game(None,[])
            self.assertTrue("You need to specify the players as an array of players" in str(context.exception))
            with self.assertRaises(Exception) as context:
                game.Game("two",[])
            self.assertTrue("You need to specify the players as an array of players" in str(context.exception))
            with self.assertRaises(Exception) as context:
                game.Game([],[])
            self.assertTrue("You need to specify the players as an array of players" in str(context.exception))

        def it_should_handle_empty_constructor(self):
             with self.assertRaises(Exception) as context:
                game.Game()
             self.assertTrue("You need to specify the number of players and cards" in str(context.exception))

        def it_should_handle_no_cards(self):
            with self.assertRaises(Exception) as context:
                game.Game(["a","b"],[])
            self.assertTrue("You need to specify a list of cards" in str(context.exception))

        def it_should_handle_too_few_cards(self):
            with self.assertRaises(Exception) as context:
                game.Game(["a","b"],[1,2,3])
            self.assertTrue("There was not enough cards for all players" in str(context.exception))

        def it_should_handle_too_many_cards(self):
            with self.assertRaises(Exception) as context:
                game.Game(["a","b"],[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16])
            self.assertTrue("There were too many cards for the amount of players" in str(context.exception))

        def it_should_not_allow_multiple_copies_of_the_same_card(self):
            with self.assertRaises(Exception) as context:
                game.Game(["a","b"],[1,1,2,3,4,5,6,7,8,8])
            self.assertTrue("The same card can't be dealt more than once" in str(context.exception))

        it_should_handle_incorrect_values_for_players(self)
        it_should_handle_empty_constructor(self)
        it_should_handle_no_cards(self)
        it_should_handle_too_few_cards(self)
        it_should_handle_too_many_cards(self)
        it_should_not_allow_multiple_copies_of_the_same_card(self)

    def test_get_player(self):

        def it_should_handle_incorrect_input(self):
            with self.assertRaises(Exception) as context:
                self.g1.get_player(-1)
            self.assertTrue("Not a valid player" in str(context.exception))
            with self.assertRaises(Exception) as context:
                self.g1.get_player(5)
            self.assertTrue("Not a valid player" in str(context.exception))

        def it_should_get_the_correct_player(self):
            self.assertEqual(self.g1.get_player(1), "Unicorn")
            self.assertEqual(self.g1.get_player(2), "Consultant")
            self.assertEqual(self.g1.get_player(3), "Ada Lovelace")
            self.assertEqual(self.g1.get_player(4), "Grace Hopper")

        it_should_handle_incorrect_input(self)
        it_should_get_the_correct_player(self)



    def test_get_winners(self):

         def it_should_give_the_correct_winners(self):
            self.assertTrue((self.g2.get_player(3) in self.g2.get_winners())
                            and (len(self.g2.get_winners())==1))
            self.assertTrue((self.g3.get_player(1) in self.g3.get_winners()
                             and self.g3.get_player(3) in self.g3.get_winners()) and (len(self.g3.get_winners()) == 2))

         it_should_give_the_correct_winners(self)




class TestCard(unittest.TestCase):

    def test_card_string(self):
        def it_should_display_the_correct_string_representation():
            self.assertEqual(str(card.Card(10)), "Jack of Diamonds")
            self.assertEqual(str(card.Card(20)), "8 of Clubs")
            self.assertEqual(str(card.Card(38)), "King of Hearts")
            self.assertEqual(str(card.Card(52)), "Ace of Spades")

        it_should_display_the_correct_string_representation()


    def test_get_value(self):
        def it_should_get_the_correct_rank_for_the_card():
            self.assertEqual(card.Card(2).get_rank(),3)
            self.assertEqual(card.Card(20).get_rank(),8)
            self.assertEqual(card.Card(38).get_rank(),13)
            self.assertEqual(card.Card(52).get_rank(),14)

        it_should_get_the_correct_rank_for_the_card()

class TestPlayer(unittest.TestCase):

    def setUp(self):
        self.unicorn = player.Player("Unicorn",[card.Card(4),card.Card(52),card.Card(17),card.Card(20),card.Card(35)])
        self.consultant = player.Player("Consultant",[card.Card(42),card.Card(13),card.Card(37),card.Card(5),card.Card(9)])
        self.lovelace = player.Player("Ada Lovelace",[card.Card(29),card.Card(28),card.Card(39),card.Card(27),card.Card(30)])
        self.hopper = player.Player("Grace Hopper", [card.Card(24),card.Card(11),card.Card(50),card.Card(51),card.Card(38)])

    def test_player_string(self):

        def it_should_display_the_correct_string_representation(self):
            self.assertEqual(str(self.unicorn), "Unicorn has Ace of Spades, 10 of Hearts, 8 of Clubs, 5 of Clubs, 5 of Diamonds")
            self.assertEqual(str(self.consultant),"Consultant has Ace of Diamonds, Queen of Hearts, 10 of Diamonds, 6 of Diamonds, 4 of Spades")
            self.assertEqual(str(self.lovelace),"Ada Lovelace has Ace of Hearts, 5 of Hearts, 4 of Hearts, 3 of Hearts, 2 of Hearts")
            self.assertEqual(str(self.hopper), "Grace Hopper has King of Spades, King of Hearts, Queen of Spades, Queen of Clubs, Queen of Diamonds")

        it_should_display_the_correct_string_representation(self)

    def test_get_hand(self):

        def it_should_get_the_player_hand_as_a_list_of_cards(self):
            self.assertEqual(self.unicorn.get_hand().length(),5)
            self.assertEqual(self.consultant.get_hand(),5)
            self.assertEqual(self.lovelace.get_hand(),5)
            self.assertEqual(self.hopper.get_hand(),5)

            self.assertEqual(self.unicorn.get_hand(),[card.Card(52),card.Card(35),card.Card(20),card.Card(17),card.Card(4)])
            self.assertEqual(self.consultant.get_hand(),[card.Card(13),card.Card(37),card.Card(9),card.Card(5),card.Card(42)])
            self.assertEqual(self.lovelace.get_hand(), [card.Card(39),card.Card(30),card.Card(29),card.Card(28),card.Card(27)])
            self.assertEqual(self.hopper.get_hand(),[card.Card(51),card.Card(38),card.Card(50),card.Card(24),card.Card(11)])

            it_should_get_the_player_hand_as_a_list_of_cards(self)

    def test_get_score(self):

        def it_should_return_the_correct_score_based_on_the_hands(self):
            self.assertEqual(self.unicorn.get_score(),2)
            self.assertEqual(self.consultant.get_score(),1)
            self.assertEqual(self.lovelace.get_score(),4)
            self.assertEqual(self.hopper.get_score(),3)

        it_should_return_the_correct_score_based_on_the_hands(self)


if __name__ == "__main__":
    unittest.main()
