package ua.ilnicki.jbgm.game.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import ua.ilnicki.jbgm.game.Game;
import ua.ilnicki.jbgm.machine.Field;
import ua.ilnicki.jbgm.machine.Keyboard.CtrlKey;
import ua.ilnicki.jbgm.machine.Keyboard.KeyboardPasser;
import ua.ilnicki.jbgm.machine.Machine.Parameters;
import ua.ilnicki.jbgm.pixelmatrix.MatrixUtils;
import ua.ilnicki.jbgm.pixelmatrix.PixelMatrix;
import ua.ilnicki.jbgm.pixelmatrix.PixelMatrixLoader;
import ua.ilnicki.jbgm.system.GameManager;
import ua.ilnicki.jbgm.system.SaveManager;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class TestGame implements Game
{

    private PixelMatrixLoader matrixLoader;

    private KeyboardPasser keyboardPasser;
    private GameManager gameManager;
    private Field field;
    private Parameters params;

    @Override
    public void init(GameManager gameManager,
            Field field,
            PixelMatrix helper,
            KeyboardPasser keyboardPasser,
            Parameters parameters,
            SaveManager saveManager,
            int argument)
    {
        this.gameManager = gameManager;
        this.keyboardPasser = keyboardPasser;
        this.params = parameters;
        this.field = field;
        this.matrixLoader = PixelMatrixLoader.create("characters", null);
    }

    @Override
    public void onLoad()
    {

    }

    @Override
    public void onTick(long tick)
    {
        System.out.print("Tick: " + tick + "; Keys: ");

        for (CtrlKey key : CtrlKey.values())
        {
            if (this.keyboardPasser.isKeyDown(key))
            {
                System.out.print(key + " ");
            }
        }

        System.out.println();

        if (this.keyboardPasser.isKeyDown(CtrlKey.UP)
                && this.keyboardPasser.isKeyDown(CtrlKey.LEFT)
                && this.keyboardPasser.isKeyDown(CtrlKey.RIGHT))
        {
            this.gameManager.exitGame();
        }
        
        if (this.keyboardPasser.keyDownTicksCount(CtrlKey.UP) == 5)
        {
            this.params.score.inc();
            this.params.hiscore.dec();
        }
        
        if (this.keyboardPasser.keyDownTicksCount(CtrlKey.DOWN) == 5)
        {
            this.params.score.dec();
            this.params.hiscore.inc();
        }

        printTime();
    }

    @Override
    public void onStop()
    {

    }

    @Override
    public void save()
    {

    }

    @Override
    public void recover()
    {

    }

    private void printTime()
    {
        MatrixUtils.clear(this.field);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        String date = sdf.format(cal.getTime());
        String[] nums = date.split("");

        int cursorX = 1;
        int cursorY = 15;

        for (int n = 0; n < nums.length; n++)
        {
            PixelMatrix numMatrix = this.matrixLoader.load(nums[n], true);

            for (int i = 0; i < numMatrix.getHeight(); i++)
                for (int j = 0; j < numMatrix.getWidth(); j++)
                    this.field.setPixel(cursorX + j,
                            cursorY + i,
                            numMatrix.getPixel(j, i));

            cursorX = cursorX + numMatrix.getWidth() + 1;
            
            if(cursorX + numMatrix.getWidth() + 1 > this.field.getWidth())
            {
                cursorX = 1;
                cursorY = cursorY - 7;
            }
        }
    }
}
