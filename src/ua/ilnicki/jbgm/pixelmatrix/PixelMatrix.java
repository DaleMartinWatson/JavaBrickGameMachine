package ua.ilnicki.jbgm.pixelmatrix;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class PixelMatrix
{

    protected final Pixel[][] pixelMatrix;

    public PixelMatrix(PixelMatrix pm)
    {
        this.pixelMatrix = pm.pixelMatrix;
    }

    public PixelMatrix(int width, int height)
    {
        if(width > 0 && height > 0)
            this.pixelMatrix = new Pixel[height][width];
        else
            throw new IllegalArgumentException("Zero bound.");
    }

    public int getWidth()
    {
        return this.pixelMatrix[0].length;
    }
    
    public int getHeight()
    {
        return this.pixelMatrix.length;
    }

    public Pixel getPixel(int x, int y)
    {
        return this.pixelMatrix[y][x];
    }

    public void setPixel(int x, int y, Pixel value)
    {
        this.pixelMatrix[y][x] = value;
    }
    
    public Pixel getPixel(Point point)
    {
        return this.pixelMatrix[point.getY()][point.getX()];
    }

    public void setPixel(Point point, Pixel value)
    {
        this.pixelMatrix[point.getY()][point.getX()] = value;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append(this.getClass().getSimpleName())
          .append(": width = ").append(this.getWidth())
          .append("; height = ").append(this.getHeight())
          .append(";\n");

        for (int i = this.getHeight() - 1; i >= 0; i--)
        {
            for (int j = 0; j < this.getWidth(); j++)
            {
                if(this.getPixel(j, i) != null)
                {
                    sb.append('[')
                        .append(this.getPixel(j, i) == Pixel.BLACK ? 'X' : ' ')
                        .append(']');
                }
                else
                {
                    sb.append("   ");
                }
            }

            sb.append('\n');
        }

        return sb.toString();
    }
}
