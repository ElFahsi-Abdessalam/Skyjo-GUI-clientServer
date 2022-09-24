package be.he2b.atlir5.skyjo.view.viewJfx;

import be.atlir5.skyjo.dbInfo.UserDbInfo;
import be.he2b.atlir5.skyjo.view.viewJfx.fxmlControllers.UserNameInterface;
import be.he2b.atlir5.skyjo.view.viewJfx.fxmlControllers.UserInfo;
import be.he2b.atlir5.skyjo.view.viewJfx.fxmlControllers.AdminWaitingInterface;
import be.he2b.atlir5.skyjo.clientCo.ClientConnexion;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import be.he2b.atlr5.skyjo.serverMsg.*;
import be.he2b.atlir5.skyjo.controller.Controller;
import be.he2b.atlr5.skyjo.model.*;
import be.he2b.atlr5.skyjo.otherMsg.OtherMsg;
import be.he2b.atlr5.skyjo.playerPOrder.User;
import static be.he2b.atlr5.skyjo.serverMsg.ServerMsgType.NEW_PLAYER;
import be.he2b.atlir5.skyjo.view.buttonHandlers.ButtonHandlers;
import be.he2b.atlir5.skyjo.view.viewJfx.fxmlControllers.CommentManager;
import be.he2b.atlir5.skyjo.view.viewJfx.fxmlControllers.GameOverController;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

/**
 * The type Skyjo view.
 */
public class SkyjoView implements Observer {

    private SkyjoVBox vBox;
    private SkyjoHBox hBox;
    private boolean isClickDiscard;
    private boolean isClickPick;
    private Card cardPick;
    private AdminWaitingInterface adminWaitingInterface;
    private UserNameInterface userInt;
    private Stage stage;
    private ButtonHandlers handlers;
    private CommentManager commentM;
    private Stage commentStage;

    /**
     *
     */
    public void consumeDiscardButton() {

        hBox.consumeDiscardButton();
    }

    public Button getCommentB() {
        return hBox.getCommentButton();
    }

    /**
     *
     */
    public void consumePickButton() {

        hBox.consumePickButton();
    }

    /**
     * Gets card pick.
     *
     * @return the card pick
     */
    public Card getCardPick() {
        return cardPick;
    }

    /**
     * Sets card pick.
     *
     * @param cardPick the card pick
     */
    public void setCardPick(Card cardPick) {
        this.cardPick = cardPick;
    }

    /**
     * Is click discard boolean.
     *
     * @return the boolean
     */
    public boolean isClickDiscard() {
        return isClickDiscard;
    }

    /**
     * Sets click discard.
     *
     * @param clickDiscard the click discard
     */
    public void setClickDiscard(boolean clickDiscard) {
        isClickDiscard = clickDiscard;
    }

    /**
     * Is click pick boolean.
     *
     * @return the boolean
     */
    public boolean isClickPick() {
        return isClickPick;
    }

    /**
     * Sets click pick.
     *
     * @param clickPick the click pick
     */
    public void setClickPick(boolean clickPick) {
        isClickPick = clickPick;
    }

    /**
     * Init view.
     *
     * @param game the game
     * @param client current client
     */
    public void initView(Facade game, User client) {
        Objects.requireNonNull(game, "Game Must not be given null");
        vBox = new SkyjoVBox(game);
        hBox = new SkyjoHBox(game);
        this.isClickDiscard = false;
        this.isClickPick = false;
        hBox.createHbox(client, game);
        vBox.getChildren().add(hBox);
        vBox.createVbox();

    }

    /**
     * Gets box.
     *
     * @return box box
     */
    public SkyjoVBox getvBox() {
        return vBox;
    }

    /**
     * Pick hand button button.
     *
     * @return the button
     */
    public Button pickHandButton() {
        return hBox.getPickButton();

    }

    /**
     * Discard button button.
     *
     * @return the button
     */
    public Button discardButton() {
        return hBox.getDiscardButton();

    }

