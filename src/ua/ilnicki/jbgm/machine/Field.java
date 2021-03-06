package ua.ilnicki.jbgm.machine;

import java.util.ArrayList;
import java.util.List;
import ua.ilnicki.jbgm.pixelmatrix.Pixel;
import ua.ilnicki.jbgm.pixelmatrix.PixelMatrix;
import ua.ilnicki.jbgm.pixelmatrix.Point;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public final class Field extends PixelMatrix
{
    private final PixelMatrix baseLayer;
    private final List<Layer> layers;
    
    /**
     *
     * @param width
     * @param height
     */
    public Field(int width, int height)
    {
        super(width, height);
        
        this.baseLayer = new PixelMatrix(this);
        this.layers = new ArrayList<>();
    }

    /**
     *
     * @return
     */
    public PixelMatrix getBaseLayer()
    {
        return baseLayer;
    }

    /**
     *
     * @return
     */
    public List<Layer> getLayers()
    {
        return layers;
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
        if (x >= this.getWidth() || x < 0 || y >= this.getHeight() || y < 0)
        {
            throw new IndexOutOfBoundsException(String.format("Wrong matrix element indexes [%d, %d].", x, y));
        }
        else
        {
            try
            {
                Pixel result = this.baseLayer.getPixel(x, y);
                
                for(Layer layer : layers)
                {
                    result = Pixel.merge(layer.getPixel(x, y), result);
                }
                
                return result;
            }
            catch (Exception e)
            {
                return null;
            }
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
    
    
}
