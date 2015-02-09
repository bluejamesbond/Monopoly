package src.com.monopoly; /**
 * @(#)FadeLogoUI.java
 *
 *
 * @author
 * @version 1.00 2010/3/3
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FadeLogoUI extends JPanel
{
    /**
     *
     */
    private static final long serialVersionUID = 4832830351784716559L;
    public float opacity;
    public Timer fadeTimerEnd;
    public Timer fadeTimerOpen;

    public void fadeOut()
    {
        opacity = 0f;  //solid

        fadeTimerEnd = new Timer(60, new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                opacity += .03;

                if (opacity > 1)

                {
                    opacity = 1; //none
                    fadeTimerEnd.stop();
                    fadeTimerEnd = null;
                }

                repaint();
            }
        });

        fadeTimerEnd.setInitialDelay(0);
        fadeTimerEnd.start();

    }

    public void fadeIn()
    {
        opacity = 1;

        fadeTimerOpen = new Timer(10, new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                opacity -= .03;

                if (opacity < 0f)

                {
                    opacity = 0f;
                    System.out.print(opacity);
                    fadeTimerOpen.stop();
                    fadeTimerOpen = null;
                }

                System.out.print(opacity + ", ");

                repaint();
            }
        });

        fadeTimerOpen.setInitialDelay(0);
        fadeTimerOpen.start();

    }

    public void paintComponent(Graphics g)
    {
        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    public void setOpacity(float opacityLevel)
    {
        opacity = opacityLevel;
        repaint();
    }
}