package ua.ilnicki.jbgm.io.lwjgl2;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.imageio.ImageIO;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glColor3ub;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLineWidth;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.opengl.GL11.glViewport;
import ua.ilnicki.jbgm.data.DataCluster;
import ua.ilnicki.jbgm.io.Drawer;
import ua.ilnicki.jbgm.io.KeyReader;
import ua.ilnicki.jbgm.machine.Keyboard;
import ua.ilnicki.jbgm.machine.Keyboard.CtrlKey;
import ua.ilnicki.jbgm.machine.Keyboard.SysKey;
import ua.ilnicki.jbgm.machine.Machine;
import ua.ilnicki.jbgm.machine.Screen;
import ua.ilnicki.jbgm.pixelmatrix.Pixel;
import static ua.ilnicki.jbgm.pixelmatrix.Pixel.BLACK;
import static ua.ilnicki.jbgm.pixelmatrix.Pixel.WHITE;
import ua.ilnicki.jbgm.pixelmatrix.PixelMatrix;
import ua.ilnicki.jbgm.system.ConfigManager;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class Lwjgl2 implements Drawer, KeyReader
{

    private Screen screen;
    private Machine machine;
    private DataCluster config;

    private Keyboard keyboard;
    private HashMap<Integer, CtrlKey> ctrlKeyMap;
    private HashMap<Integer, SysKey> sysKeyMap;

    private static final float pixelSize = 24.0f;
    private static final float pixelDecorSize = pixelSize - pixelSize / 6;
    private static final float pixelInnerSize = pixelDecorSize - pixelSize / 6;
    private static final float pixelDistance = pixelSize / 8;
    private static final float borderSize = pixelSize;
    private static final float borderLineWidth = borderSize / 5.0f;

    private Color bgColor;
    private Color disColor;
    private Color fgColor;

    @Override
    public void init(Machine machine, ConfigManager configManager) throws LWJGLException
    {
        this.bgColor = new Color(0x6D, 0x77, 0x5C);
        this.disColor = new Color(0x60, 0x6F, 0x5C);
        this.fgColor = new Color(0x0, 0x0, 0x0);

        this.screen = machine.getScreen();
        this.machine = machine;
        this.config = configManager.getCluster(this);

        this.ctrlKeyMap = loadKeyMap("CtrlKeyMap", CtrlKey.class);
        this.sysKeyMap = loadKeyMap("SysKeyMap", SysKey.class);

        Display.setTitle("Brick Game");
        Display.setFullscreen(false);
        /* Some magic calculations */
        Display.setDisplayMode(new DisplayMode(
                (int) (borderSize * 3 + pixelSize * this.screen.getWidth()
                + pixelDistance * (this.screen.getWidth() - 1)
                + (pixelSize * this.machine.getHelper().getWidth()
                + pixelDistance * (this.machine.getHelper().getWidth() - 1))),
                (int) (borderSize * 2 + pixelSize * this.screen.getHeight()
                + pixelDistance * (this.screen.getHeight() - 1))));
        Display.setVSyncEnabled(true);

        try
        {
            Display.setIcon(new ByteBuffer[]
            {
                loadIcon("icon16.png", 16, 16),
                loadIcon("icon32.png", 32, 32),
                loadIcon("icon64.png", 64, 64)
            });
        } catch (IOException e)
        {

        }

        Display.create();

        glEnable(GL_TEXTURE_2D);
        glDisable(GL_DEPTH_TEST);

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0.0, Display.getDisplayMode().getWidth(), 0.0, Display.getDisplayMode().getHeight(), -1.0, 1.0);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        glViewport(0, 0, Display.getDisplayMode().getWidth(), Display.getDisplayMode().getHeight());

        glClearColor(bgColor.getFloatR(),
                bgColor.getFloatG(),
                bgColor.getFloatB(), 1.0f);
    }

    @Override
    public void init(Keyboard keyboard, ConfigManager configManager)
    {
        this.keyboard = keyboard;
    }

    @Override
    public void onLoad()
    {

    }

    @Override
    public void onTick(long tick)
    {
        Display.update();
        keyboardUpdate();

        if (Display.isCloseRequested())
        {
            this.keyboard.updateSysKeyState(Keyboard.SysKey.ONOFF, true);
        } else if (Display.isActive())
        {
            draw();
        } else
        {
            try
            {
                Thread.sleep(100);
            } catch (InterruptedException e)
            {
                e.printStackTrace(System.err);
            }

            if (Display.isVisible() || Display.isDirty())
            {
                draw();
            }
        }
    }

    @Override
    public void onStop()
    {
        Display.destroy();
    }

    private void draw()
    {
        glClear(GL_COLOR_BUFFER_BIT);
        drawBorder();
        drawField();
        drawScoreAndHiscore();
        drawHelper();
        drawSpeedAndLevel();
        drawVolume();
        drawPause();
    }

    private void drawBorder()
    {
        glLineWidth(borderLineWidth);

        glColor3ub(fgColor.getR(), fgColor.getG(), fgColor.getB());

        float borderX = borderSize / 2.0f;
        float borderY = borderSize / 2.0f;
        float borderWidth = pixelSize * this.screen.getWidth()
                + pixelDistance * (this.screen.getWidth() - 1) + borderSize;
        float borderHeight = pixelSize * this.screen.getHeight()
                + pixelDistance * (this.screen.getHeight() - 1) + borderSize;

        //Top line
        glBegin(GL_LINES);
        glVertex2f(borderX - borderLineWidth / 2, borderY + borderHeight);
        glVertex2f(borderX + borderWidth + borderLineWidth / 2, borderY + borderHeight);
        glEnd();

        //Bottom line
        glBegin(GL_LINES);
        glVertex2f(borderX - borderLineWidth / 2, borderY);
        glVertex2f(borderX + borderWidth + borderLineWidth / 2, borderY);
        glEnd();

        //Left line
        glBegin(GL_LINES);
        glVertex2f(borderX, borderY);
        glVertex2f(borderX, borderY + borderHeight);
        glEnd();

        //Right line
        glBegin(GL_LINES);
        glVertex2f(borderX + borderWidth, borderY + borderHeight);
        glVertex2f(borderX + borderWidth, borderY);
        glEnd();
    }

    private void drawField()
    {
        for (int i = 0; i < this.screen.getHeight(); i++)
        {
            for (int j = 0; j < this.screen.getWidth(); j++)
            {
                Pixel pixelColor = this.screen.getPixel(j, i);

                drawPixel(borderSize + (pixelSize + pixelDistance) * j,
                        borderSize + (pixelSize + pixelDistance) * i,
                        pixelColor);
            }
        }
    }

    private void drawScoreAndHiscore()
    {
        float posX = borderSize + (pixelSize + pixelDistance)
                * this.screen.getWidth() + borderSize - borderLineWidth + segmentWidth;
        float posY = borderSize * 2
                + (pixelSize + pixelDistance) * this.screen.getHeight()
                - (borderSize * 1.5f);

        float factor = 1.25f;

        drawString(posX, posY, "HI-SCORE");
        drawNumber(posX, posY - segmentSize * factor,
                this.machine.getParameters().hiscore.get(), 6);

        drawString(posX, posY - segmentSize * factor * 2, "SCORE");
        drawNumber(posX, posY - segmentSize * factor * 3,
                this.machine.getParameters().score.get(), 6);
    }

    private void drawHelper()
    {
        PixelMatrix helper = machine.getHelper();

        float posX = borderSize + (pixelSize + pixelDistance)
                * this.screen.getWidth() + borderSize - borderLineWidth;
        float posY = borderSize + (pixelSize + pixelDistance)
                * this.screen.getHeight() + borderSize
                - ((pixelSize + pixelDistance) * helper.getHeight() * 2 + borderSize);

        for (int i = 0; i < helper.getHeight(); i++)
        {
            for (int j = 0; j < helper.getWidth(); j++)
            {
                Pixel pixelColor = helper.getPixel(j, i);

                float pixelX = posX + (pixelSize + pixelDistance) * j;
                float pixelY = posY + (pixelSize + pixelDistance) * i;

                drawPixel(pixelX, pixelY, pixelColor);
            }
        }
    }

    private void drawSpeedAndLevel()
    {
        float posX = borderSize + (pixelSize + pixelDistance)
                * this.screen.getWidth() + borderSize - borderLineWidth + segmentWidth;
        float posY = borderSize + (pixelSize + pixelDistance)
                * this.screen.getHeight() + borderSize
                - ((pixelSize + pixelDistance) * machine.getHelper().getHeight() * 2.3f + borderSize);

        drawNumber(posX, posY,
                this.machine.getParameters().speed.get(), 2);
        drawString(posX, posY - segmentSize * 1.5f, "SPEED");

        drawNumber(posX + (segmentWidth * 10), posY,
                this.machine.getParameters().level.get(), 2);
        drawString(posX + (segmentWidth * 10), posY - segmentSize * 1.5f, "LEVEL");
    }

    private static final float volumeIconSize = pixelSize / 2;

    private void drawVolume()
    {
        float posX = borderSize + (pixelSize + pixelDistance)
                * this.screen.getWidth() + borderSize - borderLineWidth
                + (segmentWidth * 1.5f) * 4;
        float posY = borderSize + segmentSize * 2;

        setColor(true);

        glBegin(GL_QUADS);
        glVertex2f(posX, posY);
        glVertex2f(posX + volumeIconSize / 2, posY);
        glVertex2f(posX + volumeIconSize / 2, posY + volumeIconSize);
        glVertex2f(posX, posY + volumeIconSize);
        glEnd();

        glBegin(GL_TRIANGLES);
        glVertex2f(posX, posY + volumeIconSize / 2);
        glVertex2f(posX + volumeIconSize, posY + volumeIconSize * 1.5f);
        glVertex2f(posX + volumeIconSize, posY - volumeIconSize * 0.5f);
        glEnd();

        int volume = this.machine.volume.get();

        glLineWidth(volumeIconSize / 4);

        if (volume == 0)
        {
            glBegin(GL_LINES);
            glVertex2f(posX + volumeIconSize * 1.5f, posY);
            glVertex2f(posX + volumeIconSize * 1.5f + volumeIconSize / 2, posY + volumeIconSize);
            glEnd();

            glBegin(GL_LINES);
            glVertex2f(posX + volumeIconSize * 1.5f, posY + volumeIconSize);
            glVertex2f(posX + volumeIconSize * 1.5f + volumeIconSize / 2, posY);
            glEnd();
        } else
        {
            for (int i = 0; i < volume; i++)
            {
                glBegin(GL_LINES);
                glVertex2f(posX + volumeIconSize * 1.5f
                        + (volumeIconSize / 2 * i),
                        posY + (volumeIconSize / 4 * i) + volumeIconSize);
                glVertex2f(posX + volumeIconSize * 1.5f
                        + (volumeIconSize / 2 * i),
                        posY - (volumeIconSize / 4 * i));
                glEnd();
            }
        }
    }

    private void drawPause()
    {
        float posX = borderSize + (pixelSize + pixelDistance)
                * this.screen.getWidth() + borderSize - borderLineWidth
                + (segmentWidth * 1.5f) * 4;
        float posY = borderSize;

        if (this.machine.pause.get())
        {
            drawString(posX, posY, "PAUSE");
        } else
        {
            drawString(posX, posY, "     ");
        }
    }

    private void drawPixel(float x, float y, Pixel pixel)
    {
        setColor(pixel);
        glBegin(GL_QUADS);
        glVertex2f(x, y);
        glVertex2f(x + pixelSize, y);
        glVertex2f(x + pixelSize, y + pixelSize);
        glVertex2f(x, y + pixelSize);
        glEnd();

        glColor3ub(bgColor.getR(), bgColor.getG(), bgColor.getB());
        glBegin(GL_QUADS);
        glVertex2f(x + pixelSize - pixelDecorSize, y + pixelSize - pixelDecorSize);
        glVertex2f(x + pixelDecorSize, y + pixelSize - pixelDecorSize);
        glVertex2f(x + pixelDecorSize, y + pixelDecorSize);
        glVertex2f(x + pixelSize - pixelDecorSize, y + pixelDecorSize);
        glEnd();

        setColor(pixel);
        glBegin(GL_QUADS);
        glVertex2f(x + pixelSize - pixelInnerSize, y + pixelSize - pixelInnerSize);
        glVertex2f(x + pixelInnerSize, y + pixelSize - pixelInnerSize);
        glVertex2f(x + pixelInnerSize, y + pixelInnerSize);
        glVertex2f(x + pixelSize - pixelInnerSize, y + pixelInnerSize);
        glEnd();
    }

    private void drawNumber(float x, float y, int num, int digitCount)
    {
        x = x + (segmentWidth * 1.5f * digitCount);

        for (int i = digitCount - 1; i >= 0; i--)
        {
            Integer digit = num % (int) Math.pow(10, i + 1) / (int) Math.pow(10, i);

            if (digit == 0 && num < Math.pow(10, i + 1)) //Disabling segments on left zeros
                digit = null;

            drawSegmentChar(x - (segmentWidth * 1.5f) * i, y,
                    SegmentShematic.getSchematic(digit));
        }
    }

    private void drawString(float x, float y, String string)
    {
        for (int i = 0; i < string.length(); i++)
        {
            drawSegmentChar(x + (segmentWidth * 1.5f) * i, y,
                    SegmentShematic.getSchematic(string.charAt(i)));
        }
    }

    private static final float segmentSize = pixelSize;
    private static final float segmentHeight = segmentSize / 2;
    private static final float segmentWidth = segmentSize / 4;
    private static final float segmentThick = segmentSize / 15;
    private static final float segmentIdent = segmentThick / 2;

    private void drawSegmentChar(float x, float y, boolean[] data)
    {
        glLineWidth(segmentThick);

        //"a" segment
        setColor(data[0]);
        glBegin(GL_LINES);
        glVertex2f(x + segmentIdent, y + segmentHeight * 2);
        glVertex2f(x + segmentWidth - segmentIdent, y + segmentHeight * 2);
        glEnd();

        //"b" segment
        setColor(data[1]);
        glBegin(GL_LINES);
        glVertex2f(x + segmentWidth, y + segmentHeight * 2 - segmentIdent);
        glVertex2f(x + segmentWidth, y + segmentHeight + segmentIdent);
        glEnd();

        //"c" segment
        setColor(data[2]);
        glBegin(GL_LINES);
        glVertex2f(x + segmentWidth, y + segmentHeight - segmentIdent);
        glVertex2f(x + segmentWidth, y + segmentIdent);
        glEnd();

        //"d" segment
        setColor(data[3]);
        glBegin(GL_LINES);
        glVertex2f(x + segmentIdent, y);
        glVertex2f(x + segmentWidth - segmentIdent, y);
        glEnd();

        //"e" segment
        setColor(data[4]);
        glBegin(GL_LINES);
        glVertex2f(x, y + segmentHeight - segmentIdent);
        glVertex2f(x, y + segmentIdent);
        glEnd();

        //"f" segment
        setColor(data[5]);
        glBegin(GL_LINES);
        glVertex2f(x, y + segmentHeight * 2 - segmentIdent);
        glVertex2f(x, y + segmentHeight + segmentIdent);
        glEnd();

        //"g" segment
        setColor(data[6]);
        glBegin(GL_LINES);
        glVertex2f(x + segmentIdent, y + segmentHeight);
        glVertex2f(x + segmentWidth - segmentIdent, y + segmentHeight);
        glEnd();

        if (data.length == 9)
        {
            //"h" segment
            setColor(data[7]);
            glBegin(GL_LINES);
            glVertex2f(x + segmentThick, y + segmentHeight * 2 - segmentIdent);
            glVertex2f(x + segmentWidth - segmentThick, y + segmentHeight + segmentIdent);
            glEnd();

            //"i" segment
            setColor(data[8]);
            glBegin(GL_LINES);
            glVertex2f(x + segmentThick, y + segmentHeight - segmentIdent);
            glVertex2f(x + segmentWidth - segmentThick, y + segmentIdent);
            glEnd();
        }
    }

    private void setColor(Pixel pixel)
    {
        if (pixel == null)
            pixel = WHITE;

        setColor(pixel == BLACK);
    }

    private void setColor(boolean enabled)
    {
        if (enabled)
            glColor3ub(fgColor.getR(), fgColor.getG(), fgColor.getB());
        else
            glColor3ub(disColor.getR(), disColor.getG(), disColor.getB());
    }

    private void keyboardUpdate()
    {
        for (Entry<Integer, CtrlKey> key : this.ctrlKeyMap.entrySet())
        {
            if (org.lwjgl.input.Keyboard.isKeyDown(key.getKey()))
            {
                this.keyboard.updateCtrlKeyState(key.getValue(), true);
            } else
            {
                this.keyboard.updateCtrlKeyState(key.getValue(), false);
            }
        }

        for (Entry<Integer, SysKey> key : this.sysKeyMap.entrySet())
        {
            if (org.lwjgl.input.Keyboard.isKeyDown(key.getKey()))
            {
                this.keyboard.updateSysKeyState(key.getValue(), true);
            } else
            {
                this.keyboard.updateSysKeyState(key.getValue(), false);
            }
        }
    }

    //Костыль: перенести в GsonDataProvider
    private <T extends Enum<T>> HashMap<Integer, T> loadKeyMap(String name, Class<T> enumType)
    {
        HashMap<String, String> textKeyMap = this.config.getValue(name, HashMap.class);
        HashMap<Integer, T> keyMap = new HashMap<>(textKeyMap.size());

        for (Entry<String, String> strKey : textKeyMap.entrySet())
        {
            Integer key = Integer.parseInt(strKey.getKey());
            T value = Enum.valueOf(enumType, strKey.getValue());

            keyMap.put(key, value);
        }

        return keyMap;
    }

    public ByteBuffer loadIcon(String filename, int width, int height) throws IOException
    {
        BufferedImage image = ImageIO.read(this.getClass().getClassLoader()
                .getResource("ua/ilnicki/jbgm/io/lwjgl2/res/" + filename));

        // convert image to byte array
        byte[] imageBytes = new byte[width * height * 4];
        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                int pixel = image.getRGB(j, i);
                for (int k = 0; k < 3; k++) // red, green, blue
                    imageBytes[(i * 16 + j) * 4 + k] = (byte) (((pixel >> (2 - k) * 8)) & 255);
                imageBytes[(i * 16 + j) * 4 + 3] = (byte) (((pixel >> (3) * 8)) & 255); // alpha
            }
        }
        return ByteBuffer.wrap(imageBytes);
    }
}
