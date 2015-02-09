package src.com.monopoly; /**
 * @(#)PairOfDice.java
 *
 *
 * @Mathew Kurian
 * @version 1.00 2010/2/13
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PairOfDice
{

    public int die1;   // Number showing on the first die.
    public int die2;   // Number showing on the second die.

    public int die1Roll;   // Number showing on the first die.
    public int die2Roll;   // Number showing on the second die.

    public ArrayList<JLabel> SubObjects = new ArrayList<JLabel>();
    public ArrayList<JLabel> ExtrasObjects = new ArrayList<JLabel>();

    public ImageIcon d0 = new ImageIcon("images\\dice\\d0.png");
    public ImageIcon d1 = new ImageIcon("images\\dice\\d1.png");
    public ImageIcon d2 = new ImageIcon("images\\dice\\d2.png");
    public ImageIcon d3 = new ImageIcon("images\\dice\\d3.png");
    public ImageIcon d4 = new ImageIcon("images\\dice\\d4.png");
    public ImageIcon d5 = new ImageIcon("images\\dice\\d5.png");
    public ImageIcon d6 = new ImageIcon("images\\dice\\d6.png");

    public JLabel dieOne = new JLabel(d0);
    public JLabel dieTwo = new JLabel(d0);

    public JPanel dPanel = new JPanel(new GridBagLayout());

    public PairOfDice()
    {
        try
        {
            dPanel.setBorder(new CentredBackgroundBorder(ImageIO.read(new File("images\\dice\\dieback.png"))));
        }
        catch (IOException e)
        {
        }

        dPanel.add(dieOne);
        dPanel.add(dieTwo);
        dPanel.setOpaque(false);
    }

    public void RollWithValue()
    {

        die1 = (int) (Math.random() * 6) + 1;

        if (die1 == 1)
        {
            dieOne.setIcon(d1);
        }
        if (die1 == 2)
        {
            dieOne.setIcon(d2);
        }
        if (die1 == 3)
        {
            dieOne.setIcon(d3);
        }
        if (die1 == 4)
        {
            dieOne.setIcon(d4);
        }
        if (die1 == 5)
        {
            dieOne.setIcon(d5);
        }
        if (die1 == 6)
        {
            dieOne.setIcon(d6);
        }

        die2 = (int) (Math.random() * 6) + 1;

        if (die2 == 1)
        {
            dieTwo.setIcon(d1);
        }
        if (die2 == 2)
        {
            dieTwo.setIcon(d2);
        }
        if (die2 == 3)
        {
            dieTwo.setIcon(d3);
        }
        if (die2 == 4)
        {
            dieTwo.setIcon(d4);
        }
        if (die2 == 5)
        {
            dieTwo.setIcon(d5);
        }
        if (die2 == 6)
        {
            dieTwo.setIcon(d6);
        }

        dieOne.repaint();
        dieTwo.repaint();

        dPanel.validate();
        dPanel.repaint();

    }

    public Component getDefault()
    {
        return dPanel;
    }

    public int getRollTotal()
    {
        return die1 + die2;
    }
}