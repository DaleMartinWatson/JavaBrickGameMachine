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
    
    /**
     *
     */
    protected LinkedHashMap<String, Object> dataSet;

    /**
     *
     */
    public DataCluster()
    {
        this.dataSet = new LinkedHashMap<>();
    }

    /**
     *
     * @param dataSet
     */
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

    /**
     *
     * @param dataCluster
     */
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

    /**
     *
     * @param key
     * @param value
     */
    public void putValue(String key, Object value)
    {
        this.dataSet.put(key, value);
    }

    /**
     *
     * @param key
     * @return
     */
    public Object getValue(String key)
    {
        return this.dataSet.get(key);
    }

    /**
     *
     * @param <T>
     * @param key
     * @param type
     * @return
     */
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

    /**
     *
     * @param key
     * @return
     */
    public int getIntValue(String key)
    {
        return this.getValue(key, Integer.class);
    }

    /**
     *
     * @param key
     * @return
     */
    public boolean getBoolValue(String key)
    {
        return this.getValue(key, Boolean.class);
    }

    /**
     *
     * @param key
     * @return
     */
    public String getStringValue(String key)
    {
        return this.getValue(key, String.class);
    }

    /**
     *
     * @param key
     * @return
     */
    public String getValueAsString(String key)
    {
        return this.dataSet.get(key).toString();
    }

    /**
     *
     * @param key
     * @return
     */
    public boolean valueExists(String key)
    {
        return this.dataSet.containsKey(key);
    }

    /**
     *
     * @param key
     * @param type
     * @return
     */
    public boolean valueExists(String key, Class type)
    {
        return this.dataSet.containsKey(key)
                && type.isInstance(this.dataSet.get(key));
    }

    /**
     *
     * @return
     */
    public String[] getKeys()
    {
        String[] keys = this.dataSet.keySet().toArray(new String[0]);
        return keys != null ? keys : new String[0];
    }

    /**
     *
     */
    public void close()
    {
        this.dataSet = null;
    }
}
