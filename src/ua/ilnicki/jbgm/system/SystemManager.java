package ua.ilnicki.jbgm.system;

import ua.ilnicki.jbgm.data.DataProvider;
import ua.ilnicki.jbgm.data.DataWriteException;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class SystemManager
{
    private final SaveManager saveManager;
    private final ConfigManager configManager;

    public SystemManager()
    {
        DataProvider provider = null;
        try
        {
            provider = DataProvider.createDataProvider();
        } 
        catch (DataWriteException | ClassNotFoundException |
               InstantiationException | IllegalAccessException ex)
        {
            System.exit(1);
        }
        
        this.configManager = new ConfigManager(provider);
        this.saveManager = new SaveManager(provider);
    }

    public SaveManager getSaveManager()
    {
        return saveManager;
    }

    public ConfigManager getConfigManager()
    {
        return configManager;
    }

}
