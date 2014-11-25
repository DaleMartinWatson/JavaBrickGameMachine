package ua.ilnicki.jbgm.system;

import ua.ilnicki.jbgm.machine.BrickGameMachine;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class ProcessManager
{
    protected final BrickGameMachine machine;
    private final SystemManager system;

    public ProcessManager(BrickGameMachine machine, SystemManager sm)
    {
        this.machine = machine;
        this.system = sm;
    }

    public BrickGameMachine getMachine()
    {
        return machine;
    }

    public SystemManager getSystem()
    {
        return system;
    }
}
