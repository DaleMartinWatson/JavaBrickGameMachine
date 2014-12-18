package ua.ilnicki.jbgm.system.processors;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import ua.ilnicki.jbgm.data.DataCluster;
import ua.ilnicki.jbgm.game.GameInfo;
import ua.ilnicki.jbgm.machine.Keyboard;
import ua.ilnicki.jbgm.machine.Keyboard.CtrlKey;
import ua.ilnicki.jbgm.system.GameManager;
import ua.ilnicki.jbgm.system.Module;
import ua.ilnicki.jbgm.system.SystemManager;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class DefaultGameLauncher implements Module
{

    private GameManager gameManager;
    private SystemManager systemManager;
    private ArrayList<GameInfo> gameInfoList;
    
    private DataCluster config;
    private Keyboard keyboard;

    public void init(GameManager gameManager, SystemManager systemManager)
    {
        this.gameManager = gameManager;
        this.systemManager = systemManager;
        
        this.config = this.systemManager.getConfigManager().getCluster(this);
        this.keyboard = this.systemManager.getMachine().getKeyboard();
        
        String[] gamesList = (String[]) this.config.getValue("GamesList");
        
        this.gameInfoList = new ArrayList<>();
        
        for(String gameName : gamesList)
        {
            GameInfo gameInfo = this.loadGameInfo(gameName);
            
            if(gameInfo != null)
                this.gameInfoList.add(gameInfo);
        }
    }

    @Override
    public void onLoad()
    {
        //PixelMatrixLoader loader = new PixelMatrixLoader("", null);
        //systemManager.getMachine().getField().getLayers().add(new Layer(null));
    }

    private int selectedGame = 0;
    
    @Override
    public void onTick(long tick)
    {
        if (this.keyboard.isCtrlKeyDown(CtrlKey.ROTATE))
            this.selectedGame++;
    }

    @Override
    public void onStop()
    {

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
                    new File("./games/" + gameName + ".jar").toURI().toURL()
                };

                ClassLoader urlCl = new URLClassLoader(jarFile);

                gi = (GameInfo) urlCl.loadClass(gameName + "Info").newInstance();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException| MalformedURLException ex1)
            {
                gi = null;
            }
        }

        return gi;
    }
}
