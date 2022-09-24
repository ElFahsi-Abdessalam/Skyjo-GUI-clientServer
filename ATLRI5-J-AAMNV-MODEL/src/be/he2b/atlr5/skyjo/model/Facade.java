package be.he2b.atlr5.skyjo.model;

import be.he2b.atlr5.skyjo.serverMsg.ServerMsgType;
import java.io.Serializable;
import java.util.*;

/**
 * The type Facade.
 */
public abstract class Facade extends Observable implements Serializable {

    List<Observer> observer = new ArrayList<>();

    /**
     * Gets deck.
     *
     * @return the deck
     */
    public abstract Deck getDeck();

    /**
     * return a player using his mail
     *
     * @param mail of the player
     * @return a player
     */
    public abstract Player getPlayerWithName(String mail);

    /**
     *
     * @param players
     */
    public abstract void addNewPlayers(List<Player> players);

    /**
     *
     */
    public abstract void updatePlayers();

    /**
     * setter of cardOut
     *
     * @param cardOut
     */
    public abstract void setCardOut(Card cardOut);

    /**
     * setter for the card chosen by the player
     *
     * @param card choose
     */
    public abstract void setCardCurrentPlayer(Card card);

    /**
     *
     * @param ID
     */
    public abstract void removePlayer(int ID);

    /**
     * update the game using the player choice
     *
     * @param choice
     */
    public abstract void updateGame(PlayerChoice choice);

    /**
     * @param player
     * @throws IllegalStateException
     */
    public abstract void addNewPlayer(Player player) throws IllegalStateException;

    /**
     * get the serverMsg type
     *
     * @return
     */
    public abstract ServerMsgType getType();

    /**
     * get the list of all players
     *
     * @return
     */
    public abstract List<Player> getPlayers();

    /**
     * Gets current player.
     *
     * @return the current player
     */
    public abstract Player getCurrentPlayer();

    /**
     * Gets opponent player.
     *
     * @return the opponent player
     */
    public abstract Player getOpponentPlayer();

    /**
     * Switch player.
     */
    public abstract void switchPlayer();

    /**
     * Update score.
     */
    public abstract void updateScore();

    /**
     * Hit card.
     *
     * @return the card
     */
    public abstract Card hit();

    /**
     * Gets peek.
     *
     * @return the peek
     */
    public abstract Card getPeek();

    /**
     * Decide who start.
     */
    public abstract void decideWhoStart();

    /**
     * Reveal card.
     */
    public abstract void revealCard();

    /**
     * Is over boolean.
     *
     * @return the boolean
     */
    public abstract boolean isOver();

    /**
     * Gets winner.
     *
     * @return the winner
     */
    public abstract Player getWinner();

    public abstract void updateDiscardOnly();
}
