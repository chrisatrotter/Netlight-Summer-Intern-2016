# Netlight Summer Internship - Poker
The task is to implement a simplified variant of a poker game in one of the available languages.

Read this document carefully before starting to make sure you have fully understood the assignment.

## Poker Rules
Each player should have 5 cards and the best hand will win. If two players or more are tied with the same hand they will share the first place. The deck of cards is a normal one with 52 cards.

The most valuable card is _Ace of Spades_ and the least valuable is _2 of Diamonds_. The suits are valued in the following order: diamonds (lowest), clubs, hearts, and spades (highest).

__Scoring__

| Hand            | Score |
|-----------------|-------|
| Straight Flush  | 4     |
| Full House      | 3     |
| One Pair        | 2     |
| High Card       | 1     |

_Ace should be treated as both 1 and 14 in straights._

## Assignment Rules
You are not allowed to use any external libraries/dependencies and you are free to choose **one** of the available languages.

The goal is to implement the rules of this game. Keep in mind that code quality will be evaluated along with making sure that the code fulfills the requirements and makes the tests pass.

#### Note
It is expected in all of the languages that a game with players should be created based on a list of player names and the cards that have been dealt. The cards that have been dealt are the list of cards based on their location in the deck and each player will get 5 cards from the list in the same order as they are defined for the player names.

__Example__

| ID | Card            |
|----|-----------------|
| 1  | 2 of Diamonds   |
| 13 | Ace of Diamonds |
| 20 | 8 of Clubs      |
| 38 | King of Hearts  |
| 50 | Queen of Spades |

### Java
The code can be found in `/Java`. Open the project in your favorite IDE.

It is possible to open the project as a maven project or a java project including the lib directory to include the needed dependencies.

The tests can be found in `/Java/src/test/`

### JavaScript
The code can be found in `/JavaScript`. Begin with installing the dependencies for the project using `npm install` and after completion it will be possible to run the tests with `npm run test` and `npm run test:watch`.

An empty base have been provided as a starting point in `/JavaScript/src`. Use this as a start for the needed implementation.

The tests can be found in `/JavaScript/tests.js`

The answer will be tested against Node **4.5**.

### Python
The code can be found in `/Python`.

An empty base have been provided as a starting point in `/Python/src`. Use this as a start for the needed implementation.

Tests for your program can be found in `/Python/tests.py`.

The answer will be tested against Python **3.5.2**.

## Submit your solution
Submit back your solution via email in the language you selected.
