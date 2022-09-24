/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.he2b.atlr5.skyjo.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * The type Card.
 *
 * @author jj
 */
public class Card implements Serializable {
    private Value values;
    private boolean hide;
    private int card_id;


    /**
     * Instantiates a new Card.
     *
     * @param values the values
     * @param card_id
     */
    public Card(Value values, int card_id) {
        this.values = values;
        this.hide = true;
        this.card_id = card_id;
    }

    public Card(Card card) {
        this.values = card.values;
        this.hide = card.hide;
        this.card_id = card.card_id;
    }

    /**
     * getter for values
     *
     * @return int (Valuess)
     */
    public Value getValues() {
        return values;
    }


    /**
     * Is hide boolean.
     *
     * @return the boolean
     */
    public boolean isHide() {
        return hide;
    }


    /**
     * Sets hide.
     *
     * @param hide the hide
     */
    public void setHide(boolean hide) {
        this.hide = hide;
    }

    private void setValues(Value values) {
        this.values = values;
    }

    /**
     * Sets cart state.
     *
     * @param values the values
     * @param hide   the hide
     */
    public void setCartState(Value values, boolean hide) {
        setValues(values);
        setHide(hide);
    }

    /**
     * Equals method inherited from Object class
     *
     * @param o Mother class Object
     * @return boolean true if two card are equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return isHide() == card.isHide() && card_id == card.card_id && getValues() == card.getValues();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValues(), isHide(), card_id);
    }
}

