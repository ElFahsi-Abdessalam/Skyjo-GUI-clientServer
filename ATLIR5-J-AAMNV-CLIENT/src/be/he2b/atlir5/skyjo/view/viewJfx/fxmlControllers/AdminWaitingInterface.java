package be.he2b.atlir5.skyjo.view.viewJfx.fxmlControllers;

import be.he2b.atlir5.skyjo.controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for welcomeScreen mainly made for the admin (the first
 * client that log in)
 *
 * @author
 */
public class AdminWaitingInterface implements Initializable {

    @FXML
    Label playerMail;
    Controller controller;
    @FXML
    Button buttonEnter;
    @FXML
    Label playerMsg;

    /**
     * Constructor
     *
     * @param controller for the player
     */
    public AdminWaitingInterface(Controller controller) {
        this.controller = controller;
    }

    /**
     * function override from the Initializable interface
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLabelMail();
        definedAction();
        buttonEnter.setDisable(true);

    }

    /**
     * set the player mail on the GUI
     */
    private void setLabelMail() {
        playerMail.setText(controller.getUserMail());
    }

    /**
     * Sets disable button for the button enter make it enable for the user to
     * click on it.
     */
    public void setDisable() {
        this.buttonEnter.setDisable(false);
    }

    /**
     * Defined the button enter handler
     */
    private void definedAction() {
        buttonEnter.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            controller.createTheGame();

        });
    }
}