    /**
     * Sets label advice message.
     *
     * @param signal the signal
     * @param user
     */
    public void setLabelAdviceMessage(int signal, User user) {
        vBox.setLabelMsg(signal, user);
    }

    /**
     * Gets list card.
     *
     * @return the list card
     */
    public List<CardButton> getListCard() {
        return hBox.getListButton();
    }

    /**
     * Add a card.
     *
     * @param card the card
     */
    public void addACard(Card card) {
        hBox.addACard(card);
    }

    /**
     * Gets pick option.
     *
     * @return the pick option
     */
    public int getPickOption() {
        return hBox.getPickOption();
    }

    /**
     * Gets keep b.
     *
     * @return the keep b
     */
    public Button getKeepB() {
        return hBox.getKeepB();
    }

    /**
     * Gets no keep b.
     *
     * @return the no keep b
     */
    public Button getNoKeepB() {
        return hBox.getNoKeepB();
    }

    /**
     * Sets pick option.
     *
     * @param option the option
     */
    public void setPickOption(int option) {
        hBox.setPickOption(option);
    }

    /**
     * Hide pop up.
     */
    public void hidePopUp() {
        hBox.hidePopUp();
    }

    /**
     * Gets card click.
     *
     * @return the card click
     */
    public Card getCardClick() {
        return hBox.getClick().getCard();
    }

