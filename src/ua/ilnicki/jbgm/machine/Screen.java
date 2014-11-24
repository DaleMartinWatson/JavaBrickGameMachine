package ua.ilnicki.jbgm.machine;

import ua.ilnicki.jbgm.pixelmatrix.PixelMatrix;

/**
 *
 * @author Dmytro
 */
public class Screen extends PixelMatrix
{

    private final int height;
    private final int width;
    private final Field field;
    private int positionX = 0;
    private int positionY = 0;

    public Screen(int height, int width, Field field)
    {
        super(0, 0);

        this.height = height;
        this.width = width;
        this.field = field;
    }

    @Override
    public void setPixel(int y, int x, Pixel value)
    {
        throw new UnsupportedOperationException("Can`t change state of Screen.");
    }

    @Override
    public Pixel getPixel(int y, int x)
    {
        if (y >= this.height || y < 0 || x >= this.height || x < 0)
        {
            throw new IndexOutOfBoundsException(String.format("Wrong matrix element indexes [%d, %d]", y, x));
        }
        else
        {
            try
            {
                return field.getPixel(y, x);
            } catch (Exception e)
            {
                return null;
            }
        }
    }

    @Override
    public int getWidth()
    {
        return this.width;
    }

    @Override
    public int getHeight()
    {
        return this.height;
    }
    
    @Override
    public void clear()
    {
        throw new UnsupportedOperationException("Can`t clear Screen.");
    }

    public int getPositionX()
    {
        return positionX;
    }

    public int getPositionY()
    {
        return positionY;
    }

    public void setPositionY(int positionY)
    {
        if(positionY < 0 || positionY >= this.field.getHeight())
        {
            throw new IllegalArgumentException(String.format(
                    "Wrong position: %d. Position must be in range from 0 to %d.",
                    positionY, 
                    this.field.getHeight()));
        }
        else
        {
            this.positionY = positionY;
        }
    }
    
    public void setPositionX(int positionX)
    {
        if(positionX < 0 || positionX >= this.field.getWidth())
        {
            throw new IllegalArgumentException(String.format(
                    "Wrong position: %d. Position must be in range from 0 to %d.",
                    positionX, 
                    this.field.getWidth()));
        }
        else
        {
            this.positionX = positionX;
        }
    }

    public void setPosition(int positionY, int positionX)
    {
        this.setPositionY(positionY);
        this.setPositionX(positionX);
    }
}
