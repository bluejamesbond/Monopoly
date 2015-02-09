package src.com.monopoly; /**
 * @(#)PlayerTurnInfoUI.java
 *
 *
 * @author
 * @version 1.00 2010/2/27
 */

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;

public class PlayerTurnInfoUI
{

    public JFrame tempNameFrame;
    public TransparentBackground tempNamePanel;
    public JLabel tempNameLabel = null;

    int PLAYERNUM = 1;
    int NUMPLAYERCOUNT = 0;

    ArrayList<Player> playerList = null;

    boolean recreationFrames = true; //Continue

    public PlayerTurnInfoUI(ArrayList<Player> a) //From POPUI << GAMEBOARDUI [TRANSFERRED]
    {
        NUMPLAYERCOUNT = a.size();
        playerList = a;
    }

    public void actionPlayers() ////DEPRECATED METHOD
    {
        tempNameFrame = new JFrame("tempNameFrame");
        tempNamePanel = new TransparentBackground(tempNameFrame);

        String tempName = playerList.get((PLAYERNUM) % NUMPLAYERCOUNT).getName();

        /****************************************** MAKE YOUR MOVE*/

        tempNamePanel.removeAll();
        tempNamePanel.setLayout(new FlowLayout());

        tempNameLabel = new JLabel(tempName + ": Make Your Move");
        tempNameLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 36));

        tempNamePanel.add(tempNameLabel);

        tempNameFrame.setContentPane(tempNamePanel);
        tempNameFrame.setUndecorated(true);

        tempNameFrame.setAlwaysOnTop(true);
        tempNameFrame.pack();
        tempNameFrame.setLocationRelativeTo(null);

        tempNameFrame.setVisible(true);

        Timer timer = new Timer();
        timer.schedule(new HideWindow(tempNameFrame), 2000);

        PLAYERNUM++;

        try
        {
            Thread.sleep(2001);
        }
        catch (InterruptedException a)
        {
        }
        ;

        tempNameFrame = null;
        tempNamePanel = null;
        tempName = null;

        Runtime r = Runtime.getRuntime();
        r.gc();

    }

}
    
   
