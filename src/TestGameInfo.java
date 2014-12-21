
import ua.ilnicki.jbgm.game.Game;
import ua.ilnicki.jbgm.game.GameInfo;
import ua.ilnicki.jbgm.game.test.TestGame;
import ua.ilnicki.jbgm.pixelmatrix.MatrixUtils;
import ua.ilnicki.jbgm.pixelmatrix.Pixel;
import static ua.ilnicki.jbgm.pixelmatrix.Pixel.*;
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

    private final PixelMatrix logo = MatrixUtils.makeFromArray(new Pixel[][]
    {
        {
            BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK
        },
        {
            BLACK,  null,  null,  null,  null,  null,  null,  null,  null, BLACK
        },
        {
            BLACK,  null,  null,  null,  null,  null,  null,  null,  null, BLACK
        },
        {
            BLACK,  null,  null,  null,  null,  null,  null,  null,  null, BLACK
        },
        {
            BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK
        }
    });

    private final PixelMatrix preview = MatrixUtils.makeFromArray(new Pixel[][]
    {
        {
            BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK
        },
        {
            BLACK,  null,  null,  null,  null,  null,  null,  null,  null, BLACK
        },   
        {   
            BLACK,  null,  null,  null,  null,  null,  null,  null,  null, BLACK
        },   
        {   
            BLACK,  null,  null,  null,  null,  null,  null,  null,  null, BLACK
        },   
        {   
            BLACK,  null,  null,  null,  null,  null,  null,  null,  null, BLACK
        },
        {
            BLACK,  null,  null,  null,  null,  null,  null,  null,  null, BLACK
        },
        {
            BLACK,  null,  null,  null,  null,  null,  null,  null,  null, BLACK
        },
        {
            BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK
        }
    });

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
        return this.logo;
    }

    @Override
    public PixelMatrix getPreview()
    {
        return this.preview;
    }

    @Override
    public int getBufferWidth()
    {
        return 10;
    }

    @Override
    public int getBufferHeight()
    {
        return 20;
    }

    @Override
    public Class<? extends Game> getGameClass()
    {
        return TestGame.class;
    }

}
