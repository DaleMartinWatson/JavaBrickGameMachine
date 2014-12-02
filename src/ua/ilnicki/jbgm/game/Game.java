package ua.ilnicki.jbgm.game;

import ua.ilnicki.jbgm.system.BrickGameProcessor;
import ua.ilnicki.jbgm.system.GameManager;
import ua.ilnicki.jbgm.system.SaveManager;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public interface Game extends BrickGameProcessor
{
    /**
     *
     * @param gm
     */
    public void init(GameManager gm);
    
    /**
     *
     * @param sm
     */
    public void save(SaveManager sm);
    
    /**
     *
     * @param sm
     */
    public void recover(SaveManager sm);
}
