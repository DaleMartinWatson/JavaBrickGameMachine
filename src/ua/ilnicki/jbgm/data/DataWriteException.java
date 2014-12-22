package ua.ilnicki.jbgm.data;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class DataWriteException extends Exception
{

    /**
     *
     */
    public DataWriteException()
    {
    }

    /**
     *
     * @param message
     */
    public DataWriteException(String message)
    {
        super(message);
    }

    /**
     *
     * @param message
     * @param cause
     */
    public DataWriteException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     *
     * @param cause
     */
    public DataWriteException(Throwable cause)
    {
        super(cause);
    }
    
}
