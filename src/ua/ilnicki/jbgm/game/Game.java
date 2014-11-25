package ua.ilnicki.jbgm.game;

import ua.ilnicki.jbgm.system.SaveManager;
import ua.ilnicki.jbgm.machine.BrickGameMachine.Parameters;
import ua.ilnicki.jbgm.machine.Field;
import ua.ilnicki.jbgm.machine.Keyboard.KeyboardPasser;
import ua.ilnicki.jbgm.system.BrickGameProcessor;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public interface Game extends BrickGameProcessor
{
    /**
     *
     * @param param
     * @param keyboard
     * @param field
     */
    public void init(Parameters param, KeyboardPasser keyboard, Field field);
    
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
