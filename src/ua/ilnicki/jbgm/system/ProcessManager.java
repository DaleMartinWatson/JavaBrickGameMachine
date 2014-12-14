package ua.ilnicki.jbgm.system;

import java.util.LinkedHashSet;
import java.util.Set;
import ua.ilnicki.jbgm.system.processors.SysKeysProcessor;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public final class ProcessManager implements Module
{
    private final Set<BrickGameProcessor> processors;
    private final SystemManager system;

    public ProcessManager(SystemManager sm)
    {
        this.system = sm;
        this.processors = new LinkedHashSet<>();
        
        init();
    }
    
    public void init()
    {
        this.processors.add(new SysKeysProcessor());
        this.processors.add(new GameManager());
        
        this.processors.forEach(p -> p.init(this, this.system));
    }

    @Override
    public void onLoad()
    {
        this.processors.forEach(p -> p.onLoad());
    }

    @Override
    public void onTick(long tick)
    {
        this.processors.forEach(p -> p.onTick(tick));
    }

    @Override
    public void onStop()
    {
        this.processors.forEach(p -> p.onStop());
    }
}
