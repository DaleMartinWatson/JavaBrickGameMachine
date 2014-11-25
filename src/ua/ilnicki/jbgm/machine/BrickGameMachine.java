package ua.ilnicki.jbgm.machine;

import ua.ilnicki.jbgm.machine.Keyboard.KeyboardPasser;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class BrickGameMachine
{
    public static class Parameters
    {
        /**
         *
         */
        public final BrickGameIntParameter score;

        /**
         *
         */
        public final BrickGameIntParameter hiscore;

        /**
         *
         */
        public final BrickGameIntParameter speed;

        /**
         *
         */
        public final BrickGameIntParameter level;

        /**
         *
         */
        public final BrickGameIntParameter volume;

        private Parameters()
        {
            this.score = new BrickGameIntParameter(0, 999999);
            this.hiscore = new BrickGameIntParameter(0, 999999);
            this.speed = new BrickGameIntParameter(0, 9);
            this.level = new BrickGameIntParameter(0, 9);
            this.volume = new BrickGameIntParameter(0, 3);
        }
    }

    /**
     *
     */
    private final Parameters parameters;

    /**
     *
     */
    public final BrickGameBoolParameter pause;

    /**
     *
     */
    public final Keyboard keyboard;

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

    public BrickGameMachine()
    {
        this.parameters = new Parameters();
        this.pause = new BrickGameBoolParameter(false);

        this.keyboard = new Keyboard();
        this.keyboardPasser = this.keyboard.getKeyboardPasser();

        this.field = new Field(20, 10);
        this.screen = new Screen(20, 10, this.field);
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
    
    public void recreateField(int height, int width) 
    {
        this.field = new Field(height, width);
        this.screen.setField(this.field);
    }

    public Screen getScreen()
    {
        return screen;
    }
}
