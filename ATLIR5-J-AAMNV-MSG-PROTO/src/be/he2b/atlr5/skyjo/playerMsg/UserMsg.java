/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.he2b.atlr5.skyjo.playerMsg;

import be.he2b.atlr5.skyjo.msgProto.Message;

/**
 *
 * @author justu
 */
public class UserMsg implements Message{

   private MsgType msgType;
    private Object msg;
    private int player;
    private int playerPickOption;

    /**
     * Constructor of userMsg Object
     *
     * @param msgType the type of message that will be contain in this UserMsg
     */
    public UserMsg(MsgType msgType) {
        this(msgType, null, 0);
    }

    /**
     * Overload constructor
     *
     * @param msgType the type of message
     * @param msg the message Object
     * @param player the player ID sending the message ( not surely use)
     */
    public UserMsg(MsgType msgType, Object msg, int player) {
        this.msgType = msgType;
        this.msg = msg;
        this.player = player;
    }

    /**
     * getter of pickOption the player pick Option the value exist only if the player choose the
     * pitch action -1 to drop the card 1 to keep the card
     *
     * @return a value pick option 
     */
    public int getPlayerPickOption() {
        return playerPickOption;
    }

    /**
     * Setter the pickOption
     * @param playerPickOption
     */
    public void setPlayerPickOption(int playerPickOption) {
        this.playerPickOption = playerPickOption;
    }

    /**
     * Set the Object message to be send
     * @param msg new message to be send
     */
    public void setMsg(Object msg) {
        this.msg = msg;
    }

    /**
     * return the type of the message 
     * @return MsgType
     */
    @Override
    public Object getType() {
        return msgType;
    }

    /**
     * return the contents Object Message
     * @return message
     */
    @Override
    public Object getContent() {
        return msg;
    }

    /**
     * get the the ID of the layer ( not surely use)
     * @return int 
     */
    public int getPlayer() {
        return player;
    }
}
