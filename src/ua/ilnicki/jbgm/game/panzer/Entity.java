package ua.ilnicki.jbgm.game.panzer;

import ua.ilnicki.jbgm.pixelmatrix.MatrixUtils;
import ua.ilnicki.jbgm.pixelmatrix.Pixel;
import ua.ilnicki.jbgm.pixelmatrix.PixelMatrix;
import ua.ilnicki.jbgm.pixelmatrix.Point;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class Entity
{
    private final Point pos;
    
    public Entity(int posX, int posY)
    {
        this(new Point(posX, posY));
    }
    
    public Entity(Point pos)
    {
        this.pos = pos;
    }

    public int getPosY()
    {
        return pos.getY();
    }

    public int getPosX()
    {
        return pos.getX();
    }

    public void setPosY(int posY)
    {
        this.pos.setY(posY);
    }

    public void setPosX(int posX)
    {
        this.pos.setX(posX);
    }

    public Point getPos()
    {
        return pos;
    }

    public void setPos(Point pos)
    {
        this.pos.setY(pos.getY());
        this.pos.setX(pos.getX());
    }
    
    public void setPos(int posY, int posX)
    {
        this.setPosY(posY);
        this.setPosX(posX);
    }
    
    private final PixelMatrix sprite = MatrixUtils.makeFromArray(new Pixel[][]{{Pixel.BLACK}});
    
    public PixelMatrix getSprite()
    {
        return this.sprite;
    }
    
    public boolean isCollide(int y, int x)
    {
        return this.pos.getY() == y && this.pos.getX() == x;
    }
}
