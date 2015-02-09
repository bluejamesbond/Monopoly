package src.com.monopoly;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.Timer;
import java.util.TimerTask;


public class OptionPane
{

    public JDialog frameDetection = new JDialog();
    public ImagePanel imgPanelDetection = new ImagePanel("images\\warning\\detection.png");
    public TransparentJDiag transBackDetection = new TransparentJDiag(frameDetection);

    public JDialog frameProgress = new JDialog();
    public ImagePanel imgPanelProgress = new ImagePanel("images\\progress\\progress.png");
    public TransparentJDiag transBackProgress = new TransparentJDiag(frameProgress);

    public JLabel nextLabel = new JLabel(new ImageIcon(("images\\warning\\CHECK.png")));
    public JLabel quitLabel = new JLabel(new ImageIcon(("images\\warning\\QUIT.png")));

    public JProgressBar progressBar = new JProgressBar(0, 100);

    public OptionPane()
    {
    }

    public void showDetection(String a)
    {
        setUpDetection(a);
        createAndShowGUIDetection();
    }

    public void showProgress()
    {
        setUpProgress();
        createAndShowGUIProgress();
    }

    public void setUpDetection(String a)
    {
        imgPanelDetection.setOpaque(false);
        imgPanelDetection.setBounds(0, 0, 710, 395);

        transBackDetection.add(imgPanelDetection);

        quitLabel.addMouseListener(new MouseListener()
        {

            public void mouseClicked(MouseEvent e)
            {
            }

            public void mouseExited(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\warning\\QUIT.png")));
                ((JLabel) e.getSource()).repaint();
            }

            public void mousePressed(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\warning\\QUIT_over.png")));
                ((JLabel) e.getSource()).repaint();
            }

            public void mouseReleased(MouseEvent e)
            {

                System.exit(0);
            }

            public void mouseEntered(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon(("images\\warning\\QUIT_over.png")));
                ((JLabel) e.getSource()).repaint();
            }
        });
        nextLabel.setFont(new Font("Calibri", Font.BOLD, 18));
        nextLabel.setText("10");
        nextLabel.setHorizontalTextPosition(JLabel.CENTER);
        nextLabel.setVerticalTextPosition(JLabel.CENTER);


        quitLabel.setBounds(280, 205, 180, 80);
        nextLabel.setBounds(158, 205, 180, 80);

        JTextArea text = new JTextArea();
        text.setEditable(false);
        text.setLineWrap(true);
        text.setOpaque(false);
        text.setFont(new Font("Calibri", Font.BOLD, 12));
        text.setText(a);

        JScrollPane scroll = new JScrollPane(text);
        scroll.setOpaque(false);
        scroll.setBorder(null);
        scroll.getViewport().setOpaque(false);
        scroll.setBounds(26, 99, 542, 140);

        imgPanelDetection.add(quitLabel);
        imgPanelDetection.add(nextLabel);
        imgPanelDetection.add(scroll);
    }

    public void setUpProgress()
    {
        imgPanelProgress.setOpaque(false);
        imgPanelProgress.setBounds(0, 0, 740, 430);

        progressBar.setBounds(30, 95, 670, 55);
        progressBar.setForeground(new Color(37, 128, 165));
        progressBar.setBorder(null);
        progressBar.setOpaque(false);

        imgPanelProgress.add(progressBar);

        transBackProgress.add(imgPanelProgress);
    }

    public void createAndShowGUIDetection()
    {
        frameDetection.setUndecorated(true);
        frameDetection.addMouseMotionListener(new MouseListenerClassOptionPaneDetection());
        frameDetection.setSize(710, 395);
        frameDetection.setContentPane(transBackDetection);
        frameDetection.setLocationRelativeTo(null);
        frameDetection.setVisible(true);
        transBackDetection.refresh();
    }

    public void createAndShowGUIProgress()
    {
        frameProgress.setUndecorated(true);
        frameProgress.addMouseMotionListener(new MouseListenerClassOptionPaneProgress());
        frameProgress.setSize(740, 430);
        frameProgress.setContentPane(transBackProgress);
        frameProgress.setLocationRelativeTo(null);
        frameProgress.setVisible(true);
        transBackProgress.refresh();
    }

    public void initiateTimerDetection()
    {
        Timer countDown = new Timer();
        countDown.scheduleAtFixedRate(new TimerTask()
        {

            int countDown = 10;

            public void run()
            {
                while (countDown > 0)
                {
                    nextLabel.setText("" + countDown);
                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    countDown--;
                }
            }

        }, 0, 10);
    }

    public void initiateTimerProgress()
    {
        Timer countDown = new Timer();
        countDown.scheduleAtFixedRate(new TimerTask()
        {

            int countDown = 0;

            public void run()
            {
                while (countDown <= 100)
                {
                    progressBar.setValue(countDown);
                    try
                    {
                        Thread.sleep(250);
                    }
                    catch (InterruptedException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    countDown++;
                }
            }

        }, 0, 10);
    }

    class MouseListenerClassOptionPaneDetection extends MouseMotionAdapter
    {
        public void mouseDragged(MouseEvent E)
        {
            frameDetection.setLocation(MouseInfo.getPointerInfo().getLocation().x - 100, MouseInfo.getPointerInfo().getLocation().y - 100);
        }
    }

    class MouseListenerClassOptionPaneProgress extends MouseMotionAdapter
    {
        public void mouseDragged(MouseEvent E)
        {
            frameProgress.setLocation(MouseInfo.getPointerInfo().getLocation().x - 100, MouseInfo.getPointerInfo().getLocation().y - 100);
        }
    }
}
