package be.he2b.atlir5.skyjo.view.viewJfx.fxmlControllers;

import be.he2b.atlir5.skyjo.controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;

public class UserNameInterface implements Initializable {

    @FXML
    private TextField userName;
    @FXML
    private Button buttonEnter;
    @FXML
    private Label inputMsg;
    private final Controller controller;

    /**
     * Constructor for UserMailInterface
     *
     * @param controller maintaining the client
     */
    public UserNameInterface(Controller controller) {
        this.controller = controller;
    }

    /**
     * getter of player mail.
     *
     * @return userMail
     */
    public String getPlayerName() {
        return userName.getText();
    }

    /**
     *
     */
    public void setInputMsg() {
        inputMsg.setText("Le nom entrée est déjà utilisé, veuillez en entrer un autre...");
        inputMsg.setStyle("-fx-text-fill:red;");
    }

    /**
     * Event set the mail of player when the player click on mouse.
     */
    private void actionButton() {
        buttonEnter.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            controller.setUserName(getPlayerName());
        });
    }

    /**
     * Override initialize function
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        actionButton();
    }

}
