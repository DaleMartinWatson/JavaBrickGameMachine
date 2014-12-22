package ua.ilnicki.jbgm.io.lwjgl2;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class Color
{
    private final byte red;
    private final byte green;
    private final byte blue;
    
    private float factor = 1.0f / 255;

    /**
     *
     * @param red
     * @param green
     * @param blue
     */
    public Color(int red, int green, int blue)
    {
        this.red = (byte) red;
        this.green = (byte) green;
        this.blue = (byte) blue;
    }

    /**
     *
     * @return
     */
    public byte getR()
    {
        return red;
    }

    /**
     *
     * @return
     */
    public byte getG()
    {
        return green;
    }

    /**
     *
     * @return
     */
    public byte getB()
    {
        return blue;
    }
    
    /**
     *
     * @return
     */
    public float getFloatR()
    {
        return red * factor;
    }

    /**
     *
     * @return
     */
    public float getFloatG()
    {
        return green * factor;
    }

    /**
     *
     * @return
     */
    public float getFloatB()
    {
        return blue * factor;
    }

    @Override
    public String toString()
    {
        return String.format("#%02x%02x%02x", this.red,
                                 this.red,
                                 this.blue).toUpperCase();
    }
}
