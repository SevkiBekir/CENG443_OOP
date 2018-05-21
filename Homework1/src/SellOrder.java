
import java.awt.*;
import java.util.*;

/**
 * 
 */
public class SellOrder extends Order {

	/**
	 * Default constructor
	 */
	public SellOrder(Trader trader) {
		super();
		setColor(Color.pink);
		setOrderType(2);
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
			// is enough coin to sell
			if(coinSize <= trader.getCoins()){
				trader.setCapital(trader.getCapital() + totalPayment);
				trader.setCoins(trader.getCoins() - coinSize);
			}

		}
	}

}