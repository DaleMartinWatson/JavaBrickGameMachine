package ua.ilnicki.jbgm.util;

/**
 * Класс предназначен для хранения параметров, содержащих в себе целые положительные числа с ограничением, при переполнении сбрасывающиеся в 0.
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class BrickGameParameter
{
    private int value = 0;
    private final int maxValue;

    /**
     * Создает объект с заданным максимальным значением. 
     * @param maxValue Целое число, ограничивающее значение параметра (включительно). 
     */
    public BrickGameParameter(int maxValue)
    {
        this.maxValue = maxValue;
    }

    /**
     * Возвращает текущее значение параметра.
     * @return Целое число, текущее значение параметра.
     */
    public int getValue()
    {
        return value;
    }

    /**
     * Устанавливает значение параметра,
     * @param value Значение, которое уставнливается в параметре. При отрицательном значении устанавливается 0, при переполнении - максимальное значение.
     */
    public void setValue(int value)
    {
        if (value > this.maxValue)
        {
            this.value = this.maxValue;
        }
        else if (value < 0)
        {
            this.value = 0;
        }
        else
        {
            this.value = value;
        }
    }

    /**
     * Циклически увеличивает параметр на 1. При переполнении сбрасывает значение в 0.
     */
    public void incValue()
    {
        if(this.value++ > this.maxValue)
            this.value = 0;
    }

    /**
     * Циклически уменьшает параметр на 1. При получении отрицательного числа устанавливает параметр в максимальное значение. 
     */
    public void decValue()
    {
        if(this.value-- < 0)
            this.value = this.maxValue;
    }
}
