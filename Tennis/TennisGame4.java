package tennis;

public class TennisGame4 implements TennisGame {

    private final TennisGame1 delegate;

    public TennisGame4(String player1Name, String player2Name) {
        delegate = new TennisGame1(player1Name, player2Name);
    }

    public String getScore(){
        return delegate.getScore();
    }

    public void wonPoint(String player){
        delegate.wonPoint(player);
    }
}
