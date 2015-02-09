package src.com.monopoly;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ControlsUI
{

    public MusicUI music;

    public LayeredImagePanel controlPanel = new LayeredImagePanel("images\\bar\\extras_back.png");

    public ArrayList<JSlider> sliders;

    public JLabel leftLabel = new JLabel(new ImageIcon("images\\bar\\SIDES.png"));
    public JLabel centerLabel = new JLabel(new ImageIcon("images\\bar\\CENTER.png"));
    public JLabel rightLabel = new JLabel(new ImageIcon("images\\bar\\SIDES.png"));

    public JPanel left = new JPanel(new GridLayout(1, 1));
    public JPanel center = new JPanel(new GridLayout(1, 1));
    public JPanel right = new JPanel(new GridLayout(1, 1));

    public JLabel panTitle = new JLabel("Speaker Panning");
    public JLabel panDetails = new JLabel("Align the sound to the left or right. Press center to reset.");

    public JLabel volTitle = new JLabel("Speaker Volume");
    public JLabel volDetails = new JLabel("Drat bar to the right to increase volume. Drag to  the left to decrease.");

    public JLabel posTitle = new JLabel("Track Position");
    public JLabel posDetails = new JLabel("Drag bar to skip to position in the track.");

    public int volY = 65;
    public int posY = 135;
    public int panY = 345;

    public ControlsUI(MusicUI m)
    {
        music = m;

        controlPanel.setPreferredSize(new Dimension(475, 410));
        controlPanel.setBounds(0, 20, 475, 410);
        controlPanel.setLayout(null);
        controlPanel.setOpaque(false);

        setUpButtons();
        syncSliders();
        addText();

    }

    public JLayeredPane getPanel()
    {
        return controlPanel;
    }

    public void getSliders()
    {
        sliders = music.controller.backMusic.getControls();
    }

    public void syncSliders()
    {
        getSliders();

        sliders.get(0).setBounds(35, volY, 375, 20);
        controlPanel.add(sliders.get(0));

        sliders.get(2).setBounds(35, posY, 375, 20);
        controlPanel.add(sliders.get(2));

        sliders.get(1).setBounds(35, panY, 375, 10);
        controlPanel.add(sliders.get(1));
    }

    public void addText()
    {
        /**********************************************************/
        volTitle.setHorizontalTextPosition(JLabel.LEFT);
        volTitle.setVerticalTextPosition(JLabel.CENTER);
        volTitle.setFont(new Font("Calibri", Font.PLAIN, 12));
        volTitle.setForeground(Color.WHITE);
        volTitle.validate();

        volTitle.setBounds(35, volY - 35, 200, 20);
        controlPanel.add(volTitle);

        volDetails.setHorizontalTextPosition(JLabel.LEFT);
        volDetails.setVerticalTextPosition(JLabel.CENTER);
        volDetails.setFont(new Font("Calibri", Font.PLAIN, 11));
        volDetails.setForeground(new Color(37, 125, 165));
        volDetails.validate();

        volDetails.setBounds(35, volY - 20, 360, 20);
        controlPanel.add(volDetails);
        /**********************************************************/
        /**********************************************************/
        posTitle.setHorizontalTextPosition(JLabel.LEFT);
        posTitle.setVerticalTextPosition(JLabel.CENTER);
        posTitle.setFont(new Font("Calibri", Font.PLAIN, 12));
        posTitle.setForeground(Color.WHITE);
        posTitle.validate();

        posTitle.setBounds(35, posY - 35, 200, 20);
        controlPanel.add(posTitle);

        posDetails.setHorizontalTextPosition(JLabel.LEFT);
        posDetails.setVerticalTextPosition(JLabel.CENTER);
        posDetails.setFont(new Font("Calibri", Font.PLAIN, 11));
        posDetails.setForeground(new Color(37, 125, 165));
        posDetails.validate();

        posDetails.setBounds(35, posY - 20, 360, 20);
        controlPanel.add(posDetails);
        /**********************************************************/
        /**********************************************************/
        panTitle.setHorizontalTextPosition(JLabel.LEFT);
        panTitle.setVerticalTextPosition(JLabel.CENTER);
        panTitle.setFont(new Font("Calibri", Font.PLAIN, 12));
        panTitle.setForeground(Color.WHITE);
        panTitle.validate();

        panTitle.setBounds(35, panY - 175, 200, 20);
        controlPanel.add(panTitle);

        panDetails.setHorizontalTextPosition(JLabel.LEFT);
        panDetails.setVerticalTextPosition(JLabel.CENTER);
        panDetails.setFont(new Font("Calibri", Font.PLAIN, 11));
        panDetails.setForeground(new Color(37, 125, 165));
        panDetails.validate();

        panDetails.setBounds(35, panY - 160, 360, 20);
        controlPanel.add(panDetails);
        /**********************************************************/

    }

    public void resetControls()
    {
        syncSliders();
    }

    public void setUpButtons()
    {

        leftLabel.addMouseListener(new MouseListener()
        {

            public void mouseClicked(MouseEvent e)
            {
            }

            public void mouseEntered(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\SIDES_hover.png"));
                ((JLabel) e.getSource()).repaint();
            }

            public void mouseExited(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\SIDES.png"));
                ((JLabel) e.getSource()).repaint();
            }

            public void mousePressed(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\SIDES_hover.png"));
                ((JLabel) e.getSource()).repaint();
            }

            public void mouseReleased(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\SIDES.png"));
                ((JLabel) e.getSource()).repaint();

                music.controller.backMusic.panLeft();
            }
        });

        centerLabel.addMouseListener(new MouseListener()
        {

            public void mouseClicked(MouseEvent e)
            {
            }

            public void mouseEntered(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\CENTER_hover.png"));
                ((JLabel) e.getSource()).repaint();
            }

            public void mouseExited(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\CENTER.png"));
                ((JLabel) e.getSource()).repaint();
            }

            public void mousePressed(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\CENTER_hover.png"));
                ((JLabel) e.getSource()).repaint();
            }

            public void mouseReleased(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\CENTER.png"));
                ((JLabel) e.getSource()).repaint();

                music.controller.backMusic.panCenter();
            }
        });

        rightLabel.addMouseListener(new MouseListener()
        {

            public void mouseClicked(MouseEvent e)
            {
            }

            public void mouseEntered(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\SIDES_hover.png"));
                ((JLabel) e.getSource()).repaint();
            }

            public void mouseExited(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\SIDES.png"));
                ((JLabel) e.getSource()).repaint();

                right.setBackground(Color.BLACK);
            }

            public void mousePressed(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\SIDES_hover.png"));
                ((JLabel) e.getSource()).repaint();
            }

            public void mouseReleased(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\SIDES.png"));
                ((JLabel) e.getSource()).repaint();

                music.controller.backMusic.panRight();
            }
        });

        left.setOpaque(false);
        center.setOpaque(false);
        right.setOpaque(false);

        left.add(leftLabel);
        center.add(centerLabel);
        right.add(rightLabel);

        left.setBounds(45, panY - 140, 110, 130);
        center.setBounds(165, panY - 140, 110, 130);
        right.setBounds(285, panY - 140, 110, 130);

        controlPanel.add(left);
        controlPanel.add(center);
        controlPanel.add(right);
    }

}
