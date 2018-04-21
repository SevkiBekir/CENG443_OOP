
import java.awt.*;
import java.util.*;

/**
 *
 */
public class BuyOrder extends Order {

    /**
     * Default constructor
     */
    public BuyOrder(Trader trader) {
        super();
        setColor(Color.green);
        setOrderType(1);
        setTrader(trader);

    }

    public void draw(Graphics2D g2d) {
        super.draw(g2d);
    }

    public void step() {
        super.step();
        orderOperation();
    }

    protected void orderOperation()
    {
        super.orderOperation();
        if (getHittedPrice() != 0) {
            Trader trader = getTrader();
            int hittedPrice = getHittedPrice();
            int coinSize = getSize();
            int totalPayment = coinSize * hittedPrice;
		    // is enough money to buy
            if(totalPayment<= trader.getCapital()){
		        trader.setCapital(trader.getCapital() - totalPayment);
		        trader.setCoins(trader.getCoins() + coinSize);
//		        trader.setNetMoney(trader.getCapital() + trader.getCoins()*getCommon().getPlot().getCurrentCoinPrice());
            }

        }
    }


}