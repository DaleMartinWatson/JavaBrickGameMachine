package ua.ilnicki.jbgm.system;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public interface Module
{
    /**
     *
     */
    public void onLoad();
    
    /**
     *
     * @param tick
     */
    public void onTick(long tick);

    /**
     *
     */
    public void onStop();
}
