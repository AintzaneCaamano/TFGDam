package es.gameapp.multigameapp.modelo;

public class Score {

    private int idScore, idJuegoFk;
    private String player, score;

    public Score(){}

    public Score(int idScore, int idJuegoFk, String player, String score) {
        this.idScore = idScore;
        this.idJuegoFk = idJuegoFk;
        this.player = player;
        this.score = score;
    }

    public int getIdScore() {
        return idScore;
    }

    public void setIdScore(int idScore) {
        this.idScore = idScore;
    }

    public int getIdJuegoFk() {
        return idJuegoFk;
    }

    public void setIdJuegoFk(int idJuegoFk) {
        this.idJuegoFk = idJuegoFk;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
