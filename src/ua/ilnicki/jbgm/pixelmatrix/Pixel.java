package ua.ilnicki.jbgm.pixelmatrix;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public enum Pixel
{

    /**
     *
     */
    BLACK, 

    /**
     *
     */
    WHITE;
    
    /**
     *
     * @param upperPixel
     * @param lowerPixel
     * @return
     */
    public static Pixel merge(Pixel upperPixel, Pixel lowerPixel)
    {
        if(upperPixel == null)
        {
            return lowerPixel;
        }
        else
        {
            return upperPixel;
        }
    }
}
