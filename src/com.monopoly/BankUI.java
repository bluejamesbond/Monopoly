package src.com.monopoly; /**
 * @(#)BankUI.java
 *
 *
 * @Mathew Kurian
 * @version 1.00 2010/2/16
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BankUI implements MouseListener
{

    public ImageIcon box = new ImageIcon("images\\bank\\box.png");
    public ImageIcon background = new ImageIcon("images\\bank\\background.png");
    public ImageIcon playerMenu = new ImageIcon("images\\stats\\menu_stats.png");
    public ImageIcon playerMenuOver = new ImageIcon("images\\stats\\menu_stats - over.png");
    public ImageIcon playerMenuClick = new ImageIcon("images\\stats\\menu_stats - click.png");

    public ImageIcon CardNormal = new ImageIcon("images\\button\\card.png");
    public ImageIcon CardOver = new ImageIcon("images\\button\\card_over.png");
    public ImageIcon CardClick = new ImageIcon("images\\button\\card_click.png");

    public ImageIcon PropNormal = new ImageIcon("images\\button\\prop.png");
    public ImageIcon PropOver = new ImageIcon("images\\button\\prop_over.png");
    public ImageIcon PropClick = new ImageIcon("images\\button\\prop_click.png");

    public JPanel fullPanel = new JPanel(new GridLayout(2, 2));
    public JFrame BankFrame = new JFrame("Bank");
    public ImagePanel BankPanel = new ImagePanel("images\\stats\\bank_menu.png");
    public ImagePanel ChoosePanel = new ImagePanel("images\\stats\\player_menu.png");
    public ImagePanel HistoryPanel = new ImagePanel("images\\stats\\history_menu.png");
    public ImagePanel PropertyPanel = new ImagePanel("images\\stats\\properties_menu.png");
    public JPanel PropPanel = new JPanel();
    public JPanel BankPanelLeft = new JPanel();
    public JLabel totalMoney = new JLabel("             1502.00", box, JLabel.CENTER);
    ;
    public JPanel ChoicesPanel = new JPanel();
    public JScrollPane chooseScroll = new JScrollPane(ChoicesPanel);

    public ImagePanel playerInfo1 = new ImagePanel(playerMenuClick);
    public ImagePanel playerInfo2 = new ImagePanel(playerMenu);
    public ImagePanel playerInfo3 = new ImagePanel(playerMenu);
    public ImagePanel playerInfo4 = new ImagePanel(playerMenu);
    public ImagePanel playerInfo5 = new ImagePanel(playerMenu);

    public Date Today;
    public SimpleDateFormat TimeFormat;
    public String TimeStamp;

    public String selected = "1";

    public String HistoryText1String;
    public String HistoryText2String;
    public String HistoryText3String;
    public String HistoryText4String;
    public String HistoryText5String;

    public PrintWriter printMe = null;

    public JTextArea HistoryText1 = new JTextArea(55, 51);
    public JScrollPane HistoryScroll1 = new JScrollPane(HistoryText1);

    public JTextArea HistoryText2 = new JTextArea(55, 51);
    public JScrollPane HistoryScroll2 = new JScrollPane(HistoryText2);

    public JTextArea HistoryText3 = new JTextArea(55, 51);
    public JScrollPane HistoryScroll3 = new JScrollPane(HistoryText3);

    public JTextArea HistoryText4 = new JTextArea(55, 51);
    public JScrollPane HistoryScroll4 = new JScrollPane(HistoryText4);

    public JTextArea HistoryText5 = new JTextArea(55, 51);
    public JScrollPane HistoryScroll5 = new JScrollPane(HistoryText5);

    public JScrollPane currentHistoryPane;

    public JSplitPane splitPane;

    public JPanel propChoicesPane1 = new JPanel();
    public JPanel propChoicesPane2 = new JPanel();
    public JPanel propChoicesPane3 = new JPanel();
    public JPanel propChoicesPane4 = new JPanel();
    public JPanel propChoicesPane5 = new JPanel();

    public JPanel cardChoicesPane1 = new JPanel();
    public JPanel cardChoicesPane2 = new JPanel();
    public JPanel cardChoicesPane3 = new JPanel();
    public JPanel cardChoicesPane4 = new JPanel();
    public JPanel cardChoicesPane5 = new JPanel();

    public JPanel propFullView = new JPanel();

    public Dimension dim = new Dimension();

    public BoxPoperties PopUpProps;

    public PropertiesListener PropListener;
    public CardOpener CardOpen;

    public JPanel tempProp = new JPanel();
    public JPanel tempCard = new JPanel();
    public JPanel paneInView = new JPanel();

    public JLabel proptertyButtonLabel = new JLabel(PropClick);
    public JLabel cardButtonLabel = new JLabel(CardNormal);
    public boolean propertyChosenBool = true;
    public boolean cardChosenBool = false;

    public boolean player1Jail = false;
    public boolean player2Jail = false;
    public boolean player3Jail = false;
    public boolean player4Jail = false;

    public GameBoardUI game;

    public BankUI(GameBoardUI g)

    {
        game = g;

        playerInfo1.setName("1");
        playerInfo2.setName("2");
        playerInfo3.setName("3");
        playerInfo4.setName("4");
        playerInfo5.setName("5");

        playerInfo1.setOpaque(false);
        playerInfo2.setOpaque(false);
        playerInfo3.setOpaque(false);
        playerInfo4.setOpaque(false);
        playerInfo5.setOpaque(false);

        dim.setSize(playerMenu.getIconWidth() + 20, playerMenu.getIconHeight() * 2.5);

        //Customize main Panel
        fullPanel.setBackground(new Color(176, 176, 176));
        fullPanel.setOpaque(false);
        fullPanel.setBorder(null);

        fullPanel = new JPanel()
        {
            private static final long serialVersionUID = -161818107067156928L;

            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.drawImage(background.getImage(), 0, 0, this);
            }
        };

        fullPanel.setLayout(new GridLayout(2, 2));
        fullPanel.setBorder(null);
        fullPanel.setOpaque(false);

        /************************************************************/
        //Choices
        dim.setSize(410, 347);

        chooseScroll.setPreferredSize(dim);

        ChoicesPanel.setLayout(new BoxLayout(ChoicesPanel, BoxLayout.Y_AXIS));
        ChoicesPanel.setSize(dim);
        ChoicesPanel.setOpaque(false);

        chooseScroll.setBorder(null);
        chooseScroll.setOpaque(false);
        chooseScroll.getViewport().setBorder(null);
        chooseScroll.getViewport().setOpaque(false);
        chooseScroll.setOpaque(false);

        ChoosePanel.setOpaque(false);
        ChoosePanel.setLayout(null);
        ChoosePanel.setLayout(null);
        ChoosePanel.setBorder(null);

        ChoosePanel.add(chooseScroll);
        chooseScroll.setBounds(22, 50, 410, 347);

        playerInfo1.addMouseListener(this);
        playerInfo2.addMouseListener(this);
        playerInfo3.addMouseListener(this);
        playerInfo4.addMouseListener(this);
        playerInfo5.addMouseListener(this);

        playerInfo1.setLayout(new GridLayout(1, 2));
        playerInfo2.setLayout(new GridLayout(1, 2));
        playerInfo3.setLayout(new GridLayout(1, 2));
        playerInfo4.setLayout(new GridLayout(1, 2));
        playerInfo5.setLayout(new GridLayout(1, 2));

        playerInfo1.setBorder(null);
        playerInfo2.setBorder(null);
        playerInfo3.setBorder(null);
        playerInfo4.setBorder(null);
        playerInfo5.setBorder(null);

        playerInfo1.setOpaque(false);

        chooseScroll.setOpaque(false);
        chooseScroll.getViewport().setOpaque(false);

        fullPanel.add(ChoosePanel);

        /**********************************/

        BankPanel.setLayout(new GridLayout(1, 2));
        BankPanel.setOpaque(false);

        BankPanelLeft.setLayout(new GridLayout(2, 1));
        BankPanelLeft.setOpaque(false);

        totalMoney.setOpaque(false);
        totalMoney.setText("");
        totalMoney.setBounds(0, 0, 410, 347);

        //BankPanelLeft.add(totalMoney);

        BankPanel.add(BankPanelLeft);

        fullPanel.add(BankPanel);

        /********************************************/

        //History
        HistoryPanel.setOpaque(false);
        HistoryPanel.setBorder(null);

        HistoryScroll1.setBounds(55, 51, 351, 360);
        HistoryScroll2.setBounds(55, 51, 351, 360);
        HistoryScroll3.setBounds(55, 51, 351, 360);
        HistoryScroll4.setBounds(55, 51, 351, 360);
        HistoryScroll5.setBounds(55, 51, 351, 360);

        HistoryScroll1.setOpaque(false);
        HistoryScroll2.setOpaque(false);
        HistoryScroll3.setOpaque(false);
        HistoryScroll4.setOpaque(false);
        HistoryScroll5.setOpaque(false);

        HistoryScroll1.getViewport().setOpaque(false);
        HistoryScroll2.getViewport().setOpaque(false);
        HistoryScroll3.getViewport().setOpaque(false);
        HistoryScroll4.getViewport().setOpaque(false);
        HistoryScroll5.getViewport().setOpaque(false);

        HistoryScroll1.getViewport().setBorder(null);
        HistoryScroll2.getViewport().setBorder(null);
        HistoryScroll3.getViewport().setBorder(null);
        HistoryScroll4.getViewport().setBorder(null);
        HistoryScroll5.getViewport().setBorder(null);

        HistoryScroll1.setBorder(null);
        HistoryScroll2.setBorder(null);
        HistoryScroll3.setBorder(null);
        HistoryScroll4.setBorder(null);
        HistoryScroll5.setBorder(null);

        HistoryText1.setText("Time :: Event");
        HistoryText2.setText("Time :: Event");
        HistoryText3.setText("Time :: Event");
        HistoryText4.setText("Time :: Event");
        HistoryText5.setText("Time :: Event");

        HistoryText1.setEditable(false);
        HistoryText1.setLineWrap(true);
        HistoryText1.setWrapStyleWord(true);
        HistoryText1.setOpaque(false);
        HistoryText1.setForeground(Color.WHITE);
        HistoryText1.setFont(new Font("Calibri", Font.BOLD, 15));
        HistoryText3.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

        HistoryText2.setEditable(false);
        HistoryText2.setLineWrap(true);
        HistoryText2.setWrapStyleWord(true);
        HistoryText2.setOpaque(false);
        HistoryText2.setForeground(Color.WHITE);
        HistoryText2.setFont(new Font("Calibri", Font.BOLD, 15));
        HistoryText3.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

        HistoryText3.setEditable(false);
        HistoryText3.setLineWrap(true);
        HistoryText3.setWrapStyleWord(true);
        HistoryText3.setOpaque(false);
        HistoryText3.setForeground(Color.WHITE);
        HistoryText3.setFont(new Font("Calibri", Font.BOLD, 15));
        HistoryText3.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

        HistoryText4.setEditable(false);
        HistoryText4.setLineWrap(true);
        HistoryText4.setWrapStyleWord(true);
        HistoryText4.setOpaque(false);
        HistoryText4.setForeground(Color.WHITE);
        HistoryText4.setFont(new Font("Calibri", Font.BOLD, 15));
        HistoryText3.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

        HistoryText5.setEditable(false);
        HistoryText5.setLineWrap(true);
        HistoryText5.setWrapStyleWord(true);
        HistoryText5.setOpaque(false);
        HistoryText5.setForeground(Color.WHITE);
        HistoryText5.setFont(new Font("Calibri", Font.BOLD, 15));
        HistoryText3.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

        HistoryPanel.add(HistoryScroll1);

        fullPanel.add(HistoryPanel);

        /***************************************Cards*/
        propChoicesPane1.setOpaque(false);
        propChoicesPane2.setOpaque(false);
        propChoicesPane3.setOpaque(false);
        propChoicesPane4.setOpaque(false);
        propChoicesPane5.setOpaque(false);

        cardChoicesPane1.setOpaque(false);
        cardChoicesPane2.setOpaque(false);
        cardChoicesPane3.setOpaque(false);
        cardChoicesPane4.setOpaque(false);
        cardChoicesPane5.setOpaque(false);

        JScrollPane PScrollPanelChoices = new JScrollPane(propChoicesPane1);
        PScrollPanelChoices.getViewport().setOpaque(false);
        PScrollPanelChoices.getViewport().setSize(351, 82);
        PScrollPanelChoices.setOpaque(false);
        PScrollPanelChoices.setBounds(0, 0, 351, 82);
        PScrollPanelChoices.setPreferredSize(new Dimension(351, 82));
        PScrollPanelChoices.setBorder(null);

        JScrollPane CScrollPanelChoices = new JScrollPane(cardChoicesPane1);
        CScrollPanelChoices.getViewport().setOpaque(false);
        CScrollPanelChoices.getViewport().setSize(351, 82);
        CScrollPanelChoices.setOpaque(false);
        CScrollPanelChoices.setBounds(0, 0, 351, 82);
        CScrollPanelChoices.setPreferredSize(new Dimension(351, 82));
        CScrollPanelChoices.setBorder(null);

        tempProp.setOpaque(false);
        tempCard.setOpaque(false);

        tempProp.add(PScrollPanelChoices);
        tempCard.add(CScrollPanelChoices);

        paneInView.add(tempProp);        //Change inside of this to change views
        paneInView.setBounds(54, 50, 351, 80);
        paneInView.setOpaque(false);

        propFullView.setOpaque(false);
        propFullView.setLayout(new FlowLayout());
        propFullView.setBounds(54, 135, 351, 263);

        PropertyPanel.setLayout(null);
        PropertyPanel.setOpaque(false);

        PropertyPanel.add(paneInView);
        PropertyPanel.add(propFullView);

        proptertyButtonLabel.setBounds(272, -5, 75, 65);
        cardButtonLabel.setBounds(343, -5, 75, 65);
        addListenersForButtons();

        PropertyPanel.add(proptertyButtonLabel);
        PropertyPanel.add(cardButtonLabel);

        fullPanel.add(PropertyPanel);
    }

    static Image iconToImage(Icon icon)
    {
        if (icon instanceof ImageIcon)
        {
            return ((ImageIcon) icon).getImage();
        }
        else
        {
            int w = icon.getIconWidth();
            int h = icon.getIconHeight();
            GraphicsEnvironment ge =
                    GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gd = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gd.getDefaultConfiguration();
            BufferedImage image = gc.createCompatibleImage(w, h);
            Graphics2D g = image.createGraphics();
            icon.paintIcon(null, g, 0, 0);
            g.dispose();
            return image;
        }
    }

    public Component getGUI()
    {
        return fullPanel;
    }

    public void updatePlayers(int x, JLabel[] a, JLabel[] b)
    {
        switch (x)
        {
            case 2:
                playerInfo1.add(a[0]);
                playerInfo1.add(a[0]);
                playerInfo1.add(a[0]);
                playerInfo1.add(a[0]);

                playerInfo1.add(b[0]);
                playerInfo1.add(b[0]);
                playerInfo1.add(b[0]);
                playerInfo1.add(b[0]);

                playerInfo2.add(a[1]);
                playerInfo2.add(a[1]);
                playerInfo2.add(a[1]);
                playerInfo2.add(a[1]);

                playerInfo2.add(b[1]);
                playerInfo2.add(b[1]);
                playerInfo2.add(b[1]);
                playerInfo2.add(b[1]);

                ChoicesPanel.add(playerInfo1);
                ChoicesPanel.add(playerInfo2);

                dim.setSize(playerMenu.getIconWidth() + 20, ((playerMenu.getIconHeight() * 2)));

                chooseScroll.setPreferredSize(dim);
                ChoicesPanel.setSize(dim);

                break;

            case 3:
                playerInfo1.add(a[0]);
                playerInfo1.add(a[0]);
                playerInfo1.add(a[0]);
                playerInfo1.add(a[0]);

                playerInfo1.add(b[0]);
                playerInfo1.add(b[0]);
                playerInfo1.add(b[0]);
                playerInfo1.add(b[0]);

                playerInfo2.add(a[1]);
                playerInfo2.add(a[1]);
                playerInfo2.add(a[1]);
                playerInfo2.add(a[1]);

                playerInfo2.add(b[1]);
                playerInfo2.add(b[1]);
                playerInfo2.add(b[1]);
                playerInfo2.add(b[1]);

                playerInfo3.add(a[2]);
                playerInfo3.add(a[2]);
                playerInfo3.add(a[2]);
                playerInfo3.add(a[2]);

                playerInfo3.add(b[2]);
                playerInfo3.add(b[2]);
                playerInfo3.add(b[2]);
                playerInfo3.add(b[2]);

                ChoicesPanel.add(playerInfo1);
                ChoicesPanel.add(playerInfo2);
                ChoicesPanel.add(playerInfo3);

                break;

            case 4:
                playerInfo1.add(a[0]);
                playerInfo1.add(a[0]);
                playerInfo1.add(a[0]);
                playerInfo1.add(a[0]);

                playerInfo1.add(b[0]);
                playerInfo1.add(b[0]);
                playerInfo1.add(b[0]);
                playerInfo1.add(b[0]);

                playerInfo2.add(a[1]);
                playerInfo2.add(a[1]);
                playerInfo2.add(a[1]);
                playerInfo2.add(a[1]);

                playerInfo2.add(b[1]);
                playerInfo2.add(b[1]);
                playerInfo2.add(b[1]);
                playerInfo2.add(b[1]);

                playerInfo3.add(a[2]);
                playerInfo3.add(a[2]);
                playerInfo3.add(a[2]);
                playerInfo3.add(a[2]);

                playerInfo3.add(b[2]);
                playerInfo3.add(b[2]);
                playerInfo3.add(b[2]);
                playerInfo3.add(b[2]);

                playerInfo4.add(a[3]);
                playerInfo4.add(a[3]);
                playerInfo4.add(a[3]);
                playerInfo4.add(a[3]);

                playerInfo4.add(b[3]);
                playerInfo4.add(b[3]);
                playerInfo4.add(b[3]);
                playerInfo4.add(b[3]);

                ChoicesPanel.add(playerInfo1);
                ChoicesPanel.add(playerInfo2);
                ChoicesPanel.add(playerInfo3);
                ChoicesPanel.add(playerInfo4);

                break;

            case 5:
                playerInfo1.add(a[0]);
                playerInfo1.add(a[0]);
                playerInfo1.add(a[0]);
                playerInfo1.add(a[0]);

                playerInfo1.add(b[0]);
                playerInfo1.add(b[0]);
                playerInfo1.add(b[0]);
                playerInfo1.add(b[0]);

                playerInfo2.add(a[1]);
                playerInfo2.add(a[1]);
                playerInfo2.add(a[1]);
                playerInfo2.add(a[1]);

                playerInfo2.add(b[1]);
                playerInfo2.add(b[1]);
                playerInfo2.add(b[1]);
                playerInfo2.add(b[1]);


                playerInfo3.add(a[2]);
                playerInfo3.add(a[2]);
                playerInfo3.add(a[2]);
                playerInfo3.add(a[2]);

                playerInfo3.add(b[2]);
                playerInfo3.add(b[2]);
                playerInfo3.add(b[2]);
                playerInfo3.add(b[2]);

                playerInfo4.add(a[3]);
                playerInfo4.add(a[3]);
                playerInfo4.add(a[3]);
                playerInfo4.add(a[3]);

                playerInfo4.add(b[3]);
                playerInfo4.add(b[3]);
                playerInfo4.add(b[3]);
                playerInfo4.add(b[3]);

                playerInfo5.add(a[4]);
                playerInfo5.add(a[4]);
                playerInfo5.add(a[4]);
                playerInfo5.add(a[4]);

                playerInfo5.add(b[4]);
                playerInfo5.add(b[4]);
                playerInfo5.add(b[4]);
                playerInfo5.add(b[4]);

                ChoicesPanel.add(playerInfo1);
                ChoicesPanel.add(playerInfo2);
                ChoicesPanel.add(playerInfo3);
                ChoicesPanel.add(playerInfo4);
                ChoicesPanel.add(playerInfo5);

                break;
        }

        playerInfo1.validate();
        playerInfo1.repaint();

        playerInfo2.validate();
        playerInfo2.repaint();

        playerInfo3.validate();
        playerInfo3.repaint();

        playerInfo4.validate();
        playerInfo4.repaint();

        playerInfo5.validate();
        playerInfo5.repaint();

        ChoicesPanel.validate();
        ChoicesPanel.repaint();


    }

    public void addHistory(int x, String a)
    {
        TimeFormat = new SimpleDateFormat("hh.mm.ss");
        Today = new Date();
        TimeStamp = TimeFormat.format(Today);

        switch (x)
        {

            case 0:
                HistoryText1.append("\n\n" + TimeStamp + ": " + a);
                HistoryText1.setCaretPosition(HistoryText1.getDocument().getLength());
                break;
            case 1:
                HistoryText2.append("\n\n" + TimeStamp + ": " + a);
                HistoryText2.setCaretPosition(HistoryText2.getDocument().getLength());
                break;
            case 2:
                HistoryText3.append("\n\n" + TimeStamp + ": " + a);
                HistoryText3.setCaretPosition(HistoryText3.getDocument().getLength());
                break;
            case 3:
                HistoryText4.append("\n\n" + TimeStamp + ": " + a);
                HistoryText4.setCaretPosition(HistoryText4.getDocument().getLength());
                break;
            case 4:
                HistoryText5.append("\n\n" + TimeStamp + ": " + a);
                HistoryText5.setCaretPosition(HistoryText5.getDocument().getLength());
                break;
        }
    }

    public void mouseClicked(MouseEvent e)
    {
    }

    public void mouseExited(MouseEvent e)
    {
        if ((((ImagePanel) e.getSource()).getName()) != (selected))
        {
            ((ImagePanel) e.getSource()).changeImage(playerMenu);
            ((ImagePanel) e.getSource()).repaint();
        }
    }

    public void mousePressed(MouseEvent e)
    {
        if (selected.equals("1")) ;
        {
            playerInfo1.changeImage(playerMenu);
        }
        if (selected.equals("2")) ;
        {
            playerInfo2.changeImage(playerMenu);
        }
        if (selected.equals("3")) ;
        {
            playerInfo3.changeImage(playerMenu);
        }
        if (selected.equals("4")) ;
        {
            playerInfo4.changeImage(playerMenu);
        }
        if (selected.equals("5")) ;
        {
            playerInfo5.changeImage(playerMenu);
        }

        selected = ((ImagePanel) e.getSource()).getName();

        ((ImagePanel) e.getSource()).changeImage(playerMenuClick);

        if (((ImagePanel) e.getSource()).getName().equals("1"))
        {
            updatePlayerTabbedPane(HistoryScroll1, propChoicesPane1, cardChoicesPane1);

            if (player1Jail)
            {
                totalMoney.setVisible(true);
            }
        }
        if (((ImagePanel) e.getSource()).getName().equals("2"))
        {
            updatePlayerTabbedPane(HistoryScroll2, propChoicesPane2, cardChoicesPane2);

            if (player2Jail)
            {
                totalMoney.setVisible(true);
            }
        }
        if (((ImagePanel) e.getSource()).getName().equals("3"))
        {
            updatePlayerTabbedPane(HistoryScroll3, propChoicesPane3, cardChoicesPane3);

            if (player3Jail)
            {
                totalMoney.setVisible(true);
            }
        }
        if (((ImagePanel) e.getSource()).getName().equals("4"))
        {
            updatePlayerTabbedPane(HistoryScroll4, propChoicesPane4, cardChoicesPane4);

            if (player4Jail)
            {
                totalMoney.setVisible(true);
            }
        }
        ((ImagePanel) e.getSource()).repaint();
    }

    public void mouseReleased(MouseEvent e)
    {
    }

    public void mouseEntered(MouseEvent e)
    {
        if (((ImagePanel) e.getSource()).getName() != selected)
        {
            ((ImagePanel) e.getSource()).changeImage(playerMenuOver);
            ((ImagePanel) e.getSource()).repaint();
        }
    }

    public void resizeImageAndAdd(Icon ic, int a, String name, String PropOrCards)
    {
        double scale = 85;
        int largestDimension = 55;

        int sizeDifference, originalImageLargestDim;

        Image inImage = iconToImage(ic);

        //find biggest dimension
        if (inImage.getWidth(null) > inImage.getHeight(null))
        {
            scale = (double) largestDimension / (double) inImage.getWidth(null);
            sizeDifference = inImage.getWidth(null) - largestDimension;
            originalImageLargestDim = inImage.getWidth(null);
        }
        else
        {
            scale = (double) largestDimension / (double) inImage.getHeight(null);
            sizeDifference = inImage.getHeight(null) - largestDimension;
            originalImageLargestDim = inImage.getHeight(null);
        }
        //create an image buffer to draw to
        BufferedImage outImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB); //arbitrary init so code compiles
        Graphics2D g2d;
        AffineTransform tx;

        int numSteps = sizeDifference / 100;
        int stepSize = sizeDifference / numSteps;
        int stepWeight = stepSize / 2;
        int heavierStepSize = stepSize + stepWeight;
        int lighterStepSize = stepSize - stepWeight;
        int currentStepSize, centerStep;
        double scaledW = inImage.getWidth(null);
        double scaledH = inImage.getHeight(null);
        if (numSteps % 2 == 1) //if there's an odd number of steps
            centerStep = (int) Math.ceil((double) numSteps / 2d); //find the center step
        else
            centerStep = -1; //set it to -1 so it's ignored later
        Integer intermediateSize = originalImageLargestDim, previousIntermediateSize = originalImageLargestDim;

        for (Integer i = 0; i < numSteps; i++)
        {
            if (i + 1 != centerStep) //if this isn't the center step
            {
                if (i == numSteps - 1) //if this is the last step
                {
                    //fix the stepsize to account for decimal place errors previously
                    currentStepSize = previousIntermediateSize - largestDimension;
                }
                else
                {
                    if (numSteps - i > numSteps / 2) //if we're in the first half of the reductions
                        currentStepSize = heavierStepSize;
                    else
                        currentStepSize = lighterStepSize;
                }
            }
            else //center step, use natural step size
            {
                currentStepSize = stepSize;
            }
            intermediateSize = previousIntermediateSize - currentStepSize;
            scale = (double) intermediateSize / (double) previousIntermediateSize;
            scaledW = (int) scaledW * scale;
            scaledH = (int) scaledH * scale;
            outImage = new BufferedImage((int) scaledW, (int) scaledH, BufferedImage.TYPE_INT_RGB);
            g2d = outImage.createGraphics();
            g2d.setBackground(Color.WHITE);
            g2d.clearRect(0, 0, outImage.getWidth(), outImage.getHeight());
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            tx = new AffineTransform();
            tx.scale(scale, scale);
            g2d.drawImage(inImage, tx, null);
            g2d.dispose();
            inImage = new ImageIcon(outImage).getImage();
            previousIntermediateSize = intermediateSize;
        }


        if (PropOrCards.equals("Prop"))
        {
            JLabel thumbnail = new JLabel(new ImageIcon(inImage));
            thumbnail.setName(name);
            thumbnail.addMouseListener(PropListener);

            switch (a)
            {
                case 0:
                    propChoicesPane1.add(thumbnail);
                    propChoicesPane1.validate();
                    propChoicesPane1.repaint();
                    break;
                case 1:
                    propChoicesPane2.add(thumbnail);
                    propChoicesPane2.validate();
                    propChoicesPane2.repaint();
                    break;
                case 2:
                    propChoicesPane3.add(thumbnail);
                    propChoicesPane3.validate();
                    propChoicesPane3.repaint();
                    break;
                case 3:
                    propChoicesPane4.add(thumbnail);
                    propChoicesPane4.validate();
                    propChoicesPane4.repaint();
                    break;
                case 4:
                    propChoicesPane5.add(thumbnail);
                    propChoicesPane5.validate();
                    propChoicesPane5.repaint();
                    break;
            }
        }
        if (PropOrCards.equals("Card"))
        {
            JLabel thumbnail = new JLabel(new ImageIcon(inImage));
            thumbnail.setName(name);
            thumbnail.addMouseListener(CardOpen);

            switch (a)
            {
                case 0:
                    cardChoicesPane1.add(thumbnail);
                    cardChoicesPane1.validate();
                    cardChoicesPane1.repaint();
                    break;
                case 1:
                    cardChoicesPane2.add(thumbnail);
                    cardChoicesPane2.validate();
                    cardChoicesPane2.repaint();
                    break;
                case 2:
                    cardChoicesPane3.add(thumbnail);
                    cardChoicesPane3.validate();
                    cardChoicesPane3.repaint();
                    break;
                case 3:
                    cardChoicesPane4.add(thumbnail);
                    cardChoicesPane4.validate();
                    cardChoicesPane4.repaint();
                    break;
                case 4:
                    cardChoicesPane5.add(thumbnail);
                    cardChoicesPane5.validate();
                    cardChoicesPane5.repaint();
                    break;
            }
        }

        tempProp.validate();
        tempProp.repaint();

        tempCard.validate();
        tempCard.repaint();

        paneInView.validate();
        paneInView.repaint();

    }

    public void setEnlargedImages(BoxPoperties p, ChanceCards c, CommChestCards cc)
    {
        PopUpProps = p;
        PropListener = new PropertiesListener(p, propFullView);
        CardOpen = new CardOpener(c, cc, propFullView);
    }

    public void updatePlayerTabbedPane(JScrollPane historyS, JPanel pPropteries, JPanel pCards)
    {
        HistoryPanel.removeAll();
        HistoryPanel.add(historyS);

        HistoryPanel.validate();
        HistoryPanel.repaint();

        Dimension dimScroll = new Dimension(351, 82);

        JScrollPane ScrollPaneProp = new JScrollPane(pPropteries);
        ScrollPaneProp.getViewport().setOpaque(false);
        ScrollPaneProp.setOpaque(false);
        ScrollPaneProp.setBorder(null);
        ScrollPaneProp.setBounds(0, 0, 351, 82);
        ScrollPaneProp.getViewport().setSize(351, 82);
        ScrollPaneProp.setSize(351, 82);
        ScrollPaneProp.setPreferredSize(dimScroll);
        ScrollPaneProp.getViewport().setBorder(null);
        ScrollPaneProp.setBorder(null);

        JScrollPane ScrollPaneCard = new JScrollPane(pCards);
        ScrollPaneCard.getViewport().setOpaque(false);
        ScrollPaneCard.setOpaque(false);
        ScrollPaneCard.setBorder(null);
        ScrollPaneCard.setBounds(0, 0, 351, 82);
        ScrollPaneCard.getViewport().setSize(351, 82);
        ScrollPaneCard.setSize(351, 82);
        ScrollPaneCard.setPreferredSize(dimScroll);
        ScrollPaneProp.getViewport().setBorder(null);
        ScrollPaneProp.setBorder(null);

        tempProp.removeAll();
        tempProp.add(ScrollPaneProp);

        tempProp.validate();
        tempProp.repaint();

        tempCard.removeAll();
        tempCard.add(ScrollPaneCard);

        tempCard.validate();
        tempCard.repaint();

        paneInView.validate();
        paneInView.repaint();
    }

    public void addListenersForButtons()
    {
        proptertyButtonLabel.addMouseListener(new MouseListener()
        {

            public void mousePressed(MouseEvent e)
            {
            }

            public void mouseReleased(MouseEvent e)
            {
                proptertyButtonLabel.setIcon(PropClick);
                cardButtonLabel.setIcon(CardNormal);

                proptertyButtonLabel.repaint();
                cardButtonLabel.repaint();

                paneInView.removeAll();
                paneInView.add(tempProp);

                paneInView.validate();
                paneInView.repaint();

                propertyChosenBool = true;
                cardChosenBool = false;
            }

            public void mouseEntered(MouseEvent e)
            {
                if (propertyChosenBool == false)
                {
                    proptertyButtonLabel.setIcon(PropOver);
                    proptertyButtonLabel.repaint();
                }
            }

            public void mouseExited(MouseEvent e)
            {
                if (propertyChosenBool == false)
                {
                    proptertyButtonLabel.setIcon(PropNormal);
                    proptertyButtonLabel.repaint();
                }
            }

            public void mouseClicked(MouseEvent e)
            {
            }

        });

        cardButtonLabel.addMouseListener(new MouseListener()
        {

            public void mousePressed(MouseEvent e)
            {
            }

            public void mouseReleased(MouseEvent e)
            {
                cardButtonLabel.setIcon(CardClick);
                proptertyButtonLabel.setIcon(PropNormal);

                proptertyButtonLabel.repaint();
                cardButtonLabel.repaint();

                paneInView.removeAll();
                paneInView.add(tempCard);

                paneInView.validate();
                paneInView.repaint();

                cardChosenBool = true;
                propertyChosenBool = false;

            }

            public void mouseEntered(MouseEvent e)
            {
                if (cardChosenBool == false)
                {
                    cardButtonLabel.setIcon(CardOver);
                    cardButtonLabel.repaint();
                }
            }

            public void mouseExited(MouseEvent e)
            {
                if (cardChosenBool == false)
                {
                    cardButtonLabel.setIcon(CardNormal);
                    cardButtonLabel.repaint();
                }
            }

            public void mouseClicked(MouseEvent e)
            {
            }

        });
    }

    public void printList()
    {
        try
        {
            printMe = new PrintWriter(new File("data_log.xml"));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "File Reader Error (Source: BankUI.java)", "Warning", JOptionPane.WARNING_MESSAGE);
        }

        HistoryText1String = HistoryText1.getText();
        HistoryText2String = HistoryText2.getText();
        HistoryText3String = HistoryText3.getText();
        HistoryText4String = HistoryText4.getText();
        HistoryText5String = HistoryText5.getText();

        printMe.println("Player Log");
        printMe.println("\n======================Player 1\n");
        printMe.println(HistoryText1String);
        printMe.println("\n======================Player 2\n");
        printMe.println(HistoryText2String);
        printMe.println("\n======================Player 3\n");
        printMe.println(HistoryText3String);
        printMe.println("\n======================Player 4\n");
        printMe.println(HistoryText4String);
        printMe.println("\n======================Player 5\n");
        printMe.println(HistoryText5String);
        printMe.println("\n======================");

        printMe.close();

    }

    public void placeInJail(int x)
    {

        switch (x)
        {
            case 0:
                player1Jail = true;
                game.player1Jail.setVisible(true);
                break;
            case 1:
                player2Jail = true;
                game.player2Jail.setVisible(true);
                break;
            case 2:
                player3Jail = true;
                game.player3Jail.setVisible(true);
                break;
            case 3:
                player4Jail = true;
                game.player4Jail.setVisible(true);
                break;
        }
        if (selected.equals("1") && x == 1 && player1Jail)
        {
            totalMoney.setVisible(true);
        }
        else if (selected.equals("2") && x == 2 && player2Jail)
        {
            totalMoney.setVisible(true);
        }
        else if (selected.equals("3") && x == 3 && player3Jail)
        {
            totalMoney.setVisible(true);
        }
        else if (selected.equals("4") && x == 4 && player4Jail)
        {
            totalMoney.setVisible(true);
        }
        else
        {
            totalMoney.setVisible(false);
        }
    }

    public void removeFromJail(int x, boolean a)
    {

        switch (x)
        {
            case 0:
                player1Jail = false;
                game.player1Jail.setVisible(false);
                break;
            case 1:
                player2Jail = false;
                game.player1Jail.setVisible(false);
                break;
            case 2:
                player3Jail = false;
                game.player1Jail.setVisible(false);
                break;
            case 3:
                player4Jail = false;
                game.player1Jail.setVisible(false);
                break;
        }
        if (selected.equals("1") && x == 1 && !player1Jail)
        {
            totalMoney.setVisible(false);
        }
        else if (selected.equals("2") && x == 2 && !player2Jail)
        {
            totalMoney.setVisible(false);
        }
        else if (selected.equals("3") && x == 3 && !player3Jail)
        {
            totalMoney.setVisible(false);
        }
        else if (selected.equals("4") && x == 4 && !player4Jail)
        {
            totalMoney.setVisible(false);
        }
        else
        {
            totalMoney.setVisible(true);
        }

    }

    public void tradeCard(int x, String x1, int y, String y2)
    {
        JPanel temp1 = null;
        JPanel temp2 = null;

        switch (x)
        {
            case 0:
                temp1 = propChoicesPane1;
                break;
            case 1:
                temp1 = propChoicesPane2;
                break;
            case 2:
                temp1 = propChoicesPane3;
                break;
            case 3:
                temp1 = propChoicesPane4;
                break;
            case 4:
                temp1 = propChoicesPane5;
                break;
        }
        switch (y)
        {
            case 0:
                temp2 = propChoicesPane1;
                break;
            case 1:
                temp2 = propChoicesPane2;
                break;
            case 2:
                temp2 = propChoicesPane3;
                break;
            case 3:
                temp2 = propChoicesPane4;
                break;
            case 4:
                temp2 = propChoicesPane5;
                break;
        }

        JLabel label1 = (JLabel) findInPanel(temp1, x1);
        JLabel label2 = (JLabel) findInPanel(temp2, y2);

        temp1.remove(label1);
        temp2.remove(label2);

        temp2.add(label1);
        temp1.add(label2);
    }

    public Component findInPanel(JPanel a, String b)
    {
        JLabel temp = null;

        Component[] c = a.getComponents();

        for (int x = 0; x < c.length; x++)
        {
            if (c[x].getName().equals(b))
            {
                temp = (JLabel) c[x];
            }
        }

        return temp;
    }

}