package es.gameapp.multigameapp.database;

public class Score {
    private String name;
    private String score;
    private String game;

    Score(){ }

    public Score(String name, String score, String game){
        this.name=name;
        this.score=score;
        this.game=game;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public String getScore() {
        return score;
    }

    public String getGame() {
        return game;
    }
}