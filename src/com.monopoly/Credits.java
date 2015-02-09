package src.com.monopoly; /**
 * @(#)BackgroundUI.java
 *
 *
 * @Mathew Kurian
 * @version 1.00 2010/2/13
 */

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Credits extends Thread
{

    public JFrame frame = new JFrame("");

    public ImagePanel credits = new ImagePanel("images\\credits\\credits.png");

    public JLabel OKButton = new JLabel(new ImageIcon(("images\\button\\CRED.png")));

    public BackgroundUI back;

    public Credits(BackgroundUI b, BlackStage black)
    {
        back = b;
    }

    public void createAndShowGUI()
    {
        credits.setBounds(50, 50, 670, 550);
        back.addCredBoard(credits);
    }

    @Override
    public void run()
    {
        credits.setOpaque(false);

        OKButton.addMouseListener(new MouseListener()
        {

            public void mouseClicked(MouseEvent e)
            {

            }

            public void mouseExited(MouseEvent e)
            {

                ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\CRED.png")));
                ((JLabel) e.getSource()).repaint();
            }

            public void mousePressed(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\CRED_click.png")));
                ((JLabel) e.getSource()).repaint();

            }

            public void mouseReleased(MouseEvent e)
            {

                ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\CRED.png")));
                ((JLabel) e.getSource()).repaint();

                back.hidCredBoard();
                back.menuBoard.setVisible(true);
            }

            public void mouseEntered(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\CRED_over.png")));
                ((JLabel) e.getSource()).repaint();

            }
        });

        OKButton.setBounds(465, 390, 165, 131);

        credits.add(OKButton);
    }
}