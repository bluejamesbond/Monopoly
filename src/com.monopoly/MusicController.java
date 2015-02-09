package src.com.monopoly;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;


public class MusicController
{

    public MusicUI music;

    public ArrayList<Integer> playOrder = new ArrayList<Integer>();

    public int currentIndex = 0;
    public int ShuffleNoRepeat = 0;
    public int NoShuffleNoRepeat = 0;

    public MusicPlayer backMusic;

    public MusicController(MusicUI m)
    {
        music = m;

        while (playOrder.size() != music.songs.size())
        {
            int random = (int) (Math.random() * music.songs.size());

            if (!playOrder.contains(random))
            {
                playOrder.add(random);

            }
        }

        System.out.println(playOrder);

        firstTrack();
    }

    public void firstTrack()
    {
        backMusic = new MusicPlayer(music);

        int startIndex = 0;

        music.setMaxTime(music.songs.get(startIndex).songMax);
        music.songs.get(startIndex).forceEnabled();

        try
        {
            backMusic.playMusic("file:" + music.songs.get(startIndex).fileLoc);
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (UnsupportedAudioFileException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (LineUnavailableException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (MidiUnavailableException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (InvalidMidiDataException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        backMusic.play();

        currentIndex = playOrder.indexOf(startIndex);

        System.out.println(currentIndex);
    }

    public void controlMusic()
    {

        //0 = none
        //1 = all
        //2 = single

        if (music.shuffleButtonEnabled == true && music.repeatFullSingle % 3 == 1)
        {
            System.out.println("Choice 1");

            currentIndex++;
            playMusic(playOrder.get(currentIndex % (playOrder.size())));

            ShuffleNoRepeat = 0;
            NoShuffleNoRepeat = 0;
        }
        if (music.shuffleButtonEnabled == false && music.repeatFullSingle % 3 == 1)
        {
            int nowPlaying = 0;

            for (int x = 0; x < music.songs.size(); x++)
            {
                if (!music.songs.get(x).updateFont)
                {
                    nowPlaying = x;
                }
            }

            specificTrack(new Integer((int) ((nowPlaying + 1) % (playOrder.size()))));

            ShuffleNoRepeat = 0;
            NoShuffleNoRepeat = 0;
        }
        if (music.shuffleButtonEnabled == false && music.repeatFullSingle % 3 == 2)
        {
            System.out.println("Choice 3");

            backMusic.reset();
            backMusic.play();

            ShuffleNoRepeat = 0;
            NoShuffleNoRepeat = 0;
        }
        if (music.shuffleButtonEnabled == true && music.repeatFullSingle % 3 == 0)
        {
            System.out.println("Choice 4");

            ShuffleNoRepeat++;

            if (ShuffleNoRepeat < (playOrder.size()))
            {
                currentIndex++;
                playMusic(playOrder.get(currentIndex % (playOrder.size())));
            }

            else
            {
                backMusic.stop();
                music.setIconToPlay();

                music.songs.get(currentIndex % (playOrder.size())).updateFont = true;
                music.songs.get(currentIndex % (playOrder.size())).resetFont();
                music.songs.get(currentIndex % (playOrder.size())).resetListHighlights();
            }

            NoShuffleNoRepeat = 0;

        }
        if (music.shuffleButtonEnabled == false && music.repeatFullSingle % 3 == 0)
        {
            int nowPlaying = 0;

            NoShuffleNoRepeat++;

            for (int x = 0; x < music.songs.size(); x++)
            {
                if (!music.songs.get(x).updateFont)
                {
                    nowPlaying = x;
                }
            }
            if (NoShuffleNoRepeat < (playOrder.size()))
            {
                playMusic(playOrder.get(nowPlaying % (playOrder.size()) + 1));

            }
            else
            {
                backMusic.stop();
                music.setIconToPlay();

                music.songs.get(nowPlaying).updateFont = true;
                music.songs.get(nowPlaying).resetFont();
                music.songs.get(nowPlaying).resetListHighlights();
            }

            ShuffleNoRepeat = 0;
        }
    }

    public void specificTrack(int x)
    {
        music.songs.get(x).forceEnabled();

        backMusic.stopPlayBack();
        backMusic.clearMem();

        try
        {
            backMusic.playMusic("file:" + music.songs.get(x).fileLoc);
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (UnsupportedAudioFileException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (LineUnavailableException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (MidiUnavailableException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (InvalidMidiDataException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        backMusic.reset();

        backMusic.play();

        NoShuffleNoRepeat = x--;
        //ShuffleNoRepeat = x++;
        currentIndex = playOrder.indexOf(x);
    }

    public void playMusic(int x)
    {
        music.songs.get(x).forceEnabled();

        backMusic.stopPlayBack();

        backMusic.clearMem();

        try
        {
            backMusic.playMusic("file:" + music.songs.get(x).fileLoc);
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (UnsupportedAudioFileException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (LineUnavailableException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (MidiUnavailableException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (InvalidMidiDataException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        backMusic.reset();

        backMusic.play();

    }

    public void play()
    {
        backMusic.play();
    }

    public void stop()
    {
        backMusic.stop();
    }

    public void reset()
    {
        backMusic.reset();
    }

    public void stopPlayBack()
    {
        backMusic.stopPlayBack();
    }

}
