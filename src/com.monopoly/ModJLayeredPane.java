package src.com.monopoly;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class ModJLayeredPane extends JLayeredPane implements MouseListener
{

    /**
     *
     */
    private static final long serialVersionUID = -8694416381878219479L;

    public JLayeredPane controls = new JLayeredPane();
    public ModJLabel currentImage;
    public JLabel overlay = new JLabel(new ImageIcon("images\\bar\\overlay.png"));
    public int currentPosition = 0;

    public JLabel house1 = new JLabel(new ImageIcon("images\\features\\HOUSE.png"));
    public JLabel house2 = new JLabel(new ImageIcon("images\\features\\HOUSE.png"));
    public JLabel house3 = new JLabel(new ImageIcon("images\\features\\HOUSE.png"));
    public JLabel house4 = new JLabel(new ImageIcon("images\\features\\HOUSE.png"));

    public boolean entered = false;

    public boolean j1 = false;
    public boolean j2 = false;
    public boolean j3 = false;
    public boolean j4 = false;

    public MKEngine engine;

    int houseCount = 0;

    public ModJLayeredPane(ModJLabel a, MKEngine e)
    {
        super();

        engine = e;
        currentImage = a;

        this.setPreferredSize(new Dimension(a.getIcon().getIconWidth(), a.getIcon().getIconHeight()));
        this.setMinimumSize(new Dimension(a.getIcon().getIconWidth(), a.getIcon().getIconHeight()));
        this.setMaximumSize(new Dimension(a.getIcon().getIconWidth(), a.getIcon().getIconHeight()));
        this.setSize(new Dimension(a.getIcon().getIconWidth(), a.getIcon().getIconHeight()));
        this.setOpaque(false);
        currentImage.setBounds(0, 0, a.getIcon().getIconWidth(), a.getIcon().getIconHeight());
        this.add(a, new Integer(0));

        controls.setLayout(null);
        controls.setOpaque(false);
        controls.setVisible(false);

    }

    public void setActive(int x)
    {
        currentPosition = x;
        addTopLayer();
        this.addMouseListener(this);
    }

    public void addTopLayer()
    {
        if (currentImage.getIcon().getIconWidth() == 78 && (currentImage.getIcon().getIconHeight() == 110))
        {
            house1.addMouseListener(new MouseListener()
            {

                public void mouseClicked(MouseEvent arg0)
                {
                }

                public void mouseEntered(MouseEvent arg0)
                {
                    controls.setVisible(true);

                    if (!j1)
                    {
                        ((JLabel) arg0.getSource()).setIcon(new ImageIcon("images\\features\\HOUSE_over.png"));
                    }
                }

                public void mouseExited(MouseEvent arg0)
                {
                    controls.setVisible(true);

                    if (!j1)
                    {
                        ((JLabel) arg0.getSource()).setIcon(new ImageIcon("images\\features\\HOUSE.png"));
                    }
                }

                public void mousePressed(MouseEvent arg0)
                {
                    controls.setVisible(true);

                    if (!j1)
                    {
                        ((JLabel) arg0.getSource()).setIcon(new ImageIcon("images\\features\\HOUSE_click.png"));
                    }

                }

                public void mouseReleased(MouseEvent arg0)
                {
                    controls.setVisible(true);
                    if (!j1)
                    {
                        houseCount++;

                        ((JLabel) arg0.getSource()).setIcon(new ImageIcon("images\\features\\HOUSE_click.png"));
                        currentImage.bought.addHouses(currentPosition);
                        j1 = true;

                        engine.reduceHouseAmount(currentImage.bought, houseCount);
                    }

                }
            });

            house2.addMouseListener(new MouseListener()
            {

                public void mouseClicked(MouseEvent arg0)
                {
                }

                public void mouseEntered(MouseEvent arg0)
                {
                    controls.setVisible(true);
                    if (!j2)
                    {
                        ((JLabel) arg0.getSource()).setIcon(new ImageIcon("images\\features\\HOUSE_over.png"));
                    }
                }

                public void mouseExited(MouseEvent arg0)
                {
                    controls.setVisible(true);
                    if (!j2)
                    {
                        ((JLabel) arg0.getSource()).setIcon(new ImageIcon("images\\features\\HOUSE.png"));
                    }
                }

                public void mousePressed(MouseEvent arg0)
                {
                    controls.setVisible(true);
                    if (!j2)
                    {
                        ((JLabel) arg0.getSource()).setIcon(new ImageIcon("images\\features\\HOUSE_click.png"));
                    }

                }

                public void mouseReleased(MouseEvent arg0)
                {
                    controls.setVisible(true);
                    if (!j2)
                    {
                        houseCount++;

                        ((JLabel) arg0.getSource()).setIcon(new ImageIcon("images\\features\\HOUSE_click.png"));
                        currentImage.bought.addHouses(currentPosition);
                        j2 = true;

                        engine.reduceHouseAmount(currentImage.bought, houseCount);
                    }
                }
            });

            house3.addMouseListener(new MouseListener()
            {

                public void mouseClicked(MouseEvent arg0)
                {
                }

                public void mouseEntered(MouseEvent arg0)
                {
                    controls.setVisible(true);
                    if (!j3)
                    {
                        ((JLabel) arg0.getSource()).setIcon(new ImageIcon("images\\features\\HOUSE_over.png"));
                    }
                }

                public void mouseExited(MouseEvent arg0)
                {
                    controls.setVisible(true);
                    if (!j3)
                    {
                        ((JLabel) arg0.getSource()).setIcon(new ImageIcon("images\\features\\HOUSE.png"));
                    }
                }

                public void mousePressed(MouseEvent arg0)
                {
                    controls.setVisible(true);
                    if (!j3)
                    {
                        ((JLabel) arg0.getSource()).setIcon(new ImageIcon("images\\features\\HOUSE_click.png"));
                    }

                }

                public void mouseReleased(MouseEvent arg0)
                {
                    controls.setVisible(true);
                    if (!j3)
                    {
                        houseCount++;

                        ((JLabel) arg0.getSource()).setIcon(new ImageIcon("images\\features\\HOUSE_click.png"));
                        currentImage.bought.addHouses(currentPosition);
                        j3 = true;

                        engine.reduceHouseAmount(currentImage.bought, houseCount);
                    }
                }
            });

            house4.addMouseListener(new MouseListener()
            {

                public void mouseClicked(MouseEvent arg0)
                {
                }

                public void mouseEntered(MouseEvent arg0)
                {
                    controls.setVisible(true);
                    if (!j4)
                    {
                        ((JLabel) arg0.getSource()).setIcon(new ImageIcon("images\\features\\HOUSE_over.png"));
                    }
                }

                public void mouseExited(MouseEvent arg0)
                {
                    controls.setVisible(true);
                    if (!j4)
                    {
                        ((JLabel) arg0.getSource()).setIcon(new ImageIcon("images\\features\\HOUSE.png"));
                    }
                }

                public void mousePressed(MouseEvent arg0)
                {
                    controls.setVisible(true);
                    if (!j4)
                    {
                        ((JLabel) arg0.getSource()).setIcon(new ImageIcon("images\\features\\HOUSE_click.png"));
                    }

                }

                public void mouseReleased(MouseEvent arg0)
                {
                    controls.setVisible(true);
                    if (!j4)
                    {
                        houseCount++;

                        ((JLabel) arg0.getSource()).setIcon(new ImageIcon("images\\features\\HOUSE_click.png"));
                        currentImage.bought.addHouses(currentPosition);
                        j4 = true;

                        engine.reduceHouseAmount(currentImage.bought, houseCount);
                    }
                }
            });

            overlay.setBounds(0, 0, 78, 110);

            house1.setBounds(6, 6, 23, 23);
            house2.setBounds(34, 6, 23, 23);
            house3.setBounds(50, 80, 23, 23);
            house4.setBounds(22, 80, 23, 23);

            controls.setPreferredSize(new Dimension(78, 110));
            controls.setBounds(0, 0, 78, 110);

            controls.add(overlay, new Integer(0));
            controls.add(house1, new Integer(1));
            controls.add(house2, new Integer(1));
            controls.add(house3, new Integer(1));
            controls.add(house4, new Integer(1));

        }
        if (currentImage.getIcon().getIconWidth() == 110 && (currentImage.getIcon().getIconHeight() == 78))
        {
            house1.addMouseListener(new MouseListener()
            {

                public void mouseClicked(MouseEvent arg0)
                {
                }

                public void mouseEntered(MouseEvent arg0)
                {
                    controls.setVisible(true);

                    if (!j1)
                    {
                        ((JLabel) arg0.getSource()).setIcon(new ImageIcon("images\\features\\HOUSE_over.png"));
                    }
                }

                public void mouseExited(MouseEvent arg0)
                {
                    controls.setVisible(true);

                    if (!j1)
                    {
                        ((JLabel) arg0.getSource()).setIcon(new ImageIcon("images\\features\\HOUSE.png"));
                    }
                }

                public void mousePressed(MouseEvent arg0)
                {
                    controls.setVisible(true);

                    if (!j1)
                    {
                        ((JLabel) arg0.getSource()).setIcon(new ImageIcon("images\\features\\HOUSE_click.png"));
                    }

                }

                public void mouseReleased(MouseEvent arg0)
                {
                    controls.setVisible(true);
                    if (!j1)
                    {
                        houseCount++;

                        ((JLabel) arg0.getSource()).setIcon(new ImageIcon("images\\features\\HOUSE_click.png"));
                        currentImage.bought.addHouses(currentPosition);
                        j1 = true;

                        engine.reduceHouseAmount(currentImage.bought, houseCount);
                    }

                }
            });

            house2.addMouseListener(new MouseListener()
            {

                public void mouseClicked(MouseEvent arg0)
                {
                }

                public void mouseEntered(MouseEvent arg0)
                {
                    controls.setVisible(true);
                    if (!j2)
                    {
                        ((JLabel) arg0.getSource()).setIcon(new ImageIcon("images\\features\\HOUSE_over.png"));
                    }
                }

                public void mouseExited(MouseEvent arg0)
                {
                    controls.setVisible(true);
                    if (!j2)
                    {
                        ((JLabel) arg0.getSource()).setIcon(new ImageIcon("images\\features\\HOUSE.png"));
                    }
                }

                public void mousePressed(MouseEvent arg0)
                {
                    controls.setVisible(true);
                    if (!j2)
                    {
                        ((JLabel) arg0.getSource()).setIcon(new ImageIcon("images\\features\\HOUSE_click.png"));
                    }

                }

                public void mouseReleased(MouseEvent arg0)
                {
                    controls.setVisible(true);
                    if (!j2)
                    {
                        houseCount++;

                        ((JLabel) arg0.getSource()).setIcon(new ImageIcon("images\\features\\HOUSE_click.png"));
                        currentImage.bought.addHouses(currentPosition);
                        j2 = true;

                        engine.reduceHouseAmount(currentImage.bought, houseCount);
                    }
                }
            });

            house3.addMouseListener(new MouseListener()
            {

                public void mouseClicked(MouseEvent arg0)
                {
                }

                public void mouseEntered(MouseEvent arg0)
                {
                    controls.setVisible(true);
                    if (!j3)
                    {
                        ((JLabel) arg0.getSource()).setIcon(new ImageIcon("images\\features\\HOUSE_over.png"));
                    }
                }

                public void mouseExited(MouseEvent arg0)
                {
                    controls.setVisible(true);
                    if (!j3)
                    {
                        ((JLabel) arg0.getSource()).setIcon(new ImageIcon("images\\features\\HOUSE.png"));
                    }
                }

                public void mousePressed(MouseEvent arg0)
                {
                    controls.setVisible(true);
                    if (!j3)
                    {
                        ((JLabel) arg0.getSource()).setIcon(new ImageIcon("images\\features\\HOUSE_click.png"));
                    }

                }

                public void mouseReleased(MouseEvent arg0)
                {
                    controls.setVisible(true);
                    if (!j3)
                    {
                        houseCount++;

                        ((JLabel) arg0.getSource()).setIcon(new ImageIcon("images\\features\\HOUSE_click.png"));
                        currentImage.bought.addHouses(currentPosition);
                        j3 = true;

                        engine.reduceHouseAmount(currentImage.bought, houseCount);
                    }
                }
            });

            house4.addMouseListener(new MouseListener()
            {

                public void mouseClicked(MouseEvent arg0)
                {
                }

                public void mouseEntered(MouseEvent arg0)
                {
                    controls.setVisible(true);
                    if (!j4)
                    {
                        ((JLabel) arg0.getSource()).setIcon(new ImageIcon("images\\features\\HOUSE_over.png"));
                    }
                }

                public void mouseExited(MouseEvent arg0)
                {
                    controls.setVisible(true);
                    if (!j4)
                    {
                        ((JLabel) arg0.getSource()).setIcon(new ImageIcon("images\\features\\HOUSE.png"));
                    }
                }

                public void mousePressed(MouseEvent arg0)
                {
                    controls.setVisible(true);
                    if (!j4)
                    {
                        ((JLabel) arg0.getSource()).setIcon(new ImageIcon("images\\features\\HOUSE_click.png"));
                    }

                }

                public void mouseReleased(MouseEvent arg0)
                {
                    controls.setVisible(true);
                    if (!j4)
                    {
                        houseCount++;

                        ((JLabel) arg0.getSource()).setIcon(new ImageIcon("images\\features\\HOUSE_click.png"));
                        currentImage.bought.addHouses(currentPosition);
                        j4 = true;

                        engine.reduceHouseAmount(currentImage.bought, houseCount);
                    }
                }
            });

            overlay.setBounds(0, 0, 110, 78);

            house1.setBounds(6, 6, 23, 23);
            house2.setBounds(34, 6, 23, 23);
            house3.setBounds(53, 48, 23, 23);
            house4.setBounds(81, 48, 23, 23);

            controls.setPreferredSize(new Dimension(110, 78));
            controls.setBounds(0, 0, 110, 78);

            controls.add(overlay, new Integer(0));
            controls.add(house1, new Integer(1));
            controls.add(house2, new Integer(1));
            controls.add(house3, new Integer(1));
            controls.add(house4, new Integer(1));
        }
        if (this.getWidth() == 110 && this.getHeight() == 110)
        {
            overlay.setBounds(0, 0, 110, 100);

            controls.setPreferredSize(new Dimension(110, 110));
            controls.setBounds(0, 0, 110, 110);

            controls.add(overlay, new Integer(0));
        }

        this.add(controls, new Integer(2));
    }

    public void mouseClicked(MouseEvent e)
    {
        // TODO Auto-generated method stub

    }

    public void mouseEntered(MouseEvent e)
    {
        if (!entered)
        {
            controls.setVisible(true);
            entered = true;
        }
    }

    public void mouseExited(MouseEvent e)
    {
        if (entered)
        {
            controls.setVisible(false);
            entered = false;
        }
    }

    public void mousePressed(MouseEvent e)
    {
    }

    public void mouseReleased(MouseEvent e)
    {
        // TODO Auto-generated method stub

    }

    public void reset()
    {
        j1 = false;
        j2 = false;
        j3 = false;
        j4 = false;

        house1.setIcon(new ImageIcon("images\\features\\HOUSE.png"));
        house2.setIcon(new ImageIcon("images\\features\\HOUSE.png"));
        house3.setIcon(new ImageIcon("images\\features\\HOUSE.png"));
        house4.setIcon(new ImageIcon("images\\features\\HOUSE.png"));
    }
}
