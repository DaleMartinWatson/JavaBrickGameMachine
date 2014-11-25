package ua.ilnicki.jbgm.system;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public interface BrickGameProcessor
{
    /**
     *
     * @param pm
     */
    public void init(ProcessManager pm);

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
