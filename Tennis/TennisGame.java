package tennis;

public interface TennisGame {
    /**
     * Records a point won by the player with the given name.
     *
     * @param playerName the name of the player who won the point
     */
    void wonPoint(String playerName);

    /**
     * Gets the current score of the game as a string.
     *
     * @return the current score as a string
     */
    String getScore();
}