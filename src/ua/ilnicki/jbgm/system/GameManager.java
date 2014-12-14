package ua.ilnicki.jbgm.system;

import ua.ilnicki.jbgm.data.DataCluster;
import ua.ilnicki.jbgm.game.Game;
import ua.ilnicki.jbgm.machine.Field;
import ua.ilnicki.jbgm.machine.Keyboard;
import ua.ilnicki.jbgm.machine.Machine;
import ua.ilnicki.jbgm.pixelmatrix.PixelMatrix;
import ua.ilnicki.jbgm.system.processors.DefaultGameLauncher;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class GameManager implements BrickGameProcessor
{
    private Module launcher;
    private ProcessManager processManager;
    private SystemManager systemManager;
    private Module currentGame;
    

    @Override
    public void init(ProcessManager procesManager, SystemManager systemManager)
    {
        this.processManager = procesManager;
        this.systemManager = systemManager;
        
        DataCluster cluster = this.systemManager.getConfigManager().getCluster(this);
        
        if(cluster.valueExists("DefaultLauncher"))
        {
            try
            {
                this.launcher = (BrickGameProcessor) Class.forName(cluster.getStringValue("DefaultLauncher"))
                        .newInstance();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex)
            {
                
            }
        }
        
        this.launcher = this.launcher == null ? new DefaultGameLauncher() : this.launcher;
        
        ((DefaultGameLauncher) this.launcher).init(this, systemManager);
    }

    @Override
    public void onLoad()
    {
        this.launcher.onLoad();
        this.currentGame = this.launcher;
    }

    @Override
    public void onTick(long tick)
    {
        if(!this.systemManager.getMachine().pause.get()) //If game not paused
            this.currentGame.onTick(tick);
    }

    @Override
    public void onStop()
    {
        this.currentGame.onStop();
        if(this.currentGame != this.launcher)
        {
            this.launcher.onStop();
        }
    }
    
    public void launchGame(String name)
    {
        try
        {
            Machine machine = this.systemManager.getMachine();
            
            Game game = (Game) Class.forName(name).newInstance();
            game.init(this, 
                     machine.getField(), 
                     machine.getHelper(), 
                     machine.getKeyboardPasser(), 
                     machine.getParameters(), 
                     this.systemManager.getSaveManager());
            
            this.currentGame = game;
            
            this.currentGame.onLoad();
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex)
        {
            this.systemManager.stop(ex);
        }
    }
    
    public void exitGame()
    {
        this.currentGame.onStop();
        this.currentGame = this.launcher;
    }
}
