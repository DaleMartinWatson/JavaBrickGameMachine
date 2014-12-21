package ua.ilnicki.jbgm.machine;

import ua.ilnicki.jbgm.machine.Keyboard.KeyboardPasser;
import ua.ilnicki.jbgm.pixelmatrix.PixelMatrix;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class Machine
{

    public static class Parameters
    {

        /**
         *
         */
        public final IntParameter score;

        /**
         *
         */
        public final IntParameter hiscore;

        /**
         *
         */
        public final IntParameter speed;

        /**
         *
         */
        public final IntParameter level;

        private Parameters()
        {
            this.score = new IntParameter(0, 999999);
            this.hiscore = new IntParameter(0, 999999);
            this.speed = new IntParameter(1, 10);
            this.level = new IntParameter(1, 10);
        }
    }

    /**
     *
     */
    private final Parameters parameters;

    /**
     *
     */
    public final IntParameter volume;

    /**
     *
     */
    public final BoolParameter pause;

    /**
     *
     */
    private final Keyboard keyboard;

    /**
     *
     */
    private final KeyboardPasser keyboardPasser;

    /**
     *
     */
    private Field field;

    /**
     *
     */
    private final Screen screen;

    /**
     *
     */
    private final PixelMatrix helper;

    public Machine()
    {
        this.parameters = new Parameters();
        
        this.volume = new IntParameter(0, 3);
        this.pause = new BoolParameter(false);

        this.keyboard = new Keyboard();
        this.keyboardPasser = this.keyboard.getKeyboardPasser();

        this.field = new Field(10, 20);
        this.screen = new Screen(10, 20, this.field);
        this.helper = new PixelMatrix(4, 4);
    }

    public Parameters getParameters()
    {
        return parameters;
    }

    public Keyboard getKeyboard()
    {
        return keyboard;
    }

    public KeyboardPasser getKeyboardPasser()
    {
        return keyboardPasser;
    }

    public Field getField()
    {
        return field;
    }

    public Screen getScreen()
    {
        return screen;
    }

    public PixelMatrix getHelper()
    {
        return this.helper;
    }

    public void recreateField(int width, int height)
    {
        setField(new Field(width, height));
    }
    
    public void setField(Field field)
    {
        this.field = field;
        this.screen.setField(this.field);
    }
}
