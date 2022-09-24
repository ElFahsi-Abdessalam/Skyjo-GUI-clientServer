package be.he2b.atlir5.skyjo.view.viewJfx;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import be.he2b.atlr5.skyjo.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Middle pane.
 */
public class MiddleVBox extends VBox {

    private final Deck deck;
    private final List<Node> nodeList;
    private final GridPane popUp;
    private int pickOption;
    private final Button keepB;
    private final Button notKeepB;
    private final Label cardLabel;
    private final EventHandler<MouseEvent> eventHandler;
    private final EventHandler<MouseEvent> pickEventHandler;
    private final EventHandler<MouseEvent> discardEventHandler;

    public void consumeMySelf() {
        this.addEventFilter(MouseEvent.ANY, eventHandler);
    }

    /**
     * Gets pick option.
     *
     * @return the pick option
     */
    public int getPickOption() {
        return pickOption;
    }

    /**
     * Sets pick option.
     *
     * @param pickOption the pick option
     */
    public void setPickOption(int pickOption) {
        this.pickOption = pickOption;
    }

    /**
     * Instantiates a new Middle pane.
     *
     * @param deck the deck
     */
    public MiddleVBox(Deck deck) {
        this.deck = deck;
        nodeList = new ArrayList<>();
        popUp = new GridPane();
        pickOption = 0;
        keepB = new Button("Keep");
        notKeepB = new Button("Drop");
        cardLabel = new Label();
        eventHandler = Event::consume;
        this.pickEventHandler = Event::consume;
        this.discardEventHandler = Event::consume;
    }

    public void consumeDiscardButton() {
        getDiscardButton().addEventFilter(MouseEvent.ANY, discardEventHandler);
    }

    public void removeDiscardConsume() {
        getDiscardButton().removeEventFilter(MouseEvent.ANY, discardEventHandler);

    }

    public void consumePickButton() {
        getPickButton().addEventFilter(MouseEvent.ANY, pickEventHandler);
    }

    public void removePickConsume() {
        getPickButton().removeEventFilter(MouseEvent.ANY, pickEventHandler);

    }

    /**
     * Create Gridpane.
     */
    public void createPane() {
        super.setMaxSize(300, 300);
        super.setMinSize(300, 300);
        super.setPadding(new Insets(10));
        super.setSpacing(20);
        var gridPane = new GridPane();
        gridPane.setHgap(40);
        gridPane.setVgap(33);
        definedPaneFirstProperty(gridPane);
        super.getChildren().add(gridPane);

    }

    /**
     * @param gridPane
     */
    private void definedPaneFirstProperty(GridPane gridPane) {
        createUpperLabel();
        createPickButton(gridPane);
        createHiddenCardLabel(gridPane);
        createDiscardButton(gridPane);
        createDiscardLabel(gridPane);

    }

    /**
     * Sets discard label.
     *
     * @param game
     */
    public void setDiscardLabel(Game game) {
        var discardLabel = (Label) nodeList.get(nodeList.size() - 1);
        discardLabel.setMaxSize(77, 118);
        discardLabel.setMinSize(77, 118);
        discardLabel.setText("" + game.getDeck().getFistIn().getValues().getValue());
        discardLabel.setFont(Font.font("System", FontWeight.BOLD, 20));
        discardLabel.setAlignment(Pos.CENTER);
        discardLabel.setStyle("-fx-background-color: beige;");
    }

    /**
     * Gets pick button.
     *
     * @return the pick button
     */
    public Button getPickButton() {
        return (Button) nodeList.get(1);
    }

    /**
     * Gets discard button.
     *
     * @return the discard button
     */
    public Button getDiscardButton() {
        return (Button) nodeList.get(2);
    }

    /**
     * Add show card label.
     *
     * @param card the card
     */
    public void addShowCardLabel(Card card) {
        if (popUp.isVisible()) {
            popUp.setVgap(10);
            popUp.setHgap(20);
            definedPopUpProperty(card);
            super.getChildren().add(popUp);
        } else {
            cardLabel.setText("" + card.getValues().getValue());
            popUp.setVisible(true);
        }
    }

    /**
     * Gets keep b.
     *
     * @return the keep b
     */
    public Button getKeepB() {
        return keepB;
    }

    /**
     * Gets no keep b.
     *
     * @return the no keep b
     */
    public Button getNoKeepB() {
        return notKeepB;
    }

    /**
     * Hide pop up.
     */
    public void hidePopUp() {
        popUp.setVisible(false);
    }

    /**
     * Create the upper label which contain the text "DECK"
     */
    private void createUpperLabel() {
        var upperLabel = new Label();
        upperLabel.setMaxSize(350, 30);
        upperLabel.setText("DECK MENU");
        upperLabel.setAlignment(Pos.CENTER);
        upperLabel.setStyle("-fx-background-color: burlywood;"
                + "-fx-padding:10;fx-spaccing:5;"
                + "-fx-background-radius:10.0;");
        super.getChildren().add(upperLabel);
        nodeList.add(upperLabel);//0
        upperLabel.setAlignment(Pos.CENTER);

    }

