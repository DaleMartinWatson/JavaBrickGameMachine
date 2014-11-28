package ua.ilnicki.jbgm.pixelmatrix;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class Point implements Cloneable
{

    private int x;
    private int y;

    /**
     *
     * @param y
     * @param x
     */
    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     *
     * @param point
     */
    public Point(Point point)
    {
        this(point.getX(), point.getY());
    }

    /**
     * Get the value of x
     *
     * @return the value of x
     */
    public int getX()
    {
        return x;
    }

    /**
     * Set the value of x
     *
     * @param x new value of x
     */
    public void setX(int x)
    {
        this.x = x;
    }

    /**
     * Get the value of y
     *
     * @return the value of y
     */
    public int getY()
    {
        return y;
    }

    /**
     * Set the value of y
     *
     * @param y new value of y
     */
    public void setY(int y)
    {
        this.y = y;
    }

    /**
     * Set the value of y and x
     *
     * @param y new value of y
     * @param x new value of x
     */
    public void set(int x, int y)
    {
        this.setX(x);
        this.setY(y);
    }

    /**
     *
     * @param point
     */
    public void add(Point point)
    {
        this.setX(this.getX() + point.getX());
        this.setY(this.getY() + point.getY());
    }

    /**
     *
     * @param point1
     * @param point2
     * @return
     */
    public static Point add(Point point1, Point point2)
    {
        Point result = (Point) point1.clone();
        result.add(point2);

        return result;
    }

    @Override
    public String toString()
    {
        return String.format("[%d, %d]", this.x, this.y);
    }

    @Override
    protected Object clone()
    {
        return new Point(this.x, this.y);
    }
}
