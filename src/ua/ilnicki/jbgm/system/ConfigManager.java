package ua.ilnicki.jbgm.system;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import ua.ilnicki.jbgm.data.DataCluster;
import ua.ilnicki.jbgm.data.DataProvider;
import ua.ilnicki.jbgm.data.DataWriteException;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public final class ConfigManager
{
    private static final String MAIN_CONFIG_FILE_NAME = "config"; 
    
    private final DataProvider dataProvider;
    private final DataCluster mainDataCluster;
    private final LinkedHashMap<String, DataCluster> dataClusterCache;

    public ConfigManager(DataProvider dataProvider)
    {
        this.dataProvider = dataProvider;
        this.dataClusterCache = new LinkedHashMap<>();
        this.mainDataCluster = this.getCluster(MAIN_CONFIG_FILE_NAME);
    }

    public DataCluster getMainCluster()
    {
        return this.mainDataCluster;
    }
    
    public DataCluster getCluster(Object obj)
    {
        return this.getCluster(obj.getClass().getName());
    }
    
    public DataCluster getCluster(Class clazz)
    {
        return this.getCluster(clazz.getName());
    }

    public void saveAll()
    {
        for(Entry<String, DataCluster> entry : this.dataClusterCache.entrySet())
        {
            try
            {
                this.dataProvider.writeData(entry.getKey(), entry.getValue());
            } catch (DataWriteException ex)
            {
                
            }
        }
    }
    
    private DataCluster getCluster(String clusterName)
    {
        DataCluster dataCluster; 
        
        if(this.dataClusterCache.containsKey(clusterName))
        {
            dataCluster = this.dataClusterCache.get(clusterName);
        }
        else
        {
            dataCluster = this.dataProvider.readData(clusterName);
            if(dataCluster == null)
                dataCluster = new DataCluster();       

            this.dataClusterCache.put(clusterName, dataCluster);
        }
        
        return dataCluster;
    }

}