    /**
     * create the pitched button
     *
     * @param gridPane the pane that add the pitch button
     */
    private void createPickButton(GridPane gridPane) {
        var pickButton = new Button();
        pickButton.setMaxSize(77, 120);
        pickButton.setMinSize(77, 120);
        var imageView = GeneralMethod.
                imageView("/data/pickHand.png", 77, 118);
        pickButton.setGraphic(imageView);
        nodeList.add(pickButton); //1

        gridPane.add(pickButton, 0, 0);

    }

    /**
     * create the hidden card label that show that theirs card hide
     *
     * @param gridPane
     */
    private void createHiddenCardLabel(GridPane gridPane) {
        var hiddenCard = new Label();
        hiddenCard.setMaxSize(78, 118);
        hiddenCard.setMinSize(78, 118);
        var backgroundLabel = GeneralMethod.
                imageView("/data/hiddenCard.png", 77, 118);
        hiddenCard.setGraphic(backgroundLabel);

        gridPane.add(hiddenCard, 3, 0);

    }

    /**
     * create the discard label that show the card value
     *
     * @param gridPane
     */
    private void createDiscardLabel(GridPane gridPane) {
        var discardLabel = new Label();
        discardLabel.setMaxSize(77, 118);
        discardLabel.setMinSize(77, 118);
        discardLabel.setText("" + deck.getDiscard().get(0).getValues().getValue());
        discardLabel.setFont(Font.font("System", FontWeight.BOLD, 20));
        discardLabel.setAlignment(Pos.CENTER);
        discardLabel.setStyle("-fx-background-color: beige;");
        nodeList.add(discardLabel);//3
        gridPane.add(discardLabel, 3, 1);
    }

    /**
     * create the discard button
     *
     * @param gridPane layout that add the discard button
     */
    private void createDiscardButton(GridPane gridPane) {
        var discardButton = new Button();
        discardButton.setMaxSize(77, 115);
        discardButton.setMinSize(77, 115);
        var discardButtonBg = GeneralMethod.
                imageView("/data/drop.png", 60, 115);
        discardButton.setGraphic(discardButtonBg);
        nodeList.add(discardButton);//2
        gridPane.add(discardButton, 0, 1);
    }

    /**
     * create the popUp label that describe each button
     *
     * @param card the card that need to be show
     */
    private void createPopUpLabelCard(Card card) {
        var label = new Label("Card");
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-background-color: burlywood;"
                + "-fx-padding:5;fx-spaccing:5;"
                + "-fx-background-radius:10.0;");
        label.setMaxSize(90, 30);
        popUp.add(label, 0, 0);
        cardLabel.setMaxSize(77, 108);
        cardLabel.setMinSize(77, 108);
        cardLabel.setText("" + card.getValues().getValue());
        cardLabel.setFont(Font.font("System", FontWeight.BOLD, 20));
        cardLabel.setAlignment(Pos.CENTER);
        cardLabel.setStyle("-fx-background-color: beige;");
        popUp.add(cardLabel, 0, 1);
    }

    /**
     * create keep and not keep label that contain a message that describe each
     * button below
     */
    private void createKeepAndNotKeepLabel() {
        var keepLabel = new Label("Button");
        keepLabel.setAlignment(Pos.CENTER);
        keepLabel.setStyle("-fx-background-color:"
                + " burlywood;-fx-padding:5;"
                + "fx-spaccing:5;-fx-background-radius:10.0;");
        keepLabel.setMaxSize(90, 30);
        popUp.add(keepLabel, 2, 0);

        var notKeepLabel = new Label("Button");
        notKeepLabel.setAlignment(Pos.CENTER);
        notKeepLabel.setStyle("-fx-background-color: burlywood;"
                + "-fx-padding:5;fx-spaccing:5;-fx-background-radius:10.0;");
        notKeepLabel.setMaxSize(90, 30);
        popUp.add(notKeepLabel, 1, 0);
    }

    /**
     * it assemble all the element for the popUp to be well done and fixed up
     * and show
     *
     * @param card the card value that need to be show
     */
    private void definedPopUpProperty(Card card) {
        createPopUpLabelCard(card);
        createKeepAndNotKeepLabel();
        notKeepB.setAlignment(Pos.CENTER);
        notKeepB.setStyle("-fx-background-color: #E30B5C;"
                + "-fx-padding:5;fx-spaccing:5;"
                + "-fx-background-radius:10.0;");
        notKeepB.setMaxSize(77, 100);
        notKeepB.setMinSize(77, 100);
        popUp.add(notKeepB, 1, 1);
        keepB.setAlignment(Pos.CENTER);
        keepB.setStyle("-fx-background-color: #DFFF00;"
                + "-fx-padding:5;fx-spaccing:5;"
                + "-fx-background-radius:10.0;");
        keepB.setMaxSize(77, 100);
        keepB.setMinSize(77, 100);
        popUp.add(keepB, 2, 1);
    }

    /**
     * Remove event filter for for mouse event
     */
    public void removeMouseConsumeEvent() {
        this.removeEventFilter(MouseEvent.ANY, eventHandler);
    }
}
