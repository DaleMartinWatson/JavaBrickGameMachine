package ua.ilnicki.jbgm.pixelmatrix;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import ua.ilnicki.jbgm.data.DataProvider;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class ExternalPixelMatrixLoader extends PixelMatrixLoader
{
    private final File texcturesPackage;

    protected ExternalPixelMatrixLoader(String packageName, String path)
    {
        this.texcturesPackage = new File(path + "/sprites/", packageName);
        if(!this.texcturesPackage.exists())
            throw new IllegalArgumentException(String.format("Package %s is not exist.", packageName));
    }

    @Override
    public PixelMatrix load(String textureName)
    {
        File texture = new File(this.texcturesPackage, textureName + ".pmt");
        try
        {
            return load(new FileInputStream(texture));
        }
        catch (FileNotFoundException ex)
        {
            return null;
        }
    }
}
