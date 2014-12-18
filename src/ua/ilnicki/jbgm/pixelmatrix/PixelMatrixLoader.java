package ua.ilnicki.jbgm.pixelmatrix;

import java.io.DataInputStream;
import java.io.InputStream;
import ua.ilnicki.jbgm.data.DataProvider;
import ua.ilnicki.jbgm.game.GameInfo;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public abstract class PixelMatrixLoader
{
    public abstract PixelMatrix load(String textureName);

    protected PixelMatrix load(InputStream is)
    {

        try (DataInputStream input = new DataInputStream(is))
        {

            short width = input.readShort();
            short height = input.readShort();

            PixelMatrix pixelMatrix = new PixelMatrix(width, height);

            for (int i = height - 1; i >= 0; i--)
            {
                boolean lineBreak = false;

                for (int j = 0; j < width && !lineBreak; j++)
                {
                    switch (input.readByte())
                    {
                        case 0x01:
                            pixelMatrix.setPixel(j, i, Pixel.BLACK);
                            break;
                        case 0x02:
                            pixelMatrix.setPixel(j, i, Pixel.WHITE);
                            break;
                        case 0x03:
                            lineBreak = true;
                            break;
                    }
                }
            }

            return pixelMatrix;
        } catch (Exception e)
        {
            return null;
        }
    }
    
    public static PixelMatrixLoader create(String packageName, Object pathSource)
    {
        if(pathSource instanceof DataProvider)
        {
            return new ExternalPixelMatrixLoader(packageName,
                    ((DataProvider) pathSource).getLocation());
        }
        else if(pathSource instanceof GameInfo)
        {
            return new InternalPixelMatrixLoader(packageName, (GameInfo) pathSource);
        }
        else
        {
            return null;
        }
    }
}
