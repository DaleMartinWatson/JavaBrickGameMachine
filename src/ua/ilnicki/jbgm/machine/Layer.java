package ua.ilnicki.jbgm.machine;

import ua.ilnicki.jbgm.pixelmatrix.Pixel;
import ua.ilnicki.jbgm.pixelmatrix.PixelMatrix;
import ua.ilnicki.jbgm.pixelmatrix.Point;
import ua.ilnicki.jbgm.pixelmatrix.Positionable;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public final class Layer extends PixelMatrix implements Positionable
{
    private Point position;
    
    public Layer(PixelMatrix pm)
    {
        super(pm);
        
        this.position = new Point(0, 0);
    }
    
    public Layer(int width, int height)
    {
        super(width, height);
        
        this.position = new Point(0, 0);
    }

    @Override
    public void setPixel(int x, int y, Pixel value)
    {
        super.setPixel(x - this.position.getX(), y - this.position.getY(), value);
    }

    @Override
    public Pixel getPixel(int x, int y)
    {
        try
        {
            return super.getPixel(x - this.position.getX(), y - this.position.getY());
        }
        catch(Exception e)
        {
            return null;
        }
    }
    
    @Override
    public int getPositionX()
    {
        return position.getX();
    }
        
    @Override
    public void setPositionX(int positionX)
    {
        this.position.setX(positionX);
    }
    
    @Override
    public int getPositionY()
    {
        return position.getY();
    }

    @Override
    public void setPositionY(int positionY)
    {
        this.position.setY(positionY);
    }
    
    @Override
    public void setPosition(int positionX, int positionY)
    {
        this.setPositionX(positionX);
        this.setPositionY(positionY);
    }
    
    @Override
    public void setPositionPoint(Point point)
    {
        this.position = point;
    }

    @Override
    public Point getPositionPoint()
    {
        return this.position;
    }
}
