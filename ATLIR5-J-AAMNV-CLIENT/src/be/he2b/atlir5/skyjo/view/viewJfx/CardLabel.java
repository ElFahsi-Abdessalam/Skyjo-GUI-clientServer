package be.he2b.atlir5.skyjo.view.viewJfx;

import javafx.scene.control.Label;
import be.he2b.atlr5.skyjo.model.*;

/**
 * The type Card label.
 */
public class
CardLabel extends Label {
    private Card card;

    /**
     * Instantiates a new Card label.
     *
     * @param card the card
     */
    public CardLabel(Card card) {
        this.card = card;
    }

    /**
     * Gets card.
     *
     * @return the card
     */
    public Card getCard() {
        return card;
    }

    /**
     * Sets card.
     *
     * @param card the card
     */
    public void setCard(Card card) {
        this.card = card;
    }
}
