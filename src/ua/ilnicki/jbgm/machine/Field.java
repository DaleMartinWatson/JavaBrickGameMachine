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
    
    public Field(int height, int width)
    {
        super(height, width);
        
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
                Pixel result = this.baseLayer.getPixel(y, x);
                
                for(Layer layer : layers)
                {
                    result = Pixel.merge(layer.getPixel(y, x), result);
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
