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
    @SuppressWarnings("FieldNameHidesFieldInSuperclass")
    private PixelMatrix pixelMatrix;
    
    public Layer(PixelMatrix pm)
    {
        super(1, 1);
        this.position = new Point(0, 0);
        this.pixelMatrix = pm;
    }
    
    public Layer(int width, int height)
    {
        this(new PixelMatrix(width, height));
    }

    @Override
    public int getWidth()
    {
        return pixelMatrix.getWidth();
    }

    @Override
    public int getHeight()
    {
        return pixelMatrix.getHeight();
    }

    @Override
    public void setPixel(int x, int y, Pixel value)
    {
        this.pixelMatrix.setPixel(x - this.position.getX(), y - this.position.getY(), value);
    }

    @Override
    public Pixel getPixel(int x, int y)
    {
        try
        {
            return this.pixelMatrix.getPixel(x - this.position.getX(), y - this.position.getY());
        }
        catch(Exception e)
        {
            return null;
        }
    }

    @Override
    public Pixel getPixel(Point point)
    {
        return this.getPixel(point.getX(), point.getY());
    }

    @Override
    public void setPixel(Point point, Pixel value)
    {
        this.setPixel(point.getX(), point.getY(), value);
    }

    @Override
    public String toString()
    {
        return pixelMatrix.toString();
    }
    
    @Override
    public int getPositionX()
    {
        return this.position.getX();
    }
        
    @Override
    public void setPositionX(int positionX)
    {
        this.position.setX(positionX);
    }
    
    @Override
    public int getPositionY()
    {
        return this.position.getY();
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
    
    public PixelMatrix getPixelMatrix()
    {
        return this.pixelMatrix;
    }
    
    public void setPixelMatrix(PixelMatrix pm)
    {
        if(pm != null)
            this.pixelMatrix = pm;
        else
            this.pixelMatrix = new PixelMatrix(1, 1);
    }
}
