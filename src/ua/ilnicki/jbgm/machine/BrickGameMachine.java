package ua.ilnicki.jbgm.machine;

import ua.ilnicki.jbgm.util.BrickGameParameter;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class BrickGameMachine
{

    /**
     *
     */
    public final BrickGameParameter score = new BrickGameParameter(999999);

    /**
     *
     */
    public final BrickGameParameter hiscore = new BrickGameParameter(999999);

    /**
     *
     */
    public final BrickGameParameter speed = new BrickGameParameter(9);

    /**
     *
     */
    public final BrickGameParameter level = new BrickGameParameter(9);

    /**
     *
     */
    public final BrickGameParameter volume = new BrickGameParameter(3);

    /**
     *
     */
    public boolean pause = false;
    
}
