package src.com.monopoly; /**
 * @(#)ModJLabel.java
 *
 *
 * @author
 * @version 1.00 2010/2/14
 */

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class ModJLabel extends JLabel
{
    /**
     *
     */
    // A set of the game pieces currently on this cell
    private static final long serialVersionUID = 1519067928764527008L;
    public Player bought;
    public ImageIcon GlowIcon0 = new ImageIcon("images\\borders\\propertyborder0.png");
    public ImageIcon GlowIcon90 = new ImageIcon("images\\borders\\propertyborder90.png");
    public ImageIcon GlowIcon360 = new ImageIcon("images\\borders\\propertyborder360.png");
    private Set<GamePiece> myPlayers = new HashSet<GamePiece>();
    private Set<ImageIcon> boxGlow = new HashSet<ImageIcon>();

    public ModJLabel()
    {
        super();
    }

    public ModJLabel(ImageIcon e)
    {
        super(e);
    }

    public void setPlayer(Player a)
    {
        bought = a;
    }

    public void addGamePiece(Player p)
    {
        myPlayers.add(p.getGamePiece());
        addGlow();
    }

    public void removeGamePiece(Player p)
    {
        myPlayers.remove(p.getGamePiece());
        removeGlow();
    }

    public void addGlow()
    {
        if (this.getIcon().getIconWidth() == 78)
        {
            boxGlow.add(GlowIcon0);
        }
        if ((this.getIcon().getIconWidth() == 110) && (this.getIcon().getIconHeight() == 78))
        {
            boxGlow.add(GlowIcon90);
        }
        if (this.getIcon().getIconWidth() == 110 && this.getIcon().getIconHeight() == 110)
        {
            boxGlow.add(GlowIcon360);
        }
    }

    public void removeGlow()
    {
        if (this.getIcon().getIconWidth() == 78)
        {
            boxGlow.remove(GlowIcon0);
        }
        if ((this.getIcon().getIconWidth() == 110) && (this.getIcon().getIconHeight() == 78))
        {
            boxGlow.remove(GlowIcon90);
        }
        if (this.getIcon().getIconWidth() == 110 && this.getIcon().getIconHeight() == 110)
        {
            boxGlow.remove(GlowIcon360);
        }

    }

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int offset = 0;
        for (ImageIcon p : boxGlow)
        {
            p.paintIcon(this, g, 0, 0);
        }
        for (GamePiece p : myPlayers)
        {
            p.getImageIcon().paintIcon(this, g, 10 + offset, 10 + offset);
            offset += 10;
        }
    }

}
