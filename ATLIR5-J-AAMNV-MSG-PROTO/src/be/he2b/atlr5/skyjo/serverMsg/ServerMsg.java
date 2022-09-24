/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.he2b.atlr5.skyjo.serverMsg;

import be.he2b.atlr5.skyjo.msgProto.Message;

/**
 *
 * @author justu
 */
public class ServerMsg implements Message{
     private ServerMsgType type;
    private Object msg;

    /**
     * Constructor of ServerType
     * @param type server message type
     */
    public ServerMsg(ServerMsgType type) {
        this.type = type;
    }

    /**
     * Overload constructor
     * @param type server message type
     * @param msg the object message
     */
    public ServerMsg(ServerMsgType type, Object msg) {
        this.type = type;
        this.msg = msg;
    }

    /**
     * setter of message
     * @param msg new message Object 
     */
    public void setMsg(Object msg) {
        this.msg = msg;
    }

    /**
     * getter of server Type
     * @return server type
     */
    @Override
    public ServerMsgType getType() {
        return type;
    }

    /**
     * getter of Object message
     * @return Object message
     */
    public Object getMsg() {
        return msg;
    }

    /**
     *Override Object content 
     * @return Object 
     */
    @Override
    public Object getContent() {
        return this.msg;
    }
    
}
