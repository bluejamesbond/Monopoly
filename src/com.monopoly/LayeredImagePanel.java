package src.com.monopoly; /**
 * @(#)ImagePanel.java
 *
 *
 * @author
 * @version 1.00 2010/2/27
 */


import javax.swing.*;
import java.awt.*;

class LayeredImagePanel extends JLayeredPane
{


    /**
     *
     */
    private static final long serialVersionUID = -7635738456898919540L;
    private Image img;

    public LayeredImagePanel(String img)
    {
        this(new ImageIcon(img).getImage());
        setOpaque(false);
    }

    public LayeredImagePanel(Image img)
    {
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
        setOpaque(false);
        repaint();
    }

    public LayeredImagePanel(ImageIcon img)
    {
        this(img.getImage());
    }

    public void changeImage(ImageIcon img)
    {
        this.img = img.getImage();
        Dimension size = new Dimension(img.getImage().getWidth(null), img.getImage().getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setOpaque(false);
        repaint();
    }

    public void paintComponent(Graphics g)
    {
        g.drawImage(img, 0, 0, null);
    }

}