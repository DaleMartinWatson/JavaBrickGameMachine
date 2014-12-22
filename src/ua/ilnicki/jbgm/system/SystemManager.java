package ua.ilnicki.jbgm.system;

import ua.ilnicki.jbgm.BrickGameExecuter;
import ua.ilnicki.jbgm.data.DataProvider;
import ua.ilnicki.jbgm.machine.Machine;
import ua.ilnicki.jbgm.pixelmatrix.PixelMatrixLoader;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class SystemManager
{

    private DataProvider provider;
    private ConfigManager configManager;
    private SaveManager saveManager;
    private ProcessManager processManager;
    private Machine machine;
    private final BrickGameExecuter executer;
    private boolean isInitialized = false;

    public SystemManager(BrickGameExecuter jge)
    {
        this.executer = jge;
    }

    public void init()
    {
        if (!this.isInitialized)
        {
            provider = DataProvider.createDataProvider();;

            if (provider == null)
            {
                this.executer.stop(new Exception("Can`t create Data Provider."));
            }

            this.configManager = new ConfigManager(provider);
            this.saveManager = new SaveManager(provider);

            machine = new Machine();

            this.processManager = new ProcessManager(this);
        }
        this.isInitialized = true;
    }

    public SaveManager getSaveManager()
    {
        return saveManager;
    }

    public ConfigManager getConfigManager()
    {
        return configManager;
    }

    public ProcessManager getProcessManager()
    {
        return processManager;
    }

    public Machine getMachine()
    {
        return machine;
    }

    public PixelMatrixLoader createMatrixLoader(String packageName)
    {
        return PixelMatrixLoader.create(packageName, this.provider);
    }

    public void reset()
    {
        this.executer.reset();
    }
    
    public void stop()
    {
        this.stop(null);
    }

    public void stop(Exception e)
    {
        if(e == null)
        {
            this.executer.stop();
        }
        else
        {
            this.executer.stop(e);
        }
        this.configManager.saveAll();
    }
}
