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
    
    public PixelMatrix(int height, int width)
    {
        this.pixelMatrix = new Pixel[height][width];
    }

    public int getHeight()
    {
        return this.pixelMatrix.length;
    }

    public int getWidth()
    {
        return this.pixelMatrix[0].length;
    }

    public Pixel getPixel(int y, int x)
    {
        return this.pixelMatrix[y][x];
    }

    public void setPixel(int y, int x, Pixel value)
    {
        this.pixelMatrix[y][x] = value;
    }

    public void clear()
    {
        for (int i = 0; i < this.pixelMatrix.length; i++)
        {
            for (int j = 0; j < this.pixelMatrix[i].length; j++)
            {
                this.pixelMatrix[i][j] = Pixel.WHITE;
            }
        }
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Matrix: height = ").append(this.getHeight())
                 .append("; width = ").append(this.getWidth()).append(";\n");
        
        for (int i = this.getHeight() - 1; i >= 0; i--)
        {
            for (int j = 0; j < this.getWidth(); j++)
            {
                sb.append('[')
                  .append(this.getPixel(i, j) == Pixel.BLACK ? 'X' : ' ')
                  .append(']');
            }
            
            sb.append('\n');
        }
        
        return sb.toString();
    }
}
