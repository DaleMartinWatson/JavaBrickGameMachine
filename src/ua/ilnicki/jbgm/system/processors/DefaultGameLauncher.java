package ua.ilnicki.jbgm.system.processors;

import ua.ilnicki.jbgm.machine.Keyboard;
import ua.ilnicki.jbgm.pixelmatrix.Pixel;
import ua.ilnicki.jbgm.system.GameManager;
import ua.ilnicki.jbgm.system.Module;
import ua.ilnicki.jbgm.system.ProcessManager;
import ua.ilnicki.jbgm.system.SystemManager;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class DefaultGameLauncher implements Module
{
    private GameManager gameManager;
    private SystemManager systemManager;
    
    public void init(GameManager gameManager, SystemManager systemManager)
    {
        this.gameManager = gameManager;
        this.systemManager = systemManager;
    }

    @Override
    public void onLoad()
    {
        systemManager.getMachine().getField().setPixel(0, 0, Pixel.BLACK);
    }

    @Override
    public void onTick(long tick)
    {
        if(this.systemManager.getMachine().getKeyboard().isCtrlKeyDown(Keyboard.CtrlKey.UP))
            this.gameManager.launchGame("ua.ilnicki.jbgm.game.panzer.PanzerGame");
        
        if(this.systemManager.getMachine().getKeyboard().isCtrlKeyDown(Keyboard.CtrlKey.DOWN))
            this.gameManager.launchGame("ua.ilnicki.jbgm.game.test.TestGame");
    }

    @Override
    public void onStop()
    {
        
    }
}
