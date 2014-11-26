package ua.ilnicki.jbgm.system;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class SaveManager
{
    public SaveCluster getCluster(Object obj)
    {
        return new SaveCluster(this);
    }
    
    public class SaveCluster
    {
        private SaveCluster(SaveManager cm)
        {
            
        }

        public void setValue(String key, int value)
        {
            
        }
        
        public void setValue(String key, boolean value)
        {
            
        }
        
        public void setValue(String key, String value)
        {
            
        }
        
        public int getIntValue(String key)
        {
            return 0;
        }
        
        public boolean getBoolValue(String key)
        {
            return true;
        }
        
        public String getStringValue(String key)
        {
            return "";
        }

        public boolean valueExists(String key)
        {
            return true;
        }

        public boolean valueExists(String key, Class<Integer> type)
        {
            return type.isInstance(key);
        }
    }
}
