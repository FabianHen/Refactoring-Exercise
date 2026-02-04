package tennis;

public class TennisGame1 implements TennisGame {
    private final Player player1;
    private final Player player2;
    private GameState gameState;

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
