package ua.ilnicki.jbgm.system;

import ua.ilnicki.jbgm.data.DataCluster;
import ua.ilnicki.jbgm.data.DataProvider;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public final class ConfigManager extends DataCluster
{
    private final DataProvider dataProvider;

    public ConfigManager(DataProvider dataProvider)
    {
        this.dataProvider = dataProvider;
    }
}
