package be.he2b.atlir5.skyjo.view.buttonHandlers;

import be.he2b.atlir5.skyjo.controller.Controller;
import be.he2b.atlr5.skyjo.model.Facade;
import be.he2b.atlir5.skyjo.view.viewJfx.SkyjoView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * The Drop button handler.
 */
public class DropButtonH implements EventHandler<MouseEvent> {

    private final SkyjoView view;
    private final Controller controller;

    /**
     * Instantiates a new Drop button handler.
     *
     * @param view the Skyjo View
     * @param controller
     * @param game
     */
    public DropButtonH(SkyjoView view, Controller controller, Facade game) {
        this.view = view;
        this.controller = controller;
       
    }

    /**
     * Override handle function from EventHandler
     *
     * @param event to defined the action
     */
    @Override
    public void handle(MouseEvent event) {
        view.setLabelAdviceMessage(4,controller.getClient().getUser());
        view.setPickOption(-1);

    }

    /**
     * Add event to the drop button.
     */
    public void addEvent() {
        var button = view.getNoKeepB();
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
    }
}
