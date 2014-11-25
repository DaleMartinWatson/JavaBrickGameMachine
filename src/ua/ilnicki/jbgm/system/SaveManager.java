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
        
        public int getIntValue(String volume)
        {
            return 0;
        }
        
        public boolean getBoolValue(String volume)
        {
            return true;
        }
        
        public String getStringValue(String volume)
        {
            return "";
        }

        public boolean valueExists(String volume)
        {
            return true;
        }
    }
}
