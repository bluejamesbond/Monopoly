package src.com.monopoly; /**
 * @(#)GamePiece.java
 *
 *
 * @Mathew Kurian
 * @version 1.00 2010/2/14
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePiece
{

    public BufferedImage graphic = new BufferedImage(48, 48, BufferedImage.TYPE_INT_ARGB);

    public ImageIcon BBall = new ImageIcon("images\\pieces\\basketball.png");
    public ImageIcon IPhone = new ImageIcon("images\\pieces\\iphone.png");
    public ImageIcon Logo = new ImageIcon("images\\pieces\\nba_logo.png");
    public ImageIcon Remi = new ImageIcon("images\\pieces\\Remi.png");
    public ImageIcon Sun = new ImageIcon("images\\pieces\\Sun.png");

    String IconName;

    public GamePiece()
    {

    }

    public GamePiece(String a)
    {
        IconName = a;

        if (a.equals("basketball"))
        {
            try
            {
                graphic = ImageIO.read(new File("images\\pieces\\basketball.png"));
            }
            catch (IOException e)
            {
            }
        }
        else if (a.equals("iphone"))
        {
            try
            {
                graphic = ImageIO.read(new File("images\\pieces\\iphone.png"));
            }
            catch (IOException e)
            {
            }
        }
        else if (a.equals("logo"))
        {
            try
            {
                graphic = ImageIO.read(new File("images\\pieces\\nba_logo.png"));
            }
            catch (IOException e)
            {
            }
        }
        else if (a.equals("remi"))
        {
            try
            {
                graphic = ImageIO.read(new File("images\\pieces\\Remi.png"));
            }
            catch (IOException e)
            {
            }
        }
        else
        {
            try
            {
                graphic = ImageIO.read(new File("images\\pieces\\Sun.png"));
            }
            catch (IOException e)
            {
            }
        }
    }

    public Graphics getGraphic()
    {
        Graphics returhGraphic = graphic.getGraphics();
        return returhGraphic;
    }

    public ImageIcon getImageIcon()
    {
        ImageIcon returnIcon;

        if (IconName.equalsIgnoreCase("basketball"))
        {
            returnIcon = BBall;
        }
        else if (IconName.equalsIgnoreCase("iphone"))
        {
            returnIcon = IPhone;
        }
        else if (IconName.equalsIgnoreCase("logo"))
        {
            returnIcon = Logo;
        }
        else if (IconName.equalsIgnoreCase("remi"))
        {
            returnIcon = Remi;
        }
        else
        {
            returnIcon = Sun;
        }

        return returnIcon;
    }

    public ImageIcon[] getFullChoices()
    {
        ImageIcon[] IconChoices = new ImageIcon[]{BBall, IPhone, Logo, Remi, Sun};
        return IconChoices;
    }

}
