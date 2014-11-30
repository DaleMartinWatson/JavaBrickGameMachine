package ua.ilnicki.jbgm.data;

import java.util.HashMap;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class DataCluster
{

    protected HashMap<String, Object> dataSet;

    public DataCluster()
    {
        this.dataSet = new HashMap<>();
    }

    public DataCluster(HashMap dataSet)
    {
        this.dataSet = dataSet;
    }
    
    public DataCluster(DataCluster dataCluster)
    {
        this.dataSet = dataCluster.dataSet;
    }

    public void setValue(String key, Object value)
    {
        this.dataSet.put(key, value);
    }

    public Object getValue(String key)
    {
        return this.dataSet.get(key);
    }

    public <T> T getValue(String key, Class<T> type)
    {
        return type.cast(this.getValue(key));
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
    
    public void close()
    {
        this.dataSet = null;
    }
}
