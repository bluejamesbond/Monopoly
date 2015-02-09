package src.com.monopoly; /**
 * @(#)TransparentBackgroundStandStill.java
 *
 *
 * @author
 * @version 1.00 2010/2/26
 */


import javax.swing.*;
import java.awt.*;

public class TransparentBackgroundStandStill extends JComponent
{
    /**
     *
     */
    private static final long serialVersionUID = -8766366830960898995L;
    private Image background;
    private JFrame frame;

    public TransparentBackgroundStandStill(JFrame frame)
    {
        this.setFrame(frame);
        updateBackground();
    }

    public void updateBackground()
    {
        try
        {
            Robot rbt = new Robot();
            Toolkit tk = Toolkit.getDefaultToolkit();
            Dimension dim = tk.getScreenSize();
            background = rbt.createScreenCapture(
                    new Rectangle(0, 0, (int) dim.getWidth(),
                            (int) dim.getHeight()));
        }
        catch (Exception ex)
        {

        }
    }

    public void paintComponent(Graphics g)
    {
        Point pos = this.getLocationOnScreen();
        Point offset = new Point(-pos.x, -pos.y);
        g.drawImage(background, offset.x, offset.y, null);
    }

    public JFrame getFrame()
    {
        return frame;
    }

    public void setFrame(JFrame frame)
    {
        this.frame = frame;
    }
}