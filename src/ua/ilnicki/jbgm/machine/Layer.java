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
    
    /**
     *
     * @param pm
     */
    public Layer(PixelMatrix pm)
    {
        super(1, 1);
        this.position = new Point(0, 0);
        this.pixelMatrix = pm;
    }
    
    /**
     *
     * @param width
     * @param height
     */
    public Layer(int width, int height)
    {
        this(new PixelMatrix(width, height));
    }

    /**
     *
     * @return
     */
    @Override
    public int getWidth()
    {
        return pixelMatrix.getWidth();
    }

    /**
     *
     * @return
     */
    @Override
    public int getHeight()
    {
        return pixelMatrix.getHeight();
    }

    /**
     *
     * @param x
     * @param y
     * @param value
     */
    @Override
    public void setPixel(int x, int y, Pixel value)
    {
        this.pixelMatrix.setPixel(x - this.position.getX(), y - this.position.getY(), value);
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
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

    /**
     *
     * @param point
     * @return
     */
    @Override
    public Pixel getPixel(Point point)
    {
        return this.getPixel(point.getX(), point.getY());
    }

    /**
     *
     * @param point
     * @param value
     */
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
    
    /**
     *
     * @return
     */
    @Override
    public int getPositionX()
    {
        return this.position.getX();
    }
        
    /**
     *
     * @param positionX
     */
    @Override
    public void setPositionX(int positionX)
    {
        this.position.setX(positionX);
    }
    
    /**
     *
     * @return
     */
    @Override
    public int getPositionY()
    {
        return this.position.getY();
    }

    /**
     *
     * @param positionY
     */
    @Override
    public void setPositionY(int positionY)
    {
        this.position.setY(positionY);
    }
    
    /**
     *
     * @param positionX
     * @param positionY
     */
    @Override
    public void setPosition(int positionX, int positionY)
    {
        this.setPositionX(positionX);
        this.setPositionY(positionY);
    }
    
    /**
     *
     * @param point
     */
    @Override
    public void setPositionPoint(Point point)
    {
        this.position = point;
    }

    /**
     *
     * @return
     */
    @Override
    public Point getPositionPoint()
    {
        return this.position;
    }
    
    /**
     *
     * @return
     */
    public PixelMatrix getPixelMatrix()
    {
        return this.pixelMatrix;
    }
    
    /**
     *
     * @param pm
     */
    public void setPixelMatrix(PixelMatrix pm)
    {
        if(pm != null)
            this.pixelMatrix = pm;
        else
            this.pixelMatrix = new PixelMatrix(1, 1);
    }
}
