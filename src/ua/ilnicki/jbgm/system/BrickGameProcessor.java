package ua.ilnicki.jbgm.system;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public interface BrickGameProcessor extends Module
{
    /**
     *
     * @param procesManager
     * @param systemManager
     */
    public void init(ProcessManager procesManager, SystemManager systemManager);
}
