package be.he2b.atlir5.skyjo.view.viewJfx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import be.he2b.atlr5.skyjo.model.*;
import be.he2b.atlr5.skyjo.playerPOrder.User;

import java.util.List;
import javafx.scene.control.Button;

/**
 * The type Right pane.
 */
public class RightPane extends VBox {

    private LocalLabel gridPane;
    private final Label upperLabel;
    private ChoiceBox<String> playersOnlineBox;
    private List<Player> lastList;
    private Button commentB;

    /**
     * return getter for the ChoiceBox event
     *
     * @return ChoiceBox Object
     */
    public ChoiceBox<String> getPlayersOnlineBox() {
        return playersOnlineBox;
    }

    /**
     * Instantiates a new Right pane.
     */
    public RightPane() {
        this.upperLabel = new Label();
    }

    /**
     * Create the pane for the player GUI
     *
     * @param client the current client asking for creation
     * @param players List of all the players in the game
     */
    public void creatPane(User client, List<Player> players) {
        definedVBoxProperties();
        var labelInfo = new Label();
        lastList = players.stream().filter(x -> x.getPlayerID() != client.getID()).toList();
        var index = lastList.size() - 1;
        definedOtherPlayersInformation(labelInfo, lastList, index);
        var miniHBox = new HBox(labelInfo, this.playersOnlineBox,this.commentB);
        miniHBox.setPadding(new Insets(10));
        miniHBox.setSpacing(20);
        this.gridPane = new LocalLabel(lastList.get(index));
        addChildren(miniHBox);

    }

    public Button getCommentB() {
        return commentB;
    }

    /**
     *
     * @param client
     * @param players
     */
    public void updateChoiceBox(User client, List<Player> players) {
        var tempList = lastList.stream().map(Player::getName).toList();
        lastList = players.stream().filter(x -> x.getPlayerID() != client.getID()).toList();
        this.playersOnlineBox.getItems().removeAll(tempList);
        this.playersOnlineBox.getItems().addAll(lastList.stream().map(Player::getName).toList());
    }

    /**
     * Add the children on the VBox
     *
     * @param miniHBox a hbox that contain the choiceBox and a label to show a a
     * mesage
     */
    private void addChildren(HBox miniHBox) {

        super.getChildren().add(miniHBox);
        super.getChildren().add(upperLabel);
        super.getChildren().add(gridPane);
    }

    /**
     * Fill the choiceBox with the information given in arguments
     *
     * @param labelInfo the label that show the information showing online
     * players
     * @param players the list of the players that are online
     * @param index a value as index to get a player to show on the GUI
     */
    private void definedOtherPlayersInformation(Label labelInfo, List<Player> players, int index) {
        labelInfo.setText("Joueur en ligne");
        labelInfo.setStyle("-fx-background-color: burlywood;-fx-padding:10;fx-spaccing:20;-fx-background-radius:10.0;");
        this.playersOnlineBox = new ChoiceBox<>();
        this.playersOnlineBox.getItems().addAll(players.stream().map(Player::getName).toList());
        this.commentB = new Button("Ajouter un commentaire");
        upperLabel.setMaxSize(350, 30);
        upperLabel.setText("Player: " + players.get(index).getName() + "  " + "Score: " + players.get(index).getScore());
        upperLabel.setAlignment(Pos.CENTER);
        upperLabel.setStyle("-fx-background-color: burlywood;-fx-padding:10;fx-spaccing:20;-fx-background-radius:10.0;");

    }

    /**
     * defined global vbox properties
     */
    private void definedVBoxProperties() {
        super.setMaxSize(450, 450);
        super.setMinSize(450, 450);
        super.setPadding(new Insets(10));
        super.setSpacing(20);
    }

    /**
     * Reveal cards.
     *
     * @param player the player
     */
    public void revealCards(Player player) {
        gridPane.revealCards(player);

    }

    /**
     * Sets score.
     *
     * @param player the player
     */
    public void setScore(Player player) {
        upperLabel.setText("Player: " + player.getName() + "  " + "Score: " + player.getScore());
    }

}
