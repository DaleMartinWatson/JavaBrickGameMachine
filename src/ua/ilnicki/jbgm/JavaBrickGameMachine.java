package ua.ilnicki.jbgm;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import ua.ilnicki.jbgm.data.DataCluster;
import ua.ilnicki.jbgm.data.DataWriteException;
import ua.ilnicki.jbgm.data.base.BaseDataProvider;
import ua.ilnicki.jbgm.pixelmatrix.MatrixUtils;
import ua.ilnicki.jbgm.pixelmatrix.Pixel;
import static ua.ilnicki.jbgm.pixelmatrix.Pixel.BLACK;
import static ua.ilnicki.jbgm.pixelmatrix.Pixel.WHITE;
import ua.ilnicki.jbgm.pixelmatrix.PixelMatrix;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class JavaBrickGameMachine
{
    public static void main(String[] args) throws DataWriteException
    {
        //SystemManager systemManager = new SystemManager();
        //JavaBrickGameMachine jbgm = new JavaBrickGameMachine();
        
        /*BaseDataProvider bdp = new BaseDataProvider();
        
        DataCluster dc = new DataCluster();
        dc.setValue("int", 12345);
        dc.setValue("bool", true);
        
        bdp.writeData("test", dc);*/
        
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("key", "value");
        hm.put("key2", "value2");
        
    }
}
