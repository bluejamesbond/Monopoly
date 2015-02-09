package src.com.monopoly; /**
 * @(#)PropertyReader.java
 *
 *
 * @Mathew Kurian
 * @version 1.00 2010/2/17
 */


import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PropertyReader
{

    /*public FileInputStream readInput = null;
    public FileReader readMe = null;
    public BufferedReader readIn = null;*/
    public Scanner kb = null;

    ArrayList<String> nameList = new ArrayList<String>();
    ArrayList<Integer> positionList = new ArrayList<Integer>();
    ArrayList<Integer> rentList = new ArrayList<Integer>();
    ArrayList<Integer> priceList = new ArrayList<Integer>();
    ArrayList<String> groupList = new ArrayList<String>();

    public PropertyReader()
    {
    }

    public void populateAll()
    {
        populateGroup();
        populateName();
        populatePosition();
        populatePrice();
        populateRent();
    }

    public void populateName()
    {
        try
        {
            kb = new Scanner(new File("data_properties.xml"));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "File Reader Error (Source: PropertyReader.java [PropertyReader()])", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        while (kb.hasNext())
        {
            nameList.add(kb.nextLine());
            kb.nextLine();
            kb.nextLine();
            kb.nextLine();
            kb.nextLine();
        }
        kb.close();
    }

    public void populatePosition()
    {
        try
        {
            kb = new Scanner(new File("data_properties.xml"));

            while (kb.hasNext())
            {
                kb.nextLine();
                positionList.add(Integer.parseInt(kb.nextLine()));
                kb.nextLine();
                kb.nextLine();
                kb.nextLine();
            }
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "File Reader Error (Source: PropertyReader.java [PropertyReader()])", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void populatePrice()
    {
        try
        {
            kb = new Scanner(new File("data_properties.xml"));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "File Reader Error (Source: PropertyReader.java [PropertyReader()])", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        while (kb.hasNext())
        {
            kb.nextLine();
            kb.nextLine();
            priceList.add(Integer.parseInt(kb.nextLine()));
            kb.nextLine();
            kb.nextLine();
        }
        kb.close();
    }

    public void populateRent()
    {
        try
        {
            kb = new Scanner(new File("data_properties.xml"));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "File Reader Error (Source: PropertyReader.java [PropertyReader()])", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        while (kb.hasNext())
        {
            kb.nextLine();
            kb.nextLine();
            kb.nextLine();
            rentList.add(Integer.parseInt(kb.nextLine()));
            kb.nextLine();
        }
        kb.close();
    }

    public void populateGroup()
    {
        try
        {
            kb = new Scanner(new File("data_properties.xml"));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "File Reader Error (Source: PropertyReader.java [PropertyReader()])", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        while (kb.hasNext())
        {
            kb.nextLine();
            kb.nextLine();
            kb.nextLine();
            kb.nextLine();
            groupList.add(kb.nextLine());
        }
        kb.close();
    }

    public ArrayList<String> getNameArrayList()
    {
        return nameList;
    }

    public ArrayList<Integer> getPositonArrayList()
    {
        return positionList;
    }

    public ArrayList<Integer> getPriceArrayList()
    {
        return priceList;
    }

    public ArrayList<Integer> getRentArrayList()
    {
        return rentList;
    }

    public ArrayList<String> getGroupArrayList()
    {
        return groupList;
    }

}