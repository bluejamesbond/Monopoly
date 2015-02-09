package src.com.monopoly; /**
 * @(#)ChanceCards.java
 *
 *
 * @author
 * @version 1.00 2010/3/18
 */


import javax.swing.*;
import java.util.ArrayList;

public class ChanceCards
{

    public ImageIcon AdvanceGo = new ImageIcon("images\\chance\\adv_go.png");
    public ImageIcon AdvanceIllinois = new ImageIcon("images\\chance\\adv_ill.png");
    public ImageIcon AdvanceCharles = new ImageIcon("images\\chance\\adv_stch.png");
    public ImageIcon AdvanceRailRoad = new ImageIcon("images\\chance\\adv_rail.png");
    public ImageIcon AdvanceUtil = new ImageIcon("images\\chance\\adv_util.png");
    public ImageIcon WalkBoard = new ImageIcon("images\\chance\\board.png");
    public ImageIcon Bank = new ImageIcon("images\\chance\\div.png");
    public ImageIcon Fund = new ImageIcon("images\\chance\\fund.png");
    public ImageIcon Jail = new ImageIcon("images\\chance\\jail.png");
    public ImageIcon PayAll = new ImageIcon("images\\chance\\payall.png");
    public ImageIcon ParkFine = new ImageIcon("images\\chance\\pfine.png");
    public ImageIcon PoorTax = new ImageIcon("images\\chance\\ptax.png");
    public ImageIcon ReadRail = new ImageIcon("images\\chance\\rr.png");
    public ImageIcon SchoolTax = new ImageIcon("images\\chance\\stax.png");

    JLabel AdvanceGoLabel = new JLabel(AdvanceGo);
    JLabel AdvanceIllinoisLabel = new JLabel(AdvanceIllinois);
    JLabel AdvanceCharlesLabel = new JLabel(AdvanceCharles);
    JLabel AdvanceRailRoadLabel = new JLabel(AdvanceRailRoad);
    JLabel AdvanceUtilLabel = new JLabel(AdvanceUtil);
    JLabel WalkBoardLabel = new JLabel(WalkBoard);
    JLabel BankLabel = new JLabel(Bank);
    JLabel FundLabel = new JLabel(Fund);
    JLabel JailLabel = new JLabel(Jail);
    JLabel PayAllLabel = new JLabel(PayAll);
    JLabel ParkFineLabel = new JLabel(ParkFine);
    JLabel PoorTaxLabel = new JLabel(PoorTax);
    JLabel ReadRailLabel = new JLabel(ReadRail);
    JLabel SchoolTaxLabel = new JLabel(SchoolTax);

    ArrayList<JLabel> chanceCards = new ArrayList<JLabel>();

    public ChanceCards()
    {


        AdvanceGoLabel.setName("Advance to Go");
        AdvanceIllinoisLabel.setName("Advance to Illinois Ave");
        AdvanceRailRoadLabel.setName("Advance to nearest railroad");
        AdvanceCharlesLabel.setName("Advance to St. Charles");
        AdvanceUtilLabel.setName("Advance to nearest utility");
        WalkBoardLabel.setName("Take a walk on the Boardwalk");
        BankLabel.setName("Bank pays you dividend of $50");
        FundLabel.setName("Your Xmans fund matures collect $100");
        JailLabel.setName("Get out of jail free");
        PayAllLabel.setName("You are elected chairman of the board. Pay each player $50");
        ParkFineLabel.setName("Parking fine $15");
        PoorTaxLabel.setName("Pay poor tax of $15");
        ReadRailLabel.setName("Take on the Reading Railroad");
        SchoolTaxLabel.setName("Pay the school tax of $150");
    }

    public ArrayList<JLabel> getCardList()
    {
        chanceCards.add(AdvanceGoLabel);
        chanceCards.add(AdvanceIllinoisLabel);
        chanceCards.add(AdvanceRailRoadLabel);
        chanceCards.add(AdvanceCharlesLabel);
        chanceCards.add(AdvanceUtilLabel);
        chanceCards.add(WalkBoardLabel);
        chanceCards.add(BankLabel);
        chanceCards.add(FundLabel);
        chanceCards.add(JailLabel);
        chanceCards.add(PayAllLabel);
        chanceCards.add(ParkFineLabel);
        chanceCards.add(PoorTaxLabel);
        chanceCards.add(ReadRailLabel);
        chanceCards.add(SchoolTaxLabel);

        return chanceCards;
    }

    public Icon getCertainCard(String name)
    {
        Icon toReturn;

        if (name.equals("Advance to Go"))
        {
            toReturn = (Icon) chanceCards.get(0).getIcon();
        }
        else if (name.equals("Advance to Illinois Ave"))
        {
            toReturn = (Icon) chanceCards.get(1).getIcon();
        }
        else if (name.equals("Advance to nearest railroad"))
        {
            toReturn = (Icon) chanceCards.get(2).getIcon();
        }
        else if (name.equals("Advance to St. Charles"))
        {
            toReturn = (Icon) chanceCards.get(3).getIcon();
        }
        else if (name.equals("Advance to nearest utility"))
        {
            toReturn = (Icon) chanceCards.get(4).getIcon();
        }
        else if (name.equals("Take a walk on the Boardwalk"))
        {
            toReturn = (Icon) chanceCards.get(5).getIcon();
        }
        else if (name.equals("Bank pays you dividend of $50"))
        {
            toReturn = (Icon) chanceCards.get(6).getIcon();
        }
        else if (name.equals("Your Xmans fund matures collect $100"))
        {
            toReturn = (Icon) chanceCards.get(7).getIcon();
        }
        else if (name.equals("Get out of jail free"))
        {
            toReturn = (Icon) chanceCards.get(8).getIcon();
        }
        else if (name.equals("You are elected chairman of the board. Pay each player $50"))
        {
            toReturn = (Icon) chanceCards.get(9).getIcon();
        }
        else if (name.equals("Parking fine $15"))
        {
            toReturn = (Icon) chanceCards.get(10).getIcon();
        }
        else if (name.equals("Pay poor tax of $15"))
        {
            toReturn = (Icon) chanceCards.get(11).getIcon();
        }
        else if (name.equals("Take on the Reading Railroad"))
        {
            toReturn = (Icon) chanceCards.get(12).getIcon();
        }
        else if (name.equals("Pay the school tax of $150"))
        {
            toReturn = (Icon) chanceCards.get(13).getIcon();
        }
        else
        {
            toReturn = null;
        }

        return toReturn;

    }

}