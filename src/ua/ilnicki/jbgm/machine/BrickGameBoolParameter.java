package ua.ilnicki.jbgm.machine;

/**
 *
 * @author Dmytro
 */
public class BrickGameBoolParameter implements BrickGameParameter<Boolean>
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
    
    @Override
    public Boolean get()
    {
        return this.value;
    }

    @Override
    public void set(Boolean value)
    {
        this.value = value;
    }
    
    public void toggle()
    {
        this.value ^= true;
    }
}
