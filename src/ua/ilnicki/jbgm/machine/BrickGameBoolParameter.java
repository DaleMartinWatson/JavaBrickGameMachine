package ua.ilnicki.jbgm.machine;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class BrickGameBoolParameter
{
    private boolean value;

    public BrickGameBoolParameter()
    {
        this(false);
    }
    
    public BrickGameBoolParameter(boolean value)
    {
        this.value = value;
    }

    public Boolean get()
    {
        return this.value;
    }

    public void set(Boolean value)
    {
        this.value = value;
    }
    
    public void toggle()
    {
        this.value ^= true;
    }
}
