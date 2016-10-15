function Game() {
}

Game.prototype.getPlayer = function getPlayer() {
  throw new Error('Not implemented');
}

Game.prototype.getWinners = function getWinners() {
  throw new Error('Not implemented');
}

module.exports = Game;
