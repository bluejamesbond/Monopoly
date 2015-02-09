package src.com.monopoly; /**
 * @(#)HideFrameTask.java
 *
 *
 * @author
 * @version 1.00 2010/3/1
 */

import javax.swing.*;
import java.awt.*;
import java.util.TimerTask;

class HideWindow extends TimerTask
{
    Window comp;

    public HideWindow(JDialog a)
    {
        this.comp = (Window) a;
    }

    public HideWindow(JFrame a)
    {
        this.comp = (Window) a;
    }

    public void run()
    {
        comp.setVisible(false);
        comp = null;
    }

    public void hideNow()
    {
        comp.setVisible(false);
        comp = null;
    }
}