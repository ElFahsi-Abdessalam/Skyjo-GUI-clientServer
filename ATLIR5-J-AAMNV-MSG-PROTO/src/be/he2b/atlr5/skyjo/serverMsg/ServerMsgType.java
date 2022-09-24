/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package be.he2b.atlr5.skyjo.serverMsg;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author justu
 */
public enum ServerMsgType implements Serializable {
      /**
     * Send this type when the game is created by the server
     */
    GAME_CREATED,
    /**
     * Send this type of message when a even is made on the game (discard rule
     * or pitched rule)
     */
    GAME_UPDATE,
    /**
     * Send this type when the game is over
     */
    GAME_FINISHED,
    /**
     * Send to the ADMIN player when a minimum of player are online to start the
     * game
     */
    ALL_PLAYER_ONLINE,
    /**
     * Default type send when the server create a new user ( new player ) when a
     * player connected
     */
    USER_CREATED,
    /**
     * Type of message to be send when a user is created from the server to tell
     * the ADMIn player to start the game
     */
    USER_READY,
    /**
     * Message type send to the server when the user ask for a player
     * information
     */
    PLAYER_INFO,
    /**
     * Message type when a new player is connected
     */
    NEW_PLAYER,
    /**
     * when the mail send by the user is already taken by a member
     */
    INVALID_NAME,
    /**
     * Notify the rest of the player when a new player join the game
     */
    NEW_USER,
    /**
     * When the player click on pitched the player ask for a card and the server
     * hit a card from the game and send to the player
     */
    CARD_SEND,
    /**
     *
     */
    USER_LOGOUT;
    private static Map map = new HashMap<>();

    public static ServerMsgType valueOf(int serverType) {
        return (ServerMsgType) map.get(serverType);
    }
    
}
