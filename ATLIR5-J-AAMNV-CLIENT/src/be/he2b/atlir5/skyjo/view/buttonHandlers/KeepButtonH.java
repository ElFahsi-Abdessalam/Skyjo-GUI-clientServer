package be.he2b.atlir5.skyjo.view.buttonHandlers;

import be.he2b.atlir5.skyjo.controller.Controller;
import be.he2b.atlir5.skyjo.view.viewJfx.SkyjoView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * The Keep button handler.
 */
public class KeepButtonH implements EventHandler<MouseEvent> {
    private final SkyjoView view;
    private final Controller controller;
  

    /**
     * Handler for keep button
     * @param event mouse click event
     */
    @Override
    public void handle(MouseEvent event) {
        view.setLabelAdviceMessage(3,this.controller.getClient().getUser());
        view.setPickOption(1);


    }

    public KeepButtonH(SkyjoView view, Controller controller) {
        this.view = view;
        this.controller = controller;
      
    }

  

    /**
     * Add event.
     */
    public void addEvent() {
        var button = view.getKeepB();
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
    }
}

