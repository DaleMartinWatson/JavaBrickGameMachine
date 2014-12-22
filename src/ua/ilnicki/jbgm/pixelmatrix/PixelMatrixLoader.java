package ua.ilnicki.jbgm.pixelmatrix;

import java.io.DataInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.ilnicki.jbgm.data.DataCluster;
import ua.ilnicki.jbgm.data.DataProvider;
import ua.ilnicki.jbgm.data.DataWriteException;
import ua.ilnicki.jbgm.data.base.BaseDataProvider;
import ua.ilnicki.jbgm.game.Game;
import ua.ilnicki.jbgm.game.GameInfo;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public abstract class PixelMatrixLoader
{

    protected HashMap<String, PixelMatrix> cache;
    
    public PixelMatrixLoader()
    {
        this.cache = new HashMap<>();
    }

    public PixelMatrix load(String spriteName, boolean shouldBeCached)
    {
        if (shouldBeCached)
        {
            PixelMatrix pm = this.cache.get(spriteName);

            if (pm == null)
            {
                pm = this.read(spriteName);

                if (pm != null)
                    this.cache.put(spriteName, pm);
            }

            return pm;
        } else
        {
            return this.read(spriteName);
        }
    }

    public PixelMatrix load(String spriteName)
    {
        return this.load(spriteName, false);
    }

    protected abstract PixelMatrix read(String spriteName);

    protected PixelMatrix read(InputStream is)
    {
        try (DataInputStream input = new DataInputStream(is))
        {
            if (input.readByte() == 0x01)
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
            }
        } catch (Exception e)
        {

        }

        return null;
    }

    public void clearCache()
    {
        this.cache.clear();
    }
    
    private static String defaultSource;

    static
    {
        try
        {
            DataProvider provider = new BaseDataProvider();
            
            DataCluster preConfigCluster = provider.readData("config");

            if (preConfigCluster.valueExists("WorkingPath"))
            {
                defaultSource = preConfigCluster.getStringValue("WorkingPath") + "/data";
            }
        } catch (DataWriteException ex)
        {
            defaultSource = "./data";
        }
    }
    
    public static PixelMatrixLoader create(String packageName, Object pathSource)
    {
        if (pathSource instanceof DataProvider)
        {
            return new ExternalPixelMatrixLoader(packageName,
                    ((DataProvider) pathSource).getLocation());
        } else if(pathSource == null)
        {
            return new ExternalPixelMatrixLoader(packageName, defaultSource);
        } else if (pathSource instanceof GameInfo)
        {
            return new InternalPixelMatrixLoader(packageName, pathSource.getClass());
        } else if (pathSource instanceof Game)
        {
            return new InternalPixelMatrixLoader(packageName, pathSource.getClass());
        } else if (pathSource.getClass().isInstance(Game.class))
        {
            return new InternalPixelMatrixLoader(packageName, (Class) pathSource);
        }
        else
        {
            return null;
        }
    }

}
