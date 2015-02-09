package src.com.monopoly;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class MusicPlayer
{

    /**
     *
     */
    private static final long serialVersionUID = 2950950584744816854L;
    public ModJSlider progress = new ModJSlider(0, 10, 0);
    public ArrayList<JSlider> sliders = new ArrayList<JSlider>();
    public ModJSlider vol = new ModJSlider(0, 1000);
    public ModJSlider pan = new ModJSlider(0, 1000);
    Clip clip; // Contents of a sampled audio file
    ; // Shows and sets current position in sound
    boolean playing = false; // whether the sound is current playing
    int audioLength; // Length of the sound.
    int audioPosition = 0; // Current position within the sound
    JLabel time; // Displays audioPosition as a number
    Timer timer; // Updates slider every 100 milliseconds
    MusicUI music;
    AudioInputStream ain;
    FloatControl gainControl;
    FloatControl panControl;

    public MusicPlayer(MusicUI m)
    {
        music = m;
    }

    public void playMusic(String a) throws IOException, UnsupportedAudioFileException,
            LineUnavailableException, MidiUnavailableException, InvalidMidiDataException
    {

        URL url = new URL(a);
        File f = new File(url.getPath());

        ain = AudioSystem.getAudioInputStream(f);

        try
        {
            DataLine.Info info = new DataLine.Info(Clip.class, ain.getFormat());
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(ain);
        }
        finally
        {
            ain.close();
        }

        addSampledControls();

        // Get the clip length in microseconds and convert to milliseconds
        audioLength = (int) (clip.getMicrosecondLength() / 1000);

        time = new JLabel("0"); // Shows position as a #

        progress.setMaximum(audioLength);

        progress.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent e)
            {

                int value = progress.getValue();
                progress.setValue(value);

                if (value != audioPosition)
                {
                    skip(value);
                }
            }
        });

        sliders.add(progress);

        // This timer calls the tick() method 10 times a second to keep
        // our slider in sync with the music.
        timer = new Timer(100, new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                tick();
            }
        });
    }

    public void stopPlayBack()
    {
        if (playing)
            stop();
        else
            play();
    }

    /**
     * Start playing the sound at the current position
     */
    public void play()
    {
        clip.start();
        timer.start();
        playing = true;
    }

    /**
     * Stop playing the sound, but retain the current position
     */
    public void stop()
    {
        timer.stop();
        clip.stop();
        playing = false;
    }

    /**
     * Stop playing the sound and reset the position to 0
     */
    public void reset()
    {
        stop();
        clip.setMicrosecondPosition(0);
        audioPosition = 0;
        progress.setValue(0);
    }

    /**
     * Skip to the specified position
     */
    public void skip(int position)
    { // Called when user drags the slider
        if (position < 0 || position > audioLength)
            return;
        audioPosition = position;
        clip.setMicrosecondPosition(position * 1000);
        progress.setValue(position);
        // in case skip() is called from outside
    }

    /**
     * Return the length of the sound in ms or ticks
     */
    public int getLength()
    {
        return audioLength;
    }

    public void tick()
    {
        if (true && clip.isActive())
        {
            audioPosition = (int) (clip.getMicrosecondPosition() / 1000);
            music.updateTime(audioPosition / 1000);
            progress.setValue(audioPosition);
        }
        else
        {
            music.controller.controlMusic();
        }
    }

    public void addSampledControls()
    {
        try
        {
            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            if (gainControl != null)
                sliders.add(createVolume(gainControl));
        }
        catch (IllegalArgumentException e)
        {
        }

        try
        {
            panControl = (FloatControl) clip.getControl(FloatControl.Type.PAN);
            if (panControl != null)
                sliders.add(createPan(panControl));
        }
        catch (IllegalArgumentException e)
        {
        }
    }

    public ModJSlider createVolume(final FloatControl c)
    {

        if (c == null)
            return null;

        final float min = c.getMinimum();
        final float max = c.getMaximum();
        final float width = max - min;
        float fval = c.getValue();
        vol.setValue((int) ((fval - min) / width * 1000));

        vol.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent e)
            {
                int i = vol.getValue();
                float f = min + (i * width / 1000.0f);
                c.setValue(f);
            }
        });

        return vol;
    }

    public ModJSlider createPan(final FloatControl c)
    {

        if (c == null)
            return null;

        final float min = c.getMinimum();
        final float max = c.getMaximum();
        final float width = max - min;
        float fval = c.getValue();
        pan.setValue((int) ((fval - min) / width * 1000));

        pan.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent e)
            {
                int i = pan.getValue();
                float f = min + (i * width / 1000.0f);
                c.setValue(f);
            }
        });

        return pan;
    }

    public ArrayList<JSlider> getControls()
    {
        return sliders;

        //0==Position
        //1==Volume
        //2==Pan

    }

    public void clearMem()
    {
        stop();

        try
        {
            ain.close();
        }
        catch (IOException e)
        {
        }

        ain = null;
    }

    public void panRight()
    {
        pan.setValue(pan.getValue() + 100);

        int i = pan.getValue();

        final float min = panControl.getMinimum();
        final float max = panControl.getMaximum();
        final float width = max - min;

        panControl.setValue((min + (i * width / 1000.0f)));
    }

    public void panCenter()
    {
        pan.setValue(pan.getMaximum() / 2);

        int i = pan.getValue();

        final float min = panControl.getMinimum();
        final float max = panControl.getMaximum();
        final float width = max - min;

        panControl.setValue((min + (i * width / 1000.0f)));
    }

    public void panLeft()
    {
        pan.setValue(pan.getValue() - 100);

        int i = pan.getValue();

        final float min = panControl.getMinimum();
        final float max = panControl.getMaximum();
        final float width = max - min;

        panControl.setValue((min + (i * width / 1000.0f)));
    }
}
