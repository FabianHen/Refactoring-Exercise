package tennis;

public interface GameState {
    /**
     * Determines the next game state based on the current scores of the players.
     *
     * @param player1 the first player
     * @param player2 the second player
     * @return the next game state
     */
    GameState getNextState(Player player1, Player player2);

    /**
     * Gets the current score of the game as a string based on the scores of the players.
     *
     * @param player1 the first player
     * @param player2 the second player
     * @return the current score as a string
     */
    String getScore(Player player1, Player player2);
}
