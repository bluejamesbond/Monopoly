package src.com.monopoly; /**
 * @(#)GameBoardUI.java
 *
 *
 * @Mathew Kurian
 * @version 1.00 2010/2/11
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class GameBoardUI extends Thread
{

    private static final long serialVersionUID = -688044336033182863L;

    public JTextArea textArea;

    public JFrame frameBoard = new JFrame("Monopoly");
    public JPanel combinedPanel = new JPanel();
    public JPanel transBack = new JPanel();
    public ImagePanel paperShell = new ImagePanel("images\\paper\\paper.png");

    public JFrame PopUpDeedFrame;

    public TransparentBackground PopUpPanelMain;
    public ImagePanel PopUpImagePane = new ImagePanel("images\\tooltip\\tooltip.png");

    public JLabel DeedLabel = new JLabel(new ImageIcon("images\\button\\CLOSE_click.png"));

    public BoxPoperties boxProp = new BoxPoperties();
    public PairOfDice dice = new PairOfDice();
    public PopUpUI popup;

    public JPanel dicePanel;
    public ModJLabel center;
    public JLabel RollButton;

    public MKEngine engine;
    public BankUI bank = new BankUI(this);
    public TradeUI trade = new TradeUI(this);

    public int NUMPLAYERCOUNT = 0;
    public int PLAYERNUM = 0;

    public JLayeredPane popUpPane;
    public JPanel eastAreaPanel = new JPanel();
    public JPanel westAreaPanel = new JPanel();
    public JPanel PlayerPanel1asuba;
    public JPanel PlayerPanel1asubb;
    public ArrayList<ModJLabel> NorthEdgeArrayList = new ArrayList<ModJLabel>();
    public ArrayList<ModJLabel> EastEdgeArrayList = new ArrayList<ModJLabel>();
    public ArrayList<ModJLabel> WestEdgeArrayList = new ArrayList<ModJLabel>();
    public ArrayList<ModJLabel> SouthEdgeArrayList = new ArrayList<ModJLabel>();
    public ArrayList<ModJLayeredPane> FullBoardArrayList = new ArrayList<ModJLayeredPane>();
    public Timer timer;
    public Timer COMTimer;
    public Timer CHANCETimer;
    public Timer motionTimer;
    public Integer temp1;
    public ArrayList<Player> playerList = new ArrayList<Player>();
    public ImageIcon propClear = new ImageIcon("images\\stats\\property_clear.png");
    public JLabel PlayerIconLabel1 = new JLabel();
    public JLabel PlayerGlassNameLabel1 = new JLabel();
    public JLabel PlayerGlassMoneyLabel1 = new JLabel();
    public PropJLabel PlayerGlassPropertyBoxesLabel1 = new PropJLabel(propClear);
    public JLabel PlayerIconLabel2 = new JLabel();
    public JLabel PlayerGlassNameLabel2 = new JLabel();
    public JLabel PlayerGlassMoneyLabel2 = new JLabel();
    public PropJLabel PlayerGlassPropertyBoxesLabel2 = new PropJLabel(propClear);
    public JLabel PlayerIconLabel3 = new JLabel();
    public JLabel PlayerGlassNameLabel3 = new JLabel();
    public JLabel PlayerGlassMoneyLabel3 = new JLabel();
    public PropJLabel PlayerGlassPropertyBoxesLabel3 = new PropJLabel(propClear);
    public JLabel PlayerIconLabel4 = new JLabel();
    public JLabel PlayerGlassNameLabel4 = new JLabel();
    public JLabel PlayerGlassMoneyLabel4 = new JLabel();
    public PropJLabel PlayerGlassPropertyBoxesLabel4 = new PropJLabel(propClear);
    public JLabel PlayerIconLabel5 = new JLabel();
    public JLabel PlayerGlassNameLabel5 = new JLabel();
    public JLabel PlayerGlassMoneyLabel5 = new JLabel();
    public PropJLabel PlayerGlassPropertyBoxesLabel5 = new PropJLabel(propClear);
    public JLabel[] copyPlayerIconList = new JLabel[5];
    public JLabel[] copyPlayerNameList = new JLabel[5];
    public ArrayList<ImagePanel> PlayerPanelList = new ArrayList<ImagePanel>();
    public JLabel[] PlayerMoneyList = {PlayerGlassMoneyLabel1, PlayerGlassMoneyLabel2, PlayerGlassMoneyLabel3,
            PlayerGlassMoneyLabel4, PlayerGlassMoneyLabel5};
    public JLabel[] PlayerNameList = {PlayerGlassNameLabel1, PlayerGlassNameLabel2, PlayerGlassNameLabel3,
            PlayerGlassNameLabel4, PlayerGlassNameLabel5};
    public PropJLabel[] PlayerPropList = {PlayerGlassPropertyBoxesLabel1, PlayerGlassPropertyBoxesLabel2, PlayerGlassPropertyBoxesLabel3,
            PlayerGlassPropertyBoxesLabel4, PlayerGlassPropertyBoxesLabel5};
    public JLabel StarOnCurrentPlayer = new JLabel(new ImageIcon("images\\stats\\star.png"));
    public int originalLocation = 0;
    public int newLocation = 0;
    public int tempLocation;
    public AePlayWave click;
    public AePlayWave cardFlicker;
    //Panel Creations
    public JPanel gameBoard = new JPanel(new BorderLayout(1, 2));// one row, 2 columns
    public JPanel northEdge = new JPanel(new GridBagLayout()); // one row, 9 columns
    public JPanel southEdge = new JPanel(new GridBagLayout());// one row, 9 columns
    public JPanel eastEdge = new JPanel(new GridLayout(9, 1)); // six rows, 1 column
    public JPanel westEdge = new JPanel(new GridLayout(9, 1));// six rows, 1 column
    public JLayeredPane gameBoardHolder = new JLayeredPane();
    public JLayeredPane buttonHolder = new JLayeredPane();
    public LayeredImagePanel cardPanel = new LayeredImagePanel("images\\boards\\center3.png");   ///NOTE NOTE NOTE///////////////TWO OF THE SAME BACKGROUNDS***************
    public CardListener cardsL = new CardListener();
    public Player motionTemp;
    public SplashScreenUI splash;
    Scanner kb = null;
    JPanel CombinedPlayersPanel = new JPanel();
    ImagePanel PlayerPanel1 = new ImagePanel("images\\stats\\Stat.png");
    ImagePanel PlayerPanel2 = new ImagePanel("images\\stats\\Stat.png");
    ImagePanel PlayerPanel3 = new ImagePanel("images\\stats\\Stat.png");
    ImagePanel PlayerPanel4 = new ImagePanel("images\\stats\\Stat.png");
    ImagePanel PlayerPanel5 = new ImagePanel("images\\stats\\Stat.png");
    JLabel player1Jail = new JLabel(new ImageIcon("images\\jail\\stat_jail.png"));
    JLabel player2Jail = new JLabel(new ImageIcon("images\\jail\\stat_jail.png"));
    JLabel player3Jail = new JLabel(new ImageIcon("images\\jail\\stat_jail.png"));
    JLabel player4Jail = new JLabel(new ImageIcon("images\\jail\\stat_jail.png"));
    ImageIcon chanceNormal = new ImageIcon("images\\card\\chance.png");
    public JLabel Chance = new JLabel(chanceNormal);
    ImageIcon chanceYellow = new ImageIcon("images\\card\\chance_yellow.png");
    ImageIcon CommChestNormal = new ImageIcon("images\\card\\comm_chest.png");
    public JLabel CommChest = new JLabel(CommChestNormal);
    ImageIcon CommChestYellow = new ImageIcon("images\\card\\comm_chest_yellow.png");
    JLabel copyPlayerIconLabel1;
    JLabel copyPlayerIconLabel2;
    JLabel copyPlayerIconLabel3;
    JLabel copyPlayerIconLabel4;
    JLabel copyPlayerIconLabel5;
    JLabel copyPlayerGlassNameLabel1;
    JLabel copyPlayerGlassNameLabel2;
    JLabel copyPlayerGlassNameLabel3;
    JLabel copyPlayerGlassNameLabel4;
    JLabel copyPlayerGlassNameLabel5;
    BoardPieces properties = new BoardPieces();

    public GameBoardUI(SplashScreenUI s)
    {
        splash = s;
    }

    public void createAndShowGUI()
    {
        transBack.setBounds(0, 0, 1300, 1050);
        splash.launcher.addGameBoard(transBack);
        splash.launcher.backgroundPanel.repaint();
    }

    public void move(Player p, int numSpaces)
    {
        originalLocation = p.getCellIndex();
        newLocation = (originalLocation + numSpaces) % 40;

        temp1 = new Integer(originalLocation);

        tempLocation = playerList.indexOf(p);

        int MotionDelay = 0;
        int MotionPeriod = 500;

        motionTimer = new Timer();  //FORWARD MOTION ONLY
        motionTimer.scheduleAtFixedRate(new TimerTask()
        {

            public void run()
            {

                if (originalLocation < newLocation)
                {
                    ((ModJLayeredPane) FullBoardArrayList.get(temp1)).currentImage.removeGamePiece(playerList.get(tempLocation));
                    ((ModJLayeredPane) FullBoardArrayList.get(temp1)).currentImage.repaint();

                    temp1++;

                    ((ModJLayeredPane) FullBoardArrayList.get(temp1)).currentImage.addGamePiece(playerList.get(tempLocation));
                    ((ModJLayeredPane) FullBoardArrayList.get(temp1)).currentImage.repaint();

                }
                if (originalLocation > newLocation)
                {
                    ((ModJLayeredPane) FullBoardArrayList.get(temp1)).currentImage.removeGamePiece(playerList.get(tempLocation));
                    ((ModJLayeredPane) FullBoardArrayList.get(temp1)).currentImage.repaint();

                    temp1++;

                    if (temp1 == 40)
                    {
                        temp1 = 0;
                        engine.plainAdd(playerList.get(tempLocation), 2000);
                        bank.addHistory(playerList.get(tempLocation).getNum(), "Passed GO. Added 2000 to account.");
                    }

                    ((ModJLayeredPane) FullBoardArrayList.get(temp1)).currentImage.addGamePiece(playerList.get(tempLocation));
                    ((ModJLayeredPane) FullBoardArrayList.get(temp1)).currentImage.repaint();

                }
                if (temp1 == newLocation)
                {
                    ((ModJLayeredPane) FullBoardArrayList.get(temp1)).currentImage.removeGamePiece(playerList.get(tempLocation));
                    ((ModJLayeredPane) FullBoardArrayList.get(temp1)).currentImage.repaint();

                    ((ModJLayeredPane) FullBoardArrayList.get(newLocation)).currentImage.addGamePiece(playerList.get(tempLocation));
                    ((ModJLayeredPane) FullBoardArrayList.get(newLocation)).currentImage.repaint();

                    motionTimer.cancel();
                }
                       /*		click = new AePlayWave("file:audio\\pieceMotion.wav");
                               click.runOnce();*/
            }
        }, MotionDelay, MotionPeriod);

        try
        {
            Thread.sleep((numSpaces + 2) * MotionPeriod);
        }
        catch (InterruptedException ietc)
        {
        }

        p.setCellIndex(newLocation);

        bank.addHistory(p.getNum(), "Advanced from " + ((ModJLayeredPane) FullBoardArrayList.get(originalLocation)).currentImage.getName() + " at " + originalLocation
                + " to " + ((ModJLayeredPane) FullBoardArrayList.get(newLocation)).currentImage.getName() + " at " + newLocation + ", Die roll of " + numSpaces + ".");


        if ((newLocation == 2) || (newLocation == 17) || (newLocation == 33))
        {
            flickerCommChest((playerList.get((PLAYERNUM) % NUMPLAYERCOUNT)));
        }
        else if ((newLocation == 7) || (newLocation == 22) || (newLocation == 36))
        {
            flickerChance((playerList.get((PLAYERNUM) % NUMPLAYERCOUNT)));
        }
        else
        {
            popup.provokeCorrectCompiler(p, ((ModJLabel) (FullBoardArrayList.get(newLocation)).currentImage).getName(), newLocation, frameBoard, (PLAYERNUM) % NUMPLAYERCOUNT);
        }
    }

    public void movePlain(Player p, int numSpaces)
    {
        originalLocation = p.getCellIndex();

        newLocation = (originalLocation + numSpaces) % 40;

        ((ModJLayeredPane) FullBoardArrayList.get(originalLocation)).currentImage.removeGamePiece(p);
        ((ModJLayeredPane) FullBoardArrayList.get(originalLocation)).currentImage.repaint();

        ((ModJLayeredPane) FullBoardArrayList.get(newLocation)).currentImage.addGamePiece(p);
        ((ModJLayeredPane) FullBoardArrayList.get(newLocation)).currentImage.repaint();

        p.setCellIndex(newLocation);
			
		/*	click = new AePlayWave("file:audio\\pieceMotion.wav");
       						click.runOnce();*/

        bank.addHistory(p.getNum(), "Advanced from " + ((ModJLayeredPane) FullBoardArrayList.get(originalLocation)).currentImage.getName() + " at " + originalLocation
                + " to " + ((ModJLayeredPane) FullBoardArrayList.get(newLocation)).currentImage.getName() + " at " + newLocation + ", Die roll of " + numSpaces + ".");
    }

    public void startLocation(Player p)
    {
        originalLocation = p.getCellIndex();

        ((ModJLayeredPane) FullBoardArrayList.get(originalLocation)).currentImage.addGamePiece(p);
        ((ModJLayeredPane) FullBoardArrayList.get(originalLocation)).currentImage.repaint();

    }

    public void readPlayerNamesUIFile()
    {
        int commaTemp;
        int stringLength;
        String playerName;
        String playerIcon;
        String fullLine;
        try
        {
            kb = new Scanner(new File("data_player.xml"));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "File Reader Error (Source: GameBoardUI.java [readPlayerNamesUIFile()])", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        while (kb.hasNext())
        {
            fullLine = kb.nextLine();
            stringLength = fullLine.length();
            commaTemp = fullLine.indexOf(",");
            playerName = fullLine.substring(0, commaTemp);
            playerIcon = fullLine.substring(commaTemp + 1, stringLength);
            playerList.add(new Player(playerName, playerIcon));
        }

        kb.close();

        NUMPLAYERCOUNT = playerList.size();
        popup = new PopUpUI(playerList, boxProp, engine, bank, this, trade);
        cardsL.setPopUpAndImages(popup, chanceNormal, chanceYellow, CommChestNormal, CommChestYellow);

        /**********************/
        PlayerPanel1.setOpaque(false);
        PlayerPanel2.setOpaque(false);
        PlayerPanel3.setOpaque(false);
        PlayerPanel4.setOpaque(false);
        PlayerPanel5.setOpaque(false);
        CombinedPlayersPanel.setOpaque(false);
        /**********************/

        switch (NUMPLAYERCOUNT)
        {

            case 1:
                PlayerIconLabel1.setIcon(playerList.get(0).getGamePiece().getImageIcon());
                CombinedPlayersPanel.add(PlayerPanel1);
                break;
            case 2:
                PlayerIconLabel1.setIcon(playerList.get(0).getGamePiece().getImageIcon());
                PlayerIconLabel2.setIcon(playerList.get(1).getGamePiece().getImageIcon());
                CombinedPlayersPanel.add(PlayerPanel1);
                CombinedPlayersPanel.add(PlayerPanel2);
                PlayerPanelList.add(PlayerPanel1);
                PlayerPanelList.add(PlayerPanel2);
                break;
            case 3:
                PlayerIconLabel1.setIcon(playerList.get(0).getGamePiece().getImageIcon());
                PlayerIconLabel2.setIcon(playerList.get(1).getGamePiece().getImageIcon());
                PlayerIconLabel3.setIcon(playerList.get(2).getGamePiece().getImageIcon());
                CombinedPlayersPanel.add(PlayerPanel1);
                CombinedPlayersPanel.add(PlayerPanel2);
                CombinedPlayersPanel.add(PlayerPanel3);
                PlayerPanelList.add(PlayerPanel1);
                PlayerPanelList.add(PlayerPanel2);
                PlayerPanelList.add(PlayerPanel3);
                break;
            case 4:
                PlayerIconLabel1.setIcon(playerList.get(0).getGamePiece().getImageIcon());
                PlayerIconLabel2.setIcon(playerList.get(1).getGamePiece().getImageIcon());
                PlayerIconLabel3.setIcon(playerList.get(2).getGamePiece().getImageIcon());
                PlayerIconLabel4.setIcon(playerList.get(3).getGamePiece().getImageIcon());
                CombinedPlayersPanel.add(PlayerPanel1);
                CombinedPlayersPanel.add(PlayerPanel2);
                CombinedPlayersPanel.add(PlayerPanel3);
                CombinedPlayersPanel.add(PlayerPanel4);
                PlayerPanelList.add(PlayerPanel1);
                PlayerPanelList.add(PlayerPanel2);
                PlayerPanelList.add(PlayerPanel3);
                PlayerPanelList.add(PlayerPanel4);
                break;
            case 5:
                PlayerIconLabel1.setIcon(playerList.get(0).getGamePiece().getImageIcon());
                PlayerIconLabel2.setIcon(playerList.get(1).getGamePiece().getImageIcon());
                PlayerIconLabel3.setIcon(playerList.get(2).getGamePiece().getImageIcon());
                PlayerIconLabel4.setIcon(playerList.get(3).getGamePiece().getImageIcon());
                PlayerIconLabel5.setIcon(playerList.get(4).getGamePiece().getImageIcon());
                CombinedPlayersPanel.add(PlayerPanel1);
                CombinedPlayersPanel.add(PlayerPanel2);
                CombinedPlayersPanel.add(PlayerPanel3);
                CombinedPlayersPanel.add(PlayerPanel4);
                CombinedPlayersPanel.add(PlayerPanel5);
                PlayerPanelList.add(PlayerPanel1);
                PlayerPanelList.add(PlayerPanel2);
                PlayerPanelList.add(PlayerPanel3);
                PlayerPanelList.add(PlayerPanel4);
                PlayerPanelList.add(PlayerPanel5);
                break;
        }

        CombinedPlayersPanel.validate();
        CombinedPlayersPanel.repaint();

        engine.setPlayerList(playerList);

    }

    public void SetUpPlayerPanel()
    {
        //ADD PROPERTY LIST INFO AS WELL
        //EXTEND JLABEL AGAIN FOR PROPERTIES

        for (int x = 0; x < playerList.size(); x++)
        {

            PlayerNameList[x].setText(playerList.get(x).getName());
            PlayerNameList[x].setHorizontalTextPosition(JLabel.CENTER);
            PlayerNameList[x].setFont(new Font("Liberation Sans", Font.BOLD, 15));
            PlayerNameList[x].setForeground(Color.WHITE);
            PlayerNameList[x].validate();
            PlayerNameList[x].repaint();

            try
            {
                PlayerMoneyList[x].setText(Integer.toString(playerList.get(x).getAccount()));
            }
            catch (ArithmeticException arth2)
            {
                JOptionPane.showMessageDialog(null, "Arithmetic Error (Source: GameBoardUI.java [setMoney()])\nChoose Player and Corresponding Icons", "Warning", JOptionPane.WARNING_MESSAGE);
            }
            PlayerMoneyList[x].setHorizontalTextPosition(JLabel.CENTER);
            PlayerMoneyList[x].setFont(new Font("KabaleMedium", Font.PLAIN, 14));
            PlayerMoneyList[x].setForeground(new Color(119, 182, 101));
            PlayerMoneyList[x].validate();
            PlayerMoneyList[x].repaint();

        }
    }

    public void flickerCommChest(Player p)
    {
        int COMDelay = 0;
        int COMPeriod = 300;
        int two = 2;

        cardsL.setEnabled(two);
        cardsL.setCurrentPlayer(p);

        cardFlicker = new AePlayWave("file:audio\\cardFlicker.wav");
        cardFlicker.runOnce();

        COMTimer = new Timer();
        COMTimer.scheduleAtFixedRate(new TimerTask()
        {

            boolean COMstateChange = true; //true means blue
            int COMcount = 0;

            public void run()
            {
                if (COMcount < 8)
                {
                    if (COMstateChange)
                    {
                        CommChest.setIcon(CommChestYellow);
                        CommChest.repaint();
                        COMstateChange = false;
                        COMcount++;
                    }
                    else
                    {
                        CommChest.setIcon(CommChestNormal);
                        CommChest.repaint();
                        COMstateChange = true;
                        COMcount++;
                    }
                }
                else
                {
                    COMTimer.cancel();
                }
            }

        }, COMDelay, COMPeriod);
    }

    public void flickerChance(Player p)
    {
        int CHANCEDelay = 0;
        int CHANCEPeriod = 300;

        int one = 1;

        cardsL.setEnabled(one);
        cardsL.setCurrentPlayer(p);

        cardFlicker = new AePlayWave("file:audio\\cardFlicker.wav");
        cardFlicker.runOnce();

        CHANCETimer = new Timer();
        CHANCETimer.scheduleAtFixedRate(new TimerTask()
        {

            boolean CHANCEstateChange = true; //true means blue
            int CHANCEcount = 0;

            public void run()
            {
                if (CHANCEcount < 8)
                {
                    if (CHANCEstateChange)
                    {
                        Chance.setIcon(chanceYellow);
                        Chance.repaint();
                        CHANCEstateChange = false;
                        CHANCEcount++;
                    }
                    else
                    {
                        Chance.setIcon(chanceNormal);
                        Chance.repaint();
                        CHANCEstateChange = true;
                        CHANCEcount++;
                    }
                }
                else
                {
                    CHANCETimer.cancel();
                }
            }

        }, CHANCEDelay, CHANCEPeriod);
    }

    public void moveStarTo(int a)
    {
        for (int x = 0; x < PlayerPanelList.size(); x++)
        {
            (PlayerPanelList.get(x)).remove(StarOnCurrentPlayer);
            (PlayerPanelList.get(x)).repaint();
        }

        (PlayerPanelList.get(a)).add(StarOnCurrentPlayer);
        (PlayerPanelList.get(a)).repaint();

        System.out.println("Move Star to: " + a);
    }

    @SuppressWarnings("deprecation")
    public void exitMe()
    {
        bank.printList();
        this.stop();
    }

    public void showToolTip()
    {
        SwingUtilities.invokeLater(new Runnable()
        {

            public void run()
            {
                DeedLabel.repaint();

                PopUpImagePane.validate();
                PopUpImagePane.repaint();

                PopUpPanelMain.validate();
                PopUpPanelMain.repaint();

                PopUpDeedFrame.validate();
                PopUpDeedFrame.repaint();

                PopUpDeedFrame.pack();
                PopUpDeedFrame.setAlwaysOnTop(true);
                PopUpDeedFrame.setLocation(MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y);
    		    		
    /*		PopUpDeedFrame.setVisible(true);

    		PopUpPanelMain.reCapture();
    		PopUpPanelMain.reCapture(); 			
    		PopUpPanelMain.reCapture();
    		PopUpPanelMain.reCapture();*/

            }
        });

    }

    @Override
    public void run()
    {
        cardPanel.setLayout(null);

        Chance.setBounds(19, 12, 208, 218);
        CommChest.setBounds(478, 474, 208, 218);

        Chance.setName("Chance");
        CommChest.setName("CommChest");

        cardsL.disableAll();

        cardPanel.add(Chance);
        cardPanel.add(CommChest);

        Chance.addMouseListener(cardsL);
        CommChest.addMouseListener(cardsL);

        StarOnCurrentPlayer.setBounds(180, 45, 48, 48);

        /***********************************************************************/
        PlayerPanel1.setLayout(null);
        player1Jail.setBounds(142, 12, 120, 106);
        player1Jail.setVisible(false);
        PlayerGlassNameLabel1.setBounds(87, 22, 145, 25);
        PlayerIconLabel1.setBounds(21, 19, 60, 60);
        PlayerGlassMoneyLabel1.setBounds(87, 43, 145, 25);
        PlayerGlassPropertyBoxesLabel1.setBounds(78, 65, 120, 40);
        PlayerPanel1.add(player1Jail);
        PlayerPanel1.add(StarOnCurrentPlayer);
        PlayerPanel1.add(PlayerGlassNameLabel1);
        PlayerPanel1.add(PlayerIconLabel1);
        PlayerPanel1.add(PlayerGlassMoneyLabel1);
        PlayerPanel1.add(PlayerGlassPropertyBoxesLabel1);
        /*************************************************************/
        PlayerPanel2.setLayout(null);
        player2Jail.setBounds(142, 12, 120, 106);
        player2Jail.setVisible(false);
        PlayerGlassNameLabel2.setBounds(87, 22, 145, 25);
        PlayerIconLabel2.setBounds(21, 19, 60, 60);
        PlayerGlassMoneyLabel2.setBounds(87, 43, 145, 25);
        PlayerGlassPropertyBoxesLabel2.setBounds(78, 65, 120, 40);
        PlayerPanel1.add(player2Jail);
        PlayerPanel2.add(PlayerGlassNameLabel2);
        PlayerPanel2.add(PlayerIconLabel2);
        PlayerPanel2.add(PlayerGlassMoneyLabel2);
        PlayerPanel2.add(PlayerGlassPropertyBoxesLabel2);
        /***********************************************************************/
        PlayerPanel3.setLayout(null);
        player3Jail.setBounds(142, 12, 120, 106);
        player3Jail.setVisible(false);
        PlayerGlassNameLabel3.setBounds(87, 22, 145, 25);
        PlayerIconLabel3.setBounds(21, 19, 60, 60);
        PlayerGlassMoneyLabel3.setBounds(87, 43, 145, 25);
        PlayerGlassPropertyBoxesLabel3.setBounds(78, 65, 120, 40);
        PlayerPanel1.add(player3Jail);
        PlayerPanel3.add(PlayerIconLabel3);
        PlayerPanel3.add(PlayerGlassNameLabel3);
        PlayerPanel3.add(PlayerGlassMoneyLabel3);
        PlayerPanel3.add(PlayerGlassPropertyBoxesLabel3);
        /***********************************************************************/
        PlayerPanel4.setLayout(null);
        player4Jail.setBounds(142, 12, 120, 106);
        player4Jail.setVisible(false);
        PlayerGlassNameLabel4.setBounds(87, 22, 145, 25);
        PlayerIconLabel4.setBounds(21, 19, 60, 60);
        PlayerGlassMoneyLabel4.setBounds(87, 43, 145, 25);
        PlayerGlassPropertyBoxesLabel4.setBounds(78, 65, 120, 40);
        PlayerPanel1.add(player4Jail);
        PlayerPanel4.add(PlayerIconLabel4);
        PlayerPanel4.add(PlayerGlassNameLabel4);
        PlayerPanel4.add(PlayerGlassMoneyLabel4);
        PlayerPanel4.add(PlayerGlassPropertyBoxesLabel4);
        /***********************************************************************/

        engine = new MKEngine();
        engine.setLabeltoUpdate(PlayerPropList, PlayerMoneyList);
        engine.setGameBoardUI(this);

        //Panel Customizations
        eastAreaPanel.setLayout(new BoxLayout(eastAreaPanel, BoxLayout.Y_AXIS));
        westAreaPanel.setLayout(new BoxLayout(westAreaPanel, BoxLayout.Y_AXIS));
        CombinedPlayersPanel.setLayout(new BoxLayout(CombinedPlayersPanel, BoxLayout.Y_AXIS));

        //ArrayLists
        NorthEdgeArrayList = properties.getEdge("north");
        EastEdgeArrayList = properties.getEdge("east");
        WestEdgeArrayList = properties.getEdge("west");
        SouthEdgeArrayList = properties.getEdge("south");

        //Add MouseListeners and add to panels
        for (int x = SouthEdgeArrayList.size() - 1; x >= 0; x--)
        {
            //SouthEdgeArrayList.get(x).addMouseListener(this);
            FullBoardArrayList.add(new ModJLayeredPane((ModJLabel) SouthEdgeArrayList.get(x), engine));
        }

        for (int x = 0; x < WestEdgeArrayList.size(); x++)
        {
            //WestEdgeArrayList.get(x).addMouseListener(this);
        }

        for (int x = WestEdgeArrayList.size() - 1; x >= 0; x--)
        {
            FullBoardArrayList.add(new ModJLayeredPane((ModJLabel) WestEdgeArrayList.get(x), engine));
        }

        for (int x = 0; x < NorthEdgeArrayList.size(); x++)
        {
            //NorthEdgeArrayList.get(x).addMouseListener(this);
            FullBoardArrayList.add(new ModJLayeredPane((ModJLabel) NorthEdgeArrayList.get(x), engine));
        }

        for (int x = 0; x < EastEdgeArrayList.size(); x++)
        {
            // EastEdgeArrayList.get(x).addMouseListener(this);
        }
        for (int x = 0; x < EastEdgeArrayList.size(); x++)
        {
            FullBoardArrayList.add(new ModJLayeredPane((ModJLabel) EastEdgeArrayList.get(x), engine));
        }

        FullBoardArrayList.remove(11);
        FullBoardArrayList.remove(20);
        FullBoardArrayList.remove(20);
        FullBoardArrayList.remove(31);

        for (int y = 0; y < FullBoardArrayList.size(); y++)
        {
            System.out.println(FullBoardArrayList.get(y).getName());
        }

        for (int y = 10; y >= 0; y--)
        {
            southEdge.add(FullBoardArrayList.get(y));
        }
        for (int y = 19; y >= 11; y--)
        {
            westEdge.add(FullBoardArrayList.get(y));
        }
        for (int y = 20; y < 31; y++)
        {
            northEdge.add(FullBoardArrayList.get(y));
        }
        for (int y = 31; y < 40; y++)
        {
            eastEdge.add(FullBoardArrayList.get(y));
        }

        gameBoard.add(cardPanel);           //////////////////////////////////CARD PANEL ADDED!!!!!!!!!!!!!!!!!!!!!!!

        //Buttons
        RollButton = new JLabel(new ImageIcon(("images\\button\\ROLL.png")));
        RollButton.addMouseListener(new MouseListener()
        {

            public void mouseClicked(MouseEvent e)
            {
            }

            public void mouseExited(MouseEvent e)
            {

                ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\ROLL.png")));
                ((JLabel) e.getSource()).repaint();
            }

            public void mousePressed(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\ROLL_click.png")));
                ((JLabel) e.getSource()).repaint();

            }

            public void mouseReleased(MouseEvent e)
            {

                ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\ROLL.png")));
                ((JLabel) e.getSource()).repaint();

                int delay = 0;
                int period = 50;

                timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask()
                {

                    public int x = 0;

                    public void run()
                    {
                        if (x == 15)
                        {
                            try
                            {
                                move((playerList.get((PLAYERNUM) % NUMPLAYERCOUNT)), dice.getRollTotal());
                            }
                            catch (ArithmeticException arth)
                            {
                                JOptionPane.showMessageDialog(null, "Arithmetic Error (Source: GameBoardUI.java [rollButton.addActionListener(new ActionListener()])\nChoose Player and Corresponding Icons", "Warning", JOptionPane.WARNING_MESSAGE);
                            }

                            timer.cancel();
                            PLAYERNUM++;//Next Player
                        }
                        else
                        {
                            eastAreaPanel.remove(dicePanel);
                            dice.RollWithValue();
                            eastAreaPanel.add(dicePanel);
                            eastAreaPanel.validate();
                            eastAreaPanel.repaint();
                            x++;
                        }
                    }
                }, delay, period);
            }

            public void mouseEntered(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\ROLL_over.png")));
                ((JLabel) e.getSource()).repaint();
            }
        });

        JLabel bankButton = new JLabel(new ImageIcon("images\\button\\STATS.png"));

        bankButton.addMouseListener(new MouseListener()
        {

            public void mouseClicked(MouseEvent e)
            {

            }

            public void mouseExited(MouseEvent e)
            {

                ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\STATS.png")));
                ((JLabel) e.getSource()).repaint();
            }

            public void mousePressed(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\STATS_click.png")));
                ((JLabel) e.getSource()).repaint();
            }

            public void mouseReleased(MouseEvent e)
            {

                ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\STATS.png")));
                ((JLabel) e.getSource()).repaint();

                gameBoard.setVisible(false);
                bank.getGUI().setVisible(true);
                trade.getGUI().setVisible(false);

            }

            public void mouseEntered(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\STATS_over.png")));
                ((JLabel) e.getSource()).repaint();
            }
        });

        JLabel returnLabel = new JLabel(new ImageIcon("images\\button\\RETURN.png"));

        returnLabel.addMouseListener(new MouseListener()
        {

            public void mouseClicked(MouseEvent e)
            {

            }

            public void mouseExited(MouseEvent e)
            {

                ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\RETURN.png")));
                ((JLabel) e.getSource()).repaint();
            }

            public void mousePressed(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\RETURN_click.png")));
                ((JLabel) e.getSource()).repaint();
            }

            public void mouseReleased(MouseEvent e)
            {

                ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\RETURN.png")));
                ((JLabel) e.getSource()).repaint();

                splash.launcher.hideGameBoard();
                splash.launcher.menuBoard.setVisible(true);
                exitMe();

            }

            public void mouseEntered(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\RETURN_over.png")));
                ((JLabel) e.getSource()).repaint();
            }
        });

        JLabel gameboardLabel = new JLabel(new ImageIcon("images\\paper\\GAMEBOARD.png"));

        gameboardLabel.addMouseListener(new MouseListener()
        {

            public void mouseClicked(MouseEvent e)
            {
            }

            public void mouseEntered(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\paper\\GAMEBOARD_over.png")));
                ((JLabel) e.getSource()).repaint();
            }

            public void mouseExited(MouseEvent e)
            {

                ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\paper\\GAMEBOARD.png")));
                ((JLabel) e.getSource()).repaint();
            }

            public void mousePressed(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\paper\\GAMEBOARD_click.png")));
                ((JLabel) e.getSource()).repaint();

                gameBoard.setVisible(true);
                bank.getGUI().setVisible(false);
                trade.getGUI().setVisible(false);
            }

            public void mouseReleased(MouseEvent e)
            {

                ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\paper\\GAMEBOARD.png")));
                ((JLabel) e.getSource()).repaint();

            }
        });

        JLabel statsLabel = new JLabel(new ImageIcon("images\\paper\\STATISTICS.png"));

        statsLabel.addMouseListener(new MouseListener()
        {

            public void mouseClicked(MouseEvent e)
            {
            }

            public void mouseEntered(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\paper\\STATISTICS_over.png")));
                ((JLabel) e.getSource()).repaint();
            }

            public void mouseExited(MouseEvent e)
            {

                ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\paper\\STATISTICS.png")));
                ((JLabel) e.getSource()).repaint();
            }

            public void mousePressed(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\paper\\STATISTICS_click.png")));
                ((JLabel) e.getSource()).repaint();

                gameBoard.setVisible(false);
                bank.getGUI().setVisible(true);
                trade.getGUI().setVisible(false);
            }

            public void mouseReleased(MouseEvent e)
            {

                ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\paper\\STATISTICS.png")));
                ((JLabel) e.getSource()).repaint();

            }
        });

        JLabel tradeLabel = new JLabel(new ImageIcon("images\\paper\\TRADING.png"));

        tradeLabel.addMouseListener(new MouseListener()
        {

            public void mouseClicked(MouseEvent e)
            {
            }

            public void mouseEntered(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\paper\\TRADING_over.png")));
                ((JLabel) e.getSource()).repaint();
            }

            public void mouseExited(MouseEvent e)
            {

                ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\paper\\TRADING.png")));
                ((JLabel) e.getSource()).repaint();
            }

            public void mousePressed(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\paper\\TRADING_click.png")));
                ((JLabel) e.getSource()).repaint();

                gameBoard.setVisible(false);
                bank.getGUI().setVisible(false);
                trade.getGUI().setVisible(true);
            }

            public void mouseReleased(MouseEvent e)
            {

                ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\paper\\TRADING.png")));
                ((JLabel) e.getSource()).repaint();

            }
        });

        gameboardLabel.setBounds(0, 0, 140, 25);
        statsLabel.setBounds(150, 0, 110, 25);
        tradeLabel.setBounds(270, 0, 90, 25);

        //Pieces attached
        gameBoard.add(northEdge, BorderLayout.NORTH);
        gameBoard.add(southEdge, BorderLayout.SOUTH);
        gameBoard.add(eastEdge, BorderLayout.EAST);
        gameBoard.add(westEdge, BorderLayout.WEST);

        //Die Panel
        dicePanel = (JPanel) dice.getDefault();

        //AlignmentX
        RollButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        bankButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        returnLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        eastAreaPanel.setOpaque(false);
        combinedPanel.setOpaque(false);
        westEdge.setOpaque(false);
        eastEdge.setOpaque(false);
        northEdge.setOpaque(false);
        southEdge.setOpaque(false);
        gameBoard.setOpaque(false);
        gameBoardHolder.setOpaque(false);
        paperShell.setOpaque(false);
        transBack.setOpaque(false);
        buttonHolder.setOpaque(false);

        eastAreaPanel.setBorder(null);
        combinedPanel.setBorder(null);
        westEdge.setBorder(null);
        eastEdge.setBorder(null);
        northEdge.setBorder(null);
        southEdge.setBorder(null);
        gameBoard.setBorder(null);
        gameBoardHolder.setBorder(null);
        buttonHolder.setBorder(null);

        //Full Panel Assembly
        eastAreaPanel.add(CombinedPlayersPanel);
        eastAreaPanel.add(RollButton);
        eastAreaPanel.add(bankButton);
        eastAreaPanel.add(returnLabel);
        eastAreaPanel.add(dicePanel);
        combinedPanel.setLayout(new BorderLayout(6, 6));

        buttonHolder.add(gameboardLabel);
        buttonHolder.add(statsLabel);
        buttonHolder.add(tradeLabel);

        buttonHolder.setPreferredSize(new Dimension(360, 25));
        gameBoard.setPreferredSize(new Dimension(920, 935));
        bank.getGUI().setPreferredSize(new Dimension(920, 920));
        trade.getGUI().setPreferredSize(new Dimension(920, 920));

        buttonHolder.setVisible(true);
        gameBoard.setVisible(true);
        bank.getGUI().setVisible(false);
        trade.getGUI().setVisible(false);

        buttonHolder.setBounds(285, 1, 360, 25);
        gameBoard.setBounds(5, 35, 920, 935);
        bank.getGUI().setBounds(10, 35, 920, 920);
        trade.getGUI().setBounds(0, 35, 920, 920);

        gameBoardHolder.setLayout(null);

        gameBoardHolder.add(buttonHolder, new Integer(0));
        gameBoardHolder.add(gameBoard, new Integer(1));
        gameBoardHolder.add(bank.getGUI(), new Integer(2));
        gameBoardHolder.add(trade.getGUI(), new Integer(3));

        combinedPanel.add(gameBoardHolder, BorderLayout.CENTER);
        combinedPanel.add(eastAreaPanel, BorderLayout.EAST);
        combinedPanel.setBounds(15, 28, 1221, 945);
        paperShell.add(combinedPanel);
        transBack.add(paperShell);

        readPlayerNamesUIFile();

        for (int x = 0; x < playerList.size(); x++)
        {
            this.startLocation(playerList.get(x));
            playerList.get(x).setNum(x);
            SetUpPlayerPanel();
        }

        copyPlayerIconLabel1 = new JLabel(PlayerIconLabel1.getIcon());
        copyPlayerIconLabel2 = new JLabel(PlayerIconLabel2.getIcon());
        copyPlayerIconLabel3 = new JLabel(PlayerIconLabel3.getIcon());
        copyPlayerIconLabel4 = new JLabel(PlayerIconLabel4.getIcon());
        copyPlayerIconLabel5 = new JLabel(PlayerIconLabel5.getIcon());

        copyPlayerGlassNameLabel1 = new JLabel(PlayerGlassNameLabel1.getText());
        copyPlayerGlassNameLabel2 = new JLabel(PlayerGlassNameLabel2.getText());
        copyPlayerGlassNameLabel3 = new JLabel(PlayerGlassNameLabel3.getText());
        copyPlayerGlassNameLabel4 = new JLabel(PlayerGlassNameLabel4.getText());
        copyPlayerGlassNameLabel5 = new JLabel(PlayerGlassNameLabel5.getText());

        copyPlayerGlassNameLabel1.setFont(new Font("Liberation Sans", Font.BOLD, 15));
        copyPlayerGlassNameLabel1.setForeground(new Color(147, 207, 218));

        copyPlayerGlassNameLabel2.setFont(new Font("Liberation Sans", Font.BOLD, 15));
        copyPlayerGlassNameLabel2.setForeground(new Color(147, 207, 218));

        copyPlayerGlassNameLabel3.setFont(new Font("Liberation Sans", Font.BOLD, 15));
        copyPlayerGlassNameLabel3.setForeground(new Color(147, 207, 218));

        copyPlayerGlassNameLabel4.setFont(new Font("Liberation Sans", Font.BOLD, 15));
        copyPlayerGlassNameLabel4.setForeground(new Color(147, 207, 218));

        copyPlayerGlassNameLabel5.setFont(new Font("Liberation Sans", Font.BOLD, 15));
        copyPlayerGlassNameLabel5.setForeground(new Color(147, 207, 218));

        copyPlayerIconList[0] = copyPlayerIconLabel1;
        copyPlayerIconList[1] = copyPlayerIconLabel2;
        copyPlayerIconList[2] = copyPlayerIconLabel3;
        copyPlayerIconList[3] = copyPlayerIconLabel4;
        copyPlayerIconList[4] = copyPlayerIconLabel5;

        copyPlayerNameList[0] = copyPlayerGlassNameLabel1;
        copyPlayerNameList[1] = copyPlayerGlassNameLabel2;
        copyPlayerNameList[2] = copyPlayerGlassNameLabel3;
        copyPlayerNameList[3] = copyPlayerGlassNameLabel4;
        copyPlayerNameList[4] = copyPlayerGlassNameLabel5;

        bank.updatePlayers(NUMPLAYERCOUNT, copyPlayerIconList, copyPlayerNameList);

    }

    public void showPopUp(JLayeredPane a, int x)
    {
        popUpPane = a;

        //1= north
        //2= south
        //3= east
        //0= west

        if (x == 1)
        {
            popUpPane.setBounds(((cardPanel.getWidth() - 500) / 2), (((cardPanel.getHeight() - 500)) + 500 / 2) - 500, 500, 500);
        }
        if (x == 2)
        {
            popUpPane.setBounds(((cardPanel.getWidth() - 500) / 2), (((cardPanel.getHeight() - 500)) + 500 / 2) + 500, 500, 500);
        }
        if (x == 3)
        {
            popUpPane.setBounds(((cardPanel.getWidth() - 500) / 2) + 500, ((cardPanel.getHeight() - 500)) + 500 / 2, 500, 500);
        }
        if (x == 0)
        {
            popUpPane.setBounds(((cardPanel.getWidth() - 500) / 2) - 500, ((cardPanel.getHeight() - 500)) + 500 / 2, 500, 500);
        }

        cardPanel.add(popUpPane, new Integer(5));

    }

    public void hidePopUp()
    {
        cardPanel.remove(popUpPane);

        popUpPane.repaint();
        cardPanel.repaint();
    }
}