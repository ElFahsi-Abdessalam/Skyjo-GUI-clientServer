package be.he2b.atlir5.skyjo.view.buttonHandlers;

import be.he2b.atlir5.skyjo.controller.Controller;
import be.he2b.atlir5.skyjo.view.viewJfx.SkyjoView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * The Handler for the discard button.
 */
public class HandlerDiscardB implements EventHandler<MouseEvent> {

    private final SkyjoView view;
    private final Controller controller;
    
    /**
     * Instantiates a new Handler discard button.
     *
     * @param view the view
     * @param controller
     * @param game
     */
    public HandlerDiscardB(SkyjoView view, Controller controller) {
        this.view = view;
        this.controller=controller;
       
    }

    /**
     * Handle method override from EventHandler
     * @param event mouse even when we got click
     */
    @Override
    public void handle(MouseEvent event) {
        view.setLabelAdviceMessage(1,controller.getClient().getUser());
        view.consumePickButton();
        view.consumeDiscardButton();
        view.setClickDiscard(true);

    }

    /**
     * Add action handler to the discard button.
     */
    public void definedActionHandler() {
        view.discardButton().addEventHandler(MouseEvent.MOUSE_CLICKED, this);

    }
}
