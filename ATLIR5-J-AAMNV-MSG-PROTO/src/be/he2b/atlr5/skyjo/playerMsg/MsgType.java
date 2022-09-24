
package be.he2b.atlr5.skyjo.playerMsg;

import java.io.Serializable;

/**
 *
 * @author justu
 */
public enum MsgType implements Serializable{
    /**
     * the type of message that is send when the admin of the game click on
     * start game button in the view
     */
    CLICK_START,
    /**
     * when the player choose to make a discard action.
     */
    CLICK_DISCARD,
    /**
     * when the player decided to make a pitched action.
     */
    CLICK_PITCHED,
    /**
     * When the client send his mail to the server to create a user
     */
    UPDATE_NAME,
    /**
     * ask a card to the server
     */
    ASK_CARD,
    
    SEND_COMMENT,
    /**
     * ask a information about a player
     */
    ASK_PLAYER_INFO;
    
}
