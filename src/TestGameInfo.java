
import ua.ilnicki.jbgm.game.Game;
import ua.ilnicki.jbgm.game.GameInfo;
import ua.ilnicki.jbgm.game.test.TestGame;
import ua.ilnicki.jbgm.pixelmatrix.PixelMatrix;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class TestGameInfo implements GameInfo
{

    @Override
    public String getName()
    {
        return "test";
    }

    @Override
    public String getVersion()
    {
        return "test";
    }

    @Override
    public String getAuthor()
    {
        return "test";
    }

    @Override
    public String getDescription()
    {
        return "test";
    }

    @Override
    public String getWebSite()
    {
        return "test";
    }

    @Override
    public PixelMatrix getLogo()
    {
        return null;
    }

    @Override
    public PixelMatrix getPreview()
    {
        return null;
    }

    @Override
    public Class<? extends Game> getGameClass()
    {
        return TestGame.class;
    }

}
