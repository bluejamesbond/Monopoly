package src.com.monopoly;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Song
{

    public int number = 0;

    public String title = "title";
    public String artist = "artist";
    public String album = "album";
    public String artLoc = "fileLoc";
    public String fileLoc = "fileLoc";
    public String songMax = "0";

    public ImageIcon art = new ImageIcon();

    public JLabel dataLabel = new JLabel((number + 1) + ".  " + title + "     " + artist + "    " + album);

    public LayeredImagePanel holder = new LayeredImagePanel("images\\bar\\tunes_norm.png");

    public boolean updateFont = true;

    public MusicUI music;
    public Song song = this;

    public Song(MusicUI m, String a, String b, String c, ImageIcon i, String s, int n)
    {
        music = m;
        title = a;
        artist = b;
        album = c;
        songMax = s;
        art = i;
        number = n;

        holder.setLayout(null);
        holder.setBackground(Color.BLACK);
        holder.setPreferredSize(new Dimension(375, 20));

        dataLabel.setText((number + 1) + ".  " + title + "     " + artist + "    " + album);
        dataLabel.repaint();
        dataLabel.setBounds(1, 3, 360, 13);

        dataLabel.setHorizontalTextPosition(JLabel.LEFT);
        dataLabel.setVerticalTextPosition(JLabel.CENTER);
        dataLabel.setFont(new Font("Calibri", Font.PLAIN, 11));
        dataLabel.setForeground(Color.WHITE);
        dataLabel.validate();

        dataLabel.repaint();

        holder.add(dataLabel);

        holder.addMouseListener(new MouseListener()
        {

            public void mouseClicked(MouseEvent arg0)
            {
            }

            public void mouseEntered(MouseEvent arg0)
            {
                holder.changeImage(new ImageIcon("images\\bar\\tunes_highlight.png"));
                holder.repaint();
                applyFont();
            }

            public void mouseExited(MouseEvent arg0)
            {
                holder.changeImage(new ImageIcon("images\\bar\\tunes_norm.png"));
                holder.repaint();
                if (updateFont)
                {
                    resetFont();
                }

            }

            public void mousePressed(MouseEvent arg0)
            {
            }

            public void mouseReleased(MouseEvent arg0)
            {
                music.controller.specificTrack(number);
                music.setIconToPause();
            }
        });
    }

    public void setFileLoc(String file)
    {
        fileLoc = file;
    }

    public void applyFont()
    {
        dataLabel.setHorizontalTextPosition(JLabel.LEFT);
        dataLabel.setVerticalTextPosition(JLabel.CENTER);
        dataLabel.setFont(new Font("Calibri", Font.PLAIN, 11));
        dataLabel.setForeground(new Color(37, 120, 159));
        dataLabel.validate();
        dataLabel.repaint();
    }

    public void resetFont()
    {
        dataLabel.setHorizontalTextPosition(JLabel.LEFT);
        dataLabel.setVerticalTextPosition(JLabel.CENTER);
        dataLabel.setFont(new Font("Calibri", Font.PLAIN, 11));
        dataLabel.setForeground(Color.WHITE);
        dataLabel.validate();
        dataLabel.repaint();
    }

    public void forceEnabled()
    {
        music.updateNowPlaying(album + "    " + artist + "    " + title);
        music.setMaxTime(songMax);

        updateFont = false;

        applyFont();
        resetListHighlights();
    }

    public String getTitle()
    {
        return title;
    }

    public String getArtist()
    {
        return artist;
    }

    public String getAlbum()
    {
        return album;
    }

    public String getArtLoc()
    {
        return artLoc;
    }

    public LayeredImagePanel getPanel()
    {
        return holder;
    }

    public ImageIcon getAlbumArt()
    {
        return art;
    }

    public void resetListHighlights()
    {
        for (int x = 0; x < music.songs.size(); x++)
        {
            if (song != music.songs.get(x))
            {
                music.artLabel.setIcon(song.getAlbumArt());
                music.songs.get(x).resetFont();
                music.songs.get(x).updateFont = true;
            }
        }
    }
}
