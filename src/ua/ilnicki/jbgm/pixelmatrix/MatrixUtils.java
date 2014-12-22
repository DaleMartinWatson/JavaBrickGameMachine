package ua.ilnicki.jbgm.pixelmatrix;

import static ua.ilnicki.jbgm.pixelmatrix.MatrixUtils.ReflectType.RT_HORIZONTALLY;
import static ua.ilnicki.jbgm.pixelmatrix.MatrixUtils.ReflectType.RT_ON_MAJOR_DIAGONAL;
import static ua.ilnicki.jbgm.pixelmatrix.MatrixUtils.ReflectType.RT_ON_MINOR_DIAGONAL;
import static ua.ilnicki.jbgm.pixelmatrix.MatrixUtils.ReflectType.RT_VERTICALLY;

/**
 *
 * @author Dmytro
 */
public class MatrixUtils
{

    public enum ReflectType
    {

        RT_HORIZONTALLY,
        RT_VERTICALLY,
        RT_ON_MAJOR_DIAGONAL,
        RT_ON_MINOR_DIAGONAL
    }

    public static PixelMatrix makeFromArray(Pixel[][] pixelArray)
    {
        int width = 0;
        for (Pixel[] row : pixelArray)
            if (row.length > width)
                width = row.length;

        PixelMatrix pm = new PixelMatrix(width, pixelArray.length);

        for (int i = 0; i < pixelArray.length; i++)
        {
            for (int j = 0; j < pixelArray[i].length; j++)
            {
                pm.setPixel(j, pm.getHeight() - 1 - i, pixelArray[i][j]);
            }
        }

        return pm;
    }

    public static void clear(PixelMatrix pm)
    {
        fill(pm, null);
    }

    public static void fill(PixelMatrix pm, Pixel fillWith)
    {
        for (int i = 0; i < pm.getWidth(); i++)
        {
            for (int j = 0; j < pm.getHeight(); j++)
            {
                pm.setPixel(i, j, fillWith);
            }
        }
    }

    public static PixelMatrix copy(PixelMatrix pm)
    {
        PixelMatrix copy = new PixelMatrix(pm.getWidth(), pm.getHeight());

        for (int i = 0; i < pm.getWidth(); i++)
        {
            for (int j = 0; j < pm.getHeight(); j++)
            {
                copy.setPixel(i, j, pm.getPixel(i, j));
            }
        }

        return copy;
    }

    public static PixelMatrix getReflected(PixelMatrix pm, ReflectType type)
    {
        PixelMatrix newPm;

        if ((type == RT_ON_MAJOR_DIAGONAL || type == RT_ON_MINOR_DIAGONAL)
                && pm.getWidth() != pm.getHeight())
        {
            newPm = new PixelMatrix(pm.getHeight(), pm.getWidth());
        } else
        {
            newPm = new PixelMatrix(pm.getWidth(), pm.getHeight());
        }

        for (int i = 0; i < pm.getWidth(); i++)
        {
            for (int j = 0; j < pm.getHeight(); j++)
            {
                switch (type)
                {
                    case RT_HORIZONTALLY:
                        newPm.setPixel(i, j, pm.getPixel(i, pm.getHeight() - 1 - j));
                        break;
                    case RT_VERTICALLY:
                        newPm.setPixel(i, j, pm.getPixel(pm.getWidth() - 1 - i, j));
                        break;
                    case RT_ON_MAJOR_DIAGONAL:
                        throw new UnsupportedOperationException("Not supported yet.");
                    case RT_ON_MINOR_DIAGONAL:
                        throw new UnsupportedOperationException("Not supported yet.");
                }
            }
        }

        return newPm;
    }

    public static PixelMatrix getRotated(PixelMatrix pm, int angle)
    {
        PixelMatrix newMatrix = null;

        angle = normalizeAngle(angle);

        if ((angle > 315 && angle < 360)
                || (angle >= 0 && angle <= 45))
        {
            newMatrix = copy(pm);
        } else if (angle > 45 && angle <= 135)
        {
            newMatrix = new PixelMatrix(pm.getHeight(), pm.getWidth());
            
            for (int i = 0; i < newMatrix.getWidth(); i++)
            {
                for (int j = 0; j < newMatrix.getHeight(); j++)
                {
                    newMatrix.setPixel(i, j,
                            pm.getPixel(newMatrix.getHeight()- j - 1, i));
                }
            }
        } else if (angle > 135 && angle <= 195)
        {
            newMatrix = new PixelMatrix(pm.getWidth(), pm.getHeight());
            
            for (int i = 0; i < newMatrix.getWidth(); i++)
            {
                for (int j = 0; j < newMatrix.getHeight(); j++)
                {
                    newMatrix.setPixel(i, j,
                            pm.getPixel(newMatrix.getWidth()- i - 1, 
                                    newMatrix.getHeight()- j - 1));
                }
            }
        } else if (angle > 195 && angle <= 315)
        {
            newMatrix = new PixelMatrix(pm.getHeight(), pm.getWidth());
            
            for (int i = 0; i < newMatrix.getWidth(); i++)
            {
                for (int j = 0; j < newMatrix.getHeight(); j++)
                {
                    newMatrix.setPixel(i, j,
                            pm.getPixel(j, newMatrix.getWidth()- i - 1));
                }
            }
        }

        return newMatrix;
    }

    private static int normalizeAngle(int angle)
    {
        return (angle %= 360) >= 0 ? angle : (angle + 360);
    }

}
