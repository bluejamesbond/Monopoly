package src.com.monopoly;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MusicUI
{

    public static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    public LayeredImagePanel panelTunes = new LayeredImagePanel("images\\bar\\tunes_back.png");
    public JPanel mediaIcons = new JPanel();
    public JPanel timePane = new JPanel(new FlowLayout(FlowLayout.LEFT));
    public ImagePanel currentSong = new ImagePanel(new ImageIcon("images\\bar\\overlay.png"));
    public ArrayList<Song> songs = new ArrayList<Song>();

    public Scanner kb;

    public JLabel artLabel = new JLabel();

    public JLabel playpause = new JLabel(new ImageIcon("images\\bar\\PAUSE.png"));
    public JLabel stop = new JLabel(new ImageIcon("images\\bar\\STOP.png"));
    public JLabel previous = new JLabel(new ImageIcon("images\\bar\\NEXT.png"));
    public JLabel next = new JLabel(new ImageIcon("images\\bar\\PREVIOUS.png"));
    public JLabel shuffle = new JLabel(new ImageIcon("images\\bar\\SHUFFLE.png"));
    public JLabel repeat = new JLabel(new ImageIcon("images\\bar\\REPEAT_hover.png"));

    public JLabel currentTitle = new JLabel("null", SwingConstants.RIGHT);
    public JLabel timeLabel = new JLabel("null", SwingConstants.CENTER);
    public JLabel maxTimeLabel = new JLabel("null", SwingConstants.CENTER);

    public boolean playButtonEnabled = false;

    public boolean shuffleButtonEnabled = false;
    public int repeatFullSingle = 1;

    public MusicController controller;

    public MusicUI()
    {
        populateViaXML();

        artLabel.setBounds(35, 56, 375, 101);
        artLabel.setIcon(songs.get(0).getAlbumArt());

        panelTunes.setPreferredSize(new Dimension(450, 410));
        panelTunes.setBounds(0, 20, 450, 410);
        panelTunes.add(artLabel);
        panelTunes.setLayout(null);
        panelTunes.setOpaque(false);

        addTimeLabel();
        addMaxTimeLabel();
        assembleList();
        constructListeners();
        constructFeatures();
        addNowPlaying();

        mediaIcons.setOpaque(false);

        timePane.setOpaque(false);
        timePane.setBounds(35, 30, 375, 20);

        panelTunes.add(timePane);

    }

    public void addNowPlaying()
    {
        currentSong.setLayout(null);
        currentSong.setBackground(Color.BLACK);
        currentSong.setPreferredSize(new Dimension(270, 15));
        currentSong.setBounds(139, 70, 270, 15);

        currentTitle.setVerticalTextPosition(JLabel.CENTER);
        currentTitle.setFont(new Font("Calibri", Font.PLAIN, 10));
        currentTitle.setForeground(Color.WHITE);
        currentTitle.validate();
        currentTitle.setBounds(1, 3, 264, 10);
        currentTitle.setText(songs.get(0).getAlbum() + "    " + songs.get(0).getArtist() + "    " + songs.get(0).getTitle());

        currentSong.add(currentTitle);

        panelTunes.add(currentSong, new Integer(5));

        panelTunes.validate();
        panelTunes.repaint();

    }

    public void addTimeLabel()
    {
        timeLabel.setVerticalTextPosition(JLabel.CENTER);
        timeLabel.setFont(new Font("Calibri", Font.PLAIN, 10));
        timeLabel.setForeground(new Color(37, 120, 159));
        timeLabel.validate();

        timePane.add(timeLabel);

        panelTunes.validate();
        panelTunes.repaint();

    }

    public void addMaxTimeLabel()
    {
        maxTimeLabel.setVerticalTextPosition(JLabel.CENTER);
        maxTimeLabel.setFont(new Font("Calibri", Font.PLAIN, 10));
        maxTimeLabel.setForeground(Color.WHITE);
        maxTimeLabel.validate();

        timePane.add(maxTimeLabel);

        panelTunes.validate();
        panelTunes.repaint();

    }

    public void updateNowPlaying(String a)
    {
        currentTitle.setText(a);

        panelTunes.validate();
        panelTunes.repaint();

    }

    public void assembleList()
    {
        int x = 35;
        int y = 205;

        for (int z = 0; z < songs.size(); z++)
        {
            songs.get(z).getPanel().setBounds(x, y, 375, 20);
            panelTunes.add(songs.get(z).getPanel());
            panelTunes.validate();
            panelTunes.repaint();
            y += 20;
        }
    }

    public JLayeredPane getPanel()
    {
        return panelTunes;
    }

    public void populateViaXML()
    {
        int count = 0;

        String check;
        String title;
        String artist;
        String album;
        String artLoc;
        String songLoc;
        String songMax;

        try
        {
            kb = new Scanner(new File("configuration_music.xml"));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "File Reader Error (Source: MusicUI.java [readConfigXML()])", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        while (kb.hasNext())
        {
            check = kb.nextLine();

            if (check.substring(0, 2).equals("//") == false)
            {
                String[] results = check.split(",\\s*");

                title = results[0];
                artist = results[1];
                album = results[2];
                artLoc = results[3];
                songLoc = results[4];
                songMax = results[5];

                songs.add(new Song(this, title, artist, album, new ImageIcon(artLoc), songMax, count));
                ((Song) songs.get(songs.size() - 1)).setFileLoc(songLoc);

                count++;
            }
        }

        kb.close();

        controller = new MusicController(this);
    }

    public void constructFeatures()
    {
        mediaIcons.setLayout(null);
        mediaIcons.setBackground(Color.BLACK);
        mediaIcons.setPreferredSize(new Dimension(375, 25));
        mediaIcons.setBounds(35, 171, 375, 25);

        playpause.setBounds(0, 2, 20, 20);
        stop.setBounds(25, 2, 20, 20);
        next.setBounds(50, 2, 20, 20);
        previous.setBounds(75, 2, 20, 20);
        shuffle.setBounds(330, 2, 20, 20);
        repeat.setBounds(355, 2, 20, 20);

        mediaIcons.add(playpause);
        mediaIcons.add(stop);
        mediaIcons.add(next);
        mediaIcons.add(previous);
        mediaIcons.add(shuffle);
        mediaIcons.add(repeat);

        panelTunes.add(mediaIcons);

        panelTunes.validate();
        panelTunes.repaint();

    }

    public void constructListeners()
    {
        playpause.addMouseListener(new MouseListener()
        {

            public void mouseClicked(MouseEvent e)
            {
            }

            public void mouseEntered(MouseEvent e)
            {
                if (playButtonEnabled)
                {
                    ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\PLAY_hover.png"));
                    ((JLabel) e.getSource()).repaint();
                }
                else
                {
                    ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\PAUSE_hover.png"));
                    ((JLabel) e.getSource()).repaint();
                }
            }

            public void mouseExited(MouseEvent e)
            {
                if (playButtonEnabled)
                {
                    ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\PLAY.png"));
                    ((JLabel) e.getSource()).repaint();
                }
                else
                {
                    ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\PAUSE.png"));
                    ((JLabel) e.getSource()).repaint();
                }
            }

            public void mousePressed(MouseEvent e)
            {
                if (playButtonEnabled)
                {
                    ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\PLAY_hover.png"));
                    ((JLabel) e.getSource()).repaint();

                    controller.play();
                }
                else
                {
                    ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\PAUSE_hover.png"));
                    ((JLabel) e.getSource()).repaint();

                    controller.stop();
                }
            }

            public void mouseReleased(MouseEvent e)
            {

                if (playButtonEnabled)
                {
                    playButtonEnabled = false;
                }
                else
                {
                    playButtonEnabled = true;
                }
                if (playButtonEnabled)
                {
                    ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\PLAY.png"));
                    ((JLabel) e.getSource()).repaint();
                }
                else
                {
                    ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\PAUSE.png"));
                    ((JLabel) e.getSource()).repaint();
                }
            }
        });

        stop.addMouseListener(new MouseListener()
        {

            public void mouseClicked(MouseEvent e)
            {
            }

            public void mouseEntered(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\STOP_hover.png"));
                ((JLabel) e.getSource()).repaint();
            }

            public void mouseExited(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\STOP.png"));
                ((JLabel) e.getSource()).repaint();
            }

            public void mousePressed(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\STOP_hover.png"));
                ((JLabel) e.getSource()).repaint();
            }

            public void mouseReleased(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\STOP.png"));
                ((JLabel) e.getSource()).repaint();

                controller.stopPlayBack();
                controller.reset();

                playButtonEnabled = true;

                playpause.setIcon(new ImageIcon("images\\bar\\PLAY.png"));
                playpause.repaint();
            }
        });

        previous.addMouseListener(new MouseListener()
        {

            public void mouseClicked(MouseEvent e)
            {
            }

            public void mouseEntered(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\NEXT_hover.png"));
                ((JLabel) e.getSource()).repaint();
            }

            public void mouseExited(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\NEXT.png"));
                ((JLabel) e.getSource()).repaint();
            }

            public void mousePressed(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\NEXT_hover.png"));
                ((JLabel) e.getSource()).repaint();
            }

            public void mouseReleased(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\NEXT.png"));
                ((JLabel) e.getSource()).repaint();
            }
        });

        next.addMouseListener(new MouseListener()
        {

            public void mouseClicked(MouseEvent e)
            {
            }

            public void mouseEntered(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\PREVIOUS_hover.png"));
                ((JLabel) e.getSource()).repaint();
            }

            public void mouseExited(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\PREVIOUS.png"));
                ((JLabel) e.getSource()).repaint();
            }

            public void mousePressed(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\PREVIOUS_hover.png"));
                ((JLabel) e.getSource()).repaint();
            }

            public void mouseReleased(MouseEvent e)
            {
                ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\PREVIOUS.png"));
                ((JLabel) e.getSource()).repaint();
            }
        });

        shuffle.addMouseListener(new MouseListener()
        {

            public void mouseClicked(MouseEvent e)
            {
            }

            public void mouseEntered(MouseEvent e)
            {
                if (!shuffleButtonEnabled)
                {
                    ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\SHUFFLE_hover.png"));
                    ((JLabel) e.getSource()).repaint();
                }
            }

            public void mouseExited(MouseEvent e)
            {
                if (!shuffleButtonEnabled)
                {
                    ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\SHUFFLE.png"));
                    ((JLabel) e.getSource()).repaint();
                }
            }

            public void mousePressed(MouseEvent e)
            {

                shuffleButtonEnabled = !shuffleButtonEnabled;

                if (!shuffleButtonEnabled)
                {
                    ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\SHUFFLE_hover.png"));
                    ((JLabel) e.getSource()).repaint();
                }
                if (shuffleButtonEnabled)
                {
                    if (repeatFullSingle % 3 == 2)
                    {
                        repeatFullSingle = 0;

                        repeat.setIcon(new ImageIcon("images\\bar\\REPEAT.png"));
                        repeat.repaint();
                    }
                }
            }

            public void mouseReleased(MouseEvent e)
            {
                if (!shuffleButtonEnabled)
                {
                    ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\SHUFFLE.png"));
                    ((JLabel) e.getSource()).repaint();
                }
            }
        });

        repeat.addMouseListener(new MouseListener()
        {

            public void mouseClicked(MouseEvent e)
            {
            }

            public void mouseEntered(MouseEvent e)
            {
                if (repeatFullSingle % 3 == 0)
                {
                    ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\REPEAT_hover.png"));
                    ((JLabel) e.getSource()).repaint();
                }
            }

            public void mouseExited(MouseEvent e)
            {
                if (repeatFullSingle % 3 == 0)
                {
                    ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\REPEAT.png"));
                    ((JLabel) e.getSource()).repaint();
                }
            }

            public void mousePressed(MouseEvent e)
            {

                repeatFullSingle++;

                if (repeatFullSingle % 3 == 1)
                {
                    ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\REPEAT_hover.png"));
                    ((JLabel) e.getSource()).repaint();
                }
                if (repeatFullSingle % 3 == 2)
                {
                    ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\REPEAT_hover_single.png"));
                    ((JLabel) e.getSource()).repaint();

                    shuffleButtonEnabled = false;

                    shuffle.setIcon(new ImageIcon("images\\bar\\SHUFFLE.png"));
                    shuffle.repaint();
                }
            }

            public void mouseReleased(MouseEvent e)
            {
                if (repeatFullSingle % 3 == 0)
                {
                    ((JLabel) e.getSource()).setIcon(new ImageIcon("images\\bar\\REPEAT.png"));
                    ((JLabel) e.getSource()).repaint();
                }
            }
        });
    }

    public void updateTime(int x)
    {
        if (x / 10 == 0)
        {
            timeLabel.setText(x / 60 + ":0" + x % 60);
            timePane.validate();
            timePane.repaint();
        }
        else
        {
            timeLabel.setText(x / 60 + ":" + x % 60);
            timePane.validate();
            timePane.repaint();
        }
    }

    public void setMaxTime(String a)
    {
        maxTimeLabel.setText(a);
        timePane.validate();
        timePane.repaint();
    }

    public void setIconToPlay()
    {
        playButtonEnabled = true;
        playpause.setIcon(new ImageIcon("images\\bar\\PLAY.png"));
        playpause.repaint();
    }

    public void setIconToPause()
    {
        playButtonEnabled = false;
        playpause.setIcon(new ImageIcon("images\\bar\\PAUSE.png"));
        playpause.repaint();
    }
}
