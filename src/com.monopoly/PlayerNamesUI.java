package src.com.monopoly; /**
 * @(#)PlayerNamesUI.java
 *
 *
 * @Mathew Kurian
 * @version 1.00 2010/2/13
 */

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;

public class PlayerNamesUI extends Thread
{

    public JLayeredPane outerShell = new JLayeredPane();
    public ImagePanel innerShell = new ImagePanel("images\\playerui\\back.png");

    public ImagePanel innerChoice = new ImagePanel("images\\playerui\\chooseBack.png");

    public PrintWriter printMe = null;

    public ImageIcon pieceBorder = new ImageIcon("images\\borders\\gamepiece_border.png");

    public String Name1 = "Name Here        ";

    public JFormattedTextField textArea1;
    public JFormattedTextField textArea2;
    public JFormattedTextField textArea3;
    public JFormattedTextField textArea4;

    public String textArea1String;
    public String textArea2String;
    public String textArea3String;
    public String textArea4String;

    public DefaultListModel dlist = new DefaultListModel();

    public String player1Icon = null;
    public String player2Icon = null;
    public String player3Icon = null;
    public String player4Icon = null;

    public JButton PickImage1 = new JButton("Player 1 Pick Icon");
    public JButton PickImage2 = new JButton("Player 2 Pick Icon");
    public JButton PickImage3 = new JButton("Player 3 Pick Icon");
    public JButton PickImage4 = new JButton("Player 4 Pick Icon");

    public JLabel spacer1 = new JLabel(" ");
    public String[] playerNumChoices = {"2", "3"};
    public ImageIcon findSize = new ImageIcon("images\\button\\CHECK.png");
    public JList IconChoices = new JList(dlist);
    public SplashScreenUI splash;
    public HistoryUI hist;
    public BackgroundUI backGround;
    public int total = 0;
    GamePiece IconList = new GamePiece();
    public ImageIcon[] IconArray = IconList.getFullChoices();
    ArrayList<Component> enabledComponents;

    public PlayerNamesUI(HistoryUI a, BackgroundUI b, SplashScreenUI s)
    {
        backGround = b;
        hist = a;
        splash = s;
    }

    public void createAndShowGUI()
    {
        outerShell.setBounds(0, 0, 870, 525);
        backGround.addPlayBoard(outerShell);
    }

    @SuppressWarnings("deprecation")
    public void exitMe()
    {
        innerShell.removeAll();
        this.stop();
    }

    public void printList()
    {
        try
        {
            printMe = new PrintWriter(new File("data_player.xml"));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "File Reader Error (Source: PlayerNamesUI.java)", "Warning", JOptionPane.WARNING_MESSAGE);
        }

        textArea1String = textArea1.getText();
        textArea2String = textArea2.getText();
        textArea3String = textArea3.getText();
        textArea4String = textArea4.getText();

        if (textArea1String.equals("Name Here        ") == false)
        {
            printMe.println(textArea1String + "," + player1Icon);
        }
        if (textArea2String.equals("Name Here        ") == false)
        {
            printMe.println(textArea2String + "," + player2Icon);
        }
        if (textArea3String.equals("Name Here        ") == false)
        {
            printMe.println(textArea3String + "," + player3Icon);
        }
        if (textArea4String.equals("Name Here        ") == false)
        {
            printMe.println(textArea4String + "," + player4Icon);
        }

