package be.he2b.atlir5.skyjo.dbManagement.exception;

/**
 *
 * @author mad8
 */
public class DBBusinessException extends Exception{
     /**
     * Creates a new instance of <code>BusinessException</code> without detail message.
     */
    public DBBusinessException ()  {
    
    }


    /**
     * Constructs an instance of <code>BusinessException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public DBBusinessException (String msg) {
        super(msg);
    }
}
