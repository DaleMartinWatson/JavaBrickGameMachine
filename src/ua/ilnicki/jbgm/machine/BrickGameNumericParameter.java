package ua.ilnicki.jbgm.machine;

/**
 *
 * @author Dmytro
 * @param <T>
 */
public interface BrickGameNumericParameter<T> extends BrickGameParameter<T>
{
    public void inc();
    public void dec();
}
