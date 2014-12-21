package ua.ilnicki.jbgm.game;

import ua.ilnicki.jbgm.pixelmatrix.PixelMatrix;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public interface GameInfo
{

    /**
     *
     * @return
     */
    public String getName();

    /**
     *
     * @return
     */
    public String getVersion();

    /**
     *
     * @return
     */
    public String getAuthor();

    /**
     *
     * @return
     */
    public String getDescription();

    /**
     *
     * @return
     */
    public String getWebSite();
    
    /**
     *
     * @return
     */
    public PixelMatrix getLogo();

    /**
     *
     * @return
     */
    public PixelMatrix getPreview();
    
    /**
     *
     * @return
     */
    public int getBufferWidth();
    
    /**
     *
     * @return
     */
    public int getBufferHeight();
    
    /**
     *
     * @return
     */
    public Class<? extends Game> getGameClass();
}
