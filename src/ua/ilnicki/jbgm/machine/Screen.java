package ua.ilnicki.jbgm.machine;

import ua.ilnicki.jbgm.pixelmatrix.Pixel;
import ua.ilnicki.jbgm.pixelmatrix.PixelMatrix;

/**
 *
 * @author Dmytro
 */
public final class Screen extends PixelMatrix
{

    private final int height;
    private final int width;
    private Field field;
    private int positionY = 0;
    private int positionX = 0;

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
        if (y >= this.getHeight() || y < 0 || x >= this.getWidth() || x < 0)
        {
            throw new IndexOutOfBoundsException(String.format("Wrong matrix element indexes [%d, %d]", y, x));
        }
        else
        {
            try
            {
                return Pixel.merge(field.getPixel(y, x), Pixel.WHITE);
            }
            catch (Exception e)
            {
                return Pixel.WHITE;
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
            this.positionY = positionY;
        }
    }
    
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
            this.positionX = positionX;
        }
    }

    public void setPosition(int positionY, int positionX)
    {
        this.setPositionY(positionY);
        this.setPositionX(positionX);
    }

    void setField(Field field)
    {
        this.field = field;
        this.positionY = 0;
        this.positionX = 0;
    }
}
