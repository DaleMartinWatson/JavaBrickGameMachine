package ua.ilnicki.jbgm.pixelmatrix;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class ExternalPixelMatrixLoader extends PixelMatrixLoader
{
    private final File texcturesPackage;

    /**
     *
     * @param packageName
     * @param path
     */
    protected ExternalPixelMatrixLoader(String packageName, String path)
    {
        this.texcturesPackage = new File(path + "/sprites/", packageName);
        if(!this.texcturesPackage.exists())
            throw new IllegalArgumentException(String.format("Package %s is not exist.", packageName));
    }

    /**
     *
     * @param spriteName
     * @return
     */
    @Override
    protected PixelMatrix read(String spriteName)
    {
        File texture = new File(this.texcturesPackage, spriteName + ".pmt");
        try
        {
            return read(new FileInputStream(texture));
        }
        catch (FileNotFoundException ex)
        {
            return null;
        }
    }
}
