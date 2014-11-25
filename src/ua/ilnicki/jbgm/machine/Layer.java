package ua.ilnicki.jbgm.machine;

import ua.ilnicki.jbgm.pixelmatrix.Pixel;
import ua.ilnicki.jbgm.pixelmatrix.PixelMatrix;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public final class Layer extends PixelMatrix
{
    private int positionY = 0;
    private int positionX = 0;
    
    public Layer(PixelMatrix pm)
    {
        super(pm);
    }
    
    public Layer(int height, int width)
    {
        super(height, width);
    }

    @Override
    public void setPixel(int y, int x, Pixel value)
    {
        super.setPixel(y - this.positionY, x - this.positionX, value);
    }

    @Override
    public Pixel getPixel(int y, int x)
    {
        try
        {
            return super.getPixel(y - this.positionY, x - this.positionX);
        }
        catch(Exception e)
        {
            return null;
        }
    }
    
    public int getPositionY()
    {
        return positionY;
    }

    public int getPositionX()
    {
        return positionX;
    }

    public void setPositionY(int positionY)
    {
        this.positionY = positionY;
    }

    public void setPositionX(int positionX)
    {
        this.positionX = positionX;
    }
    
    public void setPosition(int positionY, int positionX)
    {
        this.setPositionY(positionY);
        this.setPositionX(positionX);
    }
}
