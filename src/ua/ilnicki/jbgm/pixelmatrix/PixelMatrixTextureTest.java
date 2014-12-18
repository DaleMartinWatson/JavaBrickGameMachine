package ua.ilnicki.jbgm.pixelmatrix;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import ua.ilnicki.jbgm.data.DataProvider;
import ua.ilnicki.jbgm.data.DataWriteException;
import ua.ilnicki.jbgm.data.gson.GsonDataProvider;
import ua.ilnicki.jbgm.game.GameInfo;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class PixelMatrixTextureTest
{

    public static void main(String[] args) throws DataWriteException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        DataProvider dp = new GsonDataProvider();
        dp.setLocation("H:\\Documents\\Univer\\ООП\\CourseProject\\JavaBrickGame\\data");

        String gameName = "TanksGame";

        File jarFile = new File("./games/" + gameName + ".jar");
        ClassLoader urlCl = new URLClassLoader(new URL[]
            {
                jarFile.toURI().toURL()
            });

        GameInfo gi = (GameInfo) urlCl.loadClass(gameName + "Info").newInstance();

        System.out.println(gi.getName());
        System.out.println(gi.getGameClass().newInstance().getClass().getCanonicalName());
        PixelMatrixLoader pml = PixelMatrixLoader.create("characters", gi);

        PixelMatrix pm = pml.load("Z");

        System.out.println(pm);
    }
}
