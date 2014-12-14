package ua.ilnicki.jbgm.data;

import ua.ilnicki.jbgm.data.base.BaseDataProvider;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public interface DataProvider
{

    public String getLocation();

    public void setLocation(String path) throws DataWriteException;

    public DataCluster readData(String clusterName);

    public void writeData(String clusterName, DataCluster dataCluster) throws DataWriteException;

    public static DataProvider createDataProvider()
    {
        DataProvider provider = null;
        
        try
        {
            provider = new BaseDataProvider();
            
            DataCluster preConfigCluster = provider.readData("config");

            String path = provider.getLocation();
            if (preConfigCluster.valueExists("DataPath"))
            {
                path = preConfigCluster.getStringValue("DataPath");
            }

            if (preConfigCluster.valueExists("DataProvider"))
            {
                provider = (DataProvider) Class.forName(preConfigCluster.getStringValue("DataProvider")).newInstance();
            }

            provider.setLocation(path);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | DataWriteException e)
        {
            
        }

        return provider;
    }
}
