package be.he2b.atlir5.skyjo.dbManagement.dbSpecification;

/**
 * The type Data game specification.
 */
public class DataGameSpecification {
    private int idGame;
    private int idUser;
    private int score;


    /**
     * Instantiates a new Data game specification.
     *
     * @param idGame the id game
     * @param idUser the id user
     * @param score  the score
     */
    public DataGameSpecification(int idGame,int idUser, int score) {
      this.idGame = idGame;
      this.idUser = idUser;
      this.score = score;
    }


    /**
     * Instantiates a new Data game specification.
     *
     * @param idGame the id game
     * @param idUser the id user
     */
    public DataGameSpecification(int idGame, int idUser) {
        this.idGame = idGame;
        this.idUser = idUser;
    }
    public DataGameSpecification(int idGame) {
        this.idGame = idGame;
    }

    /**
     * Gets id game.
     *
     * @return the id game
     */
    public int getIdGame() {
        return idGame;
    }

    /**
     * Gets id user.
     *
     * @return the id user
     */
    public int getIdUser() {
        return idUser;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets id game.
     *
     * @param idGame the id game
     */
    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    /**
     * Sets id user.
     *
     * @param idUser the id user
     */
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    /**
     * Sets score.
     *
     * @param score the score
     */
    public void setScore(int score) {
        this.score = score;
    }
}
