package ua.ilnicki.jbgm;

import ua.ilnicki.jbgm.pixelmatrix.PixelMatrix;
import static ua.ilnicki.jbgm.pixelmatrix.MatrixUtils.*;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class JavaBrickGameMachine
{
    public static void main(String[] args)
    {
        PixelMatrix pm = makeFromArray(new int[][]
            {
                {0, 1, 0},
                {1, 1, 1},
                {1, 0, 1}
            });
        
        System.out.print(pm);
    }
}
