package be.he2b.atlir5.skyjo.view.viewJfx.fxmlControllers;

import be.atlir5.skyjo.dbInfo.UserDbInfo;
import be.he2b.atlir5.skyjo.clientCo.ClientConnexion;
import be.he2b.atlr5.skyjo.playerPOrder.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class UserInfo implements Initializable {

    @FXML
    private Label userMail;
    @FXML
    private Label id;
    @FXML
    private Label type;
    @FXML
    private Label lowestScore;
    @FXML
    private Label gamesPlayed;
    @FXML
    private Label winningGames;
    private final User user;
    private final UserDbInfo info;

    /**
     * Default constructor 
     * @param user the current user
     * @param info
     */
    public UserInfo(ClientConnexion user,UserDbInfo info) {
        this.user = user.getUser();
        this.info = info;
    }

    /**
     * Override function from Initializable
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userMail.setText(user.getName());
        id.setText(String.valueOf(user.getID()));
        type.setText(String.valueOf(user.getType()));
        lowestScore.setText(String.valueOf(info.getLowestScore()));
        gamesPlayed.setText(String.valueOf(info.getNbrGames()));
        winningGames.setText(String.valueOf(info.getNbrWins()));

    }

}
