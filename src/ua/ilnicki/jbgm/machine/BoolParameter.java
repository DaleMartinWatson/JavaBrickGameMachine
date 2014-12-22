package ua.ilnicki.jbgm.machine;

import java.io.Serializable;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class BoolParameter implements Serializable
{
    private boolean value;

    /**
     *
     */
    public BoolParameter()
    {
        this(false);
    }
    
    /**
     *
     * @param value
     */
    public BoolParameter(boolean value)
    {
        this.value = value;
    }

    /**
     *
     * @return
     */
    public Boolean get()
    {
        return this.value;
    }

    /**
     *
     * @param value
     */
    public void set(Boolean value)
    {
        this.value = value;
    }
    
    /**
     *
     */
    public void toggle()
    {
        this.value ^= true;
    }
}
