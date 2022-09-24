package be.he2b.atlir5.skyjo.view.buttonHandlers;

import be.he2b.atlir5.skyjo.controller.Controller;
import be.he2b.atlir5.skyjo.view.viewJfx.SkyjoView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * The choice Box handler anytime a player modified the value of the choice box
 *
 * @author
 */
public class ChoiceBoxHandler implements EventHandler<ActionEvent> {

    private final SkyjoView view;
    private final Controller controller;

    /**
     * Choice box handler constructor
     *
     * @param view the player view
     * @param controller the player controller that control the view
     */
    public ChoiceBoxHandler(SkyjoView view, Controller controller) {
        this.view = view;
        this.controller = controller;
    }

    /**
     * handle function override from the EventHandler interface
     *
     * @param actionEvent any action event that occur on the choice box by
     * default when the value change
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        controller.sendGetThisPlayerGameInfo(this.view.getChoiceBox().getValue());

    }

    /**
     * function that add the even handler on the choice box
     */
    public void definedEventAction() {
        this.view.getChoiceBox().addEventHandler(ActionEvent.ACTION, this::handle);
    }
}
