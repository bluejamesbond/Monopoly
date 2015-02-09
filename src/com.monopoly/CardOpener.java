package src.com.monopoly; /**
 * @(#)PropertiesListener.java
 *
 *
 * @author
 * @version 1.00 2010/3/17
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;


public class CardOpener implements MouseListener
{

    public ChanceCards chanceCard;
    public CommChestCards commCard;
    public JPanel propFullView;
    public JLabel viewEnlarged;
    public ImageIcon origEnlarged = new ImageIcon("images\\deeds\\choose_to_begin.jpg");

    public CardOpener(ChanceCards c, CommChestCards cc, JPanel a)
    {
        chanceCard = c;
        commCard = cc;
        propFullView = a;

        viewEnlarged = new JLabel();

        viewEnlarged.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewEnlarged.setAlignmentY(Component.CENTER_ALIGNMENT);
    }

    static Image iconToImage(Icon icon)
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

    public void mouseClicked(MouseEvent e)
    {
    }

    public void mouseExited(MouseEvent e)
    {
    }

    public void mousePressed(MouseEvent e)
    {

        System.out.println(((JLabel) e.getSource()).getName());

        if (chanceCard.getCertainCard(((JLabel) e.getSource()).getName()) != null)
        {
            viewEnlarged.setIcon(createThumbnail(iconToImage(chanceCard.getCertainCard((String) ((JLabel) e.getSource()).getName())), 250)); //get Icon from JLabel and Convert to Image
        }
        else
        {
            viewEnlarged.setIcon(createThumbnail(iconToImage(commCard.getCertainCard((String) ((JLabel) e.getSource()).getName())), 250)); //get Icon from JLabel and Convert to Image
        }

        propFullView.removeAll();
        propFullView.add(viewEnlarged);

        propFullView.validate();
        propFullView.repaint();

    }

    public void mouseReleased(MouseEvent e)
    {
    }

    public void mouseEntered(MouseEvent e)
    {
    }

    public ImageIcon createThumbnail(Image a, int largestDimension)
    {
        double scale = 85;
        int sizeDifference, originalImageLargestDim;

        Image inImage = a;

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

        return new ImageIcon(inImage);

    }

}