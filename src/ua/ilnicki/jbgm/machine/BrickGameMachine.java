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
    public final Parameters parameters;

    /**
     *
     */
    public final BrickGameBoolParameter pause;

    /**
     *
     */
    protected final Keyboard keyboard;

    /**
     *
     */
    public final KeyboardPasser keyboardPasser;

    /**
     *
     */
    public final Field field;

    /**
     *
     */
    public final Screen screen;

    public BrickGameMachine()
    {
        this.parameters = new Parameters();
        this.pause = new BrickGameBoolParameter(false);

        this.keyboard = new Keyboard();
        this.keyboardPasser = this.keyboard.getKeyboardPasser();

        this.field = new Field(200, 100);
        this.screen = new Screen(20, 10, this.field);
    }
}
