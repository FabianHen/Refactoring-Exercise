package tennis;

public class TennisGame1 implements TennisGame {
    private final Player player1;
    private final Player player2;
    private GameState gameState;

    /**
     * Creates a new TennisGame1 instance with the given player names and initializes the game state to NormalState.
     *
     * @param player1Name the name of the first player
     * @param player2Name the name of the second player
     */
    public TennisGame1(String player1Name, String player2Name) {
        this.player1 = new Player(player1Name);
        this.player2 = new Player(player2Name);
        this.gameState = new NormalState();
    }

    public void wonPoint(String playerName) {
        if (player1.getName().equals(playerName)){
           player1.scorePoint();
        }
        else if(player2.getName().equals(playerName)) {
            player2.scorePoint();
        }
        gameState = gameState.getNextState(player1, player2);
    }

    public String getScore() {
        return gameState.getScore(player1, player2);
    }
}
