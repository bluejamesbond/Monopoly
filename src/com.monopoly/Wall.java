package src.com.monopoly;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Wall
{

    public String title;
    public String fullLoc;

    public JLayeredPane view = new JLayeredPane();

    public JLabel thumb = new JLabel();
    public JLabel highlight = new JLabel();

    public WallUI wall;

    public Wall wallIcon = this;

    public boolean active = false;

    public boolean motion = false;

    public Wall(String t, String thumbLoc, String largeLoc, WallUI w, boolean m)
    {
        title = t;
        fullLoc = largeLoc;
        wall = w;
        motion = m;

        thumb.setIcon(new ImageIcon(thumbLoc));
        thumb.setBounds(3, 3, 45, 45);

        view.setLayout(null);
        view.setPreferredSize(new Dimension(51, 51));
        view.setOpaque(false);

        highlight.setIcon(new ImageIcon("images\\bar\\walls_highlighted.png"));
        highlight.setBounds(0, 0, 51, 51);
        highlight.setVisible(false);

        view.setPreferredSize(new Dimension(45, 45));
        view.addMouseListener(new MouseListener()
        {
            public void mouseClicked(MouseEvent arg0)
            {
            }

            public void mouseEntered(MouseEvent arg0)
            {

                if (wall.activated)
                {
                    if (active == false)
                    {
                        addHighlight();

                        Point p = SwingUtilities.convertPoint(thumb, arg0.getPoint(), wall.wallPanel);

                        wall.showPreviewPane(fullLoc, p);
                    }
                }
            }

            public void mouseExited(MouseEvent arg0)
            {
                if (wall.activated)
                {

                    if (active == false)
                    {
                        hideColoring();

                        wall.hidePreviewPane();
                    }
                }
            }

            public void mousePressed(MouseEvent arg0)
            {
                if (wall.activated)
                {
                    if (active == false)
                    {
                        addSelected();
                    }
                }
            }

            public void mouseReleased(MouseEvent arg0)
            {

                if (wall.activated)
                {
                    addSelected();

                    if (motion)
                    {
                        wall.back.backgroundLabel.setVisible(false);
                        wall.back.colorFader.setVisible(true);
                        wall.back.colorFader.setColoredPane();
                    }
                    else
                    {
                        wall.back.backgroundLabel.setVisible(true);
                        wall.back.colorFader.setVisible(false);
                        wall.back.changeBackGround(new ImageIcon(fullLoc));
                    }

                    wall.setCurrent(fullLoc, title);
                    wall.hidePreviewPane();

                    active = true;

                    wall.slideBool = false;
                    wall.slideShow.setIcon(new ImageIcon("images\\bar\\AUTO.png"));

                    for (int x = 0; x < wall.wallIcons.size(); x++)
                    {
                        if (wall.wallIcons.get(x) != wallIcon)
                        {
                            wall.wallIcons.get(x).setInActive();
                        }
                    }
                }

            }
        });

        view.add(thumb, new Integer(1));
        view.add(highlight, new Integer(8));
    }

    public JLayeredPane getPanel()
    {
        return view;
    }

    public JLabel getFull()
    {
        return new JLabel(new ImageIcon(fullLoc));
    }

    public void setInActive()
    {
        active = false;
        hideColoring();
    }

    public void setActive()
    {
        active = true;
        addSelected();
        wall.back.changeBackGround(new ImageIcon(fullLoc));
    }

    public void addHighlight()
    {
        highlight.setVisible(true);
        highlight.setIcon(new ImageIcon("images\\bar\\walls_highlight.png"));
    }

    public void addSelected()
    {
        highlight.setVisible(true);
        highlight.setIcon(new ImageIcon("images\\bar\\walls_selected.png"));
    }

    public void hideColoring()
    {
        highlight.setVisible(false);
    }
}
