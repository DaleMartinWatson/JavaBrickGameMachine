package ua.ilnicki.jbgm;

import java.io.File;
import java.io.PrintWriter;
import java.util.LinkedHashSet;
import java.util.Set;
import ua.ilnicki.jbgm.data.DataWriteException;
import ua.ilnicki.jbgm.io.*;
import ua.ilnicki.jbgm.system.ConfigManager;
import ua.ilnicki.jbgm.system.Module;
import ua.ilnicki.jbgm.system.SystemManager;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class BrickGameExecuter
{

    private final SystemManager systemManager;
    private final Set<Module> modules;
    private final TickProvider tp;

    public BrickGameExecuter()
    {
        this.systemManager = new SystemManager(this);
        this.modules = new LinkedHashSet<>();
        tp = new TickProvider(60);
    }

    private void init()
    {
        this.systemManager.init();

        try
        {
            ConfigManager configManager = this.systemManager.getConfigManager();

            Drawer drawer = Drawer.createDrawer(configManager);
            drawer.init(this.systemManager.getMachine(),
                    configManager);
            this.modules.add(drawer);

            KeyReader keyReader = KeyReader.createKeyReader(configManager);
            keyReader.init(this.systemManager.getMachine().getKeyboard(),
                    configManager);
            this.modules.add(keyReader);
            
            this.modules.add(this.systemManager.getProcessManager());

            //TODO: Add sound system implementation.
            this.modules.forEach(m -> m.onLoad());

        }
        catch (Exception ex)
        {
            stop(ex);
        }

    }

    private void run()
    {
        tp.start(tick ->
        {
            this.modules.forEach(m -> m.onTick(tick));
        });
    }

    public void stop()
    {
        this.stop(0);
    }

    public void stop(Exception ex)
    {
        try (PrintWriter pw = new PrintWriter(new File("stacktrace.log")))
        {
            this.stop(1);
            ex.printStackTrace(pw);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        
        ex.printStackTrace();
    }

    private void stop(int i)
    {
        this.modules.forEach(m -> m.onStop());
        this.tp.stop();
        System.exit(i);
    }

    public static void main(String[] args) throws DataWriteException
    {
        BrickGameExecuter jbgm = new BrickGameExecuter();

        jbgm.init();
        jbgm.run();
        jbgm.stop();
    }
}
