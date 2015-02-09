package src.com.monopoly; /**
 * @(#)CentredBackgroundBorder.java
 *
 *
 * @author
 * @version 1.00 2010/2/27
 */

import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CentredBackgroundBorder implements Border
{
    private final BufferedImage image;

    public CentredBackgroundBorder(BufferedImage image)
    {
        this.image = image;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
    {
        int x0 = x + (width - image.getWidth()) / 2;
        int y0 = y + (height - image.getHeight()) / 2;
        g.drawImage(image, x0, y0, null);
    }

    public Insets getBorderInsets(Component c)
    {
        return new Insets(0, 0, 0, 0);
    }

    public boolean isBorderOpaque()
    {
        return true;
    }
}  
 