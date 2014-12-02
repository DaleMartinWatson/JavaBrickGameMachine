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

    public static DataProvider createDataProvider() throws DataWriteException,
                                                           ClassNotFoundException,
                                                           InstantiationException,
                                                           IllegalAccessException
    {
        DataProvider provider;

            provider = new BaseDataProvider();
            DataCluster preConfigCluster = provider.readData("config");

            String path = provider.getLocation();
            if (preConfigCluster.valueExists("DataPath") &&
                    !preConfigCluster.getStringValue("DataPath")
                            .equals(provider.getLocation()))
            {
                path = preConfigCluster.getStringValue("DataPath");
            }
            
            if (preConfigCluster.valueExists("DataProvider") &&
                    !preConfigCluster.getStringValue("DataProvider")
                            .equals(provider.getClass().getName()))
            {
                provider = (DataProvider) Class.forName(preConfigCluster.getStringValue("DataProvider")).newInstance();
            }
            
            provider.setLocation(path);

        return provider;
    }
}
