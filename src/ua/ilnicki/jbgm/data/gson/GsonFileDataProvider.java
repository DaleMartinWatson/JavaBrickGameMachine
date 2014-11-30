/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.ilnicki.jbgm.data.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.ilnicki.jbgm.data.DataCluster;
import ua.ilnicki.jbgm.data.DataProvider;
import ua.ilnicki.jbgm.data.DataWriteException;


public class GsonFileDataProvider implements DataProvider
{
    private String dataPath;
    private final Gson gson;

    public GsonFileDataProvider()
    {
        this.dataPath = ".\\data\\";
        
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting()
                .registerTypeAdapter(DataCluster.class, new DataClusterSerialiser());
        
        gson = gsonBuilder.create();
    }

    @Override
    public String getLocation()
    {
        return this.dataPath;
    }

    @Override
    public void setLocation(String path)
    {
        this.dataPath = path;
    }

    @Override
    public DataCluster readData(String clusterName)
    {
        try
        {
            BufferedReader br = new BufferedReader(
                new FileReader(this.getLocation() + clusterName + ".json"));
            
            return gson.fromJson(br, DataCluster.class);  
        }
        catch (FileNotFoundException ex)
        {
            return null;
        } 
    }

    @Override
    public void writeData(String clusterName, DataCluster dataCluster) throws DataWriteException
    {
        try(FileWriter writer = new FileWriter(this.getLocation() + clusterName + ".json"))
        {
            writer.write(gson.toJson(dataCluster, DataCluster.class));    
        }
        catch (IOException ex)
        {
            throw new DataWriteException(ex);
        }
    }
}
