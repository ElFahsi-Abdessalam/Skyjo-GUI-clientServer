package be.he2b.atlir5.skyjo.view.buttonHandlers;

import be.he2b.atlir5.skyjo.view.viewJfx.SkyjoView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import be.he2b.atlir5.skyjo.controller.Controller;
import be.he2b.atlr5.skyjo.model.*;

/**
 * The handler for all the button that represent each card on the board.
 */
public class HandlerCardB implements EventHandler<MouseEvent> {

    private final SkyjoView view;
    private final Controller controller;

    /**
     * Override handle function from the EventHandler
     *
     * @param event that happen on the buttons
     */
    @Override
    public void handle(MouseEvent event) {
        if (view.isClickDiscard()) {
            discardAction();
        } else if (view.isClickPick()) {
            pitchedAction2();
        } else {
            view.setLabelAdviceMessage(-1,controller.getClient().getUser());

        }

    }

    /**
     * Constructor for the cards Handlers
     *
     * @param view for the player playing
     * @param controller that handle the player game
     */
    public HandlerCardB(SkyjoView view, Controller controller) {
        this.controller = controller;
        this.view = view;
        
       
    }

    /**
     * Send to the server to apply the discard action
     */
    private void discardAction() {
        view.setClickDiscard(false);
        this.controller.sendClickDiscard((Card) view.getCardClick());
    }

    /**
     * Add event to cards.
     */
    public void addEventToCards() {
        var cards = view.getListCard();
        cards.forEach(x -> x.addEventHandler(MouseEvent.MOUSE_CLICKED, this));
    }

    /**
     * Send to the server to apply the pitched action
     */
    private void pitchedAction2() {
        view.hidePopUp();
        view.setClickPick(false);
        this.controller.sendPitchAction(view.getCardClick(), view.getPickOption());
    }

}
