package ua.ilnicki.jbgm.pixelmatrix;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public interface Positionable
{
    public int getPositionX();
    public void setPositionX(int positionX);
    
    public int getPositionY();
    public void setPositionY(int positionY);  
    
    public void setPosition(int positionX, int positionY);
    
    public Point getPositionPoint();
    public void setPositionPoint(Point point);
}
