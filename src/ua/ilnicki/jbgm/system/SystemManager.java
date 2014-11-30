package ua.ilnicki.jbgm.system;

import ua.ilnicki.jbgm.data.gson.GsonFileDataProvider;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class SystemManager
{
    private final SaveManager saveManager;

    public SystemManager()
    {
        this.saveManager = new SaveManager(new GsonFileDataProvider());
    }

    public SaveManager getSaveManager()
    {
        return saveManager;
    }
}
