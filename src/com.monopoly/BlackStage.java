package src.com.monopoly; /**
 * @(#)BackgroundUI.java
 *
 *
 * @Mathew Kurian
 * @version 1.00 2010/2/13
 */

import javax.swing.*;
import java.awt.*;

public class BlackStage
{

    public JWindow backgroundFrame;

    public JPanel backgroundPanel;

    public ImageIcon splashScreenImage = new ImageIcon("images\\backgrounds\\black.png");

    JLabel backgroundLabel = new JLabel(splashScreenImage);

    public BlackStage()
    {

        backgroundFrame = new JWindow();

        backgroundPanel = new JPanel();

        backgroundPanel.setLayout(new BorderLayout());

        backgroundPanel.add(backgroundLabel, BorderLayout.CENTER);

    }

    public void createAndShowGUI()
    {
        backgroundFrame.getContentPane().add(backgroundPanel);
        backgroundFrame.pack();
        backgroundFrame.setLocationRelativeTo(null);
        backgroundFrame.setVisible(true);
    }

    public void endGUI()
    {
        backgroundFrame.setVisible(false);
    }

    public void showMe()
    {
        backgroundFrame.setVisible(true);
    }
}