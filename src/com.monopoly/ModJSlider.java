package src.com.monopoly;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class ModJSlider extends JSlider
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public int height = 0;

    public ModJSlider(int x, int y)
    {
        super(x, y);
        setPreferredSize(new Dimension(375, 5));
        setUI(new MyBasicSliderUI(this));
        this.setOpaque(false);
    }

    public ModJSlider(int x, int y, int g)
    {
        super(x, y, g);
        setPreferredSize(new Dimension(375, 5));
        setUI(new MyBasicSliderUI(this));
        this.setOpaque(false);
    }

    public void setBounds(int x, int y, int w, int h)
    {
        super.setBounds(x, y, w, h);
        height = h;
    }

    class MyBasicSliderUI extends BasicSliderUI implements MouseListener
    {
        public boolean hoverState = false;
        Image im;
        JSlider j;

        public MyBasicSliderUI(JSlider js)
        {
            super(js);
            js.addMouseListener(this);
            j = js;
        }

        public void paintThumb(Graphics g)
        {
            try
            {
                URL url = new URL("file:images\\bar\\slider.png");
                im = ImageIO.read(url);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            Rectangle thumb = thumbRect;

            g.drawImage(im, thumb.x, thumb.y, null);
        }

        public void paintTrack(Graphics g)
        {
            if (hoverState)
            {
                g.setColor(new Color(84, 83, 78));
                g.fill3DRect(0, (height / 2), 500, 2, true);
            }
            if (!hoverState)
            {
                g.setColor(Color.WHITE);
                g.fill3DRect(0, (height / 2), 500, 2, true);
            }
        }

        public void mouseClicked(MouseEvent arg0)
        {
        }

        public void mouseEntered(MouseEvent arg0)
        {
            hoverState = true;
            repaint();
        }

        public void mouseExited(MouseEvent arg0)
        {
            hoverState = false;
            repaint();
        }

        public void mousePressed(MouseEvent arg0)
        {
        }

        public void mouseReleased(MouseEvent arg0)
        {
        }

    }
}
