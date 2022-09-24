package be.he2b.atlir5.skyjo.view.viewJfx;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import be.he2b.atlr5.skyjo.model.*;

import java.util.List;

/**
 * The type Player grid pane.
 */
public class PlayerVBox extends VBox {

    private final LeftPane gridPane;
    private final Label upperLabel;
    private final EventHandler<MouseEvent> eventHandler;

    /**
     * Instantiates a new Player grid pane.
     *
     * @param player the player
     */
    public PlayerVBox(Player player) {
        this.gridPane = new LeftPane(player);
        this.upperLabel = new Label();
        eventHandler = Event::consume;
    }

    /**
     * add an even handler on the gridpane that consume any mouse event
     */
    public void consumeMouseEvent() {
        this.gridPane.addEventFilter(MouseEvent.ANY, eventHandler);
    }

    /**
     * Remove the consume on any mouse event
     */
    public void removeMouseConsumeEvent() {
        this.gridPane.removeEventFilter(MouseEvent.ANY, eventHandler);
    }

    /**
     * Create Gridpane.
     *
     * @param player the player
     */
    public void createPane(Player player) {
        super.setMaxSize(450, 450);
        super.setMinSize(450, 450);
        super.setPadding(new Insets(10));
        super.setSpacing(20);
        upperLabel.setMaxSize(430, 30);
        upperLabel.setText("Player: " + player.getName() + "  " + "Score: " + player.getScore());
        upperLabel.setAlignment(Pos.CENTER);
        upperLabel.setStyle("-fx-background-color: burlywood;-fx-padding:10;fx-spaccing:20;-fx-background-radius:10.0;");
        super.getChildren().add(upperLabel);
        super.getChildren().add(gridPane);

    }

    /**
     * Reveal cards.
     *
     * @param player the player
     */
    public void revealCards(Player player) {
        gridPane.revealCards(player);

    }

    /**
     * Sets score.
     *
     * @param player the player
     */
    public void setScore(Player player) {
        upperLabel.setText("Player: " + player.getName() + "  " + "Score: " + player.getScore());
    }

    /**
     * Gets click.
     *
     * @return the click
     */
    public CardButton getClick() {
        return gridPane.getButton();
    }

    /**
     * Gets list button.
     *
     * @return the list button
     */
    public List<CardButton> getListButton() {
        return gridPane.getCardsButton();
    }

}
