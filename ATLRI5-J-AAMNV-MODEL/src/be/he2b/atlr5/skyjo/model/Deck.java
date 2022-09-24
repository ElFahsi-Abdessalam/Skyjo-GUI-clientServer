package be.he2b.atlr5.skyjo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

/**
 * The type Deck.
 */
public class Deck implements Serializable {
    private final List<Card> pitched;
    private final Stack<Card> discard;

    /**
     * Instantiates a new Deck.
     */
    public Deck() {
        pitched = new ArrayList<>();
        Value[] values = Value.values();
        IntStream.range(0, values.length).
                forEachOrdered(i -> IntStream.range(0, 10).
                        forEach(j -> pitched.add(new Card(values[i],j))));
        discard = new Stack<>();
    }

    public void addToPitched(Card card) {
        pitched.add(card);
    }

    /**
     * Gets card list.
     *
     * @return the card list
     */
    public List<Card> getPitched() {
        return pitched;
    }

    /**
     * Shuffle.
     */
    public void shuffle() {
        Collections.shuffle(pitched);
    }

    /**
     * Is empty boolean.
     *
     * @return the boolean
     */
    public boolean isEmpty() {
        return pitched.isEmpty();
    }

    /**
     * Hit card.
     *
     * @return the card
     */
    public Card hit() {
        return getCard();
    }

    private Card getCard() {
        if (pitched.isEmpty()) throw new IllegalStateException("Card List Empty");
        return pitched.remove(0);
    }

    /**
     * Hit from discard card.
     *
     * @return the card
     */
    public Card hitFromDiscard() {
        if (discard.isEmpty()) throw new IllegalStateException("Discard List Empty");
        return discard.pop();
    }

    public Card popFromDiscard() {
        return discard.pop();
    }

    /**
     * Size int.
     *
     * @return the int
     */
    public int size() {
        return pitched.size();
    }

    /**
     * Gets discard.
     *
     * @return the discard
     */
    public List<Card> getDiscard() {
        return discard;
    }

    /**
     * Add to discard.
     *
     * @param card the card
     */
    public void addToDiscard(Card card) {
        this.discard.add(card);
    }

    /**
     * Gets fist in.
     *
     * @return the fist in
     */
    public Card getFistIn() {
        return discard.peek();
    }

    /**
     * Remove card from pitched.
     *
     * @param card the card
     */
    public void removeCardFromPitched(Card card) {
        this.pitched.remove(card);
    }


    /**
     * Return 12 card card [ ].
     *
     * @return the card [ ]
     */
    public Card[] return12Card() {
        var cards = new Card[12];
        for (int i = 0; i < cards.length; i++) {
            cards[i] = pitched.remove(i);
        }
        return cards;
    }

    /**
     * Switch two card.
     *
     * @param card_hit      the card hit
     * @param card_selected the card selected
     */
    public void switchTwoCard(Card card_hit, Card card_selected) {
        var temp = new Card(card_hit);
        card_hit.setCartState(card_selected.getValues(), card_selected.isHide());

        card_selected.setCartState(temp.getValues(), temp.isHide());
    }

    public int nbCard() {
        return pitched.size();
    }
}
