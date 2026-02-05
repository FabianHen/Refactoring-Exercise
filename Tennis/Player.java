package tennis;

public class Player {

    private int score;

    private final String name;

    /**
     * Creates a new player with the given name and an initial score of 0.
     *
     * @param name the name of the player
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Gets the current score of the player.
     *
     * @return the current score
     */
    public int getScore() {
        return score;
    }

    /**
     * Increments the player's score by one point.
     */
    public void scorePoint(){
        score++;
    }

    /**
     * Gets the name of the player.
     *
     * @return the name of the player
     */
    public String getName() {
        return name;
    }
}
