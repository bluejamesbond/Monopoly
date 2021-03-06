package src.com.monopoly; /**
 * @(#)GameBoard.java
 *
 *
 * @Mathew Kurian
 * @version 1.00 2010/2/18
 */

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class MKEngine
{

    public ArrayList<Player> playerList;
    public int currentPLAYERNUM = 0;
    public JLabel PlayerGlassMoneyLabel = null;
    public Timer timer;
    public String tempAmount;
    public int startAmount = 0;
    public int endAmount = 0;
    public int delay = 0;
    public int period = 25;
    public Integer tempIntofPlayer;
    public GameBoardUI gameUI;
    PropertyReader reader = new PropertyReader();
    ArrayList<String> name = null;
    ArrayList<String> group = null;
    ArrayList<Integer> position = null;
    ArrayList<Integer> price = null;
    ArrayList<Integer> rent = null;
    JLabel[] PlayerMoneyList = null;
    PropJLabel[] PlayerPropList = null;
    HashMap<Integer, ArrayList<Player>> ProptertiesBought = new HashMap<Integer, ArrayList<Player>>();

    public MKEngine()
    {
        //Read in board cost, etc

        reader.populateAll();
        name = reader.getNameArrayList();
        group = reader.getGroupArrayList();
        position = reader.getPositonArrayList();
        price = reader.getPriceArrayList();
        rent = reader.getRentArrayList();

        playerList = new ArrayList<Player>();

        for (int x = 0; x < 40; x++)
        {
            ProptertiesBought.put(x, new ArrayList<Player>());
        }
    }

    public int getPrice(int position)
    {
        return price.get(position);
    }

    public HashMap<Integer, ArrayList<Player>> getPropertiesBought()
    {
        return ProptertiesBought;
    }

    public void findAndBehave(Player p, int x, int c)
    {
        currentPLAYERNUM = c;
        //Check position see if it is a shop. If it is subtract from the player's account
        if ((group.get(x).equalsIgnoreCase("NA") == false) && ((ArrayList<Player>) ProptertiesBought.get(x)).isEmpty())
        {
            p.subAccount(price.get(x));
            p.addPropertiesBought(x);
            p.addPropertiesBoughtColor(group.get(x));
            playerBoughtShop(x, p);
            setPMoney(p); //Added for updating PlayerAccount Instantly for GUI purposes
            setAndRefreshPProp();

            System.out.println("Account: " + p.getAccount());
            System.out.println("Color Added: " + group.get(x));
        }
        else if ((group.get(x).equalsIgnoreCase("NA") == false) && ProptertiesBought.get(x).get(0) != null)
        {
            if (((Player) ProptertiesBought.get(x).get(0)).getHouses(x) == 0)
            {
                ((Player) ProptertiesBought.get(x).get(0)).addAccount(rent.get(x));  //Player which Owns the Shop

                p.subAccount(rent.get(x)); //Player Which Lands on Bought Spot
            }

            else
            {
                ((Player) ProptertiesBought.get(x).get(0)).addAccount(rent.get(x) * ((((Player) ProptertiesBought.get(x).get(0)).getHouses(x) * 2) + 1));  //Player which Owns the Shop

                p.subAccount(rent.get(x) * ((((Player) ProptertiesBought.get(x).get(0)).getHouses(x) * 2) + 1)); //Player Which Lands on Bought Spot
            }

            setPMoney(p); //Added for updating PlayerAccount Instantly for GUI purposes
            setAndRefreshPProp();

            setPMoney((Player) ProptertiesBought.get(x).get(0)); //Added for updating PlayerAccount Instantly for GUI purposes
            setAndRefreshPProp();

            System.out.println("Account: " + p.getAccount());
            System.out.println("Color Added: " + group.get(x));
        }

    }

    public void playerBoughtShop(int postion, Player p)
    {
        System.out.println("Added Player to Properties Bought List***");
        ProptertiesBought.get(postion).add(p);
    }

    public void tradeDeeds(int index1, int index2, int main, int secondary)
    {
        Player temp1 = (Player) ProptertiesBought.get(index1).get(0);
        Player temp2 = (Player) ProptertiesBought.get(index2).get(0);

        ProptertiesBought.get(index1).set(0, temp2);
        ProptertiesBought.get(index2).set(0, temp1);

        gameUI.bank.addHistory(temp1.getNum(), "Sucessfully traded " + name.get(index1) + " for " + name.get(index2));
    }

    public void setLabeltoUpdate(PropJLabel[] p, JLabel[] m)
    {
        PlayerMoneyList = m;
        PlayerPropList = p;
    }

    public void setGameBoardUI(GameBoardUI g)
    {
        gameUI = g;
    }

    public void reduceHouseAmount(Player p, int x)
    {
        p.subAccount(100 * x);

        setPMoney(p); //Added for updating PlayerAccount Instantly for GUI purposes
        setAndRefreshPProp();

        gameUI.bank.addHistory(p.getNum(), "Bought a house");
    }

    public void reduceAmount(Player p, int x)
    {
        p.subAccount(x);

        setPMoney(p); //Added for updating PlayerAccount Instantly for GUI purposes
        setAndRefreshPProp();
    }

    public void chanceCardsAction(Player p, String a)
    {
        System.out.println("Before Chance Card: " + p.getAccount());

        int x = 50;

        if (a.equals("Advance to Go"))
        {
            gameUI.movePlain(p, 40 - p.getCellIndex());
        }
        if (a.equals("Advance to Illinois Ave"))
        {
            gameUI.movePlain(p, 40 - p.getCellIndex());
        }
        if (a.equals("Advance to nearest railroad"))
        {
            gameUI.movePlain(p, 40 - p.getCellIndex());
        }
        if (a.equals("Advance to St. Charles"))
        {
            gameUI.movePlain(p, 40 - p.getCellIndex());
        }
        if (a.equals("Advance to nearest utility"))
        {
            gameUI.movePlain(p, 40 - p.getCellIndex());
        }
        if (a.equals("Take a walk on the Boardwalk"))
        {
            gameUI.movePlain(p, 40 - p.getCellIndex());
        }
        if (a.equals("Bank pays you dividend of $50"))
        {
            x = 50;
            p.addAccount(x);
            setPMoney(p);
        }
        if (a.equals("Your Xmans fund matures collect $100"))
        {
            x = 100;
            p.addAccount(x);
            setPMoney(p);
        }
        if (a.equals("Get out of jail free"))
        {
            //gameUI.movePlain(p, 40 - p.getCellIndex());
            System.out.println("Under Construction");
        }
        if (a.equals("You are elected chairman of the board. Pay each player $50"))
        {
            x = 50;

            for (int z = 0; z < playerList.size(); z++)
            {
                if (p != playerList.get(z))
                {
                    ((Player) playerList.get(z)).addAccount(x);
                    setPMoney(playerList.get(z));
                    p.subAccount(x);

                }
            }
            setPMoney(p);

        }
        if (a.equals("Parking fine $15"))
        {
            x = 50;
            p.subAccount(x);
            setPMoney(p);
        }
        if (a.equals("Pay poor tax of $15"))
        {
            x = 15;
            p.subAccount(x);
            setPMoney(p);
        }
        if (a.equals("Take on the Reading Railroad"))
        {
            gameUI.movePlain(p, 40 - p.getCellIndex());
        }
        if (a.equals("Pay the school tax of $150"))
        {
            x = 150;
            p.subAccount(x);
            ;
            setPMoney(p);
        }

        System.out.println("After Chance Card: " + p.getAccount());
    }

    public void commCardsAction(Player p, String a)
    {
        System.out.println("Before Community Chest Card: " + p.getAccount());

        int x = 50;

        if (a.equals("Advance to Go"))
        {
            gameUI.movePlain(p, 40 - p.getCellIndex());
        }
        if (a.equals("Bank error in your favor. Collect $200"))
        {
            x = 200;
            p.addAccount(x);
            setPMoney(p);
        }
        if (a.equals("You have won a 2nd prize in a beauty contest. Collect $10"))
        {
            x = 10;
            p.addAccount(x);
            setPMoney(p);
        }
        if (a.equals("Doctor's fee. Pay $50"))
        {
            x = 50;
            p.subAccount(x);
            setPMoney(p);
        }
        if (a.equals("Go to jail"))
        {
            System.out.println("Under Construction");
        }
        if (a.equals("Pay hospital $100"))
        {
            x = 100;
            p.subAccount(x);
            setPMoney(p);
        }
        if (a.equals("Income tax refund. Collect $20"))
        {
            x = 20;
            p.addAccount(x);
            setPMoney(p);
        }
        if (a.equals("You inherit $100"))
        {
            x = 100;
            p.addAccount(x);
            setPMoney(p);
        }
        if (a.equals("Get out of jail free"))
        {
            //gameUI.movePlain(p, 40 - p.getCellIndex());
            System.out.println("Under Construction");
        }
        if (a.equals("Life insurance matures. Collect $100"))
        {
            x = 100;
            p.addAccount(x);
            setPMoney(p);

        }
        if (a.equals("Grand opera opening. Collect $50 from each"))
        {
            x = 50;

            for (int z = 0; z < playerList.size(); z++)
            {
                if (p != playerList.get(z))
                {
                    ((Player) playerList.get(z)).subAccount(x);
                    setPMoney(playerList.get(z));
                    p.addAccount(x);

                }

            }
            setPMoney(p);
        }
        if (a.equals("Receive for services $25"))
        {
            x = 25;
            p.addAccount(x);
            setPMoney(p);
        }
        if (a.equals("Pay school tax of $150"))
        {
            x = 150;
            p.subAccount(x);
            setPMoney(p);
        }
        if (a.equals("You Xmas fund matures. Collect $100"))
        {
            x = 100;
            p.addAccount(x);
            ;
            setPMoney(p);
        }

        System.out.println("After Community Chest Card Card: " + p.getAccount());
    }

    public void setPlayerList(ArrayList<Player> p)
    {
        playerList = p;
    }

    public void setPMoney(Player p)
    {
        tempIntofPlayer = new Integer(p.getNum());

        tempAmount = PlayerMoneyList[tempIntofPlayer].getText();  //this is a String

        startAmount = Integer.parseInt(tempAmount); //3000  //2500
        endAmount = playerList.get(tempIntofPlayer).getAccount(); //2500  //3000

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask()
        {

            public void run()
            {
                if (endAmount >= startAmount)
                {
                    if (startAmount == endAmount)
                    {

                        try
                        {
                            PlayerMoneyList[tempIntofPlayer].setText(Integer.toString(endAmount));
                        }
                        catch (ArithmeticException arth2)
                        {
                            JOptionPane.showMessageDialog(null, "Arithmetic Error (Source: GameBoardUI.java [setMoney()])\nChoose Player and Corresponding Icons", "Warning", JOptionPane.WARNING_MESSAGE);
                        }
                        PlayerMoneyList[tempIntofPlayer].setHorizontalTextPosition(JLabel.CENTER);
                        PlayerMoneyList[tempIntofPlayer].validate();
                        PlayerMoneyList[tempIntofPlayer].repaint();

                        timer.cancel();
                    }
                    else
                    {
                        try
                        {
                            PlayerMoneyList[tempIntofPlayer].setText(Integer.toString(startAmount));
                        }
                        catch (ArithmeticException arth2)
                        {
                            JOptionPane.showMessageDialog(null, "Arithmetic Error (Source: GameBoardUI.java [setMoney()])\nChoose Player and Corresponding Icons", "Warning", JOptionPane.WARNING_MESSAGE);
                        }
                        PlayerMoneyList[tempIntofPlayer].setHorizontalTextPosition(JLabel.CENTER);
                        PlayerMoneyList[tempIntofPlayer].validate();
                        PlayerMoneyList[tempIntofPlayer].repaint();

                        startAmount++;
                    }
                }
                if (endAmount <= startAmount)
                {
                    if (endAmount == startAmount)
                    {
                        try
                        {
                            PlayerMoneyList[tempIntofPlayer].setText(Integer.toString(endAmount));
                        }
                        catch (ArithmeticException arth2)
                        {
                            JOptionPane.showMessageDialog(null, "Arithmetic Error (Source: GameBoardUI.java [setMoney()])\nChoose Player and Corresponding Icons", "Warning", JOptionPane.WARNING_MESSAGE);
                        }
                        PlayerMoneyList[tempIntofPlayer].setHorizontalTextPosition(JLabel.CENTER);
                        PlayerMoneyList[tempIntofPlayer].validate();
                        PlayerMoneyList[tempIntofPlayer].repaint();

                        timer.cancel();
                    }
                    else
                    {
                        try
                        {
                            PlayerMoneyList[tempIntofPlayer].setText(Integer.toString(startAmount));
                        }
                        catch (ArithmeticException arth2)
                        {
                            JOptionPane.showMessageDialog(null, "Arithmetic Error (Source: GameBoardUI.java [setMoney()])\nChoose Player and Corresponding Icons", "Warning", JOptionPane.WARNING_MESSAGE);
                        }
                        PlayerMoneyList[tempIntofPlayer].setHorizontalTextPosition(JLabel.CENTER);
                        PlayerMoneyList[tempIntofPlayer].validate();
                        PlayerMoneyList[tempIntofPlayer].repaint();

                        startAmount--;

                    }
                }

                if (endAmount < 0)
                {
                    System.exit(0);
                }

            }
        }, delay, period);

    }

    public void plainAdd(Player p, int x)
    {
        p.addAccount(x);
        setPMoney(p);
    }

    public void plainSub(Player p, int x)
    {
        p.subAccount(x);
        setPMoney(p);
    }

    public void setAndRefreshPProp()
    {
        PropJLabel PlayerGlassPropertyLabel = PlayerPropList[currentPLAYERNUM];

        PlayerGlassPropertyLabel.setPlayer(playerList.get(currentPLAYERNUM));
        PlayerGlassPropertyLabel.repaint();

        PlayerGlassPropertyLabel = null;

        Runtime r = Runtime.getRuntime();
        r.gc();
    }

}
    
