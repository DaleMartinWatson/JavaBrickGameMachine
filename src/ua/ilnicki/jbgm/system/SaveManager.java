package ua.ilnicki.jbgm.system;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class SaveManager
{
    public SaveCluster getCluster(Object obj)
    {
        return new SaveCluster(this, obj.getClass());
    }
    
    public class SaveCluster implements DataCluster
    {
        private SaveCluster(SaveManager sm, Class<? extends Object> callerClass)
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void setValue(String key, int value)
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void setValue(String key, boolean value)
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void setValue(String key, String value)
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public int getIntValue(String key)
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean getBoolValue(String key)
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public String getStringValue(String key)
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean valueExists(String key)
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean valueExists(String key, Class type)
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
