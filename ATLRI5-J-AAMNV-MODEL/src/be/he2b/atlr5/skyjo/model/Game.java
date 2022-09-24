package be.he2b.atlr5.skyjo.model;

import be.he2b.atlr5.skyjo.serverMsg.ServerMsgType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Game.
 */
public class Game extends Facade {

    private final Deck deck;
    private Player currentPlayer;
    private final Player opponentPlayer;
    private final List<Player> players;
    private Card cardOut;
    private ServerMsgType type;

    /**
     *
     * @return
     */
    @Override
    public List<Player> getPlayers() {
        return players;
    }

    /**
     *
     * @return
     */
    @Override
    public ServerMsgType getType() {
        return type;
    }

    /**
     * Instantiates a new Game.
     *
     * @param details1 the id of Player 1
     * @param details2 the id of player 2
     */
    public Game(PlayerDetails details1, PlayerDetails details2) {
        this.players = new ArrayList<>();
        deck = new Deck();
        currentPlayer = new Player(details1);
        opponentPlayer = new Player(details2);
        initializedGame();
        this.players.addAll(List.of(currentPlayer, opponentPlayer));
        type = ServerMsgType.GAME_CREATED;
    }

    /**
     * Add a new player to the game while the game still rolling
     *
     * @param player the player that will be added
     * @throws IllegalStateException throw an exception if the deck is empty
     */
    @Override
    public void addNewPlayer(Player player) throws IllegalStateException {
        if (!deck.isEmpty()) {
            player.addAll(deck.return12Card());
            revealCardForNewPlayer(player);
            this.players.add(Objects.requireNonNull(player, "This player add must not be null"));
            type = ServerMsgType.NEW_PLAYER;
        } else {
            throw new IllegalStateException("Deck is empty");
        }
    }

    /**
     * Initialized game.
     */
    private void initializedGame() {
        deck.shuffle();
        opponentPlayer.addAll(deck.return12Card());
        currentPlayer.addAll(deck.return12Card());
        deck.addToDiscard(deck.hit());

    }

    /**
     * Gets deck.
     *
     * @return the deck
     */
    @Override
    public Deck getDeck() {
        return deck;
    }

    @Override
    public Player getPlayerWithName(String name) {
        type = ServerMsgType.PLAYER_INFO;
        return this.players.stream().filter(x -> !name.isEmpty()&&x.getName().equals(name)).toList().get(0);
    }

    @Override
    public Player getWinner() {
        var score = players.stream().mapToInt(x -> x.getScore()).min().getAsInt();
        return players.stream().filter(x -> x.getScore() == score).toList().get(0);
    }

    @Override
    public void updateGame(PlayerChoice choice) {
        switch (choice) {
            case DISCARD ->
                switchAndReplaceCards(getPeek(), currentPlayer.getSelected());
            case PICK_KEEP ->
                switchAndReplaceCards(cardOut, currentPlayer.getSelected());
            case PICK_DROP ->
                dropAndShow(cardOut, currentPlayer.getSelected());
        }
        type = ServerMsgType.GAME_UPDATE;
    }

    @Override
    public void setCardOut(Card cardOut) {
        this.cardOut = cardOut;
    }

    @Override
    public void setCardCurrentPlayer(Card card) {
        currentPlayer.setSelected(card);
    }

    /**
     * Gets current player.
     *
     * @return the current player
     */
    @Override
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Gets opponent player.
     *
     * @return the opponent player
     */
    @Override
    public Player getOpponentPlayer() {
        return opponentPlayer;
    }

    /**
     * Switch player.
     */
    @Override
    public void switchPlayer() {
        if (currentPlayer.equals(players.get(players.size() - 1))) {
            currentPlayer = players.get(0);
        } else {
            for (int i = 0; i < players.size(); i++) {
                if (currentPlayer.equals(players.get(i))) {
                    ++i;
                    currentPlayer = players.get(i);
                    break;
                }
            }

        }
    }

