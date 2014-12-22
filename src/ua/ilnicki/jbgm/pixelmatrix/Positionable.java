package ua.ilnicki.jbgm.pixelmatrix;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public interface Positionable
{

    /**
     *
     * @return
     */
    public int getPositionX();

    /**
     *
     * @param positionX
     */
    public void setPositionX(int positionX);
    
    /**
     *
     * @return
     */
    public int getPositionY();

    /**
     *
     * @param positionY
     */
    public void setPositionY(int positionY);  
    
    /**
     *
     * @param positionX
     * @param positionY
     */
    public void setPosition(int positionX, int positionY);
    
    /**
     *
     * @return
     */
    public Point getPositionPoint();

    /**
     *
     * @param point
     */
    public void setPositionPoint(Point point);
}
