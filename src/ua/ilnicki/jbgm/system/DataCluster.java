package ua.ilnicki.jbgm.system;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public interface DataCluster
{

    public void setValue(String key, int value);
    public void setValue(String key, boolean value);
    public void setValue(String key, String value);

    public int getIntValue(String key);
    public boolean getBoolValue(String key);
    public String getStringValue(String key);

    public boolean valueExists(String key); 
    public boolean valueExists(String key, Class type);
}
