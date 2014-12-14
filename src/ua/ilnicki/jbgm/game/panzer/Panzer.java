package ua.ilnicki.jbgm.game.panzer;

import java.util.HashMap;
import ua.ilnicki.jbgm.pixelmatrix.MatrixUtils;
import ua.ilnicki.jbgm.pixelmatrix.Pixel;
import static ua.ilnicki.jbgm.pixelmatrix.Pixel.BLACK;
import ua.ilnicki.jbgm.pixelmatrix.PixelMatrix;
import ua.ilnicki.jbgm.pixelmatrix.Point;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class Panzer extends Entity
{

    private Direction direction;
    private HashMap<Direction, PixelMatrix> spirites;
    private HashMap<Direction, Point> shotPoints;

    Panzer(Point point, Direction direction)
    {
        super(point);
        this.direction = direction;
        
        spirites = new HashMap<>(4);
        
        spirites.put(Direction.UP, MatrixUtils.makeFromArray(new Pixel[][]{
                    { null, BLACK,  null},
                    {BLACK, BLACK, BLACK},
                    {BLACK,  null, BLACK}
            }));
        
        spirites.put(Direction.RIGHT, MatrixUtils.makeFromArray(new Pixel[][]{
                    {BLACK, BLACK,  null},
                    { null, BLACK, BLACK},
                    {BLACK, BLACK,  null}
            }));
        
        spirites.put(Direction.DOWN, MatrixUtils.makeFromArray(new Pixel[][]{
                    {BLACK,  null, BLACK},
                    {BLACK, BLACK, BLACK},
                    { null, BLACK,  null}
            }));
        
        spirites.put(Direction.LEFT, MatrixUtils.makeFromArray(new Pixel[][]{
                    { null, BLACK, BLACK},
                    {BLACK, BLACK,  null},
                    { null, BLACK, BLACK}
            }));
        
        shotPoints = new HashMap<>(4);
        
        shotPoints.put(Direction.UP,    new Point(1, 2));
        shotPoints.put(Direction.RIGHT, new Point(2, 1));
        shotPoints.put(Direction.DOWN,  new Point(1, 0));
        shotPoints.put(Direction.LEFT,  new Point(0, 1));
    }
    
    Panzer(int posX, int posY, Direction direction)
    {
        this(new Point(posX, posY), direction);
    }

    public Direction getDirection()
    {
        return direction;
    }

    public void setDirection(Direction direction)
    {
        this.direction = direction;
    }

    @Override
    public boolean isCollide(int y, int x)
    {
        return this.getSprite().getPixel(x - this.getPosX(), y - this.getPosY()) != null;
    }

    @Override
    public PixelMatrix getSprite()
    {
        return this.spirites.get(this.direction);
    }
    
    public Bullet shoot()
    {
        Point shootPoint = new Point(this.shotPoints.get(this.getDirection()));
        shootPoint.add(this.getPos());
        
        return new Bullet(shootPoint, this.getDirection());
    }
}
