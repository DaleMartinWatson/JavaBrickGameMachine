package ua.ilnicki.jbgm.system.processors;

import ua.ilnicki.jbgm.machine.BrickGameIntParameter;
import ua.ilnicki.jbgm.machine.Keyboard;
import ua.ilnicki.jbgm.machine.Keyboard.SysKey;
import ua.ilnicki.jbgm.system.BrickGameProcessor;
import ua.ilnicki.jbgm.system.ProcessManager;
import ua.ilnicki.jbgm.system.SaveManager.SaveCluster;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class VolumeProcessor implements BrickGameProcessor
{
    private Keyboard keyboard;
    private BrickGameIntParameter volume;
    private SaveCluster saveCluster;
    
    @Override
    public void init(ProcessManager pm)
    {
        this.keyboard = pm.getMachine().getKeyboard();
        this.volume = pm.getMachine().getParameters().volume;
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
    }

    @Override
    public void onStop()
    {
        this.saveCluster.setValue("volume", this.volume.get());
    }   
}
