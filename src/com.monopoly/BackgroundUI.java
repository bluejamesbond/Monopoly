package src.com.monopoly; /**
 * @(#)BackgroundUI.java
 *
 *
 * @Mathew Kurian
 * @version 1.00 2010/2/13
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.Timer;
import java.util.TimerTask;


//BLUE: 37, 125, 165
//GRAY: 84, 83, 78
//DARK GRAY: 17, 17, 17

public class BackgroundUI
{

    public JFrame backgroundFrame;
    public JLayeredPane backgroundPanel;
    public JPanel bar = new JPanel(null);
    public JPanel barInternal = new JPanel(null);
    public ImageIcon splashScreenImage = new ImageIcon("images\\backgrounds\\full\\full23.jpg");
    public ImageIcon themeScreenImage = new ImageIcon("images\\backgrounds\\full23.png");
    public JLabel backgroundLabel = new JLabel(splashScreenImage);
    public JLabel themeLabel = new JLabel(themeScreenImage);
    public JLabel bottomBar = new JLabel(new ImageIcon("images\\bar\\bottom.png"));
    public JLabel tunesB = new JLabel(new ImageIcon("images\\bar\\TUNES.png"));
    public JLabel controlsB = new JLabel(new ImageIcon("images\\bar\\EXTRAS.png"));
    public JLabel wallB = new JLabel(new ImageIcon("images\\bar\\WALLS.png"));
    public JLabel borderB = new JLabel(new ImageIcon("images\\bar\\BORDER_hover.png"));
    public JLabel liteBlack = new JLabel(new ImageIcon("images\\bar\\overlay.png"));
    public JLabel close1 = new JLabel(new ImageIcon("images\\bar\\close.png"));
    public JLabel close2 = new JLabel(new ImageIcon("images\\bar\\close.png"));
    public JLabel close3 = new JLabel(new ImageIcon("images\\bar\\close.png"));
    public JLabel splashExtension = new JLabel(new ImageIcon("images\\menu\\txt\\main_menu.png"));
    public Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    public JPanel gameBoard;
    public JPanel menuBoard;
    public JPanel helpBoard;
    public JPanel credBoard;
    public JLayeredPane playBoard;
    public JLayeredPane panelTunes = new JLayeredPane();
    public JLayeredPane controlOptions = new JLayeredPane();
    public JLayeredPane wallOptions = new JLayeredPane();
    public JLayeredPane innerPanel1;
    public JLayeredPane innerPanel2;
    public JLayeredPane innerPanel3;
    public JPanel overLay = new JPanel();
    public Timer musicSlider;
    public Timer controlSlider;
    public Timer wallSlider;
    public Timer barSlider;
    public MusicUI music = new MusicUI();
    public ControlsUI sound = new ControlsUI(music);
    public WallUI walls = new WallUI(this);
    public boolean borderOn = true;
    public ColorPan colorFader = new ColorPan(walls);

    public BackgroundUI()
    {
        dim.width -= 100;
        dim.height -= 100;

        backgroundFrame = new JFrame();

        bar.setSize(dim.width, dim.height);

        bar.setOpaque(false);

        backgroundPanel = new JLayeredPane();
        backgroundPanel.setLayout(null);

        backgroundLabel.setVisible(false);
        backgroundLabel.setBounds(0 - ((splashScreenImage.getIconWidth() - dim.width) / 2), 0 - ((splashScreenImage.getIconHeight() - dim.height) / 2), dim.width, dim.height);
        backgroundPanel.add(backgroundLabel, new Integer(0));

        backgroundPanel.add(colorFader, new Integer(1));

        themeLabel.setBounds(0, 0 - ((splashScreenImage.getIconHeight() - dim.height) / 2) - 600, dim.width, dim.height + 1200);
        backgroundPanel.add(themeLabel, new Integer(2));

        splashExtension.setVisible(false);
        backgroundPanel.add(splashExtension, new Integer(15));

        createAndShowGUI();
    }

    public void createAndShowGUI()
    {
        backgroundFrame.getContentPane().add(backgroundPanel);
        backgroundFrame.pack();
        backgroundFrame.setSize(dim.width, dim.height + 10);
        backgroundFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        backgroundFrame.setLocationRelativeTo(null);
        backgroundFrame.setResizable(false);
        backgroundFrame.setVisible(true);
    }

    public void changeBackGround(ImageIcon e)
    {
        backgroundLabel.setIcon(e);
        backgroundLabel.validate();
        backgroundLabel.repaint();
    }

    public void changeTheme(ImageIcon e)
    {
        themeLabel.setIcon(e);
        themeLabel.validate();
        themeLabel.repaint();
    }

    public void hideTheme()
    {
        themeLabel.setVisible(false);
    }

    public void showTheme()
    {
        themeLabel.setVisible(true);
    }

    public void reset()
    {
        backgroundLabel.setIcon(splashScreenImage);
        backgroundLabel.validate();
        backgroundLabel.repaint();
        backgroundPanel.repaint();
    }

    public void hideMe()
    {
        backgroundFrame.setVisible(false);
    }

    public void addGameBoard(JPanel a)
    {
        if (gameBoard == null)
        {
            changeTheme(new ImageIcon("images\\backgrounds\\theme\\game.png"));

            gameBoard = a;

            gameBoard.addMouseMotionListener(new MouseMotionAdapter()
            {

                public void mouseDragged(MouseEvent E)
                {
                    Point p = SwingUtilities.convertPoint(gameBoard, E.getPoint(), backgroundFrame);
                    gameBoard.setBounds(p.x, p.y, gameBoard.getWidth(), gameBoard.getHeight());
                }
            });

            backgroundPanel.add(centerPane(gameBoard), new Integer(3));
        }
        else
        {
            changeTheme(new ImageIcon("images\\backgrounds\\theme\\game.png"));

            centerPane(gameBoard);
            gameBoard.setVisible(true);
        }
    }

    public void hideGameBoard()
    {
        gameBoard.setVisible(false);
    }

    public void addMenuBoard(JPanel a)
    {
        if (menuBoard == null)
        {
            buildBar();

            changeTheme(new ImageIcon("images\\backgrounds\\theme\\menu.png"));

            menuBoard = a;

            splashExtension.setBounds(dim.width - 924, dim.height - 328, 920, 210);
            splashExtension.setVisible(true);

            menuBoard.setBounds(392, 0, 343, 1198);

            backgroundPanel.add(menuBoard, new Integer(4));
        }
        else
        {
            changeTheme(new ImageIcon("images\\backgrounds\\theme\\menu.png"));

            centerPane(menuBoard);
            menuBoard.setVisible(true);
        }
    }

    public void hideMenuBoard()
    {
        splashExtension.setVisible(false);
        menuBoard.setVisible(false);
    }

    public void addPlayBoard(JLayeredPane a)
    {
        if (playBoard == null)
        {
            changeTheme(new ImageIcon("images\\backgrounds\\theme\\player.png"));

            playBoard = a;

            playBoard.addMouseMotionListener(new MouseMotionAdapter()
            {

                public void mouseDragged(MouseEvent E)
                {
                    Point p = SwingUtilities.convertPoint(playBoard, E.getPoint(), backgroundFrame);
                    playBoard.setBounds(p.x, p.y, playBoard.getWidth(), playBoard.getHeight());
                }
            });

            backgroundPanel.add(centerPane(playBoard), new Integer(5));
        }
        else
        {
            changeTheme(new ImageIcon("images\\backgrounds\\theme\\player.png"));

            centerPane(playBoard);
            playBoard.setVisible(true);
        }

    }

    public void hidePlayBoard()
    {
        playBoard.setVisible(false);
    }

    public void addHelpBoard(JPanel a)
    {
        if (helpBoard == null)
        {
            helpBoard = a;

            changeTheme(new ImageIcon("images\\backgrounds\\theme\\help.png"));

            helpBoard.addMouseMotionListener(new MouseMotionAdapter()
            {

                public void mouseDragged(MouseEvent E)
                {
                    Point p = SwingUtilities.convertPoint(helpBoard, E.getPoint(), backgroundFrame);
                    helpBoard.setBounds(p.x, p.y, helpBoard.getWidth(), helpBoard.getHeight());
                }
            });

            backgroundPanel.add(centerPane(helpBoard), new Integer(6));
        }
        else
        {
            changeTheme(new ImageIcon("images\\backgrounds\\theme\\help.png"));

            centerPane(helpBoard);
            helpBoard.setVisible(true);
        }
    }

    public void hideHelpBoard()
    {
        helpBoard.setVisible(false);
    }

    public void addCredBoard(JPanel a)
    {
        if (credBoard == null)
        {
            credBoard = a;

            changeTheme(new ImageIcon("images\\backgrounds\\theme\\credits.png"));

            credBoard.addMouseMotionListener(new MouseMotionAdapter()
            {

                public void mouseDragged(MouseEvent E)
                {
                    Point p = SwingUtilities.convertPoint(credBoard, E.getPoint(), backgroundFrame);
                    credBoard.setBounds(p.x, p.y, credBoard.getWidth(), credBoard.getHeight());
                }
            });

            backgroundPanel.add(centerPane(credBoard), new Integer(7));

        }
        else
        {
            changeTheme(new ImageIcon("images\\backgrounds\\theme\\credits.png"));

            centerPane(credBoard);
            credBoard.setVisible(true);
        }
    }

    public void hidCredBoard()
    {
        credBoard.setVisible(false);
    }

    public void buildBar()
    {
        barInternal.setPreferredSize(new Dimension(1920, 400));
        barInternal.setBounds(0, 0, 1920, 400);
        barInternal.setOpaque(false);

        bottomBar.setBounds(0, 0, 1920, 400);   //image

        tunesB.setBounds(dim.width - 150, 6, 62, 19);
        tunesB.addMouseListener(new MouseListener()
        {
            public void mouseClicked(MouseEvent e)
            {
            }

            public void mouseEntered(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\TUNES_hover.png"));
            }

            public void mouseExited(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\TUNES.png"));
            }

            public void mousePressed(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\TUNES_hover.png"));
            }

            public void mouseReleased(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\TUNES.png"));
                showTunesOptions();
            }
        });

        controlsB.setBounds(dim.width - 92, 6, 62, 19);
        controlsB.addMouseListener(new MouseListener()
        {
            public void mouseClicked(MouseEvent e)
            {
            }

            public void mouseEntered(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\EXTRAS_hover.png"));
            }

            public void mouseExited(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\EXTRAS.png"));
            }

            public void mousePressed(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\EXTRAS_hover.png"));
            }

            public void mouseReleased(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\EXTRAS.png"));
                showControls();
            }
        });

        wallB.setBounds(dim.width - 208, 6, 62, 19);
        wallB.addMouseListener(new MouseListener()
        {
            public void mouseClicked(MouseEvent e)
            {
            }

            public void mouseEntered(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\WALLS_hover.png"));
            }

            public void mouseExited(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\WALLS.png"));
            }

            public void mousePressed(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\WALLS_hover.png"));
            }

            public void mouseReleased(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\WALLS.png"));
                showWallOptions();
            }
        });

        borderB.setBounds(dim.width - 266, 6, 62, 19);
        borderB.addMouseListener(new MouseListener()
        {
            public void mouseClicked(MouseEvent e)
            {
            }

            public void mouseEntered(MouseEvent e)
            {
                if (!borderOn)
                {
                    ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\BORDER_hover.png"));
                }
            }

            public void mouseExited(MouseEvent e)
            {
                if (!borderOn)
                {
                    ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\BORDER.png"));
                }
            }

            public void mousePressed(MouseEvent e)
            {
                if (borderOn)
                {
                    ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\BORDER_hover.png"));
                    themeLabel.setVisible(false);
                    borderOn = false;
                }
                else
                {
                    ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\BORDER_hover.png"));
                    themeLabel.setVisible(true);
                    borderOn = true;

                }
            }

            public void mouseReleased(MouseEvent e)
            {
                if (!borderOn)
                {
                    ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\BORDER.png"));
                }
                else
                {
                    ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\BORDER_hover.png"));

                }
            }
        });

        barInternal.add(bottomBar);
        barInternal.add(tunesB);
        barInternal.add(controlsB);
        barInternal.add(wallB);
        barInternal.add(borderB);

        bar.add(barInternal);
        bar.setBounds(0, dim.height - 50, 1920, 400);
        bar.addMouseListener(new MouseListener()
        {
            public void mouseClicked(MouseEvent arg0)
            {
            }

            public void mouseEntered(MouseEvent arg0)
            {
                //	barUp();
            }

            public void mouseExited(MouseEvent arg0)
            {
            }

            public void mousePressed(MouseEvent arg0)
            {
            }

            public void mouseReleased(MouseEvent arg0)
            {
            }
        });

        backgroundPanel.add(bar, new Integer(2));

        ///////
        overLay.setOpaque(false);
        overLay.setSize(1920, 1200);
        overLay.setVisible(false);

        liteBlack.setBounds(0 - ((splashScreenImage.getIconWidth() - dim.width) / 2), 0 - ((splashScreenImage.getIconHeight() - dim.height) / 2), 1920, 1200);
        overLay.add(liteBlack);

        backgroundPanel.add(overLay, new Integer(8));
        ////////

        ////////
        panelTunes.setLayout(null);

        int x = dim.width - 510;
        int y = dim.height + 10;

        panelTunes.setSize(475, 445);
        panelTunes.setBounds(x, y, 475, 445);
        panelTunes.setOpaque(false);

        innerPanel1 = music.getPanel();

        close1.setBounds(340, 0, 70, 50);

        close1.addMouseListener(new MouseListener()
        {

            public void mouseClicked(MouseEvent arg0)
            {
            }

            public void mouseEntered(MouseEvent arg0)
            {
            }

            public void mouseExited(MouseEvent arg0)
            {
            }

            public void mousePressed(MouseEvent arg0)
            {
            }

            public void mouseReleased(MouseEvent arg0)
            {
                hideTunesOptions();
            }
        });

        panelTunes.add(innerPanel1);
        panelTunes.add(close1, new Integer(5));
        backgroundPanel.add(panelTunes, new Integer(9));
        ////////
        controlOptions.setLayout(null);

        x = dim.width - 460;
        y = dim.height + 10;

        controlOptions.setSize(475, 445);
        controlOptions.setBounds(x, y, 475, 445);
        controlOptions.setOpaque(false);

        innerPanel2 = sound.getPanel(); //Add Extension here

        close2.setBounds(340, 0, 70, 50);

        close2.addMouseListener(new MouseListener()
        {

            public void mouseClicked(MouseEvent arg0)
            {
            }

            public void mouseEntered(MouseEvent arg0)
            {
            }

            public void mouseExited(MouseEvent arg0)
            {
            }

            public void mousePressed(MouseEvent arg0)
            {
            }

            public void mouseReleased(MouseEvent arg0)
            {
                hideControls();
            }
        });

        controlOptions.add(innerPanel2);
        controlOptions.add(close2, new Integer(5));
        backgroundPanel.add(controlOptions, new Integer(10));
        ////////
        ////////
        wallOptions.setLayout(null);

        x = dim.width - 540;
        y = dim.height + 10;

        wallOptions.setSize(475, 445);
        wallOptions.setBounds(x, y, 475, 445);
        wallOptions.setOpaque(false);

        innerPanel3 = walls.getPanel(); //Add Extension here

        close3.setBounds(340, 0, 70, 50);
        close3.addMouseListener(new MouseListener()
        {

            public void mouseClicked(MouseEvent arg0)
            {
            }

            public void mouseEntered(MouseEvent arg0)
            {
            }

            public void mouseExited(MouseEvent arg0)
            {
            }

            public void mousePressed(MouseEvent arg0)
            {
            }

            public void mouseReleased(MouseEvent arg0)
            {
                hideWallOptions();
                walls.hidePreviewPane();
                walls.deactivateControls();
            }
        });

        wallOptions.add(innerPanel3);
        wallOptions.add(close3, new Integer(5));
        backgroundPanel.add(wallOptions, new Integer(11));
        ////////

    }

    public void showTunesOptions()
    {
        overLay.setVisible(true);

        musicSlider = new Timer();
        musicSlider.scheduleAtFixedRate(new TimerTask()
        {

            int yToReach = dim.height - 495; //i.e. 800
            int currentY = dim.height + 10; //i.e. 1210

            public void run()
            {
                panelTunes.setBounds(dim.width - 510, currentY, 475, 445);

                if (yToReach == currentY)
                {
                    musicSlider.cancel();
                }

                currentY--;
            }

        }, 0, new Integer(2));

    }

    public void hideTunesOptions()
    {
        musicSlider = new Timer();
        musicSlider.scheduleAtFixedRate(new TimerTask()
        {

            int currentY = dim.height - 495; //i.e. 800
            int yToReach = dim.height + 10; //i.e. 1210

            public void run()
            {
                panelTunes.setBounds(dim.width - 510, currentY, 475, 445);

                if (yToReach == currentY)
                {
                    musicSlider.cancel();
                }

                currentY++;
            }

        }, 0, new Integer(2));

        overLay.setVisible(false);

    }

    public void showControls()
    {
        overLay.setVisible(true);

        controlSlider = new Timer();
        controlSlider.scheduleAtFixedRate(new TimerTask()
        {

            int yToReach = dim.height - 495; //i.e. 800
            int currentY = dim.height + 10; //i.e. 1210

            public void run()
            {
                controlOptions.setBounds(dim.width - 460, currentY, 475, 445);

                if (yToReach == currentY)
                {
                    controlSlider.cancel();
                }

                currentY--;
            }

        }, 0, new Integer(2));

    }

    public void hideControls()
    {
        controlSlider = new Timer();
        controlSlider.scheduleAtFixedRate(new TimerTask()
        {

            int currentY = dim.height - 495; //i.e. 800
            int yToReach = dim.height + 10; //i.e. 1210

            public void run()
            {
                controlOptions.setBounds(dim.width - 460, currentY, 475, 445);

                if (yToReach == currentY)
                {
                    controlSlider.cancel();
                }

                currentY++;
            }

        }, 0, new Integer(2));

        overLay.setVisible(false);

    }

    public void showWallOptions()
    {
        overLay.setVisible(true);

        wallSlider = new Timer();
        wallSlider.scheduleAtFixedRate(new TimerTask()
        {

            int yToReach = dim.height - 495; //i.e. 800
            int currentY = dim.height + 10; //i.e. 1210

            public void run()
            {
                wallOptions.setBounds(dim.width - 540, currentY, 475, 445);

                if (yToReach == currentY)
                {
                    wallSlider.cancel();
                    walls.activateControls();
                }

                currentY--;
            }

        }, 0, new Integer(2));

    }

    public void hideWallOptions()
    {
        wallSlider = new Timer();
        wallSlider.scheduleAtFixedRate(new TimerTask()
        {

            int currentY = dim.height - 495; //i.e. 800
            int yToReach = dim.height + 10; //i.e. 1210

            public void run()
            {
                wallOptions.setBounds(dim.width - 540, currentY, 475, 445);

                if (yToReach == currentY)
                {
                    wallSlider.cancel();
                }

                currentY++;
            }

        }, 0, new Integer(2));

        overLay.setVisible(false);

    }

    public JPanel centerPane(JPanel a)
    {
        a.setLocation((dim.width - a.getWidth()) / 2, (dim.height - a.getHeight()) / 2);

        return a;
    }

    public JLayeredPane centerPane(JLayeredPane a)
    {
        a.setLocation((dim.width - a.getWidth()) / 2, (dim.height - a.getHeight()) / 2);

        return a;
    }

    public void changeTxt(int x)
    {
        switch (x)
        {
            case 0:
                splashExtension.setIcon(new ImageIcon("images\\menu\\txt\\main_menu.png"));
                break;
            case 1:
                splashExtension.setIcon(new ImageIcon("images\\menu\\txt\\start_game.png"));
                break;
            case 2:
                splashExtension.setIcon(new ImageIcon("images\\menu\\txt\\help_about.png"));
                break;
            case 3:
                splashExtension.setIcon(new ImageIcon("images\\menu\\txt\\thank_you.png"));
                break;
            case 4:
                splashExtension.setIcon(new ImageIcon("images\\menu\\txt\\end_game.png"));
                break;
        }
    }
  /* public void barUp()
    {
    	barSlider = new Timer();
    	barSlider.scheduleAtFixedRate(new TimerTask() {
					
    			int currentY = 0; //i.e. 800
    			int yToReach = 	90; //i.e. 1210
    			
				public void run() 
				{   					
					barInternal.setBounds(0,currentY,1920,400);
					bar.repaint();
				
					if(yToReach==currentY)
					{
						barSlider.cancel();
					}
					
					currentY--;
				}
			
    	}, 0, new Integer(5));  
    }*/
}