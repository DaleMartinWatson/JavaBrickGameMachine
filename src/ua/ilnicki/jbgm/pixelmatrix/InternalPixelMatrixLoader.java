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
    
    protected InternalPixelMatrixLoader(String packageName, GameInfo gameInfo)
    {
        this.packageName = packageName;
        this.classLoader = gameInfo.getClass().getClassLoader();
    }
    
    @Override
    public PixelMatrix load(String textureName)
    {
        String fullName = String.format("sprites/%s/%s.pmt", this.packageName, textureName);
        return load(classLoader.getResourceAsStream(fullName));
    }
}