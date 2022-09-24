package be.he2b.atlir5.skyjo.view.viewJfx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import be.he2b.atlr5.skyjo.model.*;
import be.he2b.atlr5.skyjo.playerPOrder.User;

import java.util.Objects;
import javafx.scene.control.Button;

/**
 * The type Skyjo v box.
 */
public class SkyjoVBox extends VBox {

    private Facade game;
    private final Label labelAdvice;
    private Pane localPane;

    public void setGame(Facade game) {
        this.game = game;
    }
    /**
     * Instantiates a new Skyjo v box.
     *
     * @param game the game
     */
    public SkyjoVBox(Facade game) {
        this.game = Objects.requireNonNull(game, "Game must not be null");
        labelAdvice = new Label();

    }

    public void setPane(Pane pane) {
        super.getChildren().setAll(pane);
    }

    /**
     * Create vbox.
     */
    public void createVbox() {
        super.setMaxSize(1000, 700);
        super.setMinSize(1000, 700);
        super.setSpacing(10);
        super.setPadding(new Insets(10));
        super.setStyle("-fx-background-color: #87CEEB;");
        labelAdvice.setAlignment(Pos.CENTER);
        labelAdvice.setMaxSize(1200, 20);
        labelAdvice.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
        super.getChildren().add(labelAdvice);

    }

    /**
     *
     * @param signal
     * @param client
     */
    public void setLabelMsg(int signal, User client) {
        labelAdvice.setStyle("-fx-background-color: lightgreen;-fx-text-fill: "
                + "black;-fx-padding:10;fx-spaccing:10;-fx-background-radius:5.0;");
        if (client.getID() == game.getCurrentPlayer().getPlayerID()) {
            labelAdvice.setText(getMsgForCurrentPlayer(signal));
        } else {
            var msg = getMsgForOtherPlayer(signal);
            if (msg != null) {
                labelAdvice.setText(getMsgForOtherPlayer(signal));
            }
        }

    }

    /**
     *
     * @param signal
     * @return
     */
    private String getMsgForCurrentPlayer(int signal) {
        switch (signal) {
            case 0:
                return "Player " + game.getCurrentPlayer().getName()
                        + " Time for you to play a round".toUpperCase();
            case 1:
                return "You choose to pick a card from the discard "
                        + "select a card from your grid for exchange".toUpperCase();
            case 2:
                return "You choose to pick from the deck choose your action below...".toUpperCase();
            case -1:
                return "You did not select an action for chosen a card please select an action ".toUpperCase();
            case -2:
                return "Something went wrong retry...".toUpperCase();
            case -3:
                return "You have not choose a card from your grid".toUpperCase();
            case 3:
                return "You choose to keep the card from the pitched select a card from your grid".toUpperCase();
            case 4:
                return "You choose to drop the card choose a card from your grid for exchange".toUpperCase();
            case 5:
                return "Comment send to server and to all users ...".toUpperCase();
        }
        return null;

    }

    private String getMsgForOtherPlayer(int signal) {
        var name = game.getCurrentPlayer().getName();
        switch (signal) {
            case 0:
                return "Player " + name + " is playing a round".toUpperCase();

        }
        return null;
    }
}