    /**
     * it switch the two card values and add the hit value to the discards
     *
     * @param card_hit cart element
     * @param card_selected cart element
     */
    private void switchAndReplaceCards(Card card_hit, Card card_selected) {
        var playerCard = currentPlayer.getHand().stream().
                filter(x -> x.equals(card_selected)).
                toList().get(0);
        playerCard.setHide(false);
        card_hit.setHide(false);
        this.deck.switchTwoCard(card_hit, playerCard);
        this.deck.addToDiscard(card_hit);
    }

    /**
     * drop a card and show set the player card selected to true
     *
     * @param card_hit cart element card hit
     * @param card_selected cart seelcted
     */
    private void dropAndShow(Card card_hit, Card card_selected) {
        var playerCard = currentPlayer.getHand().stream().
                filter(x -> x.equals(card_selected)).
                toList().get(0);
        card_hit.setHide(false);
        this.deck.addToDiscard(card_hit);
        this.deck.removeCardFromPitched(card_hit);
        playerCard.setHide(false);
    }

    private void addToPitched() {
        var discard = deck.popFromDiscard();
        deck.addToPitched(discard);
        deck.shuffle();
    }

    /**
     * Update score of the current player
     */
    @Override
    public void updateScore() {
        currentPlayer.updateScore();
    }

    /**
     * Is over boolean. return true if the game is over means if so a player win
     *
     * @return the boolean
     */
    @Override
    public boolean isOver() {
        var count = (int) players.stream().filter(x -> x.allCardReveal()).count();
        if (count == players.size() - 1) {
            var minScore = players.stream().mapToInt(x -> x.getScore()).min();
            if (minScore.isPresent()) {
                type = ServerMsgType.GAME_FINISHED;
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public void updateDiscardOnly() {
        addToPitched();
        notifyObservers(this);
    }

    /**
     * Hit card.
     *
     * @return the card
     */
    @Override
    public Card hit() {
        type = ServerMsgType.CARD_SEND;
        cardOut = new Card(deck.hit());
        return cardOut;
    }

    /**
     * Update two player score.
     */
    private void updatePlayersScore() {
        players.forEach(x -> x.updateScore());
    }

    /**
     * Gets peek.
     *
     * @return the peek
     */
    @Override
    public Card getPeek() {
        return deck.getFistIn();
    }

    /**
     * Decide who start.
     */
    @Override
    public void decideWhoStart() {
        if (opponentPlayer.getScore() < currentPlayer.getScore()) {
            switchPlayer();
        }
    }

    /**
     * reveal two card card for each player
     */
    @Override
    public void revealCard() {
        players.forEach(x -> {
            for (int i = 0; i <= 1; ++i) {
                x.getCardAt(randomValues()).setHide(false);
            }
        });
        updatePlayersScore();
        decideWhoStart();

    }

    private void revealCardForNewPlayer(Player player) {
        Objects.requireNonNull(player, "Player given must not be null");
        player.getCardAt(randomValues()).setHide(false);
        player.getCardAt(randomValues()).setHide(false);
        
        
    }

    /**
     * return a valid random value
     *
     * @return int random
     */
    private int randomValues() {
        int random = (int) (Math.random() * (11));
        while (notValid(random)) {
            random = (int) (Math.random() * (11));
        }
        return random;
    }

    /**
     * return true if the value given as argument is not valid
     *
     * @param value argument
     * @return boolean
     */
    private boolean notValid(int value) {
        return value < 0 || value > 12;
    }

    @Override
    public void removePlayer(int ID) {
        players.remove(players.stream().filter(x -> x.getPlayerID() == ID).toList().get(0));
        type=ServerMsgType.USER_LOGOUT;
    }

    @Override
    public void addNewPlayers(List<Player> players) {
        this.players.addAll(players);
    }

    @Override
    public void updatePlayers() {
        players.stream().filter(x -> {
            return !x.equals(currentPlayer) && !x.equals(opponentPlayer);
        }).forEach(x -> {
            x.addAll(deck.return12Card());
        });
        revealCard();
        updateScore();
        var minscore = players.stream().mapToInt(x -> x.getScore()).min().getAsInt();
        currentPlayer = players.stream().filter(x -> x.getScore() == minscore).toList().get(0);

    }
}
