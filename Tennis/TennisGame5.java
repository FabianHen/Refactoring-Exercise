package tennis;

public class TennisGame5 implements TennisGame {

    private final TennisGame1 delegate;

    /**
     * Creates a new TennisGame5 instance with the given player names and initializes a delegate TennisGame1 instance.
     *
     * @param player1Name the name of the first player
     * @param player2Name the name of the second player
     */
    public TennisGame5(String player1Name, String player2Name) {
        delegate = new TennisGame1(player1Name, player2Name);
    }

    public String getScore(){
        return delegate.getScore();
    }

    public void wonPoint(String player){
        delegate.wonPoint(player);
    }
}
