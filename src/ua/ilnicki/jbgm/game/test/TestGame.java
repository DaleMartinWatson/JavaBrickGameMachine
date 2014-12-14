package ua.ilnicki.jbgm.game.test;

import ua.ilnicki.jbgm.game.Game;
import ua.ilnicki.jbgm.machine.Field;
import ua.ilnicki.jbgm.machine.Keyboard.CtrlKey;
import ua.ilnicki.jbgm.machine.Keyboard.KeyboardPasser;
import ua.ilnicki.jbgm.machine.Machine;
import ua.ilnicki.jbgm.machine.Machine.Parameters;
import ua.ilnicki.jbgm.pixelmatrix.PixelMatrix;
import ua.ilnicki.jbgm.system.GameManager;
import ua.ilnicki.jbgm.system.SaveManager;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class TestGame implements Game
{
    private KeyboardPasser keyboardPasser;

    @Override
    public void init(GameManager gameManager,
            Field field,
            PixelMatrix helper,
            KeyboardPasser keyboardPasser,
            Parameters parameters,
            SaveManager saveManager)
    {
        this.keyboardPasser = keyboardPasser;
    }

    @Override
    public void onLoad()
    {

    }

    @Override
    public void onTick(long tick)
    {
       // System.out.println(tick + " " + this.keyboardPasser.isKeyDown(CtrlKey.UP));
    }

    @Override
    public void onStop()
    {

    }

    @Override
    public void save()
    {

    }

    @Override
    public void recover()
    {

    }
}
