package ua.ilnicki.jbgm.pixelmatrix;

import ua.ilnicki.jbgm.pixelmatrix.Pixel;

/**
 *
 * @author Dmytro
 */
public class MatrixUtils
{
    public static final int Vertically = 0;
    public static final int Horizontally = 1;
    public static final int T = 0;
    public static final int W = -1;
    public static final int B = 1;
    
    public static void fillMatrixWith(PixelMatrix matrix, Pixel pixel)
    {
        for (int i = 0; i < matrix.getHeight(); i++)
        {
            for (int j = 0; j < matrix.getWidth(); j++)
            {
                matrix.setPixel(i, j, pixel);
            }
        }
    }
    
    public static PixelMatrix makeFromArray(int[][] array)
    {
        int width = 0;
        for(int[] line : array)
        {
            if(line.length > width)
                width = line.length;
        }
        
        PixelMatrix pm = new PixelMatrix(array.length, width);
        
        for (int i = 0; i < pm.getHeight(); i++)
        {
            for (int j = 0; j < pm.getWidth(); j++)
            {
                Pixel value;
                
                try
                {
                    if(array[pm.getHeight() - i - 1][j] == W)
                    {
                        value = Pixel.WHITE;
                    }
                    else if(array[pm.getHeight() - i - 1][j] == B)
                    {
                        value = Pixel.BLACK;
                    }
                    else
                    {
                        value = null;
                    }
                }
                catch(Exception e)
                {
                    value = null;
                }
                
                pm.setPixel(i, j, value);
            }
        }
        return pm;
    }
    
    public static PixelMatrix mirror(PixelMatrix pm, int type)
    {
        PixelMatrix newPm = new PixelMatrix(pm.getHeight(), pm.getWidth());
        
        for (int i = 0; i < pm.getHeight(); i++)
        {
            for (int j = 0; j < pm.getWidth(); j++)
            {    
                newPm.setPixel(i, j, pm.getPixel(pm.getHeight() - i - 1, j));
            }
        }
        
        return newPm;
    }
}
