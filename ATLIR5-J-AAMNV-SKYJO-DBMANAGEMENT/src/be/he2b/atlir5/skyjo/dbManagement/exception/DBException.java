/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.he2b.atlir5.skyjo.dbManagement.exception;

/**
 *
 * @author mad8
 */
public class DBException extends Exception{
    /**
     * Creates a new instance of <code>DbException</code> without detail message.
     */
    public DBException() {
    }


    /**
     * Constructs an instance of <code>DbException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public DBException(String msg) {
        super(msg);
    }
}
