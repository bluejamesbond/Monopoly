package src.com.monopoly; /**
 * @(#)Player.java
 *
 *
 * @Mathew Kurian
 * @version 1.00 2010/2/13
 */

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Player
{

    public String name;
    public int account;
    public ImageIcon icon;
    public int myCellIndex = 0;
    public int myNum = 0;
    public GamePiece trial;
    public ArrayList<Integer> PropertiesBought = new ArrayList<Integer>();
    public int[] houses = new int[39];
    public ImageIcon navy = new ImageIcon("images\\prop_color\\navy.png");
    public ImageIcon purple = new ImageIcon("images\\prop_color\\purple.png");
    public ImageIcon pink = new ImageIcon("images\\prop_color\\pink.png");
    public ImageIcon orange = new ImageIcon("images\\prop_color\\orange.png");
    public ImageIcon red = new ImageIcon("images\\prop_color\\red.png");
    public ImageIcon yellow = new ImageIcon("images\\prop_color\\yellow.png");
    public ImageIcon green = new ImageIcon("images\\prop_color\\green.png");
    public ImageIcon blue = new ImageIcon("images\\prop_color\\blue.png");
    private Set<ImageIcon> PropertiesColorBought = new HashSet<ImageIcon>();

    public Player(String a, String iconName)
    {
        name = a;
        trial = new GamePiece(iconName);
        account = 30000;
    }

    public int getNum()
    {
        return myNum;
    }

    public void setNum(int a)
    {
        myNum = a;
    }

    public void setPiece(String a)
    {
        icon = new GamePiece(a).getImageIcon();
    }

    public void addAccount(int a)
    {
        account = account + a;
    }

    public void subAccount(int a)
    {
        account = account - a;
    }

    public int getCellIndex()
    {
        return myCellIndex;
    }

    public void setCellIndex(int index)
    {
        myCellIndex = index;
    }

    public GamePiece getGamePiece()
    {
        return trial;
    }

    public int getAccount()
    {
        return account;
    }

    public String getName()
    {
        return name;
    }

    public void addPropertiesBought(int a)
    {
        PropertiesBought.add(a);
    }

    public Set<ImageIcon> getPropertiesBoughtColor()
    {
        return PropertiesColorBought;
    }

    public void addPropertiesBoughtColor(String a)
    {
        if (a.equalsIgnoreCase("navy"))
        {
            PropertiesColorBought.add(navy);
        }
        if (a.equalsIgnoreCase("purple"))
        {
            PropertiesColorBought.add(purple);
        }
        if (a.equalsIgnoreCase("pink"))
        {
            PropertiesColorBought.add(pink);
        }
        if (a.equalsIgnoreCase("orange"))
        {
            PropertiesColorBought.add(orange);
        }
        if (a.equalsIgnoreCase("red"))
        {
            PropertiesColorBought.add(red);
        }
        if (a.equalsIgnoreCase("yellow"))
        {
            PropertiesColorBought.add(yellow);
        }
        if (a.equalsIgnoreCase("green"))
        {
            PropertiesColorBought.add(green);
        }
        if (a.equalsIgnoreCase("blue"))
        {
            PropertiesColorBought.add(blue);
        }
    }

    public void addHouses(int position)
    {
        houses[position]++;
    }

    public void removeHouses(int position)
    {
        houses[position] = 0;
    }

    public int getHouses(int position)
    {
        return houses[position];
    }
}