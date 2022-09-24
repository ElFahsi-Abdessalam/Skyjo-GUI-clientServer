package be.he2b.atlr5.skyjo.model;

import java.io.Serializable;

/**
 * store the player information ( mail and ID)
 *
 * @author
 */
public class PlayerDetails implements Serializable {

    private final int playerID;
    private final int playerDBId;
    private final String playerMail;

    /**
     * Constructor for playerDetails
     *
     * @param playerID the player ID
     * @param playerDBId the player's ID on the server
     * @param playerMail the player mail
     */
    public PlayerDetails(int playerID, int playerDBId, String playerMail) {
        this.playerID = playerID;
        this.playerMail = playerMail;
        this.playerDBId = playerDBId;
    }

    /**
     * getter of player id
     *
     * @return ID
     */
    public int getPlayerID() {
        return playerID;
    }

    /**
     * getter of the player mail
     *
     * @return player mail
     */
    public String getPlayerName() {
        return playerMail;
    }

    /**
     * getter of the player db id
     * @return player db user id
     */
    public int getPlayerDBId() {
        return playerDBId;
    }

}
