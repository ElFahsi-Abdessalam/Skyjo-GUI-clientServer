package be.he2b.atlir5.skyjo.clientCo;

import be.he2b.atlr5.skyjo.playerMsg.*;
import be.he2b.atlr5.skyjo.serverMsg.*;
import be.he2b.atlir5.skyjo.controller.Controller;
import be.he2b.atlr5.skyjo.model.*;
import be.he2b.atlr5.skyjo.playerPOrder.User;
import be.he2b.atlr5.skyjo.playerPOrder.UserType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientConnexion extends AbstractClient {
    private User user;
    private final List<UserMsg> msgList;
    private final Controller controller;

    /**
     * Basic getter for user Attributes
     *
     * @return user Object
     */
    public User getUser() {
        return user;
    }

    /**
     * Constructs the client.
     *
     * @param host       the server's host name.
     * @param port       the port number.
     * @param controller the main controller for the client
     */
    public ClientConnexion(String host, int port, Controller controller) {
        super(host, port);
        this.msgList = new ArrayList<>();
        this.controller = controller;
        fieldMsgList();
    }

    /**
     * Basic getter for the controller
     *
     * @return controller Object
     */
    public Controller getController() {
        return controller;
    }

    /**
     * Handler for message for the client override from the main class AbstractClient
     *
     * @param msg the message sent by the server.
     */
    @Override
    protected void handleMessageFromServer(Object msg) {
        var srvMsg = (ServerMsg) msg;
        setChanged();
        notifyObservers(srvMsg);

    }

    /**
     * setter for user cause in the beginning
     * is the server that created a user and
     * send the detail to the client
     *
     * @param user object send by the server
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * send the mail given by the player to the server for him to register and create a user
     *
     * @param mail for the player
     */
    public void sendNameToServer(String mail) {
        var msg = getCorrectMsg(MsgType.UPDATE_NAME);
        msg.setMsg(mail);
        send(msg);

    }

    /**
     * By default, all the message send by player must be created and instantiate with the 'new' token, but it makes
     * redundant and too much instance for nothing so this method create all the message need by the player and keep it
     * stored in a list (ArrayList).
     */
    private void fieldMsgList() {
        Arrays.stream(MsgType.values()).forEach(x -> msgList.add(new UserMsg(x)));

    }

    /**
     * return true if the current player is the admin of the game false if not
     *
     * @return Boolean true /false
     */
    public boolean isAdmin() {
        return user.getType() == UserType.ADMIN;
    }

    /**
     * Basic getter for the player name
     *
     * @return name (String)
     */
    public String getMail() {
        return user.getName();
    }

    /**
     * Determined the adequate msg that need to be
     * sent to client using the server type value
     * given as argument cause each msg protocol has
     * a type, and we can determine the type
     * of msg that need to be sent
     *
     * @param type that determine which msg that need to be sent
     * @return a UserMsg Object that will be sent to the server
     */
    private UserMsg getCorrectMsg(MsgType type) {
        var serverMsg = msgList.stream().filter(x -> x.getType().equals(type)).toList();
        return serverMsg.get(0);
    }

    /**
     * Send a msg to the server to ask him to creat the game
     */
    public void createGame() {
        var serverMsg = getCorrectMsg(MsgType.CLICK_START);
        send(serverMsg);
    }

    /**
     * Send a msg to the server that the client choose the discard option
     *
     * @param cardChoose the card Object that the client choose
     */
    public void sendDiscardMessage(Card cardChoose) {
        var serverMsg = getCorrectMsg(MsgType.CLICK_DISCARD);
        serverMsg.setMsg(cardChoose);
        send(serverMsg);

    }

    /**
     * Send to the server a msg that ask a card from the game
     */
    public void sendAskCard() {
        send(getCorrectMsg(MsgType.ASK_CARD));
    }

    /**
     * method that send the msg to the server
     *
     * @param msg
     */
    private void send(UserMsg msg) {
        try {
            sendToServer(msg);
        } catch (IOException e) {
            this.controller.showError(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

   
    /**
     * Send the msg to the server that the player choose the pitched option
     *
     * @param card         chosen by the player
     * @param pickedOption the option the player choose (keep or drop)
     */
    public void sendPitchAction(Card card, int pickedOption) {
        var msg = getCorrectMsg(MsgType.CLICK_PITCHED);
        msg.setPlayerPickOption(pickedOption);
        msg.setMsg(card);
        send(msg);
    }

    public void askServerPlayerState(String name) {
        var serverMsg = getCorrectMsg(MsgType.ASK_PLAYER_INFO);
        serverMsg.setMsg(name);
        send(serverMsg);

    }

    public void sendCom(String comment) {
        var msg = getCorrectMsg(MsgType.SEND_COMMENT);
        msg.setMsg(comment);
        send(msg);
    }
}
