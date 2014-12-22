package ua.ilnicki.jbgm.data.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import ua.ilnicki.jbgm.data.DataCluster;
import ua.ilnicki.jbgm.data.DataProvider;
import ua.ilnicki.jbgm.data.DataWriteException;

/**
 *
 * @author Dmytro
 */
public final class GsonDataProvider implements DataProvider
{

    private Path dataPath;
    private final Gson gson;

    /**
     *
     * @throws DataWriteException
     */
    public GsonDataProvider() throws DataWriteException
    {
        this.setLocation(".\\data\\");

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting()
                .registerTypeAdapter(DataCluster.class, new DataClusterSerialiser());

        gson = gsonBuilder.create();
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
            dataDir.mkdirs();
            this.dataPath = Paths.get(dataDir.getCanonicalPath());
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
        try
        {
            BufferedReader br = new BufferedReader(
                    new FileReader(makeDataFileName(clusterName)));

            return gson.fromJson(br, DataCluster.class);
        } catch (FileNotFoundException ex)
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
        try (FileWriter writer = new FileWriter(makeDataFileName(clusterName)))
        {
            writer.write(gson.toJson(dataCluster, DataCluster.class));
        } catch (IOException ex)
        {
            throw new DataWriteException(ex);
        }
    }

    private String makeDataFileName(String dataName)
    {
        return Paths.get(this.getLocation(), dataName + ".json").toString();
    }
    
    @Override
    public String toString()
    {
        return this.getClass().getSimpleName();
    }
}
