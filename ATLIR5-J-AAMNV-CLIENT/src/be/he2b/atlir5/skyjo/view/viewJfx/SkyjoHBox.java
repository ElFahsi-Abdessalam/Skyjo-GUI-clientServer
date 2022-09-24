package be.he2b.atlir5.skyjo.view.viewJfx;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import be.he2b.atlr5.skyjo.model.*;
import be.he2b.atlr5.skyjo.playerPOrder.User;

import java.util.List;
import java.util.Objects;

/**
 * The type Skyjo h box.
 */
public class SkyjoHBox extends HBox {

    private PlayerVBox leftPlayer;
    private final RightPane rightPlayer;
    private final MiddleVBox middle_Pane;
    private Player myPlayer;

    /**
     * Instantiates a new Skyjo h box.
     *
     * @param game the game
     */
    public SkyjoHBox(Facade game) {
        Objects.requireNonNull(game, "Game in skyjoHBox must not be null");
        rightPlayer = new RightPane();
        middle_Pane = new MiddleVBox(game.getDeck());
    }
    
    public Button getCommentButton(){
        return rightPlayer.getCommentB();
    }

    /**
     * Create HBox.
     *
     * @param game the game instance received by the server
     * @param client the current player instance
     */
    public void createHbox(User client, Facade game) {
        definedHBoxProperties();
        createLeftPlayerVBox(game, client);
        if (client.getID() != game.getCurrentPlayer().getPlayerID()) {
            leftPlayer.consumeMouseEvent();
            middle_Pane.consumeMySelf();
        }
        rightPlayer.creatPane(client, game.getPlayers());
        super.getChildren().add(leftPlayer);
        super.getChildren().add(middle_Pane);
        super.getChildren().add(rightPlayer);
    }

    /**
     * create the left VBox that contains all the information for the player
     *
     * @param game received by the server
     * @param client current client
     */
    private void createLeftPlayerVBox(Facade game, User client) {
        var player = game.getPlayers().stream().
                filter(x -> x.getPlayerID() == client.getID()).toList();
        myPlayer = player.get(0);
        leftPlayer = new PlayerVBox(myPlayer);
        leftPlayer.createPane(myPlayer);
        middle_Pane.createPane();

    }

    /**
     * Defined default properties for the HBox
     */
    private void definedHBoxProperties() {
        super.setMaxSize(500, 600);
        super.setMinSize(500, 600);
        super.setSpacing(20);
        super.setPadding(new Insets(10));

    }

    public void consumeDiscardButton() {

        middle_Pane.consumeDiscardButton();
    }

    public void consumePickButton() {

        middle_Pane.consumePickButton();
    }

    /**
     * Gets pick button.
     *
     * @return the pick button
     */
    public Button getPickButton() {
        return middle_Pane.getPickButton();
    }

    /**
     * Gets discard button.
     *
     * @return the discard button
     */
    public Button getDiscardButton() {
        return middle_Pane.getDiscardButton();
    }

    /**
     * Update element.
     *
     * @param client current client running
     * @param game received by the server
     */
    public void updateElements(User client, Game game) {
        revealElements(client, game);
        if (client.getID() == game.getCurrentPlayer().getPlayerID()) {
            leftPlayer.removeMouseConsumeEvent();
            middle_Pane.removeMouseConsumeEvent();
            middle_Pane.removeDiscardConsume();
            middle_Pane.removePickConsume();
        } else {
            leftPlayer.consumeMouseEvent();
            middle_Pane.consumeMySelf();
        }

    }

    /**
     * change the GUI elements ( reveal cards if some card are reveal and update
     * the score and the middle pane)
     *
     * @param game received by the server
     */
    private void revealElements(User client, Game game) {
        var oldPlayer = myPlayer;
        myPlayer = game.getPlayers().stream().filter(x -> x.getPlayerID() == oldPlayer.getPlayerID()).toList().get(0);
        revealCard(myPlayer, 0);
        leftPlayer.setScore(myPlayer);
        middle_Pane.setDiscardLabel(game);
        updateChoiceBox(client, game.getPlayers());
        updateRightPane(game.getPlayers().get(game.getPlayers().size() - 1));

    }

    public void updateChoiceBox(User client, List<Player> players) {
        this.rightPlayer.updateChoiceBox(client, players);
    }

    /**
     * update the right pane only with the player as argument
     *
     * @param player last player of the list of player
     */
    public void updateRightPane(Player player) {
        revealCard(player, 1);
        rightPlayer.setScore(player);
    }

    /**
     * Gets click.
     *
     * @return the click
     */
    public CardButton getClick() {
        return leftPlayer.getClick();
    }

    /**
     * Gets list button.
     *
     * @return the list button
     */
    public List<CardButton> getListButton() {
        return leftPlayer.getListButton();
    }

    /**
     * Reveal card.
     *
     * @param player_1 the player 1
     * @param value and integer that determine if the is the leftPlayer pane
     * that will be update or the rightPlayer pane
     */
    public void revealCard(Player player_1, int value) {
        switch (value) {
            case 0:
                leftPlayer.revealCards(player_1);
                break;

            case 1:
                rightPlayer.revealCards(player_1);
                break;
        }

    }

    /**
     * Add a card.
     *
     * @param card the card
     */
    public void addACard(Card card) {
        middle_Pane.addShowCardLabel(card);
    }

    /**
     * Gets pick option.
     *
     * @return the pick option
     */
    public int getPickOption() {
        return middle_Pane.getPickOption();
    }

    /**
     * Gets keep b.
     *
     * @return the keep b
     */
    public Button getKeepB() {
        return middle_Pane.getKeepB();
    }

    /**
     * Gets no keep button.
     *
     * @return the no keep button
     */
    public Button getNoKeepB() {
        return middle_Pane.getNoKeepB();
    }

    /**
     * Sets pick option.
     *
     * @param option the option
     */
    public void setPickOption(int option) {
        middle_Pane.setPickOption(option);
    }

    /**
     * Hide pop up.
     */
    public void hidePopUp() {
        middle_Pane.hidePopUp();
    }

    /**
     * choice box getter
     *
     * @return Choice box object
     */
    public ChoiceBox<String> getChoiceBox() {
        return rightPlayer.getPlayersOnlineBox();
    }
}
