package be.he2b.atlir5.skyjo.dbManagement.dbSpecification;

/**
 * The type Data user specification.
 */
public class DataUserSpecification {
    private int idUser;
    private int nbrGames;
    private int lowestcore;
    private int nbrWins;


    /**
     * Instantiates a new Data user specification.
     *
     * @param idUser     the id user
     * @param nbrGames   the nbr games
     * @param lowestcore the lowestcore
     * @param nbrWins    the nbr wins
     */
    public DataUserSpecification(int idUser, int nbrGames, int lowestcore, int nbrWins) {
        this.idUser = idUser;
        this.nbrGames = nbrGames;
        this.lowestcore = lowestcore;
        this.nbrWins = nbrWins;
    }

    /**
     * Instantiates a new Data user specification.
     *
     * @param idUser the id user
     */
    public DataUserSpecification(int idUser) {
        this.idUser = idUser;
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
     * Gets nbr games.
     *
     * @return the nbr games
     */
    public int getNbrGames() {
        return nbrGames;
    }

    /**
     * Gets lowestcore.
     *
     * @return the lowestcore
     */
    public int getLowestcore() {
        return lowestcore;
    }

    /**
     * Gets nbr wins.
     *
     * @return the nbr wins
     */
    public int getNbrWins() {
        return nbrWins;
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
     * Sets nbr games.
     *
     * @param nbrGames the nbr games
     */
    public void setNbrGames(int nbrGames) {
        this.nbrGames = nbrGames;
    }

    /**
     * Sets lowestcore.
     *
     * @param lowestcore the lowestcore
     */
    public void setLowestcore(int lowestcore) {
        this.lowestcore = lowestcore;
    }

    /**
     * Sets nbr wins.
     *
     * @param nbrWins the nbr wins
     */
    public void setNbrWins(int nbrWins) {
        this.nbrWins = nbrWins;
    }
}