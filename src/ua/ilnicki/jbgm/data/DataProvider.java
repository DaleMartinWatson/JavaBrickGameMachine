package ua.ilnicki.jbgm.data;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public interface DataProvider
{
    public String getLocation();
    public void setLocation(String path);
    public DataCluster readData(String clusterName);
    public void writeData(String clusterName, DataCluster dataCluster) throws DataWriteException;
}
