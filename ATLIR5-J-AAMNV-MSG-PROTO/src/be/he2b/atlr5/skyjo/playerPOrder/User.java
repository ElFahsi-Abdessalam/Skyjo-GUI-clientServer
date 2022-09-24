/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.he2b.atlr5.skyjo.playerPOrder;

import java.io.Serializable;

/**
 *
 * @author justu
 */
public class User implements Serializable{
    public static final int DEFAULT_ID = 0;
    private final String name;
    private int ID;
    private int serverId; //ID of the user on the DB

    private UserType type;

    /**
     * Constructor of the user having his mail and ID
     * @param name of the player 
     * @param ID of the player
     */
    public User(String name, int ID) {
        this.name = name;
        this.ID = ID;
        type = UserType.GUEST;
    }

    /**
     * Overload constructor
     * @param name of the player
     * @param ID of the player
     * @param type ADMIN or OTHER or GUEST
     * @param servid
     */
    public User(String name, int ID, UserType type, int servid) {
        this.name = name;
        this.ID = ID;
        this.type = type;
        this.serverId = servid;
    }

    /**
     * Overload constructor using the mail only
     * @param name of the player 
     */
    public User(String name) {
        this.name = name;
        this.ID = DEFAULT_ID;
        this.type = UserType.GUEST;
    }

    /**
     * setter of ID
     * @param ID of the player
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * setter of the userType
     * @param type userType
     */
    public void setType(UserType type) {
        this.type = type;
    }

    public void setServerId(int serverID) {
        this.serverId = serverID;
    }
    
    /**
     * getter of the player mail
     * @return mail
     */
    public String getName() {
        return name;
    }

    /**
     * getter of the id 
     * @return ID
     */
    public int getID() {
        return ID;
    }

    /**
     * getter of the user Type
     * @return userType
     */
    public UserType getType() {
        return type;
    }
    
    public int getServerId() {
        return serverId;
    }
    
}
