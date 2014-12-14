package ua.ilnicki.jbgm.game.panzer;

import ua.ilnicki.jbgm.pixelmatrix.Point;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class Bullet extends Entity
{

    private final Direction direction;
    private final int speed;

    public Bullet(int posY, int posX, Direction direction, int speed)
    {
        super(posX, posY);
        this.direction = direction;
        this.speed = speed;
    }
    
    public Bullet(Point point, Direction direction, int speed)
    {
        super(point);
        this.direction = direction;
        this.speed = speed;
    }
    
    public Bullet(int posY, int posX, Direction direction)
    {
        this(posY, posX, direction, 2);
    }
    
    public Bullet(Point point, Direction direction)
    {
        this(point, direction, 2);
    }

    public Direction getDirection()
    {
        return direction;
    }

    public int getSpeed()
    {
        return speed;
    }
}
