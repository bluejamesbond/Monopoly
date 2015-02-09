package src.com.monopoly; /**
 * @(#)ChanceCards.java
 *
 *
 * @author
 * @version 1.00 2010/3/18
 */

import javax.swing.*;
import java.util.ArrayList;


public class CommChestCards
{

    public ImageIcon AdvanceGo = new ImageIcon("images\\commchest\\adv_go.png");
    public ImageIcon BankErr = new ImageIcon("images\\commchest\\bank_err.png");
    public ImageIcon Contest = new ImageIcon("images\\commchest\\contest.png");
    public ImageIcon DrFee = new ImageIcon("images\\commchest\\dr_fee.png");
    public ImageIcon GoJail = new ImageIcon("images\\commchest\\go_jail.png");
    public ImageIcon Hospital = new ImageIcon("images\\commchest\\hosp.png");
    public ImageIcon ICTax = new ImageIcon("images\\commchest\\icome_tax.png");
    public ImageIcon Inherit = new ImageIcon("images\\commchest\\inherit.png");
    public ImageIcon Jail = new ImageIcon("images\\commchest\\jail.png");
    public ImageIcon LifeIns = new ImageIcon("images\\commchest\\life_ins.png");
    public ImageIcon Opera = new ImageIcon("images\\commchest\\opera.png");
    public ImageIcon Services = new ImageIcon("images\\commchest\\services.png");
    public ImageIcon SchoolTax = new ImageIcon("images\\commchest\\stax.png");
    public ImageIcon Fund = new ImageIcon("images\\commchest\\xmasfund.png");

    JLabel AdvanceGoLabel = new JLabel(AdvanceGo);
    JLabel BankErrLabel = new JLabel(BankErr);
    JLabel ContestLabel = new JLabel(Contest);
    JLabel DrFeeLabel = new JLabel(DrFee);
    JLabel GoJailLabel = new JLabel(GoJail);
    JLabel HospitalLabel = new JLabel(Hospital);
    JLabel ICTaxLabel = new JLabel(ICTax);
    JLabel InheritLabel = new JLabel(Inherit);
    JLabel JailLabel = new JLabel(Jail);
    JLabel LifeInsLabel = new JLabel(LifeIns);
    JLabel OperaLabel = new JLabel(Opera);
    JLabel ServicesLabel = new JLabel(Services);
    JLabel SchoolTaxLabel = new JLabel(SchoolTax);
    JLabel FundLabel = new JLabel(Fund);

    ArrayList<JLabel> commChestCards = new ArrayList<JLabel>();

    public CommChestCards()
    {


        AdvanceGoLabel.setName("Advance to Go");
        BankErrLabel.setName("Bank error in your favor. Collect $200");
        ContestLabel.setName("You have won a 2nd prize in a beauty contest. Collect $10");
        DrFeeLabel.setName("Doctor's fee. Pay $50");
        GoJailLabel.setName("Go to jail");
        HospitalLabel.setName("Pay hospital $100");
        ICTaxLabel.setName("Income tax refund. Collect $20");
        InheritLabel.setName("You inherit $100");
        JailLabel.setName("Get out of jail free");
        LifeInsLabel.setName("Life insurance matures. Collect $100");
        OperaLabel.setName("Grand opera opening. Collect $50 from each");
        ServicesLabel.setName("Receive for services $25");
        SchoolTaxLabel.setName("Pay school tax of $150");
        FundLabel.setName("You Xmas fund matures. Collect $100");
    }

    public ArrayList<JLabel> getCardList()
    {
        commChestCards.add(AdvanceGoLabel);
        commChestCards.add(BankErrLabel);
        commChestCards.add(ContestLabel);
        commChestCards.add(DrFeeLabel);
        commChestCards.add(GoJailLabel);
        commChestCards.add(HospitalLabel);
        commChestCards.add(ICTaxLabel);
        commChestCards.add(InheritLabel);
        commChestCards.add(JailLabel);
        commChestCards.add(LifeInsLabel);
        commChestCards.add(OperaLabel);
        commChestCards.add(ServicesLabel);
        commChestCards.add(SchoolTaxLabel);
        commChestCards.add(FundLabel);

        return commChestCards;
    }

    public Icon getCertainCard(String name)
    {
        Icon toReturn;

        if (name.equals("Advance to Go"))
        {
            toReturn = (Icon) commChestCards.get(0).getIcon();
        }
        else if (name.equals("Bank error in your favor. Collect $200"))
        {
            toReturn = (Icon) commChestCards.get(1).getIcon();
        }
        else if (name.equals("You have won a 2nd prize in a beauty contest. Collect $10"))
        {
            toReturn = (Icon) commChestCards.get(2).getIcon();
        }
        else if (name.equals("Doctor's fee. Pay $50"))
        {
            toReturn = (Icon) commChestCards.get(3).getIcon();
        }
        else if (name.equals("Go to jail"))
        {
            toReturn = (Icon) commChestCards.get(4).getIcon();
        }
        else if (name.equals("Pay hospital $100"))
        {
            toReturn = (Icon) commChestCards.get(5).getIcon();
        }
        else if (name.equals("Income tax refund. Collect $20"))
        {
            toReturn = (Icon) commChestCards.get(6).getIcon();
        }
        else if (name.equals("You inherit $100"))
        {
            toReturn = (Icon) commChestCards.get(7).getIcon();
        }
        else if (name.equals("Get out of jail free"))
        {
            toReturn = (Icon) commChestCards.get(8).getIcon();
        }
        else if (name.equals("Life insurance matures. Collect $100"))
        {
            toReturn = (Icon) commChestCards.get(9).getIcon();
        }
        else if (name.equals("Grand opera opening. Collect $50 from each"))
        {
            toReturn = (Icon) commChestCards.get(10).getIcon();
        }
        else if (name.equals("Receive for services $25"))
        {
            toReturn = (Icon) commChestCards.get(11).getIcon();
        }
        else if (name.equals("Pay school tax of $150"))
        {
            toReturn = (Icon) commChestCards.get(12).getIcon();
        }
        else if (name.equals("You Xmas fund matures. Collect $100"))
        {
            toReturn = (Icon) commChestCards.get(13).getIcon();
        }
        else
        {
            toReturn = null;
        }

        return toReturn;

    }

}