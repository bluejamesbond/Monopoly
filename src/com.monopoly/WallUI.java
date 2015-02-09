package src.com.monopoly;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class WallUI
{

    public BackgroundUI back;

    public LayeredImagePanel wallPanel = new LayeredImagePanel("images\\bar\\walls_back.png");

    public JPanel thumbPane = new JPanel();

    public JLabel currentPic = new JLabel();
    public JLabel currentText = new JLabel("Current Wall: Dark Wood");

    public Scanner kb;

    public ArrayList<Wall> wallIcons = new ArrayList<Wall>();
    public JLabel preview = new JLabel();
    public JLabel slideShow = new JLabel(new ImageIcon("images\\bar\\AUTO.png"));
    public int uniY = 35;
    public int uniX = 35;
    public boolean activated = false;
    public Wall defaultWall;
    public boolean slideBool = false;
    int currentWidth = 144;
    int currentHeight = 90;

    public WallUI(BackgroundUI b)
    {
        back = b;

        wallPanel.setPreferredSize(new Dimension(475, 410));
        wallPanel.setBounds(0, 20, 475, 410);
        wallPanel.setLayout(null);
        wallPanel.setOpaque(false);

        addChoicesPane();
        populateViaXML();
        populateChoices();
        addPreviewPane();
        addCurrent();
    }

    public void addCurrent()
    {
        wallIcons.get(0).setActive();

        currentPic.setBounds(uniX, uniY, currentWidth, currentHeight);
        currentPic.setIcon(createThumbnail(new ImageIcon(wallIcons.get(0).fullLoc)));
        wallPanel.add(currentPic);

        currentText.setHorizontalTextPosition(JLabel.LEFT);
        currentText.setVerticalTextPosition(JLabel.CENTER);
        currentText.setFont(new Font("Calibri", Font.ITALIC, 12));
        currentText.setForeground(Color.WHITE);
        currentText.validate();

        currentText.setBounds(uniX + 198, uniY + 38, 200, 20);
        wallPanel.add(currentText);
    }

    public void addChoicesPane()
    {
        //	Border simplicity = BorderFactory.createLineBorder(Color.WHITE);

        thumbPane.setLayout(null);
        thumbPane.setPreferredSize(new Dimension(374, 218));
        //	thumbPane.setBorder(simplicity);
        thumbPane.setOpaque(false);

        thumbPane.setBounds(uniX, uniY + 110, 374, 218);
        wallPanel.add(thumbPane);

        slideShow.setBounds(uniX + 325, uniY, 50, 25);
        slideShow.addMouseListener(new MouseListener()
        {

            public void mouseClicked(MouseEvent arg0)
            {
                // TODO Auto-generated method stub

            }

            public void mouseEntered(MouseEvent arg0)
            {
                if (!slideBool)
                {
                    slideShow.setIcon(new ImageIcon("images\\bar\\AUTO_over.png"));
                }

            }

            public void mouseExited(MouseEvent arg0)
            {
                if (!slideBool)
                {
                    slideShow.setIcon(new ImageIcon("images\\bar\\AUTO.png"));
                }

            }

            public void mousePressed(MouseEvent arg0)
            {
                if (!slideBool)
                {
                    slideShow.setIcon(new ImageIcon("images\\bar\\AUTO_click.png"));
                }

            }

            public void mouseReleased(MouseEvent arg0)
            {
                if (!slideBool)
                {
                    back.backgroundLabel.setVisible(false);

                    back.colorFader.setVisible(true);
                    back.colorFader.setSlideShow();

                    setCurrent("images\\bar\\AUTO_wall.png", "Automatic");

                    slideBool = true;

                    for (int x = 0; x < wallIcons.size(); x++)
                    {
                        wallIcons.get(x).setInActive();
                    }
                }
            }

        });

        wallPanel.add(slideShow);
    }

    public void addPreviewPane()
    {
        preview.setBounds(0, 0, currentWidth, currentHeight);
        preview.setVisible(false);
        wallPanel.add(preview, new Integer(6));
    }

    public void showPreviewPane(String loc, Point a)
    {
        preview.setIcon(createThumbnail(new ImageIcon(loc)));
        preview.setBounds(a.x - 10, a.y - 10, currentWidth, currentHeight);
        preview.repaint();
        preview.setVisible(true);
    }

    public void hidePreviewPane()
    {
        preview.setVisible(false);
    }

    public void populateViaXML()
    {
        String check;
        String title;
        String thumbLoc;
        String largeLoc;
        int count = 0;

        try
        {
            kb = new Scanner(new File("configuration_wall.xml"));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "File Reader Error (Source: WallUI.java [populateViaXML()])", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        while (kb.hasNext())
        {
            check = kb.nextLine();

            if (check.substring(0, 2).equals("//") == false)
            {
                if (count == 0)
                {
                    String[] results = check.split(",\\s*");

                    title = results[0];
                    thumbLoc = results[1];
                    largeLoc = results[2];

                    defaultWall = new Wall(title, thumbLoc, largeLoc, this, true);

                    wallIcons.add(defaultWall);
                }
                else
                {
                    String[] results = check.split(",\\s*");

                    title = results[0];
                    thumbLoc = results[1];
                    largeLoc = results[2];

                    wallIcons.add(new Wall(title, thumbLoc, largeLoc, this, false));
                }

                count++;

            }
        }
        kb.close();

        thumbPane.validate();
        thumbPane.repaint();
    }

    public void populateChoices()
    {
        int x = 5;
        int y = 5;
        int count = 0;

        //7 across //-1
        //7 down  //-1

        for (int z = 1; z <= 4; z++) //y
        {
            for (int a = 1; a <= 7; a++) //x
            {
                if (count < wallIcons.size())
                {
                    wallIcons.get(count).getPanel().setBounds(x, y, 52, 52);
                    thumbPane.add(wallIcons.get(count).getPanel());
                    thumbPane.validate();
                    thumbPane.repaint();

                    x += 52;
                    count++;
                }
            }
            x = 5;
            y += 52;
        }
    }

    public void setCurrent(String loc, String title)
    {
        currentPic.setIcon(createThumbnail(new ImageIcon(loc)));
        currentPic.repaint();

        currentText.setText("Current Wall: " + title);
        currentText.repaint();
    }

    public JLayeredPane getPanel()
    {
        return wallPanel;
    }

    public ImageIcon createThumbnail(ImageIcon a)
    {
        double scale = 85;
        int sizeDifference, originalImageLargestDim;

        Image inImage = a.getImage();

        double largestDimension = currentWidth;

        if (inImage.getWidth(null) > inImage.getHeight(null))
        {
            scale = (double) largestDimension / (double) inImage.getWidth(null);
            sizeDifference = (int) (inImage.getWidth(null) - largestDimension);
            originalImageLargestDim = inImage.getWidth(null);
        }
        else
        {
            scale = (double) largestDimension / (double) inImage.getHeight(null);
            sizeDifference = (int) (inImage.getHeight(null) - largestDimension);
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
                    currentStepSize = (int) (previousIntermediateSize - largestDimension);
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

    public void activateControls()
    {
        activated = true;
    }

    public void deactivateControls()
    {
        activated = false;
    }
}
