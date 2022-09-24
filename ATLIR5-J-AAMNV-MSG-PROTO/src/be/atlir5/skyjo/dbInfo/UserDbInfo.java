/*
 */
package be.atlir5.skyjo.dbInfo;

import java.io.Serializable;

/**
 *
 * @author justu
 */
public class UserDbInfo implements Serializable{

    private int idUser;
    private int nbrGames;
    private int lowestScore;
    private int nbrWins;

    public int getIdUser() {
        return idUser;
    }

    public int getNbrGames() {
        return nbrGames;
    }

    public int getLowestScore() {
        return lowestScore;
    }

    public int getNbrWins() {
        return nbrWins;
    }

    public UserDbInfo(int idUser, int nbrGames, int lowestScore, int nbrWins) {
        this.idUser = idUser;
        this.nbrGames = nbrGames;
        this.lowestScore = lowestScore;
        this.nbrWins = nbrWins;
    }

}
