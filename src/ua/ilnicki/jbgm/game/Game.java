package ua.ilnicki.jbgm.game;

import ua.ilnicki.jbgm.machine.Field;
import ua.ilnicki.jbgm.machine.Keyboard.KeyboardPasser;
import ua.ilnicki.jbgm.machine.Machine.Parameters;
import ua.ilnicki.jbgm.pixelmatrix.PixelMatrix;
import ua.ilnicki.jbgm.system.Module;
import ua.ilnicki.jbgm.system.GameManager;
import ua.ilnicki.jbgm.system.SaveManager;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public interface Game extends Module
{
    /**
     *
     * @param gameManager
     * @param field
     * @param helper
     * @param keyboardPasser
     * @param parameters
     * @param saveManager
     * @param arg
     */
    public void init(GameManager gameManager, 
                     Field field, 
                     PixelMatrix helper, 
                     KeyboardPasser keyboardPasser, 
                     Parameters parameters, 
                     SaveManager saveManager,
                     int arg);
    
    /**
     *
     */
    public void save();
    
    /**
     *
     */
    public void recover();
}
