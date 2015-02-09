package src.com.monopoly;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

public class FadeImages implements ActionListener
{

    public JLabel label;
    public FadeImages fade = this;
    int[] data0, data1, step;
    Timer timer = new Timer(50, this);
    private int currentStage;

    public FadeImages(int stages, Image a, Image b, JLabel toUpdate)
    {

        label = toUpdate;


        BufferedImage image0 = convert(toBufferedImage(a));

        BufferedImage image1 = convert(toBufferedImage(b));


        currentStage = stages - 1;
        data0 = ((DataBufferInt) (image0.getRaster().getDataBuffer())).getData();
        data1 = ((DataBufferInt) (image1.getRaster().getDataBuffer())).getData();
        step = new int[data0.length];
        Random rnd = new Random();
        for (int i = 0, ub = step.length; i < ub; ++i)
            step[i] = rnd.nextInt(currentStage);
    }

    static BufferedImage convert(BufferedImage image)
    {
        int w = image.getWidth(), h = image.getHeight();
        BufferedImage result = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = result.createGraphics();
        g.drawRenderedImage(image, null);
        g.dispose();
        return result;
    }

    public static BufferedImage toBufferedImage(Image image)
    {
        if (image instanceof BufferedImage)
        {
            return (BufferedImage) image;
        }

        // This code ensures that all the pixels in the image are loaded
        image = new ImageIcon(image).getImage();

        // Determine if the image has transparent pixels; for this method's
        // implementation, see Determining If an Image Has Transparent Pixels
        boolean hasAlpha = true;

        // Create a buffered image with a format that's compatible with the screen
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try
        {
            // Determine the type of transparency of the new buffered image
            int transparency = Transparency.OPAQUE;
            if (hasAlpha)
            {
                transparency = Transparency.BITMASK;
            }

            // Create the buffered image
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(
                    image.getWidth(null), image.getHeight(null), transparency);
        }
        catch (HeadlessException e)
        {
            // The system does not have a screen
        }

        if (bimage == null)
        {
            // Create a buffered image using the com.monopoly color model
            int type = BufferedImage.TYPE_INT_RGB;
            if (hasAlpha)
            {
                type = BufferedImage.TYPE_INT_ARGB;
            }
            bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
        }

        // Copy image to buffered image
        Graphics g = bimage.createGraphics();

        // Paint the image onto the buffered image
        g.drawImage(image, 0, 0, null);
        g.dispose();

        return bimage;
    }

    public boolean nextStage()
    {
        if (currentStage > 0)
        {
            --currentStage;
            for (int i = 0, ub = step.length; i < ub; i++)
                if (step[i] == currentStage)
                    data0[i] = data1[i];
            return true;
        }
        else
            return false;
    }

    public void actionPerformed(ActionEvent evt)
    {
        if (fade.nextStage())
            label.repaint();
        else
            timer.stop();

    }
}
