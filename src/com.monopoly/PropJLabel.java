package src.com.monopoly; /**
 * @(#)ModJLabel.java
 *
 *
 * @author
 * @version 1.00 2010/2/14
 */

import javax.swing.*;
import java.awt.*;

public class PropJLabel extends JLabel
{
    /**
     *
     */
    private static final long serialVersionUID = -4703313733397312593L;
    private Player myPlayer = null;

    public PropJLabel()
    {
        super();
    }

    public PropJLabel(ImageIcon e)
    {
        super(e);
    }

    public void setPlayer(Player a)
    {
        myPlayer = a;
    }

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        int offsetx = 6;
        int offsety = 7;

        if (myPlayer != null)
        {
            for (ImageIcon a : myPlayer.getPropertiesBoughtColor())
            {
                a.paintIcon(this, g, offsetx, offsety);
                offsetx = offsetx + 25;
            }
        }
    }

}
