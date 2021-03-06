package src.com.monopoly; /**
 * @(#)AePlayWave.java
 *
 *
 * @Mathew Kurian
 * @version 1.00 2010/2/11
 */

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class AePlayWave extends Thread
{

    private final int EXTERNAL_BUFFER_SIZE = 524288; // 128Kb
    public String filename;
    public AudioFormat format;
    public SourceDataLine auline;
    public DataLine.Info info;
    public File soundFile;
    public boolean stop;
    private URL url;
    private Position curPosition;

    public AePlayWave()
    {

    }

    ;

    public AePlayWave(String wavfile)
    {
        try
        {
            this.url = new URL(wavfile);
        }
        catch (java.net.MalformedURLException e)
        {
            System.out.println(e.getMessage());
        }
        curPosition = Position.NORMAL;
    }

    public AePlayWave(String wavfile, Position p)
    {
        try
        {
            this.url = new URL(wavfile);
        }
        catch (java.net.MalformedURLException e)
        {
            System.out.println(e.getMessage());
        }
        curPosition = Position.NORMAL;

        curPosition = p;
    }

    public void setFile(String wavfile, boolean a)
    {
        try
        {
            this.url = new URL(wavfile);
        }
        catch (java.net.MalformedURLException e)
        {
            System.out.println(e.getMessage());
        }

        stop = false;
    }

    public void run()
    {

        soundFile = new File(url.getPath());
        if (soundFile.exists() && stop == false)
        {

            AudioInputStream audioInputStream = null;
            try
            {
                audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            }
            catch (UnsupportedAudioFileException e1)
            {
                e1.printStackTrace();
                return;
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
                return;
            }

            format = audioInputStream.getFormat();
            auline = null;
            info = new DataLine.Info(SourceDataLine.class, format);

            try
            {
                auline = (SourceDataLine) AudioSystem.getLine(info);
                auline.open(format);
            }
            catch (LineUnavailableException e)
            {
                e.printStackTrace();
                return;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return;
            }

            if (auline.isControlSupported(FloatControl.Type.PAN))
            {
                FloatControl pan = (FloatControl) auline
                        .getControl(FloatControl.Type.PAN);
                if (curPosition == Position.RIGHT)
                    pan.setValue(1.0f);
                else if (curPosition == Position.LEFT)
                    pan.setValue(-1.0f);
            }

            auline.start();

            int nBytesRead = 0;
            byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];

            try
            {
                while (nBytesRead != -1)
                {
                    nBytesRead = audioInputStream.read(abData, 0, abData.length);
                    if (nBytesRead >= 0)
                        auline.write(abData, 0, nBytesRead);
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
                return;
            }
            finally
            {
                auline.drain();
                auline.close();
                run(); //Repeat
            }
        }
    }

    public void runOnce()
    {

        soundFile = new File(url.getPath());
        if ((soundFile.exists()) && (stop == false))
        {

            AudioInputStream audioInputStream = null;
            try
            {
                audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            }
            catch (UnsupportedAudioFileException e1)
            {
                e1.printStackTrace();
                return;
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
                return;
            }

            format = audioInputStream.getFormat();
            auline = null;
            info = new DataLine.Info(SourceDataLine.class, format);

            try
            {
                auline = (SourceDataLine) AudioSystem.getLine(info);
                auline.open(format);
            }
            catch (LineUnavailableException e)
            {
                e.printStackTrace();
                return;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return;
            }

            if (auline.isControlSupported(FloatControl.Type.PAN))
            {
                FloatControl pan = (FloatControl) auline
                        .getControl(FloatControl.Type.PAN);
                if (curPosition == Position.RIGHT)
                    pan.setValue(1.0f);
                else if (curPosition == Position.LEFT)
                    pan.setValue(-1.0f);
            }

            auline.start();

            int nBytesRead = 0;
            byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];

            try
            {
                while (nBytesRead != -1)
                {
                    nBytesRead = audioInputStream.read(abData, 0, abData.length);
                    if (nBytesRead >= 0)
                        auline.write(abData, 0, nBytesRead);
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
                return;
            }
            finally
            {
                auline.drain();
                auline.close();
            }
        }
    }

    public void stopAuline()
    {
        try
        {
            auline.close();
        }
        catch (NullPointerException n)
        {

        }
    }

    public void setStop()
    {
        stop = true;
    }

    public void changeMusic(String wavfile)
    {
        try
        {
            this.url = new URL(wavfile);
        }
        catch (java.net.MalformedURLException e)
        {
            System.out.println(e.getMessage());
        }
        curPosition = Position.NORMAL;

        soundFile = new File(url.getPath());

        stopAuline();
        run();

    }

    enum Position
    {
        LEFT, RIGHT, NORMAL
    }


}
