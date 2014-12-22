package ua.ilnicki.jbgm.system.processors;

import ua.ilnicki.jbgm.data.DataCluster;
import ua.ilnicki.jbgm.machine.BoolParameter;
import ua.ilnicki.jbgm.machine.IntParameter;
import ua.ilnicki.jbgm.machine.Keyboard;
import ua.ilnicki.jbgm.machine.Keyboard.SysKey;
import ua.ilnicki.jbgm.machine.Machine;
import ua.ilnicki.jbgm.system.BrickGameProcessor;
import ua.ilnicki.jbgm.system.ProcessManager;
import ua.ilnicki.jbgm.system.SystemManager;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class SysKeysProcessor implements BrickGameProcessor
{
    private Keyboard keyboard;
    private IntParameter volume;
    private BoolParameter pause;
    private DataCluster saveCluster;
    private SystemManager systemManager;
    
    @Override
    public void init(ProcessManager procesManager, SystemManager systemManager)
    {
        Machine machine = systemManager.getMachine();
        
        this.keyboard = machine.getKeyboard();
        this.volume = machine.volume;
        this.pause = machine.pause;
        this.saveCluster = systemManager.getConfigManager().getCluster(this);
        this.systemManager = systemManager;
    }

    @Override
    public void onLoad()
    {
        if(this.saveCluster.valueExists("volume", Integer.class))
        {
            this.volume.set(this.saveCluster.getIntValue("volume"));
        }
    }

    @Override
    public void onTick(long tick)
    {
        if(this.keyboard.sysKeyDownTicksCount(SysKey.SOUND) == 0)
        {
            this.volume.inc();
        }
        
        if(this.keyboard.sysKeyDownTicksCount(SysKey.START) == 0)
        {
            this.pause.toggle();
        }
        
        if(this.keyboard.isSysKeyDown(SysKey.RESET))
        {
            this.systemManager.reset();
        }
        
        if(this.keyboard.isSysKeyDown(SysKey.ONOFF))
        {
            this.systemManager.stop();
        }
    }

    @Override
    public void onStop()
    {
        this.saveCluster.putValue("volume", this.volume.get());
    }   
}
