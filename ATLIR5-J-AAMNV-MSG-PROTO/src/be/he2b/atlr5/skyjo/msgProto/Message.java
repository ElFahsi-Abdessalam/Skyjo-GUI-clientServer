package be.he2b.atlr5.skyjo.msgProto;

import java.io.Serializable;

/**
 *
 * @author
 */
public interface Message extends Serializable {

    /**
     * Return the message type.
     *
     * @return the message type.
     */
    Object getType();

    /**
     * Return the message content.
     *
     * @return the message content.
     */
    Object getContent();

}
