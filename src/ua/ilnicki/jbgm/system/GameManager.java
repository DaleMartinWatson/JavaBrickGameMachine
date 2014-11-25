package ua.ilnicki.jbgm.system;

import ua.ilnicki.jbgm.machine.BrickGameMachine;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class GameManager implements BrickGameProcessor
{
    protected BrickGameMachine machine;

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
    
    public void exit()
    {
        
    }  
}
