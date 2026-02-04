package tennis;

public class TennisGame7 implements TennisGame {
    private final TennisGame1 delegate;

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
