package ua.ilnicki.jbgm.data;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class DataWriteException extends Exception
{

    public DataWriteException()
    {
    }

    public DataWriteException(String message)
    {
        super(message);
    }

    public DataWriteException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public DataWriteException(Throwable cause)
    {
        super(cause);
    }
    
}
