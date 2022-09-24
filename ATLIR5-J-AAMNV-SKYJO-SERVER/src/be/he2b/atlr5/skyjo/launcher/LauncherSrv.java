package be.he2b.atlr5.skyjo.launcher;

import be.he2b.atlir5.skyjo.serverManagement.serverEntry.PrimaryServer;

/**
 * Main function for the server
 */
public class LauncherSrv {
    public static void main(String[] args) {
        var primaryServer = new PrimaryServer("SKYJO-Server");
        primaryServer.start();
    }
}
