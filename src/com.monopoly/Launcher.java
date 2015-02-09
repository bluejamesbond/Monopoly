package src.com.monopoly; /**
 * @(#)Launcher.java
 *
 *
 * @Mathew Kurian
 * @version 1.00 2010/2/11
 */

import javax.swing.*;
import java.awt.*;

public class Launcher
{

    public static Runtime runtime = Runtime.getRuntime();
    public static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    public static HistoryUI hist = new HistoryUI();
    public static BlackStage black = new BlackStage();

    public static void main(String[] args)
    {       
        /*Change Log
         *
    	 * * v4.96
    	 * 27-April-2010
    	 * Increased efficiency.
    	 * Optimized the program.
    	 * Added support for drag and drop for TradeUI.
    	 *     	 
    	 * v4.95
    	 * 25-April-2010
    	 * Added support for new class TradeCards.
    	 * Added new user-interface theme.
    	 * Fixed Bug (#1321).
    	 * 
    	 * v4.94
    	 * 24-April-2010
    	 * Enhanced option panes using JDialogs.
    	 * Added new OptionPane class.
    	 * Added support for progress bar as loader.
    	 * Added support for timers during initiation.
    	 * Minor tweaks for statistics images.
    	 * Enhanced loader image.
    	 * 
    	 * v4.93
    	 * 23-April-2010
    	 * Reconfigured PopUpUI class.
    	 * Added support for layered popups.
    	 * Fixed transparency issues dealing with popups.    	 
    	 * Added support for custom option panes.    	  
    	 * Identified Bug (#1322): Time the custom option panes.
    	 * Identified Bug (#1321): TradeUI not showing added images/layers/panels.
    	 *     	  
    	 * v4.92
    	 * 22-April-2010
    	 * Added multiple minor tweaks for program optimization.
    	 * Added support for layered image panes.
    	 * 
    	 * v4.91
    	 * 21-April-2010
    	 * Updated trade user interface.
    	 * Added multiple minor tweaks for program optimization.
    	 * 
    	 * v4.90 
    	 * 20-April-2010
    	 * Added multiple minor tweaks for program optimization.
    	 * Updated trade user interface.
    	 * Improved transparency.
    	 * Added support for draggable transparent frames.
    	 * Fixed bug involving some memory leaks.
    	 * Added beta support of new tool-tip (experiences flashing frames).
    	 * Added support for multi-threading support for multi-core processors.
    	 * Added support for auto-detection of processors, memory, and screen resolution.
    	 */

//        try
//        {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        }
//        catch (Exception e)
//        {
//        }
//
//        OptionPane warning = new OptionPane();
//        warning.showDetection("Detected---\n\n" +
//                "CPU Processors: " + runtime.availableProcessors() + " cores (Recommended: 2 or more)" + "\n" +
//                "Resolution: " + dim.width + "x" + dim.height + " (Recommended: 1280x1024 or more)" + "\n" +
//                "JVM Dedicated Memory: " + runtime.freeMemory() / 1000000 + "MB (Recommended: 150 MB or more)" +
//                "\n\n*Continuing (in 10 seconds) without meeting recommended may lead to unexpected results." + "");
//
//        /************************************************/
//        warning.initiateTimerDetection();
//        /************************************************/
//        try
//        {
//            Thread.sleep(10000);
//        }
//        catch (InterruptedException e1)
//        {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        }
//        /************************************************/
//        warning.frameDetection.setVisible(false);
//        warning.showProgress();
//        warning.initiateTimerProgress();
//        /************************************************/
//        try
//        {
//            Thread.sleep(26000);
//        }
//        catch (InterruptedException e1)
//        {
//            e1.printStackTrace();
//        }
//        /************************************************/
//        warning.frameProgress.setVisible(false);
//        /************************************************/

        JFrame logo = new JFrame("");
        logo.add(new JLabel(new ImageIcon("images\\logo.png")));
        logo.setUndecorated(true);
        logo.pack();
        logo.setLocationRelativeTo(null);
        logo.setVisible(true);

        try
        {
            Thread.sleep(2000);
        }
        catch (InterruptedException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        logo.setVisible(false);

        JFrame logoFade = new JFrame("");
        logoFade.setBackground(Color.BLACK); //Frame Background Color
        logoFade.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hist.addHistory("Frame Background Color Set");

        JPanel background = new JPanel();
        background.setLayout(new FlowLayout());
        background.setOpaque(false);
        hist.addHistory("Background Color Set");

        FadeLogoUI fadeL = new FadeLogoUI();
        fadeL.setBackground(Color.BLACK); //Fade to Color
        hist.addHistory("Fader Formed");

        logoFade.setGlassPane(fadeL);

        background.add(new JLabel(new ImageIcon("images\\backgrounds\\title.png")));

        logoFade.setContentPane(background);
        logoFade.setUndecorated(true);
        logoFade.pack();

        logoFade.setLocationRelativeTo(logoFade.getRootPane()); //Center

        //   black.createAndShowGUI();  //Black stage will always be in the back

        logoFade.setVisible(true);

        try
        {
            Thread.sleep(4000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        fadeL.setVisible(true); //Begin the Fade
        fadeL.fadeOut();        //Commence Fade

        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        logoFade.setVisible(false);   //Hide the Fade Frame

        new SplashScreenUI(hist, black); //AUTO STARTUP
    }

}        



