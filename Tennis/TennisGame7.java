package tennis;

public class TennisGame7 implements TennisGame {
    private final TennisGame1 delegate;

    /**
     * Creates a new TennisGame7 instance with the given player names and initializes a delegate TennisGame1 instance.
     *
     * @param player1Name the name of the first player
     * @param player2Name the name of the second player
     */
    public TennisGame7(String player1Name, String player2Name) {
        delegate = new TennisGame1(player1Name, player2Name);
    }

    public String getScore(){
        return "Current score: " + delegate.getScore() + ", enjoy your game!";
    }

    public void wonPoint(String player){
        delegate.wonPoint(player);
    }
}
