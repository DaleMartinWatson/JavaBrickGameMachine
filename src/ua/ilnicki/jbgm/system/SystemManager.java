package ua.ilnicki.jbgm.system;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class SystemManager
{
    private final SaveManager saveManager;

    public SystemManager()
    {
        this.saveManager = new SaveManager();
    }

    public SaveManager getSaveManager()
    {
        return saveManager;
    }
}
