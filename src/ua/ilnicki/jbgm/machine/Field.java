package ua.ilnicki.jbgm.machine;

import java.util.ArrayList;
import java.util.List;
import ua.ilnicki.jbgm.pixelmatrix.Pixel;
import ua.ilnicki.jbgm.pixelmatrix.PixelMatrix;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public final class Field extends PixelMatrix
{
    private final PixelMatrix baseLayer;
    private final List<Layer> layers;
    
    public Field(int width, int height)
    {
        super(width, height);
        
        this.baseLayer = new PixelMatrix(this);
        this.layers = new ArrayList<>();
    }

    public PixelMatrix getBaseLayer()
    {
        return baseLayer;
    }

    public List<Layer> getLayers()
    {
        return layers;
    }

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
    
    
}
