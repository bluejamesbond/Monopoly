package src.com.monopoly; /**
 * @(#)PopUpUI.java
 *
 *
 * @author
 * @version 1.00 2010/2/25
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class PopUpUI
{

    public BoxPoperties PopUpProps;

    public ImageIcon BUY = new ImageIcon("images\\button\\BUY.png");
    public ImageIcon PASS = new ImageIcon("images\\button\\PASS.png");
    public ImageIcon CLOSE = new ImageIcon("images\\button\\CLOSE.png");

    public ImageIcon BUYover = new ImageIcon("images\\button\\BUY_over.png");
    public ImageIcon PASSover = new ImageIcon("images\\button\\PASS_over.png");
    public ImageIcon CLOSEover = new ImageIcon("images\\button\\CLOSE_over.png");

    public ImageIcon BUYclick = new ImageIcon("images\\button\\BUY_click.png");
    public ImageIcon PASSclick = new ImageIcon("images\\button\\PASS_click.png");
    public ImageIcon CLOSEclick = new ImageIcon("images\\button\\CLOSE_click.png");

    public JLabel BuyLabel = new JLabel(BUY);
    public JLabel PassLabel = new JLabel(PASS);
    public JLabel CloseLabel = new JLabel(CLOSE);
    public JLabel ExitLabel = new JLabel(CLOSE);
    public JLabel HideLabel = new JLabel(CLOSE);

    public JFrame frameBoard;

    public JLabel currentPopImageWithChoice = new JLabel(PASS);
    public JLabel currentPopImageWithoutChoice = new JLabel(PASS);
    public JLabel currentPopImageBought = new JLabel(PASS);
    public JLabel currentPopImageCard = new JLabel(PASS);

    public JLabel PlayerGlassMoneyLabel = null;
    public JLabel UniversalBackgroundClear = new JLabel(new ImageIcon("images\\popup\\pop_back_clear.png"));

    public JLayeredPane PopPanelMainWithChoices = new JLayeredPane();
    public JLayeredPane PopPanelMainWithoutChoices = new JLayeredPane();
    public JLayeredPane PopPanelMainBought = new JLayeredPane();
    public JLayeredPane PopPanelMainCard = new JLayeredPane();

    public MouseListenerClassPopPanelMainWithChoices PopPanelMainWithChoicesMotion = new MouseListenerClassPopPanelMainWithChoices();
    public MouseListenerClassPopPanelMainWithoutChoices PopPanelMainWithoutChoicesMotion = new MouseListenerClassPopPanelMainWithoutChoices();
    public MouseListenerClassPopPanelMainCard PopPanelMainCardMotion = new MouseListenerClassPopPanelMainCard();

    public Player currentPlayer;

    public PlayerTurnInfoUI playerCountFrame = null;

    public int currentPLAYERNUM = 0;
    public int currentPosition;

    public JLabel[] currentPlayerMoneyList = null;

    public Timer timer;

    public String tempAmount;
    public String PieceName;

    public PropJLabel[] currentPlayerPropList = null;

    public ArrayList<Player> playerList = null;

    public ArrayList<JLabel> chanceList = null;
    public ArrayList<JLabel> commList = null;

    public int startAmount = 0;
    public int endAmount = 0;

    public int rand = 3145;

    public ChanceCards chanceCard = new ChanceCards();
    public CommChestCards commCard = new CommChestCards();

    public GameBoardUI gameUI;
    public TradeUI trade;
    public MKEngine currentEngine;
    public BankUI bank;

    public Timer exit;
    public Timer enter;

    public JLayeredPane tempPointer;

    int directionEnter = 1;
    int directionExit = 2;

    public PopUpUI(ArrayList<Player> a, BoxPoperties b, MKEngine e, BankUI asd, GameBoardUI g, TradeUI t)
    {
        PopUpProps = b;
        PopUpProps.fillArraysDeeds();
        PopUpProps.fillArraysExtras();

        gameUI = g;
        trade = t;
        playerCountFrame = new PlayerTurnInfoUI(a);
        playerList = a;
        currentEngine = e;
        bank = asd;

        setUP();

        chanceList = chanceCard.getCardList();
        commList = commCard.getCardList();

        bank.setEnlargedImages(PopUpProps, chanceCard, commCard);

    }

    public void setUP()
    {
        /**********************************************************WITH CHOICES*/
        PopPanelMainWithChoices.setLayout(new BorderLayout());

        BuyLabel.addMouseListener(new MouseListener()
        {

            public void mouseClicked(MouseEvent e)
            {

            }

            public void mouseExited(MouseEvent e)
            {

                ((JLabel) e.getSource()).setIcon(BUY);
                ((JLabel) e.getSource()).repaint();
            }

            public void mousePressed(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(BUYclick);
                ((JLabel) e.getSource()).repaint();
            }

            public void mouseReleased(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(BUY);
                ((JLabel) e.getSource()).repaint();

                exitAnimation(PopPanelMainWithChoices);
                //	gameUI.hidePopUp();

                bank.addHistory(currentPLAYERNUM, "Bought " + PieceName + " for " + currentEngine.getPrice(currentPosition) + " dollars.");

                gameUI.FullBoardArrayList.get(currentPosition).currentImage.setPlayer(currentPlayer);
                gameUI.FullBoardArrayList.get(currentPosition).setActive(currentPosition);

                bank.resizeImageAndAdd(currentPopImageWithChoice.getIcon(), currentPLAYERNUM, currentPopImageWithChoice.getName(), "Prop");
                trade.combineLayers(currentPopImageWithChoice.getIcon(), currentPlayer, PieceName, currentPosition);

                currentEngine.findAndBehave(currentPlayer, currentPosition, currentPLAYERNUM);

                gameUI.moveStarTo(((gameUI.PLAYERNUM)) % (gameUI.NUMPLAYERCOUNT));

                frameBoard.setEnabled(true);
            }

            public void mouseEntered(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(BUYover);
                ((JLabel) e.getSource()).repaint();
            }
        });

        PassLabel.addMouseListener(new MouseListener()
        {

            public void mouseClicked(MouseEvent e)
            {

            }

            public void mouseExited(MouseEvent e)
            {

                ((JLabel) e.getSource()).setIcon(PASS);
                ((JLabel) e.getSource()).repaint();
            }

            public void mousePressed(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(PASSclick);
                ((JLabel) e.getSource()).repaint();
            }

            public void mouseReleased(MouseEvent e)
            {

                ((JLabel) e.getSource()).setIcon(PASS);
                ((JLabel) e.getSource()).repaint();

                exitAnimation(PopPanelMainWithChoices);
                //	gameUI.hidePopUp();

                gameUI.moveStarTo(((gameUI.PLAYERNUM)) % (gameUI.NUMPLAYERCOUNT));
            }

            public void mouseEntered(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(PASSover);
                ((JLabel) e.getSource()).repaint();
            }
        });

        PopPanelMainWithChoices.add(currentPopImageWithChoice, BorderLayout.PAGE_START);
        PopPanelMainWithChoices.add(BuyLabel, BorderLayout.LINE_START);
        PopPanelMainWithChoices.add(PassLabel, BorderLayout.LINE_END);
        PopPanelMainWithChoices.addMouseMotionListener(PopPanelMainWithChoicesMotion);

        /**********************************************************WITH CHOICES*/

        /**********************************************************WITHOUT CHOICES*/
        PopPanelMainWithoutChoices.setLayout(new BorderLayout());

        CloseLabel.addMouseListener(new MouseListener()
        {

            public void mouseClicked(MouseEvent e)
            {

            }

            public void mouseExited(MouseEvent e)
            {

                ((JLabel) e.getSource()).setIcon(CLOSE);
                ((JLabel) e.getSource()).repaint();
            }

            public void mousePressed(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(CLOSEclick);
                ((JLabel) e.getSource()).repaint();
            }

            public void mouseReleased(MouseEvent e)
            {

                ((JLabel) e.getSource()).setIcon(CLOSE);
                ((JLabel) e.getSource()).repaint();

                exitAnimation(PopPanelMainWithoutChoices);
                //	gameUI.hidePopUp();

                frameBoard.setEnabled(true);
                gameUI.moveStarTo(((gameUI.PLAYERNUM)) % (gameUI.NUMPLAYERCOUNT));
            }

            public void mouseEntered(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(CLOSEover);
                ((JLabel) e.getSource()).repaint();
            }
        });

        PopPanelMainWithoutChoices.add(currentPopImageWithoutChoice, BorderLayout.PAGE_START);
        PopPanelMainWithoutChoices.add(CloseLabel, BorderLayout.CENTER);
        PopPanelMainWithoutChoices.addMouseMotionListener(PopPanelMainWithoutChoicesMotion);

        /**********************************************************WITHOUT CHOICES*/

        /**********************************************************BOUGHT**********/
        PopPanelMainBought.setLayout(new BorderLayout());

        HideLabel.addMouseListener(new MouseListener()
        {

            public void mouseClicked(MouseEvent e)
            {

            }

            public void mouseExited(MouseEvent e)
            {

                ((JLabel) e.getSource()).setIcon(CLOSE);
                ((JLabel) e.getSource()).repaint();
            }

            public void mousePressed(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(CLOSEclick);
                ((JLabel) e.getSource()).repaint();
            }

            public void mouseReleased(MouseEvent e)
            {

                ((JLabel) e.getSource()).setIcon(CLOSE);
                ((JLabel) e.getSource()).repaint();

                exitAnimation(PopPanelMainBought);
                //		gameUI.hidePopUp();

                currentEngine.findAndBehave(currentPlayer, currentPosition, currentPLAYERNUM);

                frameBoard.setEnabled(true);
                gameUI.moveStarTo(((gameUI.PLAYERNUM)) % (gameUI.NUMPLAYERCOUNT));

            }

            public void mouseEntered(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(CLOSEover);
                ((JLabel) e.getSource()).repaint();
            }
        });

        PopPanelMainBought.add(currentPopImageBought, BorderLayout.PAGE_START);
        PopPanelMainBought.add(HideLabel, BorderLayout.CENTER);
        //  	PopPanelMainBought.addMouseMotionListener(PopPanelMainCardMotion);
        /**********************************************************BOUGHT**********/

        /**********************************************************CARDS**********/
        PopPanelMainCard.setLayout(new BorderLayout());

        ExitLabel.addMouseListener(new MouseListener()
        {

            public void mouseClicked(MouseEvent e)
            {

            }

            public void mouseExited(MouseEvent e)
            {

                ((JLabel) e.getSource()).setIcon(CLOSE);
                ((JLabel) e.getSource()).repaint();
            }

            public void mousePressed(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(CLOSEclick);
                ((JLabel) e.getSource()).repaint();
            }

            public void mouseReleased(MouseEvent e)
            {

                ((JLabel) e.getSource()).setIcon(CLOSE);
                ((JLabel) e.getSource()).repaint();

                exitAnimation(PopPanelMainCard);
                //	gameUI.hidePopUp();

                gameUI.moveStarTo(((gameUI.PLAYERNUM)) % (gameUI.NUMPLAYERCOUNT));

            }

            public void mouseEntered(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(CLOSEover);
                ((JLabel) e.getSource()).repaint();
            }
        });

        PopPanelMainCard.add(currentPopImageCard, BorderLayout.PAGE_START);
        PopPanelMainCard.add(ExitLabel, BorderLayout.CENTER);
        PopPanelMainCard.addMouseMotionListener(PopPanelMainCardMotion);
        /**********************************************************CARDS**********/
    }

    public void provokeCorrectCompiler(Player p, String s, int positionNum, JFrame fullBoard, int PLAYERNUMdividedByNUMCOUNT)
    {
        currentPlayer = p;
        currentPosition = positionNum;
        currentPLAYERNUM = PLAYERNUMdividedByNUMCOUNT;
        PieceName = s;
        frameBoard = fullBoard;
        frameBoard.setEnabled(true);

        if (currentPosition == 4)
        {
            panelCompilerWithoutChoice();
        }
        //	else if(currentPosition == 2){    		panelCompilerWithoutChoice();    				}
        else if (currentPosition == 5)
        {
            panelCompilerWithoutChoice();
        }
        //	else if(currentPosition == 7){    		panelCompilerWithoutChoice();    				}
        else if (currentPosition == 10)
        {
            panelCompilerWithoutChoice();
        }
        else if (currentPosition == 12)
        {
            panelCompilerWithoutChoice();
        }
        else if (currentPosition == 15)
        {
            panelCompilerWithoutChoice();
        }
        //	else if(currentPosition == 17){    		panelCompilerWithoutChoice();    				}
        else if (currentPosition == 20)
        {
            panelCompilerWithoutChoice();
        }
        //	else if(currentPosition == 22){    		panelCompilerWithoutChoice();    				}
        else if (currentPosition == 25)
        {
            panelCompilerWithoutChoice();
        }
        else if (currentPosition == 28)
        {
            panelCompilerWithoutChoice();
        }
        else if (currentPosition == 30)
        {
            bank.addHistory(currentPlayer.getNum(), "Placed in jail.");
        }
        //	else if(currentPosition == 33){    		panelCompilerWithoutChoice();    				}
        else if (currentPosition == 35)
        {
            panelCompilerWithoutChoice();
        }
        //	else if(currentPosition == 36){    		panelCompilerWithoutChoice();    				}
        else if (currentPosition == 38)
        {
            currentEngine.plainSub(currentPlayer, 7500);
            bank.addHistory(currentPlayer.getNum(), "Landed on Luxury Tax: $7500 subtracted");
        }
        else if (currentPosition == 0)
        {
            currentEngine.plainAdd(currentPlayer, 2000);
            bank.addHistory(currentPlayer.getNum(), "Landed on Go: $2000 added");
        }
        else
        {
            panelCompilerWithChoice();
        }
    }

    public void panelCompilerWithChoice()
    {
        try
        {
            System.out.println("Testing: " + currentEngine.getPropertiesBought().get(currentPosition).get(0));
            panelCompilerBoughtChoice();
        }
        catch (IndexOutOfBoundsException ieb)
        {
            System.out.println("Choice A");

            currentPopImageWithChoice.setIcon(((JLabel) PopUpProps.getLabel(PieceName)).getIcon());
            currentPopImageWithChoice.setName(PieceName);
            currentPopImageWithChoice.repaint();

            enterAnimation(PopPanelMainWithChoices);
        }

    }

    public void panelCompilerWithoutChoice()
    {
        currentPopImageWithoutChoice.setIcon(((JLabel) PopUpProps.getLabel(PieceName)).getIcon());
        currentPopImageWithoutChoice.repaint();

        enterAnimation(PopPanelMainWithoutChoices);
    }

    public void panelCompilerBoughtChoice()
    {
        currentPopImageBought.setIcon(((JLabel) PopUpProps.getLabel(PieceName)).getIcon());
        currentPopImageBought.setName(PieceName);
        currentPopImageBought.repaint();

        bank.addHistory(currentPLAYERNUM, "Pays for " + PieceName + " of " + currentEngine.getPropertiesBought().get(currentPosition).get(0).getName());
        bank.addHistory(currentEngine.getPropertiesBought().get(currentPosition).get(0).getNum(), currentPlayer.getName() + " gets rent from " + PieceName + " by " + currentPlayer.getName());

        enterAnimation(PopPanelMainBought);

    }

    public void showChanceCard(Player p)
    {
        currentPlayer = p;

        rand = (int) (Math.random() * 14);

        currentPopImageCard.setIcon(chanceList.get(rand).getIcon());
        currentPopImageCard.setName(chanceList.get(rand).getName());
        currentPopImageCard.repaint();

        currentEngine.chanceCardsAction(currentPlayer, currentPopImageCard.getName());

        bank.resizeImageAndAdd(currentPopImageCard.getIcon(), currentPLAYERNUM, currentPopImageCard.getName(), "Card");

        bank.addHistory(currentPLAYERNUM, "Picks up a " + PieceName + " card");
        bank.addHistory(currentPLAYERNUM, "Chance Card reads: " + currentPopImageCard.getName());

        enterAnimation(PopPanelMainCard);
    }

    public void showCommCard(Player p)
    {
        currentPlayer = p;

        System.out.println("Choice F");

        rand = (int) (Math.random() * 14);

        currentPopImageCard.setIcon(commList.get(rand).getIcon());
        currentPopImageCard.setName(commList.get(rand).getName());
        currentPopImageCard.repaint();

        currentEngine.commCardsAction(currentPlayer, currentPopImageCard.getName());

        bank.resizeImageAndAdd(currentPopImageCard.getIcon(), currentPLAYERNUM, currentPopImageCard.getName(), "Card");

        bank.addHistory(currentPLAYERNUM, "Picks up a " + PieceName + " card");
        bank.addHistory(currentPLAYERNUM, "Community Chest Card reads: " + currentPopImageCard.getName());

        enterAnimation(PopPanelMainCard);
    }

    public void exitAnimation(JLayeredPane a)
    {
        tempPointer = a;

        if (directionExit % 4 == 1)
        {
            exit = new Timer();
            exit.scheduleAtFixedRate(new TimerTask()
            {

                Point t = tempPointer.getLocation();

                int yToReach = -500;
                int currentY = (int) t.getY();

                public void run()
                {
                    tempPointer.setBounds(t.x, currentY, tempPointer.getWidth(), tempPointer.getHeight());

                    if (yToReach == currentY)
                    {
                        gameUI.hidePopUp();
                        exit.cancel();
                    }

                    currentY--;
                }

            }, 0, new Integer(2));

            resetEdge(tempPointer, directionExit % 4);
        }
        if (directionExit % 4 == 2)
        {
            exit = new Timer();
            exit.scheduleAtFixedRate(new TimerTask()
            {

                Point t = tempPointer.getLocation();

                int yToReach = 750;
                int currentY = (int) t.getY();

                public void run()
                {
                    tempPointer.setBounds(t.x, currentY, tempPointer.getWidth(), tempPointer.getHeight());

                    if (yToReach == currentY)
                    {
                        gameUI.hidePopUp();
                        exit.cancel();
                    }

                    currentY++;
                }

            }, 0, new Integer(2));

            resetEdge(tempPointer, directionExit % 4);
        }
        if (directionExit % 4 == 3)
        {
            exit = new Timer();
            exit.scheduleAtFixedRate(new TimerTask()
            {

                Point t = tempPointer.getLocation();

                int xToReach = 750;
                int currentX = (int) t.getX();

                public void run()
                {
                    tempPointer.setBounds(currentX, t.y, tempPointer.getWidth(), tempPointer.getHeight());

                    if (xToReach == currentX)
                    {
                        gameUI.hidePopUp();
                        exit.cancel();
                    }

                    currentX++;
                }

            }, 0, new Integer(2));

            resetEdge(tempPointer, directionExit % 4);
        }
        if (directionExit % 4 == 0)
        {
            exit = new Timer();
            exit.scheduleAtFixedRate(new TimerTask()
            {

                Point t = tempPointer.getLocation();

                int xToReach = -500;
                int currentX = (int) t.getX();

                public void run()
                {
                    tempPointer.setBounds(currentX, t.y, tempPointer.getWidth(), tempPointer.getHeight());

                    if (xToReach == currentX)
                    {
                        gameUI.hidePopUp();
                        exit.cancel();
                    }

                    currentX--;
                }

            }, 0, new Integer(2));

            resetEdge(tempPointer, directionExit % 4);
        }

        directionExit++;
    }

    public void enterAnimation(JLayeredPane a)
    {
        tempPointer = a;

        if (directionEnter % 4 == 1)
        {
            gameUI.showPopUp(tempPointer, directionEnter % 4);

            exit = new Timer();
            exit.scheduleAtFixedRate(new TimerTask()
            {

                int currentY = -500;
                int yToReach = (gameUI.cardPanel.getHeight() - 500) / 2;

                public void run()
                {
                    tempPointer.setBounds((gameUI.cardPanel.getWidth() - 500) / 2, currentY, tempPointer.getWidth(), tempPointer.getHeight());

                    if (yToReach == currentY)
                    {
                        exit.cancel();
                    }

                    currentY++;
                }

            }, 0, new Integer(2));
        }
        if (directionEnter % 4 == 2)
        {
            gameUI.showPopUp(tempPointer, directionEnter % 4);

            exit = new Timer();
            exit.scheduleAtFixedRate(new TimerTask()
            {

                int currentY = 700;
                int yToReach = (gameUI.cardPanel.getHeight() - 500) / 2;

                public void run()
                {
                    tempPointer.setBounds((gameUI.cardPanel.getWidth() - 500) / 2, currentY, tempPointer.getWidth(), tempPointer.getHeight());

                    if (yToReach == currentY)
                    {
                        exit.cancel();
                    }

                    currentY--;
                }

            }, 0, new Integer(2));
        }
        if (directionEnter % 4 == 3)
        {
            gameUI.showPopUp(tempPointer, directionEnter % 4);

            exit = new Timer();
            exit.scheduleAtFixedRate(new TimerTask()
            {

                int currentX = 500;
                int xToReach = (gameUI.cardPanel.getWidth() - 500) / 2;

                public void run()
                {
                    tempPointer.setBounds(currentX, (gameUI.cardPanel.getHeight() - 500) / 2, tempPointer.getWidth(), tempPointer.getHeight());

                    if (xToReach == currentX)
                    {
                        exit.cancel();
                    }

                    currentX--;
                }

            }, 0, new Integer(2));
        }
        if (directionEnter % 4 == 0)
        {
            gameUI.showPopUp(tempPointer, directionEnter % 4);

            exit = new Timer();
            exit.scheduleAtFixedRate(new TimerTask()
            {

                int currentX = -500;
                int xToReach = (gameUI.cardPanel.getWidth() - 500) / 2;

                public void run()
                {
                    tempPointer.setBounds(currentX, (gameUI.cardPanel.getHeight() - 500) / 2, tempPointer.getWidth(), tempPointer.getHeight());

                    if (xToReach == currentX)
                    {
                        exit.cancel();
                    }

                    currentX++;
                }

            }, 0, new Integer(2));
        }
        directionEnter++;
    }

    public void resetEdge(JLayeredPane a, int x)
    {
        //1= north
        //2= south
        //3= east
        //0= west

        if (x == 1)
        {
            a.setBounds(((gameUI.cardPanel.getWidth() - 500) / 2), (((gameUI.cardPanel.getHeight() - 500)) + 500 / 2) - 500, 500, 500);
        }
        if (x == 2)
        {
            a.setBounds(((gameUI.cardPanel.getWidth() - 500) / 2), (((gameUI.cardPanel.getHeight() - 500)) + 500 / 2) + 500, 500, 500);
        }
        if (x == 3)
        {
            a.setBounds(((gameUI.cardPanel.getWidth() - 500) / 2) + 500, ((gameUI.cardPanel.getHeight() - 500)) + 500 / 2, 500, 500);
        }
        if (x == 0)
        {
            a.setBounds(((gameUI.cardPanel.getWidth() - 500) / 2) - 500, ((gameUI.cardPanel.getHeight() - 500)) + 500 / 2, 500, 500);
        }

    }

    class MouseListenerClassPopPanelMainWithChoices extends MouseMotionAdapter
    {
        public void mouseDragged(MouseEvent E)
        {
            Point p = SwingUtilities.convertPoint(PopPanelMainWithChoices, E.getPoint(), gameUI.cardPanel);
            PopPanelMainWithChoices.setBounds(p.x, p.y, PopPanelMainWithChoices.getWidth(), PopPanelMainWithChoices.getHeight());

        }
    }

    class MouseListenerClassPopPanelMainWithoutChoices extends MouseMotionAdapter
    {
        public void mouseDragged(MouseEvent E)
        {
            Point p = SwingUtilities.convertPoint(PopPanelMainWithoutChoices, E.getPoint(), gameUI.cardPanel);
            PopPanelMainWithoutChoices.setBounds(p.x, p.y, PopPanelMainWithoutChoices.getWidth(), PopPanelMainWithoutChoices.getHeight());
        }
    }

    class MouseListenerClassPopPanelMainCard extends MouseMotionAdapter
    {
        public void mouseDragged(MouseEvent E)
        {
            Point p = SwingUtilities.convertPoint(PopPanelMainCard, E.getPoint(), gameUI.cardPanel);
            PopPanelMainCard.setBounds(p.x, p.y, PopPanelMainCard.getWidth(), PopPanelMainCard.getHeight());
        }
    }
}