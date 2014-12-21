package ua.ilnicki.jbgm.machine;

import java.io.Serializable;

/**
 * Класс предназначен для хранения параметров, содержащих в себе целые положительные числа с ограничением, при переполнении сбрасывающиеся в 0.
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class IntParameter implements Serializable
{
    private int value;
    private final int minValue;
    private final int maxValue;

    /**
     * Создает объект с заданным максимальным значениеями и минимальным, равным нулю.
     */
    public IntParameter()
    {
        this(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    public IntParameter(int maxValue)
    {
        this(Integer.MIN_VALUE, maxValue);
    }
    
    /**
     * Создает объект с заданными минимальным и максимальным значениеями.
     * @param minValue Целое число, ограничивающее минимальное значение параметра (включительно).
     * @param maxValue Целое число, ограничивающее максимальное значение параметра (включительно).
     */
    public IntParameter(int minValue, int maxValue)
    {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.value = this.minValue;
    }

    /**
     * Возвращает текущее значение параметра.
     * @return Целое число, текущее значение параметра.
     */
    public Integer get()
    {
        return value;
    }

    /**
     * Устанавливает значение параметра,
     * @param value Значение, которое уставнливается в параметре. При значении меньше минимального устанавливается минимальное значение, при переполнении - максимальное значение.
     */
    public void set(Integer value)
    {
        if (value > this.maxValue)
        {
            this.value = this.maxValue;
        }
        else if (value < this.minValue)
        {
            this.value = this.minValue;
        }
        else
        {
            this.value = value;
        }
    }

    /**
     * Циклически увеличивает параметр на 1. При переполнении сбрасывает значение в минимальное значение.
     */
    public void inc()
    {
        if(++this.value > this.maxValue)
            this.value = this.minValue;
    }

    /**
     * Циклически уменьшает параметр на 1. При достижении минимального значения устанавливает параметр в максимальное значение. 
     */
    public void dec()
    {
        if(--this.value < this.minValue)
            this.value = this.maxValue;
    }

    @Override
    public String toString()
    {
        return Integer.toString(value);
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 89 * hash + this.value;
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        
        final IntParameter other = (IntParameter) obj;
        return this.value == other.value;
    }
}
