package ua.ilnicki.jbgm.system;

import ua.ilnicki.jbgm.machine.BrickGameMachine;

/**
 *
 * @author Dmytro
 */
public interface BrickGameProcessor
{
    /**
     *
     * @param machine
     */
    public void init(BrickGameMachine machine);

    /**
     *
     */
    public void onLaunch();
    
    /**
     *
     */
    public void onTick();

    /**
     *
     */
    public void onStop();
}
