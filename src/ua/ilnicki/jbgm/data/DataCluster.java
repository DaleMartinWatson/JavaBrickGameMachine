package ua.ilnicki.jbgm.data;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class DataCluster implements Serializable
{
    private static final long serialVersionUID = 750L;
    
    protected LinkedHashMap<String, Object> dataSet;

    public DataCluster()
    {
        this.dataSet = new LinkedHashMap<>();
    }

    public DataCluster(LinkedHashMap dataSet)
    {
        if (dataSet != null)
        {
            this.dataSet = dataSet;
        }
        else
        {
            this.dataSet = new LinkedHashMap<>();
        }
    }

    public DataCluster(DataCluster dataCluster)
    {
        if (dataCluster != null)
        {
            this.dataSet = dataCluster.dataSet;
        }
        else
        {
            this.dataSet = new LinkedHashMap<>();
        }
    }

    public void putValue(String key, Object value)
    {
        this.dataSet.put(key, value);
    }

    public Object getValue(String key)
    {
        return this.dataSet.get(key);
    }

    public <T> T getValue(String key, Class<T> type)
    {
        try
        {
            return type.cast(this.getValue(key));
        } catch (Exception e)
        {
            throw new DataCastException(e.getMessage());
        }
    }

    public int getIntValue(String key)
    {
        return this.getValue(key, Integer.class);
    }

    public boolean getBoolValue(String key)
    {
        return this.getValue(key, Boolean.class);
    }

    public String getStringValue(String key)
    {
        return this.getValue(key, String.class);
    }

    public String getValueAsString(String key)
    {
        return this.dataSet.get(key).toString();
    }

    public boolean valueExists(String key)
    {
        return this.dataSet.containsKey(key);
    }

    public boolean valueExists(String key, Class type)
    {
        return this.dataSet.containsKey(key)
                && type.isInstance(this.dataSet.get(key));
    }

    public String[] getKeys()
    {
        String[] keys = this.dataSet.keySet().toArray(new String[0]);
        return keys != null ? keys : new String[0];
    }

    public void close()
    {
        this.dataSet = null;
    }
}
