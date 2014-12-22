package ua.ilnicki.jbgm.machine;

import ua.ilnicki.jbgm.pixelmatrix.Pixel;
import ua.ilnicki.jbgm.pixelmatrix.PixelMatrix;
import ua.ilnicki.jbgm.pixelmatrix.Point;
import ua.ilnicki.jbgm.pixelmatrix.Positionable;

/**
 *
 * @author Dmytro
 */
public final class Screen extends PixelMatrix implements Positionable
{

    private final int width;
    private final int height;
    private Field field;
    private Point position;

    /**
     *
     * @param width
     * @param height
     */
    public Screen(int width, int height)
    {
        super(1, 1);

        this.width = width;
        this.height = height;
    }
    
    /**
     *
     * @param width
     * @param height
     * @param field
     */
    public Screen(int width, int height, Field field)
    {
        this(width, height);
        this.setField(field);
    }
    
    void setField(Field field)
    {
        this.field = field;
        this.position = new Point(0, 0);;
    }

    /**
     *
     * @param y
     * @param x
     * @param value
     */
    @Override
    public void setPixel(int y, int x, Pixel value)
    {
        throw new UnsupportedOperationException("Can`t change state of Screen.");
    }

    /**
     *
     * @param point
     * @param value
     */
    @Override
    public void setPixel(Point point, Pixel value)
    {
        throw new UnsupportedOperationException("Can`t change state of Screen.");
    }

    /**
     *
     * @param point
     * @return
     */
    @Override
    public Pixel getPixel(Point point)
    {
        if (point.getX() >= this.getWidth() || point.getX() < 0 
                || point.getY() >= this.getHeight() || point.getY() < 0)
        {
            throw new IndexOutOfBoundsException(String.format(
                    "Wrong matrix element indexes [%d, %d].",
                    point.getX(), 
                    point.getY()));
        }
        else
        {
            try
            {
                return Pixel.merge(field.getPixel(Point.add(point, this.position)), Pixel.WHITE);
            }
            catch (Exception e)
            {
                return Pixel.WHITE;
            }
        }
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
        return this.getPixel(new Point(x, y));
    }

    /**
     *
     * @return
     */
    @Override
    public int getWidth()
    {
        return this.width;
    }

    /**
     *
     * @return
     */
    @Override
    public int getHeight()
    {
        return this.height;
    }

    /**
     *
     * @return
     */
    @Override
    public int getPositionX()
    {
        return position.getX();
    }
    
    /**
     *
     * @param positionX
     */
    @Override
    public void setPositionX(int positionX)
    {
        if(positionX <= -this.getWidth() || 
                positionX >= this.field.getWidth())
        {
            throw new IllegalArgumentException(String.format(
                    "Wrong X position: %d. Position must be in range from -%d to %d.",
                    positionX,
                    this.getWidth(),
                    this.field.getWidth()));
        }
        else
        {
            this.position.setX(positionX);;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public int getPositionY()
    {
        return position.getY();
    }

    /**
     *
     * @param positionY
     */
    @Override
    public void setPositionY(int positionY)
    {
        if(positionY <= -this.getHeight() ||
                positionY >= this.field.getHeight())
        {
            throw new IllegalArgumentException(String.format(
                    "Wrong Y position: %d. Position must be in range from -%d to %d.",
                    positionY,
                    this.getHeight(),
                    this.field.getHeight()));
        }
        else
        {
            this.position.setY(positionY);;
        }
    }
    
    /**
     *
     * @param positionY
     * @param positionX
     */
    @Override
    public void setPosition(int positionY, int positionX)
    {
        this.setPositionY(positionY);
        this.setPositionX(positionX);
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
}
