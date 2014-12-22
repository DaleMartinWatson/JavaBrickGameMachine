package ua.ilnicki.jbgm.system;

import ua.ilnicki.jbgm.data.DataCluster;
import ua.ilnicki.jbgm.data.DataProvider;
import ua.ilnicki.jbgm.data.DataWriteException;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public final class SaveManager
{
    private final DataProvider dataProvider;

    /**
     *
     * @param dataProvider
     */
    public SaveManager(DataProvider dataProvider)
    {
        this.dataProvider = dataProvider;
    }
    
    /**
     *
     * @param obj
     * @return
     */
    public SaveCluster getCluster(Object obj)
    {
        return new SaveCluster(obj.getClass());
    }

    private DataCluster readDataCluster(String clusterName)
    {
        return this.dataProvider.readData(clusterName);
    }

    private void writeDataCluster(String clusterName, DataCluster dataCluster) throws DataWriteException
    {
        this.dataProvider.writeData(clusterName, dataCluster);
    }

    /**
     *
     */
    public final class SaveCluster extends DataCluster
    {

        private boolean closed = false;
        private final String clusterName;

        private SaveCluster(Class callerClass)
        {
            super(SaveManager.this.readDataCluster(callerClass.getName()));
            this.clusterName = callerClass.getName();
        }

        /**
         *
         */
        @Override
        public void close()
        {
            super.close();
            this.closed = true;
        }

        /**
         *
         * @throws Exception
         */
        public void save() throws Exception
        {
            if(!this.closed)
                SaveManager.this.writeDataCluster(this.clusterName, this);
            else
                throw new Exception("Can`t save closed SaveCluster.");
        }

    }
}
