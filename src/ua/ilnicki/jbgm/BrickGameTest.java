package ua.ilnicki.jbgm;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Scanner;
import ua.ilnicki.jbgm.data.DataProvider;
import ua.ilnicki.jbgm.data.DataWriteException;
import ua.ilnicki.jbgm.data.gson.GsonDataProvider;
import ua.ilnicki.jbgm.game.GameInfo;
import ua.ilnicki.jbgm.pixelmatrix.MatrixUtils;
import ua.ilnicki.jbgm.pixelmatrix.PixelMatrix;
import ua.ilnicki.jbgm.pixelmatrix.PixelMatrixLoader;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class BrickGameTest
{

    public static void main(String[] args) throws DataWriteException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
       /* DataProvider dp = new GsonDataProvider();
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
        PixelMatrixLoader pml = PixelMatrixLoader.create("characters", dp);

        PixelMatrix pm = pml.load("3", true);

        System.out.println(pm);
        System.out.println(MatrixUtils.getRotated(pm, 0));
        System.out.println(MatrixUtils.getRotated(pm, 90));
        System.out.println(MatrixUtils.getRotated(pm, 180));
        System.out.println(MatrixUtils.getRotated(pm, 270));*/
        
        extractDigits(9050, 6);
    }

    private static void extractDigits(int num, int digitCount)
    {
        for (int i = digitCount; i >= 0; i--)
        {
            Integer digit = num % (int) Math.pow(10, i + 1) / (int) Math.pow(10, i);
            
            if(digit == 0 && num < Math.pow(10, i + 1))
                digit = null;
            
            System.out.println(digit);
        }
    }

}
