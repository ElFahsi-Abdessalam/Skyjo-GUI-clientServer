package be.he2b.atlir5.skyjo.serverManagement.serverEntry;

//import be.he2b.atlir5.skyjo.dbManagement.business.AdminModel;
import be.he2b.atlir5.skyjo.serverManagement.serverDispatch.GameManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Primary server.
 */
public class PrimaryServer extends Thread {

    //private int gameServersID;
    private final List<GameManager> gameServers;
    private ServerSocket serverSocket;
    private final int ports;
    private boolean listening;
    private int gameServersID;

    /**
     * Default constructor for the main server
     *
     * @param name the name
     */
    public PrimaryServer(String name) {
        super(name);
        this.ports = 2021;
        listening = false;
        gameServers = new ArrayList<>();
        //gameServersID = 0;
    }

    /**
     * Send to the second server the client socket
     *
     * @param clientSocket the client socket after connecting
     */
    private void sendToGameSrv(Socket clientSocket) {
        if (gameServers.isEmpty()) {
            initANewSrvGame(clientSocket);
        } else {
          
            var added = addClientSocketToGameSrv(clientSocket);
            if (!added) {
                initANewSrvGame(clientSocket);
            }
        }
        System.out.println("Nouveau joueur connecter...");
        showMainSrvStatus();

    }

    /**
     * Create a new second server game if their is no free server
     *
     * @param clientSocket the client socket
     */
    private void initANewSrvGame(Socket clientSocket){
       gameServersID++;
        gameServers.add(new GameManager(gameServersID, this));
        gameServers.get(gameServers.size() - 1).receivedData(clientSocket);
    }

    /**
     * the method that add a client to the second server when connected
     *
     * @param clientSocket the client socket
     * @param gameSrvFull value that help us to know if a game server is full or
     * not
     */
    private boolean addClientSocketToGameSrv(Socket clientSocket) {
        var sendToSrv = false;
        var index = 0;
        while (index < gameServers.size() && !sendToSrv) {
            if (!gameServers.get(index).isGameStarted()
                    && !gameServers.get(index).isFull()) {
                sendToSrv = true;
                gameServers.get(index).receivedData(clientSocket);
            }
            index++;
        }
        return sendToSrv;

    }

    /**
     * Override thread run method
     */
    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(ports);
            listening = true;
            showMainSrvStatus();
            System.out.println("Attente de joueur...\n");
            do {
                var clientSocket = serverSocket.accept();
                sendToGameSrv(clientSocket);
            } while (listening);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Close the main server socket and all connection related to it
     */
    public void stopMotherServer() {
        listening = false;
        try {
            serverSocket.close();
        } catch (IOException e) {
        }
        serverSocket = null;
    }

    /**
     * show information about the server
     */
    private void showMainSrvStatus() {
        System.out.println("-------------------INFO SERVEUR PRINCIPAL----------------------");
        System.out.println("Nombre de partie en cours : " + gameServers.size());
        System.out.println("Etat du serveur (true si écoute false sinon) :" + listening);
        System.out.println("Port d'écoute :" + ports);
        System.out.println("ID du thread: " + Thread.currentThread().getId());
        System.out.println("Nom du Thread: " + Thread.currentThread().getName());
        System.out.println("-----------------------------------------------------------------\n");
    }

    /**
     * Stop my section.
     *
     * @param sec the sec
     */
    public void stopMySession(GameManager sec){
        this.gameServers.remove(sec);
        sec=null;
    }
}