        printMe.close();

    }

    public String getNameofIcon()
    {

        int index = 0;

        try
        {
            if ((IconChoices.getSelectedValue()).toString().equals("images\\pieces\\Remi.png"))
            {
                index = IconChoices.getSelectedIndex();
                dlist.remove(index);
                return "remi";
            }
            else if ((IconChoices.getSelectedValue()).toString().equals("images\\pieces\\basketball.png"))
            {
                index = IconChoices.getSelectedIndex();
                dlist.remove(index);
                return "basketball";
            }
            else if ((IconChoices.getSelectedValue()).toString().equals("images\\pieces\\iphone.png"))
            {
                index = IconChoices.getSelectedIndex();
                dlist.remove(index);
                return "iphone";
            }
            else if ((IconChoices.getSelectedValue()).toString().equals("images\\pieces\\nba_logo.png"))
            {
                index = IconChoices.getSelectedIndex();
                dlist.remove(index);
                return "logo";
            }
            else
            {
                index = IconChoices.getSelectedIndex();
                dlist.remove(index);
            }
        }

        catch (NullPointerException e)
        {
            JOptionPane.showMessageDialog(null, "Null Pointer Exception\nPick an Image", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return "Sun";

    }

    public void showChoices(JLabel a)
    {
        JLabel newLabelofPlayer = a;
        newLabelofPlayer.setBounds(70, 120, findSize.getIconWidth(), findSize.getIconHeight());
        newLabelofPlayer.repaint();

        IconChoices.setBounds(24, 85, 310, 63);

        innerChoice.removeAll();
        innerChoice.add(newLabelofPlayer);
        innerChoice.add(IconChoices);
        innerChoice.setBounds(263, 150, innerChoice.getWidth(), innerChoice.getHeight());

        outerShell.add(innerChoice, new Integer(3));

        innerChoice.repaint();
        outerShell.repaint();
    }

    public void hideChoices()
    {
        outerShell.remove(innerChoice);
        outerShell.repaint();
    }

    @Override
    public void run()
    {

        try
        {
            textArea1 = new JFormattedTextField(new MaskFormatter("*****************"));
            textArea2 = new JFormattedTextField(new MaskFormatter("*****************"));
            textArea3 = new JFormattedTextField(new MaskFormatter("*****************"));
            textArea4 = new JFormattedTextField(new MaskFormatter("*****************"));
        }
        catch (ParseException wea)
        {
            System.out.println("Error: Parse Exception caught");
        }

        hist.addHistory("PlayerNamesUI Initialized Sucessfully");
        hist.addHistory("\nREADME-----:\nChoose Number of Players\nEnter Names\nSet Icons\n*Player 1 is first and so on");

        innerShell.setLayout(null);

        for (int i = 0; i < IconArray.length; i++)
        {
            dlist.add(i, IconArray[i]);
        }

        IconChoices.setOpaque(false);
        IconChoices.setVisibleRowCount(1);
        IconChoices.setLayoutOrientation(JList.HORIZONTAL_WRAP);

        DefaultListCellRenderer renderer = (DefaultListCellRenderer) IconChoices.getCellRenderer();
        renderer.setOpaque(false);

        textArea1.setValue(new String(Name1));
        textArea2.setValue(new String(Name1));
        textArea3.setValue(new String(Name1));
        textArea4.setValue(new String(Name1));

        textArea1.setBackground(new Color(84, 83, 78));
        textArea2.setBackground(new Color(84, 83, 78));
        textArea3.setBackground(new Color(84, 83, 78));
        textArea4.setBackground(new Color(84, 83, 78));

        textArea1.setForeground(Color.WHITE);
        textArea2.setForeground(Color.WHITE);
        textArea3.setForeground(Color.WHITE);
        textArea4.setForeground(Color.WHITE);

        textArea1.setBorder(null);
        textArea2.setBorder(null);
        textArea3.setBorder(null);
        textArea4.setBorder(null);

        textArea1.setEnabled(true);
        textArea2.setEnabled(true);
        textArea3.setEnabled(false);
        textArea4.setEnabled(false);

        textArea1.setFont(new Font("Myriad Pro", Font.PLAIN, 14));
        textArea2.setFont(new Font("Myriad Pro", Font.PLAIN, 14));
        textArea3.setFont(new Font("Myriad Pro", Font.PLAIN, 14));
        textArea4.setFont(new Font("Myriad Pro", Font.PLAIN, 14));

        textArea1.setBounds(50, 235, 230, 30);
        textArea2.setBounds(50, 285, 230, 30);
        textArea3.setBounds(50, 335, 230, 30);
        textArea4.setBounds(50, 385, 230, 30);

        PickImage1.setBorder(null);
        PickImage2.setBorder(null);
        PickImage3.setBorder(null);
        PickImage4.setBorder(null);

        PickImage1.setFont(new Font("Myriad Pro", Font.PLAIN, 14));
        PickImage2.setFont(new Font("Myriad Pro", Font.PLAIN, 14));
        PickImage3.setFont(new Font("Myriad Pro", Font.PLAIN, 14));
        PickImage4.setFont(new Font("Myriad Pro", Font.PLAIN, 14));

        PickImage1.setBackground(new Color(84, 83, 78));
        PickImage2.setBackground(new Color(84, 83, 78));
        PickImage3.setBackground(new Color(84, 83, 78));
        PickImage4.setBackground(new Color(84, 83, 78));

        PickImage1.setForeground(Color.WHITE);
        PickImage2.setForeground(Color.WHITE);
        PickImage3.setForeground(Color.WHITE);
        PickImage4.setForeground(Color.WHITE);

        PickImage1.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                JLabel startLabel = new JLabel(new ImageIcon("images\\button\\CORRECT.png"));

                startLabel.addMouseListener(new MouseListener()
                {

                    public void mouseClicked(MouseEvent e)
                    {

                    }

                    public void mouseExited(MouseEvent e)
                    {

                        ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\CORRECT.png")));
                        ((JLabel) e.getSource()).repaint();

                    }

                    public void mousePressed(MouseEvent e)
                    {
                        ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\CORRECT_click.png")));
                        ((JLabel) e.getSource()).repaint();
                    }

                    public void mouseReleased(MouseEvent e)
                    {

                        ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\CORRECT.png")));
                        ((JLabel) e.getSource()).repaint();

                        player1Icon = getNameofIcon();

                        System.out.println("Here1: " + player1Icon);

                        hideChoices();

                    }

                    public void mouseEntered(MouseEvent e)
                    {
                        ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\CORRECT_over.png")));
                        ((JLabel) e.getSource()).repaint();
                    }
                });

                showChoices(startLabel);

            }
        });

        PickImage2.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {

                JLabel startLabel = new JLabel(new ImageIcon("images\\button\\CORRECT.png"));
                startLabel.addMouseListener(new MouseListener()
                {

                    public void mouseClicked(MouseEvent e)
                    {

                    }

                    public void mouseExited(MouseEvent e)
                    {

                        ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\CORRECT.png")));
                        ((JLabel) e.getSource()).repaint();
                    }

                    public void mousePressed(MouseEvent e)
                    {
                        ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\CORRECT_click.png")));
                        ((JLabel) e.getSource()).repaint();
                    }

                    public void mouseReleased(MouseEvent e)
                    {

                        ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\CORRECT.png")));
                        ((JLabel) e.getSource()).repaint();

                        player2Icon = getNameofIcon();

                        System.out.println("Here2: " + player2Icon);

                        hideChoices();

                    }

                    public void mouseEntered(MouseEvent e)
                    {
                        ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\CORRECT_over.png")));
                        ((JLabel) e.getSource()).repaint();

                    }
                });

                showChoices(startLabel);
            }
        });

        PickImage3.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {

                JLabel startLabel = new JLabel(new ImageIcon("images\\button\\CORRECT.png"));
                startLabel.addMouseListener(new MouseListener()
                {

                    public void mouseClicked(MouseEvent e)
                    {

                    }

                    public void mouseExited(MouseEvent e)
                    {

                        ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\CORRECT.png")));
                        ((JLabel) e.getSource()).repaint();
                    }

                    public void mousePressed(MouseEvent e)
                    {
                        ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\CORRECT_click.png")));
                        ((JLabel) e.getSource()).repaint();
                    }

                    public void mouseReleased(MouseEvent e)
                    {

                        ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\CORRECT.png")));
                        ((JLabel) e.getSource()).repaint();

                        player3Icon = getNameofIcon();

                        hideChoices();

                    }

                    public void mouseEntered(MouseEvent e)
                    {
                        ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\CORRECT_over.png")));
                        ((JLabel) e.getSource()).repaint();

                    }
                });

                showChoices(startLabel);
            }
        });

        PickImage4.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {

                JLabel startLabel = new JLabel(new ImageIcon("images\\button\\CORRECT.png"));

                startLabel.addMouseListener(new MouseListener()
                {

                    public void mouseClicked(MouseEvent e)
                    {

                    }

                    public void mouseExited(MouseEvent e)
                    {

                        ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\CORRECT.png")));
                        ((JLabel) e.getSource()).repaint();
                    }

                    public void mousePressed(MouseEvent e)
                    {
                        ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\CORRECT_click.png")));
                        ((JLabel) e.getSource()).repaint();
                    }

                    public void mouseReleased(MouseEvent e)
                    {

                        ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\CORRECT.png")));
                        ((JLabel) e.getSource()).repaint();

                        player4Icon = getNameofIcon();

                        hideChoices();

                    }

                    public void mouseEntered(MouseEvent e)
                    {
                        ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\CORRECT_over.png")));
                        ((JLabel) e.getSource()).repaint();

                    }
                });

                showChoices(startLabel);
            }
        });


        PickImage1.setBounds(330, 235, 150, 30);
        PickImage2.setBounds(330, 285, 150, 30);
        PickImage3.setBounds(330, 335, 150, 30);
        PickImage4.setBounds(330, 385, 150, 30);

        PickImage1.setEnabled(true);
        PickImage2.setEnabled(true);
        PickImage3.setEnabled(false);
        PickImage4.setEnabled(false);

        JLabel startLabel = new JLabel(new ImageIcon("images\\button\\CHECK.png"));
        startLabel.addMouseListener(new MouseListener()
        {

            public void mouseClicked(MouseEvent e)
            {
            }

            public void mouseExited(MouseEvent e)
            {

                ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\CHECK.png")));
                ((JLabel) e.getSource()).repaint();
            }

            public void mousePressed(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\CHECK_click.png")));
                ((JLabel) e.getSource()).repaint();
            }

            public void mouseReleased(MouseEvent e)
            {

                ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\CHECK.png")));
                ((JLabel) e.getSource()).repaint();

                printList();

                backGround.hidePlayBoard();

                GameBoardUI theGameBoard = new GameBoardUI(splash);
                theGameBoard.run();  //Thread Optimization
                theGameBoard.createAndShowGUI();

                exitMe();

            }

            public void mouseEntered(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\CHECK_over.png")));
                ((JLabel) e.getSource()).repaint();

            }
        });

        JLabel quitLabel = new JLabel(new ImageIcon("images\\button\\QUIT.png"));
        quitLabel.addMouseListener(new MouseListener()
        {

            public void mouseClicked(MouseEvent e)
            {
            }

            public void mouseExited(MouseEvent e)
            {

                ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\QUIT.png")));
                ((JLabel) e.getSource()).repaint();
            }

            public void mousePressed(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\QUIT_click.png")));
                ((JLabel) e.getSource()).repaint();
            }

            public void mouseReleased(MouseEvent e)
            {

                ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\QUIT.png")));
                ((JLabel) e.getSource()).repaint();

                backGround.hidePlayBoard();
                backGround.menuBoard.setVisible(true);
            }

            public void mouseEntered(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\button\\QUIT_over.png")));
                ((JLabel) e.getSource()).repaint();

            }
        });

        quitLabel.setBounds(45, 62, 55, 55);
        startLabel.setBounds(641, 359, 214, 145);

        JComboBox playerNum = new JComboBox(playerNumChoices);

        playerNum.setBackground(new Color(84, 83, 78));
        playerNum.setForeground(new Color(37, 125, 165));
        playerNum.setBorder(null);
        playerNum.setSelectedIndex(0);

        //Enable/Disable TextAreas
        playerNum.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                JComboBox cb = (JComboBox) e.getSource();
                int playerNumberInt = Integer.parseInt((String) cb.getSelectedItem());

                if (playerNumberInt == 2)
                {
                    textArea1.setEnabled(true);
                    textArea2.setEnabled(true);
                    textArea3.setEnabled(false);
                    textArea4.setEnabled(false);

                    textArea3.setValue(new String(Name1));
                    textArea4.setValue(new String(Name1));

                    PickImage1.setEnabled(true);
                    PickImage2.setEnabled(true);
                    PickImage3.setEnabled(false);
                    PickImage4.setEnabled(false);
                }

                if (playerNumberInt == 3)
                {
                    textArea1.setEnabled(true);
                    textArea2.setEnabled(true);
                    textArea3.setEnabled(true);
                    textArea4.setEnabled(false);

                    textArea4.setValue(new String(Name1));

                    PickImage1.setEnabled(true);
                    PickImage2.setEnabled(true);
                    PickImage3.setEnabled(true);
                    PickImage4.setEnabled(false);
                }

                if (playerNumberInt == 4)
                {
                    textArea1.setEnabled(true);
                    textArea2.setEnabled(true);
                    textArea3.setEnabled(true);
                    textArea4.setEnabled(true);

                    PickImage1.setEnabled(true);
                    PickImage2.setEnabled(true);
                    PickImage3.setEnabled(true);
                    PickImage4.setEnabled(true);
                }
                if (playerNumberInt == 5)
                {
                    textArea1.setEnabled(true);
                    textArea2.setEnabled(true);
                    textArea3.setEnabled(true);
                    textArea4.setEnabled(true);

                    PickImage1.setEnabled(true);
                    PickImage2.setEnabled(true);
                    PickImage3.setEnabled(true);
                    PickImage4.setEnabled(true);

                }

            }
        });

        playerNum.setBounds(50, 170, 426, 35);
        playerNum.setFont(new Font("Myriad Pro", Font.PLAIN, 14));

        innerShell.add(playerNum);
        innerShell.add(quitLabel);
        innerShell.add(startLabel);
        innerShell.add(textArea1);
        innerShell.add(textArea2);
        innerShell.add(textArea3);
        innerShell.add(textArea4);
        innerShell.add(PickImage1);
        innerShell.add(PickImage2);
        innerShell.add(PickImage3);
        innerShell.add(PickImage4);

        innerShell.setOpaque(false);

        outerShell.add(innerShell, new Integer(0));

    }

}
    



  
  
  