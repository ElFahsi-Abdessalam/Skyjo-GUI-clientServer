/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.he2b.atlir5.skyjo.controller;

import be.he2b.atlir5.skyjo.clientCo.ClientConnexion;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import be.he2b.atlr5.skyjo.model.*;
import be.he2b.atlir5.skyjo.view.viewJfx.SkyjoView;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The type Controller.
 *
 * @author
 */
public class Controller {

    private Facade game;
    private SkyjoView view;
    private final ClientConnexion client;

    /**
     * Controller constructor
     */
    public Controller() {
        client = new ClientConnexion("localhost", 2021, this);
        view = null;
    }

    /**
     * launch the game
     *
     * @param primaryStage the primary stage
     */
    public void start(Stage primaryStage) {
        primaryStage.setTitle("SKYJO");
        try {
            client.openConnection();
            if (client.isConnected()) {
                this.view = new SkyjoView(this, primaryStage);
                this.client.addObserver(this.view);
            }
        } catch (IOException e) {
            var label = errorWindows(e.getMessage());
            var scene = new Scene(label, 500, 150);
            primaryStage.setScene(scene);
        }
        primaryStage.show();
    }

    /**
     * Gets game.
     *
     * @return the game
     */
    public Facade getGame() {
        return game;
    }

    private Label errorWindows(String e) {
        var label = new Label(e + " Server Not Online...");
        label.setStyle("-fx-text-fill:red;");
        label.setAlignment(Pos.CENTER);
        return label;

    }

    /**
     * Gets view.
     *
     * @return the view
     */
    public SkyjoView getView() {
        return view;
    }

    /**
     * Ask the client to send the name to the server(UPDATE_NAME)
     *
     * @param playerName name of the player
     */
    public void setUserName(String playerName) {
        this.client.sendNameToServer(playerName);
    }

    /**
     * Ask the client to send to the server to create the game (CLICK_START)
     */
    public void createTheGame() {
        this.client.createGame();
    }

    /**
     * return the username
     *
     * @return string player name
     */
    public String getUserMail() {
        return client.getMail();
    }

    /**
     * Ask the client to send a CLICK_DISCARD
     *
     * @param card choose by the player
     */
    public void sendClickDiscard(Card card) {
        this.client.sendDiscardMessage(card);
    }

    /**
     * Ask the client to send a ASK_CARD
     */
    public void sendClickPitchedAskForCard() {
        this.client.sendAskCard();
    }

    /**
     * Ask the client to send a CLICK_PITCHED
     *
     * @param card choose by the player
     * @param pickOption the option choose (keep card or drop card)
     */
    public void sendPitchAction(Card card, int pickOption) {
        this.client.sendPitchAction(card, pickOption);
    }

    /**
     * Send the name given by the player to the server
     *
     * @param name of the player
     */
    public void sendGetThisPlayerGameInfo(String name) {
        client.askServerPlayerState(name);
    }

    /**
     * close the socket connection
     */
    public void closeConnexion() {
        try {
            this.client.closeConnection();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * @return
     */
    public ClientConnexion getClient() {
        return client;
    }

    /**
     *
     * @param errorMsg
     */
    public void showError(String errorMsg) {
        this.view.getStage().getScene().setRoot(errorWindows(errorMsg));
    }

    public void sendCom(String comment) {
        this.client.sendCom(comment);
        this.view.closeCommentWindows(this.client.getUser());
    }
}
