package ua.ilnicki.jbgm.io;

import ua.ilnicki.jbgm.data.DataCluster;
import ua.ilnicki.jbgm.machine.Keyboard;
import ua.ilnicki.jbgm.system.ConfigManager;
import ua.ilnicki.jbgm.system.Module;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public interface KeyReader extends Module
{
    public void init(Keyboard keyboard, ConfigManager configManager)  throws Exception;
    
    public static KeyReader createKeyReader(ConfigManager configManager) throws Exception
    {
        DataCluster cluster = configManager.getCluster(KeyReader.class);
        
        return (KeyReader) IoManager.getInstance()
                .getModule(cluster.getStringValue("KeyReader"));
    }
}
