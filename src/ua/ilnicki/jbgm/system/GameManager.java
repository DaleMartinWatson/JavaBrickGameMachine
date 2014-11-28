package ua.ilnicki.jbgm.system;

import ua.ilnicki.jbgm.machine.BrickGameIntParameter;
import ua.ilnicki.jbgm.machine.BrickGameMachine;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class GameManager implements BrickGameProcessor
{
    protected BrickGameMachine machine;
    private final BrickGameIntParameter parameter = new BrickGameIntParameter(0, 99);

    @Override
    public void init(ProcessManager pm)
    {
        this.machine = pm.getMachine();
    }

    @Override
    public void onLaunch()
    {

    }

    @Override
    public void onTick()
    {

    }

    @Override
    public void onStop()
    {

    }

    public BrickGameIntParameter getParameter()
    {
        return parameter;
    }
    
    public void exit()
    {
        
    }  
}
