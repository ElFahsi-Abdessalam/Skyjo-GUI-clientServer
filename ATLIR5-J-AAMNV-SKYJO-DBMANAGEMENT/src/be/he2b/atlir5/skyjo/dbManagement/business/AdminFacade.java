/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.he2b.atlir5.skyjo.dbManagement.business;

import be.he2b.atlir5.skyjo.dbManagement.dbdto.DataUserDto;
import be.he2b.atlir5.skyjo.dbManagement.dbdto.UserDto;
import be.he2b.atlir5.skyjo.dbManagement.exception.DBBusinessException;
import java.util.List;

/**
 *
 * @author mad8
 */
public interface AdminFacade {
    /**
     * Returns a list of all users.
     *
     * @return a list of all users.
     * @throws DBBusinessException if the query failed.
     */
    List<UserDto> getUsers() throws DBBusinessException;
    

    /**
     * Returns the unique user with the given id.
     *
     * @param id user's id.
     * @return the unique user with the given id.
     * @throws be.he2b.atlir5.skyjo.dbManagement.exception.DBBusinessException
     */
    UserDto getUser(int id) throws DBBusinessException;

    /**
     * Returns the last user with the given name.
     *
     * @param name user's mail.
     * @return the last user with the given name.
     * @throws be.he2b.atlir5.skyjo.dbManagement.exception.DBBusinessException
     */
    UserDto getUser(String name) throws DBBusinessException;

    /**
     * Creates a user and insert it in the database.Returns the user's id.
     *
     * @param userName
     * @return the user's id.
     * @throws be.he2b.atlir5.skyjo.dbManagement.exception.DBBusinessException
     */
    int addUser(String userName) throws DBBusinessException;

    /**
     * Updates the given user.
     *
     * @param current
     * @throws be.he2b.atlir5.skyjo.dbManagement.exception.DBBusinessException
     */
    void updateUser(UserDto current) throws DBBusinessException;
}
