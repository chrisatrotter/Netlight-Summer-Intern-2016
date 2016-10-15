const expect = require('expect');

const Game = require('./src/Game');
const Card = require('./src/Card');
const Player = require('./src/Player');

describe('Poker', () => {
  // Step 1.
  describe('Card', () => {
    describe('toString', () => {
      it('should display the correct string representation', () => {
        expect(new Card(2).toString()).toEqual('3 of Diamonds');
        expect(new Card(20).toString()).toEqual('8 of Clubs');
        expect(new Card(38).toString()).toEqual('King of Hearts');
        expect(new Card(52).toString()).toEqual('Ace of Spades');
      });
    });

    describe('getValue', () => {
      it('should get the correct rank for the card', () => {
        expect(new Card(2).getValue()).toEqual(3);
        expect(new Card(20).getValue()).toEqual(8);
        expect(new Card(38).getValue()).toEqual(13);
        expect(new Card(52).getValue()).toEqual(14);
      });
    });
  });

  // Step 2.
  describe('Player', () => {
    const unicorn = new Player('Unicorn', [
      new Card(4),
      new Card(52),
      new Card(17),
      new Card(20),
      new Card(35)
    ]);

    const consultant = new Player('Consultant', [
      new Card(42),
      new Card(13),
      new Card(37),
      new Card(5),
      new Card(9)
    ]);

    const lovelace = new Player('Ada Lovelace', [
      new Card(29),
      new Card(28),
      new Card(39),
      new Card(27),
      new Card(30)
    ]);

    const hopper = new Player('Grace Hopper', [
      new Card(24),
      new Card(11),
      new Card(50),
      new Card(51),
      new Card(38)
    ]);

    describe('toString', () => {
      it('should display the correct string representation', () => {
        expect(unicorn.toString())
          .toEqual('Unicorn has Ace of Spades, 10 of Hearts, 8 of Clubs, 5 of Clubs, 5 of Diamonds');

        expect(consultant.toString())
          .toEqual('Consultant has Ace of Diamonds, Queen of Hearts, 10 of Diamonds, 6 of Diamonds, 4 of Spades');

        expect(lovelace.toString())
          .toEqual('Ada Lovelace has Ace of Hearts, 5 of Hearts, 4 of Hearts, 3 of Hearts, 2 of Hearts');

        expect(hopper.toString())
          .toEqual('Grace Hopper has King of Spades, King of Hearts, Queen of Spades, Queen of Clubs, Queen of Diamonds');
      });
    });

    describe('getHand', () => {
      it('should get the player hand as a list of cards', () => {
        expect(unicorn.getHand().length).toBe(5);
        expect(consultant.getHand().length).toBe(5);
        expect(lovelace.getHand().length).toBe(5);
        expect(hopper.getHand().length).toBe(5);

        expect(unicorn.getHand())
          .toEqual([
            new Card(52),
            new Card(35),
            new Card(20),
            new Card(17),
            new Card(4)
          ]);
        expect(consultant.getHand())
          .toEqual([
            new Card(13),
            new Card(37),
            new Card(9),
            new Card(5),
            new Card(42)
          ]);
        expect(lovelace.getHand())
          .toEqual([
            new Card(39),
            new Card(30),
            new Card(29),
            new Card(28),
            new Card(27)
          ]);
        expect(hopper.getHand())
          .toEqual([
            new Card(51),
            new Card(38),
            new Card(50),
            new Card(24),
            new Card(11)
          ]);
      });
    });

    describe('getScore', () => {
      it('should return the correct score based on the hands', () => {
        expect(unicorn.getScore()).toBe(2);
        expect(consultant.getScore()).toBe(1);
        expect(lovelace.getScore()).toBe(4);
        expect(hopper.getScore()).toBe(3);
      });
    });
  });

  // Step 3.
  describe('Game', () => {
    describe('constructor', () => {
      it('should handle empty constructor', () => {
        expect(() => new Game())
          .toThrow(/You need to specify the number of players and cards/);
      });

      it('should handle incorrect values for players', () => {
        expect(() => new Game(0, []))
          .toThrow(/You need to specify the players as an array of players/);
        expect(() => new Game(null, []))
          .toThrow(/You need to specify the players as an array of players/);
        expect(() => new Game('two', []))
          .toThrow(/You need to specify the players as an array of players/);
        expect(() => new Game([], []))
          .toThrow(/You need to specify the players as an array of players/);
      });

      it('should handle no cards', () => {
        expect(() => new Game(['a', 'b']))
          .toThrow(/You need to specify a list of cards/);
      });

      it('should handle incorrect values for cards', () => {
        expect(() => new Game(['a', 'b'], null))
          .toThrow(/You need to specify the cards as an array of integers/);
        expect(() => new Game(['a', 'b'], 1))
          .toThrow(/You need to specify the cards as an array of integers/);
        expect(() => new Game(['a', 'b'], '1, 2, 3, 4'))
          .toThrow(/You need to specify the cards as an array of integers/);
        expect(() => new Game(['a', 'b'], [1, 2, 3, '4']))
          .toThrow(/You need to specify the cards as an array of integers/);
      });

      it('should handle too few cards', () => {
        expect(() => new Game(['a', 'b'], [1, 2, 3, 4, 5, 6, 7, 8, 9]))
          .toThrow(/There was not enough cards for all players/);
      });

      it('should handle too many cards', () => {
        expect(() => new Game(['a', 'b'], [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]))
          .toThrow(/There was too many cards for the amount of players/);
      });

      it('should the same card multiple times', () => {
        expect(() => new Game(['a', 'b'], [1, 2, 3, 4, 5, 2, 7, 8, 9, 10]))
          .toThrow(/The same card can't be dealt more than once/);
      });
    });

    describe('getPlayer', () => {
      const game = new Game(
        ['Unicorn', 'Consultant', 'Ada Lovelace', 'Grace Hopper'],
        [4, 52, 17, 20, 35, 42, 13, 37, 5, 9, 29, 28 ,39, 27, 30, 24, 11, 50, 51, 38]
      );

      it('should handle incorrect input', () => {
        expect(() => game.getPlayer(-1)).toThrow(/Not a valid player/);
        expect(() => game.getPlayer()).toThrow(/Not a valid player/);
        expect(() => game.getPlayer(5)).toThrow(/Not a valid player/);
      });

      it('should get the correct player', () => {
        expect(game.getPlayer(1).getName()).toEqual('Unicorn');
        expect(game.getPlayer(2).getName()).toEqual('Consultant');
        expect(game.getPlayer(3).getName()).toEqual('Ada Lovelace');
        expect(game.getPlayer(4).getName()).toEqual('Grace Hopper');
      });
    });

    describe('getWinners', () => {

      it('should give the correct winners', () => {
        const game1 = new Game(
          ['Unicorn', 'Consultant', 'Ada Lovelace', 'Grace Hopper'],
          [4, 52, 17, 20, 35, 42, 13, 37, 5, 9, 29, 28 ,39, 27, 30, 24, 11, 50, 51, 38]
        );

        expect(game1.getWinners()).toEqual([
          game1.getPlayer(3)
        ]);

        const game2 = new Game(
          ['Steve Jobs', 'Steve Balmer', 'Bill Gates'],
          [4, 10, 17, 20, 35, 52, 51, 50, 49, 47, 22, 13, 37, 5, 9]
        );

        expect(game2.getWinners()).toEqual([
          game2.getPlayer(1),
          game2.getPlayer(3)
        ]);
      });
    });
  });
});
