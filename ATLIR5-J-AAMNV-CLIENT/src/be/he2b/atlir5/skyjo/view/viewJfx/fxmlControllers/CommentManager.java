package be.he2b.atlir5.skyjo.view.viewJfx.fxmlControllers;

import be.he2b.atlir5.skyjo.controller.Controller;
import be.he2b.atlir5.skyjo.view.viewJfx.SkyjoView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author justu
 */
public class CommentManager implements Initializable{
    @FXML
    private TextField comInput;
    @FXML
    private Button sendCom;
    @FXML
    private Label labelMsg;
    private final Controller controller;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        definedAction();
    }
    
    public String getComment(){
        return comInput.getText();
    }
    private void definedAction(){
        this.sendCom.addEventHandler(MouseEvent.MOUSE_CLICKED, event->{
            if(getComment().isEmpty()) showErrorMsg();
            else controller.sendCom(getComment());
        });
    }

    public CommentManager(Controller controller) {
        this.controller = controller;
    }

    public Button getSendCom() {
        return sendCom;
    }
    private void showErrorMsg(){
        this.labelMsg.setText("Empty comment please write a comment");
        this.labelMsg.setStyle("-fx-text-fill:red;");
        
    }
    
    
}
