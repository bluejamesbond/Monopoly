package src.com.monopoly; /**
 * @(#)BankUI.java
 *
 *
 * @Mathew Kurian
 * @version 1.00 2010/2/16
 */

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TradeUI implements MouseListener
{

    public LayeredImagePanel fullPanel = new LayeredImagePanel("images\\trade\\back.png");
    public JLayeredPane topPanel = new JLayeredPane();
    public ImageIcon clearSquare = new ImageIcon("images\\trade\\property_sq.png");
    public int count = 2;
    public ArrayList<TradeCards> cardList = new ArrayList<TradeCards>();
    public TradeCards[] toTrade = new TradeCards[2];
    public JLabel tradeLabel = new JLabel(new ImageIcon("images\\trade\\TRADE.png"));
    public JTextPane details = new JTextPane();
    public GameBoardUI game;

    public TradeUI(GameBoardUI g)
    {
        game = g;

        topPanel.setLayout(null);
        topPanel.setOpaque(false);

        fullPanel.setOpaque(false);

        tradeLabel.addMouseListener(new MouseListener()
        {

            public void mouseClicked(MouseEvent e)
            {
            }

            public void mouseEntered(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\trade\\TRADE_over.png"));
                ((JLabel) e.getSource()).repaint();

            }

            public void mouseExited(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\trade\\TRADE.png"));
                ((JLabel) e.getSource()).repaint();

            }

            public void mousePressed(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\trade\\TRADE_click.png"));
                ((JLabel) e.getSource()).repaint();

            }

            public void mouseReleased(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\trade\\TRADE.png"));
                ((JLabel) e.getSource()).repaint();

                if (checkReadyForTrade())
                {
                    tradeCards();
                }
                else
                {
                    setText("\n\nPlease choose two cards to trade.");
                }

            }
        });

        tradeLabel.setBounds(677, -8, 240, 260);
        tradeLabel.setVisible(false);

        SimpleAttributeSet aSet = new SimpleAttributeSet();
        StyleConstants.setForeground(aSet, Color.WHITE);
        StyleConstants.setFontFamily(aSet, "Calibri");
        StyleConstants.setFontSize(aSet, 12);

        SimpleAttributeSet bSet = new SimpleAttributeSet();
        StyleConstants.setAlignment(bSet, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontFamily(bSet, "Calibri");
        StyleConstants.setFontSize(bSet, 12);

        details.setCharacterAttributes(aSet, false);
        details.setParagraphAttributes(bSet, false);

        details.setEditable(false);
        details.setOpaque(false);
        details.setForeground(Color.WHITE);
        details.setBounds(692, 778, 191, 96);

        topPanel.add(tradeLabel);
        topPanel.add(details);

        topPanel.setBounds(0, 0, 920, 926);
        fullPanel.add(topPanel, new Integer(0));
    }

    public void setText(String a)
    {
        details.setText(a);
        details.setCaretPosition(details.getDocument().getLength());
    }

    public void combineLayers(Icon deedImage, Player owner, String prop, int index)
    {
        TradeCards deed = new TradeCards(resizeImage(iconToImage(deedImage)), this, prop, index);

        deed.setNum(count - 2);
        deed.setOwner(owner);

        cardList.add(deed);

        topPanel.add(deed.getCardPanel(), new Integer(count));

        topPanel.repaint();
        fullPanel.repaint();

        count++;
    }

    public void tradeCards()
    {
        TradeCards temp1 = toTrade[0];
        TradeCards temp2 = toTrade[1];

        toTrade[0] = null;
        toTrade[1] = null;

        toTrade[0] = temp2;
        toTrade[1] = temp1;

        temp1.tradeThis(temp2);
        game.engine.reduceAmount(temp1.owner, 80);
        game.engine.tradeDeeds(temp1.index, temp2.index, temp1.owner.getNum(), temp2.owner.getNum());

        game.bank.tradeCard(temp1.owner.getNum(), temp1.name, temp2.owner.getNum(), temp2.name);

        /**********************/
        Player a = game.FullBoardArrayList.get(temp1.index).currentImage.bought;
        Player b = game.FullBoardArrayList.get(temp2.index).currentImage.bought;

        a.removeHouses(temp1.index);
        b.removeHouses(temp2.index);

        game.FullBoardArrayList.get(temp1.index).currentImage.bought = b;
        game.FullBoardArrayList.get(temp2.index).currentImage.bought = a;

        game.FullBoardArrayList.get(temp1.index).reset();
        game.FullBoardArrayList.get(temp2.index).reset();
        /************************/
        //One Card is in a higher position than the other.

        Player temp3 = temp1.owner;
        Player temp4 = temp2.owner;

        temp1.setOwner(temp4);
        temp2.setOwner(temp3);


        setText("\n\nTrade Sucessful.");

    }

    public void addToList(TradeCards a)
    {
        if ((toTrade[0] == null) && (toTrade[1] != a))
        {
            toTrade[0] = a;

            System.out.println("Added to 0");
        }
        else if ((toTrade[1] == null) && (toTrade[0] != a))
        {
            toTrade[1] = a;

            System.out.println("Added to 1");
        }
    }

    public boolean checkReadyForTrade()
    {
        boolean returnable = false;

        if (toTrade[0] instanceof TradeCards && toTrade[1] instanceof TradeCards)
        {
            returnable = true;
        }
        return returnable;
    }

    public void removeFromList(TradeCards a)
    {
        if (toTrade[0] == a)
        {
            toTrade[0] = null;

            System.out.println("Removed from 0");
        }
        else if (toTrade[1] == a)
        {
            toTrade[1] = null;

            System.out.println("Removed from 1");
        }
    }

    public int checkAddable()
    {
        int returnable = 45;

        if (toTrade[0] == null)
        {
            returnable = 0;
        }
        else if (toTrade[1] == null)
        {
            returnable = 1;
        }

        return returnable;
    }

    public Image iconToImage(Icon icon)
    {
        if (icon instanceof ImageIcon)
        {
            return ((ImageIcon) icon).getImage();
        }
        else
        {
            int w = icon.getIconWidth();
            int h = icon.getIconHeight();
            GraphicsEnvironment ge =
                    GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gd = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gd.getDefaultConfiguration();
            BufferedImage image = gc.createCompatibleImage(w, h);
            Graphics2D g = image.createGraphics();
            icon.paintIcon(null, g, 0, 0);
            g.dispose();
            return image;
        }
    }

    public Component getGUI()
    {
        fullPanel.setBorder(null);

        return fullPanel;
    }

    public void mouseClicked(MouseEvent arg0)
    {
        // TODO Auto-generated method stub

    }

    public void mouseEntered(MouseEvent arg0)
    {
        // TODO Auto-generated method stub

    }

    public void mouseExited(MouseEvent arg0)
    {
        // TODO Auto-generated method stub

    }

    public void mousePressed(MouseEvent arg0)
    {
        // TODO Auto-generated method stub

    }

    public void mouseReleased(MouseEvent arg0)
    {
        // TODO Auto-generated method stub

    }

    public Image resizeImage(Image im)
    {
        double scale = 85;
        int largestDimension = 180;

        int sizeDifference, originalImageLargestDim;

        Image inImage = im;

        //find biggest dimension
        if (inImage.getWidth(null) > inImage.getHeight(null))
        {
            scale = (double) largestDimension / (double) inImage.getWidth(null);
            sizeDifference = inImage.getWidth(null) - largestDimension;
            originalImageLargestDim = inImage.getWidth(null);
        }
        else
        {
            scale = (double) largestDimension / (double) inImage.getHeight(null);
            sizeDifference = inImage.getHeight(null) - largestDimension;
            originalImageLargestDim = inImage.getHeight(null);
        }
        //create an image buffer to draw to
        BufferedImage outImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB); //arbitrary init so code compiles
        Graphics2D g2d;
        AffineTransform tx;

        int numSteps = sizeDifference / 100;
        int stepSize = sizeDifference / numSteps;
        int stepWeight = stepSize / 2;
        int heavierStepSize = stepSize + stepWeight;
        int lighterStepSize = stepSize - stepWeight;
        int currentStepSize, centerStep;
        double scaledW = inImage.getWidth(null);
        double scaledH = inImage.getHeight(null);
        if (numSteps % 2 == 1) //if there's an odd number of steps
            centerStep = (int) Math.ceil((double) numSteps / 2d); //find the center step
        else
            centerStep = -1; //set it to -1 so it's ignored later
        Integer intermediateSize = originalImageLargestDim, previousIntermediateSize = originalImageLargestDim;

        for (Integer i = 0; i < numSteps; i++)
        {
            if (i + 1 != centerStep) //if this isn't the center step
            {
                if (i == numSteps - 1) //if this is the last step
                {
                    //fix the stepsize to account for decimal place errors previously
                    currentStepSize = previousIntermediateSize - largestDimension;
                }
                else
                {
                    if (numSteps - i > numSteps / 2) //if we're in the first half of the reductions
                        currentStepSize = heavierStepSize;
                    else
                        currentStepSize = lighterStepSize;
                }
            }
            else //center step, use natural step size
            {
                currentStepSize = stepSize;
            }
            intermediateSize = previousIntermediateSize - currentStepSize;
            scale = (double) intermediateSize / (double) previousIntermediateSize;
            scaledW = (int) scaledW * scale;
            scaledH = (int) scaledH * scale;
            outImage = new BufferedImage((int) scaledW, (int) scaledH, BufferedImage.TYPE_INT_RGB);
            g2d = outImage.createGraphics();
            g2d.setBackground(Color.WHITE);
            g2d.clearRect(0, 0, outImage.getWidth(), outImage.getHeight());
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            tx = new AffineTransform();
            tx.scale(scale, scale);
            g2d.drawImage(inImage, tx, null);
            g2d.dispose();
            inImage = new ImageIcon(outImage).getImage();
            previousIntermediateSize = intermediateSize;
        }

        return inImage;
    }

}