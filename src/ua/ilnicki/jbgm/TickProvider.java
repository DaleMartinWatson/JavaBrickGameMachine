package ua.ilnicki.jbgm;

import java.util.function.LongConsumer;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
class TickProvider
{
    private int fps;
    
    private boolean isRun; 
    private long tickNumber = 0;

    public TickProvider()
    {
        this(60);
    }
    
    public TickProvider(int fps)
    {
        this.fps = fps;
    }
    
    public void start(LongConsumer tickConsumer)
    {
        this.isRun = true;
        
        while(this.isRun)
        {
            tickConsumer.accept(tickNumber);
            this.tickNumber++;
            Sync.sync(fps);
        }    
    }
    
    public void stop()
    {
        this.isRun = false;
    }
}
