package ua.ilnicki.jbgm.io.lwjgl2;

import java.util.HashMap;
import java.util.Map.Entry;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glVertex2i;
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
import static ua.ilnicki.jbgm.pixelmatrix.Pixel.*;
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

    private static final int pixelSize = 20;
    private static final int pixelDecorSize = pixelSize - 4;
    private static final int pixelInnerSize = pixelDecorSize - 4;
    private static final int pixelDistance = 3;
    private static final int borderSize = 20;

    @Override
    public void init(Machine machine, ConfigManager configManager) throws LWJGLException
    {
        this.screen = machine.getScreen();
        this.machine = machine;
        this.config = configManager.getCluster(this);

        this.ctrlKeyMap = loadKeyMap("CtrlKeyMap", CtrlKey.class);
        this.sysKeyMap = loadKeyMap("SysKeyMap", SysKey.class);
        
        /*this.ctrlKeyMap = new HashMap<>();
        this.ctrlKeyMap.put(KEY_UP, CtrlKey.UP);
        this.ctrlKeyMap.put(KEY_RIGHT, CtrlKey.RIGHT);
        this.ctrlKeyMap.put(KEY_DOWN, CtrlKey.DOWN);
        this.ctrlKeyMap.put(KEY_LEFT, CtrlKey.LEFT);
        this.ctrlKeyMap.put(KEY_SPACE, CtrlKey.ENTER);
        
        this.config.putValue("CtrlKeyMap", this.ctrlKeyMap);

        this.sysKeyMap = new HashMap<>();
        this.sysKeyMap.put(KEY_RETURN, SysKey.START);
        this.sysKeyMap.put(KEY_TAB, SysKey.SOUND);
        this.sysKeyMap.put(KEY_ESCAPE, SysKey.ONOFF);
        
        this.config.putValue("SysKeyMap", this.sysKeyMap);*/

        Display.setTitle("Brick Game");
        Display.setFullscreen(false);
        Display.setDisplayMode(new DisplayMode(
                borderSize * 2 + pixelSize * this.screen.getWidth()
                + pixelDistance * (this.screen.getWidth() - 1 + 30),
                borderSize * 2 + pixelSize * this.screen.getHeight()
                + pixelDistance * (this.screen.getHeight() - 1)));
        Display.setVSyncEnabled(true);
        Display.create();
        
        glEnable(GL_TEXTURE_2D);
        glDisable(GL_DEPTH_TEST);
        
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0.0, Display.getDisplayMode().getWidth(), 0.0, Display.getDisplayMode().getHeight(), -1.0, 1.0);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        glViewport(0, 0, Display.getDisplayMode().getWidth(), Display.getDisplayMode().getHeight());

        glClearColor(0.60f, 0.70f, 0.65f, 1.0f);
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
            drawScreen();
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
                drawScreen();
            }
        }
        
        System.out.println(this.machine.getParameters().score.get());
    }

    @Override
    public void onStop()
    {
        Display.destroy();
    }

    private void drawScreen()
    {
        glClear(GL_COLOR_BUFFER_BIT);

        for (int i = 0; i < this.screen.getHeight(); i++)
        {
            for (int j = 0; j < this.screen.getWidth(); j++)
            {
                Pixel currentColor = this.screen.getPixel(j, i) != null
                        ? this.screen.getPixel(j, i) : WHITE;

                int pixelCurrentPositionY = borderSize + (pixelSize + pixelDistance) * j;
                int pixelCurrentPositionX = borderSize + (pixelSize + pixelDistance) * i;

                switch (currentColor)
                {
                    case WHITE:
                        glColor3f(0.55f, 0.50f, 0.65f);
                        break;
                    case BLACK:
                        glColor3f(0.0f, 0.0f, 0.0f);
                        break;
                }

                glBegin(GL_QUADS);
                glVertex2i(pixelCurrentPositionY,
                        pixelCurrentPositionX);
                glVertex2i(pixelCurrentPositionY + pixelSize,
                        pixelCurrentPositionX);
                glVertex2i(pixelCurrentPositionY + pixelSize,
                        pixelCurrentPositionX + pixelSize);
                glVertex2i(pixelCurrentPositionY,
                        pixelCurrentPositionX + pixelSize);
                glEnd();

                glColor3f(0.60f, 0.70f, 0.65f);
                glBegin(GL_QUADS);
                glVertex2i(pixelCurrentPositionY + pixelSize - pixelDecorSize,
                        pixelCurrentPositionX + pixelSize - pixelDecorSize);
                glVertex2i(pixelCurrentPositionY + pixelDecorSize,
                        pixelCurrentPositionX + pixelSize - pixelDecorSize);
                glVertex2i(pixelCurrentPositionY + pixelDecorSize,
                        pixelCurrentPositionX + pixelDecorSize);
                glVertex2i(pixelCurrentPositionY + pixelSize - pixelDecorSize,
                        pixelCurrentPositionX + pixelDecorSize);
                glEnd();

                switch (currentColor)
                {
                    case WHITE:
                        glColor3f(0.55f, 0.50f, 0.65f);
                        break;
                    case BLACK:
                        glColor3f(0.0f, 0.0f, 0.0f);
                        break;
                }

                glBegin(GL_QUADS);
                glVertex2i(pixelCurrentPositionY + pixelSize - pixelInnerSize,
                        pixelCurrentPositionX + pixelSize - pixelInnerSize);
                glVertex2i(pixelCurrentPositionY + pixelInnerSize,
                        pixelCurrentPositionX + pixelSize - pixelInnerSize);
                glVertex2i(pixelCurrentPositionY + pixelInnerSize,
                        pixelCurrentPositionX + pixelInnerSize);
                glVertex2i(pixelCurrentPositionY + pixelSize - pixelInnerSize,
                        pixelCurrentPositionX + pixelInnerSize);
                glEnd();
            }
        }
    }

    private void keyboardUpdate()
    {
        for (Entry<Integer, CtrlKey> key : this.ctrlKeyMap.entrySet())
        {
            if (org.lwjgl.input.Keyboard.isKeyDown(key.getKey()))
            {
                this.keyboard.updateCtrlKeyState(key.getValue(), true);
            }
            else
            {
                this.keyboard.updateCtrlKeyState(key.getValue(), false);
            }
        }
        
        for (Entry<Integer, SysKey> key : this.sysKeyMap.entrySet())
        {
            if (org.lwjgl.input.Keyboard.isKeyDown(key.getKey()))
            {
                this.keyboard.updateSysKeyState(key.getValue(), true);
            }
            else
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
        
        for(Entry<String, String> strKey : textKeyMap.entrySet())
        {
            Integer key = Integer.parseInt(strKey.getKey());
            T value = Enum.valueOf(enumType, strKey.getValue());
            
            keyMap.put(key, value);
        }
        
        return keyMap;
    }
}
