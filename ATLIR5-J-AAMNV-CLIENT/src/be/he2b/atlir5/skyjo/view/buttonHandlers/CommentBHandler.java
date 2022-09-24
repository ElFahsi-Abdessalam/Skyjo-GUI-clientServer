package be.he2b.atlir5.skyjo.view.buttonHandlers;

import be.he2b.atlir5.skyjo.controller.Controller;
import be.he2b.atlir5.skyjo.view.viewJfx.SkyjoView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author jj
 */
public class CommentBHandler implements EventHandler<MouseEvent> {

    private final SkyjoView view;
    private final Controller controller;

    public CommentBHandler(SkyjoView view,Controller controller) {
        this.view = view;
        this.controller= controller;
        
    }

    @Override
    public void handle(MouseEvent t) {
        view.openCommentWindows(this.controller);
        view.getCommentB().setDisable(true);
    }

    public void initHandler() {
        var cButton = view.getCommentB();
        cButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this);

    }

}
