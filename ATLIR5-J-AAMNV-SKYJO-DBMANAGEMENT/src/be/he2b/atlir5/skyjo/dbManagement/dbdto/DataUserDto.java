package be.he2b.atlir5.skyjo.dbManagement.dbdto;

import be.he2b.atlir5.skyjo.dbManagement.exception.DtoException;

/**
 * The type Data user dto.
 */
public class DataUserDto {
    private int idUser;
    private int nbrGames;
    private int lowestScore;
    private int nbrWins;


    /**
     * constructeur d'un user persistant
     *
     * @param idUser      the id user
     * @param nbrGames    the nbr games
     * @param lowestScore the lowest score
     * @param nbrWins     the nbr wins
     * @throws DtoException the dto exception
     */
    public DataUserDto(int idUser,int nbrGames, int lowestScore, int nbrWins) throws DtoException {
      this.idUser = idUser;
      this.nbrGames = nbrGames;
      this.lowestScore = lowestScore;
      this.nbrWins = nbrWins;
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
    public Integer getNbrGames() {
        return nbrGames;
    }

    /**
     * Gets lowest score.
     *
     * @return the lowest score
     */
    public Integer getLowestScore() {
        return lowestScore;
    }

    /**
     * Gets nbr wins.
     *
     * @return the nbr wins
     */
    public Integer getNbrWins() {
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
    public void setNbrGames(Integer nbrGames) {
        this.nbrGames = nbrGames;
    }

    /**
     * Sets lowest score.
     *
     * @param lowestScore the lowest score
     */
    public void setLowestScore(Integer lowestScore) {
        this.lowestScore = lowestScore;
    }

    /**
     * Sets nbr wins.
     *
     * @param nbrWins the nbr wins
     */
    public void setNbrWins(Integer nbrWins) {
        this.nbrWins = nbrWins;
    }

    @Override
    public String toString() {
        return "[UserDto] (" + getIdUser() + ") " + getLowestScore() + " " + getNbrGames()+ " " + getNbrWins();
    }
}
