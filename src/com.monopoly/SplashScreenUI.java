package src.com.monopoly; /**
 * @(#)SplashUI.java
 *
 *
 * @author
 * @version 1.00 2010/3/18
 */


import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SplashScreenUI
{

    public BackgroundUI launcher = new BackgroundUI();
    public ImagePanel clearPane = new ImagePanel("images\\menu\\back.png");
    public ImageIcon newGameNormal = new ImageIcon("images\\menu\\newgame_normal.png");
    public ImageIcon newGameOver = new ImageIcon("images\\menu\\newgame_over.png");
    public ImageIcon credNormal = new ImageIcon("images\\menu\\credits_normal.png");
    public ImageIcon credOver = new ImageIcon("images\\menu\\credits_over.png");
    public ImageIcon helpNormal = new ImageIcon("images\\menu\\help_about_normal.png");
    public ImageIcon helpOver = new ImageIcon("images\\menu\\help_about_over.png");
    public ImageIcon quitNormal = new ImageIcon("images\\menu\\quit_normal.png");
    public ImageIcon quitOver = new ImageIcon("images\\menu\\quit_over.png");
    public JLabel newGame = new JLabel(newGameNormal);
    public JLabel credGame = new JLabel(credNormal);
    public JLabel helpGame = new JLabel(helpNormal);
    public JLabel quitGame = new JLabel(quitNormal);
    public HistoryUI hist;
    public PlayerNamesUI PNameUI;
    public SplashScreenUI temp;
    public HelpUI help;
    public AePlayWave accept = new AePlayWave("file:audio\\glassAccept.wav");
    public BlackStage black;

    //	public ImageIcon changeAfterSplash = new ImageIcon("images\\backgrounds\\background1.jpg");
    JFrame splashBoard = new JFrame("Welcome");

    public SplashScreenUI(HistoryUI a, BlackStage b)
    {

        hist = a;
        temp = this;
        black = b;

        help = new HelpUI(temp);

        clearPane.setOpaque(false);

        newGame.setBounds(44, 407, 445, 45);
        helpGame.setBounds(44, 456, 445, 45);
        credGame.setBounds(44, 501, 445, 45);
        quitGame.setBounds(44, 545, 445, 45);

        /************************************************************************************************/
        newGame.addMouseListener(new MouseListener()
        {

            public void mouseClicked(MouseEvent e)
            {

            }

            public void mouseExited(MouseEvent e)
            {

                ((JLabel) e.getSource()).setIcon(newGameNormal);
                ((JLabel) e.getSource()).repaint();

                launcher.changeTxt(0);

            }

            public void mousePressed(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(newGameOver);
                ((JLabel) e.getSource()).repaint();
            }

            public void mouseReleased(MouseEvent e)
            {

                ((JLabel) e.getSource()).setIcon(newGameNormal);
                ((JLabel) e.getSource()).repaint();

                // 			accept.runOnce();

                //		launcher.changeBackGround(changeAfterSplash);

                hist.addHistory("PlayerNamesUI instantiaed");

                hideMe();

                PNameUI = new PlayerNamesUI(hist, launcher, temp);

                PNameUI.start(); //Thread Optimizations

                PNameUI.createAndShowGUI();


            }

            public void mouseEntered(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(newGameOver);
                ((JLabel) e.getSource()).repaint();

                launcher.changeTxt(1);

            }
        });
        /*********************************************************************************/
        quitGame.addMouseListener(new MouseListener()
        {

            public void mouseClicked(MouseEvent e)
            {

            }

            public void mouseExited(MouseEvent e)
            {

                ((JLabel) e.getSource()).setIcon(quitNormal);
                ((JLabel) e.getSource()).repaint();

                launcher.changeTxt(0);
            }

            public void mousePressed(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(quitOver);
                ((JLabel) e.getSource()).repaint();
            }

            public void mouseReleased(MouseEvent e)
            {

                ((JLabel) e.getSource()).setIcon(quitNormal);
                ((JLabel) e.getSource()).repaint();

                accept = new AePlayWave("file:audio\\glassAccept.wav");
                //  		accept.runOnce();

                System.exit(0);
            }

            public void mouseEntered(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(quitOver);
                ((JLabel) e.getSource()).repaint();

                launcher.changeTxt(4);

            }
        });
        /********************************************************/
        credGame.addMouseListener(new MouseListener()
        {

            public void mouseClicked(MouseEvent e)
            {

            }

            public void mouseExited(MouseEvent e)
            {

                ((JLabel) e.getSource()).setIcon(credNormal);
                ((JLabel) e.getSource()).repaint();

                launcher.changeTxt(0);
            }

            public void mousePressed(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(credOver);
                ((JLabel) e.getSource()).repaint();
            }

            public void mouseReleased(MouseEvent e)
            {

                ((JLabel) e.getSource()).setIcon(credNormal);
                ((JLabel) e.getSource()).repaint();

                hideMe();
                // 		accept.runOnce();
                Credits cred = new Credits(launcher, black);
                cred.start(); //Threaded Optimization
                cred.createAndShowGUI();

            }

            public void mouseEntered(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(credOver);
                ((JLabel) e.getSource()).repaint();

                launcher.changeTxt(3);

            }
        });
        /********************************************************/
        helpGame.addMouseListener(new MouseListener()
        {

            public void mouseClicked(MouseEvent e)
            {

            }

            public void mouseExited(MouseEvent e)
            {

                ((JLabel) e.getSource()).setIcon(helpNormal);
                ((JLabel) e.getSource()).repaint();

                launcher.changeTxt(0);
            }

            public void mousePressed(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(helpOver);
                ((JLabel) e.getSource()).repaint();
            }

            public void mouseReleased(MouseEvent e)
            {

                ((JLabel) e.getSource()).setIcon(helpNormal);
                ((JLabel) e.getSource()).repaint();

                hideMe();
                //    	accept.runOnce();
                help.createAndShowGUI();

            }

            public void mouseEntered(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(helpOver);
                ((JLabel) e.getSource()).repaint();

                launcher.changeTxt(2);

            }
        });
        /********************************************************/

        clearPane.add(newGame);
        clearPane.add(credGame);
        clearPane.add(helpGame);
        clearPane.add(quitGame);

        createAndShowGUI();
        //	ost.run();
    }

    public void createAndShowGUI()
    {
        launcher.addMenuBoard(clearPane);
    }

    public void hideMe()
    {
        launcher.hideMenuBoard();
    }

    public void showMe()
    {
        clearPane.setBounds(0, 0, 1210, 775);
        launcher.menuBoard.setVisible(true);
        launcher.reset();
    }

}