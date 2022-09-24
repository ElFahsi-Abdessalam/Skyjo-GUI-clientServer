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
 * The type Local label.
 */
public class LocalLabel extends GridPane {
    private final List<CardLabel> labelList;

    /**
     * Instantiates a new Local label.
     *
     * @param player the player
     */
    public LocalLabel(Player player) {
        Objects.requireNonNull(player, LeftPane.class.getName()
                + " " + Player.class.getName() + "must not be null");
        labelList = new ArrayList<>();
        createLocalPane(player);

    }

    /**
     * create local pane
     *
     * @param player object given as argument
     */
    private void createLocalPane(Player player) {
        super.setVgap(40);
        super.setHgap(15);
        var index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {

                int finalIndex = index;
                var cardLabel = new CardLabel(player.getCardAt(finalIndex));
                var imageView = GeneralMethod.imageView("/data/hiddenCard.png", 77, 118);
                if (player.getCardAt(finalIndex).isHide()) {
                    cardLabel.setGraphic(imageView);
                } else {
                    revealCard(cardLabel);
                }
                labelList.add(cardLabel);
                super.add(cardLabel, i, j);
                index++;
            }

        }

    }

    /**
     * Reveal cards.
     *
     * @param player the player
     */
    public void revealCards(Player player) {
        var cards = player.getHand();
        updateButton(cards);
        labelList.forEach(cardLabel -> {
            if (!cardLabel.getCard().isHide()) {
                revealCard(cardLabel);
            } else {
                var imageView = GeneralMethod.
                        imageView("/data/hiddenCard.png", 77, 118);
                cardLabel.setGraphic(imageView);
            }

        });

    }

    /**
     * update all the card button
     *
     * @param cards element player cards
     */
    private void updateButton(List<Card> cards) {
        for (int i = 0; i < labelList.size(); i++) {
            labelList.get(i).setCard(cards.get(i));

        }
    }

    /**
     * redefined an accurate label for the card
     *
     * @param cardLabel card
     */
    private void revealCard(CardLabel cardLabel) {
        var accurateLabel = new Label();
        accurateLabel.setMinSize(77, 118);
        accurateLabel.setMaxSize(77, 118);
        accurateLabel.setText("" + cardLabel.getCard().getValues().getValue());
        accurateLabel.setFont(Font.font("System", FontWeight.BOLD, 20));
        accurateLabel.setAlignment(Pos.CENTER);
        accurateLabel.setStyle("-fx-background-color: beige;");
        cardLabel.setGraphic(accurateLabel);
        cardLabel.setMinSize(77, 118);
        cardLabel.setMaxSize(77, 118);

    }
}
