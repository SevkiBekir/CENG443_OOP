
import java.util.*;

/**
 * 
 */
public class SellOrderFactory extends OrderFactory {

	/**
	 * Default constructor
	 */
	public SellOrderFactory() {
	}

	/**
	 * @param trader
	 * @return
	 */
	public Order createOrder(Trader trader) {
		return new SellOrder(trader);
	}

}