/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.he2b.atlir5.skyjo.dbManagement.exception;

/**
 *
 * @author mad8
 */
public class DtoException extends Exception {
    
    /**
     * Creates a new instance of <code>DtoException</code> without detail message.
     */
    public DtoException() {
    }


    /**
     * Constructs an instance of <code>DtoException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public DtoException(String msg) {
        super(msg);
    }
}
