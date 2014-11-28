package ua.ilnicki.jbgm;

import ua.ilnicki.jbgm.machine.Field;
import ua.ilnicki.jbgm.machine.Layer;
import ua.ilnicki.jbgm.machine.Screen;
import ua.ilnicki.jbgm.pixelmatrix.MatrixUtils;
import static ua.ilnicki.jbgm.pixelmatrix.MatrixUtils.ReflectType.*;
import ua.ilnicki.jbgm.pixelmatrix.PixelMatrix;
import ua.ilnicki.jbgm.pixelmatrix.Pixel;
import static ua.ilnicki.jbgm.pixelmatrix.Pixel.*;
import ua.ilnicki.jbgm.pixelmatrix.Point;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class JavaBrickGameMachine
{
    public static void main(String[] args)
    {
        /*PixelMatrix pm = MatrixUtils.makeFromArray(new Pixel[][]
            {
                {BLACK, null,   null},
                {BLACK, BLACK,  null, BLACK},
                {BLACK, BLACK, BLACK}
            });
        
        System.out.println(pm);
        
        for(MatrixUtils.ReflectType type : MatrixUtils.ReflectType.values())
        {
            System.out.println(type.name() + " - " + MatrixUtils.getReflected(pm, type));
        }*/
        Field field = new Field(5, 5);
        Screen screen = new Screen(4, 4, field);
       
        PixelMatrix layPm = new PixelMatrix(3, 3);
        Layer layer = new Layer(layPm);
        field.getLayers().add(layer);
        
        field.setPixel(0, 0, BLACK);
        layer.setPixel(1, 1, BLACK);
        layer.setPosition(-1, 0);
        layer.setPixel(1, 1, BLACK);
        
        System.out.println(field);
        System.out.println(field.getBaseLayer());
        System.out.println(layer);
        System.out.println(new PixelMatrix(layer));
        System.out.println(screen);
        screen.setPosition(1, 1);
        System.out.println(screen);
    }
}
