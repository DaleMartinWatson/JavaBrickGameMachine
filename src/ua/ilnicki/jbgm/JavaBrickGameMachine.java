package ua.ilnicki.jbgm;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import java.util.HashMap;
import ua.ilnicki.jbgm.machine.Field;
import ua.ilnicki.jbgm.machine.Layer;
import ua.ilnicki.jbgm.machine.Screen;
import ua.ilnicki.jbgm.pixelmatrix.MatrixUtils;
import static ua.ilnicki.jbgm.pixelmatrix.MatrixUtils.ReflectType.*;
import ua.ilnicki.jbgm.pixelmatrix.PixelMatrix;
import ua.ilnicki.jbgm.pixelmatrix.Pixel;
import static ua.ilnicki.jbgm.pixelmatrix.Pixel.*;
import ua.ilnicki.jbgm.pixelmatrix.Point;
import ua.ilnicki.jbgm.data.DataCluster;
import ua.ilnicki.jbgm.data.DataProvider;
import ua.ilnicki.jbgm.data.DataWriteException;
import ua.ilnicki.jbgm.data.gson.DataClusterSerialiser;
import ua.ilnicki.jbgm.data.gson.GsonFileDataProvider;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class JavaBrickGameMachine
{
    public static void main(String[] args) throws DataWriteException
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
        /*Field field = new Field(5, 5);
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
        System.out.println(screen);*/
        
        PixelMatrix pixelMatrix = MatrixUtils.makeFromArray(new Pixel[][]
            {
                {BLACK, null},
                {WHITE, BLACK},
            });
        
        HashMap ds = new HashMap();
        
        DataCluster dc = new DataCluster(ds);
        dc.setValue("int", 12345);
        dc.setValue("bool", true);
        dc.setValue("string", "str");
        dc.setValue("matrix", pixelMatrix);
        
        System.out.println(dc.getIntValue("int"));
        System.out.println(dc.getBoolValue("bool"));
        System.out.println(dc.getStringValue("string"));
        System.out.println(dc.getValue("matrix", PixelMatrix.class));
        
        DataProvider dp = new GsonFileDataProvider();     
        dp.setLocation("D:\\data\\");
        
        dp.writeData("test", dc);
                
        DataCluster dcNew = dp.readData("test");
        
        System.out.println(dcNew.getIntValue("int"));
        System.out.println(dcNew.getBoolValue("bool"));
        System.out.println(dcNew.getStringValue("string"));
        System.out.println(dcNew.getValue("matrix", PixelMatrix.class));
    }
}
