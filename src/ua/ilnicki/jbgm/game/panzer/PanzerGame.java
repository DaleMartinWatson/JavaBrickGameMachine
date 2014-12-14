package ua.ilnicki.jbgm.game.panzer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import ua.ilnicki.jbgm.game.Game;
import ua.ilnicki.jbgm.pixelmatrix.MatrixUtils;
import ua.ilnicki.jbgm.pixelmatrix.PixelMatrix;
import ua.ilnicki.jbgm.system.GameManager;

import static ua.ilnicki.jbgm.game.panzer.Direction.*;
import ua.ilnicki.jbgm.machine.Field;
import ua.ilnicki.jbgm.machine.Keyboard.CtrlKey;
import ua.ilnicki.jbgm.machine.Keyboard.KeyboardPasser;
import ua.ilnicki.jbgm.machine.Machine.Parameters;
import static ua.ilnicki.jbgm.pixelmatrix.Pixel.*;
import ua.ilnicki.jbgm.system.SaveManager;
import ua.ilnicki.jbgm.system.SaveManager.SaveCluster;

public class PanzerGame implements Game
{

    private GameManager gameManager;
    private Field field;
    private PixelMatrix helper;
    private KeyboardPasser keyboardPasser;
    private Parameters parameters;
    private SaveCluster saveCluster;

    private int skip = 0;
    private final int shouldSkip = 2;

    private int blink = 0;
    private final int blinkOn = 5;

    private final int maxMineCount = 15;

    private int weaponCooldown = 0;
    private final int minWeaponCooldown = 9;

    private int bulletMove = 0;

    private final Panzer player = new Panzer(0, 0, UP);
    private final List<Panzer> panzerList = new ArrayList<>();
    private final List<Mine> mineList = new ArrayList<>();
    private final List<Bullet> bulletList = new ArrayList<>();

    @Override
    public void init(GameManager gameManager,
            Field field,
            PixelMatrix helper,
            KeyboardPasser keyboardPasser,
            Parameters parameters,
            SaveManager saveManager)
    {
        this.gameManager = gameManager;
        this.field = field;
        this.helper = helper;
        this.keyboardPasser = keyboardPasser;
        this.parameters = parameters;
        this.saveCluster = saveManager.getCluster(this);
    }

    @Override
    public void save()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void recover()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onLoad()
    {
        panzerList.add(player);
    }

    @Override
    public void onTick(long tick)
    {
        if (mineList.size() == maxMineCount)
        {
            this.gameManager.exitGame();
        }

        handleControlls();
        generateMines();
        processBullets();

        MatrixUtils.clear(this.field);
        drawPanzers();
        drawBullets();
        drawMines();
    }

    @Override
    public void onStop()
    {

    }

    private void handleControlls()
    {
        if (skip == shouldSkip)
        {
            if (keyboardPasser.isKeyDown(CtrlKey.UP))
            {
                if (player.getDirection() == UP)
                {
                    int newPosY = player.getPosY() + 1;

                    if (newPosY + player.getSprite().getHeight() - 1 < this.field.getHeight())
                        player.setPosY(newPosY);
                } else
                {
                    player.setDirection(UP);
                }
            }

            if (keyboardPasser.isKeyDown(CtrlKey.DOWN))
            {
                if (player.getDirection() == DOWN)
                {
                    int newPosY = player.getPosY() - 1;

                    if (newPosY >= 0)
                        player.setPosY(newPosY);
                } else
                {
                    player.setDirection(DOWN);
                }
            }

            if (keyboardPasser.isKeyDown(CtrlKey.LEFT))
            {
                if (player.getDirection() == LEFT)
                {
                    int newPosX = player.getPosX() - 1;

                    if (newPosX >= 0)
                        player.setPosX(newPosX);
                } else
                {
                    player.setDirection(LEFT);
                }
            }

            if (keyboardPasser.isKeyDown(CtrlKey.RIGHT))
            {
                if (player.getDirection() == RIGHT)
                {
                    int newPosX = player.getPosX() + 1;

                    if (newPosX + player.getSprite().getWidth() - 1 < this.field.getWidth())
                        player.setPosX(newPosX);
                } else
                {
                    player.setDirection(RIGHT);
                }
            }

            if (keyboardPasser.isKeyDown(CtrlKey.ENTER))
            {
                shoot();
            }
        }

        if (++skip > shouldSkip)
            skip = 0;
    }

    private void drawPanzers()
    {
        panzerList.forEach((panzer) ->
        {
            PixelMatrix sprite = panzer.getSprite();

            for (int i = 0; i < sprite.getHeight(); i++)
            {

                for (int j = 0; j < sprite.getWidth(); j++)
                {
                    try
                    {
                        if (sprite.getPixel(j, i) != null)
                            this.field.setPixel(panzer.getPosX() + j, panzer.getPosY() + i, sprite.getPixel(j, i));
                    } catch (Exception e)
                    {

                    }
                }
            }
        });
    }

    private void drawBullets()
    {
        bulletList.forEach((bullet) ->
        {
            this.field.setPixel(bullet.getPosX(), bullet.getPosY(), BLACK);
        });
    }

    private void drawMines()
    {
        if (!(blink % blinkOn == 0))
        {
            mineList.forEach((mine) ->
            {
                this.field.setPixel(mine.getPosX(), mine.getPosY(), BLACK);
            });
        }

        blink++;
    }

    private void generateMines()
    {
        Random rnd = new Random();
        if (rnd.nextInt((int) (75 - (this.parameters.score.get() / 1)
                                                + mineList.size() * 1.5)) == 0)
        {
            int enemyPosX = rnd.nextInt(this.field.getWidth());
            int enemyPosY = rnd.nextInt(this.field.getHeight());

            if (!(enemyPosX == player.getPosX()) && enemyPosY == player.getPosY())
            {
                mineList.add(new Mine(enemyPosX, enemyPosY));
            }
        }
    }

    private void shoot()
    {
        if (weaponCooldown > 0)
        {
            final Bullet bullet = player.shoot();

            bulletList.removeIf(b -> b.getPosY() == bullet.getPosY()
                    && b.getPosX() == bullet.getPosX());

            bulletList.add(bullet);

            weaponCooldown = -minWeaponCooldown;
        }
    }

    private void processBullets()
    {
        for (Iterator<Bullet> i = bulletList.iterator(); i.hasNext();)
        {
            Bullet bullet = i.next();

            if (bulletMove % bullet.getSpeed() == 0)
            {
                switch (bullet.getDirection())
                {
                    case UP:
                        bullet.setPosY(bullet.getPosY() + 1);
                        break;
                    case DOWN:
                        bullet.setPosY(bullet.getPosY() - 1);
                        break;
                    case RIGHT:
                        bullet.setPosX(bullet.getPosX() + 1);
                        break;
                    case LEFT:
                        bullet.setPosX(bullet.getPosX() - 1);
                        break;
                }
            }

            if (mineList.removeIf(mine -> (mine.getPosY() == bullet.getPosY()
                    && mine.getPosX() == bullet.getPosX())))
            {
                this.parameters.score.inc();
                i.remove();
            }

            if (bullet.getPosY() < 0 || bullet.getPosX() < 0
                    || bullet.getPosY() >= this.field.getHeight()
                    || bullet.getPosX() >= this.field.getWidth())
            {
                i.remove();
            }
        }

        bulletMove++;
        weaponCooldown++;
    }

}
