package ua.ilnicki.jbgm.system.processors;

import ua.ilnicki.jbgm.machine.BrickGameBoolParameter;
import ua.ilnicki.jbgm.machine.BrickGameIntParameter;
import ua.ilnicki.jbgm.machine.Keyboard;
import ua.ilnicki.jbgm.machine.Keyboard.SysKey;
import ua.ilnicki.jbgm.system.BrickGameProcessor;
import ua.ilnicki.jbgm.data.DataCluster;
import ua.ilnicki.jbgm.system.ProcessManager;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class SysKeysProcessor implements BrickGameProcessor
{
    private Keyboard keyboard;
    private BrickGameIntParameter volume;
    private BrickGameBoolParameter pause;
    private DataCluster saveCluster;
    
    @Override
    public void init(ProcessManager pm)
    {
        this.keyboard = pm.getMachine().getKeyboard();
        this.volume = pm.getMachine().getParameters().volume;
        this.pause = pm.getMachine().pause;
        this.saveCluster = pm.getSystem().getSaveManager().getCluster(this);
    }

    @Override
    public void onLaunch()
    {
        if(this.saveCluster.valueExists("volume", Integer.class))
        {
            this.volume.set(this.saveCluster.getIntValue("volume"));
        }
    }

    @Override
    public void onTick()
    {
        if(this.keyboard.isSysKeyDown(SysKey.SOUND))
        {
            this.volume.inc();
        }
        
        if(this.keyboard.isSysKeyDown(SysKey.START))
        {
            this.pause.toggle();
        }
    }

    @Override
    public void onStop()
    {
        this.saveCluster.setValue("volume", this.volume.get());
    }   
}
