package ua.ilnicki.jbgm.machine;

/**
 *
 * @author Dmytro
 * @param <T>
 */
public interface BrickGameParameter<T>
{
    public T get();
    public void set(T value);
}
