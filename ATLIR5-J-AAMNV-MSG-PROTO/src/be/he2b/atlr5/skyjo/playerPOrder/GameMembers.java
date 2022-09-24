/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.he2b.atlr5.skyjo.playerPOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author justu
 */
public class GameMembers implements Serializable{
      private final List<User> users;

    /**
     * Constructor of GameMembers
     */
    public GameMembers() {
        this.users = new ArrayList<>();
    }

    /**
     * Add a user to the list of users
     *
     * @param user that will be added
     */
    public void addUser(User user) {
        this.users.add(Objects.requireNonNull(user, "User can't be null"));
    }

    /**
     * remove a specific user from the users list maybe when he's offline
     *
     * @param user to remove
     */
    public void removeUser(User user) {
        this.users.remove(Objects.requireNonNull(user, "User can't be null"));
    }
    public User getFirstUser(){
        return users.isEmpty() ? null: users.get(0);
    }
    /**
     * return the list of all the users
     *
     * @return ArrayList of users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * return true if the list is empty false if not
     *
     * @return Boolean
     */
    public boolean noUsers() {
        return users.isEmpty();
    }

    /**
     * find a user and return the user if found or return null if no object
     *
     * @param ID the ID of the user
     * @return User found or null
     */
    public User findAUser(int ID) {
        var list = users.stream().filter(x -> x.getID() == ID).collect(Collectors.toList());
        return list.isEmpty() ? null : list.get(0);
    }

    /**
     * Add a user to the list using his details (mail and ID)
     *
     * @param name of the player
     * @param ID of the player
     */
    public void addUser(String name, int ID) {
        users.add(new User(name, ID));
    }

    /**
     * remove a user using his ID only return true if user remove false if not
     *
     * @param ID of the user
     * @return Boolean
     */
    public boolean removeUser(int ID) {
        var user = findAUser(ID);
        if (user == null) {
            return false;
        } else {
            removeUser(user);
            return true;
        }
    }

    /**
     * return true if the ID of the user given as argument is inside the list
     * @param ID of the user
     * @return Boolean
     */
    public boolean userExists(int ID) {
        return findAUser(ID) != null;
    }
    
    
}