    /**
     * Overload constructor for Skyjo view it create and initialize the first
     * GUI for the client
     *
     * @param controller maintaining the game
     * @param stage GUI element for JFX
     */
    public SkyjoView(Controller controller, Stage stage) {
        var fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/clientStartScreen.fxml"));
        userInt = new UserNameInterface(controller);
        fxmlLoader.setController(userInt);
        this.stage = stage;
        try {
            Pane pane = fxmlLoader.load();
            var scene = new Scene(pane);
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Initialize the game GUI with the argument received
     *
     * @param game received by the server
     * @param client the current client
     */
    private void createView(Facade game, ClientConnexion client) {
        initView(game, client.getUser());
        vBox.setLabelMsg(0, client.getUser());
        this.handlers = new ButtonHandlers(client.getController(), game, this);
        this.handlers.definedHandlers();
        this.stage.setHeight(700);
        this.stage.setWidth(1200);

    }

    /**
     * Observer Observable implementation In the function we interpret each
     * message received by the server GAME_CREATED, NEW_PLAYER (this message is
     * send by the server when a new player is connect while the game is running
     * or when a game is created by the server) GAME_UPDATE (this message is
     * send when the server apply a discard action or a pitched action so the
     * game is update and the view also will be update) GAME_FINISHED (when the
     * game is over and we go a winner) USER_READY ( when a new user is
     * connected and complete the login scenario and ready to start the game
     * with the first player already connected and waiting) ALL_PLAYER_ONLINE
     * (when another player is connected ) CARD_SEND ( when the client ask for a
     * card and the server send a card deck.hit() function call in server )
     * USER_CREATED ( when the server created a user and send to the client for
     * him to update the his user state) PLAYER_INFO ( send the player
     * information this happen when the choice box change his value )
     *
     * @param observable the clientConnexion
     * @param o the Object received by the server
     */
    @Override
    public void update(Observable observable, Object o) {
        var client = (ClientConnexion) observable;
        var srvMsg = (ServerMsg) o;
        switch (srvMsg.getType()) {
            case GAME_CREATED, NEW_PLAYER -> {
                if (srvMsg.getType() == NEW_PLAYER) {
                    if (hBox != null) {
                        Platform.runLater(() -> {
                            hBox.updateChoiceBox(client.getUser(),
                                    ((Facade) srvMsg.getContent()).getPlayers());
                        });
                    } else {
                        var otherMsg = (OtherMsg) srvMsg.getMsg();
                        client.setUser((User) otherMsg.getObject1());
                        createView((Facade) otherMsg.getObject2(), client);
                    }

                } else {
                    createView((Facade) srvMsg.getMsg(), client);
                }
                this.stage.getScene().setRoot(this.vBox);
            }
            case GAME_UPDATE, USER_LOGOUT ->
                Platform.runLater(() -> {
                    hBox.updateElements(client.getUser(), (Game) srvMsg.getMsg());
                    vBox.setGame((Facade) srvMsg.getMsg());
                    vBox.setLabelMsg(0, client.getUser());
                });
            case GAME_FINISHED -> {
                Platform.runLater(() -> {
                    loadFmxlGameOver(((Facade) srvMsg.getMsg()).getWinner());
                });
            }
            case ALL_PLAYER_ONLINE -> {
                if (client.isAdmin()) {
                    loadWaitScreenForAdmin(client.getController());
                }
            }
            case USER_READY -> {
                if (client.isAdmin()) {
                    enableStartButton();
                }
            }
            case CARD_SEND ->
                Platform.runLater(() -> {
                    addACard((Card) srvMsg.getMsg());
                    setCardPick((Card) srvMsg.getMsg());

                });
            case USER_CREATED -> {
                client.setUser((User) ((OtherMsg) srvMsg.getMsg()).getObject1());
                showUsersInformation(client, (UserDbInfo) ((OtherMsg) srvMsg.getMsg()).getObject2());
            }
            case PLAYER_INFO ->
                Platform.runLater(() -> updateRightPane((Player) srvMsg.getMsg()));
            case INVALID_NAME ->
                Platform.runLater(() -> {
                    showErrorInvalidName();

                });
        }

    }

    public Stage getStage() {
        return stage;
    }

    /**
     * Ask the HBox the update the right pane according to the player given as
     * argument
     *
     * @param player need to be updated
     */
    private void updateRightPane(Player player) {
        hBox.updateRightPane(player);

    }

    /**
     * Basic getter for the choice box
     *
     * @return choice box Object
     */
    public ChoiceBox<String> getChoiceBox() {
        return hBox.getChoiceBox();
    }

    /**
     * GUI to show when the game is over
     *
     * @param winner the player that win received by the server
     */
    private void loadFmxlGameOver(Player winner) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/game_over.fxml"));
        var controller = new GameOverController(winner);
        loader.setController(controller);
        try {
            Parent pane = loader.load();
            this.stage.setHeight(400);
            this.stage.setWidth(600);
            this.stage.getScene().setRoot(pane);
        } catch (IOException e) {
        }

    }

    /**
     * Load fxml file to show the user information
     *
     * @param client current client connected
     */
    private void showUsersInformation(ClientConnexion client, UserDbInfo info) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/userInfo.fxml"));
        var infoController = new UserInfo(client, info);
        loader.setController(infoController);
        try {
            Parent pane = loader.load();
            this.stage.getScene().setRoot(pane);
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
    }

    /**
     *
     */
    private void showErrorInvalidName() {
        this.userInt.setInputMsg();
    }

    /**
     * Load fxml file for default screen
     *
     * @param controller maintaining the player game
     */
    private void loadWaitScreenForAdmin(Controller controller) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/welcomeScreen.fxml"));
        this.adminWaitingInterface = new AdminWaitingInterface(controller);
        loader.setController(this.adminWaitingInterface);
        try {
            Pane pane = loader.load();
            this.stage.getScene().setRoot(pane);
        } catch (IOException e) {

        }

    }

    public void openCommentWindows(Controller controller) {
        var fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/commentSection.fxml"));
        this.commentM = new CommentManager(controller);
        fxmlLoader.setController(this.commentM);
        try {
            Pane pane = fxmlLoader.load();
            this.commentStage = new Stage();
            this.commentStage.setScene(new Scene(pane));
            this.commentStage.show();
        } catch (IOException e) {
        }

    }

    public Button getSendComButton() {
        return this.commentM.getSendCom();
    }

    /**
     * Enable the button to start the game
     */
    private void enableStartButton() {
        this.adminWaitingInterface.setDisable();

    }

    public void closeCommentWindows(User client) {
       this.commentStage.close();
       getCommentB().setDisable(false);
       this.vBox.setLabelMsg(5, client);
    }
}
