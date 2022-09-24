/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.he2b.atlir5.skyjo.view.viewJfx.fxmlControllers;

import be.he2b.atlr5.skyjo.model.Player;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author 
 */
public class GameOverController implements Initializable{
    private final Player winner;
    @FXML
    private Label winnerMail;

    public GameOverController(Player winner) {
        this.winner = winner;
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.winnerMail.setText(winner.getName());
    }
    
}
