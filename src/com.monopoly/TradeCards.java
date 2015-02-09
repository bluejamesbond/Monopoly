package src.com.monopoly;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;


public class TradeCards
{

    public LayeredImagePanel clearBack = new LayeredImagePanel("images\\trade\\property_sq.png");
    public Image finalReady;
    public JLabel uploadToUI;
    public Player owner;
    public int num = 0;
    public int index = 0;
    public TradeUI trade;
    public TradeCards tradeCard = this;
    public String name;


    public TradeCards(Image a, TradeUI t, String prop, int i)
    {
        finalReady = a;
        uploadToUI = new JLabel(new ImageIcon(finalReady));
        trade = t;
        name = prop;
        index = i;

        clearBack.setLayout(new GridLayout(1, 1));
        clearBack.add(uploadToUI);
        clearBack.setBounds(10, 10, 220, 240);
        clearBack.addMouseMotionListener(new MouseMotionAdapter()
        {

            public void mouseDragged(MouseEvent E)
            {
                //X: 677 Y: 201

                if ((MouseInfo.getPointerInfo().getLocation().x < 690 + clearBack.getWidth()))
                {
                    Point p = SwingUtilities.convertPoint(clearBack, E.getPoint(), trade.topPanel);
                    clearBack.setBounds(p.x - 10, p.y - 10, clearBack.getWidth(), clearBack.getHeight());
                    trade.removeFromList(tradeCard);
                    trade.tradeLabel.setVisible(false);

                }

                else
                {
                    if ((trade.toTrade[0] == null) || trade.toTrade[0] == tradeCard)
                    {
                        if (checkOwnersLevel0())
                        {
                            clearBack.setBounds(677, 201, clearBack.getWidth(), clearBack.getHeight());
                            trade.addToList(tradeCard);

                            if (trade.checkReadyForTrade())
                            {
                                trade.tradeLabel.setVisible(true);
                            }
                            else
                            {
                                trade.tradeLabel.setVisible(false);
                            }
                        }
                    }
                    else if (trade.toTrade[1] == null || trade.toTrade[0] == tradeCard)
                    {
                        if (checkOwnersLevel1())
                        {
                            clearBack.setBounds(677, 461, clearBack.getWidth(), clearBack.getHeight());
                            trade.addToList(tradeCard);

                            trade.setText("\nPress \"Make Trade\" to continue with the exchange. Click tile(s) for detail(s). It will cost $80 for " + trade.toTrade[0].owner.getName() + ".");

                            if (trade.checkReadyForTrade())
                            {
                                trade.tradeLabel.setVisible(true);
                            }
                            else
                            {
                                trade.tradeLabel.setVisible(false);
                            }
                        }
                    }
                }
            }

        });

        clearBack.addMouseListener(new MouseListener()
        {

            public void mouseClicked(MouseEvent e)
            {
            }

            public void mouseEntered(MouseEvent e)
            {
            }

            public void mouseExited(MouseEvent e)
            {
            }

            public void mousePressed(MouseEvent e)
            {
                trade.setText("Property\n" + name + "\n\n" + "Owner\n" + owner.getName());
            }

            public void mouseReleased(MouseEvent e)
            {
            }

        });

    }

    public LayeredImagePanel getCardPanel()
    {
        return (LayeredImagePanel) clearBack;
    }

    public void setOwner(Player a)
    {
        owner = a;
    }

    public void setNum(int a)
    {
        num = a;
    }

    public void tradeThis(TradeCards other)
    {
        this.clearBack.setBounds(677, 461, clearBack.getWidth(), clearBack.getHeight());
        other.clearBack.setBounds(677, 201, clearBack.getWidth(), clearBack.getHeight());

        trade.topPanel.repaint();
    }

    public boolean checkOwnersLevel0()
    {
        boolean returnable = true;

        if (trade.toTrade[1] instanceof TradeCards)
        {
            if (trade.toTrade[1].owner == this.owner)
            {
                returnable = false;
            }
        }

        return returnable;
    }

    public boolean checkOwnersLevel1()
    {
        boolean returnable = true;

        if (trade.toTrade[0] instanceof TradeCards)
        {
            if (trade.toTrade[0].owner == this.owner)
            {
                returnable = false;
            }
        }

        return returnable;
    }

}
