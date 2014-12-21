package ua.ilnicki.jbgm.system.processors;

import java.util.List;
import ua.ilnicki.jbgm.data.DataCluster;
import ua.ilnicki.jbgm.game.GameInfo;
import ua.ilnicki.jbgm.machine.Field;
import ua.ilnicki.jbgm.machine.Keyboard;
import ua.ilnicki.jbgm.machine.Keyboard.CtrlKey;
import ua.ilnicki.jbgm.machine.Keyboard.SysKey;
import ua.ilnicki.jbgm.machine.Layer;
import ua.ilnicki.jbgm.pixelmatrix.MatrixUtils;
import ua.ilnicki.jbgm.pixelmatrix.PixelMatrix;
import ua.ilnicki.jbgm.pixelmatrix.PixelMatrixLoader;
import ua.ilnicki.jbgm.system.GameManager;
import ua.ilnicki.jbgm.system.Module;
import ua.ilnicki.jbgm.system.SystemManager;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class DefaultGameLauncher implements Module
{

    private GameManager gameManager;
    private SystemManager systemManager;
    private PixelMatrixLoader matrixLoader;

    private DataCluster config;
    private Keyboard keyboard;
    private Field field;

    private Layer logoLayer;
    private Layer prevLayer;
    private Layer argLayer;

    private int argument = 1;

    private List<GameInfo> gameInfoList;

    public void init(GameManager gameManager, SystemManager systemManager)
    {
        this.gameManager = gameManager;
        this.systemManager = systemManager;
        this.matrixLoader = this.systemManager.createMatrixLoader("characters");

        this.config = this.systemManager.getConfigManager().getCluster(this);
        this.keyboard = this.systemManager.getMachine().getKeyboard();

        this.gameInfoList = this.gameManager.getGameInfoList();

        this.field = this.systemManager.getMachine().getField();

        this.logoLayer = new Layer(10, 5);
        this.logoLayer.setPosition(0, 15);
        this.field.getLayers().add(this.logoLayer);

        this.prevLayer = new Layer(10, 7);
        this.prevLayer.setPosition(0, 6);
        this.field.getLayers().add(prevLayer);

        this.argLayer = new Layer(10, 5);
        this.argLayer.setPosition(0, 0);
        this.field.getLayers().add(argLayer);
    }

    @Override
    public void onLoad()
    {
        drawLogo();
        drawPrewiev();
        drawArgument();
    }

    private int selectedGame = 0;

    @Override
    public void onTick(long tick)
    {
        int downKeyTime = this.keyboard.ctrlKeyDownTicksCount(CtrlKey.DOWN);
        if (downKeyTime == 0 || (downKeyTime % 8 == 0 && downKeyTime > 24))
        {
            if (++this.argument == 100)
                this.argument = 1;

            drawArgument();
        }

        int upKeyTime = this.keyboard.ctrlKeyDownTicksCount(CtrlKey.UP);
        if (upKeyTime == 0 || (upKeyTime % 8 == 0 && upKeyTime > 24))
        {
            if (--this.argument == 0)
                this.argument = 99;

            drawArgument();
        }

        if (this.keyboard.ctrlKeyDownTicksCount(CtrlKey.RIGHT) == 3)
        {
            this.systemManager.getMachine().getParameters().speed.inc();
        }

        if (this.keyboard.ctrlKeyDownTicksCount(CtrlKey.LEFT) == 3)
        {
            this.systemManager.getMachine().getParameters().level.inc();
        }

        if (this.keyboard.ctrlKeyDownTicksCount(CtrlKey.ROTATE) == 3)
        {
            if (++this.selectedGame == this.gameInfoList.size())
                this.selectedGame = 0;

            drawLogo();
            drawPrewiev();
        }

        if (this.keyboard.sysKeyDownTicksCount(SysKey.START) == 0)
        {
            this.gameManager.launchGame(this.gameInfoList.get(selectedGame), 0);
        }
    }

    @Override
    public void onStop()
    {

    }

    private void drawLogo()
    {
        this.logoLayer.setPixelMatrix(this.gameInfoList.get(
                        this.selectedGame).getLogo());
    }

    private void drawPrewiev()
    {
        this.prevLayer.setPixelMatrix(this.gameInfoList.get(
                        this.selectedGame).getPreview());
    }

    private void drawArgument()
    {
        MatrixUtils.clear(this.argLayer);

        String[] nums = String.format("%02d", argument).split("");

        int cursorX = 1;
        int cursorY = 0;

        for (String num : nums)
        {
            PixelMatrix numMatrix = this.matrixLoader.load(num, true);

            for (int i = 0; i < numMatrix.getHeight(); i++)
                for (int j = 0; j < numMatrix.getWidth(); j++)
                    this.argLayer.setPixel(cursorX + j,
                            cursorY + i,
                            numMatrix.getPixel(j, i));

            cursorX = cursorX + numMatrix.getWidth() + 1;
        }
    }
}
