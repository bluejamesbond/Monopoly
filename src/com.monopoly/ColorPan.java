package src.com.monopoly;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class ColorPan extends JComponent
{

    private static final long serialVersionUID = 1L;
    static Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    public WallUI wall;
    public boolean colorVariation = false;
    float alpha = 1.0f;
    float alphaAdjustment = -0.01f;
    BufferedImage image1;
    BufferedImage image2;
    int currentImage = 0;
    int countOdd = 0;
    int time = 60;
    private Timer timer = new Timer(time, new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
            refreshImage();
            repaint();//just repaint
        }
    });

    public ColorPan(WallUI w)
    {
        wall = w;
        setColoredPane();
        this.setBounds(0, 0, 1920, 1200);
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

    public void setColoredPane()
    {
        time = 60;

        timer.setDelay(time);

        try
        {
            timer.restart();
        }
        catch (NullPointerException e)
        {
        }

        timer.setDelay(time);

        colorVariation = true;

        image1 = createImage(dimension.width, dimension.height, true);
        image2 = createImage(dimension.width, dimension.height, false);
    }

    public void setSlideShow()
    {
        currentImage = 0;
        time = 120;
        countOdd = 0;

        colorVariation = false;

        timer.setDelay(time);

        try
        {
            timer.restart();
        }
        catch (NullPointerException e)
        {
        }

        changeImage();
    }

    public void changeImage()
    {
        if (currentImage == 0)
        {
            currentImage = 1;
            image1 = toBufferedImage(new ImageIcon(wall.wallIcons.get((currentImage) % (wall.wallIcons.size())).fullLoc).getImage());
            currentImage++;
            image2 = toBufferedImage(new ImageIcon(wall.wallIcons.get((currentImage) % (wall.wallIcons.size())).fullLoc).getImage());
            currentImage++;
        }
        else if (countOdd % 2 == 0)
        {
            image2 = toBufferedImage(new ImageIcon(wall.wallIcons.get((currentImage) % (wall.wallIcons.size())).fullLoc).getImage());
            currentImage++;
            countOdd++;
        }
        else if (countOdd % 2 == 1)
        {
            image1 = toBufferedImage(new ImageIcon(wall.wallIcons.get((currentImage) % (wall.wallIcons.size())).fullLoc).getImage());
            currentImage++;
            countOdd++;
        }
    }

    public void setVisible(boolean a)
    {
        super.setVisible(a);

        if (a && !colorVariation)
        {
            setSlideShow();
        }
        if (a && colorVariation)
        {
            setColoredPane();
        }
    }

    private BufferedImage createImage(int width, int height, boolean isRed)
    {
        int red = 0, green = 0, blue = 0;
        int i = 0;
        int[] data = new int[width * height];
        for (int y = 0; y < height; y++)
        {
            red = isRed ? (y * 37) / (height - 1) : 128;
            blue = isRed ? 128 : (y * 125) / (height - 1);
            for (int x = 0; x < width; x++)
            {
                green = (x * 165) / (width - 1);
                data[i++] = (red << 16) | (green << 8) | blue;
            }
        }
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        image.setRGB(0, 0, width, height, data, 0, width);
        return image;
    }

    public void addNotify()
    {
        super.addNotify();
        timer.start();
    }

    public void removeNotify()
    {
        super.removeNotify();
        timer.stop();
    }

    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(image1, 0, 0, this);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawImage(image2, 0, 0, this);
    }

    public void refreshImage()
    {
        alpha += alphaAdjustment;
        // switch alphaAdjustment when it gets out of range
        if (alpha <= 0.001 || alpha >= 1.0)
        {
            alphaAdjustment *= -1;

            if (!colorVariation)
            {
                changeImage();
        /*		 		try {
                // 	Thread.sleep(10000);
	    	 	} catch (InterruptedException e) {
	    	 			// TODO Auto-generated catch block
						e.printStackTrace();
				}*/
            }
        }

    }

}