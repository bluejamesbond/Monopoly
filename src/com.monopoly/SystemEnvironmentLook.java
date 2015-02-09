package src.com.monopoly;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Map;

public class SystemEnvironmentLook
{
    private final static JTextArea output = new JTextArea();
    public static JFrame f = new JFrame("");

    public static void output(String str)
    {
        output.append(str);
    }

    public static void initGUI()
    {
        output.setForeground(Color.WHITE);
        output.setBackground(new Color(18, 30, 49));

        f.add(output, BorderLayout.CENTER);
        f.setSize(1280, 1024);
        f.setLocationRelativeTo(null);
        f.setUndecorated(true);
        f.setVisible(true);

    }

    public void releasePanel()
    {
        initGUI();

        output.setWrapStyleWord(true);
        output.setLineWrap(true);

        Map<String, String> env = System.getenv();
        Iterator<String> iter = env.keySet().iterator();
        while (iter.hasNext())
        {
            String key = iter.next();
            output(String.format("%s   >>>>   %s\n", key, env.get(key)));

            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e1)
            {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

        try
        {
            Thread.sleep(200);
        }
        catch (InterruptedException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        output("\n\n\nCONFIGURING TEXTURING RENDERING........................\n");

        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        output("\nENABLING AERO SHADERS............................\n");

        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        output("\nENGINE STARTING............................\n");

        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        output("\nLOADER INITIALIZING............................\n");

        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        output("\n-------------------------FOR EDUCATIONAL PURPOSES ONLY-------------------------\n");

        try
        {
            Thread.sleep(3000);
        }
        catch (InterruptedException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }


        f.setVisible(false);
    }
}