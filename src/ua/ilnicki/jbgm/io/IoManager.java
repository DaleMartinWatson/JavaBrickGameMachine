package ua.ilnicki.jbgm.io;

import java.util.HashMap;
import ua.ilnicki.jbgm.system.Module;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public final class IoManager
{
    private final HashMap<Class, Module> modules;
    
    private IoManager()
    {
        this.modules = new HashMap<>();
    }
    
    /**
     *
     * @param moduleName
     * @return
     * @throws Exception
     */
    public Module getModule(String moduleName) throws Exception
    {
        if(moduleName != null)
        {
            Module module;
            Class moduleType = Class.forName(moduleName);
            
            module = modules.get(moduleType);
            
            if(module == null)
            {
                module = (Module) moduleType.newInstance();
                this.modules.put(moduleType, module);
            }
            
            return module;
        }
        else
        {
            throw new Exception("Null module name.");
        }
    }
    
    /**
     *
     * @return
     */
    protected static IoManager getInstance()
    {
        return IoManagerHolder.INSTANCE;
    }
    
    private static class IoManagerHolder
    {
        private static final IoManager INSTANCE = new IoManager();
    }
}
