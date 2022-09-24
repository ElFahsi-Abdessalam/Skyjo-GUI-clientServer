package be.he2b.atlir5.skyjo.view.viewJfx;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import be.he2b.atlr5.skyjo.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Local grid pane.
 */
public class LeftPane extends GridPane {
    private final List<CardButton> cardsButton;
    private CardButton button;


    /**
     * Instantiates a new Local grid pane.
     *
     * @param player the player
     */
    public LeftPane(Player player) {
        Objects.requireNonNull(player, LeftPane.class.getName()
                + " " + Player.class.getName() + "must not be null");
        cardsButton = new ArrayList<>(12);
        createLocalPane(player);

    }

    /**
     * Gets cards button.
     *
     * @return the cards button
     */
    public List<CardButton> getCardsButton() {
        return cardsButton;
    }

    /**
     * Gets button.
     *
     * @return the button
     */
    public CardButton getButton() {
        return button;
    }

    /**
     * create a localPane for the player space
     *
     * @param player object representing the player
     */
    private void createLocalPane(Player player) {
        super.setVgap(33);
        super.setHgap(15);
        var index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                int finalIndex = index;
                var cardButton = new CardButton(player.getCardAt(finalIndex));
                var imageView = GeneralMethod.imageView("/data/hiddenCard.png", 77, 118);
                if (player.getCardAt(finalIndex).isHide()) {
                    cardButton.setGraphic(imageView);
                } else {
                    revealCard(cardButton);
                }
                cardButton.setOnAction(event -> this.button = cardButton
                );
                cardsButton.add(cardButton);
                super.add(cardButton, i, j);
                index++;
            }

        }

    }

    /**
     * Reveal cards. show all the card that are not hide anymore
     *
     * @param player the player
     */
    public void revealCards(Player player) {
        var cards = player.getHand();
        updateButton(cards);
        cardsButton.forEach(button1 -> {
            if (!button1.getCard().isHide()) {
                revealCard(button1);
            } else {
                var imageView = GeneralMethod.imageView("/data/hiddenCard.png", 77, 118);
                button1.setGraphic(imageView);

            }
        });

    }

    /**
     * Update the card into the button
     *
     * @param cards element
     */
    private void updateButton(List<Card> cards) {
        for (int i = 0; i < cardsButton.size(); i++) {
            cardsButton.get(i).setCard(cards.get(i));

        }
    }

    /**
     * reveal a card inside a button
     * defined the accurate label for showing the card
     *
     * @param cardButton element card
     */
    private void revealCard(CardButton cardButton) {
        var accurateLabel = new Label();
        accurateLabel.setMinSize(90, 130);
        accurateLabel.setMaxSize(90, 130);
        accurateLabel.setText("" + cardButton.getCard().getValues().getValue());
        accurateLabel.setFont(Font.font("System", FontWeight.BOLD, 20));
        accurateLabel.setAlignment(Pos.CENTER);
        accurateLabel.setStyle("-fx-background-color: beige;");
        cardButton.setGraphic(accurateLabel);
        cardButton.setMinSize(90, 130);
        cardButton.setMaxSize(90, 130);

    }
}
