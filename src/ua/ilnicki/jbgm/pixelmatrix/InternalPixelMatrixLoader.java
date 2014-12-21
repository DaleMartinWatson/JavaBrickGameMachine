package ua.ilnicki.jbgm.pixelmatrix;

import ua.ilnicki.jbgm.game.GameInfo;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class InternalPixelMatrixLoader extends PixelMatrixLoader
{
    private final ClassLoader classLoader;
    private final String packageName;
    
    protected InternalPixelMatrixLoader(String packageName, Class clazz)
    {
        this.packageName = packageName;
        this.classLoader = clazz.getClassLoader();
    }
    
    @Override
    protected PixelMatrix read(String spriteName)
    {
        String fullName = String.format("sprites/%s/%s.pmt", this.packageName, spriteName);
        return read(classLoader.getResourceAsStream(fullName));
    }
}