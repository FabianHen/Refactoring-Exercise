package tennis;

public class TennisGame1 implements TennisGame {
    private final int WIN_THRESHOLD = 4;
    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if ("player1".equals(playerName))
            m_score1 += 1;
        else
            m_score2 += 1;
    }

    public String getScore() {
        if (m_score1 == m_score2)
        {
            return getTieScore();
        }
        else if (m_score1 >= WIN_THRESHOLD || m_score2 >= WIN_THRESHOLD)
        {
            return getWinScore();
        }
        else
        {
            return getNormalScore();
        }
    }

    private String getNormalScore() {
        int tempScore;
        StringBuilder score = new StringBuilder();
        for (int i = 1; i < 3; i++)
        {
            if (i == 1) tempScore = m_score1;
            else { score.append("-"); tempScore = m_score2;}
            switch(tempScore)
            {
                case 0:
                    score.append("Love");
                    break;
                case 1:
                    score.append("Fifteen");
                    break;
                case 2:
                    score.append("Thirty");
                    break;
                case 3:
                    score.append("Forty");
                    break;
            }
        }
        return score.toString();
    }

    private String getWinScore() {
        int minusResult = m_score1-m_score2;
        if (minusResult==1) {
            return "Advantage player1";
        }else if (minusResult ==-1){
            return "Advantage player2";
        }else if (minusResult>=2) {
            return "Win for player1";
        }else{
            return "Win for player2";
        }
    }

    private String getTieScore() {
        return switch (m_score1) {
            case 0 -> "Love-All";
            case 1 -> "Fifteen-All";
            case 2 -> "Thirty-All";
            default -> "Deuce";
        };
    }
}
