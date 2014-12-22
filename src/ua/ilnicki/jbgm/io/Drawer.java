package ua.ilnicki.jbgm.io;

import ua.ilnicki.jbgm.data.DataCluster;
import ua.ilnicki.jbgm.machine.Machine;
import ua.ilnicki.jbgm.system.ConfigManager;
import ua.ilnicki.jbgm.system.Module;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public interface Drawer extends Module
{

    /**
     *
     * @param machine
     * @param configManager
     * @throws Exception
     */
    public void init(Machine machine, ConfigManager configManager) throws Exception;
    
    /**
     *
     * @param configManager
     * @return
     * @throws Exception
     */
    public static Drawer createDrawer(ConfigManager configManager) throws Exception
    {
        DataCluster cluster = configManager.getCluster(Drawer.class);
        
        return (Drawer) IoManager.getInstance()
                .getModule(cluster.getStringValue("Drawer"));
    }
}
