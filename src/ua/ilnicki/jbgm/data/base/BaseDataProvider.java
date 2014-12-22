package ua.ilnicki.jbgm.data.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import ua.ilnicki.jbgm.data.DataCluster;
import ua.ilnicki.jbgm.data.DataProvider;
import ua.ilnicki.jbgm.data.DataWriteException;

/**
 *
 * @author Dmytro
 */
public final class BaseDataProvider implements DataProvider
{

    private Path dataPath;

    /**
     *
     * @throws DataWriteException
     */
    public BaseDataProvider() throws DataWriteException
    {
        this.setLocation(".\\data\\");
    }

    /**
     *
     * @return
     */
    @Override
    public String getLocation()
    {
        return this.dataPath.toString();
    }

    /**
     *
     * @param path
     * @throws DataWriteException
     */
    @Override
    public void setLocation(String path) throws DataWriteException
    {
        File dataDir = new File(path);

        try
        {
            if(dataDir.isDirectory())
            {
                dataDir.mkdirs();
                this.dataPath = Paths.get(dataDir.getCanonicalPath());
            }
            else
            {
                throw new DataWriteException("Data storage path is file.");
            }
        } catch (SecurityException | IOException se)
        {
            throw new DataWriteException(se);
        }
    }

    /**
     *
     * @param clusterName
     * @return
     */
    @Override
    public DataCluster readData(String clusterName)
    {
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream((makeDataFileName(clusterName)))))
        {
            return (DataCluster) in.readObject();

        } catch (Exception ex)
        {
            return null;
        }
    }

    /**
     *
     * @param clusterName
     * @param dataCluster
     * @throws DataWriteException
     */
    @Override
    public void writeData(String clusterName, DataCluster dataCluster) throws DataWriteException
    {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(makeDataFileName(clusterName))))
        {
            out.writeObject(dataCluster);
        } catch (IOException ex)
        {
            throw new DataWriteException(ex);
        }
    }

    private String makeDataFileName(String dataName)
    {
        return Paths.get(this.getLocation(), dataName + ".base").toString();
    }

    @Override
    public String toString()
    {
        return this.getClass().getSimpleName();
    }
}
