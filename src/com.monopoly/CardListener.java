package src.com.monopoly; /**
 * @(#)CardListener.java
 *
 *
 * @author
 * @version 1.00 2010/3/18
 */

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CardListener implements MouseListener
{

    ImageIcon chanceNormal;
    ImageIcon chanceYellow;

    ImageIcon commNormal;
    ImageIcon commYellow;

    PopUpUI popup;

    Player p;

    boolean enabled = false;
    int numCount = 2000;

    public CardListener()
    {
    }

    public void mousePressed(MouseEvent e)
    {
    }

    public void mouseReleased(MouseEvent e)
    {
        if (enabled)
        {
            if ((((JLabel) e.getSource()).getName() == "Chance") && (numCount == 1))
            {
                ((JLabel) e.getSource()).setIcon(chanceNormal);
                ((JLabel) e.getSource()).repaint();
                disableAll();
                popup.showChanceCard(p);

            }
            if ((((JLabel) e.getSource()).getName() == "CommChest") && (numCount == 2))
            {
                ((JLabel) e.getSource()).setIcon(commNormal);
                ((JLabel) e.getSource()).repaint();
                disableAll();
                popup.showCommCard(p);
            }
        }
    }

    public void mouseEntered(MouseEvent e)
    {
        if (enabled)
        {
            if ((((JLabel) e.getSource()).getName() == "Chance") && (numCount == 1))
            {
                ((JLabel) e.getSource()).setIcon(chanceYellow);
                ((JLabel) e.getSource()).repaint();
            }
            if ((((JLabel) e.getSource()).getName() == "CommChest") && (numCount == 2))
            {
                ((JLabel) e.getSource()).setIcon(commYellow);
                ((JLabel) e.getSource()).repaint();
            }
        }
    }

    public void mouseExited(MouseEvent e)
    {
        if (enabled)
        {
            if ((((JLabel) e.getSource()).getName() == "Chance") && (numCount == 1))
            {
                ((JLabel) e.getSource()).setIcon(chanceNormal);
                ((JLabel) e.getSource()).repaint();
            }
            if ((((JLabel) e.getSource()).getName() == "CommChest") && (numCount == 2))
            {
                ((JLabel) e.getSource()).setIcon(commNormal);
                ((JLabel) e.getSource()).repaint();
            }
        }
    }

    public void mouseClicked(MouseEvent e)
    {
    }

    public void setPopUpAndImages(PopUpUI p, ImageIcon cn, ImageIcon cy, ImageIcon ccn, ImageIcon ccy)
    {
        chanceNormal = cn;
        chanceYellow = cy;

        commNormal = ccn;
        commYellow = ccy;

        popup = p;
    }

    public void setEnabled(int a)
    {
        numCount = a;
        enabled = true;
    }

    public void disableAll()
    {
        numCount = 454545;
        enabled = false;
    }

    public void setCurrentPlayer(Player pa)
    {
        p = pa;
    }
}	