package ua.ilnicki.jbgm.system;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import ua.ilnicki.jbgm.data.DataCluster;
import ua.ilnicki.jbgm.game.Game;
import ua.ilnicki.jbgm.game.GameInfo;
import ua.ilnicki.jbgm.machine.Field;
import ua.ilnicki.jbgm.machine.Machine;
import ua.ilnicki.jbgm.system.processors.DefaultGameLauncher;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class GameManager implements BrickGameProcessor
{

    private Module launcher;
    private Field launcherField;

    private ProcessManager processManager;
    private SystemManager systemManager;
    private Module currentGame;

    private ArrayList<GameInfo> gameInfoList;

    private String workingpath;

    @Override
    public void init(ProcessManager procesManager, SystemManager systemManager)
    {
        this.processManager = procesManager;
        this.systemManager = systemManager;

        DataCluster mainConfig = this.systemManager.getConfigManager().getMainCluster();

        if (mainConfig.valueExists("WorkingPath"))
        {
            this.workingpath = mainConfig.getStringValue("WorkingPath");
        } else
        {
            this.workingpath = ".";
        }

        DataCluster config = this.systemManager.getConfigManager().getCluster(this);

        String[] gamesList = (String[]) config.getValue("GamesList");

        this.gameInfoList = new ArrayList<>();

        for (String gameName : gamesList)
        {
            GameInfo gameInfo = this.loadGameInfo(gameName);

            if (gameInfo != null)
                this.gameInfoList.add(gameInfo);
        }

        if (config.valueExists("DefaultLauncher"))
        {
            try
            {
                this.launcher = (BrickGameProcessor) Class.forName(config.getStringValue("DefaultLauncher"))
                        .newInstance();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex)
            {

            }
        }

        this.launcher = this.launcher == null ? new DefaultGameLauncher() : this.launcher;

        ((DefaultGameLauncher) this.launcher).init(this, systemManager);
        this.launcherField = this.systemManager.getMachine().getField();
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
        if (!this.systemManager.getMachine().pause.get()
                || this.currentGame == this.launcher) //If game not paused of current proces is launcher
        {
            this.systemManager.getMachine().pause.set(false);
            this.currentGame.onTick(tick);
        }
    }

    @Override
    public void onStop()
    {
        this.currentGame.onStop();
        if (this.currentGame != this.launcher)
        {
            this.launcher.onStop();
        }
    }

    /**
     *
     * @return
     */
    public List<GameInfo> getGameInfoList()
    {
        return gameInfoList;
    }

    /**
     *
     * @param gameInfo
     * @param argument
     */
    public void launchGame(GameInfo gameInfo, int argument)
    {
        try
        {
            Game game = (Game) gameInfo.getGameClass().newInstance();

            this.systemManager.getMachine().recreateField(gameInfo.getBufferWidth(),
                    gameInfo.getBufferHeight());

            Machine machine = this.systemManager.getMachine();
            game.init(this,
                    machine.getField(),
                    machine.getHelper(),
                    machine.getKeyboardPasser(),
                    machine.getParameters(),
                    this.systemManager.getSaveManager(),
                    argument);

            this.currentGame = game;

            this.currentGame.onLoad();

        } catch (InstantiationException | IllegalAccessException ex)
        {
            this.systemManager.stop(ex);
        }
    }

    /**
     *
     */
    public void exitGame()
    {
        this.currentGame.onStop();
        this.currentGame = this.launcher;
        this.systemManager.getMachine().setField(this.launcherField);
    }

    private GameInfo loadGameInfo(String gameName)
    {
        GameInfo gi;

        try
        {
            gi = (GameInfo) Class.forName(gameName + "Info").newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex)
        {
            try
            {
                URL[] jarFile = new URL[]
                {
                    new File(this.workingpath + "/games/" + gameName + ".jar").toURI().toURL()
                };

                ClassLoader urlCl = new URLClassLoader(jarFile);

                gi = (GameInfo) urlCl.loadClass(gameName + "Info").newInstance();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | MalformedURLException ex1)
            {
                gi = null;
            }
        }

        return gi;
    }
}
